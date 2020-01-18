package com.example.comicfantasy.adapter

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
import com.example.comicfantasy.data.remote.MovieData
import com.example.comicfantasy.movie.fragment.MovieFragment
import com.example.comicfantasy.util.ShapeAppearanceTransformation

import com.example.comicfantasy.util.loadImageWithGlide
import com.example.comicfantasy.util.zoomImageFromThumb
import com.google.android.youtube.player.internal.x
import com.google.android.youtube.player.internal.y
import kotlin.math.abs
import kotlin.math.max



class MovieFragmentAdapter (private var list: List<MovieData?>,
                            private val listener: MovieFragment.OnFragmentInteractionListener
)
    : RecyclerView.Adapter<MovieFragmentAdapter.ViewHolder>() {
    private val shapeTransform =
        ShapeAppearanceTransformation(com.example.comicfantasy.R.style.ShapeAppearance_Owl_SmallComponent)
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

    fun zoomImageFromThumb(
        thumbView: View,
        imageResId: String,
        expandedImageView: ImageView,
        i: Int
    ) {
        // If there's an animation in progress, cancel it
        // immediately and proceed with this one.
        if (i > lastPosition) {

            // Load the high-resolution "zoomed-in" image.
            expandedImageView.loadImageWithGlide(imageResId)
            val startBoundsInt = Rect()
            val finalBoundsInt = Rect()
            val globalOffset = Point()
            startBoundsInt.offset(-globalOffset.x, -globalOffset.y)
            finalBoundsInt.offset(-globalOffset.x, -globalOffset.y)

            val startBounds = RectF(startBoundsInt)
            val finalBounds = RectF(finalBoundsInt)

            val startScale: Float
            if ((finalBounds.width() / finalBounds.height() > startBounds.width() / startBounds.height())) {
                // Extend start bounds horizontally
                startScale = startBounds.height() / finalBounds.height()
                val startWidth: Float = startScale * finalBounds.width()
                val deltaWidth: Float = (startWidth - startBounds.width()) / 2
                startBounds.left -= deltaWidth.toInt()
                startBounds.right += deltaWidth.toInt()
            } else {
                // Extend start bounds vertically
                startScale = startBounds.width() / finalBounds.width()
                val startHeight: Float = startScale * finalBounds.height()
                val deltaHeight: Float = (startHeight - startBounds.height()) / 2f
                startBounds.top -= deltaHeight.toInt()
                startBounds.bottom += deltaHeight.toInt()
            }

            thumbView.alpha = 0f
            expandedImageView.visibility = View.VISIBLE

            expandedImageView.pivotX = 0f
            expandedImageView.pivotY = 0f

            // Construct and run the parallel animation of the four translation and
            // scale properties (X, Y, SCALE_X, and SCALE_Y).
            AnimatorSet().apply {
                play(
                    ObjectAnimator.ofFloat(
                        expandedImageView,
                        View.X,
                        startBounds.left,
                        finalBounds.left
                    )
                ).apply {
                    with(
                        ObjectAnimator.ofFloat(
                            expandedImageView,
                            View.Y,
                            startBounds.top,
                            finalBounds.top
                        )
                    )
                    with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X, startScale, 1f))
                    with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_Y, startScale, 1f))
                }
                duration = 1500
                interpolator = DecelerateInterpolator()
            }
            AnimatorSet().apply {
                play(ObjectAnimator.ofFloat(expandedImageView, View.X, startBounds.left)).apply {
                    with(ObjectAnimator.ofFloat(expandedImageView, View.Y, startBounds.top))
                    with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X, startScale))
                    with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_Y, startScale))
                }
                duration = 1500
                interpolator = DecelerateInterpolator()
            }
            lastPosition = i
        }
    }
    fun setFlipAnimation(view: ViewGroup, position: Int){
        if (position > lastPosition) {
            lastPosition = position

            scaleAnimator = ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0f,1.0f)
            translateAnimator = ObjectAnimator.ofFloat(view, "translationY", 0f, 300f,0f)
           // rotateAnimator = ObjectAnimator.ofFloat(view, "rotation", 0.0f, 360.0f)

            AnimatorSet().apply {
                play(translateAnimator).with(scaleAnimator)
                duration = 500
                start()

            }
            lastPosition =position
        }
    }

    inner class ViewHolder(inflater: LayoutInflater?, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater?.inflate(com.example.comicfantasy.R.layout.movie_list_item, parent, false)!!) {
        var mthumbnail: ImageView? = null
        private var mtitle: TextView? = null
        var moviePosition=0
        var layout:ViewGroup?=null

        // imageTransform: ShapeAppearanceTransformation


        val IMAGE_URL_BASE_PATH = "http://image.tmdb.org/t/p/w500//"
        private val YOUTUBE_VIDEO_URL = "http://www.youtube.com/watch?v=%s"


        init {
            mthumbnail = itemView.findViewById(com.example.comicfantasy.R.id.movie_thumbnail)
            mtitle = itemView.findViewById(com.example.comicfantasy.R.id.movie_title)
            layout = itemView.findViewById(com.example.comicfantasy.R.id.screenRoot)
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