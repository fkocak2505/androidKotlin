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

class HolderItemGoogleAdsBottom(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var publisherAdViewBottom: PublisherAdView? =
        itemView.findViewById(R.id.publisherAdViewBottom) as PublisherAdView

    fun bindItems(
        context: Context,
        item: ArticleDetailAdapterModel,
        listener: (Int, ArticleDetailAdapterModel, String) -> Unit,
        pos: Int
    ) {


        val adRequest = PublisherAdRequest.Builder().build()
        publisherAdViewBottom?.loadAd(adRequest)

        publisherAdViewBottom?.adListener = object : AdListener() {
            override fun onAdLoaded() {
                Toast.makeText(context, "onAdLoadedBottom", Toast.LENGTH_SHORT).show()
            }

            override fun onAdFailedToLoad(adError: LoadAdError) {
                Toast.makeText(context, "onAdFailedToLoadBottom", Toast.LENGTH_SHORT).show()
            }

            override fun onAdOpened() {
                Toast.makeText(context, "onAdOpenedBottom", Toast.LENGTH_SHORT).show()
            }

            override fun onAdClicked() {
                Toast.makeText(context, "onAdClickedBottom", Toast.LENGTH_SHORT).show()
            }

            override fun onAdLeftApplication() {
                Toast.makeText(context, "onAdLeftApplicationBottom", Toast.LENGTH_SHORT).show()
            }

            override fun onAdClosed() {
                Toast.makeText(context, "onAdClosedBottom", Toast.LENGTH_SHORT).show()
            }
        }
    }

}