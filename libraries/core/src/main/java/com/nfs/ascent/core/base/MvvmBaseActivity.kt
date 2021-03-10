package com.nfs.ascent.core.base

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.nfs.ascent.core.R
import com.nfs.ascent.core.ext.exhaustive
import com.nfs.ascent.core.ext.toast
import kotlinx.coroutines.flow.collect

abstract class MvvmBaseActivity<V : ViewDataBinding, M : BaseViewModel>(
    @LayoutRes val layoutId: Int,/*private val viewModelClass: Class<M>,*/
    val vm: Int
) :
    BaseActivity() {

    protected lateinit var mBinding: V
    protected lateinit var mViewModel: M

//    val mModel by lazy {
//        ViewModelProvider(this).get(viewModelClass)
//    }
//    protected val binding by lazy {
//        DataBindingUtil.setContentView(this, layoutId) as V
//    }

    protected abstract fun getViewModel(): M

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = getViewModel()
        mBinding = DataBindingUtil.setContentView(this, layoutId)
        mBinding.setLifecycleOwner { lifecycle }
        mBinding.setVariable(vm, mViewModel)
        initBaseObservers()
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
                        showLoading(this@MvvmBaseActivity.getString(R.string.txt_please_wait))
                    }
                    is RequestState.Success -> {
                        hideLoading()
                    }
                    is RequestState.Error -> {
                        hideLoading()
                        it.message?.let { msg -> this@MvvmBaseActivity.toast(msg) }
                    }
                }.exhaustive
            }
        }
        lifecycleScope.launchWhenStarted {
            mViewModel.infoMessage.collect {
                Toast.makeText(this@MvvmBaseActivity, it, Toast.LENGTH_SHORT).show()
            }
        }

    }

    protected fun <T> LiveData<T>.onResult(action: (T) -> Unit) {
        observe(this@MvvmBaseActivity) {
            it.let(action)
        }
    }
}