package com.onedio.androidside.mvp.view.dashboard.homeFragment.messageActivity.conversation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayout
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.vanniktech.emoji.EmojiManager
import com.vanniktech.emoji.EmojiPopup
import com.vanniktech.emoji.ios.IosEmojiProvider
import kotlinx.android.synthetic.main.activity_conversation.*
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.common.OnedioSharedPrefs
import com.onedio.androidside.common.model.OnedioEzDialogMessageModel4Activity
import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.chatModel.conversationModel.ChatData
import com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.conversation.JWTObjPojoModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.conversation.MessageActivityConversationModelImpl
import com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.conversation.responseModel.MessagesData
import com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.conversation.responseModel.Response4ConversationIDMessage
import com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.conversation.responseModel.sendMessage.Response4SendMessage
import com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.conversation.responseModel.startConversation.Response4StartConversation
import com.onedio.androidside.mvp.presenter.dashboard.homeFragment.messagePresenter.conversation.MessageActivityConversationPresenterImpl
import com.onedio.androidside.mvp.view.dashboard.DashboardActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.messageActivity.MessageActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.messageActivity.conversation.adapter.ConversationRecyclerView
import com.onedio.androidside.mvp.view.dashboard.homeFragment.messageActivity.conversation.pageTransform.PageTransformer
import com.onedio.androidside.singleton.OnedioSingletonPattern
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class MessageActivityConversationViewImpl : AppCompatActivity(),
    IMessageActivityConversationView, SwipyRefreshLayout.OnRefreshListener {

    private lateinit var messageActivityConversationPresenterImpl: MessageActivityConversationPresenterImpl

    private lateinit var mAdapter: ConversationRecyclerView

    private lateinit var emojiPopup: EmojiPopup

    var modifiedMessagesData = ArrayList<ChatData>()
    var diffDay4LastMessageAndCurrentDate: Long = 0

    private lateinit var userId: String

    //==================================================================================================================
    /**
     * onCreate Method 4 Activity
     */
    //==================================================================================================================
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setEmojiProvider()

        setContentView(R.layout.activity_conversation)

        initConversationComponent()
        changeIconIfDarkModeOn()

        clickWritingMessageAreaListener()
        clickSendMessageListener()
        setUpEmojiPopup()

        emojiButtonClickListener()

        clickUserPhotoOrUserName()

        goBack()

    }

    //==================================================================================================================
    /**
     * Set Emoji Provider...
     */
    //==================================================================================================================
    override fun setEmojiProvider() {
        EmojiManager.install(IosEmojiProvider())
    }

    //==================================================================================================================
    /**
     * Init Conversation Component
     */
    //==================================================================================================================
    override fun initConversationComponent() {
        supportActionBar?.hide()

        setUserProfilePhoto()

        if (OnedioSingletonPattern.instance.isConversationStart()) {
            userNameMessage.text =
                OnedioSingletonPattern.instance.getConversationChatData().mName
            userId = OnedioSingletonPattern.instance.getConversationChatData().userId
        } else {
            userNameMessage.text = OnedioSingletonPattern.instance.getUserNameAndSurname()
            userId = OnedioSingletonPattern.instance.getAnotherUserId()
        }


        swipeRefresh.setOnRefreshListener(this)
        swipeRefresh.setColorSchemeColors(
            ContextCompat.getColor(applicationContext, R.color.whiteColor),
            ContextCompat.getColor(applicationContext, R.color.userFollowerCountColor)
        )

        messageActivityConversationPresenterImpl =
            MessageActivityConversationPresenterImpl(
                MessageActivityConversationModelImpl(),
                this
            )

        conversationRecyclerView.setHasFixedSize(true)
        conversationRecyclerView.layoutManager = LinearLayoutManager(this)

        if (OnedioSingletonPattern.instance.isConversationStart()) getConversationIDMessage()
        else startConversation()

    }

    //==================================================================================================================
    /**
     * Change Icon if Dark mode is on...
     */
    //==================================================================================================================
    override fun changeIconIfDarkModeOn() {
        if (OnedioSharedPrefs(applicationContext).isNightModeEnabled()) {
            goBack.setImageResource(R.drawable.ic_back_dark_mode)
        } else {
            goBack.setImageResource(R.drawable.ic_back)
        }
    }

    override fun setUserProfilePhoto() {
        if (OnedioSingletonPattern.instance.getConversationUserImageURL() != "emptyAvatar")
            Picasso.get().load(OnedioSingletonPattern.instance.getConversationUserImageURL())
                .into(conversationProfilePhoto, object : Callback {
                    override fun onSuccess() {
                        conversationPhotoProgressMessage.visibility = View.GONE
                    }

                    override fun onError(e: Exception?) {

                    }
                })
        else {
            conversationProfilePhoto.setImageResource(R.drawable.empty_avatar)
            conversationPhotoProgressMessage.visibility = View.GONE
        }
    }

    override fun clickUserPhotoOrUserName() {
        conversationProfilePhoto.setOnClickListener {
            OnedioSingletonPattern.instance.setIsUserOwnProfile(false)
            OnedioSingletonPattern.instance.setAnotherUserId(userId)
            OnedioSingletonPattern.instance.setAnotherUserName(userNameMessage.text.toString())

            /*OnedioSingletonPattern.instance.setTabIndex(4)*/
            startActivity(Intent(applicationContext, DashboardActivityViewImpl::class.java))

        }

        userNameMessage.setOnClickListener {
            OnedioSingletonPattern.instance.setIsUserOwnProfile(false)
            OnedioSingletonPattern.instance.setAnotherUserId(userId)
            OnedioSingletonPattern.instance.setAnotherUserName(userNameMessage.text.toString())

            /*OnedioSingletonPattern.instance.setTabIndex(4)*/
            startActivity(Intent(applicationContext, DashboardActivityViewImpl::class.java))

        }
    }

    //==================================================================================================================
    /**
     * Start Conversation
     */
    //==================================================================================================================
    override fun startConversation() {
        messageActivityConversationPresenterImpl.startConversation()
    }

    //==================================================================================================================
    /**
     * Handle Start Conversation Data...
     */
    //==================================================================================================================
    override fun handleStartConversationData(response4StartConversation: Response4StartConversation) {
        if (response4StartConversation.status?.code != 200)
            showEzDialogMessage(
                true,
                "Hata..!",
                "Mesajlaşma başlatırken bir sorun oluştu. Daha sonra tekrar deneyiniz..!",
                "Tamam",
                ContextCompat.getColor(
                    applicationContext,
                    R.color.tabIndicatorColor4Proile
                ),
                ContextCompat.getColor(applicationContext, R.color.constWhite),
                ContextCompat.getColor(applicationContext, R.color.constTextColor),
                ContextCompat.getColor(applicationContext, R.color.constTextColor)
            )
        /*EZDialog.Builder(this)
            .setTitle("Hata.!")
            .setMessage("Mesajlaşma başlatılırken bir sorun oluştu. Daha sonra tekrar deneyiniz.!")
            .setPositiveBtnText("Tamam")
            .setHeaderColor(
                ContextCompat.getColor(
                    applicationContext,
                    R.color.tabIndicatorColor4Proile
                )
            )
            .setTitleTextColor(ContextCompat.getColor(applicationContext, R.color.constWhite))
            .setMessageTextColor(
                ContextCompat.getColor(
                    applicationContext,
                    R.color.constTextColor
                )
            )
            .setButtonTextColor(
                ContextCompat.getColor(
                    applicationContext,
                    R.color.constTextColor
                )
            )
            .setCancelableOnTouchOutside(false)
            .OnPositiveClicked {
                OnedioSingletonPattern.instance.setTabIndex(3)
                startActivity(Intent(applicationContext, DashboardActivityViewImpl::class.java))
            }
            .build()*/

        OnedioSingletonPattern.instance.setConversationID(response4StartConversation.dataOfStartConversation?.convrsationID!!)
        getConversationIDMessage()
    }

    //==================================================================================================================
    /**
     * Get Conversation Data...
     */
    //==================================================================================================================
    override fun getConversationIDMessage() {
        if (OnedioSingletonPattern.instance.isConversationStart())
            messageActivityConversationPresenterImpl.getConversationIDMessages(
                OnedioSingletonPattern.instance.getConversationChatData().conversationID
            )
        else
            messageActivityConversationPresenterImpl.getConversationIDMessages(
                OnedioSingletonPattern.instance.getConversationID()
            )
    }

    //==================================================================================================================
    /**
     * Handle Conversation Data...
     */
    //==================================================================================================================
    override fun handleConversationIDMessages(response4ConversationIDMessage: Response4ConversationIDMessage) {

        setChatDataAdapter(addMessageDate2AllMessages(response4ConversationIDMessage))
    }

    //==================================================================================================================
    /**
     * Add Message Data 2 All Messages...
     */
    //==================================================================================================================
    override fun addMessageDate2AllMessages(response4ConversationIDMessage: Response4ConversationIDMessage): List<ChatData> {

        val listOfMessages =
            response4ConversationIDMessage.dataOfConversationIDMessage?.messages?.toMutableList()

        deleteIfMessageDateBeforeDeletedConversationDate(listOfMessages!!)

        if (listOfMessages?.size == 1) {
            val text = listOfMessages[0].textOfMessage!!
            val time =
                OnedioCommon.convertDate2Time(OnedioCommon.convertEpochTime2Date(listOfMessages[0].sendDate?.raw!!)) //conversations[i].sendDate?.relative!!
            val type = if (listOfMessages[0].direction.equals("out")) {
                "2"
            } else "1"

            val firstStringDate =
                OnedioCommon.convertEpochTime2Date(listOfMessages[0].sendDate?.raw!!)

            modifiedMessagesData.add(
                ChatData(
                    "0",
                    OnedioCommon.changeDateFormat2Text(firstStringDate),
                    ""
                )
            )
            modifiedMessagesData.add(
                ChatData(
                    type,
                    text,
                    time
                )
            )

        } else {
            for (i in 0 until listOfMessages.size) {

                val text = listOfMessages[i].textOfMessage!!
                val time =
                    OnedioCommon.convertDate2Time(OnedioCommon.convertEpochTime2Date(listOfMessages[i].sendDate?.raw!!)) //conversations[i].sendDate?.relative!!
                val type = if (listOfMessages[i].direction.equals("out")) {
                    "2"
                } else "1"

                for (j in i + 1 until listOfMessages.size) {

                    val anotherText = listOfMessages[j].textOfMessage!!
                    val anotherTime =
                        OnedioCommon.convertDate2Time(
                            OnedioCommon.convertEpochTime2Date(
                                listOfMessages[j].sendDate?.raw!!
                            )
                        )
                    val anotherType = if (listOfMessages[j].direction.equals("out")) "2" else "1"


                    val firstStringDate =
                        OnedioCommon.convertEpochTime2Date(listOfMessages[i].sendDate?.raw!!)
                    val secondStringDate =
                        OnedioCommon.convertEpochTime2Date(listOfMessages[j].sendDate?.raw!!)

                    val diffInDays = OnedioCommon.getDiffTwoDate(secondStringDate, firstStringDate)

                    if (modifiedMessagesData.size == 0) {
                        modifiedMessagesData.add(
                            ChatData(
                                "0",
                                OnedioCommon.changeDateFormat2Text(
                                    firstStringDate
                                ),
                                ""
                            )
                        )
                        modifiedMessagesData.add(
                            ChatData(
                                type,
                                text,
                                time
                            )
                        )
                    }

                    if (diffInDays >= 1) {
                        modifiedMessagesData.add(
                            ChatData(
                                "0",
                                OnedioCommon.changeDateFormat2Text(
                                    secondStringDate
                                ),
                                ""
                            )
                        )
                        modifiedMessagesData.add(
                            ChatData(
                                anotherType,
                                anotherText,
                                anotherTime
                            )
                        )
                    } else
                        modifiedMessagesData.add(
                            ChatData(
                                anotherType,
                                anotherText,
                                anotherTime
                            )
                        )


                    break

                }
            }
        }

        return modifiedMessagesData
    }

    //==================================================================================================================
    /**
     * Delete If Message Date before Deleted Conversation Date...
     */
    //==================================================================================================================
    private fun deleteIfMessageDateBeforeDeletedConversationDate(listOfMessages: MutableList<MessagesData>) {
        val userName = userNameMessage.text.toString()

        /*val meUserId = Gson().fromJson(
            OnedioJWTDecrypted.decoded(OnedioSingletonPattern.instance.getAccessToken()),
            JWTObjPojoModel::class.java
        )*/

        /*for (j in 0 until OnedioSingletonPattern.instance.getConversationDeletedDate().size) {
            if (OnedioSingletonPattern.instance.getConversationDeletedDate()[j].userName == userName && OnedioSingletonPattern.instance.getConversationDeletedDate()[j].meUserId == meUserId.uid) {
                for (i in listOfMessages.size - 1 downTo 0) {
                    if (OnedioCommon.stringCompare(
                            OnedioSingletonPattern.instance.getConversationDeletedDate()[j].deletedDate,
                            listOfMessages[i].sendDate?.raw!!
                        )
                    ) {
                        for (k in i downTo 0) {
                            listOfMessages.removeAt(k)
                        }
                        break
                    }
                }
                break
            }
        }*/
    }

    //==================================================================================================================
    /**
     * Set Chat Data Adapter...
     */
    //==================================================================================================================
    override fun setChatDataAdapter(chatData: List<ChatData>) {
        mAdapter =
            ConversationRecyclerView(
                this,
                chatData.toMutableList()
            ) {
                //Toast.makeText(applicationContext, setData()[it].text, Toast.LENGTH_SHORT).show()
            }
        conversationRecyclerView.adapter = mAdapter

        //conversationRecyclerView.smoothScrollToPosition(conversationRecyclerView.adapter?.itemCount!! - 1)
        (conversationRecyclerView.layoutManager as LinearLayoutManager).stackFromEnd = true
    }

    //==================================================================================================================
    /**
     * Emoj, Button Click Listener...
     */
    //==================================================================================================================
    override fun emojiButtonClickListener() {
        emojiButton.setOnClickListener { emojiPopup.toggle() }
    }

    //==================================================================================================================
    /**
     * Click Writing Message Area Listener...
     */
    //==================================================================================================================
    override fun clickWritingMessageAreaListener() {
        writingMessageArea.setOnTouchListener { _, _ ->
            conversationRecyclerView.postDelayed({
                //conversationRecyclerView.smoothScrollToPosition(conversationRecyclerView.adapter?.itemCount!! - 1)
                (conversationRecyclerView.layoutManager as LinearLayoutManager).stackFromEnd = true
            }, 200)
            false
        }
    }

    //==================================================================================================================
    /**
     * Click Send Message Listener...
     */
    //==================================================================================================================
    override fun clickSendMessageListener() {
        sendMessage.setOnClickListener {

            calculateLastmessageDate2CurrentDate()

            if (writingMessageArea.text.toString() != "") {
                if (OnedioSingletonPattern.instance.isConversationStart()) messageActivityConversationPresenterImpl.sendMessage(
                    writingMessageArea.text.toString(),
                    OnedioSingletonPattern.instance.getConversationChatData().conversationID
                )
                else
                    messageActivityConversationPresenterImpl.sendMessage(
                        writingMessageArea.text.toString(),
                        OnedioSingletonPattern.instance.getConversationID()
                    )
            }
        }
    }

    //==================================================================================================================
    /**
     * Calculate Last Message Date 2 Current Date...
     */
    //==================================================================================================================
    private fun calculateLastmessageDate2CurrentDate(): Long {
        var lastChatDateText = ""
        if (modifiedMessagesData.size != 0) {
            for (i in modifiedMessagesData.size - 1 downTo 0) {
                if (modifiedMessagesData[i].type == "0") {
                    lastChatDateText = modifiedMessagesData[i].text
                    break
                }
            }
        } else {
            diffDay4LastMessageAndCurrentDate = 2
        }


        if (lastChatDateText != "") {
            val lastChatTextNumber = OnedioCommon.changeDateFormatFromText(lastChatDateText)
            val currentDate = OnedioCommon.getCurrentDate()

            diffDay4LastMessageAndCurrentDate =
                OnedioCommon.getDiffTwoDate(lastChatTextNumber, currentDate)
            return diffDay4LastMessageAndCurrentDate

        }

        return 0
    }

    //==================================================================================================================
    /**
     * Handle Send Message Data...
     */
    //==================================================================================================================
    @SuppressLint("SimpleDateFormat")
    override fun handleSendMessageData(response4SendMessage: Response4SendMessage) {

        if (response4SendMessage.status?.code == 200) {
            if (response4SendMessage.dataOfSendMessage?.success!!) {
                val data = ArrayList<ChatData>()

                if (diffDay4LastMessageAndCurrentDate >= 1)
                    data.add(
                        ChatData(
                            "0",
                            OnedioCommon.changeDateFormat2Text(OnedioCommon.getCurrentDate()),
                            ""
                        )
                    )

                data.add(
                    ChatData(
                        "2",
                        writingMessageArea.text.toString(),
                        SimpleDateFormat("HH:mm").format(Date())
                    )
                )
                mAdapter.addItem(data)
                conversationRecyclerView.smoothScrollToPosition(conversationRecyclerView.adapter?.itemCount!! - 1)
                writingMessageArea.setText("")
            } else
                showEzDialogMessage(
                    false,
                    "Hata..!",
                    "Bir sorun oluştu..!",
                    "Tamam",
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.tabIndicatorColor4Proile
                    ),
                    ContextCompat.getColor(applicationContext, R.color.constWhite),
                    ContextCompat.getColor(applicationContext, R.color.constTextColor),
                    ContextCompat.getColor(applicationContext, R.color.constTextColor)
                )
            /*EZDialog.Builder(this)
                .setTitle("Hata.!")
                .setMessage("Bir Sorun oluştu..")
                .setPositiveBtnText("Tamam")
                .setHeaderColor(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.tabIndicatorColor4Proile
                    )
                )
                .setTitleTextColor(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.constWhite
                    )
                )
                .setMessageTextColor(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.constTextColor
                    )
                )
                .setButtonTextColor(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.constTextColor
                    )
                )
                .setCancelableOnTouchOutside(false)
                .OnPositiveClicked {

                }
                .build()*/
        } else
            showEzDialogMessage(
                false,
                "Hata..!",
                response4SendMessage.status?.message!!,
                "Tamam",
                ContextCompat.getColor(
                    applicationContext,
                    R.color.tabIndicatorColor4Proile
                ),
                ContextCompat.getColor(applicationContext, R.color.constWhite),
                ContextCompat.getColor(applicationContext, R.color.constTextColor),
                ContextCompat.getColor(applicationContext, R.color.constTextColor)
            )
        /*EZDialog.Builder(this)
            .setTitle("Hata.!")
            .setMessage(response4SendMessage.status?.message)
            .setPositiveBtnText("Tamam")
            .setHeaderColor(
                ContextCompat.getColor(
                    applicationContext,
                    R.color.tabIndicatorColor4Proile
                )
            )
            .setTitleTextColor(ContextCompat.getColor(applicationContext, R.color.constWhite))
            .setMessageTextColor(
                ContextCompat.getColor(
                    applicationContext,
                    R.color.constTextColor
                )
            )
            .setButtonTextColor(
                ContextCompat.getColor(
                    applicationContext,
                    R.color.constTextColor
                )
            )
            .setCancelableOnTouchOutside(false)
            .OnPositiveClicked {

            }
            .build()*/


    }

    //==================================================================================================================
    /**
     * Swipe Refrefs for conversation messages...
     */
    //==================================================================================================================
    override fun onRefresh(direction: SwipyRefreshLayoutDirection?) {
        modifiedMessagesData = ArrayList()

        getConversationIDMessage()

        swipeRefresh.isRefreshing = false
    }

    //==================================================================================================================
    /**
     * Setup Emoji Popup...
     */
    //==================================================================================================================
    override fun setUpEmojiPopup() {
        if (OnedioSharedPrefs(applicationContext).isNightModeEnabled()) {
            emojiButton.setImageDrawable(resources.getDrawable(R.drawable.ic_emoji_darkmode))

            emojiPopup = EmojiPopup.Builder.fromRootView(rootConstraintLayout)
                .setOnEmojiBackspaceClickListener { }
                .setOnEmojiClickListener { _, _ -> }
                .setOnEmojiPopupShownListener { emojiButton.setImageResource(R.drawable.ic_keyboard_dark_mode) }
                .setOnSoftKeyboardOpenListener { }
                .setOnEmojiPopupDismissListener { emojiButton.setImageResource(R.drawable.ic_emoji_darkmode) }
                .setOnSoftKeyboardCloseListener { }
                .setKeyboardAnimationStyle(R.style.emoji_fade_animation_style)
                .setPageTransformer(PageTransformer())
                .build(writingMessageArea)
        } else {
            emojiButton.setImageDrawable(resources.getDrawable(R.drawable.ic_emoji))

            emojiPopup = EmojiPopup.Builder.fromRootView(rootConstraintLayout)
                .setOnEmojiBackspaceClickListener { }
                .setOnEmojiClickListener { _, _ -> }
                .setOnEmojiPopupShownListener { emojiButton.setImageResource(R.drawable.ic_keyboard) }
                .setOnSoftKeyboardOpenListener { }
                .setOnEmojiPopupDismissListener { emojiButton.setImageResource(R.drawable.ic_emoji) }
                .setOnSoftKeyboardCloseListener { }
                .setKeyboardAnimationStyle(R.style.emoji_fade_animation_style)
                .setPageTransformer(PageTransformer())
                .build(writingMessageArea)
        }
    }

    //==================================================================================================================
    /**
     * Show Loading
     */
    //==================================================================================================================
    override fun showLoading() {
        messageConversationAvlIndicatorProgress.playAnimation()
        messageConversationAvlIndicatorProgress.visibility = View.VISIBLE
        /*window?.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )*/
    }

    //==================================================================================================================
    /**
     * Hide Loading...
     */
    //==================================================================================================================
    override fun hideLoading() {
        messageConversationAvlIndicatorProgress.cancelAnimation()
        messageConversationAvlIndicatorProgress.visibility = View.GONE
        //window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    //==================================================================================================================
    /**
     * Show Error Message...
     */
    //==================================================================================================================
    override fun showError(response4ErrorObj: Response4ErrorObj) {
        showEzDialogMessage(
            false,
            "Hata..!",
            response4ErrorObj.status?.message!!,
            "Tamam",
            ContextCompat.getColor(
                applicationContext,
                R.color.tabIndicatorColor4Proile
            ),
            ContextCompat.getColor(applicationContext, R.color.constWhite),
            ContextCompat.getColor(applicationContext, R.color.constTextColor),
            ContextCompat.getColor(applicationContext, R.color.constTextColor)
        )
        /*EZDialog.Builder(this)
            .setTitle("Hata.!")
            .setMessage(response4ErrorObj.status?.message)
            .setPositiveBtnText("Tamam")
            .setHeaderColor(
                ContextCompat.getColor(
                    applicationContext,
                    R.color.tabIndicatorColor4Proile
                )
            )
            .setTitleTextColor(ContextCompat.getColor(applicationContext, R.color.constWhite))
            .setMessageTextColor(ContextCompat.getColor(applicationContext, R.color.constTextColor))
            .setButtonTextColor(ContextCompat.getColor(applicationContext, R.color.constTextColor))
            .setCancelableOnTouchOutside(false)
            .OnPositiveClicked {

            }
            .build()*/
    }

    //==================================================================================================================
    /**
     * Go Back...
     */
    //==================================================================================================================
    override fun goBack() {
        goBack.setOnClickListener() {
            startActivity(Intent(applicationContext, MessageActivityViewImpl::class.java))
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
                startActivity(Intent(applicationContext, MessageActivityViewImpl::class.java))
            }
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun showEzDialogMessage(
        isCallbackTrigger: Boolean,
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
                this,
                titleText,
                messageText,
                buttonText,
                headerColor,
                titleTextColor,
                messageTextColor,
                buttonTextColor
            )

        OnedioCommon.showEzDialog4Activity(ezDialogMessage) {
            when (isCallbackTrigger) {
                true -> goDashboardActivity()
            }
        }
    }

    override fun goDashboardActivity() {
        startActivity(Intent(applicationContext, MessageActivityViewImpl::class.java))
    }


}