package com.musicore.musicoreandroid.view.adapter.holder

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import android.graphics.drawable.Drawable
import android.widget.Toast
import androidx.navigation.Navigation
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.gson.Gson
import com.musicore.musicoreandroid.R
import com.musicore.musicoreandroid.manager.PreCachingLayoutManager
import com.musicore.musicoreandroid.model.WidgetsItem
import com.musicore.musicoreandroid.utils.DeviceUtils
import com.musicore.musicoreandroid.view.ProductListFragmentDirections
import com.musicore.musicoreandroid.view.adapter.holder.bannerSlider.BannerSliderAdapter
import com.musicore.musicoreandroid.view.adapter.holder.productSlider.ProductSliderAdapter

class HolderItemProductSlider(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val viewPool = RecyclerView.RecycledViewPool()

    private var productSliderRecyclerView: RecyclerView? =
        itemView.findViewById(R.id.productSliderRecyclerView) as RecyclerView

    private var productSliderTitle: TextView? =
        itemView.findViewById(R.id.productSliderTitle) as TextView

    fun bindItems(
        context: Context,
        item: WidgetsItem,
        listener: (View, WidgetsItem, String) -> Unit,
        pos: Int
    ) {

        productSliderTitle?.text = item.title

        val childEntriesLayoutManager =
            PreCachingLayoutManager(productSliderRecyclerView?.context!!, RecyclerView.HORIZONTAL, false)
        childEntriesLayoutManager.setExtraLayoutSpace(DeviceUtils.getScreenHeight(productSliderRecyclerView?.context))

        productSliderRecyclerView?.apply {
            layoutManager = childEntriesLayoutManager
            adapter =
                ProductSliderAdapter(productSliderRecyclerView?.context!!, item.products, item.displayCount!!) {view, itemOfProduct ->
                    Navigation.findNavController(view).navigate(
                        ProductListFragmentDirections.actionDetailFragment(
                        Gson().toJson(itemOfProduct)))
                }
            setRecycledViewPool(viewPool)
        }
    }
}