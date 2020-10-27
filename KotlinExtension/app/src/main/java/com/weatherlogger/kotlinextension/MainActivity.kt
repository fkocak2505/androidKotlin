package com.weatherlogger.kotlinextension

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView.loadImage("https://diodynamicimage.oned.io/fit-in/500x0/filters:format(png)/filters:quality(80)/b35208750096b9d4f4a013cb4ec20e9bb47a7d66/53616c7465645f5f4e18432050f5af1afcb7bdd8ca056af95665038b56a0b2c73a9dd70ba9403f949d3528b7ef00c5dd29d6e862954fbc8746946cd77b5416f74bea6b6a34b94b216ddd8b6e2df705507202cf6f59d0b717837282b373602b692c9c538288a566026a5d810ba0f649d5.png")
    }

    fun ImageView.loadImage(imageUrl: String) {
        Glide.with(context)
            .load(imageUrl)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    this@loadImage.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    this@loadImage.visibility = View.VISIBLE
                    return false
                }
            })
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .into(this)


    }
}
