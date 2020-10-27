package com.weatherlogger.viewflipperdevlab

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        itemOfButton.text = (0..100).random().toString()

        /*viewFlipper.flipInterval = 2000
        viewFlipper.startFlipping()*/
    }

    fun previousView(v: View) {
        viewFlipper.setInAnimation(this, R.anim.slide_in_right)
        viewFlipper.setOutAnimation(this, R.anim.slide_in_left)
        viewFlipper.showPrevious()
    }

    fun nextView(v: View) {
        viewFlipper.setInAnimation(this, android.R.anim.slide_in_left)
        viewFlipper.setOutAnimation(this, android.R.anim.slide_out_right)
        viewFlipper.showNext()
    }
}
