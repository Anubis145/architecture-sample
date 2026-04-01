package com.example.core.network.model

import retrofit2.HttpException
import retrofit2.Response
import java.lang.reflect.Type

sealed class ApiResult<T> {
    class Success<T: Any>(val data: T) : ApiResult<T>()
    class Error<T: Any>(val httpCode: Int, val message: String?) : ApiResult<T>()
    class ApiException<T: Any>(val throwable: Throwable) : ApiResult<T>() {
        override fun toString(): String = "ApiException: ${throwable.message}"
    }
}

internal fun <T : Any> handleApi(
    resultType: Type,
    execute: () -> Response<T>,
) : ApiResult<T> {
    val response = execute()
    val body = response.body()

    return try {
        if (response.isSuccessful && resultType == Unit::class.java) {
            @Suppress("UNCHECKED_CAST")
            ApiResult.Success(Unit as T)
        } else if (response.isSuccessful && body != null) {
            ApiResult.Success(body)
        } else {
            ApiResult.Error(response.code(), response.message())
        }
    } catch (e: HttpException) {
        ApiResult.Error(e.code(), e.message())
    }
}
