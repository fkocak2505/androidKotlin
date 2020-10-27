package com.weatherlogger.sharedprefinsideapplicatonclass

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val a = CustomAppClass.instance?.applicationContext?.getSharedPreferences("aaa", Context.MODE_PRIVATE)

        aaaaa.text = "awf"
    }
}
