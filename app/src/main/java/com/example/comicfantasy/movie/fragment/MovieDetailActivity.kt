package com.example.comicfantasy.movie.fragment

import android.app.PendingIntent.getActivity
import android.content.Context
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.MediaController
import androidx.lifecycle.*
import com.example.comicfantasy.R
import com.example.comicfantasy.base.BaseViewModel
import com.example.comicfantasy.data.remote.MovieResult
import com.example.comicfantasy.data.remote.Trailer
import com.example.comicfantasy.data.repo.MovieRepository
import com.example.comicfantasy.home.ui.HomeActivity
import com.example.comicfantasy.movie.viewmodel.MovieViewModel
import com.example.comicfantasy.util.BaseInteractionListener
import com.example.comicfantasy.util.DataUIModel
import com.example.comicfantasy.util.loadImageWithGlide
import com.example.comicfantasy.util.showToast
import com.google.android.youtube.player.*
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import javax.inject.Inject
import com.google.android.youtube.player.YouTubePlayerFragment
import com.google.android.youtube.player.internal.i


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class MovieDetailActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    private var videoId = 0
    private lateinit var videoKey:String
    private var youTubePlayer: YouTubePlayer? = null
    // val movie_id: Int
    private val IMAGE_URL_BASE_PATH = "http://image.tmdb.org/t/p/w5"
    private val API_KEY = "AIzaSyDvv7jGWU_vOprf7TjDbOsVTKeep6ysOss"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.comicfantasy.R.layout.activity_trailer)

        videoKey = intent.getStringExtra("videoKey")

        val youTubePlayerView = findViewById<View>(R.id.youtube_player_view) as YouTubePlayerView

        youTubePlayerView!!.initialize(
            API_KEY,
            object : YouTubePlayer.OnInitializedListener {
                override fun onInitializationFailure(
                    p0: YouTubePlayer.Provider?,
                    p1: YouTubeInitializationResult?
                ) {
                }

                override fun onInitializationSuccess(
                    provider: YouTubePlayer.Provider, player: YouTubePlayer,
                    wasRestored: Boolean
                ) {
                    if (!wasRestored) {
                        youTubePlayer = player

                        //set the player style default
                        youTubePlayer!!.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT)

                        //cue the 1st video by default
                        //  youTubePlayer!!.cueVideo(videoKey[0])
                        youTubePlayer!!.loadVideo(videoKey)
                        youTubePlayer!!.cueVideo(videoKey)
                        youTubePlayer!!.play()
                    }
                }
            })
    }

    override fun onInitializationFailure(
        p0: YouTubePlayer.Provider?,
        p1: YouTubeInitializationResult?
    ) {
    }

    override fun onInitializationSuccess(
        provider: YouTubePlayer.Provider, player: YouTubePlayer,
        wasRestored: Boolean
    ) {
        if (!wasRestored) {
            youTubePlayer = player
            youTubePlayer!!.cueVideo(videoKey)

            /* //set the player style default
             youTubePlayer!!.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT)

             //cue the 1st video by default
             //  youTubePlayer!!.cueVideo(videoKey[0])
             youTubePlayer!!.loadVideo(videoKey[0])
             youTubePlayer!!.cueVideo(videoKey[0])
             youTubePlayer!!.play()*/
        }
    }


    /*private fun vid() {


    *//* val youTubePlayerFragment = fragmentManager!!
            .findFragmentById(com.example.comicfantasy.R.id.vView) as YouTubePlayerFragment?
            ?: return*//*


        youTubePlayer!!.initialize(
            API_KEY,
            object : YouTubePlayer.OnInitializedListener {

                override fun onInitializationSuccess(
                    provider: YouTubePlayer.Provider, player: YouTubePlayer,
                    wasRestored: Boolean
                ) {
                    if (!wasRestored) {
                        youTubePlayer = player

                        //set the player style default
                        youTubePlayer!!.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT)

                        //cue the 1st video by default
                        //  youTubePlayer!!.cueVideo(videoKey[0])
                        youTubePlayer!!.loadVideo(videoKey[0])
                        youTubePlayer!!.cueVideo(videoKey[0])
                        youTubePlayer!!.play()
                    }
                }

                override fun onInitializationFailure(
                    arg0: YouTubePlayer.Provider,
                    arg1: YouTubeInitializationResult
                ) {

                    //print or show error if initialization failed
                    Log.e("Youtube", "Youtube failed ")
                }
            })
    }*/


   /* companion object {
        const val KEY_MSG = "videoKey"
    }*/
}
