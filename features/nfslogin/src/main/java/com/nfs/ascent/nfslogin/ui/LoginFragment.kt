package com.nfs.ascent.nfslogin.ui

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.nfs.ascent.base.ui.MainActivity
import com.nfs.ascent.core.base.MvvmBaseFragment
import com.nfs.ascent.core.di.ACTIVITY_CONTEXT
import com.nfs.ascent.core.di.APP_CONTEXT
import com.nfs.ascent.core.utils.NfsLogUtils
import com.nfs.ascent.nfslogin.BR
import com.nfs.ascent.nfslogin.R
import com.nfs.ascent.nfslogin.data.LoginDatabase
import com.nfs.ascent.nfslogin.data.SettingsDao
import com.nfs.ascent.nfslogin.data.model.SettingEntity
import com.nfs.ascent.nfslogin.databinding.FragmentLoginBinding
import com.nfs.ascent.nfslogin.di.inject
import com.nfs.ascent.nfslogin.vm.LoginViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

class LoginFragment : MvvmBaseFragment<FragmentLoginBinding, LoginViewModel>(
    R.layout.fragment_login,
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
    lateinit var settingsDao: SettingsDao

    @Inject
    lateinit var loginDatabase :LoginDatabase

    override fun getViewModel(): LoginViewModel {
        return ViewModelProvider(requireActivity(), factory).get(LoginViewModel::class.java)
    }

    override fun subscribeUI() {
        super.subscribeUI()

        lifecycleScope.launch {
            settingsDao.insert(SettingEntity(appName = "asdf"))
        }


        NfsLogUtils.d("LoginFragment || app context <<< " + appContext.hashCode() + " >>>")
        NfsLogUtils.d("LoginFragment || activity context <<< " + activityContext.hashCode() + " >>>")
        NfsLogUtils.d("LoginFragment || loginDatbase <<< " + loginDatabase.hashCode() + " >>> settings dao : "+ settingsDao.hashCode())

        mBinding.btnLogin.setOnClickListener {
            Toast.makeText(requireContext(), "Login Success!", Toast.LENGTH_SHORT).show()

            // approach-1, that is for single activity
//            findNavController().navigate(
//                com.nfs.ascent.nfslogin.R.id.action_signInFragment_to_mainFragment
//            )

            Intent(requireActivity(), MainActivity::class.java).apply {
                startActivity(this)
                requireActivity().finish()
            }
        }
    }

}