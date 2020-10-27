package com.weatherlogger.fillxmlpart

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val inflated = layoutInflater.inflate(R.layout.deneme, null)
        val textView = inflated.findViewById(R.id.deneme) as TextView

        textView.text = "Fatih"

        Log.i("awfawf: ", textView.text.toString())

        //startActivity(Intent(applicationContext, Deneme::class.java))

    }
}
