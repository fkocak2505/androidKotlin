package com.androidlab.jetpackarchitecture.mvvm.viewModel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import java.lang.ref.WeakReference

abstract class BaseViewModel<N> : ViewModel() {

    private val isLoading = ObservableBoolean()
    private var mNavigator: WeakReference<N>? = null

    /*val loading: ObservableBoolean?
        get() = isLoading*/

    fun isLoading(): ObservableBoolean{
        return isLoading
    }

    fun setIsLoading(isLoading: Boolean) {
        this.isLoading.set(isLoading)
    }

    val navigator: N?
        get() = mNavigator!!.get()

    fun setNavigator(navigator: N) {
        mNavigator = WeakReference(navigator)
    }
}