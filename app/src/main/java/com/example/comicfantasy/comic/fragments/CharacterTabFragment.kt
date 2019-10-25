package com.example.comicfantasy.comic.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager

import com.example.comicfantasy.R
import com.example.comicfantasy.adapter.ComicStoryAdaapter
import com.example.comicfantasy.adapter.HomeFragmentAdapterclass
import com.example.comicfantasy.data.remote.Image
import com.example.comicfantasy.data.remote.Results
import com.example.comicfantasy.util.GridItemDecoration
import com.example.comicfantasy.util.loadImageWithGlide
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.comic_story_list_items.*
import kotlinx.android.synthetic.main.comic_story_list_items.view.*
import kotlinx.android.synthetic.main.fragment_character.*
import kotlinx.android.synthetic.main.fragment_home.*


class CharacterTabFragment : DaggerFragment() {

    private var listener: OnFragmentInteractionListener? = null
    private var results: Results?=null

    private lateinit var comicAdapter:ComicStoryAdaapter
    private lateinit var layManager:GridLayoutManager
    private  var images= ArrayList<Image>()
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
        return inflater.inflate(R.layout.fragment_character, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var i=0
        results?.images?.forEach {

         images.add(i,it!!)
            i++
        }
    }


    private fun initViews() {
        textViewCharacter.text=results?.name
        textViewAvailable.text=results?.characters?.available.toString()
        textViewRole.text=results?.role.toString()
        textViewCollectionUrl.text=results?.characters?.collectionURI
    /*    comicAdapter = ComicStoryAdaapter(images)
        layManager = GridLayoutManager(context,3)
        comic_story_list.addItemDecoration(GridItemDecoration(10,3))
        comic_story_list.adapter = comicAdapter
        comic_story_list.layoutManager = layManager
*/
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
        fun newInstance(results: Results) =
            CharacterTabFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("results",results)

                }
            }
    }
}
