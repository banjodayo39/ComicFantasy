package com.example.eBox.movie.fragment

import android.os.Bundle
import android.view.View
import com.example.eBox.BuildConfig
import com.example.eBox.R
import com.google.android.youtube.player.*


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class MovieDetailActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    private var videoId = 0
    private lateinit var videoKey:String
    private var youTubePlayer: YouTubePlayer? = null
    private val myDeveloperKey = BuildConfig.DeveloperKey

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.eBox.R.layout.activity_trailer)

        videoKey = intent.getStringExtra("videoKey")

        val youTubePlayerView = findViewById<View>(R.id.youtube_player_view) as YouTubePlayerView

        youTubePlayerView.initialize(
            myDeveloperKey,
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
                        youTubePlayer!!.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT)
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
        }
    }
}
