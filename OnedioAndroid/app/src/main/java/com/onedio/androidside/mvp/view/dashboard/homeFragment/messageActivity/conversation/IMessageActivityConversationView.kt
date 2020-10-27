package com.onedio.androidside.mvp.view.dashboard.homeFragment.messageActivity.conversation

import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.conversation.responseModel.Response4ConversationIDMessage
import com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.conversation.responseModel.sendMessage.Response4SendMessage
import com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.conversation.responseModel.startConversation.Response4StartConversation
import com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.chatModel.conversationModel.ChatData

interface IMessageActivityConversationView {

    fun setEmojiProvider()

    fun initConversationComponent()

    fun changeIconIfDarkModeOn()

    fun setUserProfilePhoto()

    fun clickUserPhotoOrUserName()

    fun startConversation()

    fun getConversationIDMessage()

    fun handleStartConversationData(response4StartConversation: Response4StartConversation)

    fun handleConversationIDMessages(response4ConversationIDMessage: Response4ConversationIDMessage)

    fun handleSendMessageData(response4SendMessage: Response4SendMessage)

    fun addMessageDate2AllMessages(response4ConversationIDMessage: Response4ConversationIDMessage): List<ChatData>

    fun setChatDataAdapter(chatData: List<ChatData>)

    fun emojiButtonClickListener()

    fun clickWritingMessageAreaListener()

    fun clickSendMessageListener()

    fun setUpEmojiPopup()

    fun showLoading()

    fun hideLoading()

    fun showError(response4ErrorObj: Response4ErrorObj)

    fun goBack()

    fun showEzDialogMessage(isCallbackTrigger: Boolean,titleText: String, messageText: String, buttonText: String, headerColor: Int, titleTextColor: Int, messageTextColor: Int, buttonTextColor: Int)

    fun goDashboardActivity()

}