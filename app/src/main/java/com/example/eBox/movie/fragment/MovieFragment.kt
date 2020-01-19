package com.example.eBox.movie.fragment

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

import com.example.eBox.R
import com.example.eBox.adapter.MovieFragmentAdapter
import com.example.eBox.data.remote.*
import com.example.eBox.movie.viewmodel.MovieViewModel
import com.example.eBox.util.BaseInteractionListener
import com.example.eBox.util.ZoomRecyclerLayout
import com.example.eBox.util.showToast
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
        viewModel = ViewModelProviders.of(this, factory).get(MovieViewModel::class.java)
        getListOfTopRatedMovies()
        getListOfPopularMovies()
        getListOfNowPlayingMovies()
        getListOfUpcomingMovies()
        getAllMovies()
        initViews()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (outState != null && viewModel.listOfTopRatedMovie.isNotEmpty()) {
           viewModel.listOfTopRatedMovie =
                outState!!.getStringArrayList("savedList") as ArrayList<TopRatedMovie>
        }
    }

    private fun getAllMovies() {
        viewModel.setAllMovies(movieMap)
    }

    private fun initViews() {
        container_layout.visibility = View.INVISIBLE
        top_rated_movie_layout.apply {
            viewModel.topRatedMovieAdapter =
                MovieFragmentAdapter(viewModel.listOfTopRatedMovie, listener!!)
            this.adapter = viewModel.topRatedMovieAdapter
            val linearLayoutManager = ZoomRecyclerLayout(context)
            linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            this.layoutManager = linearLayoutManager
            /*val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            this.layoutManager = layoutManager*/
        }

        now_playing_movie_layout.apply {
            viewModel.nowPlayingMovieAdapter =
                MovieFragmentAdapter(viewModel.listOfNowShowing, listener!!)
            this.adapter = viewModel.nowPlayingMovieAdapter
            val linearLayoutManager = ZoomRecyclerLayout(context)
            linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            this.layoutManager = linearLayoutManager
            /*val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            this.layoutManager = layoutManager*/
        }

        upcoming_movie_layout.apply {
            viewModel.upcomingMovieAdapter =
                MovieFragmentAdapter(viewModel.listOfUpComing, listener!!)
            this.adapter = viewModel.upcomingMovieAdapter
           /* val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            this.layoutManager = layoutManager*/
            val linearLayoutManager = ZoomRecyclerLayout(context)
            linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            this.layoutManager = linearLayoutManager
        }


        popular_movie_layout.apply {
            viewModel.popularMovieAdapter = MovieFragmentAdapter(viewModel.listOfPopularMovie, listener!!)
            this.adapter = viewModel.popularMovieAdapter
            /*val linearLayoutManager = ZoomRecyclerLayout(context)
            linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            this.layoutManager = linearLayoutManager*/
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
                    viewModel.listOfPopularMovie = it.list!! as List<PopularMovie>
                    viewModel.popularMovieAdapter.updateList(viewModel.listOfPopularMovie )
                    movieMap.addAll(it.list as ArrayList<MovieData>)
                    popular_tv.visibility = View.VISIBLE
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
                    viewModel.listOfTopRatedMovie = it.list as List<TopRatedMovie>
                    viewModel.topRatedMovieAdapter.updateList(viewModel.listOfTopRatedMovie as ArrayList<MovieData>)
                    movieMap.addAll(it.list as ArrayList<MovieData>)
                    top_rated_tv.visibility = View.VISIBLE
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
                    viewModel.listOfNowShowing = it.list as List<NowShowing>
                    viewModel.nowPlayingMovieAdapter.updateList(viewModel.listOfNowShowing  as ArrayList<MovieData>)
                    movieMap.addAll(it.list as ArrayList<MovieData>)
                    now_playing_tv.visibility = View.VISIBLE

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
                    viewModel.listOfUpComing= (it.list as List<UpComing>)
                    viewModel.upcomingMovieAdapter.updateList(viewModel.listOfUpComing as ArrayList<MovieData>)
                    movieMap.addAll(it.list as ArrayList<MovieData>)
                    up_coming_tv.visibility = View.VISIBLE
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
