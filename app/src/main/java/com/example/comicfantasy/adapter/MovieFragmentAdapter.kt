package com.example.comicfantasy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.comicfantasy.data.remote.MovieResult
import com.example.comicfantasy.movie.fragment.MovieFragment

import com.example.comicfantasy.util.loadImageWithGlide



class MovieFragmentAdapter (private var list: List<MovieResult?>,
                            private val listener: MovieFragment.OnFragmentInteractionListener
)
    : RecyclerView.Adapter<MovieFragmentAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val results: MovieResult?= list[position]
        holder.bind(results!!)
        holder.moviePosition=position
    }

    override fun getItemCount(): Int = list.size

    fun updateList(list: List<MovieResult?>) {
        this.list = list
        notifyDataSetChanged()
    }


    inner class ViewHolder(inflater: LayoutInflater?, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater?.inflate(com.example.comicfantasy.R.layout.movie_list_item, parent, false)!!) {
        private var mthumbnail: ImageView? = null
        private var mtitle: TextView? = null
        var moviePosition=0

        val IMAGE_URL_BASE_PATH = "http://image.tmdb.org/t/p/w500//"
        private val YOUTUBE_VIDEO_URL = "http://www.youtube.com/watch?v=%s"


        init {
            mthumbnail = itemView.findViewById(com.example.comicfantasy.R.id.movie_thumbnail)
            mtitle = itemView.findViewById(com.example.comicfantasy.R.id.movie_title)

        }

        fun bind(movieResult: MovieResult) {
            mthumbnail?.loadImageWithGlide(IMAGE_URL_BASE_PATH + movieResult.backdrop_path)
            mtitle?.text =movieResult.title
            mthumbnail!!.setOnClickListener{
                listener.onMovieThumbnailClicked( movieResult)

            }
        }



    }
}