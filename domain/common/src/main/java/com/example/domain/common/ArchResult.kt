package com.example.domain.common

sealed interface ArchResult<D, E : DomainError> {
    class Success<D, E : DomainError>(val data: D) : ArchResult<D, E>
    class Error<D, E : DomainError>(val error: E) : ArchResult<D, E>

    fun getOrNull(): D? {
        return when (this) {
            is Error<D, E> -> null
            is Success<D, E> -> this.data
        }
    }
}
