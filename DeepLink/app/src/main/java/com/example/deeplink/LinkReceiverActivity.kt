package com.example.deeplink

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle

class LinkReceiverActivity: Activity() {

    private val GALATASARAY = "Galatasaray"
    private val BESIKTAS = "Besiktas"
    private val FENERBAHCE = "Fenerbahce"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val action: String? = intent?.action
        val data: Uri? = intent?.data


        /*val intent = intent

        val intentData = intent.dataString
        if (intentData != null && Intent.ACTION_VIEW == intent.action) {
            val param = intentData.substring(intentData.lastIndexOf("/") + 1)
            var i: Intent? = null
            if (GALATASARAY.equals(param)) {
                i = Intent(this, Galatasaray::class.java)
            } else if (BESIKTAS.equals(param)) {
                i = Intent(this, Besiktas::class.java)
            } else if (FENERBAHCE.equals(param)) {
                i = Intent(this, Fenerbahce::class.java)
            }
            startActivity(i)
        }*/
    }

}