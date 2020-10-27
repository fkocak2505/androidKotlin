package com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.optimizedArticleDetail.adapter.holder.entryAdapter.holder

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.onedio.androidside.R
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.newResponse.ArticleFeedDetailsEntryModel
import com.squareup.picasso.Callback
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import io.noties.markwon.Markwon
import java.text.SimpleDateFormat

class HolderOnedioEmbedEntries(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var onedioEmbedEntriesTitle: TextView? =
        itemView.findViewById(R.id.onedioEmbedEntriesTitle) as TextView

    private var onedioEmbedPhoto: ImageView? =
        itemView.findViewById(R.id.onedioEmbedPhoto) as ImageView

    private var onedioEmbedTitle: TextView? =
        itemView.findViewById(R.id.onedioEmbedTitle) as TextView

    private var onedioEmbedReadCount: TextView? =
        itemView.findViewById(R.id.onedioEmbedReadCount) as TextView

    private var onedioEmbedInfoText: TextView? =
        itemView.findViewById(R.id.onedioEmbedInfoText) as TextView

    private var onedioEmbedDate: TextView? =
        itemView.findViewById(R.id.onedioEmbedDate) as TextView

    private var onedioEmbedEntriesContent: TextView? =
        itemView.findViewById(R.id.onedioEmbedEntriesContent) as TextView

    private var onedioEmbedConstraintRoot: ConstraintLayout? =
        itemView.findViewById(R.id.onedioEmbedConstraintRoot) as ConstraintLayout

    private var onedioEmbedConstraint: ConstraintLayout? =
        itemView.findViewById(R.id.onedioEmbedConstraint) as ConstraintLayout

    private var onedioEmbedEntriesSource: TextView? =
        itemView.findViewById(R.id.onedioEmbedEntriesSource) as TextView

    private var constraintLayoutOnedioEmbed: ConstraintLayout? =
        itemView.findViewById(R.id.constraintLayoutOnedioEmbed) as ConstraintLayout

    private var onedioEmbedPhotoProgress: ProgressBar? =
        itemView.findViewById(R.id.onedioEmbedPhotoProgress) as ProgressBar


    fun bindItems(
        context: Context,
        item: ArticleFeedDetailsEntryModel,
        pos: Int,
        listener: (Int, ArticleFeedDetailsEntryModel, String) -> Unit
    ) {

        setTextViewsData(context, item)

        setImageViewsData(context, item)

        setViewsClick(pos, item, listener)

        setDarkModeTheme(context)
    }

    private fun setTextViewsData(context: Context, item: ArticleFeedDetailsEntryModel) {
        item.title?.let {
            if (item.title != "") {
                onedioEmbedEntriesTitle?.visibility = View.VISIBLE
                Markwon.create(context)
                    .setMarkdown(onedioEmbedEntriesTitle!!, it)
                //textEntriesTitle?.text = item.data.title
            }
        } ?: run {
            onedioEmbedEntriesTitle?.visibility = View.GONE
        }

        item.content?.let {
            if (item.content != "") {
                onedioEmbedEntriesContent?.visibility = View.VISIBLE
                Markwon.create(context)
                    .setMarkdown(onedioEmbedEntriesContent!!, it)
                //textEntriesTitle?.text = item.data.title
            }
        } ?: run {
            onedioEmbedEntriesContent?.visibility = View.GONE
        }

        item.embedArticle?.let {
            it.title?.let {
                if (it != "") {
                    onedioEmbedTitle?.visibility = View.VISIBLE
                    Markwon.create(context)
                        .setMarkdown(onedioEmbedTitle!!, it)
                    //textEntriesTitle?.text = item.data.title
                }
            } ?: run {
                onedioEmbedTitle?.visibility = View.GONE
            }
        } ?: run {
            onedioEmbedTitle?.visibility = View.GONE
        }

        item.urls?.let {
            it.source?.let {
                if (it != "") {
                    onedioEmbedEntriesSource?.visibility = View.GONE
                    Markwon.create(context).setMarkdown(onedioEmbedEntriesSource!!, it)
                }
            } ?: run {
                onedioEmbedEntriesSource?.visibility = View.GONE
            }
        } ?: run {
            onedioEmbedEntriesSource?.visibility = View.GONE
        }


        item.embedArticle?.let {
            it.createDate?.let {
                onedioEmbedDate?.text = convertFeedDate(it)
            } ?: run {
                onedioEmbedDate?.text = ""
            }

            it.stats?.let {
                it.viewsTotal?.let {
                    constraintLayoutOnedioEmbed?.visibility = View.VISIBLE
                    onedioEmbedDate?.visibility = View.VISIBLE
                    onedioEmbedReadCount?.text = calculateViewCount(it.toString())
                } ?: run {
                    constraintLayoutOnedioEmbed?.visibility = View.INVISIBLE
                    onedioEmbedDate?.visibility = View.INVISIBLE
                }
            } ?: run {
                constraintLayoutOnedioEmbed?.visibility = View.INVISIBLE
                onedioEmbedDate?.visibility = View.INVISIBLE
            }

        } ?: run {
            constraintLayoutOnedioEmbed?.visibility = View.INVISIBLE
            onedioEmbedDate?.visibility = View.INVISIBLE
        }
    }

    private fun setImageViewsData(context: Context, item: ArticleFeedDetailsEntryModel) {
        loadImageWithoutProgress(
            item.embedArticle?.image?.alternates?.standardResolution?.url!!,
            onedioEmbedPhoto!!,
            onedioEmbedPhotoProgress!!
        )
    }

    private fun setViewsClick(
        pos: Int,
        item: ArticleFeedDetailsEntryModel,
        listener: (Int, ArticleFeedDetailsEntryModel, String) -> Unit
    ) {
        onedioEmbedConstraintRoot?.setOnClickListener {
            listener(pos, item, "ONEDIO_EMBED")
        }

        onedioEmbedTitle?.setOnClickListener {
            listener(pos, item, "ONEDIO_EMBED")
        }

    }

    private fun setDarkModeTheme(context: Context) {
        val sharedPref =
            context.getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        val theme = sharedPref.getString("mode", "default")!!

        if (theme == "dark") {
            onedioEmbedEntriesTitle?.setTextColor(Color.parseColor("#FFFFFF"))
            onedioEmbedEntriesContent?.setTextColor(Color.parseColor("#FFFFFF"))
            onedioEmbedTitle?.setTextColor(Color.parseColor("#FFFFFF"))
            onedioEmbedInfoText?.setTextColor(Color.parseColor("#FFFFFF"))
            onedioEmbedDate?.setTextColor(Color.parseColor("#FFFFFF"))
            onedioEmbedReadCount?.setTextColor(Color.parseColor("#FFFFFF"))
            onedioEmbedEntriesSource?.setTextColor(Color.parseColor("#FFFFFF"))
            onedioEmbedConstraint?.background =
                ContextCompat.getDrawable(context, R.drawable.layout_border_dark_mode)
            constraintLayoutOnedioEmbed?.background =
                ContextCompat.getDrawable(context, R.drawable.bg_category_item_read_count_dark_mode)
        } else {
            onedioEmbedEntriesTitle?.setTextColor(Color.parseColor("#191919"))
            onedioEmbedEntriesContent?.setTextColor(Color.parseColor("#191919"))
            onedioEmbedTitle?.setTextColor(Color.parseColor("#191919"))
            onedioEmbedInfoText?.setTextColor(Color.parseColor("#191919"))
            onedioEmbedDate?.setTextColor(Color.parseColor("#191919"))
            onedioEmbedReadCount?.setTextColor(Color.parseColor("#191919"))
            onedioEmbedEntriesSource?.setTextColor(Color.parseColor("#191919"))

            onedioEmbedConstraint?.background =
                ContextCompat.getDrawable(context, R.drawable.layout_border)
            constraintLayoutOnedioEmbed?.background =
                ContextCompat.getDrawable(context, R.drawable.bg_category_item_read_count)
        }
    }

    private fun loadImageWithoutProgress(
        imageUrl: String,
        imageView: ImageView,
        progressBar: ProgressBar
    ) {
        Picasso.get().load(imageUrl)
            .networkPolicy(NetworkPolicy.NO_CACHE)
            .memoryPolicy(MemoryPolicy.NO_CACHE)
            .into(imageView, object : Callback {
                override fun onSuccess() {
                    progressBar.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    progressBar.visibility = View.GONE
                    imageView.setImageResource(R.drawable.image_error_dark_mode)
                }
            })
    }

    private fun calculateViewCount(strOfViewCount: String): String =
        when {
            strOfViewCount.length in 4..6 -> strOfViewCount.substring(
                0,
                strOfViewCount.length - 3
            ) + "b"
            strOfViewCount.length in 7..9 -> strOfViewCount.substring(
                0,
                strOfViewCount.length - 6
            ) + "m"
            else -> strOfViewCount
        }

    @SuppressLint("SimpleDateFormat")
    fun convertFeedDate(utcDate: String): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val outputFormat = SimpleDateFormat("dd/MM/yyyy")
        val date = sdf.parse(utcDate)
        return outputFormat.format(date!!)
    }

}