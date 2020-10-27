package com.weatherlogger.videoviewwithrecyclerview


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.RequestManager

import java.util.ArrayList

class VideoPlayerRecyclerAdapter(
    private val mediaObjects: ArrayList<MediaObject>,
    private val requestManager: RequestManager
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int {
        return mediaObjects.size
    }

    @NonNull
    override fun onCreateViewHolder(@NonNull viewGroup: ViewGroup, i: Int): RecyclerView.ViewHolder {
        return VideoPlayerViewHolder(
            LayoutInflater.from(viewGroup.context).inflate(
                R.layout.layout_video_list_item,
                viewGroup,
                false
            )
        )
    }

    override fun onBindViewHolder(@NonNull viewHolder: RecyclerView.ViewHolder, i: Int) {
        (viewHolder as VideoPlayerViewHolder).onBind(mediaObjects[i], requestManager)
    }

}
