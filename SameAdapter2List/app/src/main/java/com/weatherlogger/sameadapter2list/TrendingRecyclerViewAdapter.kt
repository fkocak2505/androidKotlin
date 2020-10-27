package com.weatherlogger.sameadapter2list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.trending_recycler_view_item_row.view.*

class TrendingRecyclerViewAdapter(
    private val context: Context,
    private val items: MutableList<String>
) :
    RecyclerView.Adapter<TrendingRecyclerViewAdapter.ViewHoldere>() {

    fun addItems(items: MutableList<String>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        p1: Int
    ): ViewHoldere {
        return ViewHoldere(
            LayoutInflater.from(parent.context).inflate(
                R.layout.trending_recycler_view_item_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHoldere, position: Int) {



        holder.itemOfTrendCoverPhoto.text = items[position]


    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHoldere(view: View) : RecyclerView.ViewHolder(view) {
        val itemOfTrendCoverPhoto: TextView = view.aaaaa
    }


}