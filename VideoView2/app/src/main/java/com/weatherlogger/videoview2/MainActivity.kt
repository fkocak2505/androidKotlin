package com.weatherlogger.videoview2

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.MediaController
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val mediacontroller = MediaController(this)
        mediacontroller.setAnchorView(videoView)


        videoView.setMediaController(mediacontroller)
        videoView.setVideoPath("https://onedio.com/api/v2/video/files/5eb02e31dc6571d25519b6b7.mp4")
        videoView.requestFocus()

        videoView.setOnPreparedListener { mp ->
            mp.setOnVideoSizeChangedListener { _, _, _ ->
                videoView.setMediaController(mediacontroller)
                mediacontroller.setAnchorView(videoView)
            }
        }

        videoView.setOnCompletionListener { mp ->
            Toast.makeText(applicationContext, "Video over", Toast.LENGTH_SHORT).show()

            mp.release()
            videoView.setVideoPath("https://onedio.com/api/v2/video/files/5eb02e31dc6571d25519b6b7.mp4")
            videoView.start()

        }

        videoView.setOnErrorListener { mp, what, extra ->
            Log.d("API123", "What $what extra $extra")
            false
        }

    }
}
