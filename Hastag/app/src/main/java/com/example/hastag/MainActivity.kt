package com.example.hastag

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import com.greenfrvr.hashtagview.HashtagView
import android.widget.Toast




class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val DATA = listOf(
            "#android", "#library", "#collection",
            "#hashtags", "#min14sdk", "#UI", "#view", "#github", "#opensource", "#project", "#widget"
        )

        searchTagsStaggeredGrid.setData(DATA)

        searchTagsStaggeredGrid.addOnTagClickListener(HashtagView.TagsClickListener { item ->
            Toast.makeText(applicationContext, item.toString(), Toast.LENGTH_SHORT).show()
        })

    }
}
