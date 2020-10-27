package com.onedio.androidside.mvp.view.dashboard.homeFragment.adapter.holder

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.doubleclick.PublisherAdRequest
import com.google.android.gms.ads.doubleclick.PublisherAdView
import com.onedio.androidside.R
import com.onedio.androidside.mvp.view.dashboard.homeFragment.ArticlesFragmentAdapterModel
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.optimizedArticleDetail.model.ArticleDetailAdapterModel

class HolderArticleItemGoogleAdsTop(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var publisherAdView: PublisherAdView? =
        itemView.findViewById(R.id.itemOfpublisherAdViewTop) as PublisherAdView

    fun bindItems(
        context: Context,
        item: ArticlesFragmentAdapterModel,
        listener: (Int, ArticlesFragmentAdapterModel) -> Unit,
        pos: Int
    ) {


        val adRequest = PublisherAdRequest.Builder().build()
        publisherAdView?.loadAd(adRequest)

        publisherAdView?.adListener = object : AdListener() {
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