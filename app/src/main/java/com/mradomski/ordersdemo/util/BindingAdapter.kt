package com.mradomski.ordersdemo.util

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mradomski.ordersdemo.R

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        Glide.with(imgView.context)
            .load(imgUrl.toUri())
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_loading_anim)
                    .error(R.drawable.ic_error)
            )
            .into(imgView)
    }
}