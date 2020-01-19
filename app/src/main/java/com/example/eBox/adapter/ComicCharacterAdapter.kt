package com.example.eBox.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eBox.R

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

