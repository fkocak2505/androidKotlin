package com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.optimizedArticleDetail.adapter.holder.entryAdapter.holder

import android.content.Context
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
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.newResponse.ArticleFeedDetailsEntryModel
import com.squareup.picasso.Callback
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import io.noties.markwon.Markwon

class HolderTextEntries(itemView: View) : RecyclerView.ViewHolder(itemView) {

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
        item: ArticleFeedDetailsEntryModel,
        pos: Int,
        listener: (Int, ArticleFeedDetailsEntryModel, String) -> Unit
    ) {

        setTextViewsData(context, item)

        setImageViewsData(context, item)

        itemView.setOnClickListener {
            listener(pos, item, "TEXT_ENTRIES")
        }

       /* val sharedPref =
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
        }*/

        /*itemView.setOnClickListener {
            listener(item)
        }*/
    }

    private fun setTextViewsData(context: Context, item: ArticleFeedDetailsEntryModel){
        ////// Data Set Text
        item.title?.let {
            if (item.title != "") {
                textEntriesTitle?.visibility = View.VISIBLE
                Markwon.create(context)
                    .setMarkdown(textEntriesTitle!!, it)
                //textEntriesTitle?.text = item.data.title
            }
        } ?: run {
            textEntriesTitle?.visibility = View.GONE
        }

        item.content?.let {
            if (item.content != "") {
                textEntriesContent?.visibility = View.VISIBLE
                Markwon.create(context).setMarkdown(textEntriesContent!!, it)
                //textEntriesContent?.text = item.data.content
            }
        } ?: run {
            textEntriesContent?.visibility = View.GONE
        }

        item.urls?.let {
            it.source?.let {
                if(it != ""){
                    textEntriesSource?.visibility = View.GONE
                    Markwon.create(context).setMarkdown(textEntriesSource!!, it)
                }
            }?: run{
                textEntriesSource?.visibility = View.GONE
            }
        }?: run{
            textEntriesSource?.visibility = View.GONE
        }

    }

    private fun setImageViewsData(context: Context, item: ArticleFeedDetailsEntryModel){
        item.image?.let {
            it.alternates?.let {
                it.standardResolution?.let {
                    it.url?.let {
                        if(it != ""){
                            textEntriesImage?.visibility = View.VISIBLE
                            if (it.contains("gif", true)) {
                                textEntriesImage?.scaleType = ImageView.ScaleType.FIT_CENTER
                                loadGifWithGlide(context, it, textEntriesImage!!)
                            } else {
                                //imageEntriesImage?.scaleType = ImageView.ScaleType.CENTER_CROP
                                textEntriesImage?.adjustViewBounds = true
                                textEntriesImage?.scaleType = ImageView.ScaleType.FIT_CENTER
                                loadImageWithoutProgress(it, textEntriesImage!!)
                            }
                        }else{
                            textEntriesImage?.visibility = View.GONE
                        }
                    }?: run{
                        textEntriesImage?.visibility = View.GONE
                    }
                }?: run{
                    textEntriesImage?.visibility = View.GONE
                }
            }?: run{
                textEntriesImage?.visibility = View.GONE
            }
        }?: run{
            textEntriesImage?.visibility = View.GONE
        }
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