package com.nfs.ascent.base.vm

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.nfs.ascent.core.base.BaseViewModel
import javax.inject.Inject

class MainFragmentViewModel
@Inject constructor(
    application: Application,
) :
    BaseViewModel(application) {

    val vmName: MutableLiveData<String> = MutableLiveData("Main Fragment View Model")

}