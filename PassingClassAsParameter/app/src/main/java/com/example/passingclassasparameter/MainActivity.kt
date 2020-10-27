package com.example.passingclassasparameter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CSingleton.instance.setActivityName(MainActivity::class.java)

        main.setOnClickListener{
            startActivity(Intent(applicationContext, Deneme1::class.java))
        }
    }
}
