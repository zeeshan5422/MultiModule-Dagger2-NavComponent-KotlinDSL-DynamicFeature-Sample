package com.nfs.ascent.core.base

import android.content.DialogInterface

/**
 * Created by ZEESHAN on 26,October,2020
 */
interface FragmentNavHelper {

    fun showAlertDialog(
        title: String? = null,
        message: String? = null,
        textOk: String? = null,
        textCancel: String? = null,
        okListener: DialogInterface.OnClickListener? = null,
        cancelListener: DialogInterface.OnClickListener? = null
    )

    fun showLoading(message: String)

    fun hideLoading()
}