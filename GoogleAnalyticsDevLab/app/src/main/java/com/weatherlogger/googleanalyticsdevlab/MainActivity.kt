package com.weatherlogger.googleanalyticsdevlab

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tagmanager.TagManager
import com.google.android.gms.tagmanager.DataLayer





class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dataLayer = TagManager.getInstance(this).dataLayer
        dataLayer.push(
            DataLayer.mapOf(
                "event",
                "openScreen", // The event type. This value should be used consistently for similar event types.
                "screenName", // Writes a key "screenName" to the dataLayer map.
                "Home Screen"
            )     // Writes a value "Home Screen" for the "screenName" key.
        )


    }
}
