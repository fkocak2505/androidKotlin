package com.musicore.musicoreandroid.view.adapter.holder

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.musicore.musicoreandroid.R
import com.musicore.musicoreandroid.manager.PreCachingLayoutManager
import com.musicore.musicoreandroid.model.WidgetsItem
import com.musicore.musicoreandroid.utils.DeviceUtils
import com.musicore.musicoreandroid.view.ProductListFragmentDirections
import com.musicore.musicoreandroid.view.adapter.holder.bannerSlider.BannerSliderAdapter

class HolderItemBannerSlider(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val viewPool = RecyclerView.RecycledViewPool()

    private var bannerSliderRecyclerView: RecyclerView? =
        itemView.findViewById(R.id.bannerSliderRecyclerView) as RecyclerView

    private var bannerSliderTitle: TextView? =
        itemView.findViewById(R.id.bannerSliderTitle) as TextView


    fun bindItems(
        context: Context,
        item: WidgetsItem,
        listener: (View, WidgetsItem, String) -> Unit,
        pos: Int
    ) {

        bannerSliderTitle?.text = item.title

        val childEntriesLayoutManager =
            PreCachingLayoutManager(
                bannerSliderRecyclerView?.context!!,
                RecyclerView.HORIZONTAL,
                false
            )
        childEntriesLayoutManager.setExtraLayoutSpace(
            DeviceUtils.getScreenHeight(
                bannerSliderRecyclerView?.context
            )
        )

        bannerSliderRecyclerView?.apply {
            layoutManager = childEntriesLayoutManager
            adapter =
                BannerSliderAdapter(
                    bannerSliderRecyclerView?.context!!,
                    item.bannerContents,
                    item.displayCount!!
                ) { view, _ ->
                    val sharedPreferences =
                        context.getSharedPreferences("DUMMY", Context.MODE_PRIVATE)
                    val productData = sharedPreferences.getString("PRODUCT_ITEM", "")

                    Navigation.findNavController(view).navigate(
                        ProductListFragmentDirections.actionDetailFragment(productData)
                    )


                }
            setRecycledViewPool(viewPool)
        }


    }
}