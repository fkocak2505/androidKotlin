package com.onedio.androidside.mvp.view.dashboard.homeFragment.articleComment.commentAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.onedio.androidside.mvp.model.dashboard.homeFragment.commentModel.CommentModel
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleComment.holder.HolderCommentChild
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleComment.holder.HolderCommentParent
import com.onedio.androidside.R

class CommentRecyclerViewAdapter(
    val context: Context,
    val items: MutableList<CommentModel>,
    val listener: (CommentModel, String, TextView, ImageView) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val COMMENT_PARENT = 0
    private val COMMENT_CHILD = 1

    private lateinit var viewHolder: RecyclerView.ViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        var inflater = LayoutInflater.from(parent.context)

        when (viewType) {
            COMMENT_PARENT -> {
                val vCommentParent = inflater.inflate(R.layout.comment_holder_comment_parent, parent, false)
                viewHolder =
                    HolderCommentParent(
                        vCommentParent
                    )
            }
            COMMENT_CHILD -> {
                val vCommentChild = inflater.inflate(R.layout.comment_holder_comment_child, parent, false)
                viewHolder =
                    HolderCommentChild(
                        vCommentChild
                    )
            }
        }

        return viewHolder

    }

    fun addItem(item: List<CommentModel>) {
        var oldItemsSize = items.size
        var newItemsSize = item.size
        items.addAll(item)
        //notifyDataSetChanged()
        notifyItemRangeInserted(oldItemsSize, newItemsSize)
    }

    fun removeAllItem(){
        items.clear()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            COMMENT_PARENT -> {
                var vhCommentParent = holder as HolderCommentParent
                configureViewHolderCommentParent(vhCommentParent, position)
            }
            COMMENT_CHILD -> {
                var vhCommentChild = holder as HolderCommentChild
                configureViewHolderCommentChild(vhCommentChild, position)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (items[position].type == "0") {
            return COMMENT_PARENT
        } else if (items[position].type == "1") {
            return COMMENT_CHILD
        }
        return -1
    }

    private fun configureViewHolderCommentParent(vh1: HolderCommentParent, position: Int) {
        //val emojiInformation = EmojiUtils.emojiInformation(items[position].text)

        vh1.bindItems(context,items[position], position, listener)

    }

    private fun configureViewHolderCommentChild(vh1: HolderCommentChild, position: Int) {
        //val emojiInformation = EmojiUtils.emojiInformation(items[position].text)

        vh1.bindItems(context,items[position], position, listener)

    }


}