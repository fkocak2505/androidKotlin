package com.musicore.nestedrecyclerview.holder

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.musicore.nestedrecyclerview.ChildAdapter
import com.musicore.nestedrecyclerview.ParentModel
import com.musicore.nestedrecyclerview.R

class HolderItemThree(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val viewPool = RecyclerView.RecycledViewPool()

    private var textView: TextView? =
        itemView.findViewById(R.id.textView) as TextView

    private var rvChild: RecyclerView? =
        itemView.findViewById(R.id.rv_child) as RecyclerView

    fun bindItems(
        context: Context,
        item: ParentModel,
        listener: (String) -> Unit,
        pos: Int
    ){

        textView?.text = item.title

        val childLayoutManager =
            LinearLayoutManager(rvChild?.context, RecyclerView.HORIZONTAL, false)
        childLayoutManager.initialPrefetchItemCount = 10
        rvChild?.apply {
            layoutManager = childLayoutManager
            adapter = ChildAdapter(rvChild?.context!! ,item.children){
                listener(it)
            }
            setRecycledViewPool(viewPool)
        }

        itemView.setOnClickListener {
            listener(pos.toString())
        }
    }

}