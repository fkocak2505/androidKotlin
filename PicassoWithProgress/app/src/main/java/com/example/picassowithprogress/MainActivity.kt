package com.example.picassowithprogress

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

        /*Picasso.get().load("https://img-s1.onedio.com/id-5a8dd037b2a76cb937984de3/rev-0/w-2000/h-1000/f-jpg/s-53af36079143d6083e860089c70bb5cdcc457426.jpg")
            .into(deneme, object : Callback {
                override fun onSuccess() {
                    articleActionPhotoProgress.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    articleActionPhotoProgress.visibility = View.VISIBLE
                    /// Log. Errorr..
                }
            })*/

    }
}
