package com.onedio.androidside.mvp.view.dashboard.profileFragment.generalSetting.userAgreement

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.onedio.androidside.R
import com.onedio.androidside.constant.OnedioConstant
import kotlinx.android.synthetic.main.activity_profile_user_agreement.*

class UserAgreementActivityViewImpl : AppCompatActivity(),
    IUserAgreementActivityView {

    private lateinit var toolBar: Toolbar

    private var theme: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_profile_user_agreement)

        val sharedPref =
            getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        theme = sharedPref.getString("mode", "default")!!

        initUserAgreementActivityComponent()

        prepareToolbar()

    }

    private fun prepareToolbar() {
        toolBar = toolBar4UserAgreement as Toolbar

        toolBar.title = resources.getString(R.string.userAggreementAndPolicyTitle)
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

    override fun initUserAgreementActivityComponent() {
        supportActionBar?.hide()

        if (theme == "dark") {
            userAggreementWebView.loadUrl("https://static.onedio.com/html/kullanici-night.html")
        } else {
            userAggreementWebView.loadUrl("https://static.onedio.com/html/kullanici-day.html")
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}