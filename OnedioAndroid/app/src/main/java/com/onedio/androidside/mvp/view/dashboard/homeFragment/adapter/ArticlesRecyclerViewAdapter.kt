package com.onedio.androidside.mvp.view.dashboard.homeFragment.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.onedio.androidside.R
import com.onedio.androidside.mvp.presenter.dashboard.homeFragment.ArticlesFragmentViewPresenterImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.ArticlesFragmentAdapterModel
import com.onedio.androidside.mvp.view.dashboard.homeFragment.adapter.holder.HolderArticleItemGoogleAdsTop
import com.onedio.androidside.mvp.view.dashboard.homeFragment.adapter.holder.HolderArticleItemSlider
import com.onedio.androidside.mvp.view.dashboard.homeFragment.adapter.holder.horizontalCard.HolderArticleItemHorizontalCard
import com.onedio.androidside.mvp.view.dashboard.homeFragment.adapter.holder.hugeCard.HolderArticleItemHugeCard
import com.onedio.androidside.mvp.view.dashboard.homeFragment.widgets.hugeCardRecyclerViewAdapter.model.HugeCardModel

class ArticlesRecyclerViewAdapter(
    private val activity: Activity,
    private val context: Context,
    private val articles: MutableList<ArticlesFragmentAdapterModel>,
    private val articlesFragmentViewPresenterImpl: ArticlesFragmentViewPresenterImpl,
    private val allArticlesList: MutableList<HugeCardModel>,
    private val listener: (Int, ArticlesFragmentAdapterModel) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()

    private val ITEM_GOOGLE_ADS_TOP = 1
    private val ITEM_SLIDER = 2
    private val ITEM_HORIZONTAL_CARD = 3
    private val ITEM_HUGE_CARD = 4

    private lateinit var viewHolder: RecyclerView.ViewHolder

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {

        val inflater = LayoutInflater.from(parent.context)

        when (viewType) {
            ITEM_GOOGLE_ADS_TOP -> {
                val vItemGoogleAds =
                    inflater.inflate(R.layout.article_detail_item_google_ads_top, parent, false)
                viewHolder =
                    HolderArticleItemGoogleAdsTop(
                        vItemGoogleAds
                    )
            }
            ITEM_SLIDER -> {
                val vItemArticleSlider =
                    inflater.inflate(R.layout.article_item_slider, parent, false)
                viewHolder =
                    HolderArticleItemSlider(
                        vItemArticleSlider
                    )
            }
            ITEM_HUGE_CARD -> {
                val vItemArticleHugeCard =
                    inflater.inflate(R.layout.widget_huge_card_list_item, parent, false)
                viewHolder =
                    HolderArticleItemHugeCard(
                        vItemArticleHugeCard
                    )
            }
            ITEM_HORIZONTAL_CARD -> {
                val vItemArticleHorizontalCard =
                    inflater.inflate(R.layout.article_item_horizontal_card, parent, false)
                viewHolder =
                    HolderArticleItemHorizontalCard(
                        vItemArticleHorizontalCard
                    )
            }
        }

        return viewHolder

    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        when (holder.itemViewType) {
            ITEM_GOOGLE_ADS_TOP -> {
                if (!articles[position].isAdLoaded!!) {
                    articles[position].isAdLoaded = true
                    val vItemGoogleAdsTop = holder as HolderArticleItemGoogleAdsTop
                    configureViewHolderItemGoogleAdsTop(vItemGoogleAdsTop, position)
                }
            }
            ITEM_SLIDER -> {
                val vItemHeader = holder as HolderArticleItemSlider
                configureViewHolderItemSlider(vItemHeader, position)
            }
            ITEM_HUGE_CARD -> {
                val vItemComment = holder as HolderArticleItemHugeCard
                configureViewHolderItemHugeCard(vItemComment, position)
            }
            ITEM_HORIZONTAL_CARD -> {
                val vItemEmoji = holder as HolderArticleItemHorizontalCard
                configureViewHolderItemHorizontalCard(vItemEmoji, position)
            }

        }
    }

    override fun getItemViewType(position: Int): Int {
        when (articles[position].type) {
            "googleAdsTop" -> {
                return ITEM_GOOGLE_ADS_TOP
            }
            "slider" -> {
                return ITEM_SLIDER
            }
            "hugeCard" -> {
                return ITEM_HUGE_CARD
            }
            "horizontalSlideCard" -> {
                return ITEM_HORIZONTAL_CARD
            }
            else -> {
                return -1
            }
        }
    }

    private fun configureViewHolderItemGoogleAdsTop(
        vh1: HolderArticleItemGoogleAdsTop,
        position: Int
    ) {

        vh1.bindItems(context, articles[position], listener, position)

    }

    private fun configureViewHolderItemSlider(vh2: HolderArticleItemSlider, position: Int) {

        vh2.bindItems(context, articles[position], listener, position, allArticlesList)

    }

    private fun configureViewHolderItemHugeCard(vh5: HolderArticleItemHugeCard, position: Int) {

        vh5.bindItems(activity, articles[position], listener, position)

    }

    private fun configureViewHolderItemHorizontalCard(
        vh4: HolderArticleItemHorizontalCard,
        position: Int
    ) {

        vh4.bindItems(context, articles[position], listener, position)

    }

}