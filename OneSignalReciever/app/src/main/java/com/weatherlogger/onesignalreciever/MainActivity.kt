package com.weatherlogger.onesignalreciever

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.onesignal.OneSignal

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        OneSignal.startInit(this)
            .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
            .setNotificationReceivedHandler(NotificationReceivedHandler())
            .setNotificationOpenedHandler(NotificationHandlerOpened(applicationContext))
            .unsubscribeWhenNotificationsAreDisabled(true)
            .init()
    }
}
