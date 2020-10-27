package com.example.recyclerviewwithwebview

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.webkit.WebView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HolderEntriesVideo(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var videoEntriesTitle: TextView? =
        itemView.findViewById(R.id.videoEntriesTitle) as TextView

    private var videoEntriesVideo: WebView? =
        itemView.findViewById(R.id.videoEntriesVideo) as WebView

    private var videoEntriesContent: TextView? =
        itemView.findViewById(R.id.videoEntriesContent) as TextView


    fun bindItems(
        context: Context,
        item: EntriesModel,
        pos: Int,
        listener: (Int) -> Unit
    ) {

        ///// Data Set Image
        item.data.title?.let {
            if (item.data.title != "") {
                videoEntriesTitle?.visibility = View.VISIBLE
                videoEntriesTitle?.text = item.data.title
            }
        } ?: run {
            videoEntriesTitle?.visibility = View.GONE
        }

        item.data.content?.let {
            if (item.data.content != "") {
                videoEntriesContent?.visibility = View.VISIBLE
                videoEntriesContent?.text = item.data.title
            }
        } ?: run {
            videoEntriesContent?.visibility = View.GONE
        }

        initWebView4Video(item)

        itemView.setOnClickListener {
            listener(pos)
        }

    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView4Video(item: EntriesModel) {

        val newIFrame = changeIFrameWidthValue(item)

        if (newIFrame != "") {
            videoEntriesVideo?.visibility = View.VISIBLE

            val webSettings = videoEntriesVideo?.settings
            webSettings?.javaScriptEnabled = true
            videoEntriesVideo?.loadData(newIFrame, "text/html", "utf-8")
        } else
            videoEntriesVideo?.visibility = View.GONE

    }

    private fun changeIFrameWidthValue(item: EntriesModel): String {

        item.data.html?.let {
            var iFrame = item.data.html
            var height = 360

            iFrame = Regex("width=\"([0-9]{1,4})\"").replace(iFrame!!, "width='100%'")
            iFrame = Regex("height=\"([0-9]{1,4})\"").replace(
                iFrame,
                "height='$height'"
            )
            return iFrame

        } ?: run {
            return ""
        }
    }


}