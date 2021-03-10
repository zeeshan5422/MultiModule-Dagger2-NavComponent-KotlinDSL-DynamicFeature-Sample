package com.nfs.ascent.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import com.nfs.ascent.core.R
import com.nfs.ascent.core.ext.exhaustive
import com.nfs.ascent.core.ext.toast
import kotlinx.coroutines.flow.collect

/**
 * Created by ZEESHAN on 28,October,2020
 */
abstract class MvvmBaseFragment<V : ViewDataBinding, M : BaseViewModel>
    (@LayoutRes val layoutId: Int, val vm: Int) : BaseFragment() {


    protected lateinit var mBinding: V
    protected lateinit var mViewModel: M

    protected abstract fun getViewModel(): M


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = getViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        mBinding.setLifecycleOwner { lifecycle }
        mBinding.setVariable(vm, mViewModel)
        initBaseObservers()
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeUI()
    }

    open fun subscribeUI() {
    }

    private fun initBaseObservers() {

        lifecycleScope.launchWhenStarted {

            mViewModel.requestEvent.collect {
                when (it) {
                    is RequestState.Start -> {
                    }
                    is RequestState.Loading -> {
                        showLoading(requireContext().getString(R.string.txt_please_wait))
                    }
                    is RequestState.Success -> {
                        hideLoading()
                    }
                    is RequestState.Error -> {
                        hideLoading()
                        it.message?.let { msg -> requireContext().toast(msg) }
                    }
                }.exhaustive
            }
        }

        lifecycleScope.launchWhenStarted {
            mViewModel.infoMessage.collect {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    protected fun <T> LiveData<T>.onResult(action: (T) -> Unit) {
        observe(this@MvvmBaseFragment) {
            it.let(action)
        }
    }
}