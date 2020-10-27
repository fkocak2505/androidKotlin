package com.example.svgurlloader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.internal.Util

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val svgUrl = "https://static.onedio.com/badges/530bd809764e7634c69c39c9.svg"
        Utils.fetchSvg(this, svgUrl, svgImageView)
    }
}
