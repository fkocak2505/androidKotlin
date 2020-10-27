package com.musicore.musicoreandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.musicore.musicoreandroid.model.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class ProductListViewModel @Inject constructor() :ViewModel() {

    private val playListService = PlayListApisService()
    private val disposable = CompositeDisposable()

    val playList = MutableLiveData<MutableList<WidgetsItem>>()
    val playListError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh(){
        fetchFromRemote()
    }

    private fun fetchFromRemote(){
        loading.value = true
        disposable.add(
            playListService.getTrendyolData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Response4TrendyolData>(){
                    override fun onSuccess(response4PLayListData: Response4TrendyolData) {
                        playList.value = response4PLayListData.widgets
                        playListError.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        playListError.value = true
                        loading.value = false
                        e.printStackTrace()
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}