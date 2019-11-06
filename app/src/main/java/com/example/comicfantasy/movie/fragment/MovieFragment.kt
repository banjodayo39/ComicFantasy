package com.example.comicfantasy.movie.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager

import com.example.comicfantasy.R
import com.example.comicfantasy.adapter.HomeFragmentAdapterclass
import com.example.comicfantasy.adapter.MovieFragmentAdapter
import com.example.comicfantasy.comic.viewmodel.ComicFragmentViewModel
import com.example.comicfantasy.community.CommunityFragment
import com.example.comicfantasy.data.remote.MovieResult
import com.example.comicfantasy.data.remote.Results
import com.example.comicfantasy.movie.viewmodel.MovieViewModel
import com.example.comicfantasy.util.BaseInteractionListener
import com.example.comicfantasy.util.GridItemDecoration
import com.example.comicfantasy.util.showToast
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_movie.*
import javax.inject.Inject

class MovieFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private var listener: OnFragmentInteractionListener? = null
    private var listOfMovies: List<MovieResult?> = ArrayList()

    lateinit var viewModel: MovieViewModel
    private lateinit var movieAdapter: MovieFragmentAdapter
    private lateinit var layManager: GridLayoutManager


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
        getListOfMovies()

    }


    private fun initViews() {
        movieAdapter = MovieFragmentAdapter(listOfMovies, listener!!)
        layManager = GridLayoutManager(context, 2)
        movie_list.addItemDecoration(GridItemDecoration(10, 3))
        movie_list.adapter = movieAdapter
        movie_list.layoutManager = layManager
    }


    private fun getListOfMovies() {
        viewModel.getMovie().observe(this, Observer {
            Log.e("MovieFragment", it.toString())
            if (it.contentIfNotUsed != null) {
                if (it.isLoading)
                    listener?.onShowProgress()
                else
                    listener?.onHideProgress()

                if (!it.list.isNullOrEmpty()) {
                    listOfMovies = it.list!!
                    movieAdapter.updateList(listOfMovies)
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
