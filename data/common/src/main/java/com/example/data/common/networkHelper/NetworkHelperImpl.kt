package com.example.data.common.networkHelper

import com.example.core.network.model.ApiResult
import com.example.domain.common.ArchResult
import com.example.domain.common.DataError
import javax.inject.Inject

class NetworkHelperImpl @Inject constructor(

) : NetworkHelper {

    override suspend fun <I : Any, O> fetchResult(
        mapper: (I) -> O,
        apiCall: suspend () -> ApiResult<I>
    ): ArchResult<O, DataError.NetworkError> {
        return when (val result = apiCall()) {
            is ApiResult.Success -> ArchResult.Success(mapper(result.data))
            is ApiResult.Error -> {
                val errorResult = when (result.httpCode) {
                    400 -> DataError.NetworkError.BadRequest
                    401 -> DataError.NetworkError.Unauthorized
                    403 -> DataError.NetworkError.Forbidden
                    404 -> DataError.NetworkError.DataNotFound
                    409 -> DataError.NetworkError.Conflict
                    503 -> DataError.NetworkError.ServiceUnavailable
                    else -> DataError.NetworkError.Unknown
                }
                ArchResult.Error(errorResult)
            }

            is ApiResult.ApiException -> ArchResult.Error(DataError.NetworkError.Unknown)
        }
    }

    override suspend fun <I : Any> fetchResult(
        apiCall: suspend () -> ApiResult<I>
    ): ArchResult<Unit, DataError.NetworkError> {
        return fetchResult(
            mapper = {},
            apiCall = apiCall
        )
    }
}
