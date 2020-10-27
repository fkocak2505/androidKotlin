package com.example.paginationrecylerviewwithoutlib

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class RecyclerViewAdapter(private var mItemList: List<String>?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_ITEM) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
            ItemViewHolder(view)
        } else {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_loading, parent, false)
            LoadingViewHolder(view)
        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {

        if (viewHolder is ItemViewHolder) {
            populateItemRows(viewHolder, position)
        } else if (viewHolder is LoadingViewHolder) {
            showLoadingView(viewHolder, position)
        }
    }

    override fun getItemCount(): Int {
        return if (mItemList == null) 0 else mItemList!!.size
    }


    override fun getItemViewType(position: Int): Int {
        return if (mItemList!![position] == "null") VIEW_TYPE_LOADING else VIEW_TYPE_ITEM
    }


    private inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tvItem: TextView = itemView.findViewById(R.id.tvItem)
        var imageView: ImageView = itemView.findViewById(R.id.imageView)
        var foodCoverPhotoProgress: ProgressBar = itemView.findViewById(R.id.foodCoverPhotoProgress)
    }

    private inner class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var progressBar: ProgressBar = itemView.findViewById(R.id.progressBar)

    }

    private fun showLoadingView(viewHolder: LoadingViewHolder, position: Int) {

        viewHolder.progressBar.visibility = View.VISIBLE

    }

    private fun populateItemRows(viewHolder: ItemViewHolder, position: Int) {

        val item = mItemList!![position]
        viewHolder.tvItem.text = item

        Picasso.get().load("https://img-s2.onedio.com/id-596a3adbac6b91a7196c6361/rev-0/w-900/h-500/f-jpg/s-b7a766fe19b6d1e8ed26a7a2c6f19d9e3d99f647.jpg")
            .into(viewHolder.imageView, object : Callback {
                override fun onSuccess() {
                    viewHolder.foodCoverPhotoProgress.visibility = View.INVISIBLE
                }

                override fun onError(e: Exception?) {
                    viewHolder.foodCoverPhotoProgress.visibility = View.VISIBLE
                }
            })
    }
}