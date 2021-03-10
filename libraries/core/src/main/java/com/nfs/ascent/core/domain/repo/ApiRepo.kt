package com.nfs.ascent.core.domain.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.google.gson.stream.MalformedJsonException
import com.nfs.ascent.core.utils.NfsLogUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response
import java.net.ConnectException
import java.net.UnknownHostException

/**
 * Created by ZEESHAN on 19,November,2020
 */
interface ApiRepo {

    suspend fun <T> handleRequest(call: suspend () -> Response<T>): ApiResponse<T> {
        return try {
            val apiResponse = call.invoke()
            if (apiResponse.isSuccessful) {
                ApiResponse.Success(apiResponse.body())
            } else {
                handleFailureResponse(apiResponse.errorBody())
            }
        } catch (e: Exception) {
            e.printStackTrace()
            handleException(e)
        }
    }

    private fun <T> handleFailureResponse(errorBody: ResponseBody?): ApiResponse<T> {
        errorBody?.let {
            val jsonObject = JSONObject(it.string())
            try {
                val code = jsonObject.getInt("status")
                val error = jsonObject.getString("error")
                val errorMessage = jsonObject.getString("message")

                val errorCode: ErrorCode = when (code) {
                    401 -> ErrorCode.UNAUTHORIZED
                    404 -> ErrorCode.NOT_FOUND
                    500 -> ErrorCode.BAD_RESPONSE
                    else -> ErrorCode.UNKNOWN
                }

                return ApiResponse.Failure(ErrorResponse(errorCode, error, errorMessage))
            } catch (ex: Exception) {
                NfsLogUtils.e(
                    "BaseRepository",
                    ex.message ?: "Unknown error while handling failure response"
                )
            }
        }
        return ApiResponse.Failure(ErrorResponse(ErrorCode.UNKNOWN))
    }

    private fun <T> handleException(exception: Exception?): ApiResponse<T> {
        exception?.let {
            val errorResponse = if (exception is ConnectException) {
                ErrorResponse(ErrorCode.NO_NETWORK, error = exception.message.toString())
            } else if (exception is MalformedJsonException) {
                ErrorResponse(ErrorCode.BAD_RESPONSE, error = exception.message.toString())
            }  else if (exception is UnknownHostException) {
                ErrorResponse(ErrorCode.NOT_FOUND, error = exception.message.toString())
            } else {
                ErrorResponse(ErrorCode.UNKNOWN, error = exception.message.toString())
            }
            return ApiResponse.Failure(errorResponse)
        }
        return ApiResponse.Failure(ErrorResponse(ErrorCode.UNKNOWN, error = exception?.message.toString()))
    }

    // use this method when you want response of type LiveData
    fun <T> resultLiveData(
        scope: CoroutineScope,
        call: suspend () -> ApiResponse<T>
    ): LiveData<ApiResponse<T>> {
        return liveData(scope.coroutineContext) {
            emit(ApiResponse.Loading)
            withContext(Dispatchers.IO) {
                emit(call.invoke())
            }
        }
    }

}