package com.example.domain.common

interface DataError : DomainError {
    sealed class NetworkError : DataError {
        object BadRequest : NetworkError()
        object Unauthorized : NetworkError()
        object Forbidden : NetworkError()
        object DataNotFound : NetworkError()
        object Conflict : NetworkError()
        object ServiceUnavailable : NetworkError()

        object Unknown : NetworkError()
        object NoInternetConnection : NetworkError()
    }
}
