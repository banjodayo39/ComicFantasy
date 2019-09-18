package com.example.comicfantasy

import android.os.Bundle
import android.view.View
import com.example.comicfantasy.home.fragments.HomeFragment
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : DaggerAppCompatActivity(),HomeFragment.OnFragmentInteractionListener {

    private var comicFragment: HomeFragment? = null


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
        setContentView(R.layout.activity_main)

        AndroidInjection.inject(this)

        val fragment = HomeFragment.newInstance()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.placeholder, fragment)
            .commit()
    }
}








