package com.example.useraggreement

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.widget.Button


class CustomDialog(context: Context) : Dialog(context), View.OnClickListener {
    lateinit var btnClose: Button
    lateinit var webView : WebView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.user_agreement)
        webView = findViewById(R.id.webViewi)
        btnClose = findViewById(R.id.buton)

        btnClose.setOnClickListener(this)

        webView.loadUrl("https://static.onedio.com/html/kullanici-night.html")

    }

    override fun onClick(v: View) {
        when (v.getId()) {
            R.id.buton -> dismiss()
            else -> {
            }
        }
        dismiss()
    }
}