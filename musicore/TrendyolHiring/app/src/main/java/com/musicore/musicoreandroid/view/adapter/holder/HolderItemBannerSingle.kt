package com.musicore.musicoreandroid.view.adapter.holder

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.musicore.musicoreandroid.R
import com.musicore.musicoreandroid.model.WidgetsItem

class HolderItemBannerSingle(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var bannerSingleTitle: TextView? =
        itemView.findViewById(R.id.bannerSingleTitle) as TextView

    private var bannerSingleImage: ImageView? =
        itemView.findViewById(R.id.bannerSingleImage) as ImageView

    fun bindItems(
        context: Context,
        item: WidgetsItem,
        listener: (View, WidgetsItem, String) -> Unit,
        pos: Int
    ) {


        if (item.bannerContents.size != 0) {
            loadGifWithGlide(context, item.bannerContents[item.bannerContents.size - 1].imageUrl!!, bannerSingleImage!!)
        } else {
            bannerSingleImage?.setImageResource(R.drawable.ic_launcher_foreground)
        }

        item.title?.let {
            bannerSingleTitle?.text = it
        } ?: run {
            bannerSingleTitle?.text = "Empty Title"
        }

        bannerSingleImage?.setOnClickListener {
            listener(it, item, "BANNER_SINGLE")
        }

        bannerSingleTitle?.setOnClickListener {
            listener(it, item, "BANNER_SINGLE")
        }


    }


    private fun loadGifWithGlide(context: Context, gifUrl: String, imageView: ImageView) {
        Glide.with(context)
            .load(gifUrl)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    imageView.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    imageView.visibility = View.VISIBLE
                    return false
                }
            })
            .into(imageView)
    }
}