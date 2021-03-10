package com.nfs.ascent.core.ext

import com.google.gson.reflect.TypeToken

/**
 * Created by ZEESHAN on 19,January,2021
 */

inline fun <reified T> getTypeToken() = object : TypeToken<T>() {}.type
