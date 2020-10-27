package com.example.videoview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cn.jzvd.JzvdStd
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        video_player.setUp(
            "http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4",
            "Video title",
            JzvdStd.SCREEN_WINDOW_NORMAL
        )

        Glide.with(this)
            .load("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640")
            .into(video_player.thumbImageView)

    }
}
