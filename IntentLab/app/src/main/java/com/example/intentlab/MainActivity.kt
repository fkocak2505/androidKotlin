package com.example.intentlab

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        deneme.setOnClickListener{
            val intent = Intent(applicationContext, Deneme::class.java)
            val options = ActivityOptions.makeCustomAnimation(
                applicationContext,
                android.R.anim.fade_in,
                android.R.anim.fade_out
            )
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent, options.toBundle())
        }
    }
}
