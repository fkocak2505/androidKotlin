package com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.holder

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
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

class HolderEntriesRecommendWidget(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var recommendWidgetTitle: TextView? =
        itemView.findViewById(R.id.recommendWidgetTitle) as TextView
    /*private var onedioEmbedPhotoProgress: ProgressBar? =
        itemView.findViewById(R.id.onedioEmbedPhotoProgress) as ProgressBar*/
    private var recommendWidgetImage: ImageView? =
        itemView.findViewById(R.id.recommendWidgetImage) as ImageView
    private var holderEntriesRecommendWidgetConstraint: ConstraintLayout? =
        itemView.findViewById(R.id.holderEntriesRecommendWidgetConstraint) as ConstraintLayout

    private var viewLineRecommendWidget: View? =
        itemView.findViewById(R.id.viewLineRecommendWidget) as View




    fun bindItems(
        context: Context,
        item: EntriesModelData,
        pos: Int,
        listener: (EntriesModelData) -> Unit
    ) {
        ////// Data Set Text
        item.entryTitle?.let {
            if (item.entryTitle != "") {
                recommendWidgetTitle?.visibility = View.VISIBLE
                Markwon.create(context)
                    .setMarkdown(recommendWidgetTitle!!, item.entryTitle)
                //textEntriesTitle?.text = item.data.title
            }
        } ?: run {
            recommendWidgetTitle?.visibility = View.GONE
        }


        item.entryImage?.let {
            if(item.entryImage != ""){
                recommendWidgetImage?.visibility = View.VISIBLE
                if (item.entryImage.contains("gif", true)) {
                    recommendWidgetImage?.scaleType = ImageView.ScaleType.FIT_CENTER
                    loadGifWithGlide(context, item.entryImage, recommendWidgetImage!!)
                } else {
                    //imageEntriesImage?.scaleType = ImageView.ScaleType.CENTER_CROP
                    recommendWidgetImage?.adjustViewBounds = true
                    recommendWidgetImage?.scaleType = ImageView.ScaleType.FIT_CENTER
                    loadImageWithoutProgress(item.entryImage, recommendWidgetImage!!)
                }
            }else
                recommendWidgetImage?.visibility = View.GONE
        } ?: run {
            recommendWidgetImage?.visibility = View.GONE
        }

        val sharedPref =
            context.getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        val theme = sharedPref.getString("mode", "default")!!

        if(theme == "dark"){
            holderEntriesRecommendWidgetConstraint?.setBackgroundColor(ContextCompat.getColor(context, R.color.recommend_dark_background_color))
            viewLineRecommendWidget?.setBackgroundColor(ContextCompat.getColor(context, R.color.recommend_dark__view_line_background_color))
        }else{
            holderEntriesRecommendWidgetConstraint?.setBackgroundColor(ContextCompat.getColor(context, R.color.recommend_light_background_color))
            viewLineRecommendWidget?.setBackgroundColor(ContextCompat.getColor(context, R.color.recommend_light_view_line_background_color))
        }

        itemView.setOnClickListener {
            listener(item)
        }

        holderEntriesRecommendWidgetConstraint?.setOnClickListener {
            listener(item)
        }

        recommendWidgetTitle?.setOnClickListener {
            listener(item)
        }

        recommendWidgetImage?.setOnClickListener {
            listener(item)
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