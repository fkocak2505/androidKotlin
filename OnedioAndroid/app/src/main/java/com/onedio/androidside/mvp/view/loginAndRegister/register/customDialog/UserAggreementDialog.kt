package com.onedio.androidside.mvp.view.loginAndRegister.register.customDialog

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.common.OnedioSharedPrefs
import com.onedio.androidside.constant.OnedioConstant


class UserAggreementDialog(var activity: Activity) : Dialog(activity), View.OnClickListener {
    lateinit var checkBoxUserAggreement: TextView
    lateinit var userAggreementWebView: WebView
    lateinit var onedioLogoUserAggreement: ImageView

    private var theme: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_user_aggreement)

        val sharedPref = activity.applicationContext?.getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        theme = sharedPref?.getString("mode", "default")!!

        userAggreementWebView = findViewById(R.id.userAggreementWebView)
        checkBoxUserAggreement = findViewById(R.id.checkBoxUserAggreement)
        onedioLogoUserAggreement = findViewById(R.id.onedioLogoUserAggrement)


        checkDarkMode()
        changeComponentTextFonts()


        checkBoxUserAggreement.setOnClickListener(this)

    }

    fun checkDarkMode() {
        if (theme == "dark") {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            userAggreementWebView.loadUrl("https://static.onedio.com/html/kullanici-night.html")
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            userAggreementWebView.loadUrl("https://static.onedio.com/html/kullanici-day.html")
        }
    }

    fun changeComponentTextFonts() {
        checkBoxUserAggreement.typeface =
            OnedioCommon.changeTypeFace4Activity(activity.applicationContext!!)
    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.checkBoxUserAggreement -> dismiss()
            else -> {
            }
        }
        dismiss()
    }
}