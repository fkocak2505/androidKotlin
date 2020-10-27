package com.onedio.androidside.mvp.view.dashboard.trendingFragment.adapter.holder

import android.content.Context
import android.graphics.Color
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
import com.onedio.androidside.R
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.view.dashboard.trendingFragment.adapter.model.TrendingAdapterModel
import io.noties.markwon.Markwon

class HolderItemTrendingHeader(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var itemOfPopularTrendingArticleCoverPhoto: ImageView? =
        itemView.findViewById(R.id.itemOfPopularTrendingArticleCoverPhoto) as ImageView

    private var itemOfPopularTrendingBadgeLineCovertPhoto: View? =
        itemView.findViewById(R.id.itemOfPopularTrendingBadgeLineCovertPhoto) as View

    private var itemOfPopularTrendingCoverPhotoBadgeImage: ImageView? =
        itemView.findViewById(R.id.itemOfPopularTrendingCoverPhotoBadgeImage) as ImageView

    private var itemOfPopularTrendingArticleActionBackground: ImageView? =
        itemView.findViewById(R.id.itemOfPopularTrendingArticleActionBackground) as ImageView

    private var itemOfPopularTrendingArticleDetailAction: ImageView? =
        itemView.findViewById(R.id.itemOfPopularTrendingArticleDetailAction) as ImageView

    private var itemOfPopularTrendingArticleDetailTitle: TextView? =
        itemView.findViewById(R.id.itemOfPopularTrendingArticleDetailTitle) as TextView

    private var itemOfPopularTrendingWidthLine: View? =
        itemView.findViewById(R.id.itemOfPopularTrendingWidthLine) as View


    fun bindItems(
        context: Context,
        item: TrendingAdapterModel,
        listener: (TrendingAdapterModel, String) -> Unit,
        pos: Int
    ) {

        itemOfPopularTrendingWidthLine?.visibility = View.VISIBLE

        setTextViewsData(context, item)

        setImageViewsData(context, item)

        setViewsClickListener(pos, item, listener)

        val sharedPref = context.getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        val theme = sharedPref.getString("mode", "default")

        if (theme == "dark") {
            itemOfPopularTrendingArticleDetailTitle?.setTextColor(Color.parseColor("#FFFFFF"))
            itemOfPopularTrendingArticleActionBackground?.setImageResource(R.drawable.icon_circle_dark_mode)
            itemOfPopularTrendingWidthLine?.setBackgroundColor(Color.parseColor("#0e1720"))
        }else{
            itemOfPopularTrendingArticleDetailTitle?.setTextColor(Color.parseColor("#191919"))
            itemOfPopularTrendingArticleActionBackground?.setImageResource(R.drawable.ic_circle)
            itemOfPopularTrendingWidthLine?.setBackgroundColor(Color.parseColor("#e2e2e2"))
        }


    }

    private fun setTextViewsData(context: Context, item: TrendingAdapterModel) {

        Markwon.create(context)
            .setMarkdown(itemOfPopularTrendingArticleDetailTitle!!, item.title)
    }

    private fun setImageViewsData(context: Context, item: TrendingAdapterModel) {
        if(item.coverPhoto != ""){
            loadImageWithGlide(
                context,
                item.coverPhoto,
                itemOfPopularTrendingArticleCoverPhoto!!,
                R.drawable.image_error_dark_mode
            )
        }else{
            itemOfPopularTrendingArticleCoverPhoto?.visibility = View.VISIBLE
            itemOfPopularTrendingArticleCoverPhoto?.setImageResource(R.drawable.image_error_dark_mode)
        }



        itemOfPopularTrendingCoverPhotoBadgeImage?.visibility = View.INVISIBLE
        itemOfPopularTrendingBadgeLineCovertPhoto?.visibility = View.INVISIBLE


        if(item.categoryUrl != ""){
            itemOfPopularTrendingArticleDetailAction?.visibility = View.VISIBLE
            itemOfPopularTrendingArticleActionBackground?.visibility = View.VISIBLE

            loadImageWithGlide(
                context,
                item.categoryUrl,
                itemOfPopularTrendingArticleDetailAction!!,
                R.drawable.empty_avatar
            )
        }else{
            itemOfPopularTrendingArticleDetailAction?.visibility = View.GONE
            itemOfPopularTrendingArticleActionBackground?.visibility = View.GONE
        }
    }

    private fun setViewsClickListener(
        pos: Int,
        item: TrendingAdapterModel,
        listener: (TrendingAdapterModel, String) -> Unit
    ) {
        itemOfPopularTrendingArticleCoverPhoto?.setOnClickListener {
            listener(item, "COVER_PHOTO")
        }

        itemOfPopularTrendingArticleDetailTitle?.setOnClickListener {
            listener(item, "COVER_PHOTO")
        }

        itemOfPopularTrendingArticleActionBackground?.setOnClickListener {
            listener(item, "CATEGORY")
        }

        itemOfPopularTrendingArticleDetailAction?.setOnClickListener {
            listener(item, "CATEGORY")
        }

    }

    private fun loadImageWithGlide(
        context: Context,
        imageUrl: String,
        imageView: ImageView,
        hintImage: Int
    ) {
        Glide.with(context)
            .load(imageUrl)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    imageView.setImageResource(hintImage)
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