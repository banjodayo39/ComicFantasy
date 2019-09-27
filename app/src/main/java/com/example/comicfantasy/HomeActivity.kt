package com.example.comicfantasy

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.comicfantasy.home.fragments.HomeFragment
import com.example.comicfantasy.series.fragment.SeriesFragment
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*



class HomeActivity : DaggerAppCompatActivity(),HomeFragment.OnFragmentInteractionListener,
                     SeriesFragment.OnFragmentInteractionListener {

    private var comicFragment: HomeFragment? = null
    private var seriesFragment: SeriesFragment? = null

    // private var comicSeriesFragment: SeriesFragment? = null


    override fun onShowProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun onHideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun onBackPressed() {
        if (comicFragment != null && comicFragment!!.isAdded)
            super.onBackPressed()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        AndroidInjection.inject(this)

        val fragment = HomeFragment.newInstance()

        loadFragment(fragment)
    }

    override fun onThumbnailClicked(id: Int) {
        seriesFragment = SeriesFragment.newInstance(id)
        loadFragment(seriesFragment!!)
    }


        /* override fun onThumbnailClicked(position: Int, images: List<Image>) {
             seriesFragment = SeriesFragment.newInstance(images as ArrayList<Image>)
             loadFragment(seriesFragment!!)

         }*/

        private fun loadFragment(fragment: Fragment) {
            supportFragmentManager.beginTransaction()
                .replace(placeholder.id, fragment, fragment.javaClass.simpleName)
                .commitAllowingStateLoss()
        }
    }









