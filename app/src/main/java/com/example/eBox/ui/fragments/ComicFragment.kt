package com.example.eBox.ui.fragments

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
import com.example.eBox.adapter.ComicFragmentAdapterclass
import com.example.eBox.viewmodel.ComicFragmentViewModel
import com.example.eBox.data.remote.ComicResults
import com.example.eBox.util.BaseInteractionListener
import com.example.eBox.util.showToast
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject


class ComicFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    lateinit var viewModel: ComicFragmentViewModel
    private var listener: OnFragmentInteractionListener? = null
    private var listOfComics = ArrayList<ComicResults?>()

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
        return inflater.inflate(com.example.eBox.R.layout.fragment_home, container, false)

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (outState != null && listOfComics.isNotEmpty()) {
            listOfComics =
                outState!!.getStringArrayList("savedList") as ArrayList<ComicResults?>
        }
       }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(ComicFragmentViewModel::class.java)
        if (savedInstanceState != null) {
            listener
        }
        initViews()
        viewModel.getAllComicList()
    }


    private fun initViews() {
        getListOfComics()
        viewModel.adapter = ComicFragmentAdapterclass(viewModel.comicList, listener!!, this)
        comic_list.apply {
            this.adapter = viewModel.adapter
            val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            this.layoutManager = layoutManager
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
            }
            if (!it.list.isNullOrEmpty()) {
                viewModel.comicList = it.list!! as ArrayList<ComicResults>
                viewModel.adapter.updateList(it.list as ArrayList<ComicResults>)
            }
            else{
                Log.e("null", "list")
            }
            if (!it.error.isNullOrEmpty())
                showToast(context!!, it.error!!)
        })
    }

    override fun onResume() {
        super.onResume()
        initViews()
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
