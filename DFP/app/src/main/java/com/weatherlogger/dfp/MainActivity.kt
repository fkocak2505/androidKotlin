package com.weatherlogger.dfp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.doubleclick.PublisherAdRequest
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val adRequest = PublisherAdRequest.Builder().build()
        publisherAdView.loadAd(adRequest)
        publisherAdView1.loadAd(adRequest)


        publisherAdView.adListener = object: AdListener() {
            override fun onAdLoaded() {
                Log.i("onAdLoaded", "onAdLoaded")

            }

            override fun onAdFailedToLoad(errorCode : Int) {
                Log.i("onAdFailedToLoad", "onAdFailedToLoad")
                Toast.makeText(applicationContext, errorCode.toString(), Toast.LENGTH_SHORT).show()
            }

            override fun onAdOpened() {
                Log.i("onAdOpened", "onAdOpened")

            }

            override fun onAdClicked() {
                Log.i("onAdClicked", "onAdClicked")
            }

            override fun onAdLeftApplication() {
                Log.i("onAdLeftApplication", "onAdLeftApplication")
            }

            override fun onAdClosed() {
                Log.i("onAdClosed", "onAdClosed")
            }
        }


        publisherAdView1.adListener = object: AdListener() {
            override fun onAdLoaded() {
                Log.i("onAdLoaded", "onAdLoadedBottom")
            }

            override fun onAdFailedToLoad(errorCode : Int) {
                Log.i("onAdFailedToLoad", "onAdFailedToLoadBottom")
                Toast.makeText(applicationContext, errorCode.toString(), Toast.LENGTH_SHORT).show()
            }

            override fun onAdOpened() {
                Log.i("onAdOpened", "onAdOpenedBottom")
            }

            override fun onAdClicked() {
                Log.i("onAdClicked", "onAdClickedBottom")
            }

            override fun onAdLeftApplication() {
                Log.i("onAdLeftApplication", "onAdLeftApplicationBottom")
            }

            override fun onAdClosed() {
                Log.i("onAdClosed", "onAdClosedBottom")
            }
        }



        /*MobileAds.initialize(this) {}
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
        adView1.loadAd(adRequest)



        adView.adListener = object: AdListener() {
            override fun onAdLoaded() {
                Log.i("onAdLoaded", "onAdLoaded")
            }

            override fun onAdFailedToLoad(errorCode : Int) {
                Log.i("onAdFailedToLoad", "onAdFailedToLoad")
                Toast.makeText(applicationContext, errorCode.toString(), Toast.LENGTH_SHORT).show()
            }

            override fun onAdOpened() {
                Log.i("onAdOpened", "onAdOpened")
            }

            override fun onAdClicked() {
                Log.i("onAdClicked", "onAdClicked")
            }

            override fun onAdLeftApplication() {
                Log.i("onAdLeftApplication", "onAdLeftApplication")
            }

            override fun onAdClosed() {
                Log.i("onAdClosed", "onAdClosed")
            }
        }*/

    }
}
