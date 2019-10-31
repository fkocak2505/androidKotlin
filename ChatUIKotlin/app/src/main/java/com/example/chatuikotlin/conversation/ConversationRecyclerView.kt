package com.example.chatuikotlin.conversation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chatuikotlin.R
import com.vanniktech.emoji.EmojiUtils

class ConversationRecyclerView(
    var context: Context,
    var items: MutableList<ChatData>,
    val listener: (Int) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val DATE = 0
    private val YOU = 1
    private val ME = 2

    private lateinit var viewHolder: RecyclerView.ViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        var inflater = LayoutInflater.from(parent.context)

        when (viewType) {
            DATE -> {
                val vDate = inflater.inflate(R.layout.layout_holder_date, parent, false)
                viewHolder = HolderDate(vDate)
            }
            YOU -> {
                val vYou = inflater.inflate(R.layout.layout_holder_you, parent, false)
                viewHolder = HolderYou(vYou)
            }
            ME -> {
                val vMe = inflater.inflate(R.layout.layout_holder_me, parent, false)
                viewHolder = HolderMe(vMe)
            }
        }

        return viewHolder

    }

    fun addItem(item: List<ChatData>) {
        items.addAll(item)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            DATE -> {
                var vhDate = holder as HolderDate
                configureViewHolderDate(vhDate, position)
            }
            YOU -> {
                var vhYou = holder as HolderYou
                configureViewHolderYou(vhYou, position)
            }
            ME -> {
                var vhMe = holder as HolderMe
                configureViewHolderMe(vhMe, position)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        //More to come
        if (items[position].type.equals("0")) {
            return DATE
        } else if (items[position].type.equals("1")) {
            return YOU
        } else if (items[position].type.equals("2")) {
            return ME
        }
        return -1
    }

    private fun configureViewHolderMe(vh1: HolderMe, position: Int) {
        val emojiInformation = EmojiUtils.emojiInformation(items[position].text)

        vh1.bindItems(context, items[position], emojiInformation, position, listener)

    }

    private fun configureViewHolderYou(vh1: HolderYou, position: Int) {
        val emojiInformation = EmojiUtils.emojiInformation(items[position].text)

        vh1.bindItems(context, items[position], emojiInformation, position, listener)
    }

    private fun configureViewHolderDate(vh1: HolderDate, position: Int) {
        vh1.getDate()?.text = items[position].text
    }


}