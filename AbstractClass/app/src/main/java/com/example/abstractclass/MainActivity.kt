package com.example.abstractclass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val obj = College("Fatih", 26)
        obj.absFunc("Fatih Koçak")
        obj.demo()

    }
}
