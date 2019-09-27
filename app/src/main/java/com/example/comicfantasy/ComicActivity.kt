package com.example.comicfantasy

import android.os.Bundle
import android.view.View
import com.example.comicfantasy.home.fragments.ComicFragment
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class ComicActivity : DaggerAppCompatActivity(),ComicFragment.OnFragmentInteractionListener {

    private var comicFragment: ComicFragment? = null


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

        val fragment = ComicFragment.newInstance()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.placeholder, fragment)
            .commit()
    }
}








