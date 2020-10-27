package com.weatherlogger.webview

import android.annotation.SuppressLint
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.util.DisplayMetrics
import android.view.ViewTreeObserver
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebViewClient
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private val aaa =
        "<iframe class='video-embed' src='https://www.youtube.com/embed/XZHNOdDKFIA' width='1280' height='720' frameborder='0'></iframe>"

    private val aaaa =
        "<iframe class='video-embed' src='https://www.youtube.com/embed/zhWZCgUuhQw' width='1280' height='720' frameborder='0'></iframe>"


    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val webSettings = webView?.settings!!
        webSettings.domStorageEnabled = true
        webSettings.javaScriptEnabled = true
        webView?.loadData(aaaa, "text/html", "utf-8")

        val viewTreeObserver = webView.viewTreeObserver

        viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                val height = webView.measuredHeight
                if (height != 0) {
                    Toast.makeText(applicationContext, "height:$height", Toast.LENGTH_SHORT).show()
                    webView.viewTreeObserver.removeOnPreDrawListener(this)

                    var newIFrame = changeIFrameWidthValue(pxToDp(height), aaaa)
                    webView?.loadData(newIFrame, "text/html", "utf-8")
                }
                return false
            }
        })

    }

    private fun changeIFrameWidthValue(dpVal: Int, aaa: String): String {

        var iFrame = aaa

        iFrame = Regex("width=\'([^\"]+)\' height=\'([^\"]+)\'").replace(iFrame, "width='100%' height='400'")

        return iFrame

    }


    private fun pxToDp(px: Int): Int {
        return (px / Resources.getSystem().displayMetrics.density).toInt()
    }
}
