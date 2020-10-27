package com.onedio.androidside.prefs

abstract class AbsSharedPref<T> {

    abstract fun <T: Any> getValue(): T

    abstract fun <T: Any> setValue(vData: T)

}