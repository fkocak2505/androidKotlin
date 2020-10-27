package com.onedio.androidside.mvp.view.dashboard.search.moreTag.tagsArticle.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.onedio.androidside.R
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.video.adapter.holder.HolderVideoOnedio
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.video.adapter.holder.HolderVideoOther
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.video.adapter.holder.HolderVideoTopAds
import com.onedio.androidside.mvp.view.dashboard.search.moreTag.tagsArticle.adapter.holder.HolderTagsAdsTop
import com.onedio.androidside.mvp.view.dashboard.search.moreTag.tagsArticle.adapter.holder.HolderTagsArticle
import com.onedio.androidside.mvp.view.dashboard.search.moreTag.tagsArticle.adapter.holder.HolderTagsLoader
import com.onedio.androidside.mvp.view.dashboard.search.moreTag.tagsArticle.adapter.model.TagsAdapterModel

class TagsArticleRecyclerViewAdapter(
    val context: Context,
    var items: MutableList<TagsAdapterModel>,
    val listener: (TagsAdapterModel, String) -> Unit
)  : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TAGS_GOOGLE_ADS_TOP = 0
    private val TAGS_ARTICLE = 1
    private val TAGS_LOADER = 2

    private lateinit var viewHolder: RecyclerView.ViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        var inflater = LayoutInflater.from(parent.context)

        when (viewType) {
            TAGS_GOOGLE_ADS_TOP -> {
                val vItemGoogleAds =
                    inflater.inflate(R.layout.article_detail_item_google_ads_top, parent, false)
                viewHolder =
                    HolderTagsAdsTop(
                        vItemGoogleAds
                    )
            }
            TAGS_ARTICLE -> {
                val vTagsArticle =
                    inflater.inflate(R.layout.tags_article_recycler_view_item_row, parent, false)
                viewHolder =
                    HolderTagsArticle(
                        vTagsArticle
                    )
            }
            TAGS_LOADER -> {
                val vVideoLoader =
                    inflater.inflate(R.layout.item_recycler_loading, parent, false)
                viewHolder =
                    HolderTagsLoader(
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
            TAGS_GOOGLE_ADS_TOP -> {
                if (!items[position].isAdLoaded!!) {
                    items[position].isAdLoaded = true
                    val vItemGoogleAdsTop = holder as HolderTagsAdsTop
                    configureViewHolderItemGoogleAdsTop(vItemGoogleAdsTop, position)
                }
            }
            TAGS_ARTICLE -> {
                val vhTagsArticle = holder as HolderTagsArticle
                configureViewHolderTagsArticle(vhTagsArticle, position)
            }
            TAGS_LOADER -> {
                val vhTagsLoader = holder as HolderTagsLoader
                configureViewHolderTagsLoader(vhTagsLoader, position)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position].type) {
            "googleAdsTop" -> {
                TAGS_GOOGLE_ADS_TOP
            }
            "article" -> {
                TAGS_ARTICLE
            }
            "loading" -> {
                TAGS_LOADER
            }
            else -> {
                -1
            }
        }
    }

    private fun configureViewHolderItemGoogleAdsTop(vh1: HolderTagsAdsTop, position: Int) {

        vh1.bindItems(context, items[position], listener, position)

    }


    private fun configureViewHolderTagsArticle(vh1: HolderTagsArticle, position: Int) {
        vh1.bindItems(context, items[position], listener, position)

    }

    private fun configureViewHolderTagsLoader(vh1: HolderTagsLoader, position: Int) {

        vh1.bindItems(context, items[position], listener, position)

    }


}