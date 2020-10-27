package com.weatherlogger.webviewlib

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import im.delight.android.webview.AdvancedWebView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AdvancedWebView.Listener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        webview.setListener(this, this)
        webview.loadUrl("https://onedio.com/mobile/5e9460678cbf9ee12e0e4c2a/appview2")



    }


    override fun onPageFinished(url: String?) {

    }

    override fun onPageError(errorCode: Int, description: String?, failingUrl: String?) {

    }

    override fun onDownloadRequested(
        url: String?,
        suggestedFilename: String?,
        mimeType: String?,
        contentLength: Long,
        contentDisposition: String?,
        userAgent: String?
    ) {

    }

    override fun onExternalPageRequest(url: String?) {

    }

    override fun onPageStarted(url: String?, favicon: Bitmap?) {

    }


}
