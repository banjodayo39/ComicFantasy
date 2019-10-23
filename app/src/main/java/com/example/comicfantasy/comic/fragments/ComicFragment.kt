package com.example.comicfantasy.comic.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresPermission
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.comicfantasy.home.ui.HomeActivity
import com.example.comicfantasy.R
import com.example.comicfantasy.adapter.HomeFragmentAdapterclass
import com.example.comicfantasy.data.remote.Results
import com.example.comicfantasy.comic.viewmodel.ComicFragmentViewModel
import com.example.comicfantasy.data.remote.MovieResult
import com.example.comicfantasy.util.BaseInteractionListener
import com.example.comicfantasy.util.GridItemDecoration
import com.example.comicfantasy.util.showToast
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList



class ComicFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    lateinit var viewModel: ComicFragmentViewModel

    private var listener: OnFragmentInteractionListener? = null
    private var homeActivity: HomeActivity?=null
   // private var listOfComics = mutableListOf<Results>()
   private var listOfComics = arrayListOf<Results>()
    private lateinit var comicAdapter:HomeFragmentAdapterclass
    private lateinit var layManager:GridLayoutManager
    private var Id: Int? = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

            Id  = it.getInt("Id")

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(ComicFragmentViewModel::class.java)
        getListOfComics()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }


    private fun initViews() {
        comicAdapter = HomeFragmentAdapterclass(listOfComics,listener!!)
        layManager =GridLayoutManager(context,3)
        comic_list.addItemDecoration(GridItemDecoration(10,3))
        comic_list.adapter = comicAdapter
        comic_list.layoutManager = layManager
    }






    private fun getListOfComics() {
        viewModel.getComic().observe(this, Observer {
            Log.e("ComicHomeFragment", it.toString())
            if (it.isLoading)
                listener?.onShowProgress()
            else
                listener?.onHideProgress()

            if (it.data != null && !it.data?.data?.results.isNullOrEmpty()) {
               // listOfComics = it?.data?.data?.results?.filterNotNull()?.toMutableList()!!
                listOfComics.addAll(it.data?.data?.results as ArrayList<Results>)

                comicAdapter.notifyDataSetChanged()
            }

            if (!it.error.isNullOrEmpty())
                showToast(context!!, it.error!!)
        })
    }




    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString())
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener : BaseInteractionListener {
        fun onThumbnailClicked(results: Results)
    }

    companion object {
          @JvmStatic
        fun newInstance() =
            ComicFragment().apply {
                arguments = Bundle().apply {


                    /*putString(ARG_PARAM1, param1)

                    putString(ARG_PARAM2, param2)
             */   }
            }
    }
}
