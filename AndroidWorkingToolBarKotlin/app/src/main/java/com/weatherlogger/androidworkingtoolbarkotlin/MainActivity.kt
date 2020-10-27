package com.weatherlogger.androidworkingtoolbarkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main.setOnClickListener{
            startActivity(Intent(applicationContext, Deneme1::class.java))
        }

        deneme2.setOnClickListener{
            startActivity(Intent(applicationContext, Deneme2::class.java))
        }


    }
}
