package com.weatherlogger.onesignalreciever

import android.content.Context
import android.content.Intent
import android.util.Log
import com.onesignal.OSNotificationAction
import com.onesignal.OSNotificationOpenResult
import com.onesignal.OneSignal

internal class NotificationHandlerOpened : OneSignal.NotificationOpenedHandler {

    private val context: Context

    constructor(context: Context) {
        this.context = context
    }

    // This fires when a notification is opened by tapping on it.
    override fun notificationOpened(result: OSNotificationOpenResult) {
        val actionType = result.action.type
        val data = result.notification.payload.additionalData
        val customKey: String?

        Log.i(
            "OSNotificationPayload",
            "result.notification.payload.toJSONObject().toString(): " + result.notification.payload.toJSONObject().toString()
        )

        if (data != null) {
            customKey = data.optString("customkey", null!!)
            if (customKey != null)
                Log.i("OneSignalExample", "customkey set with value: $customKey")
        }

        if (actionType == OSNotificationAction.ActionType.ActionTaken)
            Log.i("OneSignalExample", "Button pressed with id: " + result.action.actionID)


        val intent = Intent(context, Deneme::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT or Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)

    }
}