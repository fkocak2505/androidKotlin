package com.musicore.recyclerviewinsidescrollview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_of_recycler_view.view.*

class CategoryFeedRecyclerViewAdapter(
    private val context: Context,
    private var items: MutableList<String>,
    private val listener: (String, Int) -> Unit
) :
    RecyclerView.Adapter<CategoryFeedRecyclerViewAdapter.ViewHolder4CategoryDetailItem>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        p1: Int
    ): ViewHolder4CategoryDetailItem {
        return ViewHolder4CategoryDetailItem(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_of_recycler_view,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: ViewHolder4CategoryDetailItem,
        position: Int
    ) {

        ///// Set Text TypeFace
        holder.itemofRecyclerView.text = items[position]
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder4CategoryDetailItem(view: View) : RecyclerView.ViewHolder(view) {
        val itemofRecyclerView: TextView = view.itemofRecyclerView
    }

}