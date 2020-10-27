package com.weatherlogger.multiplelayoutlab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewGroup  = findViewById<ViewGroup>(R.id.linearLayoutt)
        var aaa = LayoutInflater.from(applicationContext).inflate(R.layout.demo_layout, viewGroup, false)
        var aaa1 = LayoutInflater.from(applicationContext).inflate(R.layout.demo_layout, viewGroup, false)

        viewGroup.addView(aaa)
        viewGroup.addView(aaa1)

        val textView: TextView = aaa.findViewById(R.id.deneme)
        val textView2: TextView = aaa1.findViewById(R.id.deneme)

        textView.text = "Fatih Koçak"
        textView2.text = "zsfgasfa Koçak"

        denemeeeee(textView)
        denemeeeee(textView2)

    }

    private fun denemeeeee(view: View){
        view.setOnClickListener{
            (view as TextView).text = "awfawfff"
        }
    }
}
