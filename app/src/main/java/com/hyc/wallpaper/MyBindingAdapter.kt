package com.hyc.wallpaper

import android.databinding.BindingAdapter
import com.facebook.drawee.view.SimpleDraweeView

@BindingAdapter("imageUrl")
fun loadImage(view: SimpleDraweeView, url: String) {
    view.setImageURI(url)
}