package com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.optimizedArticleDetail.adapter.holder.entryAdapter.holder

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
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
import com.google.firebase.analytics.FirebaseAnalytics
import com.onedio.androidside.R
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.newResponse.ArticleFeedDetailsEntryModel
import com.squareup.picasso.Callback
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import com.yandex.metrica.YandexMetrica
import io.noties.markwon.Markwon

class HolderRecommendWidgetEntries(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var recommendWidgetTitle: TextView? =
        itemView.findViewById(R.id.recommendWidgetTitle) as TextView
    private var recommendWidgetImage: ImageView? =
        itemView.findViewById(R.id.recommendWidgetImage) as ImageView
    private var holderEntriesRecommendWidgetConstraint: ConstraintLayout? =
        itemView.findViewById(R.id.holderEntriesRecommendWidgetConstraint) as ConstraintLayout

    private var viewLineRecommendWidget: View? =
        itemView.findViewById(R.id.viewLineRecommendWidget) as View


    fun bindItems(
        context: Context,
        item: ArticleFeedDetailsEntryModel,
        pos: Int,
        listener: (Int, ArticleFeedDetailsEntryModel, String) -> Unit
    ) {

        val params = Bundle()
        val firebaseAnalytics =
            FirebaseAnalytics.getInstance(context)
        firebaseAnalytics.logEvent("in_article_recommendation_view", params)

        val mapOfFeedAppMetrica = HashMap<String, Any>()
        YandexMetrica.reportEvent("in_article_recommendation_view", mapOfFeedAppMetrica)

        item.title?.let {
            if (it != "") {
                recommendWidgetTitle?.visibility = View.VISIBLE
                Markwon.create(context)
                    .setMarkdown(recommendWidgetTitle!!, it)
            } else {
                recommendWidgetTitle?.visibility = View.GONE
            }
        } ?: run {
            recommendWidgetTitle?.visibility = View.GONE
        }

        item.image?.let {
            it.alternates?.let {
                it.standardResolution?.let {
                    it.url?.let {
                        if (it.contains("gif", true)) {
                            recommendWidgetImage?.scaleType = ImageView.ScaleType.FIT_CENTER
                            loadGifWithGlide(context, it, recommendWidgetImage!!)
                        } else {
                            //imageEntriesImage?.scaleType = ImageView.ScaleType.CENTER_CROP
                            recommendWidgetImage?.adjustViewBounds = true
                            recommendWidgetImage?.scaleType = ImageView.ScaleType.FIT_CENTER
                            loadImageWithoutProgress(it, recommendWidgetImage!!)
                        }
                    } ?: run {
                        recommendWidgetImage?.visibility = View.GONE
                    }
                } ?: run {
                    recommendWidgetImage?.visibility = View.GONE
                }
            } ?: run {
                recommendWidgetImage?.visibility = View.GONE
            }
        } ?: run {
            recommendWidgetImage?.visibility = View.GONE
        }

        val sharedPref =
            context.getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        val theme = sharedPref.getString("mode", "default")!!

        if (theme == "dark") {
            holderEntriesRecommendWidgetConstraint?.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.recommend_dark_background_color
                )
            )
            viewLineRecommendWidget?.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.recommend_dark__view_line_background_color
                )
            )
        } else {
            holderEntriesRecommendWidgetConstraint?.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.recommend_light_background_color
                )
            )
            viewLineRecommendWidget?.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.recommend_light_view_line_background_color
                )
            )
        }

        itemView.setOnClickListener {
            listener(pos, item, "RECOMMEND_WIDGET")
        }

        holderEntriesRecommendWidgetConstraint?.setOnClickListener {
            listener(pos, item, "RECOMMEND_WIDGET")
        }

        recommendWidgetTitle?.setOnClickListener {
            listener(pos, item, "RECOMMEND_WIDGET")
        }

        recommendWidgetImage?.setOnClickListener {
            listener(pos, item, "RECOMMEND_WIDGET")
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