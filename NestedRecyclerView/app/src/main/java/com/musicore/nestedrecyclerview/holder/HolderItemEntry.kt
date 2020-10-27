package com.musicore.nestedrecyclerview.holder

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.musicore.nestedrecyclerview.DeviceUtils
import com.musicore.nestedrecyclerview.PreCachingLayoutManager
import com.musicore.nestedrecyclerview.R
import com.musicore.nestedrecyclerview.holder.entryAdapter.ArticleEntriesAdapter
import com.musicore.nestedrecyclerview.model.ArticleDetailAdapterModel

class HolderItemEntry(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val viewPool = RecyclerView.RecycledViewPool()

    private var entryRecyclerView: RecyclerView? =
        itemView.findViewById(R.id.entryRecyclerView) as RecyclerView

    fun bindItems(
        context: Context,
        item: ArticleDetailAdapterModel,
        listener: (Int, ArticleDetailAdapterModel, String) -> Unit,
        pos: Int
    ) {

        val childEntriesLayoutManager =
            PreCachingLayoutManager(entryRecyclerView?.context!!, RecyclerView.VERTICAL, false)
        childEntriesLayoutManager.setExtraLayoutSpace(DeviceUtils.getScreenHeight(entryRecyclerView?.context))

        /*val childEntriesLayoutManager =
            PreCachingLayoutManager(entryRecyclerView?.context!!, RecyclerView.VERTICAL, false)
        childEntriesLayoutManager.initialPrefetchItemCount = 5*/

        entryRecyclerView?.apply {
            layoutManager = childEntriesLayoutManager
            adapter =
                ArticleEntriesAdapter(entryRecyclerView?.context!!, item.data.data?.entries!!) { itemPos, itemEntries ->
                    //listener(itemPos, item, "")
                    Toast.makeText(context, itemEntries.embedArticle?.title, Toast.LENGTH_SHORT).show()
                }
            setRecycledViewPool(viewPool)
        }
    }

}