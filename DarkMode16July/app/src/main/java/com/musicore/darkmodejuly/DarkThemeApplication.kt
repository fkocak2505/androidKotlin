package com.musicore.darkmodejuly

import android.app.Application
import android.content.Context


class DarkThemeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val prefences = getSharedPreferences("Deneme", Context.MODE_PRIVATE)
        val themePref = prefences.getString("mode", "default")
        ThemeHelper.applyTheme(applicationContext, themePref!!)
    }
}
