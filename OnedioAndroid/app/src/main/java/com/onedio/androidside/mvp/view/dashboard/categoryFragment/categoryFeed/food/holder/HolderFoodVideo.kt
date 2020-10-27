package com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.food.holder

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatDelegate
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.onedio.androidside.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.video.adapter.model.VideoAdapterModel

class HolderFoodVideo(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var videoType: ImageView? =
        itemView.findViewById(R.id.videoType) as ImageView

    private var foodCoverPhoto: ImageView? =
        itemView.findViewById(R.id.foodCoverPhoto) as ImageView

    private var foodCoverPhotoProgress: ProgressBar? =
        itemView.findViewById(R.id.foodCoverPhotoProgress) as ProgressBar

    private var foodVideoReadCount: TextView? =
        itemView.findViewById(R.id.foodVideoReadCount) as TextView

    private var foodVideoReadCountInfoText: TextView? =
        itemView.findViewById(R.id.foodVideoReadCountInfoText) as TextView

    private var foodVideoDate: TextView? =
        itemView.findViewById(R.id.foodVideoDate) as TextView

    private var foodVideoTitle: TextView? =
        itemView.findViewById(R.id.foodVideoTitle) as TextView

    private var readCountConstraint: ConstraintLayout? =
        itemView.findViewById(R.id.constraintLayout7) as ConstraintLayout

    private var lastLine: View? =
        itemView.findViewById(R.id.lastLine) as View

    private var foodVideoTitleFullWidth: TextView? =
        itemView.findViewById(R.id.foodVideoTitleFullWidth) as TextView




    fun bindItems(
        context: Context,
        item: VideoAdapterModel,
        listener: (VideoAdapterModel, String) -> Unit,
        pos: Int
    ) {
        ///// Data Set Image

        foodVideoDate?.text = item.categoryItemCreatedDate

        if(!item.isHideCountLayout){
            if (item.categoryItemReadCount != "") {
                foodVideoTitle?.text = item.categoryItemTitle
                foodVideoTitle?.visibility = View.VISIBLE
                foodVideoTitleFullWidth?.visibility = View.INVISIBLE
                readCountConstraint?.visibility = View.VISIBLE
                foodVideoDate?.visibility = View.VISIBLE
                foodVideoReadCount?.text = calculateViewCount(item.categoryItemReadCount)
            } else {
                foodVideoTitleFullWidth?.text = item.categoryItemTitle
                foodVideoTitle?.visibility = View.INVISIBLE
                foodVideoTitleFullWidth?.visibility = View.VISIBLE
                readCountConstraint?.visibility = View.INVISIBLE
                foodVideoDate?.visibility = View.INVISIBLE
            }
        }else{
            foodVideoTitleFullWidth?.text = item.categoryItemTitle
            foodVideoTitle?.visibility = View.INVISIBLE
            foodVideoTitleFullWidth?.visibility = View.VISIBLE
            readCountConstraint?.visibility = View.INVISIBLE
            foodVideoDate?.visibility = View.INVISIBLE
        }



        loadImageWithProgress(
            item.categoryItemVideoLink,
            item.categoryItemPhoto,
            foodCoverPhoto!!,
            foodCoverPhotoProgress!!
        )

        val sharedPref =
            context.getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        val theme = sharedPref.getString("mode", "default")!!
        if (theme == "dark") {
            foodVideoReadCount?.setTextColor(Color.parseColor("#e6e6e6"))
            foodVideoDate?.setTextColor(Color.parseColor("#e6e6e6"))
            foodVideoTitle?.setTextColor(Color.parseColor("#e6e6e6"))
            foodVideoReadCountInfoText?.setTextColor(Color.parseColor("#e6e6e6"))

            readCountConstraint?.background = ContextCompat.getDrawable(context, R.drawable.bg_category_item_read_count_dark_mode)
            lastLine?.setBackgroundColor(Color.parseColor("#0e1720"))

        } else {
            foodVideoReadCount?.setTextColor(Color.parseColor("#231f20"))
            foodVideoDate?.setTextColor(Color.parseColor("#191919"))
            foodVideoTitle?.setTextColor(Color.parseColor("#191919"))
            foodVideoReadCountInfoText?.setTextColor(Color.parseColor("#191919"))

            readCountConstraint?.background = ContextCompat.getDrawable(context, R.drawable.bg_category_item_read_count)
            lastLine?.setBackgroundColor(Color.parseColor("#e2e2e2"))
        }

        //// Text TypeFace
        foodVideoTitle?.typeface = OnedioCommon.changeTypeFace4ActivitySemiBold(context)
        foodVideoReadCount?.typeface = OnedioCommon.changeTypeFace4ActivitySemiBold(context)
        foodVideoDate?.typeface = OnedioCommon.changeTypeFace4ActivitySemiBold(context)
        foodVideoReadCountInfoText?.typeface = OnedioCommon.changeTypeFace4ActivitySemiBold(context)

        itemView.setOnClickListener {
            listener(item, "FOOD_VIDEO")
        }

        /*videoType?.setOnClickListener {
            listener(item)
        }*/
    }

    private fun loadImageWithProgress(
        videoUrl: String,
        imageUrl: String,
        imageView: ImageView,
        progressBar: ProgressBar
    ) {
        Picasso.get().load(imageUrl)
            .into(imageView, object : Callback {
                override fun onSuccess() {
                    progressBar.visibility = View.GONE
                    videoType?.visibility = View.VISIBLE
                    if (videoUrl.contains("youtube"))
                        videoType?.setImageResource(R.drawable.ic_youtube)
                    else
                        videoType?.setImageResource(R.drawable.ic_video_black)
                }

                override fun onError(e: Exception?) {
                    imageView.setImageResource(R.drawable.image_error_dark_mode)
                    videoType?.visibility = View.GONE
                    progressBar.visibility = View.GONE
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