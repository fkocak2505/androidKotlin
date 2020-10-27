package com.musicore.darkmodejuly

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate


object ThemeHelper {
    const val LIGHT_MODE = "light"
    const val DARK_MODE = "dark"
    const val DEFAULT_MODE = "default"
    fun applyTheme(context: Context, themePref: String) {

        val prefences = context.getSharedPreferences("Deneme", Context.MODE_PRIVATE)
        val editor = prefences.edit()

        when (themePref) {
            LIGHT_MODE -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                editor.putString("mode", LIGHT_MODE)
            }
            DARK_MODE -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                editor.putString("mode", DARK_MODE)
            }
            else -> {
                editor.putString("mode", DEFAULT_MODE)

                /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
                }*/
            }
        }

        editor.apply()
    }
}
