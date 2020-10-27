package com.weatherlogger.customvideoview

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.setDisplayUseLogoEnabled(false)
        supportActionBar?.title = "Video Deneme"

        setContentView(R.layout.activity_main)


        jzVideo.setUp(
            "https://video-s3.onedio.com/5b57326d872c84c812880dde/hls.m3u8",
            "Fatih Koçak",
            JzvdStd.SCREEN_NORMAL
        )
        Glide.with(this)
            .load("https://img-s1.onedio.com/id-5b57330b872c84c812880de3/rev-0/w-585/h-351/f-jpg/s-03db236191f8e9d47c67c629de96fc44788273da.jpg")
            .into(jzVideo.thumbImageView)

        //jzVideo.startVideo()

    }

    override fun onResume() {
        super.onResume()
        Jzvd.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        Jzvd.NORMAL_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    /*override fun onBackPressed() {
        if (Jzvd.backPress()) {
            return
        }
        super.onBackPressed()
    }

    override fun onPause() {
        super.onPause()
        Jzvd.releaseAllVideos()

        //Change these two variables back
        Jzvd.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
        Jzvd.NORMAL_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }*/
}
