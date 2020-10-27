package com.weatherlogger.lateinitaccess

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var className: Class<out Activity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        aaa()

    }

    private fun aaa(): Class<out Activity> {
        return if (::className.isInitialized)
            className
        else
            MainActivity::class.java
    }
}
