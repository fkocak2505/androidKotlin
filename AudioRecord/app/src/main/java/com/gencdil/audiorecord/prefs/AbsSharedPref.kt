package com.gencdil.audiorecord.prefs

abstract class AbsSharedPref<T> {

    abstract fun <T: Any> getValue(): T

    abstract fun <T: Any> setValue(vData: T)

}