package com.example.comicfantasy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.comicfantasy.R
import com.example.comicfantasy.data.remote.Results
import com.example.comicfantasy.comic.fragments.ComicFragment
import com.example.comicfantasy.util.loadImageWithGlide


class HomeFragmentAdapterclass(
    private var list: List<Results?>,
    private val listener: ComicFragment.OnFragmentInteractionListener
) : RecyclerView.Adapter<HomeFragmentAdapterclass.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val results: Results? = list[position]
        holder.bind(results!!)
        holder.comicPosition = position
    }

    override fun getItemCount(): Int = list.size

    fun updateList(list: List<Results?>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(inflater: LayoutInflater?, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater?.inflate(R.layout.comic_list_item, parent, false)!!) {
        private var mthumbnail: ImageView? = null
        private var mtitle: TextView? = null
        var comicPosition = 0


        init {
            mthumbnail = itemView.findViewById(R.id.comic_thumbnail)
            mtitle = itemView.findViewById(R.id.comic_title)

        }

        fun bind(result: Results) {
            mthumbnail?.loadImageWithGlide(result.thumbnail!!.path.toString() + "." + result.thumbnail!!.extension)
            mtitle?.text = result.title
            itemView.setOnClickListener {
                listener.onThumbnailClicked(result)

            }
        }


    }
}