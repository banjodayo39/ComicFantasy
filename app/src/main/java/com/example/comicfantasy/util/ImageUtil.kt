package com.example.comicfantasy.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.comicfantasy.R
import com.materialstudies.owl.util.ShapeAppearanceTransformation

private val shapeTransform =
    ShapeAppearanceTransformation(R.style.ShapeAppearance_Owl_SmallComponent)

fun ImageView.loadImageWithGlide(imageUrl: String?) {
    Glide
        .with(this)
        .load(imageUrl)
        .apply(
            RequestOptions().transform(shapeTransform)
        )
        .placeholder(R.drawable.semi_curved_corner_radius)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}
