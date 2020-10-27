package com.onedio.androidside.mvp.view.dashboard.trendingFragment.adapter.holder

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
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

class HolderItemTrendList(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var itemOfTrendCoverPhoto: ImageView? =
        itemView.findViewById(R.id.itemOfTrendCoverPhoto) as ImageView

    private var trendingCategoryBackground: ImageView? =
        itemView.findViewById(R.id.trendingCategoryBackground) as ImageView

    private var trendingCategory: ImageView? =
        itemView.findViewById(R.id.trendingCategory) as ImageView

    private var itemOfTrendCoverPhotoProgress: ProgressBar? =
        itemView.findViewById(R.id.itemOfTrendCoverPhotoProgress) as ProgressBar

    private var itemOfTrendCoverPhotoText: TextView? =
        itemView.findViewById(R.id.itemOfTrendCoverPhotoText) as TextView

    private var trendingConstraint: ConstraintLayout? =
        itemView.findViewById(R.id.trendingConstraint) as ConstraintLayout


    fun bindItems(
        context: Context,
        item: TrendingAdapterModel,
        listener: (TrendingAdapterModel, String) -> Unit,
        pos: Int
    ) {

        setTextViewsData(context, item)

        setImageViewsData(context, item)

        setViewClickListener(context, item, listener)

        val sharedPref =
            context.getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        val theme = sharedPref.getString("mode", "default")

        if (theme == "dark") {
            itemOfTrendCoverPhotoText?.setTextColor(Color.parseColor("#FFFFFF"))
            trendingCategoryBackground?.setImageResource(R.drawable.bg_image_view_background_dark_mode)
        } else {
            itemOfTrendCoverPhotoText?.setTextColor(Color.parseColor("#191919"))
            trendingCategoryBackground?.setImageResource(R.drawable.bg_image_view_background)
        }


    }

    private fun setTextViewsData(context: Context, item: TrendingAdapterModel) {

        Markwon.create(context)
            .setMarkdown(itemOfTrendCoverPhotoText!!, item.title)

    }

    private fun setImageViewsData(context: Context, item: TrendingAdapterModel) {
        if (item.coverPhoto != "") {
            loadGifWithGlide(
                context,
                item.coverPhoto,
                itemOfTrendCoverPhoto!!,
                itemOfTrendCoverPhotoProgress!!
            )
        } else {
            itemOfTrendCoverPhoto?.visibility = View.VISIBLE
            itemOfTrendCoverPhoto?.setImageResource(R.drawable.image_error_dark_mode)
            itemOfTrendCoverPhotoProgress?.visibility = View.GONE
        }



        if (item.categoryUrl != "") {
            trendingCategory?.visibility = View.VISIBLE
            trendingCategoryBackground?.visibility = View.VISIBLE

            loadGifWithGlide(
                context,
                item.categoryUrl,
                trendingCategory!!,
                null
            )

        } else {
            trendingCategory?.visibility = View.GONE
            trendingCategoryBackground?.visibility = View.GONE
        }

    }

    private fun setViewClickListener(context: Context, item: TrendingAdapterModel, listener: (TrendingAdapterModel, String) -> Unit){

        itemOfTrendCoverPhoto?.setOnClickListener {
            listener(item, "COVER_PHOTO")
        }

        itemOfTrendCoverPhotoText?.setOnClickListener {
            listener(item, "COVER_PHOTO")
        }

        trendingCategoryBackground?.setOnClickListener {
            listener(item, "CATEGORY")
        }

        trendingCategory?.setOnClickListener {
            listener(item, "CATEGORY")
        }
    }

    private fun loadGifWithGlide(
        context: Context,
        gifUrl: String,
        imageView: ImageView,
        progressBar: ProgressBar?
    ) {
        Glide.with(context)
            .load(gifUrl)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    imageView.visibility = View.VISIBLE
                    progressBar?.let {
                        it.visibility = View.GONE
                    }
                    imageView.setImageResource(R.drawable.image_error_dark_mode)
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
                    progressBar?.let {
                        it.visibility = View.GONE
                    }
                    return false
                }
            })
            .into(imageView)
    }
}