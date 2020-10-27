package com.example.shinebutton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import com.sackcentury.shinebuttonlib.ShineButton



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        shineButton.setOnCheckStateChangeListener { view, checked ->
            val a = 2
        }

    }
}
