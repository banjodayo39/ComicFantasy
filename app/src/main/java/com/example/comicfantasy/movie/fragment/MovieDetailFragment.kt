package com.example.comicfantasy.movie.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

import com.example.comicfantasy.data.remote.MovieData
import com.example.comicfantasy.movie.viewmodel.MovieViewModel
import com.example.comicfantasy.util.BaseInteractionListener
import com.example.comicfantasy.util.loadImageWithGlide
import com.example.comicfantasy.util.showToast
import dagger.android.support.DaggerFragment

import kotlinx.android.synthetic.main.fragment_movie_detail.*
import javax.inject.Inject
import android.content.Intent
import com.example.comicfantasy.BuildConfig
import com.example.comicfantasy.R
import com.example.comicfantasy.util.loadCircularImage

class MovieDetailFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private var listener: OnFragmentInteractionListener? = null
    lateinit var viewModel: MovieViewModel
    private var results: MovieData? = null
    private var videoId = 0
    private var videoKey = arrayOf("")
    private val imageBaseUrl = "http://image.tmdb.org/t/p/w500//"
    private val myDeveloperKey = BuildConfig.DeveloperKey

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
        getTrailerForMovies()
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
        videoThumbnailImageView?.loadImageWithGlide(imageBaseUrl+results?.poster_path)
        loadCircularImage(context!!,movie_backdrop,imageBaseUrl + results?.backdrop_path,R.drawable.button_shape)
        movie_detail_title.text = results?.title
        overviewTextView.text = results?.overview
        rating_text.text = results?.vote_average.toString()
        date_text.text = results?.release_date
        rating_text.text = results?.vote_average.toString()
    }


    private fun getTrailerForMovies() {
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
        listener =null
    }

    interface OnFragmentInteractionListener : BaseInteractionListener {
        fun onPlayButtonClicked(videoKey: String)
    }

    companion object {

        @JvmStatic
        fun newInstance(movieData: MovieData) =
            MovieDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("results", movieData)
                }
            }
    }
}
