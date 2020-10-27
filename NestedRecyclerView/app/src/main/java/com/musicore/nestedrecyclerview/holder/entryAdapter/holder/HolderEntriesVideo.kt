package com.musicore.nestedrecyclerview.holder.entryAdapter.holder

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.net.http.SslError
import android.view.View
import android.view.ViewTreeObserver
import android.webkit.SslErrorHandler
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import cn.jzvd.JzvdStd
import com.musicore.nestedrecyclerview.R
import com.musicore.nestedrecyclerview.model.EntriesItem
import com.squareup.picasso.Callback
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import io.noties.markwon.Markwon


class HolderEntriesVideo(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var videoEntriesTitle: TextView? =
        itemView.findViewById(R.id.videoEntriesTitle) as TextView

    private var videoEntriesVideo: WebView? =
        itemView.findViewById(R.id.videoEntriesVideo) as WebView

    private var videoEntriesContent: TextView? =
        itemView.findViewById(R.id.videoEntriesContent) as TextView

    private var jzVideoOnedio: LinearLayout? =
        itemView.findViewById(R.id.jzVideoOnedio) as LinearLayout

    private var jzVideo: JzvdStd? =
        itemView.findViewById(R.id.jzVideo) as JzvdStd

    private var videoEntriesSource: TextView? =
        itemView.findViewById(R.id.videoEntriesSource) as TextView


    fun bindItems(
        context: Context,
        item: EntriesItem,
        pos: Int,
        listener: (Int, EntriesItem) -> Unit
    ) {

        setTextViewsData(context, item)

        setVideoViewsData(context, item)

        itemView.setOnClickListener {
            listener(pos, item)
        }


        /*val sharedPref =
            context.getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        val theme = sharedPref.getString("mode", "default")!!

        if(theme == "dark"){
            videoEntriesTitle?.setTextColor(Color.parseColor("#FFFFFF"))
            videoEntriesContent?.setTextColor(Color.parseColor("#FFFFFF"))
            videoEntriesSource?.setTextColor(Color.parseColor("#FFFFFF"))
        }else{
            videoEntriesTitle?.setTextColor(Color.parseColor("#191919"))
            videoEntriesContent?.setTextColor(Color.parseColor("#191919"))
            videoEntriesSource?.setTextColor(Color.parseColor("#191919"))
        }*/

        /*itemView.setOnClickListener {
            listener(pos)
        }*/

    }

    private fun setTextViewsData(context: Context, item: EntriesItem) {
        ///// Data Set Image
        item.title?.let {
            if (item.title != "") {
                videoEntriesTitle?.visibility = View.VISIBLE
                Markwon.create(context)
                    .setMarkdown(videoEntriesTitle!!, item.title)
                //textEntriesTitle?.text = item.data.title
            }
        } ?: run {
            videoEntriesTitle?.visibility = View.GONE
        }

        item.content?.let {
            if (item.content != "") {
                videoEntriesContent?.visibility = View.VISIBLE
                Markwon.create(context).setMarkdown(videoEntriesContent!!, item.content)
                //textEntriesContent?.text = item.data.content
            }
        } ?: run {
            videoEntriesContent?.visibility = View.GONE
        }

        item.urls?.let {
            it.source?.let {
                if (it != "") {
                    videoEntriesSource?.visibility = View.VISIBLE
                    Markwon.create(context).setMarkdown(videoEntriesSource!!, it)
                }
            } ?: run {
                videoEntriesSource?.visibility = View.GONE
            }
        } ?: run {
            videoEntriesSource?.visibility = View.GONE
        }
    }

    private fun setVideoViewsData(context: Context, item: EntriesItem) {
        item.embedSource?.let {
            if (item.embedSource.contains("onedio")) {
                videoEntriesVideo?.visibility = View.INVISIBLE
                jzVideoOnedio?.visibility = View.VISIBLE

                /*Jzvd.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                Jzvd.NORMAL_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT*/


                setImageViewsData(context, item)
                setUpVideoView(context, item)

            } else {
                jzVideoOnedio?.visibility = View.INVISIBLE
                videoEntriesVideo?.visibility = View.VISIBLE
                initWebView4Video(context, item)
            }
        } ?: run {
            item.html?.let {
                jzVideoOnedio?.visibility = View.INVISIBLE
                videoEntriesVideo?.visibility = View.VISIBLE
                initWebView4Video(context, item)
            } ?: run {
                videoEntriesVideo?.visibility = View.INVISIBLE
                jzVideoOnedio?.visibility = View.INVISIBLE
            }
        }

    }

    private fun setImageViewsData(context: Context, item: EntriesItem) {
        item.image?.let {
            it.alternates?.let {
                it.standardResolution?.let {
                    it.url?.let {
                        if (it != "") {
                            Picasso.get().load(it)
                                .networkPolicy(NetworkPolicy.NO_CACHE)
                                .memoryPolicy(MemoryPolicy.NO_CACHE)
                                .into(jzVideo?.thumbImageView, object : Callback {
                                    override fun onSuccess() {

                                    }

                                    override fun onError(e: Exception?) {

                                    }
                                })
                        }
                    } ?: run {

                    }
                } ?: run {

                }
            } ?: run {

            }
        } ?: run {

        }
    }

    private fun setUpVideoView(context: Context, item: EntriesItem) {
        var internalDataFile: String? = null
        item.internaldata?.let {
            it.file?.let {
                internalDataFile = it
            } ?: run {
                internalDataFile = ""
            }
        } ?: run {
            internalDataFile = ""
        }

        jzVideo?.setUp(
            internalDataFile,
            "",
            JzvdStd.SCREEN_WINDOW_NORMAL
        )
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView4Video(context: Context, item: EntriesItem) {

        //val newIFrame = changeIFrameWidthValue(item)

        videoEntriesVideo?.clearCache(true)

        //if (newIFrame != "") {
        videoEntriesVideo?.visibility = View.VISIBLE

        val webSettings = videoEntriesVideo?.settings
        webSettings?.javaScriptEnabled = true
        webSettings?.allowContentAccess = true
        webSettings?.domStorageEnabled = true


        videoEntriesVideo?.webViewClient = object : WebViewClient() {
            override fun onReceivedSslError(
                view: WebView?,
                handler: SslErrorHandler?,
                error: SslError?
            ) {
                handler?.let {
                    Toast.makeText(
                        context,
                        "Video yüklenirken bir sıkıntı oldu..",
                        Toast.LENGTH_SHORT
                    ).show()
                    handler.cancel()
                }
                super.onReceivedSslError(view, handler, error)
            }
        }

        videoEntriesVideo?.loadData(item.html, "text/html", "utf-8")

        val viewTreeObserver = videoEntriesVideo?.viewTreeObserver!!

        viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                val height = videoEntriesVideo?.measuredHeight
                if (height != 0) {
                    videoEntriesVideo?.viewTreeObserver?.removeOnPreDrawListener(this)
                    var newIFrame = changeIFrameWidthValue(pxToDp(height!!), item)
                    videoEntriesVideo?.loadData(newIFrame, "text/html", "utf-8")
                }
                return false
            }
        })


        /*} else
            videoEntriesVideo?.visibility = View.GONE*/

    }

    private fun changeIFrameWidthValue(dpVal: Int, item: EntriesItem): String {

        item.html?.let {
            var iFrame = item.html

            iFrame = Regex("width=\"([^\"]+)\" height=\"([^\"]+)\"").replace(
                iFrame,
                "width='100%' height='$dpVal'"
            )

            return iFrame


            /*item.data.internalData?.height?.let {
                iFrame = Regex("width=\"([0-9]{1,4})\"").replace(iFrame!!, "width='100%'")
                iFrame = Regex("height=\"([0-9]{1,4})\"").replace(
                    iFrame!!,
                    "height='220'"
                )

                return iFrame!!

            } ?: run {
                iFrame = Regex("width=\"([0-9]{1,4})\"").replace(iFrame!!, "width='100%'")
                iFrame = Regex("height=\"([0-9]{1,4})\"").replace(iFrame!!, "height='220'")

                return iFrame!!
            }*/
        } ?: run {
            return ""
        }
    }

    private fun pxToDp(px: Int): Int {
        return (px / Resources.getSystem().displayMetrics.density).toInt()
    }


}