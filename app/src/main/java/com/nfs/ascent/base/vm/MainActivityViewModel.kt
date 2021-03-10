package com.nfs.ascent.base.vm

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.nfs.ascent.core.base.BaseViewModel
import javax.inject.Inject

class MainActivityViewModel
@Inject constructor(
    application: Application,
) :
    BaseViewModel(application) {

    val vmName: MutableLiveData<String> = MutableLiveData("Main Activity View Model")

}