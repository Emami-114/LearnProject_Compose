package com.example.learnproject_compose.deutschTest.domain.model

sealed class Resources2<T>(
    val data: T? = null,
    val throwable: Throwable? = null,
) {
    class Loading<T> : Resources2<T>()
    class Success<T>(data: T?) : Resources2<T>(data = data)
    class Error<T>(throwable: Throwable?) : Resources2<T>(throwable = throwable)
}