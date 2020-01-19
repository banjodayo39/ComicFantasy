package com.example.eBox.home.ui

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.eBox.ui.fragments.CharacterTabFragment
import com.example.eBox.ui.fragments.ComicDetailFragment
import com.example.eBox.ui.fragments.ComicFragment
import com.example.eBox.ui.fragments.StoryTabFragment
import com.example.eBox.viewmodel.ComicFragmentViewModel
import com.example.eBox.community.CommunityFragment
import com.example.eBox.data.remote.ComicResults
import com.example.eBox.data.remote.MovieData
import com.example.eBox.games.fragments.GamesFragment
import com.example.eBox.movie.fragment.MovieDetailFragment
import com.example.eBox.movie.fragment.MovieFragment
import com.example.eBox.movie.viewmodel.MovieViewModel
import com.example.eBox.notification.fragment.NotificationFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*
import javax.inject.Inject




class HomeActivity : DaggerAppCompatActivity()
    , ComicFragment.OnFragmentInteractionListener,
    ComicDetailFragment.OnFragmentInteractionListener,
    CommunityFragment.OnFragmentInteractionListener,
    GamesFragment.OnFragmentInteractionListener,
    MovieFragment.OnFragmentInteractionListener,
    MovieDetailFragment.OnFragmentInteractionListener,
    NotificationFragment.OnFragmentInteractionListener {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    lateinit var movieFragmentViewModel: MovieViewModel
    lateinit var comicViewModel: ComicFragmentViewModel

    private var comicFragment: ComicFragment? = null
    private var movieFragment: MovieFragment? = null
    private var movieDetailFragment: MovieDetailFragment? = null
    private var comicDetailFragment: ComicDetailFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.eBox.R.layout.activity_main)
        showMovie()
        initBottomNav()
        settingClicked()
        if (savedInstanceState != null) {
            getSavedDataAcrossFragment(savedInstanceState)
        }
    }

    private fun getSavedDataAcrossFragment(savedInstanceState: Bundle?) {
        comicFragment = ComicFragment.newInstance()
        movieFragment = MovieFragment.newInstance()
        comicFragment = supportFragmentManager.getFragment(savedInstanceState!!, "comicFragment") as ComicFragment
        movieFragment = supportFragmentManager.getFragment(savedInstanceState!!, "movieFragment")  as MovieFragment
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        comicFragment = ComicFragment.newInstance()
        movieFragment = MovieFragment.newInstance()
        if(comicFragment!!.isAdded ) {
            supportFragmentManager.putFragment(outState, "comicFragment", comicFragment as Fragment)
        }

        if(movieFragment!!.isAdded ) {
            supportFragmentManager.putFragment(outState, "movieFragment", movieFragment as Fragment)
        }
    }

    private fun settingClicked() {
        toolbar_layout.setting.setOnClickListener {
            val fragment = NotificationFragment.newInstance()
            loadFragment(fragment)
        }
    }

    override fun onThumbnailClicked(comicResults: ComicResults) {
        var title: TextView
        toolbar_layout.findViewById<TextView>(com.example.eBox.R.id.title).text =
            getString(com.example.eBox.R.string.title_comic)
        val fragment = ComicDetailFragment.newInstance(comicResults)
        val fragment2 = StoryTabFragment.newInstance(comicResults)
        val fragment3 = CharacterTabFragment.newInstance(comicResults)
        loadChildFragment(fragment)
    }


    override fun onMovieThumbnailClicked(movieData: MovieData) {
        var title: TextView
        toolbar_layout.findViewById<TextView>(com.example.eBox.R.id.title).text =
            getString(com.example.eBox.R.string.title_movie)
        val fragment = MovieDetailFragment.newInstance(movieData)
        loadChildFragment(fragment)
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

    private fun loadChildFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(placeholder.id, fragment, fragment.javaClass.simpleName)
            .addToBackStack(null)
            .commit()
    }

    private fun initBottomNav() {
        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationSelectedListener)
    }

    private val mOnNavigationSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener {
            when (it.itemId) {
                com.example.eBox.R.id.navigation_movie -> {
                    showMovie()
                    true
                }
                com.example.eBox.R.id.navigation_comic -> {
                    showComic()
                    true
                }
                com.example.eBox.R.id.navigation_trivia -> {
                    showGames()
                    true
                }

                else ->
                    false
            }
        }

    private fun showMovie() {
        var title: TextView
        toolbar_layout.findViewById<TextView>(com.example.eBox.R.id.title).text =
            getString(com.example.eBox.R.string.title_movie)
        val fragment = MovieFragment.newInstance()
        loadFragment(fragment)
    }

    private fun showComic() {
        toolbar_layout.findViewById<TextView>(com.example.eBox.R.id.title).text =
            getString(com.example.eBox.R.string.title_comic)
        val fragment = ComicFragment.newInstance()
        loadFragment(fragment)
    }

    private fun showGames() {
        toolbar_layout.findViewById<TextView>(com.example.eBox.R.id.title).text =
            getString(com.example.eBox.R.string.trivia)
        val fragment = GamesFragment.newInstance()
        loadFragment(fragment)
    }

    override fun onBackPressed() {
        val fragment =
            this.supportFragmentManager
        movieFragment = MovieFragment()
        if (fragment.backStackEntryCount > 0) {
            fragment.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onShowProgress() {
        progress_bar_layout.visibility = View.VISIBLE
    }

    override fun onHideProgress() {
        progress_bar_layout.visibility = View.GONE
    }

}








