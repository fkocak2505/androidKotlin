package com.onedio.androidside.mvp.view.dashboard.profileFragment.generalSetting.securityPolicy.userBlocked

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_user_blocked_list.*
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.mvp.model.noDesigned.blockedUser.UserBlockedListActivityModelImpl
import com.onedio.androidside.mvp.model.noDesigned.blockedUser.responseModel.BlockedUser
import com.onedio.androidside.mvp.model.noDesigned.blockedUser.responseModel.Response4UserBlockedList
import com.onedio.androidside.mvp.presenter.notDesigned.userBlocked.UserBlockedListActivityPresenterImpl
import com.onedio.androidside.mvp.view.dashboard.profileFragment.generalSetting.securityPolicy.SecurityAndPolicyActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.profileFragment.generalSetting.securityPolicy.userBlocked.blockedUserAdapter.ProfileUserBlockedRecylerAdapter2
import kotlinx.android.synthetic.main.activity_user_blocked_list.goBack
import com.onedio.androidside.common.OnedioSharedPrefs
import com.onedio.androidside.common.model.OnedioEzDialogMessageModel4Activity


class UserBlockListActivityViewImpl : AppCompatActivity(),
    IUserBlockedListActivityView {

    private lateinit var userBlockedListActivityPresenterImpl: UserBlockedListActivityPresenterImpl

    var mListOfBlockedList: MutableList<BlockedUser> = mutableListOf()


    //==================================================================================================================
    /**
     * onCreate Override Method...
     */
    //==================================================================================================================
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_blocked_list)

        initUserBlockedActivityComponent()
        changeIconIfDarkModeOn()

        goBack()

        changeComponentTypeFace()
    }

    //==================================================================================================================
    /**
     * Init User Blocked Activity Component...
     */
    //==================================================================================================================
    override fun initUserBlockedActivityComponent() {
        supportActionBar?.hide()

        userBlockedListActivityPresenterImpl =
            UserBlockedListActivityPresenterImpl(
                UserBlockedListActivityModelImpl(),
                this
            )

        getUserBlockedListData()
    }

    //==================================================================================================================
    /**
     * Change icon if dark mode on...
     */
    //==================================================================================================================
    override fun changeIconIfDarkModeOn() {
        if (OnedioSharedPrefs(applicationContext).isNightModeEnabled()) {
            goBack.setImageResource(R.drawable.ic_back_dark_mode)
        } else {
            goBack.setImageResource(R.drawable.ic_back)
        }
    }

    //==================================================================================================================
    /**
     * Change Component Typeface...
     */
    //==================================================================================================================
    override fun changeComponentTypeFace() {
        toolbarTitleBlockedUser.typeface = OnedioCommon.changeTypeFace4Activity(applicationContext)
        txtOfNoData.typeface = OnedioCommon.changeTypeFace4Activity(applicationContext)
    }

    //==================================================================================================================
    /**
     * Get User Blocked List Data...
     */
    //==================================================================================================================
    override fun getUserBlockedListData() {
        userBlockedListActivityPresenterImpl.getUserBlockedList()
    }

    //==================================================================================================================
    /**
     * Handle User Blocked List Data...
     */
    //==================================================================================================================
    override fun handleUserBlockedListData(response4UserBlockedList: Response4UserBlockedList) {
        if (response4UserBlockedList.status?.code == 200) {
            if (response4UserBlockedList.data!!.isNotEmpty()) {
                recylerUserBlocked.visibility = View.VISIBLE
                noUserBlockedConstraintLayout.visibility = View.INVISIBLE

                propRecylerViewComponent(response4UserBlockedList.data!!)
            } else {
                recylerUserBlocked.visibility = View.INVISIBLE
                noUserBlockedConstraintLayout.visibility = View.VISIBLE

                txtOfNoData.text = "Kullanıcının engellediği \nkişi bulunmamaktadır."


            }
        } else {
            showEzDialogMessage(
                "Hata..!",
                "Şu an da bir hata mevcut. Daha sonra tekrar deneyiniz..!",
                "Tamam",
                ContextCompat.getColor(
                    applicationContext,
                    R.color.tabIndicatorColor4Proile
                ),
                ContextCompat.getColor(applicationContext, R.color.constWhite),
                ContextCompat.getColor(applicationContext, R.color.constTextColor),
                ContextCompat.getColor(applicationContext, R.color.constTextColor)
            )

            /*EZDialog.Builder(this@UserBlockListActivityViewImpl)
                .setTitle("Hata..!")
                .setMessage("Şu an da bir hata mevcut. Daha sonra tekrar deneyiniz")
                .setPositiveBtnText("Tamam")
                .setHeaderColor(resources.getColor(R.color.tabIndicatorColor4Proile))
                .setTitleTextColor(resources.getColor(R.color.constWhite))
                .setMessageTextColor(resources.getColor(R.color.constTextColor))
                .setButtonTextColor(resources.getColor(R.color.constTextColor))
                .setCancelableOnTouchOutside(false)
                .OnPositiveClicked {
                    startActivity(Intent(applicationContext, SecurityAndPolicyActivityViewImpl::class.java))
                }
                .build()*/
        }
    }

    //==================================================================================================================
    /**
     * Prop RecylerView Component...
     */
    //==================================================================================================================
    @SuppressLint("WrongConstant")
    override fun propRecylerViewComponent(listOfBlockedList: List<BlockedUser>) {
        recylerUserBlocked.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayout.VERTICAL, false)

        recylerUserBlocked.adapter =
            ProfileUserBlockedRecylerAdapter2(
                applicationContext!!,
                getBlockedList(listOfBlockedList.toMutableList())
            ) {
                Toast.makeText(
                    applicationContext,
                    getBlockedList(listOfBlockedList.toMutableList()).get(it).name,
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
    }

    //==================================================================================================================
    /**
     * Get Blocked List...
     */
    //==================================================================================================================
    fun getBlockedList(listOfBlockedList: MutableList<BlockedUser>): MutableList<BlockedUser> {
        mListOfBlockedList = mutableListOf()

        for (itemOfBlockedUser in listOfBlockedList) {
            mListOfBlockedList.add(
                BlockedUser(
                    itemOfBlockedUser.username!!,
                    itemOfBlockedUser.name!!,
                    itemOfBlockedUser.avatar!!
                )
            )
        }

        return mListOfBlockedList

    }

    //==================================================================================================================
    /**
     * Show Loading...
     */
    //==================================================================================================================
    override fun showLoading() {
        avlIndicatorProgressUserBlocked.playAnimation()
        avlIndicatorProgressUserBlocked.visibility = View.VISIBLE
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
        avlIndicatorProgressUserBlocked.cancelAnimation()
        avlIndicatorProgressUserBlocked.visibility = View.GONE
        //window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    //==================================================================================================================
    /**
     * Go Back...
     */
    //==================================================================================================================
    fun goBack() {
        goBack.setOnClickListener() {
            startActivity(
                Intent(
                    this@UserBlockListActivityViewImpl,
                    SecurityAndPolicyActivityViewImpl::class.java
                )
            )
        }
    }

    //==================================================================================================================
    /**
     * Handle Keyboard Back Button...
     */
    //==================================================================================================================
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        when (keyCode) {
            KeyEvent.KEYCODE_BACK -> startActivity(
                Intent(
                    this@UserBlockListActivityViewImpl,
                    SecurityAndPolicyActivityViewImpl::class.java
                )
            )
        }
        return super.onKeyDown(keyCode, event);
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
                this,
                titleText,
                messageText,
                buttonText,
                headerColor,
                titleTextColor,
                messageTextColor,
                buttonTextColor
            )

        OnedioCommon.showEzDialog4Activity(ezDialogMessage) { goSecurityActivityView() }
    }

    override fun goSecurityActivityView() {
        startActivity(Intent(applicationContext, SecurityAndPolicyActivityViewImpl::class.java))
    }
}