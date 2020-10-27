package com.onedio.androidside.mvp


import android.app.Application
import android.content.Context
import androidx.lifecycle.LifecycleObserver
import com.onedio.androidside.common.ThemeHelper
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.onesignalConfig.NotificationHandlerOpened
import com.onedio.androidside.onesignalConfig.NotificationReceivedHandler
import com.onesignal.OneSignal
import com.yandex.metrica.YandexMetrica
import com.yandex.metrica.YandexMetricaConfig


class AnalyticsApplication : Application(), LifecycleObserver {


    override fun onCreate() {
        super.onCreate()

        /*when((resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK)){
            Configuration.UI_MODE_NIGHT_YES -> {
                ThemeHelper.applyTheme(applicationContext, "light")
            }
            else -> {
                val prefences = getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
                val themePref = prefences.getString("mode", "default")
                ThemeHelper.applyTheme(applicationContext, themePref!!)
            }
        }*/

        val prefences =
            getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        val themePref = prefences.getString("mode", "default")

        if(themePref == "default")
            ThemeHelper.applyTheme(applicationContext, "light")
        else
            ThemeHelper.applyTheme(applicationContext, themePref!!)


        val yandexMetricaConfig =
            YandexMetricaConfig.newConfigBuilder(OnedioConstant.YANDEX_METRICA_API_KEY)
                .withSessionTimeout(1800)
                .build()

        YandexMetrica.activate(applicationContext, yandexMetricaConfig)
        YandexMetrica.enableActivityAutoTracking(this)

        OneSignal.startInit(this)
            .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
            .setNotificationReceivedHandler(NotificationReceivedHandler())
            .setNotificationOpenedHandler(NotificationHandlerOpened(applicationContext))
            .unsubscribeWhenNotificationsAreDisabled(true)
            .init()

        sharedPrefInstance = this
        appContext = applicationContext

        //Fabric.with(this, Crashlytics())
        //sAnalytics = GoogleAnalytics.getInstance(this)
    }

    companion object {

        var sharedPrefInstance: AnalyticsApplication? = null
            private set
        lateinit var appContext: Context

        @JvmOverloads
        operator fun get(context: Context = appContext): AnalyticsApplication {
            return context.applicationContext as AnalyticsApplication
        }

    }
}