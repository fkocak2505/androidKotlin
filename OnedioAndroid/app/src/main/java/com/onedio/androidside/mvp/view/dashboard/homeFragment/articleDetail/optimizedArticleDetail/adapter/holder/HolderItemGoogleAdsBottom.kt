package com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.optimizedArticleDetail.adapter.holder

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.doubleclick.PublisherAdRequest
import com.google.android.gms.ads.doubleclick.PublisherAdView
import com.onedio.androidside.R
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.optimizedArticleDetail.model.ArticleDetailAdapterModel

class HolderItemGoogleAdsBottom(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var publisherAdViewBottom: PublisherAdView? =
        itemView.findViewById(R.id.itemOfPublisherAdViewBottom) as PublisherAdView

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

            }

            override fun onAdFailedToLoad(errorCode: Int) {

            }

            override fun onAdOpened() {

            }

            override fun onAdClicked() {

            }

            override fun onAdLeftApplication() {

            }

            override fun onAdClosed() {

            }
        }
    }

}