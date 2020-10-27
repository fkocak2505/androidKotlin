package com.onedio.androidside.prefs

import android.content.SharedPreferences

@Suppress("UNCHECKED_CAST")
class LongSharedPrefs(
    private val sharedPreferences: SharedPreferences,
    private val key: String,
    private val defaultValue: Long? = null
): AbsSharedPref<Long>() {
    override fun <T : Any> getValue(): T {
        return sharedPreferences.getLong(key, defaultValue!!) as T
    }

    override fun <T : Any> setValue(vData: T) {
        sharedPreferences.edit()
            .putLong(key, vData as Long)
            .apply()
    }
}