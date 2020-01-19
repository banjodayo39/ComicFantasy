package com.example.eBox.games.fragments

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

import com.example.eBox.R
import com.example.eBox.adapter.GamesAdapter
import com.example.eBox.data.remote.GamesResult
import com.example.eBox.games.viewmodel.GamesViewModel
import com.example.eBox.util.BaseInteractionListener
import com.example.eBox.util.showToast
import com.littlemango.stacklayoutmanager.StackLayoutManager
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_games.*
import javax.inject.Inject


class GamesFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private var listOfTrivia = ArrayList<GamesResult>()
    lateinit var viewModel: GamesViewModel
    private lateinit var gamesAdapter: GamesAdapter
    private lateinit var layManager: LinearLayoutManager
    private lateinit var stackLayout: StackLayoutManager
    private var optionSelected = ""
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

            if (it.list != null ) {
                listOfTrivia.addAll(it.list!!.toCollection(ArrayList<GamesResult>()))
                gamesAdapter.notifyDataSetChanged()

            }
            if (!it.error.isNullOrEmpty())
                showToast(context!!, it.error!!)
        })
    }

    private fun initViews() {
        // val  recyclerView = view?.findViewById<RecyclerView>(R.id.games_recycler_list)
        val orientation = StackLayoutManager.ScrollOrientation.TOP_TO_BOTTOM

        Log.e("", "recycler")
        gamesAdapter = GamesAdapter(listOfTrivia, listener!!, this)
        stackLayout = StackLayoutManager(orientation)
        layManager = LinearLayoutManager(context)
        games_recycler_list?.adapter = gamesAdapter
        games_recycler_list?.layoutManager = stackLayout
    }

    private fun answerSelection() {


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


    interface OnFragmentInteractionListener : BaseInteractionListener {
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
