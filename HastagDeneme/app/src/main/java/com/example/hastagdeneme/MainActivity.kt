package com.example.hastagdeneme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val DATA = listOf(
            "#android",
            "#library",
            "#collection",
            "#hashtags",
            "#min14sdk",
            "#UI",
            "#view",
            "#github",
            "#opensource",
            "#project",
            "#widget"
        )

        afafa.setData(DATA)

        afafa.addOnTagClickListener(HashtagView.TagsClickListener { item ->
            Toast.makeText(applicationContext, item.toString(), Toast.LENGTH_SHORT).show()
        })


    }
}
