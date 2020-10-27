package com.example.intentlab

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class Deneme: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_2)

        supportActionBar?.hide()
    }
}