package com.example.onesignal

import android.util.Log
import com.onesignal.OSNotification
import com.onesignal.OneSignal


internal class OneSignalPushNotificationReceiver : OneSignal.NotificationReceivedHandler {
    override fun notificationReceived(notification: OSNotification) {
        val data = notification.payload.additionalData
        val customKey: String?

        if (data != null) {
            customKey = data.optString("customkey", null!!)
            if (customKey != null)
                Log.i("OneSignalExample", "customkey set with value: $customKey")
        }
    }
}