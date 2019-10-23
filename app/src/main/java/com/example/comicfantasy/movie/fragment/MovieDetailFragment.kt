package com.example.comicfantasy.movie.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

import com.example.comicfantasy.R
import com.example.comicfantasy.data.remote.MovieResult
import com.example.comicfantasy.data.remote.Results
import com.example.comicfantasy.movie.viewmodel.MovieViewModel
import com.example.comicfantasy.util.loadImageWithGlide
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.content_movie_detail.*
import kotlinx.android.synthetic.main.fragment_comic_detail.*
import javax.inject.Inject


class MovieDetailFragment :  DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private var listener: OnFragmentInteractionListener? = null
    private var listOfMovies = ArrayList<MovieResult>()
    lateinit var viewModel: MovieViewModel
    private var results: MovieResult?=null

   // val movie_id: Int
    private val IMAGE_URL_BASE_PATH = "http://image.tmdb.org/t/p/w500//"


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

        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!, factory).get(MovieViewModel::class.java)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayViews()
    }

     private fun displayViews(){

         comic_detail_thumbnail?.loadImageWithGlide(IMAGE_URL_BASE_PATH + results?.poster_path)
         movietitle.text=results?.title
         plotsynopsis.text=results?.overview
         userrating.text= results?.vote_average.toString()
         release.text=results?.release_date
         /*nameOfMovie?.text= results?.original_title
         plotSynopsis?.text= results?.overview
         userRating?.text=results?.vote_average.toString()
         releaseDate?.text=results?.release_date*/
     }

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {

        @JvmStatic
        fun newInstance(movieResult:MovieResult) =
            MovieDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("results",movieResult)

                }
            }
    }
}
