package com.musicore.nestedrecyclerview.holder.entryAdapter.holder

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
import com.musicore.nestedrecyclerview.R
import com.musicore.nestedrecyclerview.model.EntriesItem
import com.squareup.picasso.Callback
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import io.noties.markwon.Markwon
import java.text.SimpleDateFormat

class HolderEntriesOnedioEmbed(itemView: View) : RecyclerView.ViewHolder(itemView) {

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



    fun bindItems(
        context: Context,
        item: EntriesItem,
        pos: Int,
        listener: (Int, EntriesItem) -> Unit
    ) {

        setTextViewsData(context, item)

        setImageViewsData(context, item)

        setViewsClick(pos, item, listener)
    }

    private fun setTextViewsData(context: Context, item: EntriesItem){
        item.title?.let {
            if (item.title != "") {
                onedioEmbedEntriesTitle?.visibility = View.VISIBLE
                Markwon.create(context)
                    .setMarkdown(onedioEmbedEntriesTitle!!, item.title)
                //textEntriesTitle?.text = item.data.title
            }
        } ?: run {
            onedioEmbedEntriesTitle?.visibility = View.GONE
        }

        item.content?.let {
            if (item.content != "") {
                onedioEmbedEntriesContent?.visibility = View.VISIBLE
                Markwon.create(context)
                    .setMarkdown(onedioEmbedEntriesContent!!, item.content)
                //textEntriesTitle?.text = item.data.title
            }
        } ?: run {
            onedioEmbedEntriesContent?.visibility = View.GONE
        }

        item.embedArticle?.title?.let {
            if (item.embedArticle.title != "") {
                onedioEmbedTitle?.visibility = View.VISIBLE
                Markwon.create(context)
                    .setMarkdown(onedioEmbedTitle!!, item.embedArticle.title)
                //textEntriesTitle?.text = item.data.title
            }
        } ?: run {
            onedioEmbedTitle?.visibility = View.GONE
        }

        item.urls?.let {
            it.source?.let {
                if(it != ""){
                    onedioEmbedEntriesSource?.visibility = View.VISIBLE
                    Markwon.create(context).setMarkdown(onedioEmbedEntriesSource!!, it)
                }
            }?: run{
                onedioEmbedEntriesSource?.visibility = View.GONE
            }
        }?: run{
            onedioEmbedEntriesSource?.visibility = View.GONE
        }


        item.embedArticle?.let {
            it.createDate?.let {
                onedioEmbedDate?.text = convertFeedDate(it)
            }?: run{
                onedioEmbedDate?.text = ""
            }

            it.stats?.let {
                it.views?.let {
                    constraintLayoutOnedioEmbed?.visibility = View.VISIBLE
                    onedioEmbedDate?.visibility = View.VISIBLE
                    onedioEmbedReadCount?.text = calculateViewCount(it.toString())
                }?: run{
                    constraintLayoutOnedioEmbed?.visibility = View.INVISIBLE
                    onedioEmbedDate?.visibility = View.INVISIBLE
                }
            }?: run{
                constraintLayoutOnedioEmbed?.visibility = View.INVISIBLE
                onedioEmbedDate?.visibility = View.INVISIBLE
            }

        }?: run{
            constraintLayoutOnedioEmbed?.visibility = View.INVISIBLE
            onedioEmbedDate?.visibility = View.INVISIBLE
        }
    }

    private fun setImageViewsData(context: Context, item: EntriesItem){
        loadImageWithoutProgress(item.embedArticle?.image?.alternates?.standardResolution?.url!!, onedioEmbedPhoto!!)
    }

    private fun setViewsClick(pos: Int, item: EntriesItem, listener: (Int, EntriesItem) -> Unit){
        onedioEmbedConstraintRoot?.setOnClickListener {
            listener(pos, item)
        }

        onedioEmbedTitle?.setOnClickListener {
            listener(pos, item)
        }

    }

    private fun loadImageWithoutProgress(imageUrl: String, imageView: ImageView) {
        Picasso.get().load(imageUrl)
            .networkPolicy(NetworkPolicy.NO_CACHE)
            .memoryPolicy(MemoryPolicy.NO_CACHE)
            .into(imageView, object : Callback {
                override fun onSuccess() {
                    //progressBar.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    //progressBar.visibility = View.GONE
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