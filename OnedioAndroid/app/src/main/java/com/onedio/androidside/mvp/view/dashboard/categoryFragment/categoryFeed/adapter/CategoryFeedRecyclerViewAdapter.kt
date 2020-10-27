package com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.onedio.androidside.R
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.adapter.holder.HolderCategoryArticle
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.adapter.holder.HolderCategoryLoading
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.adapter.holder.HolderCategoryTopAds
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.adapter.model.CategoryAdapterModel
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.video.adapter.holder.HolderVideoLoading

class CategoryFeedRecyclerViewAdapter(
    val context: Context,
    var items: MutableList<CategoryAdapterModel>,
    val listener: (CategoryAdapterModel, String) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val CATEGORY_GOOGLE_ADS_TOP = 0
    private val CATEGORY_ARTICLE = 1
    private val CATEGORY_LOADING = 2

    private lateinit var viewHolder: RecyclerView.ViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        var inflater = LayoutInflater.from(parent.context)

        when (viewType) {
            CATEGORY_GOOGLE_ADS_TOP -> {
                val vItemGoogleAds =
                    inflater.inflate(R.layout.article_detail_item_google_ads_top, parent, false)
                viewHolder =
                    HolderCategoryTopAds(
                        vItemGoogleAds
                    )
            }
            CATEGORY_ARTICLE -> {
                val vCategoryArticle =
                    inflater.inflate(
                        R.layout.category_detail_item_recycler_view_item_row,
                        parent,
                        false
                    )
                viewHolder =
                    HolderCategoryArticle(
                        vCategoryArticle
                    )
            }
            CATEGORY_LOADING -> {
                val vVideoLoader =
                    inflater.inflate(R.layout.item_recycler_loading, parent, false)
                viewHolder =
                    HolderCategoryLoading(
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
            CATEGORY_GOOGLE_ADS_TOP -> {
                if (!items[position].isAdLoaded!!) {
                    items[position].isAdLoaded = true
                    val vItemGoogleAdsTop = holder as HolderCategoryTopAds
                    configureViewHolderItemGoogleAdsTop(vItemGoogleAdsTop, position)
                }
            }
            CATEGORY_ARTICLE -> {
                val vhCategoryArticle = holder as HolderCategoryArticle
                configureViewHolderCategoryArticle(vhCategoryArticle, position)
            }
            CATEGORY_LOADING -> {
                val vhCategoryLoading = holder as HolderCategoryLoading
                configureViewHolderCategoryLoading(vhCategoryLoading, position)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        when (items[position].type) {
            "googleAdsTop" -> {
                return CATEGORY_GOOGLE_ADS_TOP
            }
            "article" -> {
                return CATEGORY_ARTICLE
            }
            "loading" -> {
                return CATEGORY_LOADING
            }
            else -> {
                return -1
            }
        }
    }

    private fun configureViewHolderItemGoogleAdsTop(vh1: HolderCategoryTopAds, position: Int) {

        vh1.bindItems(context, items[position], listener, position)

    }


    private fun configureViewHolderCategoryArticle(vh1: HolderCategoryArticle, position: Int) {
        vh1.bindItems(context, items[position], listener, position)

    }

    private fun configureViewHolderCategoryLoading(vh1: HolderCategoryLoading, position: Int) {

        vh1.bindItems(context, items[position], listener, position)

    }

}