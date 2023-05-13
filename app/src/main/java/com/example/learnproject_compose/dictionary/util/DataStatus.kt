package com.example.learnproject_compose.dictionary.util

enum class Status { SUCCESS, LOADING, ERROR }

sealed class DataStatus<T>(val status: Status, val data: T? = null, val message: String? = null) {
    class Loading<T> : DataStatus<T>(Status.LOADING)
    class Success<T>(data: T?) : DataStatus<T>(Status.SUCCESS, data)
    class Error<T>(message: String?): DataStatus<T>(Status.ERROR, message = message)
}