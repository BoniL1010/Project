package com.example.myapplication.util


import android.app.Notification
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import com.example.myapplication.MyApplication

data class NotificationData(
    val originatingPackageName: String,
    val title: String,
    val body: String,
    val timestamp: Long
)

class MyNotificationListenerService : NotificationListenerService() {

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        super.onNotificationPosted(sbn)
        sbn?.let {
            val originatingPackage = it.packageName
            val extras = it.notification.extras
            val title = extras.getString(Notification.EXTRA_TITLE) ?: ""
            val body = extras.getCharSequence(Notification.EXTRA_TEXT)?.toString() ?: ""
            val timestamp = System.currentTimeMillis()

            val notificationData = NotificationData(originatingPackage, title, body, timestamp)
            DataCoder.encryptData(notificationData.toString())

            if (MyApplication.instance.shouldBlockPackageNotifications(originatingPackage)) {
                cancelNotification(it.key)
            }
        }
    }

}
