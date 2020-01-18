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
import com.example.comicfantasy.data.remote.*
import com.example.comicfantasy.movie.viewmodel.MovieViewModel
import com.example.comicfantasy.util.BaseInteractionListener
import com.example.comicfantasy.util.ZoomRecyclerLayout
import com.example.comicfantasy.util.loadImageWithGlide
import com.example.comicfantasy.util.showToast
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_movie.*
import javax.inject.Inject

class MovieFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private var listener: OnFragmentInteractionListener? = null
    private var listOfMovies: List<PopularMovie?> = ArrayList()
    private var listOfTopRated: List<TopRatedMovie?> = ArrayList()
    private var listOfNowPlaying: List<NowShowing?> = ArrayList()
    private var listOfUpcoming: List<UpComing?> = ArrayList()
    //private var listOfLatest: List<Latest?> = ArrayList()

    lateinit var viewModel: MovieViewModel
    private lateinit var movieAdapter: MovieFragmentAdapter
    private lateinit var topRatedAdapter: MovieFragmentAdapter
    private lateinit var upcomingAdapter: MovieFragmentAdapter
    private lateinit var nowShowingAdapter: MovieFragmentAdapter
    private lateinit var latestAdapter: MovieFragmentAdapter
    private lateinit var layManager: LinearLayoutManager
    private val movieMap = ArrayList<MovieData>()


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
        getListOfUpcomingMovies()
        getAllMovies()
    }

    private fun getAllMovies() {
        viewModel.setAllMovies(movieMap)
    }

    private fun initViews() {
        container_layout.visibility = View.INVISIBLE
        top_rated_movie_layout.apply {

            topRatedAdapter =
                MovieFragmentAdapter(listOfTopRated as ArrayList<MovieData>, listener!!)
            layManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            this.adapter = topRatedAdapter
            val linearLayoutManager = ZoomRecyclerLayout(context)
            linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            this.layoutManager = linearLayoutManager

        }

        now_playing_movie_layout.apply {
            nowShowingAdapter =
                MovieFragmentAdapter(listOfNowPlaying as ArrayList<MovieData>, listener!!)
            layManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            this.adapter = nowShowingAdapter
            val linearLayoutManager = ZoomRecyclerLayout(context)
            linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            this.layoutManager = linearLayoutManager
        }

        upcoming_movie_layout.apply {
            upcomingAdapter =
                MovieFragmentAdapter(listOfUpcoming as ArrayList<MovieData>, listener!!)
            layManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            this.adapter = upcomingAdapter
            val linearLayoutManager = ZoomRecyclerLayout(context)
            linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            this.layoutManager = linearLayoutManager
        }


        popular_movie_layout.apply {
            movieAdapter = MovieFragmentAdapter(listOfMovies, listener!!)
            layManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            this.adapter = movieAdapter
            val linearLayoutManager = ZoomRecyclerLayout(context)
            linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            this.layoutManager = linearLayoutManager
        }
    }


    private fun getListOfPopularMovies() {
        viewModel.getPopularMovie().observe(this, Observer {
            if (it.contentIfNotUsed != null) {
                if (it.isLoading) {
                    listener?.onShowProgress()
                } else {
                    listener?.onHideProgress()
                    listener!!.onHideProgress()
                    container_layout.visibility = View.VISIBLE
                }
                if (!it.list.isNullOrEmpty()) {
                    popular_tv.visibility = View.VISIBLE
                    listOfMovies = it.list!! as List<PopularMovie?>
                    movieAdapter.updateList(listOfMovies)
                    movieMap.addAll(it.list as ArrayList<MovieData>)
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
                    top_rated_tv.visibility = View.VISIBLE
                    topRatedAdapter.updateList(listOfTopRated as ArrayList<MovieData>)
                    movieMap.addAll(it.list as ArrayList<MovieData>)
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
                    listOfNowPlaying = it.list!!
                    now_playing_tv.visibility = View.VISIBLE
                    nowShowingAdapter.updateList(listOfNowPlaying as ArrayList<MovieData>)
                    movieMap.addAll(it.list as ArrayList<MovieData>)
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
                    up_coming_tv.visibility = View.VISIBLE
                    upcomingAdapter.updateList(listOfUpcoming as ArrayList<MovieData>)
                    movieMap.addAll(it.list as ArrayList<MovieData>)
                }
                if (!it.error.isNullOrEmpty())
                    showToast(context!!, it.error!!)

            }
        })
    }

/*
    private fun getListOfLatesMovies() {
        Log.e("latest", "See")
        viewModel.getLatestMovie().observe(this, Observer {
            if (it.contentIfNotUsed != null) {
                Log.e(" Not MovieFragment", it.toString())

                if (it.isLoading)
                    listener?.onShowProgress()
                else
                    listener?.onHideProgress()

                if (it.data != null) {
                    latest_tv.visibility = View.VISIBLE
                    latest_movie_layout.loadImageWithGlide(it.data!!.poster_path)
                }
                if (!it.error.isNullOrEmpty())
                    showToast(context!!, it.error!!)
            }
        })

    }
*/

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
        fun onMovieThumbnailClicked(results: MovieData)

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
