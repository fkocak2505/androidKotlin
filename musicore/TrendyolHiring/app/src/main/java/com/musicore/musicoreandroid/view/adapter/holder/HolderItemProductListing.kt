package com.musicore.musicoreandroid.view.adapter.holder

import android.content.Context
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.musicore.musicoreandroid.R
import com.musicore.musicoreandroid.manager.PreCachingLayoutManager
import com.musicore.musicoreandroid.model.WidgetsItem
import com.musicore.musicoreandroid.utils.DeviceUtils
import com.musicore.musicoreandroid.view.ProductListFragmentDirections
import com.musicore.musicoreandroid.view.adapter.holder.productListing.ProductListingAdapter

class HolderItemProductListing(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val viewPool = RecyclerView.RecycledViewPool()

    private var productListingRecyclerView: RecyclerView? =
        itemView.findViewById(R.id.productListingRecyclerView) as RecyclerView

    private var productListingTitle: TextView? =
        itemView.findViewById(R.id.productListingTitle) as TextView


    fun bindItems(
        context: Context,
        item: WidgetsItem,
        listener: (View, WidgetsItem, String) -> Unit,
        pos: Int
    ) {

        productListingTitle?.text = item.title

        val childEntriesLayoutManager =
            PreCachingLayoutManager(
                productListingRecyclerView?.context!!,
                RecyclerView.HORIZONTAL,
                false
            )
        childEntriesLayoutManager.setExtraLayoutSpace(
            DeviceUtils.getScreenHeight(
                productListingRecyclerView?.context
            )
        )

        productListingRecyclerView?.apply {
            layoutManager = childEntriesLayoutManager
            adapter =
                ProductListingAdapter(
                    productListingRecyclerView?.context!!,
                    item.products,
                    item.displayCount!!
                ) { view, itemOfProduct ->
                    Navigation.findNavController(view).navigate(
                        ProductListFragmentDirections.actionDetailFragment(
                            Gson().toJson(itemOfProduct)))
                }
            setRecycledViewPool(viewPool)
        }

    }
}