package com.example.comicfantasy.games.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.comicfantasy.R
import com.example.comicfantasy.adapter.GamesAdapter
import com.example.comicfantasy.adapter.HomeFragmentAdapterclass
import com.example.comicfantasy.data.remote.GamesResult
import com.example.comicfantasy.data.remote.MovieResult
import com.example.comicfantasy.data.remote.Trivia
import com.example.comicfantasy.games.viewmodel.GamesViewModel
import com.example.comicfantasy.movie.fragment.MovieFragment
import com.example.comicfantasy.movie.viewmodel.MovieViewModel
import com.example.comicfantasy.util.BaseInteractionListener
import com.example.comicfantasy.util.GridItemDecoration
import com.example.comicfantasy.util.showToast
import com.littlemango.stacklayoutmanager.StackLayout
import com.littlemango.stacklayoutmanager.StackLayoutManager
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_games.*
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject


class GamesFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private var listOfTrivia= ArrayList<GamesResult>()
    lateinit var viewModel: GamesViewModel
    private lateinit var gamesAdapter: GamesAdapter
    private lateinit var layManager:LinearLayoutManager
    private lateinit var stackLayout: StackLayoutManager
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_games, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(GamesViewModel::class.java)
        getListOfTrivia()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }



    private fun getListOfTrivia() {
        viewModel.getGames().observe(this, Observer {
            if (it.isLoading)
                listener?.onShowProgress()
            else
              listener?.onHideProgress()

            if (it.data != null && !it.data?.results.isNullOrEmpty()) {
                listOfTrivia.addAll(it.data?.results as ArrayList<GamesResult>)
                gamesAdapter.notifyDataSetChanged()

            }
            if (!it.error.isNullOrEmpty())
                showToast(context!!, it.error!!)
        })
    }

    private fun initViews(){
      // val  recyclerView = view?.findViewById<RecyclerView>(R.id.games_recycler_list)
        val  orientation = StackLayoutManager.ScrollOrientation.LEFT_TO_RIGHT


       Log.e("","recycler")
        gamesAdapter = GamesAdapter(listOfTrivia,listener!!)
        stackLayout= StackLayoutManager(orientation)
       layManager= LinearLayoutManager(context)
       games_recycler_list?.adapter = gamesAdapter
        games_recycler_list?.layoutManager = stackLayout
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


    interface OnFragmentInteractionListener :BaseInteractionListener{

    }

    companion object {

        @JvmStatic
        fun newInstance() =
            GamesFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
