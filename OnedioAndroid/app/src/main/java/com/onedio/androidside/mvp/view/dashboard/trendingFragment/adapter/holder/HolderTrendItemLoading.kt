package com.onedio.androidside.mvp.view.dashboard.trendingFragment.adapter.holder

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.onedio.androidside.R
import com.onedio.androidside.mvp.view.dashboard.trendingFragment.adapter.model.TrendingAdapterModel

class HolderTrendItemLoading(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var loading: ProgressBar? =
        itemView.findViewById(R.id.loading) as ProgressBar

    fun bindItems(
        context: Context,
        item: TrendingAdapterModel,
        listener: (TrendingAdapterModel, String) -> Unit,
        pos: Int
    ) {

    }
}