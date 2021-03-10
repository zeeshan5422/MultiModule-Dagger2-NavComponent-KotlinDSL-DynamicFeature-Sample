package com.nfs.ascent.core.base

/**
 * Created by ZEESHAN on 26,October,2020
 */
sealed class RequestState {
    object Start : RequestState()
    object Loading : RequestState()
    object Success : RequestState()
    data class Error(val message: String?) : RequestState()
}