package com.example.comicfantasy.notification.fragment

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Build.VERSION_CODES.N
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.comicfantasy.R
import com.example.comicfantasy.home.ui.HomeActivity
import com.example.comicfantasy.notification.viewmodel.NotificationViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_notification.*
import javax.inject.Inject


class NotificationFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private var listener: OnFragmentInteractionListener? = null
    lateinit var viewModel: NotificationViewModel
    lateinit var notificationChannel: NotificationChannel
    lateinit var notificationManager: NotificationManager
    lateinit var builder: Notification.Builder
    private val REQUEST_CODE = 0


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
        return inflater.inflate(R.layout.fragment_notification, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(NotificationViewModel::class.java)

        notificationManager = getSystemService(
            context!!,
            NotificationManager::class.java
        ) as NotificationManager

        switch_button.setOnCheckedChangeListener { buttonView, isChecked ->

            if (isChecked) {
                viewModel.isAlarmOn
                viewModel.setTimeSelected(1)
                viewModel.setAlarm(true)
                Toast.makeText(activity!!, "Notification Subscribed", Toast.LENGTH_SHORT).show()
                createChannel(
                    getString(R.string.notification_channel_id),
                    getString(R.string.notification_channel_name)
                )

            } else {
                viewModel.setAlarm(false)
                Toast.makeText(activity!!, "Notification Unsubscribed", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun createChannel(channelId: String, channelName: String) {

        val intent = Intent(activity, HomeActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            activity,
            REQUEST_CODE,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        // TODO: Step 1.6 START create a channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                channelId,
                channelName,
                // TODO: Step 2.4 change importance
                NotificationManager.IMPORTANCE_HIGH
            )// TODO: Step 2.6 disable badges for this channel
                .apply {
                    setShowBadge(false)
                }

            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.description = getString(R.string.notification_channel_description)

            builder = Notification.Builder(activity, channelId)
                .setSmallIcon(R.drawable.ic_ondemand_video_dark_mint_24dp)
                .setContentTitle(
                    activity!!
                        .getString(R.string.notification_title)
                )
                .setContentText(context!!.getText(R.string.notification_channel_description).toString())
                .setLargeIcon(
                    BitmapFactory.decodeResource(
                        this.resources,
                        R.drawable.ic_ondemand_video_dark_mint_24dp
                    )
                )
                .setContentIntent(pendingIntent)

        } else {
            builder = Notification.Builder(activity)
                .setSmallIcon(R.drawable.ic_ondemand_video_dark_mint_24dp)
                .setContentTitle(
                    activity!!
                        .getString(R.string.notification_title)
                )
                .setContentText(context!!.getText(R.string.notification_channel_description).toString())
                .setLargeIcon(
                    BitmapFactory.decodeResource(
                        this.resources,
                        R.drawable.ic_ondemand_video_dark_mint_24dp
                    )
                )
                .setContentIntent(pendingIntent)
        }
                       notificationManager.notify(1234,builder.build())

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
                }
            }
    }
}
