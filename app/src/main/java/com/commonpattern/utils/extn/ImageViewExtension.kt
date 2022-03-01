package com.biteMe.extn

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

fun ImageView.loadImage(url: String?,progressBar: ProgressBar?) {
    progressBar?.visibility= View.VISIBLE
    Glide.with(context).load(url).listener (object : RequestListener<Drawable>{
        override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
            progressBar?.visibility= View.GONE
            return false

        }

        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
            progressBar?.visibility= View.GONE
            return false
        }
    }).apply(RequestOptions.bitmapTransform( RoundedCorners(17)))/*.placeholder(placeHolder)*/.into(this)
}
