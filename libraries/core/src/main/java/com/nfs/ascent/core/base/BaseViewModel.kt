package com.nfs.ascent.core.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.nfs.ascent.core.domain.repo.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by ZEESHAN on 26,October,2020
 */
abstract class BaseViewModel constructor(application: Application) :
    AndroidViewModel(application) {

    // This will handle all types of events based on the actions performed.
    private val requestEventChannel = Channel<RequestState>()
    val requestEvent: Flow<RequestState> = requestEventChannel.receiveAsFlow()

    // This will handle all types of Toasts.
    private val infoMessageChannel = Channel<String>()
    val infoMessage: Flow<String> = infoMessageChannel.receiveAsFlow()

    var isInProcess = false

    fun requestStarted() = viewModelScope.launch(Dispatchers.Main) {
        if (!isInProcess) {
            isInProcess = true
            requestEventChannel.send(RequestState.Loading)
        }
    }

    fun requestFinished() = viewModelScope.launch(Dispatchers.Main) {
        isInProcess = false
        requestEventChannel.send(RequestState.Success)
    }

    fun requestFinished(code: Int = -1, message: String) = viewModelScope.launch(Dispatchers.Main) {
        isInProcess = false
        requestEventChannel.send(RequestState.Error(message = message))
    }

    fun showInfo(message: String) = viewModelScope.launch(Dispatchers.Main) {
        infoMessageChannel.send(message)
    }

    protected open suspend fun <T> handleSimpleRequest(call: suspend () -> ApiResponse<T>) {
        withContext(Dispatchers.Main) {
            requestStarted()
            withContext(Dispatchers.IO) {
                val res = call.invoke()
                if (res.isSuccessful) {
                    requestFinished()
                } else {
                    requestFinished(message = res.errorResponse?.message.toString())
                }
            }
        }
    }

    protected suspend fun <T> handleRequest(
        call: suspend () -> ApiResponse<T>,
        result: suspend (ApiResponse<T>) -> Unit
    ) {
        requestStarted()
        withContext(Dispatchers.IO) {
            val res = call.invoke()
            delay(1000L)
            if (res.isSuccessful) {
                requestFinished()
            } else {
                requestFinished(message = res.errorResponse?.error.toString())
            }
            withContext(Dispatchers.Main) {
                result.invoke(res)
            }
        }
    }
}