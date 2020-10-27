package com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.video.adapter.holder

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.doubleclick.PublisherAdRequest
import com.google.android.gms.ads.doubleclick.PublisherAdView
import com.onedio.androidside.R
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.food.holder.CategoryItem4Food
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.video.adapter.model.VideoAdapterModel

class HolderVideoTopAds(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var publisherAdView: PublisherAdView? =
        itemView.findViewById(R.id.itemOfpublisherAdViewTop) as PublisherAdView

    fun bindItems(
        context: Context,
        item: VideoAdapterModel,
        listener: (VideoAdapterModel, String) -> Unit,
        pos: Int
    ) {


        val adRequest = PublisherAdRequest.Builder().build()
        publisherAdView?.loadAd(adRequest)

        publisherAdView?.adListener = object : AdListener() {
            override fun onAdLoaded() {
                //Toast.makeText(context, "onAdLoaded", Toast.LENGTH_SHORT).show()
            }

            override fun onAdFailedToLoad(errorCode: Int) {
                //Toast.makeText(context, "onAdFailedToLoad", Toast.LENGTH_SHORT).show()
            }

            override fun onAdOpened() {
                //Toast.makeText(context, "onAdOpened", Toast.LENGTH_SHORT).show()
            }

            override fun onAdClicked() {
                //Toast.makeText(context, "onAdClicked", Toast.LENGTH_SHORT).show()
            }

            override fun onAdLeftApplication() {
                //Toast.makeText(context, "onAdLeftApplication", Toast.LENGTH_SHORT).show()
            }

            override fun onAdClosed() {
                //Toast.makeText(context, "onAdClosed", Toast.LENGTH_SHORT).show()
            }
        }
    }

}