package com.onedio.androidside.mvp.view.dashboard.homeFragment.messageActivity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_message.*
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.common.OnedioSharedPrefs
import com.onedio.androidside.common.model.OnedioEzDialogMessageModel4Activity
import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.MessageActivityModelImpl
import com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.chatModel.Chat
import com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.chatModel.UserSearchChat
import com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.conversation.DeletedConversationDataModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.conversation.responseModel.deleteConversation.Response4DeleteConversation
import com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.responseModel.userAllConversation.ConversationData
import com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.responseModel.userAllConversation.Response4UserAllConversation
import com.onedio.androidside.mvp.presenter.dashboard.homeFragment.messagePresenter.MessageActivityPresenterImpl
import com.onedio.androidside.mvp.view.dashboard.DashboardActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.messageActivity.chatAdapter.ChatAdapter
import com.onedio.androidside.mvp.view.dashboard.homeFragment.messageActivity.chatAdapter.holder.ViewHolder
import com.onedio.androidside.mvp.view.dashboard.homeFragment.messageActivity.conversation.MessageActivityConversationViewImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.messageActivity.userSearchAdapter.UserSearchAdapter
import com.onedio.androidside.singleton.OnedioSingletonPattern

class MessageActivityViewImpl : AppCompatActivity(),
    IMessageActivityView, ViewHolder.ClickListener  {

    private lateinit var messageActivityPresenterImpl: MessageActivityPresenterImpl

    private lateinit var mAdapter: ChatAdapter
    private lateinit var mAdapter4UserSearchChatList: UserSearchAdapter

    var deletedConversationItemPositions: MutableList<Int> = mutableListOf()
    var conversationDeletedDates: MutableList<DeletedConversationDataModel> = mutableListOf()
    var deletedItemPosition: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)

        initMessageFragmentComponent()

        changeIconIfDarkModeOn()

        changeComponentTypeFace()

        clickUserSearchArea()

        getUserAllConversation()

        selectedDeleteMessage()

        goBack()

    }

    //==================================================================================================================
    /**
     * Change Icon if Dark Mode is On...
     */
    //==================================================================================================================
    override fun changeIconIfDarkModeOn() {
        if (OnedioSharedPrefs(applicationContext).isNightModeEnabled()) {
            messageGoBack.setImageResource(R.drawable.ic_back_dark_mode)
            searchAreaFollower.setCompoundDrawablesWithIntrinsicBounds(
                ContextCompat.getDrawable(
                    applicationContext,
                    R.drawable.ic_search_dark_mode
                ),
                null,
                null,
                null
            )
        } else {
            messageGoBack.setImageResource(R.drawable.ic_back)
            searchAreaFollower.setCompoundDrawablesWithIntrinsicBounds(
                ContextCompat.getDrawable(
                    applicationContext!!,
                    R.drawable.ic_search
                ),
                null,
                null,
                null
            )
        }
    }

    //==================================================================================================================
    /**
     * Change Component TypeFace...
     */
    //==================================================================================================================
    override fun changeComponentTypeFace() {
        applicationToolbarTitle.typeface = OnedioCommon.changeTypeFace4Activity(applicationContext)
        searchAreaFollower.typeface = OnedioCommon.changeTypeFace4Activity(applicationContext)
    }

    //==================================================================================================================
    /**
     * Gete User All Conversation...
     */
    //==================================================================================================================
    override fun getUserAllConversation() {
        messageActivityPresenterImpl.getUserAllConversation()
    }

    //==================================================================================================================
    /**
     * Handle User All Conversation Data...
     */
    //==================================================================================================================
    override fun handleUserAllConversationData(response4UserAllConversation: Response4UserAllConversation) {

        if (response4UserAllConversation.dataOfUserAllConversation?.conversationList?.size == 0) {
            txtOfNoMessage.visibility = View.VISIBLE
            txtOfNoMessage.text = resources.getString(R.string.txtOfNoMessage)

        } else {
            txtOfNoMessage.visibility = View.GONE
            setChatListAdapter(fillUserAllConversationData(response4UserAllConversation))
        }
    }

    //==================================================================================================================
    /**
     * Init Message Fragment Component...
     */
    //==================================================================================================================
    override fun initMessageFragmentComponent() {
        supportActionBar?.hide()

        messageRecylerView.setHasFixedSize(true)
        messageRecylerView.layoutManager = LinearLayoutManager(applicationContext)

        searchUserRecylerView.setHasFixedSize(true)
        searchUserRecylerView.layoutManager =
            LinearLayoutManager(applicationContext)

        messageActivityPresenterImpl =
            MessageActivityPresenterImpl(
                MessageActivityModelImpl(),
                this
            )

    }

    //==================================================================================================================
    /**
     * Select Delete Message
     */
    //==================================================================================================================
    override fun selectedDeleteMessage() {
        selectedDeleteMessage.setOnClickListener() {
            //setChatListAdapter(mAdapter.arrayList.filter { elem -> !elem.isDelete })

            if (mAdapter.selectedItemCount > 1) {
                Toast.makeText(
                    applicationContext,
                    "Lütfen konuşmaları tek tek siliniz.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                conversationDeletedDates = mutableListOf()
                deletedConversationItemPositions = mutableListOf()

                var deletingConversationID =
                    mAdapter.arrayList[deletedItemPosition!!].conversationID
                messageActivityPresenterImpl.deleteConversation(deletingConversationID)

                selectedDeleteMessage.visibility = View.GONE
            }
        }
    }

    //==================================================================================================================
    /**
     * Click User Search Area...
     */
    //==================================================================================================================
    override fun clickUserSearchArea() {
        searchAreaFollower.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                if (s.isNotEmpty()) {
                    messageRecylerView.visibility = View.GONE
                    searchUserRecylerView.visibility = View.VISIBLE

                    txtOfNoMessage.visibility = View.GONE

                    setUserSearchChatListAdapter(fillSearchUserChatListData())
                }
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (s.isEmpty()) {
                    messageRecylerView.visibility = View.VISIBLE
                    txtOfNoMessage.visibility = View.VISIBLE
                    searchUserRecylerView.visibility = View.GONE
                }
            }
        })
    }

    //==================================================================================================================
    /**
     * Handle Delete Conversation Data...
     */
    //==================================================================================================================
    override fun handleDeleteConversation(response4DeleteConversation: Response4DeleteConversation) {

        if (response4DeleteConversation.status?.code == 200) {
            if (response4DeleteConversation.dataOfDeleteConversation?.success!!) {
                messageActivityPresenterImpl.getUserAllConversation()
            } else
                showEzDialogMessage(
                    "Hata..!",
                    "Mesaj silme sırasında bir hata oluştu..",
                    "Tamam",
                    ContextCompat.getColor(
                        applicationContext!!,
                        R.color.tabIndicatorColor4Proile
                    ),
                    ContextCompat.getColor(applicationContext!!, R.color.constWhite),
                    ContextCompat.getColor(applicationContext!!, R.color.constTextColor),
                    ContextCompat.getColor(applicationContext!!, R.color.constTextColor)
                )
        } else
            showEzDialogMessage(
                "Hata..!",
                response4DeleteConversation.status?.message!!,
                "Tamam",
                ContextCompat.getColor(
                    applicationContext!!,
                    R.color.tabIndicatorColor4Proile
                ),
                ContextCompat.getColor(applicationContext!!, R.color.constWhite),
                ContextCompat.getColor(applicationContext!!, R.color.constTextColor),
                ContextCompat.getColor(applicationContext!!, R.color.constTextColor)
            )
    }

    //==================================================================================================================
    /**
     * onItemClick for Conversation List...
     */
    //==================================================================================================================
    override fun onItemClicked(position: Int) {
        if (mAdapter.selectedItemCount > 0)
        //toggleSelection(position)
        else {
            OnedioSingletonPattern.instance.setConversationChatData(mAdapter.arrayList[position])
            OnedioSingletonPattern.instance.setConversationStart(true)
            OnedioSingletonPattern.instance.setConversationUserImageURL(mAdapter.arrayList[position].mImage)

            startActivity(
                Intent(
                    applicationContext,
                    MessageActivityConversationViewImpl::class.java
                )
            )
        }

    }

    //==================================================================================================================
    /**
     * onItemLongClicked for Conversation List...
     */
    //==================================================================================================================
    override fun onItemLongClicked(position: Int): Boolean {
        deletedItemPosition = position
        toggleSelection(position)
        return true
    }

    //==================================================================================================================
    /**
     * Toggle Selection 4 Conversation List...
     */
    //==================================================================================================================
    private fun toggleSelection(position: Int) {
        mAdapter.toggleSelection(position)
        if (mAdapter.selectedItemCount > 0) {
            selectedDeleteMessage.visibility = View.VISIBLE
        } else
            selectedDeleteMessage.visibility = View.GONE


        /*activity?.runOnUiThread(Runnable {
            viewP.selectedDeleteMessage.text = "Delete (" + mAdapter.selectedItemCount + ")"
        })*/

    }

    //==================================================================================================================
    /**
     * Set Conversation List data 2 Adapter...
     */
    //==================================================================================================================
    override fun setChatListAdapter(chatList: List<Chat>) {
        mAdapter =
            ChatAdapter(
                applicationContext,
                chatList,
                this
            )
        messageRecylerView.adapter = mAdapter
    }

    //==================================================================================================================
    /**
     * Set User Search List Adapter 4 Start New conversation...
     */
    //==================================================================================================================
    override fun setUserSearchChatListAdapter(userSearchChatList: List<UserSearchChat>) {
        mAdapter4UserSearchChatList =
            UserSearchAdapter(
                applicationContext!!,
                userSearchChatList.toMutableList()
            ) {
                OnedioSingletonPattern.instance.setConversationStart(
                    false
                )
                OnedioSingletonPattern.instance.setUserNameAndSurname(
                    userSearchChatList[it].mName
                )
                OnedioSingletonPattern.instance.setAnotherUserId(
                    userSearchChatList[it].userID
                )
                OnedioSingletonPattern.instance.setConversationUserImageURL(
                    userSearchChatList[it].mImage
                )
                startActivity(
                    Intent(
                        applicationContext,
                        MessageActivityConversationViewImpl::class.java
                    )
                )

            }
        searchUserRecylerView.adapter = mAdapter4UserSearchChatList
    }

    //==================================================================================================================
    /**
     * Fill User All Conversation Data...
     */
    //==================================================================================================================
    fun fillUserAllConversationData(response4UserAllConversation: Response4UserAllConversation): List<Chat> {
        var data = ArrayList<Chat>()

        val conversationList =
            response4UserAllConversation.dataOfUserAllConversation?.conversationList

        for (i in 0 until response4UserAllConversation.dataOfUserAllConversation?.conversationList?.size!!) {

            conversationList!![i].deletedByKnowledge?.let {
                deletedConversationItemPositions.add(i)
                conversationDeletedDates.add(
                    DeletedConversationDataModel(
                        conversationList[i].target?.user?.userName!!,
                        conversationList[i].deletedByKnowledge?.deletedAt.toString(),
                        conversationList[i].deletedByKnowledge?.deletedByWho.toString()
                    )
                )
            } ?: run {


                if (conversationList[i].messaage?.size != 0)
                    addChatData(data, conversationList, i)

            }
        }

        OnedioSingletonPattern.instance.setConversationDeletedDate(conversationDeletedDates)

        for (j in 0 until deletedConversationItemPositions.size) {
            if (OnedioSingletonPattern.instance.getConversationDeletedDate()[j].meUserId == conversationList!![deletedConversationItemPositions[j]].me?.user?.id) {
                if (OnedioSingletonPattern.instance.getConversationDeletedDate()[j].deletedDate != "" && OnedioCommon.stringCompare(
                        conversationList[deletedConversationItemPositions[j]].messaage!![0].sendDate?.raw.toString(),
                        OnedioSingletonPattern.instance.getConversationDeletedDate()[j].deletedDate
                    )
                )
                    addChatData(data, conversationList, deletedConversationItemPositions[j])
            } else {
                addChatData(data, conversationList, deletedConversationItemPositions[j])
            }
        }

        return data.sortedWith(compareBy { it.updatedDate }).reversed()

    }

    //==================================================================================================================
    /**
     * Add Conversation Data...
     */
    //==================================================================================================================
    fun addChatData(
        arrOfConversationData: ArrayList<Chat>,
        conversationList: List<ConversationData>,
        position: Int
    ) {
        val conversationID = conversationList[position].id!!
        val userName = conversationList[position].target?.user?.userName!!
        val lastMessage = conversationList[position].messaage?.get(0)?.text!!
        val lastMessageTime = conversationList[position].messaage?.get(0)?.sendDate?.relative!!
        val avatar = conversationList[position].target?.user?.avatar!!
        val updatedDate = conversationList[position].updateDate?.raw!!
        val userId = conversationList[position].target?.user?.id!!

        arrOfConversationData.add(
            Chat(
                conversationID,
                userName,
                lastMessage,
                lastMessageTime,
                avatar,
                false,
                false,
                updatedDate,
                userId
            )
        )
    }

    //==================================================================================================================
    /**
     * Fill Search User Chat Dummy Data...
     */
    //==================================================================================================================
    fun fillSearchUserChatListData(): List<UserSearchChat> {
        val userSearchList = ArrayList<UserSearchChat>()

        userSearchList.add(
            UserSearchChat(
                "5d70dee9241e853727e89411",
                "fatih.kocak",
                "Fatih Koçak",
                "https://img-s1.onedio.com/id-5d71195d73e4d290370829ea/rev-0/w-500/h-500/devel/f-jpg/s-67714130f4dc8cdcf59d265b6bc1d289e77cf220.jpg"
            )
        )

        userSearchList.add(
            UserSearchChat(
                "5d358f1faea5f17816c6bf18",
                "fatihkocak12",
                "Fatih Koçak",
                "https://img-s2.onedio.com/id-5d753fa4961a8c5f17f2a341/rev-0/w-500/h-500/devel/f-jpg/s-b2c9a4c9b4083e6a714e528d5adc5f077fa57d4f.jpg"
            )
        )

        userSearchList.add(
            UserSearchChat(
                "5d5d4fd623fb7cb17e53faa7",
                "111root",
                "111root",
                "https://img-s2.onedio.com/id-5d5e783355d1703e5eb453ab/rev-0/w-500/h-500/devel/f-jpg/s-2e98679debf6dbba647a9a763d58cd072cdbfa82.jpg"
            )
        )

        return userSearchList

    }

    //==================================================================================================================
    /**
     * Show Loading...
     */
    //==================================================================================================================
    override fun showLoading() {
        messageAvlIndicatorProgress.playAnimation()
        messageAvlIndicatorProgress.visibility = View.VISIBLE
        /*window?.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )*/
    }

    //==================================================================================================================
    /**
     * Hide Loading
     */
    //==================================================================================================================
    override fun hideLoading() {
        messageAvlIndicatorProgress.cancelAnimation()
        messageAvlIndicatorProgress.visibility = View.GONE
        //window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    //==================================================================================================================
    /**
     * Show Error...
     */
    //==================================================================================================================
    override fun showError(response4ErrorObj: Response4ErrorObj) {
        showEzDialogMessage(
            "Hata..!",
            response4ErrorObj.status?.message!!,
            "Tamam",
            ContextCompat.getColor(
                applicationContext!!,
                R.color.tabIndicatorColor4Proile
            ),
            ContextCompat.getColor(applicationContext!!, R.color.constWhite),
            ContextCompat.getColor(applicationContext!!, R.color.constTextColor),
            ContextCompat.getColor(applicationContext!!, R.color.constTextColor)
        )
    }

    override fun showEzDialogMessage(
        titleText: String,
        messageText: String,
        buttonText: String,
        headerColor: Int,
        titleTextColor: Int,
        messageTextColor: Int,
        buttonTextColor: Int
    ) {
        val ezDialogMessage =
            OnedioEzDialogMessageModel4Activity(
                applicationContext,
                titleText,
                messageText,
                buttonText,
                headerColor,
                titleTextColor,
                messageTextColor,
                buttonTextColor
            )

        OnedioCommon.showEzDialog4Activity(ezDialogMessage) {}
    }

    //==================================================================================================================
    /**
     * Go Back...
     */
    //==================================================================================================================
    override fun goBack() {
        messageGoBack.setOnClickListener{
            /*OnedioSingletonPattern.instance.setTabIndex(0)*/
            startActivity(Intent(applicationContext, DashboardActivityViewImpl::class.java))
        }
    }

    //==================================================================================================================
    /**
     * Handle Hardware back button...
     */
    //==================================================================================================================
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        when (keyCode) {
            KeyEvent.KEYCODE_BACK -> {
                /*OnedioSingletonPattern.instance.setTabIndex(0)*/
                startActivity(Intent(applicationContext, DashboardActivityViewImpl::class.java))
            }
        }
        return super.onKeyDown(keyCode, event)
    }

}