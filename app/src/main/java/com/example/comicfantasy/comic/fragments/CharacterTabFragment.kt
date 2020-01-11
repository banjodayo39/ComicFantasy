package com.example.comicfantasy.comic.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.comicfantasy.R
import com.example.comicfantasy.adapter.ComicCharacterAdapter
import com.example.comicfantasy.data.remote.Image
import com.example.comicfantasy.data.remote.ComicResults
import com.example.comicfantasy.util.GridItemDecoration
import com.example.comicfantasy.util.ItemDecoration
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_character.*


class CharacterTabFragment : DaggerFragment() {

    private var listener: OnFragmentInteractionListener? = null
    private var comicResults: ComicResults?=null

    private lateinit var comicAdapter:ComicCharacterAdapter
    private lateinit var layManager:GridLayoutManager
    private  var authorNames= ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            comicResults=it.getParcelable("comicResults")

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_character, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        for (it in comicResults!!.creators!!.items!!) {
            authorNames.add(it!!.name!!)
        }
    }


    private fun initViews() {
        if(authorNames.size < 1){
            Toast.makeText(activity, getString(R.string.no_author_available), Toast.LENGTH_LONG).show()
        }
        author_recycler_view.apply {
            adapter = ComicCharacterAdapter(authorNames)
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
            addItemDecoration(ItemDecoration(context,0,0))
        }
    }



    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
       /* if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }*/
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {

        @JvmStatic
        fun newInstance(comicResults: ComicResults) =
            CharacterTabFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("comicResults",comicResults)

                }
            }
    }
}
