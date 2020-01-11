package com.example.comicfantasy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.comicfantasy.R
import com.example.comicfantasy.data.remote.ComicResults

class ComicDetailAdapter(private val list: List<ComicResults>)
    : RecyclerView.Adapter<ComicDetailAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comicResults: ComicResults = list[position]
        holder.bind(comicResults)
        holder.comicPosition=position
    }

    override fun getItemCount(): Int = list.size



inner class ViewHolder(inflater: LayoutInflater?, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater?.inflate(R.layout.comic_detail_list, parent, false)!!) {
    private var mthumbnail: ImageView? = null
    private var mtitle: TextView? = null
     var comicPosition= 0


    init {

        mtitle = itemView.findViewById(R.id.comic_title)
        mthumbnail=itemView.findViewById(R.id.comic_thumbnail)
    }

    fun bind(comicResults: ComicResults) {



    }

}
}