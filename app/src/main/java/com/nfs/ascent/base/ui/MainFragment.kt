package com.nfs.ascent.base.ui

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.nfs.ascent.base.BR
import com.nfs.ascent.base.R
import com.nfs.ascent.base.databinding.FragmentMainBinding
import com.nfs.ascent.base.di.inject
import com.nfs.ascent.base.vm.MainFragmentViewModel
import com.nfs.ascent.core.base.MvvmBaseFragment
import com.nfs.ascent.core.data.LocalizationItem
import com.nfs.ascent.core.data.LocalizationItemDao
import com.nfs.ascent.core.db.CoreDatabase
import com.nfs.ascent.core.di.ACTIVITY_CONTEXT
import com.nfs.ascent.core.di.APP_CONTEXT
import com.nfs.ascent.core.utils.NfsLogUtils
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

class MainFragment : MvvmBaseFragment<FragmentMainBinding, MainFragmentViewModel>(
    R.layout.fragment_main,
    BR.viewModel
) {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        inject()
    }

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    @Named(APP_CONTEXT)
    @Inject
    lateinit var appContext: Context

    @Named(ACTIVITY_CONTEXT)
    @Inject
    lateinit var activityContext: Context


    @Inject
    lateinit var localizationItemDao: LocalizationItemDao

    @Inject
    lateinit var coreDatabase: CoreDatabase

    override fun getViewModel(): MainFragmentViewModel {
        return ViewModelProvider(this, factory).get(MainFragmentViewModel::class.java)
    }

    override fun subscribeUI() {
        super.subscribeUI()


        lifecycleScope.launch {
            localizationItemDao.insert(LocalizationItem(usename = "asdf", body = "Hellow world"))
        }

        NfsLogUtils.d("MainFragment || app context <<< " + appContext.hashCode() + " >>>")
        NfsLogUtils.d("MainFragment || activity context <<< " + activityContext.hashCode() + " >>>")
        NfsLogUtils.d("LoginFragment || core database <<< " + coreDatabase.hashCode() + " >>> settings dao : " + localizationItemDao.hashCode())

        mBinding.btnBackToLogin.setOnClickListener {
            findNavController().navigate(R.id.action_mainfragment_to_login_graph)
            requireActivity().finish()
        }
    }

}