package com.example.statusline

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var isClickEmojiReaction = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val count = 10

        val lp = statusLine.layoutParams as ConstraintLayout.LayoutParams
        val firstHeight = lp.height


        button.setOnClickListener{
            val lp = statusLine.layoutParams as ConstraintLayout.LayoutParams
            if (!isClickEmojiReaction){
                lp.height = firstHeight + 10
                statusLine.layoutParams = lp
                isClickEmojiReaction = true
            } else {
                lp.height = firstHeight
                statusLine.layoutParams = lp
                isClickEmojiReaction = false
            }
        }
    }
}
