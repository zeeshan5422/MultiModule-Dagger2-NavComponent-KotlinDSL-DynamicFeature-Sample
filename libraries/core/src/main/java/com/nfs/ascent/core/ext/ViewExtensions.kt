package com.nfs.ascent2.controls.ext

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.nfs.ascent.core.R

/**
 * Created by ZEESHAN on 26,October,2020
 */


fun Context.showLoadingDialog(): Dialog {
    val view = LayoutInflater.from(this).inflate(R.layout.loading, null)
    val alertDialog = AlertDialog.Builder(this).setView(view).setCancelable(false)
    val dialog = alertDialog.create()
    dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
    dialog.show()
    return dialog
}


fun Context.showIosLoadingDialog(): Dialog {
    val view = LayoutInflater.from(this).inflate(R.layout.ios_loading, null)
    val alertDialog = AlertDialog.Builder(this).setView(view).setCancelable(false)
    val dialog = alertDialog.create()
    dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
    view.findViewById<ImageView>(R.id.img_loading).loadImage(R.drawable.loading)
    dialog.show()

    return dialog
}

fun ImageView.loadImage(@DrawableRes drawableRes: Int) {
    Glide.with(this).load(drawableRes).fitCenter().into(this)
}

