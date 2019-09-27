package com.example.comicfantasy.series.fragment


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.comicfantasy.R
import com.example.comicfantasy.adapter.SeriesFragmentAdapter
import com.example.comicfantasy.data.remote.Image
import com.example.comicfantasy.home.viewmodel.HomeFragmentViewModel
import com.example.comicfantasy.util.BaseInteractionListener
import com.example.comicfantasy.util.GridItemDecoration
import com.example.comicfantasy.util.showToast
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_series.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class SeriesFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    lateinit var viewModel: HomeFragmentViewModel
    private var listener: OnFragmentInteractionListener? = null
    private var listOfImages = ArrayList<Image>()
    private lateinit var comicAdapter: SeriesFragmentAdapter
    private lateinit var layManager: GridLayoutManager



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
        viewModel = ViewModelProviders.of(this, factory).get(HomeFragmentViewModel::class.java)

        return inflater.inflate(R.layout.fragment_series, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        getListOfComics()

    }


    private fun initViews() {
        comicAdapter = SeriesFragmentAdapter(listOfImages)
        layManager = GridLayoutManager(context,2)
        image_list.addItemDecoration(GridItemDecoration(10,2))
        image_list.adapter = comicAdapter
        image_list.layoutManager = layManager
    }


    private fun getListOfComics() {
        viewModel.getComic().observe(this, Observer {
            if (it.isLoading)
                listener?.onShowProgress()
            else
                listener?.onHideProgress()

            if (it.data != null && !it.data?.data?.results.isNullOrEmpty()) {
                Log.e("ComicSeriesFragment", it.toString())
                it.data?.data?.results?.map { results ->  listOfImages.addAll(results?.images as ArrayList<Image>) }
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
            throw RuntimeException( " must implement OnFragmentInteractionListener")
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
        fun newInstance(id:Int) =
            SeriesFragment().apply {
                arguments = Bundle().apply {
                    putInt("id",id)
                       }
            }


       /* fun newInstance(images: ArrayList<Image>?) =
            SeriesFragment().apply {
                arguments = Bundle().apply {
                    if(!images.isNullOrEmpty())
                        putParcelableArrayList("images", images)

                    *//*putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
             *//*   }
            }*/
    }

}
