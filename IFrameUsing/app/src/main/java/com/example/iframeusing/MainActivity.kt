package com.example.iframeusing

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //var frameVideo = "<iframe class='video-embed' src='https://www.youtube.com/embed/yG--0AP4j_4' width='640' height='360' frameborder='0'></iframe>"
        var frameVideo = "<iframe data-id='onedio-player' src='https://onedio.com/player/5b56f76773fba0b61b61ecce/5b56f76773fba0b61b61eccd?i=v&vast=ok&mobileApp=1&disableAds=1' width='635' height='350' frameborder='0' scrolling='no' allowfullscreen webkitallowfullscreen mozallowfullscreen oallowfullscreen msallowfullscreen></iframe>"

        val height = "360"

        frameVideo = Regex("width=\'([0-9]{1,4})\'").replace(frameVideo,"width='100%'")
        frameVideo = Regex("height=\'([0-9]{1,4})\'").replace(frameVideo,"height='$height'")


        /*val webSettings = webView.settings
        webSettings.javaScriptEnabled = true*/
        webViewa.loadHtml(frameVideo)

    }
}
