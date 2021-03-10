package com.nfs.ascent.nfslogin.vm

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nfs.ascent.core.base.BaseViewModel
import com.nfs.ascent.core.domain.repo.ApiResponse
import com.nfs.ascent.nfslogin.domain.LoginUseCase
import com.nfs.ascent.nfslogin.domain.model.SettingsPojo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel
@Inject constructor(
    application: Application,
    private val loginUseCase: LoginUseCase
) :
    BaseViewModel(application) {

    val vmName: MutableLiveData<String> = MutableLiveData("Login View Model")

    fun getSettings() = viewModelScope.launch {

        val settings: Flow<ApiResponse<SettingsPojo>> = loginUseCase.getSettings()
        settings.onStart {
            requestStarted()
        }.onCompletion {
            requestFinished()
        }

    }
}