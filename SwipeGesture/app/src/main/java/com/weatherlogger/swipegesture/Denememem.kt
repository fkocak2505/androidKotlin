package com.weatherlogger.swipegesture

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.r0adkll.slidr.Slidr
import kotlinx.android.synthetic.main.aaaa.*
import kotlinx.android.synthetic.main.activity_main.*

class Denememem: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.aaaa)


        constraintLayoutRoot1.setOnTouchListener(object : OnSwipeTouchListener(this){
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                return super.onTouch(v, event)
            }

            override fun onSwipeRight() {
                super.onSwipeRight()
                val intent = Intent(this@Denememem, MainActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
            }

            override fun onSwipeLeft() {
                super.onSwipeLeft()
            }

            override fun onSwipeTop() {
                super.onSwipeTop()
                Toast.makeText(applicationContext, "top", Toast.LENGTH_SHORT).show()
            }

            override fun onSwipeBottom() {
                super.onSwipeBottom()
            }
        })

    }
}