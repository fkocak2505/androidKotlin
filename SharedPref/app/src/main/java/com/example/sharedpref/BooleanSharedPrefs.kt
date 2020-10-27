package com.example.sharedpref

import android.content.SharedPreferences

@Suppress("UNCHECKED_CAST")
class BooleanSharedPrefs(
    private val sharedPreferences: SharedPreferences,
    private val key: String,
    private val defaultValue: Boolean
) : AbsSharedPref<Boolean>() {

    override fun <T : Any> getValue(): T {
        return sharedPreferences.getBoolean(key, defaultValue) as T
    }

    override fun <T : Any> setValue(vData: T) {
        sharedPreferences.edit()
            .putBoolean(key, vData as Boolean)
            .apply()
    }
}