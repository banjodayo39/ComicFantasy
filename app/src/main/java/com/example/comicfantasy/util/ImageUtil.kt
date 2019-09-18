package com.example.comicfantasy.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.comicfantasy.R

fun ImageView.loadImageWithGlide(imageUrl: String?){
    Glide
        .with(this)
        .load(imageUrl)
        .apply(
            RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .fitCenter())
        .into(this)
}
