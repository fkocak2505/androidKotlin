package com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.holder

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
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import io.noties.markwon.Markwon
import com.onedio.androidside.R
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.entries.EntriesModelData

class HolderEntriesEmbedHtml(itemView: View) : RecyclerView.ViewHolder(itemView)  {

    private var embedHtmlEntriesTitle: TextView? =
        itemView.findViewById(R.id.embedHtmlEntriesTitle) as TextView

    private var embedHtmlEntriesWebView: WebView? =
        itemView.findViewById(R.id.embedHtmlEntriesWebView) as WebView

    private var embedHtmlEntriesContent: TextView? =
        itemView.findViewById(R.id.embedHtmlEntriesContent) as TextView

    private var embedHtmlEntriesSource: TextView? =
        itemView.findViewById(R.id.embedHtmlEntriesSource) as TextView

    private var holderEntriesImageConstraint: ConstraintLayout? =
        itemView.findViewById(R.id.holderEntriesImageConstraint) as ConstraintLayout





    fun bindItems(
        context: Context,
        item: EntriesModelData,
        pos: Int,
        listener: (EntriesModelData) -> Unit
    ) {

        ///// Data Set Image
        item.entryTitle?.let {
            if (item.entryTitle != "") {
                embedHtmlEntriesTitle?.visibility = View.VISIBLE
                //imageEntriesTitle?.text = item.data.title
                Markwon.create(context).setMarkdown(embedHtmlEntriesTitle!!, item.entryTitle!!)
            }
        } ?: run {
            embedHtmlEntriesTitle?.visibility = View.GONE
        }

        item.entryContent?.let {
            if (item.entryContent != "") {
                embedHtmlEntriesContent?.visibility = View.VISIBLE
                //imageEntriesTitle?.text = item.data.title
                Markwon.create(context).setMarkdown(embedHtmlEntriesContent!!, item.entryContent)
            }
        } ?: run {
            embedHtmlEntriesContent?.visibility = View.GONE
        }

        if (item.source != "") {
            embedHtmlEntriesSource?.visibility = View.VISIBLE
            Markwon.create(context).setMarkdown(embedHtmlEntriesSource!!, item.source)
            //textEntriesContent?.text = item.data.content
        }else{
            embedHtmlEntriesSource?.visibility = View.GONE
        }





        val sharedPref =
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
        }

        initWebView4Video(context, item, theme)

        /*itemView.setOnClickListener {
            listener(pos)
        }*/

    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView4Video(context: Context, item: EntriesModelData, theme: String) {

        embedHtmlEntriesWebView?.visibility = View.VISIBLE
        //embedHtmlEntriesWebView?.clearCache(true)
        val webSettings = embedHtmlEntriesWebView?.settings
        webSettings?.javaScriptEnabled = true
        webSettings?.domStorageEnabled = true
        webSettings?.setAppCacheEnabled(true)
        webSettings?.setAppCachePath(context.filesDir.absolutePath + "/cache");
        webSettings?.databaseEnabled = true;
        webSettings?.databasePath = context.filesDir.absolutePath + "/databases"

        item.htmlData?.let {
            if(item.htmlData.substring(0, 3).contains("<bl")){

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

                if(theme == "dark"){
                    val sEmbedData = item.htmlData.split("<blockquote").toMutableList()
                    val darkModeTwitEmbed = "<blockquote data-theme='dark' " + sEmbedData[1]

                    embedHtmlEntriesWebView?.loadDataWithBaseURL("https://twitter.com", darkModeTwitEmbed, "text/html", "UTF-8", null)

                }else
                    embedHtmlEntriesWebView?.loadDataWithBaseURL("https://twitter.com", item.htmlData, "text/html", "UTF-8", null)


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

    private fun changeIFrameWidthValue(item: EntriesModelData): String {

        item.htmlData?.let {
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