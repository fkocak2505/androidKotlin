package com.example.recyclerviewwithwebview

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
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso


class HolderEntriesImage(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var imageEntriesTitle: TextView? =
        itemView.findViewById(R.id.imageEntriesTitle) as TextView
    private var imageEntriesImage: ImageView? =
        itemView.findViewById(R.id.imageEntriesImage) as ImageView
    private var imageEntriesContent: TextView? =
        itemView.findViewById(R.id.imageEntriesContent) as TextView

    fun bindItems(
        context: Context,
        item: EntriesModel,
        pos: Int,
        listener: (Int) -> Unit
    ) {
        ///// Data Set Image
        item.data.title?.let {
            if (item.data.title != "") {
                imageEntriesTitle?.visibility = View.VISIBLE
                imageEntriesTitle?.text = item.data.title
            }
        } ?: run {
            imageEntriesTitle?.visibility = View.GONE
        }

        item.data.content?.let {
            if (item.data.content != "") {
                imageEntriesContent?.visibility = View.VISIBLE
                imageEntriesContent?.text = item.data.content
            }
        } ?: run {
            imageEntriesContent?.visibility = View.VISIBLE
        }


        if (item.image.url!!.contains("gif", true)) {
            imageEntriesImage?.scaleType = ImageView.ScaleType.FIT_CENTER
            loadGifWithGlide(context, item.image.url, imageEntriesImage!!)
        } else {
            imageEntriesImage?.scaleType = ImageView.ScaleType.FIT_XY
            loadImageWithoutProgress(item.image.url, imageEntriesImage!!)
        }


        itemView.setOnClickListener {
            listener(pos)
        }
    }

    private fun loadImageWithoutProgress(imageUrl: String, imageView: ImageView) {
        Picasso.get().load(imageUrl)
            .into(imageView, object : Callback {
                override fun onSuccess() {
                    imageView.visibility = View.VISIBLE
                }

                override fun onError(e: Exception?) {
                    imageView.visibility = View.GONE
                }
            })
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