package com.example.eBox.notification.fragment

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.eBox.data.remote.MovieData
import com.example.eBox.home.ui.HomeActivity
import com.example.eBox.home.ui.Recommender
import com.example.eBox.movie.viewmodel.MovieViewModel
import com.example.eBox.util.ModelUtils
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_notification.*
import javax.inject.Inject



class NotificationFragment : DaggerFragment() {
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private var listener: OnFragmentInteractionListener? = null
    lateinit var viewModel: MovieViewModel
    lateinit var notificationChannel: NotificationChannel
    lateinit var notificationManager: NotificationManager
    lateinit var builder: Notification.Builder
    private val REQUEST_CODE = 0
    private var movieMap = ArrayList<MovieData>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
           // movieMap = it.getParcelableArrayList<MovieMap>(Constants.movieMap)!!
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(com.example.eBox.R.layout.fragment_notification, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
           }

    private fun recommender(){
        val recommender = Recommender(ModelUtils.assetFilePath(context,"recommender.pt"))
        // val pred = recommender.predict()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface OnFragmentInteractionListener {

    }

    companion object {

        @JvmStatic
        fun newInstance() =
            NotificationFragment().apply {
                arguments = Bundle().apply {
                   // if(movieMap != null) putParcelableArrayList(Constants.movieMap,movieMap)
                }
            }
    }
}
