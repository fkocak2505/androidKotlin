package com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.food.holder

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.video.adapter.model.VideoAdapterModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import io.noties.markwon.Markwon

class HolderFoodText(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var categoryItemPhoto: ImageView? =
        itemView.findViewById(R.id.categoryItemPhoto) as ImageView

    private var categoryItemPhotoProgress: ProgressBar? =
        itemView.findViewById(R.id.categoryItemPhotoProgress) as ProgressBar

    private var categoryItemTitle: TextView? =
        itemView.findViewById(R.id.categoryItemTitle) as TextView

    private var categoryItemSubTitle: TextView? =
        itemView.findViewById(R.id.categoryItemSubTitle) as TextView

    private var categoryItemReadCount: TextView? =
        itemView.findViewById(R.id.categoryItemReadCount) as TextView

    private var infoText: TextView? =
        itemView.findViewById(R.id.infoText) as TextView

    private var categoryItemDate: TextView? =
        itemView.findViewById(R.id.categoryItemDate) as TextView

    private var readCountConstraint: ConstraintLayout? =
        itemView.findViewById(R.id.constraintLayout7) as ConstraintLayout

    private var lastLine: View? =
        itemView.findViewById(R.id.lastLine) as View


    fun bindItems(
        context: Context,
        item: VideoAdapterModel,
        listener: (VideoAdapterModel, String) -> Unit,
        pos: Int
    ) {
        ////// Data Set Text


        Markwon.create(context).setMarkdown(categoryItemTitle!!, item.categoryItemTitle)
        Markwon.create(context).setMarkdown(categoryItemSubTitle!!, item.categoryItemSubTitle)
        //categoryItemReadCount?.text = calculateViewCount(item.categoryItemReadCount)
        categoryItemDate?.text = item.categoryItemCreatedDate


        if(!item.isHideCountLayout){
            if (item.categoryItemReadCount != "") {
                readCountConstraint?.visibility = View.VISIBLE
                categoryItemDate?.visibility = View.VISIBLE
                categoryItemReadCount?.text = calculateViewCount(item.categoryItemReadCount)
            } else {
                readCountConstraint?.visibility = View.INVISIBLE
                categoryItemDate?.visibility = View.INVISIBLE
            }
        }else{
            readCountConstraint?.visibility = View.INVISIBLE
            categoryItemDate?.visibility = View.INVISIBLE
        }


        loadImageWithoutProgress(
            item.categoryItemPhoto,
            categoryItemPhoto!!,
            categoryItemPhotoProgress!!
        )

        val sharedPref =
            context.getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        val theme = sharedPref.getString("mode", "default")!!
        if (theme == "dark") {
            categoryItemReadCount?.setTextColor(Color.parseColor("#FFFFFF"))
            categoryItemDate?.setTextColor(Color.parseColor("#FFFFFF"))
            categoryItemTitle?.setTextColor(Color.parseColor("#FFFFFF"))
            infoText?.setTextColor(Color.parseColor("#FFFFFF"))

            readCountConstraint?.background = ContextCompat.getDrawable(context, R.drawable.bg_category_item_read_count_dark_mode)
            lastLine?.setBackgroundColor(Color.parseColor("#0e1720"))
        } else {
            categoryItemReadCount?.setTextColor(Color.parseColor("#231f20"))
            categoryItemDate?.setTextColor(Color.parseColor("#191919"))
            categoryItemTitle?.setTextColor(Color.parseColor("#191919"))
            infoText?.setTextColor(Color.parseColor("#191919"))

            readCountConstraint?.background = ContextCompat.getDrawable(context, R.drawable.bg_category_item_read_count)
            lastLine?.setBackgroundColor(Color.parseColor("#e2e2e2"))
        }

        //// Text TypeFace
        categoryItemTitle?.typeface = OnedioCommon.changeTypeFace4ActivitySemiBold(context)
        categoryItemSubTitle?.typeface = OnedioCommon.changeTypeFace4ActivitySemiBold(context)
        categoryItemReadCount?.typeface = OnedioCommon.changeTypeFace4ActivitySemiBold(context)
        categoryItemDate?.typeface = OnedioCommon.changeTypeFace4ActivitySemiBold(context)
        infoText?.typeface = OnedioCommon.changeTypeFace4ActivitySemiBold(context)

        itemView.setOnClickListener {
            listener(item, "FOOD_TEXT")
        }

        categoryItemTitle?.setOnClickListener {
            listener(item, "FOOD_TEXT")
        }

        categoryItemSubTitle?.setOnClickListener {
            listener(item, "FOOD_TEXT")
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