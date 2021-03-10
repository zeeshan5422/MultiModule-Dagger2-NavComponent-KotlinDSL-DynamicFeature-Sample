package com.nfs.ascent.core.base

/**
 * Created by ZEESHAN on 13,November,2020
 */
sealed class GlobalState {
    class SessionError(message: String)
    class GeneralException(message: String)
}