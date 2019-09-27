package com.example.comicfantasy.adapter

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Shader
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.example.comicfantasy.R
import com.example.comicfantasy.data.remote.Results
import com.example.comicfantasy.util.loadImageWithGlide

class ComicDetailAdapter(private val list: List<Results>)
    : RecyclerView.Adapter<ComicDetailAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val results: Results = list[position]
        holder.bind(results)
    }

    override fun getItemCount(): Int = list.size



inner class ViewHolder(inflater: LayoutInflater?, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater?.inflate(R.layout.comic_detail_list, parent, false)!!) {
    private var mthumbnail: ImageView? = null
    private var mtitle: TextView? = null
    private var toolbar:Toolbar?=null


    init {
        toolbar =itemView.findViewById(R.id.detail_toolbar)

        mtitle = itemView.findViewById(R.id.comic_title)

    }

    fun bind(results: Results) {
       /* val bmp:Bitmap=BitmapFactory.decodeResource(Resources.getSystem(),R.id.comic_thumbnail)
        var bitmap:BitmapDrawable= BitmapDrawable(bmp)
        bitmap.setTileModeXY(Shader.TileMode.REPEAT,Shader.TileMode.REPEAT)
        toolbar?.setBackgroundDrawable(bitmap)
        mthumbnail?.loadImageWithGlide(results.thumbnail!!.path.toString() + "." + results.thumbnail!!.extension)
        mtitle?.text = results.title*/
    }

}
}