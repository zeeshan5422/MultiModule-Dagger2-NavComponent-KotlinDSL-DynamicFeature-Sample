package com.nfs.ascent.core.ext

import com.nfs.ascent.core.domain.repo.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by ZEESHAN on 24,November,2020
 */

suspend fun <T : Any> runIOThenMain(work: suspend () -> ApiResponse<T>): ApiResponse<T> =
    withContext(Dispatchers.IO) {
        val resultData = work()
        withContext(Dispatchers.Main) {
            resultData
        }
    }

val <T> T.exhaustive: T
    get() = this
