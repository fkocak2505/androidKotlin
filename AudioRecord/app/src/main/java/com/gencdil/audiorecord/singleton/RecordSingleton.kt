package com.gencdil.audiorecord

import com.gencdil.audiorecord.adapter.RecordModel

class RecordSingleton {

    companion object {
        val instance: RecordSingleton by lazy { RecordSingleton() }
    }

    //==============================================================================================
    /**
     * Response4Forecast
     */
    //==============================================================================================
    private var recordItem: RecordModel? = null

    fun getRecordItem(): RecordModel {
        return recordItem!!
    }

    fun setRecordItem(valOfRecordItem: RecordModel) {
        recordItem = valOfRecordItem
    }

    //==============================================================================================
    /**
     * Response4Forecast
     */
    //==============================================================================================
    private var position: Int? = null

    fun getPosition(): Int {
        return position!!
    }

    fun setPosition(valOfPos: Int) {
        position = valOfPos
    }
}