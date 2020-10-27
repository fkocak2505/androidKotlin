package com.example.scaleimageview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val url =
            "https://img-s1.onedio.com/id-5b29102b6e44d74e34a37b3c/rev-0/w-1024/h-615/f-jpg/s-753bf6ba1c4bedf94b34a45764251c0e48cdd1c5.jpg"
        val url2 =
            "https://img-s1.onedio.com/id-5aa7af5c687de41e2bb05e6a/rev-0/w-312/h-400/f-jpg/s-c5955a86066ef43b139491653773dbc98a05dcbc.jpg"

        Picasso.get().load(url)
            .into(imageView, object : Callback {
                override fun onSuccess() {
                    imageView.visibility = View.VISIBLE
                }

                override fun onError(e: Exception?) {
                    imageView.visibility = View.GONE
                }
            })
    }
}
