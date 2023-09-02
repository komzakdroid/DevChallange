package com.example.android.hilt.di

import android.app.Application
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.android.hilt.data.network.ApiService
import com.example.android.hilt.data.network.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providesBaseUrl(): String = "https://newsapi.org"

    private val interceptor = run {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .baseUrl(baseUrl)
        .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(
        chuckerInterceptor: ChuckerInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor())
            .addInterceptor(chuckerInterceptor)
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideChucker(application: Application): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(application.applicationContext).maxContentLength(250_000L)
            .alwaysReadResponseBody(true).build()
    }


    @Provides
    @Singleton
    fun provideMainService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)
}