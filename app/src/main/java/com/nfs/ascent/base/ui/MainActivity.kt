package com.nfs.ascent.base.ui

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.nfs.ascent.base.BR
import com.nfs.ascent.base.R
import com.nfs.ascent.base.databinding.ActivityMainBinding
import com.nfs.ascent.base.di.inject
import com.nfs.ascent.base.vm.MainActivityViewModel
import com.nfs.ascent.core.base.MvvmBaseActivity
import com.nfs.ascent.core.di.ACTIVITY_CONTEXT
import com.nfs.ascent.core.di.APP_CONTEXT
import com.nfs.ascent.core.utils.NfsLogUtils
import javax.inject.Inject
import javax.inject.Named

class MainActivity  : MvvmBaseActivity<ActivityMainBinding, MainActivityViewModel>(
    R.layout.activity_main,
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

    override fun getViewModel(): MainActivityViewModel =
        ViewModelProvider(this, viewModelFactory).get(MainActivityViewModel::class.java)


    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
    }

    override fun subscribeUI() {
        super.subscribeUI()

        NfsLogUtils.d("MainActivity || app context <<< " + appContext.hashCode() + " >>>")
        NfsLogUtils.d("MainActivity || activity context <<< " + activityContext.hashCode() + " >>>")

    }
}