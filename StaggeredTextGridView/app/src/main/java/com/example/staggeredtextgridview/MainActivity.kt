package com.example.staggeredtextgridview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var aa: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        aa.add("Beşiktaş Jimnastik Kulübü")
        aa.add("Native")
        aa.add("Onedio")
        aa.add("Onedio Molası")


        staggeredTextView.setAdapter(WordsAdapter(applicationContext, aa))


    }
}
