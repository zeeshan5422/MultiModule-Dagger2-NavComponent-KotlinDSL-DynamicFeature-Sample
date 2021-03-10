package com.nfs.ascent.core.utils

import androidx.lifecycle.MutableLiveData
import com.nfs.ascent.core.domain.repo.ApiResponse

/**
 * Created by ZEESHAN on 23,November,2020
 */

typealias ApiLiveData<T> = MutableLiveData<ApiResponse<T>>