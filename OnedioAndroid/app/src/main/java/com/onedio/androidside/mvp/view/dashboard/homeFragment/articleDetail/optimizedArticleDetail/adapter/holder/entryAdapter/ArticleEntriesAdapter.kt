package com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.optimizedArticleDetail.adapter.holder.entryAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.onedio.androidside.R
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.newResponse.ArticleFeedDetailsEntryModel
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.optimizedArticleDetail.adapter.holder.HolderItemRecommendWidget
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.optimizedArticleDetail.adapter.holder.entryAdapter.holder.*


class ArticleEntriesAdapter(
    val context: Context,
    val items: MutableList<ArticleFeedDetailsEntryModel>?,
    val listener: (Int, ArticleFeedDetailsEntryModel, String) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val ENTRIES_TEXT = 0
    private val ENTRIES_IMAGE = 1
    private val ENTRIES_VIDEO = 2
    private val ENTRIES_HTML = 3
    private val ENTRIES_EMBED = 4
    private val ENTRIES_ONEDIO_EMBED = 5
    private val ENTRIES_RECOMMEND_WIDGED = 6

    private lateinit var viewHolder: RecyclerView.ViewHolder


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val inflater = LayoutInflater.from(parent.context)

        when (viewType) {
            ENTRIES_TEXT -> {
                val vEntriesText =
                    inflater.inflate(R.layout.article_detail_holder_entries_text, parent, false)
                viewHolder =
                    HolderTextEntries(
                        vEntriesText
                    )
            }
            ENTRIES_IMAGE -> {
                val vEntriesImage =
                    inflater.inflate(R.layout.article_detail_holder_entries_image, parent, false)
                viewHolder =
                    HolderImageEntries(
                        vEntriesImage
                    )
            }
            ENTRIES_VIDEO -> {
                val vEntriesVideo =
                    inflater.inflate(R.layout.article_detail_holder_entries_video, parent, false)
                viewHolder =
                    HolderVideoEntries(
                        vEntriesVideo
                    )
            }
            ENTRIES_EMBED -> {
                val vEntriesEmbed =
                    inflater.inflate(
                        R.layout.article_detail_holder_entries_embed_html,
                        parent,
                        false
                    )
                viewHolder =
                    HolderEmbedHtmlEntries(
                        vEntriesEmbed
                    )
            }
            ENTRIES_HTML -> {
                val vEntriesHtml =
                    inflater.inflate(
                        R.layout.article_detail_holder_entries_embed_html,
                        parent,
                        false
                    )
                viewHolder =
                    HolderEmbedHtmlEntries(
                        vEntriesHtml
                    )
            }
            ENTRIES_ONEDIO_EMBED -> {
                val vEntriesOnedioEmbed =
                    inflater.inflate(
                        R.layout.article_detail_holder_entries_onedio_embed,
                        parent,
                        false
                    )
                viewHolder =
                    HolderOnedioEmbedEntries(
                        vEntriesOnedioEmbed
                    )
            }
            ENTRIES_RECOMMEND_WIDGED -> {
                val vEntriesRecommendWidget =
                    inflater.inflate(
                        R.layout.article_detail_item_recommend_widget,
                        parent,
                        false
                    )
                viewHolder =
                    HolderRecommendWidgetEntries(
                        vEntriesRecommendWidget
                    )
            }
        }

        return viewHolder

    }

    override fun getItemCount(): Int {
        return items?.size!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            ENTRIES_TEXT -> {
                val vhEntriesText = holder as HolderTextEntries
                configureViewHolderEntriesText(vhEntriesText, position)
            }
            ENTRIES_IMAGE -> {
                val vhEntriesImage = holder as HolderImageEntries
                configureViewHolderEntriesImage(vhEntriesImage, position)
            }
            ENTRIES_VIDEO -> {
                val vhEntriesVideo = holder as HolderVideoEntries
                configureViewHolderEntriesVideo(vhEntriesVideo, position)
            }
            ENTRIES_EMBED -> {
                val vhEntriesEmbed = holder as HolderEmbedHtmlEntries
                configureViewHolderEntriesEmbedHtml(vhEntriesEmbed, position)
            }
            ENTRIES_HTML -> {
                val vhEntriesHtml = holder as HolderEmbedHtmlEntries
                configureViewHolderEntriesEmbedHtml(vhEntriesHtml, position)
            }
            ENTRIES_ONEDIO_EMBED -> {
                val vhEntriesOnedioEmbed = holder as HolderOnedioEmbedEntries
                configureViewHolderEntriesOnedioEmbed(vhEntriesOnedioEmbed, position)
            }
            ENTRIES_RECOMMEND_WIDGED -> {
                val vhEntriesRecommendWidget = holder as HolderRecommendWidgetEntries
                configureViewHolderEntriesRecommendWidget(vhEntriesRecommendWidget, position)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        items?.get(position)?.let {
            return when (it.mode) {
                "text" -> ENTRIES_TEXT
                "image" -> ENTRIES_IMAGE
                "video" -> ENTRIES_VIDEO
                "html" -> ENTRIES_HTML
                "embed" -> ENTRIES_EMBED
                "oio-embed" -> ENTRIES_ONEDIO_EMBED
                "recommendWidget" -> ENTRIES_RECOMMEND_WIDGED
                else -> -1
            }
        } ?: run {
            return -1
        }
    }

    private fun configureViewHolderEntriesText(vh1: HolderTextEntries, position: Int) {

        vh1.bindItems(context, items?.get(position)!!, position, listener)

    }

    private fun configureViewHolderEntriesImage(vh1: HolderImageEntries, position: Int) {

        vh1.bindItems(context, items?.get(position)!!, position, listener)

    }

    private fun configureViewHolderEntriesVideo(vh1: HolderVideoEntries, position: Int) {

        vh1.bindItems(context, items?.get(position)!!, position, listener)

    }

    private fun configureViewHolderEntriesEmbedHtml(vh1: HolderEmbedHtmlEntries, position: Int) {

        vh1.bindItems(context, items?.get(position)!!, position, listener)

    }

    private fun configureViewHolderEntriesOnedioEmbed(
        vh1: HolderOnedioEmbedEntries,
        position: Int
    ) {

        vh1.bindItems(context, items?.get(position)!!, position, listener)

    }

    private fun configureViewHolderEntriesRecommendWidget(vh1: HolderRecommendWidgetEntries, position: Int) {

        vh1.bindItems(context, items?.get(position)!!, position, listener)

    }


}