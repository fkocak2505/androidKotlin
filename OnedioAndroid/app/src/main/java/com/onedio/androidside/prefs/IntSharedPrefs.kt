package com.onedio.androidside.prefs

import android.content.SharedPreferences

@Suppress("UNCHECKED_CAST")
class IntSharedPrefs(
    private val sharedPreferences: SharedPreferences,
    private val key: String,
    private val defaultValue: Int

): AbsSharedPref<Int>() {
    override fun <T : Any> getValue(): T {
        return sharedPreferences.getInt(key, defaultValue) as T
    }

    override fun <T : Any> setValue(vData: T) {
        sharedPreferences.edit()
            .putInt(key, vData as Int)
            .apply()
    }
}