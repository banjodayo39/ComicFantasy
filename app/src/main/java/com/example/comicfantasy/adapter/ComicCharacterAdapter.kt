package com.example.comicfantasy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.comicfantasy.R
import com.example.comicfantasy.data.remote.Image
import com.example.comicfantasy.util.loadImageWithGlide

class ComicCharacterAdapter (private val list: List<String>)
        : RecyclerView.Adapter<ComicCharacterAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            return ViewHolder(inflater, parent)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val name = list[position]
            holder.bind(name)
        }

        override fun getItemCount(): Int = list.size

        inner class ViewHolder(inflater: LayoutInflater?, parent: ViewGroup) :
            RecyclerView.ViewHolder(inflater?.inflate(R.layout.author_list, parent, false)!!) {
            private var authorName: TextView? = null

            init {

               authorName = itemView.findViewById(R.id.author_tv)
            }

            fun bind(name:String) {
                authorName!!.text = name
                }
            }

        }

