package com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.holder

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.onedio.androidside.R
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.entries.EntriesModelData
import com.squareup.picasso.Callback
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import io.noties.markwon.Markwon

class HolderEntriesText(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var textEntriesTitle: TextView? =
        itemView.findViewById(R.id.textEntriesTitle) as TextView
    private var textEntriesImage: ImageView? =
        itemView.findViewById(R.id.textEntriesImage) as ImageView
    private var textEntriesContent: TextView? =
        itemView.findViewById(R.id.textEntriesContent) as TextView
    private var textEntriesSource: TextView? =
        itemView.findViewById(R.id.textEntriesSource) as TextView


    fun bindItems(
        context: Context,
        item: EntriesModelData,
        pos: Int,
        listener: (EntriesModelData) -> Unit
    ) {
        ////// Data Set Text
        item.entryTitle?.let {
            if (item.entryTitle != "") {
                textEntriesTitle?.visibility = View.VISIBLE
                Markwon.create(context)
                    .setMarkdown(textEntriesTitle!!, item.entryTitle)
                //textEntriesTitle?.text = item.data.title
            }
        } ?: run {
            textEntriesTitle?.visibility = View.GONE
        }

        item.entryContent?.let {
            if (item.entryContent != "") {
                textEntriesContent?.visibility = View.VISIBLE
                Markwon.create(context).setMarkdown(textEntriesContent!!, item.entryContent)
                //textEntriesContent?.text = item.data.content
            }
        } ?: run {
            textEntriesTitle?.visibility = View.GONE
        }


        item.entryImage?.let {
            if(item.entryImage != ""){
                textEntriesImage?.visibility = View.VISIBLE
                if (item.entryImage.contains("gif", true)) {
                    textEntriesImage?.scaleType = ImageView.ScaleType.FIT_CENTER
                    loadGifWithGlide(context, item.entryImage, textEntriesImage!!)
                } else {
                    //imageEntriesImage?.scaleType = ImageView.ScaleType.CENTER_CROP
                    textEntriesImage?.adjustViewBounds = true
                    textEntriesImage?.scaleType = ImageView.ScaleType.FIT_CENTER
                    loadImageWithoutProgress(item.entryImage, textEntriesImage!!)
                }
            }else
                textEntriesImage?.visibility = View.GONE
        } ?: run {
            textEntriesImage?.visibility = View.GONE
        }


        if (item.source != "") {
            textEntriesSource?.visibility = View.GONE
            Markwon.create(context).setMarkdown(textEntriesSource!!, item.source)
            //textEntriesContent?.text = item.data.content
        } else {
            textEntriesSource?.visibility = View.GONE
        }


        val sharedPref =
            context.getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        val theme = sharedPref.getString("mode", "default")!!

        if(theme == "dark"){
            textEntriesTitle?.setTextColor(Color.parseColor("#FFFFFF"))
            textEntriesContent?.setTextColor(Color.parseColor("#FFFFFF"))
            textEntriesSource?.setTextColor(Color.parseColor("#FFFFFF"))
        }else{
            textEntriesTitle?.setTextColor(Color.parseColor("#191919"))
            textEntriesContent?.setTextColor(Color.parseColor("#191919"))
            textEntriesSource?.setTextColor(Color.parseColor("#191919"))
        }

        /*itemView.setOnClickListener {
            listener(item)
        }*/
    }

    private fun loadImageWithoutProgress(imageUrl: String, imageView: ImageView) {
        Picasso.get().load(imageUrl)
            .networkPolicy(NetworkPolicy.NO_CACHE)
            .memoryPolicy(MemoryPolicy.NO_CACHE)
            .into(imageView, object : Callback {
                override fun onSuccess() {
                    //imageView.visibility = View.VISIBLE
                }

                override fun onError(e: Exception?) {
                    //imageView.visibility = View.GONE
                }
            })
    }

    private fun loadGifWithGlide(context: Context, gifUrl: String, imageView: ImageView) {
        Glide.with(context)
            .load(gifUrl)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
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