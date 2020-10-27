package com.onedio.androidside.mvp.view.dashboard.search.moreTag.tagsArticle.adapter.holder

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.onedio.androidside.R
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.video.adapter.model.VideoAdapterModel
import com.onedio.androidside.mvp.view.dashboard.search.moreTag.tagsArticle.adapter.model.TagsAdapterModel

class HolderTagsLoader(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var loading: ProgressBar? =
        itemView.findViewById(R.id.loading) as ProgressBar

    fun bindItems(
        context: Context,
        item: TagsAdapterModel,
        listener: (TagsAdapterModel, String) -> Unit,
        pos: Int
    ) {

    }
}