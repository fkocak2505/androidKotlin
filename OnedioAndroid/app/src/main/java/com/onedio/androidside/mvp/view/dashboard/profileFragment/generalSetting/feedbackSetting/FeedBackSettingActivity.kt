package com.onedio.androidside.mvp.view.dashboard.profileFragment.generalSetting.feedbackSetting

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.profil.Response4UsersProfile
import com.onedio.androidside.mvp.model.feedBack.FeedBackSettingActivityModelImpl
import com.onedio.androidside.mvp.model.feedBack.requestParam.FeedBackRequestParam
import com.onedio.androidside.mvp.presenter.dashboard.feedBack.FeedBackSettingActivityPresenterImpl
import com.onedio.androidside.prefs.StringSharedPrefs
import kotlinx.android.synthetic.main.activity_feedback.*


class FeedBackSettingActivity : AppCompatActivity(), IFeedBackSetting {

    private lateinit var toolBar: Toolbar

    private var theme: String = ""

    private var response4UsersProfile: String = ""

    private lateinit var feedBackSettingActivityPresenterImpl: FeedBackSettingActivityPresenterImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)

        val sharedPref =
            getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        theme = sharedPref.getString("mode", "default")!!

        feedBackSettingActivityPresenterImpl = FeedBackSettingActivityPresenterImpl(FeedBackSettingActivityModelImpl(), this)

        response4UsersProfile = StringSharedPrefs(
            sharedPref,
            OnedioConstant.SHARED_PREF_USER_PROFILE_INFO,
            ""
        ).getValue()

        initFeedBackActivityComponent()

        prepareToolbar()

        clickSendFeedback()

        if (response4UsersProfile != "")
            fillEmailDataIfExist(response4UsersProfile)
    }

    private fun prepareToolbar() {
        toolBar = toolBar4FeedBack as Toolbar

        toolBar.title = "Geri Bildirim"
        toolBar.setTitleTextAppearance(this, R.style.MuliSemiboldTextAppearance)

        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)


        toolBar.setOnClickListener {

        }

        if (theme == "dark") {
            toolBar.setTitleTextColor(Color.parseColor("#FFFFFF"))
            toolBar.setNavigationIcon(R.drawable.ic_back_dark_mode)
        } else {
            toolBar.setTitleTextColor(Color.parseColor("#191919"))
            toolBar.setNavigationIcon(R.drawable.ic_back)
        }

    }

    private fun initFeedBackActivityComponent() {
        supportActionBar?.hide()

        if (theme == "dark") {
            txtInfoEPosta.background = ContextCompat.getDrawable(
                applicationContext!!,
                R.drawable.custom_edittext_dark_mode
            )
            textInputLayoutFeedback.background = ContextCompat.getDrawable(
                applicationContext!!,
                R.drawable.custom_edittext_dark_mode
            )
        } else {
            txtInfoEPosta.background =
                ContextCompat.getDrawable(applicationContext!!, R.drawable.custom_edittext)
            textInputLayoutFeedback.background =
                ContextCompat.getDrawable(applicationContext!!, R.drawable.custom_edittext)
        }

    }

    private fun fillEmailDataIfExist(response4UsersProfile: String) {

        val response4UsersProfileObj =
            Gson().fromJson(response4UsersProfile, Response4UsersProfile::class.java)

        edtOfEposta.setText(response4UsersProfileObj?.data?.email)
    }

    private fun clickSendFeedback() {
        sendFeedBack.setOnClickListener {
            val email = edtOfEposta.text.toString()
            val message = edtOfFeedBack.text.toString()
            if (email.trim() != "") {
                if (OnedioCommon.isEmailValid(email)) {
                    if (message.trim() != "") {
                        val requestParam = getFeedBackInfo()
                        feedBackSettingActivityPresenterImpl.sendFeedBack(requestParam)
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "Lütfen mesaj yazınız..",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Geçerli bir email giriniz..",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(applicationContext, "Email boş bırakılamaz", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun getFeedBackInfo(): FeedBackRequestParam {
        val feedBackRequestParam = FeedBackRequestParam()
        feedBackRequestParam.email = edtOfEposta.text.toString()
        feedBackRequestParam.message = edtOfFeedBack.text.toString()
        feedBackRequestParam.deviceOs = "Android"
        feedBackRequestParam.deviceModel = Build.MODEL
        feedBackRequestParam.appVersion = applicationContext.packageManager.getPackageInfo(packageName, 0).versionName
        feedBackRequestParam.osVersion = "Android ${Build.VERSION.RELEASE}"

        return feedBackRequestParam
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun handleFeedBackResponse(response4SendFeedback: String) {
        Toast.makeText(applicationContext, "Geri bildiriminiz başarıyla gönderildi.", Toast.LENGTH_SHORT).show()
        onBackPressed()
    }

    override fun showLoading() {
        avlIndicatorProgress4FeedBack.playAnimation()
        avlIndicatorProgress4FeedBack.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        avlIndicatorProgress4FeedBack.cancelAnimation()
        avlIndicatorProgress4FeedBack.visibility = View.GONE
    }

    override fun showError(response4ErrorObj: Response4ErrorObj) {
        Toast.makeText(applicationContext, response4ErrorObj.status?.message, Toast.LENGTH_SHORT)
            .show()
    }
}