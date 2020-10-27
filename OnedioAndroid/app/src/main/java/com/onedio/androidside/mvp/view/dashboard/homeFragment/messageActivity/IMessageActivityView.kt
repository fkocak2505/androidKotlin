package com.onedio.androidside.mvp.view.dashboard.homeFragment.messageActivity

import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.chatModel.Chat
import com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.chatModel.UserSearchChat
import com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.conversation.responseModel.deleteConversation.Response4DeleteConversation
import com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.responseModel.userAllConversation.Response4UserAllConversation

interface IMessageActivityView {

    fun getUserAllConversation()

    fun changeIconIfDarkModeOn()

    fun changeComponentTypeFace()

    fun initMessageFragmentComponent()

    fun selectedDeleteMessage()

    fun clickUserSearchArea()

    fun setChatListAdapter(chatList: List<Chat> )

    fun setUserSearchChatListAdapter(userSearchChatList: List<UserSearchChat>)

    fun handleUserAllConversationData(response4UserAllConversation: Response4UserAllConversation)

    fun handleDeleteConversation(response4DeleteConversation: Response4DeleteConversation)

    fun showLoading()

    fun hideLoading()

    fun showError(response4ErrorObj: Response4ErrorObj)

    fun showEzDialogMessage(titleText: String, messageText: String, buttonText: String, headerColor: Int, titleTextColor: Int, messageTextColor: Int, buttonTextColor: Int)

    fun goBack()

}