package com.weatherlogger.androidapp.prefs

//==============================================================================================
/**
 * Abstract SharedPrefs Class. Just has get and set function with generic Type
 * ForExample; Int, String, Boolean
 */
//==============================================================================================

abstract class AbsSharedPref<T> {

    abstract fun <T: Any> getValue(): T

    abstract fun <T: Any> setValue(vData: T)

}