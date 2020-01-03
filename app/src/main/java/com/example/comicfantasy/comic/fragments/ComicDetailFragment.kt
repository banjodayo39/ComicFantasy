package com.example.comicfantasy.comic.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager

import com.example.comicfantasy.R
import com.example.comicfantasy.adapter.ComicDetailAdapter
import com.example.comicfantasy.adapter.ViewPagerAdapter
import com.example.comicfantasy.data.remote.Results
import com.example.comicfantasy.comic.viewmodel.ComicFragmentViewModel
import com.example.comicfantasy.util.loadImageWithGlide
import com.google.android.material.tabs.TabLayout
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_comic_detail.*
import kotlinx.android.synthetic.main.tab_layout_custom_view.*
import javax.inject.Inject


class ComicDetailFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    lateinit var viewModel: ComicFragmentViewModel


    private var listOfComics = ArrayList<Results>()
    private lateinit var pagerAdapter: ViewPagerAdapter
    private lateinit var comicAdapter: ComicDetailAdapter
    private var listener: OnFragmentInteractionListener? = null
    private var results: Results? = null
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        arguments?.let {
            results = it.getParcelable("results")

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProviders.of(this, factory).get(ComicFragmentViewModel::class.java)
        return inflater.inflate(R.layout.fragment_comic_detail, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setUpViewPager(viewPager)
        tabLayout.setupWithViewPager(viewPager)
        setUpTabIcons(tabLayout)

    }


    private fun initView() {
        viewPager = view!!.findViewById(R.id.comic_detail_view_pager)
        tabLayout = view!!.findViewById(R.id.tab)
        comic_detail_title.text = results?.title
        comic_detail_thumbnail.loadImageWithGlide(results?.thumbnail!!.path.toString() + "." + results?.thumbnail!!.extension)
        //comic_detail_thumbnail.loadImageWithGlide(results?.images?.get(3)!!.path.toString() +"." + results!!.images?.get(3)!!.extension.toString())

    }


    private fun setUpTabIcons(tabLayout: TabLayout) {
        val tabOne = LayoutInflater.from(context).inflate(R.layout.tab_layout_custom_view, null) as ImageView
        tabOne.setImageResource(R.drawable.ic_book_collection)
        tabLayout.getTabAt(0)!!.customView = tabOne

        val tabTwo =
            LayoutInflater.from(context).inflate(R.layout.tab_layout_custom_view, null) as ImageView
        tabTwo.setImageResource(R.drawable.ic_people)
        tabLayout.getTabAt(1)!!.customView = tabTwo

    }

    private fun setUpViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(StoryTabFragment.newInstance(results!!))
        adapter.addFragment(CharacterTabFragment.newInstance(results!!))


        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = 1
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + "must implement OnFragmentInteractionListener")
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
                    putParcelable("results", results)
                    /* putString(ARG_PARAM1, param1)
                     putString(ARG_PARAM2, param2)*/
                }
            }
    }
}
