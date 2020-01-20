package com.example.eBox.adapter

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Point
import android.graphics.Rect
import android.graphics.RectF
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eBox.data.remote.MovieData
import com.example.eBox.movie.fragment.MovieFragment
import com.example.eBox.util.ShapeAppearanceTransformation

import com.example.eBox.util.loadImageWithGlide


class MovieFragmentAdapter (private var list: List<MovieData?>,
                            private val listener: MovieFragment.OnFragmentInteractionListener
)
    : RecyclerView.Adapter<MovieFragmentAdapter.ViewHolder>() {
    private val shapeTransform =
        ShapeAppearanceTransformation(com.example.eBox.R.style.ShapeAppearance_Owl_SmallComponent)
    var DURATION: Long = 1500
    private val on_attach = true
    var lastPosition=-1
    private var rotateAnimator: ObjectAnimator? = null
    private var scaleAnimator: ObjectAnimator? = null
    private var translateAnimator: ObjectAnimator? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val results: MovieData?= list[position]
        holder.bind(results!!)
        holder.moviePosition=position
      // setFlipAnimation(holder.layout!!,position)
        //zoomImageFromThumb(holder.layout!!,holder.IMAGE_URL_BASE_PATH+results.backdrop_path, holder.mthumbnail!!,position)

    }

    override fun getItemCount(): Int = list.size

    fun updateList(list: List<MovieData?>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(inflater: LayoutInflater?, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater?.inflate(com.example.eBox.R.layout.movie_list_item, parent, false)!!) {
        var mthumbnail: ImageView? = null
        private var mtitle: TextView? = null
        var moviePosition=0
        var layout:ViewGroup?=null

        // imageTransform: ShapeAppearanceTransformation


        val IMAGE_URL_BASE_PATH = "http://image.tmdb.org/t/p/w500//"
        private val YOUTUBE_VIDEO_URL = "http://www.youtube.com/watch?v=%s"


        init {
            mthumbnail = itemView.findViewById(com.example.eBox.R.id.movie_thumbnail)
            mtitle = itemView.findViewById(com.example.eBox.R.id.movie_title)
            layout = itemView.findViewById(com.example.eBox.R.id.screenRoot)
        }

        fun bind(movieData: MovieData) {
            mthumbnail?.loadImageWithGlide(IMAGE_URL_BASE_PATH + movieData.backdrop_path).toString()
            val imageRes :String= IMAGE_URL_BASE_PATH + movieData.backdrop_path
            mtitle?.text =movieData.title
            mthumbnail!!.setOnClickListener{
                listener.onMovieThumbnailClicked( movieData)

            }
        }



    }
}