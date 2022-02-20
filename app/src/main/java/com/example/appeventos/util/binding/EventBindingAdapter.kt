package com.example.appeventos.util.binding

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.appeventos.R

@BindingAdapter("imageUrl")
fun AppCompatImageView.imageUrl(url: String?) {
    url?:return
    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.ic_no_photos)
        .error(R.drawable.ic_no_photos)
        .fallback(R.drawable.ic_no_photos)
        .into(this)
}