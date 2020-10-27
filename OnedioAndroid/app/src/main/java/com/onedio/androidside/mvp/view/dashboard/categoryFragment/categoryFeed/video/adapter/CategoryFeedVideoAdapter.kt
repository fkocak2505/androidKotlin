package com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.video.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.onedio.androidside.R
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.food.holder.CategoryItem4Food
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.video.adapter.holder.HolderVideoLoading
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.video.adapter.holder.HolderVideoOnedio
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.video.adapter.holder.HolderVideoOther
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.video.adapter.holder.HolderVideoTopAds
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.video.adapter.model.VideoAdapterModel
import com.onedio.androidside.mvp.view.dashboard.trendingFragment.adapter.holder.HolderTrendingItemGoogleAdsTop

class CategoryFeedVideoAdapter(
    val context: Context,
    var items: MutableList<VideoAdapterModel>,
    val listener: (VideoAdapterModel, String) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIDEO_GOOGLE_ADS_TOP = 0
    private val VIDEO_ONEDIO = 1
    private val VIDEO_OTHER = 2
    private val VIDEO_LOADER = 3

    private lateinit var viewHolder: RecyclerView.ViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        var inflater = LayoutInflater.from(parent.context)

        when (viewType) {
            VIDEO_GOOGLE_ADS_TOP -> {
                val vItemGoogleAds =
                    inflater.inflate(R.layout.article_detail_item_google_ads_top, parent, false)
                viewHolder =
                    HolderVideoTopAds(
                        vItemGoogleAds
                    )
            }
            VIDEO_ONEDIO -> {
                val vVideoOnedio =
                    inflater.inflate(R.layout.category_detail_video_onedio, parent, false)
                viewHolder =
                    HolderVideoOnedio(
                        vVideoOnedio
                    )
            }
            VIDEO_OTHER -> {
                val vVideoOther =
                    inflater.inflate(R.layout.category_detail_video_other, parent, false)
                viewHolder =
                    HolderVideoOther(
                        vVideoOther
                    )
            }
            VIDEO_LOADER -> {
                val vVideoLoader =
                    inflater.inflate(R.layout.item_recycler_loading, parent, false)
                viewHolder =
                    HolderVideoLoading(
                        vVideoLoader
                    )
            }
        }

        return viewHolder

    }


    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            VIDEO_GOOGLE_ADS_TOP -> {
                if (!items[position].isAdLoaded!!) {
                    items[position].isAdLoaded = true
                    val vItemGoogleAdsTop = holder as HolderVideoTopAds
                    configureViewHolderItemGoogleAdsTop(vItemGoogleAdsTop, position)
                }
            }
            VIDEO_ONEDIO -> {
                val vhVideoOnedio = holder as HolderVideoOnedio
                configureViewHolderVideoOnedio(vhVideoOnedio, position)
            }
            VIDEO_OTHER -> {
                val vhVideoOther = holder as HolderVideoOther
                configureViewHolderVideoOther(vhVideoOther, position)
            }
            VIDEO_LOADER -> {
                val vhVideoLoader = holder as HolderVideoLoading
                configureViewHolderVideoLoader(vhVideoLoader, position)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        when (items[position].type) {
            "googleAdsTop" -> {
                return VIDEO_GOOGLE_ADS_TOP
            }
            "onedio" -> {
                return VIDEO_ONEDIO
            }
            "other" -> {
                return VIDEO_OTHER
            }
            "loading" -> {
                return VIDEO_LOADER
            }
            else -> {
                return -1
            }
        }
    }

    private fun configureViewHolderItemGoogleAdsTop(vh1: HolderVideoTopAds, position: Int) {

        vh1.bindItems(context, items[position], listener, position)

    }


    private fun configureViewHolderVideoOnedio(vh1: HolderVideoOnedio, position: Int) {
        vh1.bindItems(context, items[position], listener, position)

    }

    private fun configureViewHolderVideoOther(vh1: HolderVideoOther, position: Int) {

        vh1.bindItems(context, items[position], listener, position)

    }

    private fun configureViewHolderVideoLoader(vh1: HolderVideoLoading, position: Int) {

        vh1.bindItems(context, items[position], listener, position)

    }


}