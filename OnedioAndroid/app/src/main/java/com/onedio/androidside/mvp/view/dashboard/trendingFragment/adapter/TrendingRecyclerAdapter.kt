package com.onedio.androidside.mvp.view.dashboard.trendingFragment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.onedio.androidside.R
import com.onedio.androidside.mvp.view.dashboard.trendingFragment.adapter.model.TrendingAdapterModel
import com.onedio.androidside.mvp.view.dashboard.trendingFragment.adapter.holder.HolderItemTrendList
import com.onedio.androidside.mvp.view.dashboard.trendingFragment.adapter.holder.HolderItemTrendingHeader
import com.onedio.androidside.mvp.view.dashboard.trendingFragment.adapter.holder.HolderTrendItemLoading
import com.onedio.androidside.mvp.view.dashboard.trendingFragment.adapter.holder.HolderTrendingItemGoogleAdsTop

class TrendingRecyclerAdapter(
    private val context: Context,
    private var items: MutableList<TrendingAdapterModel>,
    private val listener: (TrendingAdapterModel, String) -> Unit
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val ITEM_GOOGLE_ADS_TOP = 1
    private val ITEM_HEADER = 2
    private val ITEM_LIST = 3
    private val ITEM_LOADING = 4

    private lateinit var viewHolder: RecyclerView.ViewHolder

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {

        val inflater = LayoutInflater.from(parent.context)

        when (viewType) {
            ITEM_GOOGLE_ADS_TOP -> {
                val vItemGoogleAds = inflater.inflate(R.layout.article_detail_item_google_ads_top, parent, false)
                viewHolder =
                    HolderTrendingItemGoogleAdsTop(
                        vItemGoogleAds
                    )
            }
            ITEM_HEADER -> {
                val vItemTrendingHeader = inflater.inflate(R.layout.article_detail_item_trending_header, parent, false)
                viewHolder =
                    HolderItemTrendingHeader(
                        vItemTrendingHeader
                    )
            }
            ITEM_LIST -> {
                val vItemTrendList = inflater.inflate(R.layout.trending_recycler_view_item_row, parent, false)
                viewHolder =
                    HolderItemTrendList(
                        vItemTrendList
                    )
            }
            ITEM_LOADING -> {
                val vItemTrendLoading = inflater.inflate(R.layout.item_recycler_loading, parent, false)
                viewHolder =
                    HolderTrendItemLoading(
                        vItemTrendLoading
                    )
            }
        }

        return viewHolder

    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {

        when (holder.itemViewType) {
            ITEM_GOOGLE_ADS_TOP -> {
                if (!items[position].isAdLoaded!!) {
                    items[position].isAdLoaded = true
                    val vItemGoogleAdsTop = holder as HolderTrendingItemGoogleAdsTop
                    configureViewHolderItemGoogleAdsTop(vItemGoogleAdsTop, position)
                }
            }
            ITEM_HEADER -> {
                val vItemTrendHeader = holder as HolderItemTrendingHeader
                configureViewHolderItemTrendHeader(vItemTrendHeader, position)
            }
            ITEM_LIST -> {
                val vItemTrendList = holder as HolderItemTrendList
                configureViewHolderItemTrendList(vItemTrendList, position)
            }
            ITEM_LOADING -> {
                val vItemTrendLoading = holder as HolderTrendItemLoading
                configureViewHolderItemLoading(vItemTrendLoading, position)
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        when (items[position].type) {
            "googleAdsTop" -> {
                return ITEM_GOOGLE_ADS_TOP
            }
            "header" -> {
                return ITEM_HEADER
            }
            "list" -> {
                return ITEM_LIST
            }
            "loading" ->{
                return ITEM_LOADING
            }
            else -> {
                return -1
            }
        }
    }

    private fun configureViewHolderItemGoogleAdsTop(vh1: HolderTrendingItemGoogleAdsTop, position: Int) {

        vh1.bindItems(context, items[position], listener, position)

    }

    private fun configureViewHolderItemTrendHeader(vh2: HolderItemTrendingHeader, position: Int) {

        vh2.bindItems(context, items[position], listener, position)

    }

    private fun configureViewHolderItemTrendList(vh3: HolderItemTrendList, position: Int) {

        vh3.bindItems(context, items[position], listener, position)

    }

    private fun configureViewHolderItemLoading(vh4: HolderTrendItemLoading, position: Int) {

        vh4.bindItems(context, items[position], listener, position)

    }

}