package com.musicore.nestedrecyclerview.holder.entryAdapter.holder

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.net.http.SslError
import android.view.View
import android.view.ViewTreeObserver
import android.webkit.SslErrorHandler
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.musicore.nestedrecyclerview.R
import com.musicore.nestedrecyclerview.model.EntriesItem
import io.noties.markwon.Markwon

class HolderEntriesEmbedHtml(itemView: View) : RecyclerView.ViewHolder(itemView)  {

    private var embedHtmlEntriesTitle: TextView? =
        itemView.findViewById(R.id.embedHtmlEntriesTitle) as TextView

    private var embedHtmlEntriesWebView: WebView? =
        itemView.findViewById(R.id.embedHtmlEntriesWebView) as WebView

    private var embedHtmlEntriesContent: TextView? =
        itemView.findViewById(R.id.embedHtmlEntriesContent) as TextView

    private var embedHtmlEntriesSource: TextView? =
        itemView.findViewById(R.id.embedHtmlEntriesSource) as TextView



    fun bindItems(
        context: Context,
        item: EntriesItem,
        pos: Int,
        listener: (Int, EntriesItem) -> Unit
    ) {

        setTextViewsData(context, item)
        ///// Data Set Image


        initWebView4Video(context, item)

        itemView.setOnClickListener {
            listener(pos, item)
        }



        /*val sharedPref =
            context.getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        val theme = sharedPref.getString("mode", "default")!!

        if(theme == "dark"){
            embedHtmlEntriesTitle?.setTextColor(Color.parseColor("#FFFFFF"))
            embedHtmlEntriesContent?.setTextColor(Color.parseColor("#FFFFFF"))
            embedHtmlEntriesSource?.setTextColor(Color.parseColor("#FFFFFF"))
        }else{
            embedHtmlEntriesTitle?.setTextColor(Color.parseColor("#191919"))
            embedHtmlEntriesContent?.setTextColor(Color.parseColor("#191919"))
            embedHtmlEntriesSource?.setTextColor(Color.parseColor("#191919"))
        }*/


        /*itemView.setOnClickListener {
            listener(pos)
        }*/

    }

    private fun setTextViewsData(context: Context, item: EntriesItem){
        item.title?.let {
            if (item.title != "") {
                embedHtmlEntriesTitle?.visibility = View.VISIBLE
                Markwon.create(context)
                    .setMarkdown(embedHtmlEntriesTitle!!, item.title)
                //textEntriesTitle?.text = item.data.title
            }
        } ?: run {
            embedHtmlEntriesTitle?.visibility = View.GONE
        }

        item.content?.let {
            if (item.content != "") {
                embedHtmlEntriesContent?.visibility = View.VISIBLE
                Markwon.create(context).setMarkdown(embedHtmlEntriesContent!!, item.content)
                //textEntriesContent?.text = item.data.content
            }
        } ?: run {
            embedHtmlEntriesContent?.visibility = View.GONE
        }

        item.urls?.let {
            it.source?.let {
                if(it != ""){
                    embedHtmlEntriesSource?.visibility = View.VISIBLE
                    Markwon.create(context).setMarkdown(embedHtmlEntriesSource!!, it)
                }
            }?: run{
                embedHtmlEntriesSource?.visibility = View.GONE
            }
        }?: run{
            embedHtmlEntriesSource?.visibility = View.GONE
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView4Video(context: Context, item: EntriesItem) {

        embedHtmlEntriesWebView?.visibility = View.VISIBLE
        //embedHtmlEntriesWebView?.clearCache(true)
        val webSettings = embedHtmlEntriesWebView?.settings
        webSettings?.javaScriptEnabled = true
        webSettings?.domStorageEnabled = true
        webSettings?.setAppCacheEnabled(true)
        webSettings?.setAppCachePath(context.filesDir.absolutePath + "/cache");
        webSettings?.databaseEnabled = true;
        webSettings?.databasePath = context.filesDir.absolutePath + "/databases";

        item.html?.let {
            if(item.html.substring(0, 3).contains("<bl")){

                embedHtmlEntriesWebView?.webViewClient = object : WebViewClient(){
                    override fun onReceivedSslError(
                        view: WebView?,
                        handler: SslErrorHandler?,
                        error: SslError?
                    ) {
                        handler?.let {
                            Toast.makeText(context, "Embedler yüklenirken bir sıkıntı oldu..", Toast.LENGTH_SHORT).show()
                            handler.cancel()
                        }
                        super.onReceivedSslError(view, handler, error)
                    }
                }

                embedHtmlEntriesWebView?.loadDataWithBaseURL("https://twitter.com", item.html, "text/html", "UTF-8", null)
            } else {
                val newIFrame = changeIFrameWidthValue(item)
                if (newIFrame != "") {

                    embedHtmlEntriesWebView?.webViewClient = object : WebViewClient(){
                        override fun onReceivedSslError(
                            view: WebView?,
                            handler: SslErrorHandler?,
                            error: SslError?
                        ) {
                            handler?.let {
                                Toast.makeText(context, "Embedler yüklenirken bir sıkıntı oldu..", Toast.LENGTH_SHORT).show()
                                handler.cancel()
                            }
                            super.onReceivedSslError(view, handler, error)
                        }
                    }

                    embedHtmlEntriesWebView?.loadData(newIFrame, "text/html", "utf-8")

                    val viewTreeObserver = embedHtmlEntriesWebView?.viewTreeObserver

                    viewTreeObserver?.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
                        override fun onPreDraw(): Boolean {
                            val height = embedHtmlEntriesWebView?.measuredHeight
                            if (height != 0) {
                                embedHtmlEntriesWebView?.viewTreeObserver?.removeOnPreDrawListener(this)
                                embedHtmlEntriesWebView?.loadData("<iframe class='instagram-embed' src='https://instagram.com/p/BPnFpbwBjDr/embed/captioned' width='100%' height='${pxToDp(
                                    height?.plus(100)!!
                                )}' frameborder='0'></iframe>", "text/html", "utf-8")
                            }
                            return false
                        }
                    })
                } else
                    embedHtmlEntriesWebView?.visibility = View.GONE
            }
        }?: run{
            embedHtmlEntriesWebView?.visibility = View.GONE
        }
    }

    private fun changeIFrameWidthValue(item: EntriesItem): String {

        item.html?.let {
            var iFrame = it
            return it

            /*item.data.internalData?.height?.let {
                iFrame = Regex("width=\"([0-9]{1,4})\"").replace(iFrame!!, "width='100%'")
                iFrame = Regex("height=\"([0-9]{1,4})\"").replace(
                    iFrame!!,
                    "height='360'"
                )

                return iFrame!!

            } ?: run {
                iFrame = Regex("width=\"([0-9]{1,4})\"").replace(iFrame!!, "width='100%'")
                iFrame = Regex("height=\"([0-9]{1,4})\"").replace(iFrame!!, "height='350'")

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