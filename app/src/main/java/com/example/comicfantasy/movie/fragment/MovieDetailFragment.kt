package com.example.comicfantasy.movie.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

import com.example.comicfantasy.R
import com.example.comicfantasy.data.remote.MovieResult
import com.example.comicfantasy.data.remote.Results
import com.example.comicfantasy.movie.viewmodel.MovieViewModel
import com.example.comicfantasy.util.BaseInteractionListener
import com.example.comicfantasy.util.loadImageWithGlide
import com.example.comicfantasy.util.showToast
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_movie_detail.*
import kotlinx.android.synthetic.main.fragment_comic_detail.*
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import javax.inject.Inject


class MovieDetailFragment :  DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private var listener: OnFragmentInteractionListener? = null
    private var listOfMovies = ArrayList<MovieResult>()
    lateinit var viewModel: MovieViewModel
    private var results: MovieResult?=null
    private var btnplay: ImageView? = null
    private var uri: Uri? = null
    var isPlay=false
    private var btncontinuously:ImageView? = null
    private var btnstop: ImageView? = null
    private var mediacontroller: MediaController? = null
    private var videoId = 0
    private var videoKey = ""



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
        videoSetUp()
    }

     private fun displayViews(){
         movie_backdrop?.loadImageWithGlide(IMAGE_URL_BASE_PATH + results?.poster_path)
         movie_detail_title.text=results?.title
         overviewTextView.text=results?.overview
         rating_text.text= results?.vote_average.toString()
         date_text.text=results?.release_date
         rating_text.text=results?.vote_average.toString()
         /*nameOfMovie?.text= results?.original_title
         plotSynopsis?.text= results?.overview
         userRating?.text=results?.vote_average.toString()
         releaseDate?.text=results?.release_date*/
     }

    private fun videoSetUp(){
        btnplay = view?.findViewById(R.id.playButton)
        val vView = view?.findViewById(R.id.vView) as VideoView

        btnplay?.setOnClickListener {
            isPlay=true
            mediacontroller = MediaController(context)
            mediacontroller!!.setAnchorView(vView)
            val uriPath = "https://www.demonuts.com/Demonuts/smallvideo.mp4" //update package n
            vView.setMediaController(mediacontroller)

            /*videoKey=results?.key!!
            val videUri= "https://www.youtube.com/watch?v=$videoKey"

            Log.e("",videoKey)*/
            uri = Uri.parse(uriPath)
            vView.setMediaController(mediacontroller)
            vView.setVideoURI(uri)
            vView.requestFocus()
            vView.start()
        }

        mediacontroller = MediaController(context)
        mediacontroller!!.setAnchorView(vView)
        val uriPath = "https://www.demonuts.com/Demonuts/smallvideo.mp4" //update package n
        vView.setMediaController(mediacontroller)

      //  videoKey=results?.key!!
        val videUri= "https://www.youtube.com/watch?v=$videoKey"

       // Log.e("",videoKey)
        uri = Uri.parse(uriPath)
        vView.setMediaController(mediacontroller)
        vView.setVideoURI(uri)
        vView.requestFocus()
        vView.start()


/*

        vView!!.setOnCompletionListener {
            if (isPlay) {
                vView!!.start()
            }
        }
*/
        //https://api.themoviedb.org/3/movie/550?api_key=a0b45b8501266c1f4a40ac53d4faaedc&callback=trailer
       // vView!!.setOnPreparedListener { progressBar!!.visibility = View.GONE }


        /* btnstop!!.setOnClickListener { vv!!.pause() }

           btnplay!!.setOnClickListener { vv!!.start() }*/

          /* btnonce!!.setOnClickListener {
               isContinuously = false
               progressBar!!.visibility = View.VISIBLE
               vv!!.setMediaController(mediacontroller)
               vv!!.setVideoURI(uri)
               vv!!.requestFocus()
               vv!!.start()
           }

           btncontinuously!!.setOnClickListener {
               isContinuously = true
               progressBar!!.visibility = View.VISIBLE
               vv!!.setMediaController(mediacontroller)
               vv!!.setVideoURI(uri)
               vv!!.requestFocus()
               vv!!.start()
           }*/

         //  vView!!.setOnPreparedListener { progressBar!!.visibility = View.GONE }

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


    interface OnFragmentInteractionListener:BaseInteractionListener {
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
