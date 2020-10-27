package com.example.glidelib

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //"https://img-s2.onedio.com/id-56dc8d2a6765b582286e9c74/rev-0/w-499/h-349/f-gif/s-f7d4b462e8a63bae41ababa27edfa46952c96275.gif"

        /*Glide.with(applicationContext)
            .load("https://img-s2.onedio.com/id-56dc8d2a6765b582286e9c74/rev-0/w-499/h-349/f-gif/s-f7d4b462e8a63bae41ababa27edfa46952c96275.gif")
            .into(gifImageView)*/

        Glide.with(applicationContext)
            .load("https://img-s2.onedio.com/id-59c28f34a3f7daf3217c3794/rev-0/w-399/h-341/f-gif/s-a18de00f71ee6fef6937c2d7aa87ee9938ca92ab.gif")
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    var a = 2
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    var a = 2
                    return false
                }
            })
            .into(gifImageView)

        /*Picasso.get().load("https://img-s2.onedio.com/id-56dc8d2a6765b582286e9c74/rev-0/w-499/h-349/f-gif/s-f7d4b462e8a63bae41ababa27edfa46952c96275.gif")
            .into(gifImageView, object : Callback {
                override fun onSuccess() {
                    var a = 1
                }

                override fun onError(e: Exception?) {
                    var a = 1
                    /// Log. Errorr..
                }
            })*/


    }
}
