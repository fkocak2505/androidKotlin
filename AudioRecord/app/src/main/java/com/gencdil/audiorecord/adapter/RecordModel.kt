package com.gencdil.audiorecord.adapter

data class RecordModel(
    val recordId: String,
    val date: String,
    var playIcon: Int,
    val recordPath: String,
    var isPlay: Int,
    val time: String,
    val fileName: String

)