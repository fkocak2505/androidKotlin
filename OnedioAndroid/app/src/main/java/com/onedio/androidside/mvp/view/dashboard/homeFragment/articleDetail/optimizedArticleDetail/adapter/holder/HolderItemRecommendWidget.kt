package com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.optimizedArticleDetail.adapter.holder

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.onedio.androidside.R
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.sliderModel.FeedArticleModel
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.optimizedArticleDetail.model.ArticleDetailAdapterModel
import io.noties.markwon.Markwon


class HolderItemRecommendWidget(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var recommendWidgetTitle: TextView? =
        itemView.findViewById(R.id.recommendWidgetTitle) as TextView

    private var recommendWidgetImage: ImageView? =
        itemView.findViewById(R.id.recommendWidgetImage) as ImageView

    private var holderEntriesRecommendWidgetConstraint: ConstraintLayout? =
        itemView.findViewById(R.id.holderEntriesRecommendWidgetConstraint) as ConstraintLayout

    private var viewLineRecommendWidget: View? =
        itemView.findViewById(R.id.viewLineRecommendWidget) as View


    private lateinit var recommendWidgetData: FeedArticleModel
    private var legacyId: Long = 0


    fun bindItems(
        context: Context,
        item: ArticleDetailAdapterModel,
        listener: (Int, ArticleDetailAdapterModel, String) -> Unit,
        pos: Int
    ) {

        item.data.data?.let {
            it.legacyId?.let {
                legacyId = it
            }
        }

        item.widgetData?.let {
            it.title?.let {
                if (it != "") {
                    recommendWidgetTitle?.visibility = View.VISIBLE
                    Markwon.create(context)
                        .setMarkdown(recommendWidgetTitle!!, it)
                    //textEntriesTitle?.text = item.data.title
                } else {
                    recommendWidgetTitle?.visibility = View.GONE
                }
            } ?: run {
                recommendWidgetTitle?.visibility = View.GONE
            }

            it.image?.let {
                it.alternates?.let {
                    it.standardResolution?.let {
                        it.url?.let {
                            if (it != "") {
                                recommendWidgetImage?.visibility = View.VISIBLE
                                recommendWidgetImage?.adjustViewBounds = true
                                recommendWidgetImage?.scaleType = ImageView.ScaleType.FIT_CENTER
                                loadGifWithGlide(context, it, recommendWidgetImage!!)
                            } else
                                recommendWidgetImage?.visibility = View.GONE
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

        } ?: run {

        }

        recommendWidgetTitle?.setOnClickListener {
            listener(pos, item, "RECOMEND_WIDGET")
        }

        recommendWidgetImage?.setOnClickListener {
            listener(pos, item, "RECOMEND_WIDGET")
        }

        holderEntriesRecommendWidgetConstraint?.setOnClickListener {
            listener(pos, item, "RECOMEND_WIDGET")
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