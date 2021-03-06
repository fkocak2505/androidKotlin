package com.onedio.androidside.prefs

import android.content.SharedPreferences

@Suppress("UNCHECKED_CAST")
class StringSharedPrefs(
    private val sharedPreferences: SharedPreferences,
    private val key: String,
    private val defaultValue: String? = null
): AbsSharedPref<String>() {
    override fun <T : Any> getValue(): T {
        return sharedPreferences.getString(key, defaultValue) as T
    }

    override fun <T : Any> setValue(vData: T) {
        sharedPreferences.edit()
            .putString(key, vData as String)
            .apply()
    }
}