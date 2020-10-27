package com.musicore.nestedrecyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.musicore.nestedrecyclerview.holder.*
import com.musicore.nestedrecyclerview.model.ArticleDetailAdapterModel

class ParentAdapter2(
    private val context: Context,
    private val parents: MutableList<ArticleDetailAdapterModel>,
    private val listener: (Int, ArticleDetailAdapterModel, String) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()

    private val ITEM_GOOGLE_ADS_TOP = 1
    private val ITEM_HEADER = 2
    private val ITEM_ENTRY = 3
    private val ITEM_GOOGLE_ADS_BOTTOM = 4
    private val ITEM_EMOJI = 5
    private val ITEM_COMMENT = 6
    private val ITEM_TABOOLA = 7


    private lateinit var viewHolder: RecyclerView.ViewHolder

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {

        val inflater = LayoutInflater.from(parent.context)

        when (viewType) {
            ITEM_GOOGLE_ADS_TOP -> {
                val vItemGoogleAds = inflater.inflate(R.layout.item_google_ads_top, parent, false)
                viewHolder =
                    HolderItemGoogleAdsTop(
                        vItemGoogleAds
                    )
            }
            ITEM_HEADER -> {
                val vItemHeader = inflater.inflate(R.layout.item_header, parent, false)
                viewHolder =
                    HolderItemHeader(
                        vItemHeader
                    )
            }
            ITEM_ENTRY -> {
                val vItemEntry = inflater.inflate(R.layout.item_entry, parent, false)
                viewHolder =
                    HolderItemEntry(
                        vItemEntry
                    )
            }
            ITEM_GOOGLE_ADS_BOTTOM -> {
                val vItemGoogleAdsBottom =
                    inflater.inflate(R.layout.item_google_ads_bottom, parent, false)
                viewHolder =
                    HolderItemGoogleAdsBottom(
                        vItemGoogleAdsBottom
                    )
            }
            ITEM_EMOJI -> {
                val vItemEmoji = inflater.inflate(R.layout.item_emoji, parent, false)
                viewHolder =
                    HolderItemEmoji(
                        vItemEmoji
                    )
            }
            ITEM_COMMENT -> {
                val vItemComment = inflater.inflate(R.layout.item_comment, parent, false)
                viewHolder =
                    HolderItemComment(
                        vItemComment
                    )
            }
            ITEM_TABOOLA -> {
                val vItemTaboole = inflater.inflate(R.layout.item_taboola, parent, false)
                viewHolder =
                    HolderItemTaboola(
                        vItemTaboole
                    )
            }
        }

        return viewHolder

    }

    override fun getItemCount(): Int {
        return parents.size
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        when (holder.itemViewType) {
            ITEM_GOOGLE_ADS_TOP -> {
                if (!parents[position].isAdLoaded!!) {
                    parents[position].isAdLoaded = true
                    val vItemGoogleAdsTop = holder as HolderItemGoogleAdsTop
                    configureViewHolderItemGoogleAdsTop(vItemGoogleAdsTop, position)
                }
            }
            ITEM_HEADER -> {
                val vItemHeader = holder as HolderItemHeader
                configureViewHolderItemHeader(vItemHeader, position)
            }
            ITEM_ENTRY -> {
                val vItemEntry = holder as HolderItemEntry
                configureViewHolderItemEntry(vItemEntry, position)
            }
            ITEM_GOOGLE_ADS_BOTTOM -> {
                if (!parents[position].isAdLoaded!!) {
                    parents[position].isAdLoaded = true
                    val vItemGoogleAdsBottom = holder as HolderItemGoogleAdsBottom
                    configureViewHolderItemGoogleAdsBottom(vItemGoogleAdsBottom, position)
                }
            }
            ITEM_EMOJI -> {
                val vItemEmoji = holder as HolderItemEmoji
                configureViewHolderItemEmoji(vItemEmoji, position)
            }
            ITEM_COMMENT -> {
                val vItemComment = holder as HolderItemComment
                configureViewHolderItemComment(vItemComment, position)
            }
            ITEM_TABOOLA -> {
                if (!parents[position].isAdLoaded!!) {
                    parents[position].isAdLoaded = true
                    val vItemTaboola = holder as HolderItemTaboola
                    configureViewHolderItemTaboola(vItemTaboola, position)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        when (parents[position].type) {
            "googleAdsTop" -> {
                return ITEM_GOOGLE_ADS_TOP
            }
            "header" -> {
                return ITEM_HEADER
            }
            "entry" -> {
                return ITEM_ENTRY
            }
            "googleAdsBottom" -> {
                return ITEM_GOOGLE_ADS_BOTTOM
            }
            "emoji" -> {
                return ITEM_EMOJI
            }
            "comment" -> {
                return ITEM_COMMENT
            }
            "taboola" -> {
                return ITEM_TABOOLA
            }
            else -> {
                return -1
            }
        }
    }

    private fun configureViewHolderItemGoogleAdsTop(vh1: HolderItemGoogleAdsTop, position: Int) {

        vh1.bindItems(context, parents[position], listener, position)

    }

    private fun configureViewHolderItemHeader(vh2: HolderItemHeader, position: Int) {

        vh2.bindItems(context, parents[position], listener, position)

    }

    private fun configureViewHolderItemEntry(vh3: HolderItemEntry, position: Int) {

        vh3.bindItems(context, parents[position], listener, position)

    }

    private fun configureViewHolderItemGoogleAdsBottom(vh4: HolderItemGoogleAdsBottom, position: Int) {

        vh4.bindItems(context, parents[position], listener, position)

    }

    private fun configureViewHolderItemEmoji(vh5: HolderItemEmoji, position: Int) {

        vh5.bindItems(context, parents[position], listener, position)

    }

    private fun configureViewHolderItemComment(vh6: HolderItemComment, position: Int) {

        vh6.bindItems(context, parents[position], listener, position)

    }

    private fun configureViewHolderItemTaboola(vh7: HolderItemTaboola, position: Int) {

        vh7.bindItems(context, parents[position], listener, position)

    }




}