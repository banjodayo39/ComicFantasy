package com.example.comicfantasy.home.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.comicfantasy.R
import com.example.comicfantasy.comic.fragments.CharacterTabFragment
import com.example.comicfantasy.data.remote.Results
import com.example.comicfantasy.comic.fragments.ComicDetailFragment
import com.example.comicfantasy.comic.fragments.ComicFragment
import com.example.comicfantasy.comic.fragments.StoryTabFragment
import com.example.comicfantasy.community.CommunityFragment
import com.example.comicfantasy.data.remote.MovieResult
import com.example.comicfantasy.games.fragments.GamesFragment
import com.example.comicfantasy.movie.fragment.MovieDetailFragment
import com.example.comicfantasy.movie.fragment.MovieFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : DaggerAppCompatActivity()
                      ,ComicFragment.OnFragmentInteractionListener,
    ComicDetailFragment.OnFragmentInteractionListener,
     CommunityFragment.OnFragmentInteractionListener,
     GamesFragment.OnFragmentInteractionListener,
     MovieFragment.OnFragmentInteractionListener{



    private var comicFragment: ComicFragment? = null
    private var movieFragment: MovieFragment?=null
    private var comicDetailFragment:ComicDetailFragment?=null
    private val result:Results?=null

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
        setContentView(R.layout.activity_main)

        AndroidInjection.inject(this)
        initToolbar()
        showComic()
        initBottomNav()

    }


    private fun initToolbar() {
        setSupportActionBar(home_toolbar)
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
            R.id.navigation_movie-> {
                showMovie()
                true
            }
            R.id.navigation_comic-> {
                showComic()
                true
            }
           R.id.navigation_trivia -> {
                showGames()
                true
            }


            else ->
                false
        }
    }

    fun showMovie() {
        home_toolbar.title = getString(R.string.title_movie)
        val fragment = MovieFragment.newInstance()
        loadFragment(fragment)
    }

    fun showComic(){
        home_toolbar.title = getString(R.string.title_comic)
        val fragment = ComicFragment.newInstance()
        loadFragment(fragment)
    }

    fun showGames(){
        home_toolbar.title = getString(R.string.trivia)
        val fragment = GamesFragment.newInstance()
        loadFragment(fragment)
    }

    fun showCommmunity(){
        home_toolbar.title = getString(R.string.title_community)
        val fragment = CommunityFragment.newInstance()
        loadFragment(fragment)
    }





}








