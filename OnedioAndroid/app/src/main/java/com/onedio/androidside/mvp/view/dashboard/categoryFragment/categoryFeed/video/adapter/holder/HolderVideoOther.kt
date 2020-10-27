package com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.video.adapter.holder

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
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.food.holder.CategoryItem4Food
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.video.adapter.model.VideoAdapterModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import io.noties.markwon.Markwon

class HolderVideoOther(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var videoOther: ImageView? =
        itemView.findViewById(R.id.videoOther) as ImageView

    private var videoOtherPhotoProgress: ProgressBar? =
        itemView.findViewById(R.id.videoOtherPhotoProgress) as ProgressBar

    private var videoOtherReadCount: TextView? =
        itemView.findViewById(R.id.videoOtherReadCount) as TextView

    private var videoOtherReadCountInfoText: TextView? =
        itemView.findViewById(R.id.videoOtherReadCountInfoText) as TextView

    private var videoOtherDate: TextView? =
        itemView.findViewById(R.id.videoOtherDate) as TextView

    private var videoOtherTitle: TextView? =
        itemView.findViewById(R.id.videoOtherTitle) as TextView

    private var readCountConstraint: ConstraintLayout? =
        itemView.findViewById(R.id.constraintLayout7) as ConstraintLayout

    private var lastLine: View? =
        itemView.findViewById(R.id.lastLine) as View

    private var videoOtherTitleFullWidth: TextView? =
        itemView.findViewById(R.id.videoOtherTitleFullWidth) as TextView




    fun bindItems(
        context: Context,
        item: VideoAdapterModel,
        listener: (VideoAdapterModel, String) -> Unit,
        pos: Int
    ) {
        ////// Data Set Text

        //videoOtherReadCount?.text = calculateViewCount(item.categoryItemReadCount)
        videoOtherDate?.text = item.categoryItemCreatedDate

        if (!item.isHideCountLayout) {
            if (item.categoryItemReadCount != "") {
                Markwon.create(context).setMarkdown(videoOtherTitle!!, item.categoryItemTitle)
                videoOtherTitle?.visibility = View.VISIBLE
                videoOtherTitleFullWidth?.visibility = View.INVISIBLE
                readCountConstraint?.visibility = View.VISIBLE
                videoOtherDate?.visibility = View.VISIBLE
                videoOtherReadCount?.text = calculateViewCount(item.categoryItemReadCount)
            } else {
                Markwon.create(context).setMarkdown(videoOtherTitleFullWidth!!, item.categoryItemTitle)
                videoOtherTitle?.visibility = View.INVISIBLE
                videoOtherTitleFullWidth?.visibility = View.VISIBLE
                readCountConstraint?.visibility = View.INVISIBLE
                videoOtherDate?.visibility = View.INVISIBLE
            }
        } else {
            Markwon.create(context).setMarkdown(videoOtherTitleFullWidth!!, item.categoryItemTitle)
            videoOtherTitle?.visibility = View.INVISIBLE
            videoOtherTitleFullWidth?.visibility = View.VISIBLE
            readCountConstraint?.visibility = View.INVISIBLE
            videoOtherDate?.visibility = View.INVISIBLE
        }


        loadImageWithoutProgress(
            item.categoryItemPhoto,
            videoOther!!,
            videoOtherPhotoProgress!!
        )

        val sharedPref =
            context.getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        val theme = sharedPref.getString("mode", "default")!!

        if (theme == "dark") {
            videoOtherReadCount?.setTextColor(Color.parseColor("#FFFFFF"))
            videoOtherDate?.setTextColor(Color.parseColor("#FFFFFF"))
            videoOtherTitle?.setTextColor(Color.parseColor("#FFFFFF"))
            videoOtherReadCountInfoText?.setTextColor(Color.parseColor("#FFFFFF"))

            readCountConstraint?.background =
                ContextCompat.getDrawable(context, R.drawable.bg_category_item_read_count_dark_mode)
            lastLine?.setBackgroundColor(Color.parseColor("#0e1720"))

        } else {
            videoOtherReadCount?.setTextColor(Color.parseColor("#231f20"))
            videoOtherDate?.setTextColor(Color.parseColor("#191919"))
            videoOtherTitle?.setTextColor(Color.parseColor("#191919"))
            videoOtherReadCountInfoText?.setTextColor(Color.parseColor("#191919"))

            readCountConstraint?.background =
                ContextCompat.getDrawable(context, R.drawable.bg_category_item_read_count)
            lastLine?.setBackgroundColor(Color.parseColor("#e2e2e2"))
        }

        //// Text TypeFace
        videoOtherTitle?.typeface = OnedioCommon.changeTypeFace4ActivitySemiBold(context)
        videoOtherReadCount?.typeface = OnedioCommon.changeTypeFace4ActivitySemiBold(context)
        videoOtherDate?.typeface = OnedioCommon.changeTypeFace4ActivitySemiBold(context)
        videoOtherReadCountInfoText?.typeface =
            OnedioCommon.changeTypeFace4ActivitySemiBold(context)

        itemView.setOnClickListener {
            listener(item, "OTHER")
        }

        videoOtherTitle?.setOnClickListener {
            listener(item, "OTHER")
        }
    }

    private fun loadImageWithoutProgress(
        imageUrl: String,
        imageView: ImageView,
        progressBar: ProgressBar
    ) {
        Picasso.get().load(imageUrl)
            .into(imageView, object : Callback {
                override fun onSuccess() {
                    progressBar.visibility = View.INVISIBLE
                }

                override fun onError(e: Exception?) {
                    imageView.setImageResource(R.drawable.image_error_dark_mode)
                    progressBar.visibility = View.INVISIBLE
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

}