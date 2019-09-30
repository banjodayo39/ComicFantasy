package com.example.comicfantasy.home.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager

import com.example.comicfantasy.R
import com.example.comicfantasy.adapter.ComicDetailAdapter
import com.example.comicfantasy.adapter.ViewPagerAdapter
import com.example.comicfantasy.data.remote.Results
import com.example.comicfantasy.home.viewmodel.ComicFragmentViewModel
import com.example.comicfantasy.util.loadImageWithGlide
import com.google.android.material.tabs.TabLayout
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_comic_detail.*
import javax.inject.Inject


class ComicDetailFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    lateinit var viewModel: ComicFragmentViewModel


    private var listOfComics = ArrayList<Results>()
    private lateinit var comicAdapter:ComicDetailAdapter
    private var listener: OnFragmentInteractionListener? = null
    private var results:Results?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            results=it.getParcelable("results")

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comic_detail, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        comic_detail_title.text=results?.title
        comic_detail_thumbnail.loadImageWithGlide(results?.thumbnail!!.path.toString() + "." + results?.thumbnail!!.extension)

    }






    private fun setUpTabIcons(tabLayout: TabLayout){
        val tabOne = LayoutInflater.from(context).inflate(R.layout.comic_detail_list, null) as TextView
        tabOne.text = (getString(R.string.story))
        tabLayout.getTabAt(0)!!.customView = tabOne

        val tabTwo = LayoutInflater.from(context).inflate(R.layout.comic_detail_list, null) as TextView
        tabTwo.text = (getString(R.string.character))
        tabLayout.getTabAt(1)!!.customView = tabTwo

        val tabThree = LayoutInflater.from(context).inflate(R.layout.comic_detail_list, null) as TextView
        tabTwo.text = (getString(R.string.creator))
        tabLayout.getTabAt(1)!!.customView = tabThree

        val tabFour = LayoutInflater.from(context).inflate(R.layout.comic_detail_list, null) as TextView
        tabTwo.text = (getString(R.string.event))
        tabLayout.getTabAt(1)!!.customView = tabFour
    }

    private fun setUpViewPager(viewPager: ViewPager){
        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(StoryTabFragment.newInstance(), getString(R.string.story))
        adapter.addFragment(CharacterTabFragment.newInstance(), getString(R.string.character))
        adapter.addFragment(CreatorTabFragment.newInstance(), getString(R.string.creator))
        adapter.addFragment(EventTabFragment.newInstance(), getString(R.string.event))

        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = 1
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }



    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface OnFragmentInteractionListener {
    }

    companion object {

        @JvmStatic
        fun newInstance(results: Results) =
            ComicDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("results",results)
                   /* putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)*/
                }
            }
    }
}
