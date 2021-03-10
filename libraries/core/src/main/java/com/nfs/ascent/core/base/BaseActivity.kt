package com.nfs.ascent.core.base

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.nfs.ascent.core.R
import com.nfs.ascent2.controls.ext.showLoadingDialog

/**
 * Created by ZEESHAN on 26,October,2020
 */
abstract class BaseActivity constructor() : AppCompatActivity(), FragmentNavHelper {

    lateinit var alertDialog: AlertDialog
    private var loadingDialog: Dialog? = null

    private val progressBar: ProgressBar by lazy {
        ProgressBar(this)
    }

    init {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun showAlertDialog(
        title: String?,
        message: String?,
        textOk: String?,
        textCancel: String?,
        okListener: DialogInterface.OnClickListener?,
        cancelListener: DialogInterface.OnClickListener?
    ) {
        val builder = AlertDialog.Builder(this)
        title?.let { builder.setTitle(it) }
        message?.let { builder.setMessage(it) }

        if (okListener == null && cancelListener == null) {
            builder.setCancelable(true)
        } else {
            builder.setCancelable(false)
        }
        builder.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_warning))
        okListener?.onClick(alertDialog, DialogInterface.BUTTON_POSITIVE)
        cancelListener?.onClick(alertDialog, DialogInterface.BUTTON_NEGATIVE)
        alertDialog = builder.create()
        alertDialog.window?.setLayout(DIALOG_WIDTH, DIALOG_HEIGHT)
        alertDialog.show()
    }

    fun showAlertDialog(
        title: String?,
        message: String?,
        textOk: String?,
        textCancel: String?,
        okListener: (() -> Unit)? = null,
        cancelListener: (() -> Unit)? = null
    ) {
        val builder = AlertDialog.Builder(this)
        title?.let { builder.setTitle(it) }
        message?.let { builder.setMessage(it) }

        if (okListener == null && cancelListener == null) {
            builder.setCancelable(true)
        } else {
            builder.setCancelable(false)
        }

        builder.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_warning))
            .setPositiveButton(textOk) { dialog, which -> okListener?.invoke() }
            .setNegativeButton(textCancel) { dialog, which -> cancelListener?.invoke() }

        alertDialog = builder.create()
        alertDialog.window?.setLayout(DIALOG_WIDTH, DIALOG_HEIGHT)
        alertDialog.show()
    }

    fun showCustomViewAlertDialog(
        title: String,
        message: String? = null,
        textOk: String? = "OK",
        textCancel: String? = "Cancel",
        @LayoutRes layoutId: Int? = null,
        view: ((view: View) -> Unit)? = null,
        okListener: ((dialog: DialogInterface) -> Unit)? = null,
        cancelListener: ((dialog: DialogInterface) -> Unit)? = null
    ) {
        val builder = AlertDialog.Builder(this)
        title.let { builder.setTitle(it) }
        message?.let { builder.setMessage(it) }

        if (okListener == null && cancelListener == null) {
            builder.setCancelable(true)
        } else {
            builder.setCancelable(false)
        }

        layoutId?.let {
            val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val inflatedView = inflater.inflate(it, null)
            builder.setView(inflatedView)
            view?.invoke(inflatedView)
        }
        builder.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_warning))
            .setPositiveButton(textOk) { dialog, which -> okListener?.invoke(dialog) }
            .setNegativeButton(textCancel) { dialog, which -> cancelListener?.invoke(dialog) }

        alertDialog = builder.create()
        alertDialog.window?.setLayout(DIALOG_WIDTH, DIALOG_HEIGHT)
        alertDialog.show()
    }

    override fun showLoading(message: String) {
        loadingDialog = showLoadingDialog()
    }

    override fun hideLoading() {
        loadingDialog?.dismiss()
        loadingDialog = null
    }


    fun View.hideKeyboard() {
        (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).apply {
            this.hideSoftInputFromWindow(this@hideKeyboard.windowToken, 0)
        }
    }


    fun showKeyboard() {
        (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).apply {
            this.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
        }
    }

    companion object {
        const val DIALOG_WIDTH = 600
        const val DIALOG_HEIGHT = 900
    }

}