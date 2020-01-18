package com.example.comicfantasy.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.comicfantasy.R
import kotlinx.android.synthetic.main.activity_main.view.*

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

public fun loadCircularImage(context: Context, imageView: ImageView, imageUrl: String?, placeHolder: Int) {

    if (imageUrl != null && !imageUrl.isEmpty()) {
        Glide.with(context)
            .load(imageUrl)
            .placeholder(placeHolder)
            .apply(RequestOptions.circleCropTransform())
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imageView)
    } else {
        imageView.setImageResource(placeHolder)
    }

    /*fun ImageView.loadImageWithGlide(imageUrl: String?) {
        Glide
            .with(this)
            .load(imageUrl)
            .apply(
                RequestOptions().transform(shapeTransform)
            )
            .placeholder(R.drawable.semi_curved_corner_radius)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(this)
    }*/

}