package com.commonpattern.utils.application

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.view.WindowManager
import androidx.annotation.NonNull
import com.commonpattern.R


class CustomLoader(@NonNull context: Context,theme:Int) :Dialog(context) {
    companion object{
        var loader: CustomLoader? = null

        fun showLoader(activity: Activity?) {
            if (loader == null) loader = show(activity,true)
            try {
                loader?.setCancelable(false)
                loader?.show()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        fun show(context: Context?, cancelable: Boolean): CustomLoader? {
            val dialog = CustomLoader(context!!, android.R.style.Theme_Black)
            dialog.window!!.requestFeature(Window.FEATURE_NO_TITLE)
            dialog.window!!.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.setContentView(R.layout.layout_loader)
            dialog.setCancelable(cancelable)
            dialog.show()
            return dialog
        }

        fun hideLoader() {
            try {
                if (loader !=null && loader?.isShowing()!!) {
                    loader?.dismiss()
                    loader = null
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


}