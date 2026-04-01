package com.example.core.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class BooksApiHostSelectionInterceptor(

) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        //TODO

        return chain.proceed(originalRequest)
    }
}