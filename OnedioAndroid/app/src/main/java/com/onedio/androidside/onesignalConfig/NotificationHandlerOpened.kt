package com.onedio.androidside.onesignalConfig


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import com.google.gson.Gson
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.mvp.view.dashboard.DashboardActivityViewImpl
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

        result.notification.payload.additionalData?.let {

            val data = Gson().fromJson(
                result.notification.payload.additionalData.toString(),
                NotificationDataModel::class.java
            )

            val legacyId: String? = data.legacyid
            val isTest: String? = data.isTest

            if (actionType == OSNotificationAction.ActionType.ActionTaken)
                Log.i("OneSignalExample", "Button pressed with id: " + result.action.actionID)

            OnedioCommon.sendLog2Crashlytics("$legacyId & $isTest push geldi..")

            val intent = Intent(context, DashboardActivityViewImpl::class.java)
            intent.putExtra(
                "NOTIFICATION_DATA",
                Gson().toJson(data)
            )
            intent.putExtra("IS_NOTIFICATION_COMING", true)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)

        } ?: run {
            result.notification.payload.launchURL?.let {
                val browserIntent =
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(result.notification.payload.launchURL)
                    )
                browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(browserIntent)
            }?: run{
                val intent = Intent(context, DashboardActivityViewImpl::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
            }
        }

    }
}
