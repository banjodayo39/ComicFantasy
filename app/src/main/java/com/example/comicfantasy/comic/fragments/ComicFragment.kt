package com.example.comicfantasy.comic.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.comicfantasy.R
import com.example.comicfantasy.adapter.ComicFragmentAdapterclass
import com.example.comicfantasy.comic.viewmodel.ComicFragmentViewModel
import com.example.comicfantasy.data.remote.ComicResults
import com.example.comicfantasy.home.ui.HomeActivity
import com.example.comicfantasy.util.BaseInteractionListener
import com.example.comicfantasy.util.showToast
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject


class ComicFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    lateinit var viewModel: ComicFragmentViewModel

    private var listener: OnFragmentInteractionListener? = null
    private var homeActivity: HomeActivity? = null
    // private var listOfComics = mutableListOf<ComicResults>()
    //private var listOfComics = arrayListOf<ComicResults>()
    private var listOfComics: List<ComicResults?> = ArrayList()

    private lateinit var comicAdapter: ComicFragmentAdapterclass
    private lateinit var layManager: LinearLayoutManager
    private var Id: Int? = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            Id = it.getInt("Id")

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
        if(savedInstanceState != null){
            listener 
        }

        initViews()
        //getAllComic()
    }



    private fun initViews() {
        getListOfComics()
        viewModel.adapter = ComicFragmentAdapterclass(listOfComics, listener!!)
        layManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        comic_list.adapter = viewModel.adapter
        comic_list.layoutManager = layManager
    }

    /*  private  fun getAllComic(){
          viewModel.getComic().observe(activity!!, Observer {
              if (it!= null && !it.isNullOrEmpty()) {
                   listOfComics = (it.toMutableList() as ArrayList<ComicResults>?)!!
                  comicAdapter.notifyDataSetChanged()
              }
          })
          }*/

    private fun getListOfComics() {
        viewModel.getComic().observe(this, Observer {
            Log.e("ComicHomeFragment", it.toString())
            if (it.contentIfNotUsed != null) {
                if (it.isLoading)
                    listener?.onShowProgress()
                else
                    listener?.onHideProgress()

                if (!it.list.isNullOrEmpty()) {
                    Log.e("ViewModel give out", "I am filled")
                    // listOfComics = (it.list!!.filterNotNull().toMutableList() as ArrayList<ComicResults>?)!!
                    listOfComics = it.list!!
                    val text = listOfComics[0]!!.title.toString()
                    Log.e("See the first", text)
                    viewModel.adapter.updateList(listOfComics)
                }

                if (!it.error.isNullOrEmpty())
                    showToast(context!!, it.error!!)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        initViews()
    }
/*

    private fun getAllCommunities(){
        viewModel.getUserCommunities().observe(this, Observer {
            if(it.contentIfNotUsed != null) {
                if (it.isLoading)
                    listener?.onShowProgress()
                else
                    listener?.onHideProgress()

                if (!it.list.isNullOrEmpty()) {
                    communities = it.list!!
                    communityNames.clear()
                    communityNames.add("All")
                    communityNames.addAll(it.list!!.map { community -> community.communityName!! })
                    populateCommunitySpinner(communityNames)
                }

                if (!it.error.isNullOrEmpty())
                    showToast(context!!, it.error!!)
            }
        })
    }
*/


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

    interface OnFragmentInteractionListener : BaseInteractionListener {
        fun onThumbnailClicked(comicResults: ComicResults)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ComicFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
