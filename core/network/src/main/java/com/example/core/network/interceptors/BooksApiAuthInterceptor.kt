package com.example.core.network.interceptors

import com.example.core.common.buildConfig.ArchBuildConfigProvider
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class BooksApiAuthInterceptor @Inject constructor(
    private val buildConfigProvider: ArchBuildConfigProvider
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val booksApiKey = buildConfigProvider.getBuildConfig().booksApiKey

        if (booksApiKey.isEmpty()) {
            return chain.proceed(originalRequest)
        }

        val authenticatedRequest = originalRequest.newBuilder()
            .header("x-api-key", booksApiKey)
            .build()

        return chain.proceed(authenticatedRequest)
    }
}
