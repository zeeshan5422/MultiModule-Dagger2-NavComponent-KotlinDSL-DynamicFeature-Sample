package com.nfs.ascent.core.domain.repo

sealed class ApiResponse<out T>(
    val isSuccessful: Boolean,
    val data: T?,
    val errorResponse: ErrorResponse?
) {
    data class Success<out T>(val responseData: T?) : ApiResponse<T>(true, responseData, null)
    data class Failure<out T>(val errorData: ErrorResponse?) : ApiResponse<T>(false, null, errorData)

    // NOTE:: Use this state only when we are using live data of type response from repo.
    object Loading : ApiResponse<Nothing>(false, null, null)
}


// other way to handle generic data
/*
data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): Resource<T> = Resource(status = SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String): Resource<T> =
            Resource(status = ERROR, data = data, message = message)

        fun <T> loading(data: T?): Resource<T> = Resource(status = LOADING, data = data, message = null)
    }
}
enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}
* */