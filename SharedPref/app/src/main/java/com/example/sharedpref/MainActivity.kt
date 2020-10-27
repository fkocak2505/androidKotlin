package com.example.sharedpref

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    val fileName = "FATIH"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val prefs = getSharedPreferences(fileName, Context.MODE_PRIVATE)
        val oldValue = IntSharedPrefs(prefs, "INTEGER_VALUE", 0).getValue<Int>()

        val a = 10

        IntSharedPrefs(prefs, "INTEGER_VALUE", 0).setValue(a)

        val old2 = IntSharedPrefs(prefs, "INTEGER_VALUE", 0).getValue<Int>()

        var ff = 2
    }
}
