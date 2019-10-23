package com.example.comicfantasy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.comicfantasy.R
import com.example.comicfantasy.data.remote.Image
import com.example.comicfantasy.data.remote.Results
import com.example.comicfantasy.util.loadImageWithGlide
import kotlinx.android.synthetic.main.comic_story_list_items.*

class ComicStoryAdaapter (private val list: List<Image>)
        : RecyclerView.Adapter<ComicStoryAdaapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            return ViewHolder(inflater, parent)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val image:Image = list[position]
            holder.bind(image)
        }

        override fun getItemCount(): Int = list.size



        inner class ViewHolder(inflater: LayoutInflater?, parent: ViewGroup) :
            RecyclerView.ViewHolder(inflater?.inflate(R.layout.comic_story_list_items, parent, false)!!) {
            private var mthumbnail: ImageView? = null


            init {

                mthumbnail=itemView.findViewById(R.id.comic_story_image)
            }

            fun bind(images: Image) {

                   mthumbnail?.loadImageWithGlide(images.path+"."+images.extension)

                }
            }

        }

