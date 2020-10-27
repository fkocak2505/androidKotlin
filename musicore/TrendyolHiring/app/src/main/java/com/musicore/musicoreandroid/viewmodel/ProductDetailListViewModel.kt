package com.musicore.musicoreandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.musicore.musicoreandroid.model.Response4PLayList

class ProductDetailListViewModel: ViewModel() {

    val playListLiveData = MutableLiveData<Response4PLayList>()

    fun fetch(){
        /*val itemOfPlayList = PlayList("1")
        playListLiveData.value = itemOfPlayList*/
    }

}