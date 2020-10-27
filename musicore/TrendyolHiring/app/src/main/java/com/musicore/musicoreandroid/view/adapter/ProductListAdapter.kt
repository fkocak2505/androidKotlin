package com.musicore.musicoreandroid.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.musicore.musicoreandroid.R
import com.musicore.musicoreandroid.model.WidgetsItem
import com.musicore.musicoreandroid.view.adapter.holder.HolderItemBannerSingle
import com.musicore.musicoreandroid.view.adapter.holder.HolderItemBannerSlider
import com.musicore.musicoreandroid.view.adapter.holder.HolderItemProductListing
import com.musicore.musicoreandroid.view.adapter.holder.HolderItemProductSlider

class ProductListAdapter(
    private val context: Context,
    private val response4PLayList: MutableList<WidgetsItem>,
    private val listener: (View, WidgetsItem, String) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val ITEM_BANNER_SLIDER = 1
    private val ITEM_BANNER_SINGLE = 2
    private val ITEM_PRODUCT_SLIDER = 3
    private val ITEM_PRODUCT_LISTING = 4

    private lateinit var viewHolder: RecyclerView.ViewHolder

    fun updatePlayList(newResponse4PLayList: MutableList<WidgetsItem>) {
        response4PLayList.clear()
        response4PLayList.addAll(newResponse4PLayList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val inflater = LayoutInflater.from(parent.context)

        when (viewType) {
            ITEM_BANNER_SINGLE -> {
                val vItemBannerSingle = inflater.inflate(R.layout.item_banner_single, parent, false)
                viewHolder =
                    HolderItemBannerSingle(
                        vItemBannerSingle
                    )
            }
            ITEM_BANNER_SLIDER -> {
                val vItemBannerSlider = inflater.inflate(R.layout.item_banner_slider, parent, false)
                viewHolder =
                    HolderItemBannerSlider(
                        vItemBannerSlider
                    )
            }
            ITEM_PRODUCT_SLIDER -> {
                val vItemProductSlider =
                    inflater.inflate(R.layout.item_product_slider, parent, false)
                viewHolder =
                    HolderItemProductSlider(
                        vItemProductSlider
                    )
            }
            ITEM_PRODUCT_LISTING -> {
                val vItemProductListing =
                    inflater.inflate(R.layout.item_product_listing, parent, false)
                viewHolder =
                    HolderItemProductListing(
                        vItemProductListing
                    )
            }

        }

        return viewHolder
    }

    override fun getItemCount() = response4PLayList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            ITEM_BANNER_SLIDER -> {
                val vItemBannerSliderr = holder as HolderItemBannerSlider
                configureViewHolderItemBannerSlider(vItemBannerSliderr, position)
            }
            ITEM_BANNER_SINGLE -> {
                val vItemBannerSingle = holder as HolderItemBannerSingle
                configureViewHolderItemBannerSingle(vItemBannerSingle, position)
            }
            ITEM_PRODUCT_SLIDER -> {
                val vItemProductSlider = holder as HolderItemProductSlider
                configureViewHolderItemProductSlider(vItemProductSlider, position)
            }
            ITEM_PRODUCT_LISTING -> {
                val vItemProductListing = holder as HolderItemProductListing
                configureViewHolderItemProductListing(vItemProductListing, position)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        when (response4PLayList[position].type + " " + response4PLayList[position].displayType) {
            "BANNER SINGLE" -> {
                return ITEM_BANNER_SINGLE
            }
            "BANNER SLIDER" -> {
                return ITEM_BANNER_SLIDER
            }
            "PRODUCT SLIDER" -> {
                return ITEM_PRODUCT_SLIDER
            }
            "PRODUCT LISTING" -> {
                return ITEM_PRODUCT_LISTING
            }else -> {
                return -1
            }
        }
    }

    private fun configureViewHolderItemBannerSlider(vh1: HolderItemBannerSlider, position: Int) {

        vh1.bindItems(context, response4PLayList[position], listener, position)

    }

    private fun configureViewHolderItemBannerSingle(vh2: HolderItemBannerSingle, position: Int) {

        vh2.bindItems(context, response4PLayList[position], listener, position)

    }

    private fun configureViewHolderItemProductSlider(vh3: HolderItemProductSlider, position: Int) {

        vh3.bindItems(context, response4PLayList[position], listener, position)

    }

    private fun configureViewHolderItemProductListing(vh4: HolderItemProductListing, position: Int) {

        vh4.bindItems(context, response4PLayList[position], listener, position)

    }

}