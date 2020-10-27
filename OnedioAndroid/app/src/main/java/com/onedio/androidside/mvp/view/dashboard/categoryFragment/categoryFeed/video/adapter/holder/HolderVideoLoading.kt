package com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.video.adapter.holder

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.onedio.androidside.R
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.food.holder.CategoryItem4Food
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.video.adapter.model.VideoAdapterModel

class HolderVideoLoading(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var loading: ProgressBar? =
        itemView.findViewById(R.id.loading) as ProgressBar

    fun bindItems(
        context: Context,
        item: VideoAdapterModel,
        listener: (VideoAdapterModel, String) -> Unit,
        pos: Int
    ) {

    }
}