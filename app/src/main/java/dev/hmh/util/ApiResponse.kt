package dev.hmh.ammmvvm.util

sealed class ApiResponse<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?) : ApiResponse<T>(data = data)
    class Error<T>(message: String?, data: T? = null) : ApiResponse<T>(message = message, data = data)
    class Loading<T> : ApiResponse<T>()
}