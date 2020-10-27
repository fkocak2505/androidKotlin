package com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.adapter.holder

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
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.adapter.model.CategoryAdapterModel
import io.noties.markwon.Markwon

class HolderCategoryArticle(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var categoryItemPhoto: ImageView? =
        itemView.findViewById(R.id.categoryItemPhoto) as ImageView

    private var categoryItemPhotoProgress: ProgressBar? =
        itemView.findViewById(R.id.categoryItemPhotoProgress) as ProgressBar

    private var categoryItemTitle: TextView? =
        itemView.findViewById(R.id.categoryItemTitle) as TextView

    private var categoryItemSubTitle: TextView? =
        itemView.findViewById(R.id.categoryItemSubTitle) as TextView

    private var constraintLayout7ReadCount: ConstraintLayout? =
        itemView.findViewById(R.id.constraintLayout7) as ConstraintLayout

    private var categoryItemConstraint: ConstraintLayout? =
        itemView.findViewById(R.id.categoryItemConstraint) as ConstraintLayout

    private var categoryItemReadCount: TextView? =
        itemView.findViewById(R.id.categoryItemReadCount) as TextView

    private var categoryItemDate: TextView? =
        itemView.findViewById(R.id.categoryItemDate) as TextView

    private var categoryFeedViewLine: View? =
        itemView.findViewById(R.id.categoryFeedViewLine) as View

    private var infoText: TextView? =
        itemView.findViewById(R.id.infoText) as TextView


    fun bindItems(
        context: Context,
        item: CategoryAdapterModel,
        listener: (CategoryAdapterModel, String) -> Unit,
        pos: Int
    ) {

        setImageViewsData(context, item)

        setTextViewsData(context, item)

        setDarkModeTheme(context)

        setTextViewsTypeFace(context)

        setViewsClicked(item, listener)

    }

    private fun setImageViewsData(context: Context, item: CategoryAdapterModel) {

        loadGifWithGlide(context, item.categoryItemPhoto, categoryItemPhoto!!)

    }

    private fun setTextViewsData(context: Context, item: CategoryAdapterModel) {
        Markwon.create(context)
            .setMarkdown(categoryItemTitle!!, item.categoryItemTitle)
        Markwon.create(context)
            .setMarkdown(categoryItemSubTitle!!, item.categoryItemSubTitle)
        categoryItemDate?.text = item.categoryItemCreatedDate

        if (!item.isHideReadLayout) {
            if (item.categoryItemReadCount != "") {
                categoryItemDate?.visibility = View.VISIBLE
                constraintLayout7ReadCount?.visibility = View.VISIBLE
                categoryItemReadCount?.text =
                    calculateViewCount(item.categoryItemReadCount)
            } else {
                constraintLayout7ReadCount?.visibility = View.INVISIBLE
                categoryItemDate?.visibility = View.INVISIBLE
            }
        } else {
            constraintLayout7ReadCount?.visibility = View.INVISIBLE
            categoryItemDate?.visibility = View.INVISIBLE
        }
    }

    private fun setDarkModeTheme(context: Context) {
        val sharedPref =
            context.getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        val theme = sharedPref.getString("mode", "default")!!

        if (theme == "dark") {
            categoryItemTitle?.setTextColor(Color.parseColor("#FFFFFF"))
            categoryItemSubTitle?.setTextColor(Color.parseColor("#FFFFFF"))
            categoryItemReadCount?.setTextColor(Color.parseColor("#FFFFFF"))
            categoryItemDate?.setTextColor(Color.parseColor("#FFFFFF"))
            categoryFeedViewLine?.setBackgroundColor(Color.parseColor("#0e1720"))

            constraintLayout7ReadCount?.background =
                ContextCompat.getDrawable(context, R.drawable.bg_category_item_read_count_dark_mode)
        } else {
            categoryItemTitle?.setTextColor(Color.parseColor("#231f20"))
            categoryItemSubTitle?.setTextColor(Color.parseColor("#191919"))
            categoryItemReadCount?.setTextColor(Color.parseColor("#191919"))
            categoryItemDate?.setTextColor(Color.parseColor("#191919"))
            categoryFeedViewLine?.setBackgroundColor(Color.parseColor("#e2e2e2"))

            constraintLayout7ReadCount?.background =
                ContextCompat.getDrawable(context, R.drawable.bg_category_item_read_count)
        }
    }

    private fun setTextViewsTypeFace(context: Context) {
        categoryItemTitle?.typeface = OnedioCommon.changeTypeFace4ActivityBold(context)
        categoryItemSubTitle?.typeface = OnedioCommon.changeTypeFace4Activity(context)
        categoryItemReadCount?.typeface = OnedioCommon.changeTypeFace4ActivityBold(context)
        categoryItemDate?.typeface = OnedioCommon.changeTypeFace4Activity(context)
        infoText?.typeface = OnedioCommon.changeTypeFace4Activity(context)
    }

    private fun setViewsClicked(
        item: CategoryAdapterModel,
        listener: (CategoryAdapterModel, String) -> Unit
    ) {
        categoryItemConstraint?.setOnClickListener {
            listener(item, "ARTICLE")
        }

        categoryItemTitle?.setOnClickListener {
            listener(item, "ARTICLE")
        }

        categoryItemSubTitle?.setOnClickListener {
            listener(item, "ARTICLE")
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
                    categoryItemPhotoProgress?.visibility = View.GONE
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
                    categoryItemPhotoProgress?.visibility = View.GONE
                    return false
                }
            })
            .into(imageView)
    }

    private fun calculateViewCount(strOfViewCount: String): String =
        when (strOfViewCount.length) {
            in 4..6 -> strOfViewCount.substring(
                0,
                strOfViewCount.length - 3
            ) + "b"
            in 7..9 -> strOfViewCount.substring(
                0,
                strOfViewCount.length - 6
            ) + "m"
            else -> strOfViewCount
        }
}