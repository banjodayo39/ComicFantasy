package com.example.eBox.comic.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager

import com.example.eBox.R
import com.example.eBox.adapter.ComicDetailAdapter
import com.example.eBox.adapter.ViewPagerAdapter
import com.example.eBox.data.remote.ComicResults
import com.example.eBox.viewmodel.ComicFragmentViewModel
import com.example.eBox.util.loadImageWithGlide
import com.google.android.material.tabs.TabLayout
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_comic_detail.*
import javax.inject.Inject


class ComicDetailFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    lateinit var viewModel: ComicFragmentViewModel


    private var listOfComics = ArrayList<ComicResults>()
    private lateinit var pagerAdapter: ViewPagerAdapter
    private lateinit var comicAdapter: ComicDetailAdapter
    private var listener: OnFragmentInteractionListener? = null
    private var comicResults: ComicResults? = null
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        arguments?.let {
            comicResults = it.getParcelable("comicResults")

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
        comic_detail_title.text = comicResults?.title
        comic_detail_thumbnail.loadImageWithGlide(comicResults?.thumbnail!!.path.toString() + "." + comicResults?.thumbnail!!.extension)
        //comic_detail_thumbnail.loadImageWithGlide(comicResults?.images?.get(3)!!.path.toString() +"." + comicResults!!.images?.get(3)!!.extension.toString())

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
        adapter.addFragment(StoryTabFragment.newInstance(comicResults!!))
        adapter.addFragment(CharacterTabFragment.newInstance(comicResults!!))


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
        fun newInstance(comicResults: ComicResults) =
            ComicDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("comicResults", comicResults)
                    /* putString(ARG_PARAM1, param1)
                     putString(ARG_PARAM2, param2)*/
                }
            }
    }
}
