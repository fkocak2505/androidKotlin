package com.musicore.nestedrecyclerview.holder.entryAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.musicore.nestedrecyclerview.R
import com.musicore.nestedrecyclerview.holder.entryAdapter.holder.*
import com.musicore.nestedrecyclerview.model.EntriesItem

class ArticleEntriesAdapter(
    val context: Context,
    val items: MutableList<EntriesItem?>,
    val listener: (Int, EntriesItem) -> Unit
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

        var inflater = LayoutInflater.from(parent.context)

        when (viewType) {
            ENTRIES_TEXT -> {
                val vEntriesText =
                    inflater.inflate(R.layout.article_detail_holder_entries_text, parent, false)
                viewHolder =
                    HolderEntriesText(
                        vEntriesText
                    )
            }
            ENTRIES_IMAGE -> {
                val vEntriesImage =
                    inflater.inflate(R.layout.article_detail_holder_entries_image, parent, false)
                viewHolder =
                    HolderEntriesImage(
                        vEntriesImage
                    )
            }
            ENTRIES_VIDEO -> {
                val vEntriesVideo =
                    inflater.inflate(R.layout.article_detail_holder_entries_video, parent, false)
                viewHolder =
                    HolderEntriesVideo(
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
                    HolderEntriesEmbedHtml(
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
                    HolderEntriesEmbedHtml(
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
                    HolderEntriesOnedioEmbed(
                        vEntriesOnedioEmbed
                    )
            }
            /*ENTRIES_RECOMMEND_WIDGED -> {
                val vEntriesRecommendWidget =
                    inflater.inflate(
                        R.layout.article_detail_holder_entries_recommend_widget,
                        parent,
                        false
                    )
                viewHolder =
                    HolderEntriesRecommendWidget(
                        vEntriesRecommendWidget
                    )
            }*/
        }

        return viewHolder

    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            ENTRIES_TEXT -> {
                val vhEntriesText = holder as HolderEntriesText
                configureViewHolderEntriesText(vhEntriesText, position)
            }
            ENTRIES_IMAGE -> {
                val vhEntriesImage = holder as HolderEntriesImage
                configureViewHolderEntriesImage(vhEntriesImage, position)
            }
            ENTRIES_VIDEO -> {
                val vhEntriesVideo = holder as HolderEntriesVideo
                configureViewHolderEntriesVideo(vhEntriesVideo, position)
            }
            ENTRIES_EMBED -> {
                val vhEntriesEmbed = holder as HolderEntriesEmbedHtml
                configureViewHolderEntriesEmbedHtml(vhEntriesEmbed, position)
            }
            ENTRIES_HTML -> {
                val vhEntriesHtml = holder as HolderEntriesEmbedHtml
                configureViewHolderEntriesEmbedHtml(vhEntriesHtml, position)
            }
            ENTRIES_ONEDIO_EMBED -> {
                val vhEntriesHtml = holder as HolderEntriesOnedioEmbed
                configureViewHolderEntriesOnedioEmbed(vhEntriesHtml, position)
            }
            /*ENTRIES_RECOMMEND_WIDGED -> {
                val vhEntriesRecommendWidget = holder as HolderEntriesRecommendWidget
                configureViewHolderEntriesRecommendWidget(vhEntriesRecommendWidget, position)
            }*/
        }
    }

    override fun getItemViewType(position: Int): Int {
        items[position]?.let {
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

    private fun configureViewHolderEntriesText(vh1: HolderEntriesText, position: Int) {

        vh1.bindItems(context, items[position]!!, position, listener)

    }

    private fun configureViewHolderEntriesImage(vh1: HolderEntriesImage, position: Int) {

        vh1.bindItems(context, items[position]!!, position, listener)

    }

    private fun configureViewHolderEntriesVideo(vh1: HolderEntriesVideo, position: Int) {

        vh1.bindItems(context, items[position]!!, position, listener)

    }

    private fun configureViewHolderEntriesEmbedHtml(vh1: HolderEntriesEmbedHtml, position: Int) {

        vh1.bindItems(context, items[position]!!, position, listener)

    }

    private fun configureViewHolderEntriesOnedioEmbed(
        vh1: HolderEntriesOnedioEmbed,
        position: Int
    ) {

        vh1.bindItems(context, items[position]!!, position, listener)

    }

    /*private fun configureViewHolderEntriesRecommendWidget(vh1: HolderEntriesRecommendWidget, position: Int) {

        vh1.bindItems(context, items[position], position, listener)

    }*/


}