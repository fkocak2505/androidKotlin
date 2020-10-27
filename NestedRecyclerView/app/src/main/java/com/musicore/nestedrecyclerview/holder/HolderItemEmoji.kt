package com.musicore.nestedrecyclerview.holder

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.musicore.nestedrecyclerview.R
import com.musicore.nestedrecyclerview.holder.emojiAdapter.EmojiReactionAdapter
import com.musicore.nestedrecyclerview.model.ArticleDetailAdapterModel

class HolderItemEmoji(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val viewPool = RecyclerView.RecycledViewPool()

    private var emojiReactionsRecyclerView: RecyclerView? =
        itemView.findViewById(R.id.emojiReactionsRecyclerView) as RecyclerView

    fun bindItems(
        context: Context,
        item: ArticleDetailAdapterModel,
        listener: (Int, ArticleDetailAdapterModel, String) -> Unit,
        pos: Int
    ) {

        val childLayoutManager =
            LinearLayoutManager(emojiReactionsRecyclerView?.context, RecyclerView.HORIZONTAL, false)
        childLayoutManager.initialPrefetchItemCount = 10
        emojiReactionsRecyclerView?.apply {
            layoutManager = childLayoutManager
            adapter = EmojiReactionAdapter(emojiReactionsRecyclerView?.context!! ,item.data.data?.reactions!!){ pos, itemofReaction ->
                Toast.makeText(context, (itemofReaction.count?.plus(1)).toString(), Toast.LENGTH_SHORT).show()
            }
            setRecycledViewPool(viewPool)
        }

    }
}