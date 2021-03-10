package com.nfs.ascent.core.base

import android.content.Context
import androidx.fragment.app.Fragment

/**
 * Created by ZEESHAN on 28,October,2020
 */
abstract class BaseFragment : Fragment() {

    protected lateinit var fragmentNavhelper: FragmentNavHelper

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentNavhelper = (context as FragmentNavHelper)
    }


    fun showLoading(message: String) {
        fragmentNavhelper.showLoading(message)
    }

    fun hideLoading() {
        fragmentNavhelper.hideLoading()
    }

}