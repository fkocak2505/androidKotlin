package com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.adapter.holder

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.doubleclick.PublisherAdRequest
import com.google.android.gms.ads.doubleclick.PublisherAdView
import com.onedio.androidside.R
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.adapter.model.CategoryAdapterModel
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.video.adapter.model.VideoAdapterModel

class HolderCategoryLoading(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var loading: ProgressBar? =
        itemView.findViewById(R.id.loading) as ProgressBar

    fun bindItems(
        context: Context,
        item: CategoryAdapterModel,
        listener: (CategoryAdapterModel, String) -> Unit,
        pos: Int
    ) {

    }

}