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
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.comicfantasy.R
import com.example.comicfantasy.adapter.MovieFragmentAdapter
import com.example.comicfantasy.data.remote.MovieResult
import com.example.comicfantasy.movie.viewmodel.MovieViewModel
import com.example.comicfantasy.util.BaseInteractionListener
import com.example.comicfantasy.util.showToast
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_movie.*
import javax.inject.Inject

class MovieFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private var listener: OnFragmentInteractionListener? = null
    private var listOfMovies: List<MovieResult?> = ArrayList()
    private var listOfTopRated: List<MovieResult?> = ArrayList()
    private var listOfNowPlaying: List<MovieResult?> = ArrayList()
    private var listOfUpcoming: List<MovieResult?> = ArrayList()
    private var listOfLatest: List<MovieResult?> = ArrayList()

    lateinit var viewModel: MovieViewModel
    private lateinit var movieAdapter: MovieFragmentAdapter
    private lateinit var topRatedAdapter: MovieFragmentAdapter
    private lateinit var upcomingAdapter: MovieFragmentAdapter
    private lateinit var nowShowingAdapter: MovieFragmentAdapter
    private lateinit var latestAdapter: MovieFragmentAdapter
    private lateinit var layManager: LinearLayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(MovieViewModel::class.java)
        getListOfTopRatedMovies()
        getListOfPopularMovies()
        getListOfNowPlayingMovies()
        getListOfTopRatedMovies()
        getListOfUpcomingMovies()
    }

    private fun initViews() {
       // container_layout.visibility = View.INVISIBLE
        top_rated_movie_layout.apply {
            topRatedAdapter = MovieFragmentAdapter(listOfTopRated, listener!!)
            layManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            this.adapter = topRatedAdapter
            this.layoutManager = layManager
        }

        popular_movie_layout.apply {
            movieAdapter = MovieFragmentAdapter(listOfMovies, listener!!)
            layManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            this.adapter = movieAdapter
            this.layoutManager = layManager
        }

       now_playing_movie_layout.apply {
           nowShowingAdapter = MovieFragmentAdapter(listOfNowPlaying, listener!!)
           layManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
           this.adapter = movieAdapter
           this.layoutManager = layManager
       }

        upcoming_movie_layout.apply {
            upcomingAdapter = MovieFragmentAdapter(listOfUpcoming, listener!!)
            layManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            this.adapter = movieAdapter
            this.layoutManager = layManager
        }

        latest_movie_layout.apply {
            latestAdapter = MovieFragmentAdapter(listOfLatest, listener!!)
            layManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            this.adapter = movieAdapter
            this.layoutManager = layManager
        }
    }


    private fun getListOfPopularMovies() {
        viewModel.getPopularMovie().observe(this, Observer {
            if (it.contentIfNotUsed != null) {
               if (it.isLoading) {
                   listener?.onShowProgress()
               }
                else {
                   listener?.onHideProgress()
                   listener!!.onHideProgress()
                   container_layout.visibility = View.VISIBLE
               }
                if (!it.list.isNullOrEmpty()) {
                    listOfMovies = it.list!!
                    movieAdapter.updateList(listOfMovies)
                }
                if (!it.error.isNullOrEmpty())
                    showToast(context!!, it.error!!)

            }
        })
    }

    private fun getListOfTopRatedMovies() {
        Log.e("MovieFragment", "See")
        viewModel.getTopRatedMovie().observe(this, Observer {
            if (it.contentIfNotUsed != null) {
                Log.e(" Not MovieFragment", it.toString())

                if (it.isLoading)
                    listener?.onShowProgress()
                else
                    listener?.onHideProgress()

                if (!it.list.isNullOrEmpty()) {
                    listOfTopRated = it.list!!
                    topRatedAdapter.updateList(listOfTopRated)
                }
                if (!it.error.isNullOrEmpty())
                    showToast(context!!, it.error!!)

            }
        })
    }

    private fun getListOfNowPlayingMovies() {
        Log.e("MovieFragment", "See")
        viewModel.getNowPlayingMovie().observe(this, Observer {
            if (it.contentIfNotUsed != null) {
                Log.e(" Not MovieFragment", it.toString())

                if (it.isLoading)
                    listener?.onShowProgress()
                else
                    listener?.onHideProgress()

                if (!it.list.isNullOrEmpty()) {
                    listOfNowPlaying= it.list!!
                    topRatedAdapter.updateList(listOfNowPlaying)
                }
                if (!it.error.isNullOrEmpty())
                    showToast(context!!, it.error!!)

            }
        })
    }

    private fun getListOfUpcomingMovies() {
        Log.e("MovieFragment", "See")
        viewModel.getUpComingMovie().observe(this, Observer {
            if (it.contentIfNotUsed != null) {
                Log.e(" Not MovieFragment", it.toString())

                if (it.isLoading)
                    listener?.onShowProgress()
                else
                    listener?.onHideProgress()

                if (!it.list.isNullOrEmpty()) {
                    listOfUpcoming = it.list!!
                    topRatedAdapter.updateList(listOfUpcoming)
                }
                if (!it.error.isNullOrEmpty())
                    showToast(context!!, it.error!!)

            }
        })
    }

    private fun getListOfLatesMovies() {
        Log.e("MovieFragment", "See")
        viewModel.getLatestMovie().observe(this, Observer {
            if (it.contentIfNotUsed != null) {
                Log.e(" Not MovieFragment", it.toString())

                if (it.isLoading)
                    listener?.onShowProgress()
                else
                    listener?.onHideProgress()

                if (!it.list.isNullOrEmpty()) {
                    listOfLatest = it.list!!
                    topRatedAdapter.updateList(listOfLatest)
                }
                if (!it.error.isNullOrEmpty())
                    showToast(context!!, it.error!!)

            }
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + "must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface OnFragmentInteractionListener : BaseInteractionListener {
        fun onMovieThumbnailClicked(results: MovieResult)

    }

    companion object {

        @JvmStatic
        fun newInstance() =
            MovieFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
