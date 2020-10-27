package com.weatherlogger.sharedprefinsideapplicatonclass

import android.app.Application
import android.content.Context


class CustomAppClass : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        appContext = applicationContext

    }

    companion object {
        var instance: CustomAppClass? = null
            private set
        lateinit var appContext: Context
        @JvmOverloads
        operator fun get(context: Context = appContext): CustomAppClass {
            return context.applicationContext as CustomAppClass
        }
    }
}