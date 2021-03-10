package com.nfs.ascent.nfslogin.ui

import com.nfs.ascent.core.utils.NfsLogUtils
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.nfs.ascent.core.base.MvvmBaseActivity
import com.nfs.ascent.core.di.ACTIVITY_CONTEXT
import com.nfs.ascent.core.di.APP_CONTEXT
import com.nfs.ascent.nfslogin.BR
import com.nfs.ascent.nfslogin.R
import com.nfs.ascent.nfslogin.databinding.ActivityLoginBinding
import com.nfs.ascent.nfslogin.di.inject
import com.nfs.ascent.nfslogin.vm.LoginViewModel
import javax.inject.Inject
import javax.inject.Named

class LoginActivity : MvvmBaseActivity<ActivityLoginBinding, LoginViewModel>(
    R.layout.activity_login,
    BR.viewModel
) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Named(APP_CONTEXT)
    @Inject
    lateinit var appContext: Context

    @Named(ACTIVITY_CONTEXT)
    @Inject
    lateinit var activityContext: Context

    override fun getViewModel(): LoginViewModel =
        ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)


    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
    }

    override fun subscribeUI() {
        super.subscribeUI()

        NfsLogUtils.d("LoginActivity || app context <<< " + appContext.hashCode() + " >>>")
        NfsLogUtils.d("LoginActivity || activity context <<< " + activityContext.hashCode() + " >>>")
    }

}