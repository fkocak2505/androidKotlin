package com.onedio.androidside.mvp.presenter.dashboard.homeFragment.messagePresenter

interface IMessageActivityPresenter {

    fun getUserAllConversation()

    fun deleteConversation(deletingConversationID: String)

}