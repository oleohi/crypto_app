package com.yoti.android.cryptocurrencychallenge.utils

sealed class Resource<out T>(
) {
    class Success<T>(val data: T) : Resource<T>()
    class Loading<T>(val data: T? = null) : Resource<T>()
    class Error(val throwable: Throwable) : Resource<Nothing>()
}