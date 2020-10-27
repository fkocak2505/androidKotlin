package com.onedio.androidside.mvp.view.dashboard.homeFragment.widgets.storyListView.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.onedio.androidside.R
import com.onedio.androidside.mvp.view.dashboard.homeFragment.widgets.storyListView.model.StoryListModel

class StoryListRecyclerViewAdapter(
    private val context: Context,
    private val storyListData: MutableList<StoryListModel>,
    private val listener: (Int) -> Unit
) : RecyclerView.Adapter<StoryListRecyclerViewAdapter.ModelViewHolder>() {


    class ModelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val storyListItemTitle = view.findViewById(R.id.storyItemTitle) as TextView
        private val storyListItemIcon = view.findViewById(R.id.storyItemIcon) as ImageView

        fun bindItems(item: StoryListModel, pos: Int, listener: (Int) -> Unit) {
            storyListItemTitle.text = item.title
            storyListItemIcon.setImageResource(item.icon)

            itemView.setOnClickListener {
                listener(pos)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.story_recycler_view_item, parent, false)
        return ModelViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return storyListData.size
    }

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        holder.bindItems(storyListData.get(position), position, listener)
    }


}