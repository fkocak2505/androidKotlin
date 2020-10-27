package com.weatherlogger.videoviewplayerjioza

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        jz_video.setUp(
            "https://onedio.com/api/v2/video/files/5eb02e31dc6571d25519b6b7.mp4", "饺子快长大"
        )


        /*Glide.with(this)
            .load("http://jzvd-pic.nathen.cn/jzvd-pic/1bb2ebbe-140d-4e2e-abd2-9e7e564f71ac.png")
            .into<Target<Drawable>>(jz_video.thumbImageView)*/

    }
}
