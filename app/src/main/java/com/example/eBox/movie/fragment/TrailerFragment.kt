package com.example.eBox.movie.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

import com.example.eBox.R
import com.example.eBox.data.remote.MovieData
import com.example.eBox.movie.viewmodel.MovieViewModel
import com.example.eBox.util.BaseInteractionListener
import com.example.eBox.util.showToast
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerFragment
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class TrailerFragment : DaggerFragment(),YouTubePlayer.OnInitializedListener {



    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private var listener: OnFragmentInteractionListener? = null
    private var results: MovieData?=null
    lateinit var viewModel: MovieViewModel
    private var videoId = 0
    private var videoKey = arrayOf("")




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            results=it.getParcelable("results")

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trailer, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!, factory).get(MovieViewModel::class.java)
          getTrailerForfMovies()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
          vid()
    }
        private fun getTrailerForfMovies() {
            videoId=results?.id!!
            viewModel.getTrailer(videoId).observe(this, Observer {
                Log.e("MovieFragment", it.toString())
                if (it.isLoading)
                    listener?.onShowProgress()
                else
                    listener?.onHideProgress()

                if (it.data != null && !it.data?.results.isNullOrEmpty()) {
                    videoKey= it.data!!.results?.mapNotNull { it!!.key }!!.toTypedArray()
                }
                if (!it.error.isNullOrEmpty())
                    showToast(context!!, it.error!!)
            })
        }


    private fun vid(){
        val youTubePlayerFragment = childFragmentManager!!
            .findFragmentById(com.example.eBox.R.id.youtube_player_view) as YouTubePlayerFragment?

        youTubePlayerFragment!!.initialize(videoKey[0], this)
    }

    override fun onInitializationSuccess(
        p0: YouTubePlayer.Provider?,
        p1: YouTubePlayer?,
        p2: Boolean
    ) {
        p1!!.setFullscreenControlFlags(YouTubePlayer.FULLSCREEN_FLAG_CONTROL_ORIENTATION)
        YouTubePlayer.FULLSCREEN_FLAG_ALWAYS_FULLSCREEN_IN_LANDSCAPE


        if (!p2) {
            p1.cueVideo(videoKey[0])
        }
    }

    override fun onInitializationFailure(
        p0: YouTubePlayer.Provider?,
        p1: YouTubeInitializationResult?
    ) {
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface OnFragmentInteractionListener :BaseInteractionListener{
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {

        @JvmStatic
        fun newInstance(movieData: MovieData) =
            TrailerFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("results",movieData)

                }
            }
    }
}
