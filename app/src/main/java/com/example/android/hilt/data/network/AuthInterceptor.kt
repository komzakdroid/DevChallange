package com.example.android.hilt.data.network

import okhttp3.Interceptor
import okhttp3.Response


/**
 * Created by Komiljon on 09/02/2023
 */
class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newUrl = originalRequest.url
            .newBuilder()
            .addQueryParameter(
                "apiKey",
                "5b3a161f1f874b50b0e960674be5500a"
            )
            .build()
        val request = originalRequest.newBuilder()
            .url(newUrl)
            .build()
        return chain.proceed(request)
    }
}