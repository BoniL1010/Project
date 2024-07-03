package com.example.myapplication.ui

import android.content.Intent
import android.os.Bundle
import android.provider.Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.MyApplication
import com.example.myapplication.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestNotificationListenerPermission()
    }

    private fun requestNotificationListenerPermission() {
        if (MyApplication.instance.shouldShowNotificationsPopUp()) {
            AlertDialog.Builder(this)
                .setTitle(R.string.notifications_pop_up_title)
                .setMessage(R.string.notifications_pop_up_message)
                .setNegativeButton(android.R.string.cancel, null)
                .setPositiveButton(
                    android.R.string.ok
                ) { _, _ ->
                    val intent = Intent(ACTION_NOTIFICATION_LISTENER_SETTINGS)
                    startActivity(intent)
                    MyApplication.instance.setNotificationsPopUpShown()
                }
                .show()
        }
    }
}