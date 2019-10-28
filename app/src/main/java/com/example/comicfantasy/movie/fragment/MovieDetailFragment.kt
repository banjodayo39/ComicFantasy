package com.example.comicfantasy.movie.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

import com.example.comicfantasy.data.remote.MovieResult
import com.example.comicfantasy.movie.viewmodel.MovieViewModel
import com.example.comicfantasy.util.BaseInteractionListener
import com.example.comicfantasy.util.loadImageWithGlide
import com.example.comicfantasy.util.showToast
import dagger.android.support.DaggerFragment

import kotlinx.android.synthetic.main.fragment_movie_detail.*
import javax.inject.Inject
import android.content.ContentValues.TAG
import android.content.Intent
import com.google.android.youtube.player.*
import com.google.android.youtube.player.YouTubeThumbnailLoader
import com.google.android.youtube.player.YouTubeThumbnailView

class MovieDetailFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private var listener: OnFragmentInteractionListener? = null
    private var listOfMovies = ArrayList<MovieResult>()
    lateinit var viewModel: MovieViewModel
    private var results: MovieResult? = null

    private var videoId = 0
    private var videoKey = arrayOf("")
    private val IMAGE_URL_BASE_PATH = "http://image.tmdb.org/t/p/w500//"
    private val API_KEY = "AIzaSyDvv7jGWU_vOprf7TjDbOsVTKeep6ysOss"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            results = it.getParcelable("results")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            com.example.comicfantasy.R.layout.fragment_movie_detail,
            container,
            false
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!, factory).get(MovieViewModel::class.java)
        getTrailerForfMovies()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayViews()
        play_button.setOnClickListener {
            Log.e("btn", "btn")
            val intent = Intent(activity, MovieDetailActivity::class.java)
            intent.putExtra("videoKey", videoKey[0])
            startActivity(intent)
            // listener!!.onPlayButtonClicked(videoKey[0])
        }
    }

    private fun displayViews() {
        movie_backdrop?.loadImageWithGlide(IMAGE_URL_BASE_PATH + results?.poster_path)
        movie_detail_title.text = results?.title
        overviewTextView.text = results?.overview
        rating_text.text = results?.vote_average.toString()
        date_text.text = results?.release_date
        rating_text.text = results?.vote_average.toString()
        /*nameOfMovie?.text= results?.original_title
        plotSynopsis?.text= results?.overview
        userRating?.text=results?.vote_average.toString()
        releaseDate?.text=results?.release_date*/
        videoThumbnailInitializer()
    }

    private fun videoThumbnailInitializer() {
        videoThumbnailImageView.initialize(API_KEY,
            object : YouTubeThumbnailView.OnInitializedListener {
                override fun onInitializationSuccess(
                  youTubeThumbnailView: YouTubeThumbnailView?,
                    youTubeThumbnailLoader: YouTubeThumbnailLoader?
                ) {
                    youTubeThumbnailLoader!!.setVideo(videoKey[0])
                    youTubeThumbnailLoader.setOnThumbnailLoadedListener(object :
                        YouTubeThumbnailLoader.OnThumbnailLoadedListener {
                        override fun onThumbnailLoaded(
                            youTubeThumbnailView: YouTubeThumbnailView,
                            s: String
                        ) {
                            //when thumbnail loaded successfully release the thumbnail loader as we are showing thumbnail in adapter
                            youTubeThumbnailLoader.release()
                        }

                        override fun onThumbnailError(
                            youTubeThumbnailView: YouTubeThumbnailView,
                            errorReason: YouTubeThumbnailLoader.ErrorReason
                        ) {
                            //print or show error when thumbnail load failed
                            Log.e(TAG, "Youtube Thumbnail Error")
                        }
                    })
                }

                override fun onInitializationFailure(
                    p0: YouTubeThumbnailView?,
                    p1: YouTubeInitializationResult?
                ) {
                    Log.e(TAG, "Youtube Initialization Failure")
                }
            })
    }

//SHA1: BF:2F:82:18:A4:B4:67:9B:9F:82:83:BA:F4:E2:81:B0:63:6F:73:D1
//AIzaSyDvv7jGWU_vOprf7TjDbOsVTKeep6ysOss
//https://www.youtube.com/watch?v=SbXIj2T-_uk
    //https://api.themoviedb.org/3/movie/550?api_key=a0b45b8501266c1f4a40ac53d4faaedc&callback=trailer
    // vView!!.setOnPreparedListener { progressBar!!.visibility = View.GONE }

    private fun getTrailerForfMovies() {
        videoId = results?.id!!
        viewModel.getTrailer(videoId).observe(this, Observer {
            Log.e("MovieFragment", it.toString())
            if (it.isLoading)
                listener?.onShowProgress()
            else
                listener?.onHideProgress()

            if (it.data != null && !it.data?.results.isNullOrEmpty()) {
                videoKey = it.data!!.results?.mapNotNull { it!!.key }!!.toTypedArray()
            }
            if (!it.error.isNullOrEmpty())
                showToast(context!!, it.error!!)
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener : BaseInteractionListener {
        fun onPlayButtonClicked(videoKey: String)
    }

    companion object {

        @JvmStatic
        fun newInstance(movieResult: MovieResult) =
            MovieDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("results", movieResult)

                }
            }
    }
}
