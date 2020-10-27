package com.musicore.nestedrecyclerview.holder

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.doubleclick.PublisherAdRequest
import com.google.android.gms.ads.doubleclick.PublisherAdView
import com.musicore.nestedrecyclerview.R
import com.musicore.nestedrecyclerview.model.ArticleDetailAdapterModel


class HolderItemGoogleAdsTop(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var publisherAdView: PublisherAdView? =
        itemView.findViewById(R.id.publisherAdView) as PublisherAdView

    fun bindItems(
        context: Context,
        item: ArticleDetailAdapterModel,
        listener: (Int, ArticleDetailAdapterModel, String) -> Unit,
        pos: Int
    ) {


        val adRequest = PublisherAdRequest.Builder().build()
        publisherAdView?.loadAd(adRequest)

        publisherAdView?.adListener = object : AdListener() {
            override fun onAdLoaded() {
                Toast.makeText(context, "onAdLoaded", Toast.LENGTH_SHORT).show()
            }

            override fun onAdFailedToLoad(adError: LoadAdError) {
                Toast.makeText(context, "onAdFailedToLoad", Toast.LENGTH_SHORT).show()
            }

            override fun onAdOpened() {
                Toast.makeText(context, "onAdOpened", Toast.LENGTH_SHORT).show()
            }

            override fun onAdClicked() {
                Toast.makeText(context, "onAdClicked", Toast.LENGTH_SHORT).show()
            }

            override fun onAdLeftApplication() {
                Toast.makeText(context, "onAdLeftApplication", Toast.LENGTH_SHORT).show()
            }

            override fun onAdClosed() {
                Toast.makeText(context, "onAdClosed", Toast.LENGTH_SHORT).show()
            }
        }
    }


}