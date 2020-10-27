package com.example.passingclassasparameter

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main_1.*

class Deneme1: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_1)

        deneme1.setOnClickListener{
            val className = CSingleton.instance.getActivityname()
            startActivity(Intent(applicationContext, className))
        }
    }
}