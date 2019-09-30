package com.example.comicfantasy

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.comicfantasy.data.remote.Image
import com.example.comicfantasy.data.remote.Results
import com.example.comicfantasy.data.remote.Stories
import com.example.comicfantasy.home.fragments.ComicDetailFragment
import com.example.comicfantasy.home.fragments.ComicFragment
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class ComicActivity : DaggerAppCompatActivity()
                      ,ComicFragment.OnFragmentInteractionListener,
    ComicDetailFragment.OnFragmentInteractionListener {

    private var comicFragment: ComicFragment? = null
    private var comicDetailFragment:ComicDetailFragment?=null
    private val id:Int? =0
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
        val fragment = ComicFragment.newInstance(id!!,result!!)
        return loadFragment(fragment)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AndroidInjection.inject(this)

        val fragment = ComicFragment.newInstance(id!!,result!!)
        loadFragment(fragment)
    }

    override fun onThumbnailClicked(id:Int,results: Results) {
        val fragment=ComicDetailFragment.newInstance()
       loadFragment(fragment)
        fragment.displayDataRecieved(id,results)
    }





    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(placeholder.id, fragment, fragment.javaClass.simpleName)
            .commitAllowingStateLoss()
    }


}








