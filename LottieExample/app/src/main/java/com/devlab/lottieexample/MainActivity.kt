package com.devlab.lottieexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonDeneme.setOnClickListener {
            lottieProgress.playAnimation()
        }

        buttonDeneme1.setOnClickListener {
            lottieProgress.cancelAnimation()
            lottieProgress.visibility = View.GONE
        }
    }
}