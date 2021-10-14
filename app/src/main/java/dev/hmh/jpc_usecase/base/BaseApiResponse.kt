package dev.hmh.ammmvvm.base

import dev.hmh.ammmvvm.util.ApiResponse
import retrofit2.Response
import java.lang.Exception

@Suppress("UNREACHABLE_CODE")
abstract class BaseApiResponse {
    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): ApiResponse<T> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return ApiResponse.Success(body)
                }
            }
            return error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(errorMessage: String): ApiResponse<T> {
        return ApiResponse.Error("Api call failed $errorMessage")
    }
}