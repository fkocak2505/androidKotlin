package com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.chatModel

data class Chat(
    var conversationID: String,
    var mName: String,
    var mLastChat: String,
    var mTime: String,
    var mImage: String,
    var online: Boolean,
    var isDelete: Boolean,
    var updatedDate: String,
    var userId: String
)