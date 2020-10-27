package com.example.recyclerviewwithwebview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class EntriesRecyclerViewAdapter(
    val context: Context,
    val items: MutableList<EntriesModel>,
    val listener: (Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val ENTRIES_TEXT = 0
    private val ENTRIES_IMAGE = 1
    private val ENTRIES_VIDEO = 2

    private lateinit var viewHolder: RecyclerView.ViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        var inflater = LayoutInflater.from(parent.context)

        when (viewType) {
            ENTRIES_TEXT -> {
                val vEntriesText =
                    inflater.inflate(R.layout.article_detail_holder_entries_text, parent, false)
                viewHolder = HolderEntriesText(vEntriesText)
            }
            ENTRIES_IMAGE -> {
                val vEntriesImage =
                    inflater.inflate(R.layout.article_detail_holder_entries_image, parent, false)
                viewHolder = HolderEntriesImage(vEntriesImage)
            }
            ENTRIES_VIDEO -> {
                val vEntriesVideo =
                    inflater.inflate(R.layout.article_detail_holder_entries_video, parent, false)
                viewHolder = HolderEntriesVideo(vEntriesVideo)
            }
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
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position].mode) {
            "text" -> ENTRIES_TEXT
            "image" -> ENTRIES_IMAGE
            "video" -> ENTRIES_VIDEO
            else -> -1
        }

    }

    private fun configureViewHolderEntriesText(vh1: HolderEntriesText, position: Int) {

        vh1.bindItems(context, items[position], position, listener)

    }

    private fun configureViewHolderEntriesImage(vh1: HolderEntriesImage, position: Int) {

        vh1.bindItems(context, items[position], position, listener)

    }

    private fun configureViewHolderEntriesVideo(vh1: HolderEntriesVideo, position: Int) {

        vh1.bindItems(context, items[position], position, listener)

    }


}