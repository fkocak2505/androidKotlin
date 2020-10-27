package com.musicore.firebaseremoteconfig

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var mFirebaseRemoteConfig: FirebaseRemoteConfig
    private val LOADING_PHRASE_CONFIG_KEY = "loading_phrase"
    private val WELCOME_MESSAGE_KEY = "message"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
        val configSettings = FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(0)
                .build()
        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings)

        mFirebaseRemoteConfig.setDefaultsAsync(R.xml.remote_config)

        fetchWelcome()

    }

    private fun fetchWelcome() {
        deneme.text =  mFirebaseRemoteConfig.getString(LOADING_PHRASE_CONFIG_KEY)

        // [START fetch_config_with_callback]
        mFirebaseRemoteConfig.fetchAndActivate()
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val updated = task.result
                        Log.d("RConfig", "Config params updated: $updated")
                        Toast.makeText(this@MainActivity, "Fetch and activate succeeded",
                                Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@MainActivity, "Fetch failed",
                                Toast.LENGTH_SHORT).show()
                    }
                    displayWelcomeMessage()
                }
        // [END fetch_config_with_callback]
    }

    private fun displayWelcomeMessage() {
        // [START get_config_values]
        val welcomeMessage = mFirebaseRemoteConfig.getString(WELCOME_MESSAGE_KEY)

        deneme.text = welcomeMessage
    }


}