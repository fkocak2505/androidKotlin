package com.weatherlogger.swipegesture

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        intent.getStringExtra("data")?.let {
            aaaaa.text = intent.getStringExtra("data")
        }?: run{

        }

        aaaaa.setOnClickListener{
            startActivity(Intent(applicationContext, Denememem::class.java))
        }

        constraintLayoutRoot.setOnTouchListener(object : OnSwipeTouchListener(this){
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                return super.onTouch(v, event)
            }

            override fun onSwipeRight() {
                super.onSwipeRight()
                Toast.makeText(applicationContext, "Right", Toast.LENGTH_SHORT).show()
            }

            override fun onSwipeLeft() {
                super.onSwipeLeft()
                val intent = Intent(this@MainActivity, MainActivity::class.java)
                intent.putExtra("data", "textData")
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            }

            override fun onSwipeTop() {
                super.onSwipeTop()
                Toast.makeText(applicationContext, "top", Toast.LENGTH_SHORT).show()
            }

            override fun onSwipeBottom() {
                super.onSwipeBottom()
            }
        })

        /*constraintLayoutRoot.setOnTouchListener(object : OnSwipeTouchListener() {

            override fun onSwipeLeft(): Boolean {
                return true
            }

            override fun onSwipeRight(): Boolean {
                val intent = Intent(this@MainActivity, Denememem::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
                return true
            }

        })
*/

    }
}
