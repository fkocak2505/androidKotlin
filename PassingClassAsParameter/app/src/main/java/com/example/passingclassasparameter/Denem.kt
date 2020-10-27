package com.example.passingclassasparameter

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main_2.*

class Denem : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_2)

        CSingleton.instance.setActivityName(Denem::class.java)

        denem.setOnClickListener {
            startActivity(Intent(applicationContext, Deneme1::class.java))
        }

    }
}