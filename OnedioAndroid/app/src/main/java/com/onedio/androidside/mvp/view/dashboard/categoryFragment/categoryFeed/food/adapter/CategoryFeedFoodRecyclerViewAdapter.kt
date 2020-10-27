package com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.food.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.onedio.androidside.R
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.food.holder.CategoryItem4Food
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.food.holder.HolderFoodText
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.food.holder.HolderFoodVideo
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.video.adapter.holder.HolderVideoLoading
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.video.adapter.holder.HolderVideoTopAds
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.video.adapter.model.VideoAdapterModel

class CategoryFeedFoodRecyclerViewAdapter(
    val context: Context,
    var items: MutableList<VideoAdapterModel>,
    val listener: (VideoAdapterModel, String) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val FOOD_GOOGLE_ADS_TOP = 0
    private val FOOD_TEXT = 1
    private val FOOD_VIDEO = 2
    private val FOOD_LOADER = 3

    private lateinit var viewHolder: RecyclerView.ViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val inflater = LayoutInflater.from(parent.context)

        when (viewType) {
            FOOD_GOOGLE_ADS_TOP -> {
                val vItemGoogleAds =
                    inflater.inflate(R.layout.article_detail_item_google_ads_top, parent, false)
                viewHolder =
                    HolderVideoTopAds(
                        vItemGoogleAds
                    )
            }
            FOOD_TEXT -> {
                val vCommentParent = inflater.inflate(R.layout.category_detail_food_text, parent, false)
                viewHolder =
                    HolderFoodText(
                        vCommentParent
                    )
            }
            FOOD_VIDEO -> {
                val vCommentChild = inflater.inflate(R.layout.category_detail_food_video, parent, false)
                viewHolder =
                    HolderFoodVideo(
                        vCommentChild
                    )
            }
            FOOD_LOADER -> {
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
            FOOD_GOOGLE_ADS_TOP -> {
                if (!items[position].isAdLoaded!!) {
                    items[position].isAdLoaded = true
                    val vItemGoogleAdsTop = holder as HolderVideoTopAds
                    configureViewHolderItemGoogleAdsTop(vItemGoogleAdsTop, position)
                }
            }
            FOOD_TEXT -> {
                val vhCommentParent = holder as HolderFoodText
                configureViewHolderFoodText(vhCommentParent, position)
            }
            FOOD_VIDEO -> {
                val vhCommentChild = holder as HolderFoodVideo
                configureViewHolderFoodVideo(vhCommentChild, position)
            }
            FOOD_LOADER -> {
                val vhVideoLoader = holder as HolderVideoLoading
                configureViewHolderVideoLoader(vhVideoLoader, position)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        when (items[position].type) {
            "googleAdsTop" -> {
                return FOOD_GOOGLE_ADS_TOP
            }
            "text" -> {
                return FOOD_TEXT
            }
            "video" -> {
                return FOOD_VIDEO
            }
            "loading" -> {
                return FOOD_LOADER
            }
            else -> {
                return -1
            }
        }

    }

    private fun configureViewHolderItemGoogleAdsTop(vh1: HolderVideoTopAds, position: Int) {

        vh1.bindItems(context, items[position], listener, position)

    }


    private fun configureViewHolderFoodText(vh1: HolderFoodText, position: Int) {
        vh1.bindItems(context,items[position], listener, position)

    }

    private fun configureViewHolderFoodVideo(vh1: HolderFoodVideo, position: Int) {

        vh1.bindItems(context,items[position], listener, position)

    }

    private fun configureViewHolderVideoLoader(vh1: HolderVideoLoading, position: Int) {

        vh1.bindItems(context, items[position], listener, position)

    }


}