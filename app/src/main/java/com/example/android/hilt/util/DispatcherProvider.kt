package com.example.android.hilt.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

open class DispatchersProvider @Inject constructor() {
    open val main: CoroutineDispatcher
        get() = Dispatchers.Main
    open val mainImmediate: CoroutineDispatcher
        get() = Dispatchers.Main.immediate
    open val io: CoroutineDispatcher
        get() = Dispatchers.IO
    open val default: CoroutineDispatcher
        get() = Dispatchers.Default
    open val unconfined: CoroutineDispatcher
        get() = Dispatchers.Unconfined
}