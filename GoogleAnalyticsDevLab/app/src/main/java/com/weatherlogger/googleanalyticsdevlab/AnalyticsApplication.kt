package com.weatherlogger.googleanalyticsdevlab

import android.app.Application
import com.google.android.gms.analytics.GoogleAnalytics
import com.google.android.gms.analytics.Tracker


class AnalyticsApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        /*OneSignal.startInit(this)
            .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
            .unsubscribeWhenNotificationsAreDisabled(true)
            .init()*/

        /*.setNotificationReceivedHandler(NotificationReceivedHandler())
            .setNotificationOpenedHandler(NotificationHandlerOpened(applicationContext))*/

        //Fabric.with(this, Crashlytics())
        sAnalytics = GoogleAnalytics.getInstance(this)
    }

    /**
     * Gets the default [Tracker] for this [Application].
     * @return tracker
     */
    // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
    val defaultTracker: Tracker
        @Synchronized get() {
            if (sTracker == null) {
                sTracker = sAnalytics!!.newTracker(R.xml.global_tracker)
            }

            return sTracker!!
        }

    companion object {

        private var sAnalytics: GoogleAnalytics? = null
        private var sTracker: Tracker? = null
    }
}