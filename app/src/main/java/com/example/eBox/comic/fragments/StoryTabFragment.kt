package com.example.eBox.comic.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat

import com.example.eBox.R
import com.example.eBox.data.remote.ComicResults
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_story_tab.*


class StoryTabFragment : DaggerFragment() {

    private var listener: OnFragmentInteractionListener? = null
    private var comicResults:ComicResults?=null

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
        return inflater.inflate(R.layout.fragment_story_tab, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
          displaySetUp()
    }

    private fun displaySetUp() {
        if ( comicResults?.description != null) {
            story_text_tv.text =
                HtmlCompat.fromHtml(comicResults?.description!!, HtmlCompat.FROM_HTML_MODE_LEGACY)
        }
    }


    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
      /*  if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString())
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

        fun newInstance(comicResults: ComicResults) =
            StoryTabFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("comicResults",comicResults)

                }
            }
    }
}
