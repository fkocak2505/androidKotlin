package com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.video.adapter.holder

import android.content.Context
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import cn.jzvd.Jzvd
import cn.jzvd.JzvdStd
import com.onedio.androidside.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import io.noties.markwon.Markwon
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.food.holder.CategoryItem4Food
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.video.adapter.model.VideoAdapterModel

class HolderVideoOnedio(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var jzVideoOnedio: JzvdStd? =
        itemView.findViewById(R.id.jzVideo) as JzvdStd

    private var videoOnedioReadCount: TextView? =
        itemView.findViewById(R.id.videoOnedioReadCount) as TextView

    private var videoOnedioReadCountInfoText: TextView? =
        itemView.findViewById(R.id.videoOnedioReadCountInfoText) as TextView

    private var videoOnedioDate: TextView? =
        itemView.findViewById(R.id.videoOnedioDate) as TextView

    private var videoOnedioTitle: TextView? =
        itemView.findViewById(R.id.videoOnedioTitle) as TextView

    private var lastLine: View? =
        itemView.findViewById(R.id.lastLine) as View

    private var readCountConstraint: ConstraintLayout? =
        itemView.findViewById(R.id.constraintLayout7) as ConstraintLayout

    private var videoOnedioTitleFullWidth: TextView? =
        itemView.findViewById(R.id.videoOnedioTitleFullWidth) as TextView




    fun bindItems(
        context: Context,
        item: VideoAdapterModel,
        listener: (VideoAdapterModel, String) -> Unit,
        pos: Int
    ) {
        ////// Data Set Text
        videoOnedioDate?.text = item.categoryItemCreatedDate


        if(!item.isHideCountLayout){
            if(item.categoryItemReadCount != ""){
                Markwon.create(context).setMarkdown(videoOnedioTitle!!, item.categoryItemTitle)
                videoOnedioTitle?.visibility = View.VISIBLE
                videoOnedioTitleFullWidth?.visibility = View.INVISIBLE
                readCountConstraint?.visibility = View.VISIBLE
                videoOnedioDate?.visibility = View.VISIBLE
                videoOnedioReadCount?.text = calculateViewCount(item.categoryItemReadCount)
            }else{
                Markwon.create(context).setMarkdown(videoOnedioTitleFullWidth!!, item.categoryItemTitle)
                videoOnedioTitle?.visibility = View.INVISIBLE
                videoOnedioTitleFullWidth?.visibility = View.VISIBLE
                readCountConstraint?.visibility = View.INVISIBLE
                videoOnedioDate?.visibility = View.INVISIBLE
            }
        }else{
            Markwon.create(context).setMarkdown(videoOnedioTitleFullWidth!!, item.categoryItemTitle)
            videoOnedioTitle?.visibility = View.INVISIBLE
            videoOnedioTitleFullWidth?.visibility = View.VISIBLE
            readCountConstraint?.visibility = View.INVISIBLE
            videoOnedioDate?.visibility = View.INVISIBLE
        }


        Jzvd.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        Jzvd.NORMAL_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        jzVideoOnedio?.setUp(
            item.categoryItemVideoLink,
            "",
            JzvdStd.SCREEN_WINDOW_NORMAL
        )

        loadImageWithoutProgress(
            item.categoryItemPhoto,
            jzVideoOnedio?.thumbImageView!!
        )

        val sharedPref =
            context.getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        val theme = sharedPref.getString("mode", "default")!!

        if (theme == "dark") {
            videoOnedioReadCount?.setTextColor(Color.parseColor("#FFFFFF"))
            videoOnedioDate?.setTextColor(Color.parseColor("#FFFFFF"))
            videoOnedioTitle?.setTextColor(Color.parseColor("#FFFFFF"))
            videoOnedioTitleFullWidth?.setTextColor(Color.parseColor("#FFFFFF"))
            videoOnedioReadCountInfoText?.setTextColor(Color.parseColor("#FFFFFF"))

            readCountConstraint?.background = ContextCompat.getDrawable(context, R.drawable.bg_category_item_read_count_dark_mode)
            lastLine?.setBackgroundColor(Color.parseColor("#0e1720"))

        } else {
            videoOnedioReadCount?.setTextColor(Color.parseColor("#231f20"))
            videoOnedioDate?.setTextColor(Color.parseColor("#191919"))
            videoOnedioTitle?.setTextColor(Color.parseColor("#191919"))
            videoOnedioTitleFullWidth?.setTextColor(Color.parseColor("#191919"))
            videoOnedioReadCountInfoText?.setTextColor(Color.parseColor("#191919"))

            readCountConstraint?.background = ContextCompat.getDrawable(context, R.drawable.bg_category_item_read_count)
            lastLine?.setBackgroundColor(Color.parseColor("#e2e2e2"))
        }

        //// Text TypeFace
        videoOnedioTitle?.typeface = OnedioCommon.changeTypeFace4ActivitySemiBold(context)
        videoOnedioTitleFullWidth?.typeface = OnedioCommon.changeTypeFace4ActivitySemiBold(context)
        videoOnedioReadCount?.typeface = OnedioCommon.changeTypeFace4ActivitySemiBold(context)
        videoOnedioDate?.typeface = OnedioCommon.changeTypeFace4ActivitySemiBold(context)
        videoOnedioReadCountInfoText?.typeface =
            OnedioCommon.changeTypeFace4ActivitySemiBold(context)

        itemView.setOnClickListener {
            listener(item, "ONEDIO")
        }

        videoOnedioTitle?.setOnClickListener {
            listener(item, "ONEDIO")
        }

        videoOnedioTitleFullWidth?.setOnClickListener {
            listener(item, "ONEDIO")
        }
    }

    private fun loadImageWithoutProgress(imageUrl: String, imageView: ImageView) {
        Picasso.get().load(imageUrl)
            .into(imageView, object : Callback {
                override fun onSuccess() {}

                override fun onError(e: Exception?) {
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

}