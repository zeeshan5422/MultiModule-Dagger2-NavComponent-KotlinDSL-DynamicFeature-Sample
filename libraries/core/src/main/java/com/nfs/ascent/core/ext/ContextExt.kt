package com.nfs.ascent.core.ext

import android.content.Context
import android.content.res.Configuration
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

/**
 * Created by ZEESHAN on 24,November,2020
 */

fun Context.getString(@StringRes id: Int): String {
    return this.resources.getString(id)
}


fun Context.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun Context.isPortrait() =
    resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT



fun <T : ViewModel> Fragment.obtainViewModel(owner: ViewModelStoreOwner,
                                             viewModelClass: Class<T>,
                                             viewmodelFactory: ViewModelProvider.Factory
) =
    ViewModelProvider(owner, viewmodelFactory).get(viewModelClass)