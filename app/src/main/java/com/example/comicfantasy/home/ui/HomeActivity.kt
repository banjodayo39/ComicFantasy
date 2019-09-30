package com.example.comicfantasy.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.comicfantasy.R
import com.example.comicfantasy.data.remote.Results
import com.example.comicfantasy.comic.fragments.ComicDetailFragment
import com.example.comicfantasy.comic.fragments.ComicFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : DaggerAppCompatActivity()
                      ,ComicFragment.OnFragmentInteractionListener,
    ComicDetailFragment.OnFragmentInteractionListener {

    private var comicFragment: ComicFragment? = null
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
        val fragment = ComicFragment.newInstance()
        return loadFragment(fragment)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AndroidInjection.inject(this)

        val fragment = ComicFragment.newInstance()
        loadFragment(fragment)
    }

    override fun onThumbnailClicked(results: Results) {
        val fragment=ComicDetailFragment.newInstance(results)
       loadFragment(fragment)
    }


    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(placeholder.id, fragment, fragment.javaClass.simpleName)
            .commitAllowingStateLoss()
    }


    private val mOnNavigationSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        when (it.itemId) {
            com.gidimo.R.id.programs -> {
                showPrograms()
                true
            }
            com.gidimo.R.id.messages -> {
                showMessages()
                true
            }
            com.gidimo.R.id.community -> {
                showCommunity()
                true
            }
            com.gidimo.R.id.notifications -> {
                showNotification()
                true
            }
            com.gidimo.R.id.market -> {
                showMarketPlace()
                true
            }
            else ->
                false
        }
    }


}








