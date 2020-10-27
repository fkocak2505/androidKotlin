package com.weatherlogger.tabooladevlab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.taboola.android.PublisherInfo
import com.taboola.android.Taboola
import com.taboola.android.utils.SdkDetailsHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val publisherInfo = PublisherInfo("onedio-app-android")
        Taboola.init(publisherInfo)

        taboolaWidget.layoutParams.height = SdkDetailsHelper.getDisplayHeight(applicationContext) * 2
        taboolaWidget.setInterceptScroll(true)
        taboolaWidget.fetchContent()
    }
}
