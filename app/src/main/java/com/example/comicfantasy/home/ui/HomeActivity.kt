package com.example.comicfantasy.home.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.comicfantasy.comic.fragments.CharacterTabFragment
import com.example.comicfantasy.data.remote.Results
import com.example.comicfantasy.comic.fragments.ComicDetailFragment
import com.example.comicfantasy.comic.fragments.ComicFragment
import com.example.comicfantasy.comic.fragments.StoryTabFragment
import com.example.comicfantasy.community.CommunityFragment
import com.example.comicfantasy.data.remote.MovieResult
import com.example.comicfantasy.games.fragments.GamesFragment
import com.example.comicfantasy.movie.fragment.MovieDetailActivity
import com.example.comicfantasy.movie.fragment.MovieDetailFragment
import com.example.comicfantasy.movie.fragment.MovieFragment
import com.example.comicfantasy.movie.fragment.TrailerFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.app.PendingIntent.getActivity
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.comicfantasy.R
import com.example.comicfantasy.comic.viewmodel.ComicFragmentViewModel
import com.example.comicfantasy.data.remote.GamesResult
import kotlinx.android.synthetic.main.layout_toolbar.view.*
import javax.inject.Inject


class HomeActivity : DaggerAppCompatActivity()
                      ,ComicFragment.OnFragmentInteractionListener,
    ComicDetailFragment.OnFragmentInteractionListener,
     CommunityFragment.OnFragmentInteractionListener,
     GamesFragment.OnFragmentInteractionListener,
     MovieFragment.OnFragmentInteractionListener,
     MovieDetailFragment.OnFragmentInteractionListener{

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    lateinit var comicFragmentViewModel:ComicFragmentViewModel

    private var comicFragment: ComicFragment? = null
    private var movieFragment: MovieFragment?=null
    private var comicDetailFragment:ComicDetailFragment?=null

    override fun onShowProgress() {
         progressBar.visibility = View.VISIBLE
    }

    override fun onHideProgress() {
         progressBar.visibility = View.GONE
    }

    override fun onBackPressed() {
        if (comicFragment != null && comicFragment!!.isAdded)
            super.onBackPressed()
        if (comicDetailFragment != null && comicDetailFragment!!.isAdded)
            super.onBackPressed()
        showComic()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.comicfantasy.R.layout.activity_main)

       AndroidInjection.inject(this)
        initToolbar()

        comicFragmentViewModel= ViewModelProviders.of(this, factory).get(ComicFragmentViewModel::class.java)
        comicFragmentViewModel.getComic().observe(this, androidx.lifecycle.Observer {
            showMovie()
        })
        initBottomNav()
    }

    private fun initToolbar() {
        //setSupportActionBar(toolbar_layout.findViewById(R.id.title))
        //supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onThumbnailClicked(results: Results) {
        val fragment=ComicDetailFragment.newInstance(results)
        val fragment2= StoryTabFragment.newInstance(results)
        val fragment3= CharacterTabFragment.newInstance(results)
       loadFragment(fragment)
    }


    override fun onMovieThumbnailClicked(movieResult: MovieResult) {
        val fragment= MovieDetailFragment.newInstance(movieResult)
       loadFragment(fragment)

    }

    override fun onPlayButtonClicked(videoId: String) {
      /*  val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(MovieDetailActivity.KEY_MSG,videoId)
        startActivity(intent)*/
    }


    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(placeholder.id, fragment, fragment.javaClass.simpleName)
            .commitAllowingStateLoss()
    }

    private fun initBottomNav() {
        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationSelectedListener)
    }



    private val mOnNavigationSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        when (it.itemId) {
            com.example.comicfantasy.R.id.navigation_movie-> {
                showMovie()
                true
            }
            com.example.comicfantasy.R.id.navigation_comic-> {
                showComic()
                true
            }
           com.example.comicfantasy.R.id.navigation_trivia -> {
                showGames()
                true
            }

            else ->
                false
        }
    }

    fun showMovie() {
        var title :TextView
        toolbar_layout.findViewById<TextView>(R.id.title).text= getString(com.example.comicfantasy.R.string.title_movie)
        val fragment = MovieFragment.newInstance()
        loadFragment(fragment)
    }

    fun showComic(){
        toolbar_layout.findViewById<TextView>(R.id.title).text = getString(com.example.comicfantasy.R.string.title_comic)
        val fragment = ComicFragment.newInstance()
        loadFragment(fragment)
    }

    fun showGames(){
        toolbar_layout.findViewById<TextView>(R.id.title).text = getString(com.example.comicfantasy.R.string.trivia)
        val fragment = GamesFragment.newInstance()
        loadFragment(fragment)
    }

    fun showCommmunity(){
        toolbar_layout.findViewById<TextView>(R.id.title).text = getString(com.example.comicfantasy.R.string.title_community)
        val fragment = CommunityFragment.newInstance()
        loadFragment(fragment)
    }





}








