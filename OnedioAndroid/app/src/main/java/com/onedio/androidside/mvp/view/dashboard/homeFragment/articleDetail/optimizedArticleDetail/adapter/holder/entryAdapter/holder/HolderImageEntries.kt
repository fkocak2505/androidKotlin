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
import io.noties.markwon.Markwon


class HolderImageEntries(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var imageEntriesTitle: TextView? =
        itemView.findViewById(R.id.imageEntriesTitle) as TextView
    private var imageEntriesImage: ImageView? =
        itemView.findViewById(R.id.imageEntriesImage) as ImageView
    private var imageEntriesContent: TextView? =
        itemView.findViewById(R.id.imageEntriesContent) as TextView
    private var imageEntriesSource: TextView? =
        itemView.findViewById(R.id.imageEntriesSource) as TextView



    fun bindItems(
        context: Context,
        item: ArticleFeedDetailsEntryModel,
        pos: Int,
        listener: (Int, ArticleFeedDetailsEntryModel, String) -> Unit
    ) {

        setTextViewsData(context, item)

        setImageViewsData(context, item)

        itemView.setOnClickListener {
            listener(pos, item, "IMAGE_ENTRIES")
        }


        /*val sharedPref =
            context.getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        val theme = sharedPref.getString("mode", "default")!!

        if(theme == "dark"){
            imageEntriesTitle?.setTextColor(Color.parseColor("#FFFFFF"))
            imageEntriesContent?.setTextColor(Color.parseColor("#FFFFFF"))
            imageEntriesSource?.setTextColor(Color.parseColor("#FFFFFF"))
        }else{
            imageEntriesTitle?.setTextColor(Color.parseColor("#191919"))
            imageEntriesContent?.setTextColor(Color.parseColor("#191919"))
            imageEntriesSource?.setTextColor(Color.parseColor("#191919"))
        }*/


        /*itemView.setOnClickListener {
            listener(item)
        }*/
    }

    private fun setTextViewsData(context: Context, item: ArticleFeedDetailsEntryModel){
        item.title?.let {
            if (item.title != "") {
                imageEntriesTitle?.visibility = View.VISIBLE
                //imageEntriesTitle?.text = item.data.title
                Markwon.create(context).setMarkdown(imageEntriesTitle!!, item.title!!)
            }
        } ?: run {
            imageEntriesTitle?.visibility = View.GONE
        }

        item.content?.let {
            if (item.content != "") {
                imageEntriesContent?.visibility = View.VISIBLE
                //imageEntriesContent?.text = item.data.content
                Markwon.create(context).setMarkdown(imageEntriesContent!!, it)
            }
        } ?: run {
            imageEntriesContent?.visibility = View.GONE
        }

        item.urls?.let {
            it.source?.let {
                if(it != ""){
                    imageEntriesSource?.visibility = View.GONE
                    Markwon.create(context).setMarkdown(imageEntriesSource!!, it)
                }
            }?: run{
                imageEntriesSource?.visibility = View.GONE
            }
        }?: run{
            imageEntriesSource?.visibility = View.GONE
        }
    }

    private fun setImageViewsData(context: Context, item: ArticleFeedDetailsEntryModel){
        item.image?.let {
            it.alternates?.let {
                it.standardResolution?.let {
                    it.url?.let {
                        if(it != ""){
                            imageEntriesImage?.visibility = View.VISIBLE
                            imageEntriesImage?.scaleType = ImageView.ScaleType.FIT_CENTER
                            loadGifWithGlide(context, it, imageEntriesImage!!)
                            /*if (it.contains("gif", true)) {
                                imageEntriesImage?.scaleType = ImageView.ScaleType.FIT_CENTER
                                loadGifWithGlide(context, it, imageEntriesImage!!)
                            } else {
                                //imageEntriesImage?.scaleType = ImageView.ScaleType.CENTER_CROP
                                imageEntriesImage?.adjustViewBounds = true
                                imageEntriesImage?.scaleType = ImageView.ScaleType.FIT_CENTER
                                loadImageWithoutProgress(it, imageEntriesImage!!)
                            }*/
                        }else{
                            imageEntriesImage?.visibility = View.GONE
                        }
                    }?: run{
                        imageEntriesImage?.visibility = View.GONE
                    }
                }?: run{
                    imageEntriesImage?.visibility = View.GONE
                }
            }?: run{
                imageEntriesImage?.visibility = View.GONE
            }
        }?: run{
            imageEntriesImage?.visibility = View.GONE
        }
    }

    /*private fun loadImageWithoutProgress(imageUrl: String, imageView: ImageView) {
        Picasso.get().load(imageUrl)
            .networkPolicy(NetworkPolicy.NO_CACHE)
            .memoryPolicy(MemoryPolicy.NO_CACHE)
            .into(imageView, object : Callback {
                override fun onSuccess() {
                    var a = 2
                }

                override fun onError(e: Exception?) {
                    var a = 2
                }
            })

    }*/

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