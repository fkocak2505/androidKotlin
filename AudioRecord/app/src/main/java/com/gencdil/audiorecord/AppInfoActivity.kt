package com.gencdil.audiorecord

import android.graphics.Typeface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_info.*

class AppInfoActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        supportActionBar?.hide()

        //aaa.typeface = Typeface.createFromAsset(applicationContext?.assets, "muli-regular.ttf")
        info.typeface = Typeface.createFromAsset(applicationContext?.assets, "muli-regular.ttf")
    }
}