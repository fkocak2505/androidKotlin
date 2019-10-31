package com.example.kotlinmutliconstructor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val age = MutliConstructor(applicationContext, 10).deneme()

        val secondAge = MutliConstructor(20).deneme()

        Toast.makeText(applicationContext, age.toString(), Toast.LENGTH_SHORT).show()
        Toast.makeText(applicationContext, secondAge.toString(), Toast.LENGTH_SHORT).show()

    }
}
