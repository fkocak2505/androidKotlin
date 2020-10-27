package com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity

import com.onedio.androidside.IRequestListener
import com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.conversation.responseModel.deleteConversation.Response4DeleteConversation
import com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.responseModel.userAllConversation.Response4UserAllConversation

interface IMessageActivityModel {

    fun initApiService()

    fun getUserAllConversation(iRequestListener: IRequestListener<Response4UserAllConversation>)

    fun deleteConversation(deletingConversationID: String,iRequestListener: IRequestListener<Response4DeleteConversation>)

}