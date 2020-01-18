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
import com.example.comicfantasy.util.ZoomRecyclerLayout
import com.example.comicfantasy.util.showToast
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject
import android.R
import com.google.android.youtube.player.internal.l


class ComicFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    lateinit var viewModel: ComicFragmentViewModel

    private var listener: OnFragmentInteractionListener? = null
    private var homeActivity: HomeActivity? = null
    // private var listOfComics = mutableListOf<ComicResults>()
    //private var listOfComics = arrayListOf<ComicResults>()
    private var listOfComics = ArrayList<ComicResults?>()

    private lateinit var comicAdapter: ComicFragmentAdapterclass
    private lateinit var layManager: LinearLayoutManager
    private var Id: Int? = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            Id = it.getInt("Id")
           listOfComics =savedInstanceState!!.getStringArrayList("savedList") as ArrayList<ComicResults?>

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(com.example.comicfantasy.R.layout.fragment_home, container, false)

    }

     override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //saving data when orientation change trigger
        //saving  data before ondestroy happens
        //onSaveInstanceState is called before going to another activity or orientation change
        //outState.putStringArrayList("savedList", listOfComics as java.util.ArrayList<String>)
        //outState.putString("savetext", textname)
        //all imnportant data saved and can be restored
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

            //this is  only called after ondestroy-> oncreate occur
            listOfComics = savedInstanceState!!.getParcelableArrayList<ComicResults>("savedList") as ArrayList<ComicResults?>
            //oncreate->onSaveInstanceState->ondestroy->oncreate->onRestoreInstanceState

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(ComicFragmentViewModel::class.java)
        if (savedInstanceState != null) {
            listener
        }
        initViews()
        //getAllComic()
    }


    private fun initViews() {
        getListOfComics()
        viewModel.adapter = ComicFragmentAdapterclass(viewModel.comicList, listener!!, this)
        comic_list.apply {
            this.adapter = viewModel.adapter
            val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
            this.layoutManager = layoutManager
           /* val linearLayoutManager = ZoomRecyclerLayout(context!!)
            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
            this.layoutManager = linearLayoutManager*/

        }
    }

    private fun getListOfComics() {
        viewModel.getComic().observe(this, Observer {
            Log.e("ComicHomeFragment", it.toString())
            if (it.contentIfNotUsed != null) {
                if (it.isLoading)
                    listener?.onShowProgress()
                else
                    listener?.onHideProgress()

                if (!it.list.isNullOrEmpty()) {
                    viewModel.comicList = it.list!! as ArrayList<ComicResults>
                    viewModel.adapter.updateList(it.list as ArrayList<ComicResults>)
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
