package com.onedio.androidside.remoteConfig

import android.app.Activity
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.onedio.androidside.BuildConfig


class RemoteConfigClient(val activitiy: Activity) {
    private val firebaseRemoteConfig: FirebaseRemoteConfig

    // return mutableLiveData to caller
    val mutableLiveData = MutableLiveData<RemoteConfig>()


    /**
     * Fetches remote configuration data from Firebase or uses local default configuration
     * Sets RemoteConfig object with configuration data
     * Sets mutableLiveData with newly created RemoteConfig object
     */
    private fun fetchRemoteConfig() {
        // if developer mode is enabled set cache to 0 to instantly see updates in remote config
        //if (BuildConfig.DEBUG) cacheExpiration = 0

        firebaseRemoteConfig.fetchAndActivate()
            .addOnCompleteListener(activitiy) { task ->
                if (task.isSuccessful) {
                    val updated = task.result

                    val remoteConfig =
                        RemoteConfig()
                    remoteConfig.forceUpdate = firebaseRemoteConfig.getString(RemoteConfigKeys.FORCE_UPDATE)

                    mutableLiveData.postValue(remoteConfig)

                } else {
                    Toast.makeText(activitiy.applicationContext, "Fetch failed",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    init {

        firebaseRemoteConfig = FirebaseRemoteConfig.getInstance()

        val settings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(0)
            .setDeveloperModeEnabled(BuildConfig.DEBUG).build()
        firebaseRemoteConfig.setConfigSettings(settings)

        fetchRemoteConfig()
    }
}