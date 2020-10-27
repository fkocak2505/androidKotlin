package com.androidlab.apptheme

import android.app.Application
import androidx.preference.PreferenceManager

class AppThemeApplication: Application(){

    override fun onCreate() {
        super.onCreate()

        val sharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(this)
        val themePref =
            sharedPreferences.getString("themePref", ThemeHelper.DEFAULT_MODE)
        ThemeHelper.applyTheme(themePref!!)
    }
}