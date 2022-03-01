package com.commonpattern.utils.binding

import android.text.TextUtils
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.biteMe.extn.loadImage

class ImageViewBinding {
    companion object {
        @BindingAdapter(value = ["imageUrl", "progressbar"], requireAll = true)
        @JvmStatic
        fun loadProfilePic(view: ImageView?, imageUrl: String?, progressBar: ProgressBar?) {
         if(!TextUtils.isEmpty(imageUrl)) view?.loadImage(imageUrl, progressBar)
        }
    }
}