package com.musicore.musicoreandroid.view.adapter.productDetail

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.musicore.musicoreandroid.R
import com.smarteist.autoimageslider.SliderViewAdapter


class SliderWithAnimAdapter(
    private val context: Context,
    private var mSliderItems: MutableList<String>?
) : SliderViewAdapter<SliderWithAnimAdapter.SliderAdapterVH>() {


    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterVH {
        val inflate = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_detail_slider_item, null)
        return SliderAdapterVH(inflate)
    }

    override fun onBindViewHolder(viewHolder: SliderAdapterVH, position: Int) {

        loadGifWithGlide(context, mSliderItems?.get(position)!!, viewHolder.imageSlider)

    }

    override fun getCount(): Int {
        return mSliderItems!!.size
    }

    private fun loadGifWithGlide(context: Context, gifUrl: String, imageView: ImageView) {
        Glide.with(context)
            .load(gifUrl)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    imageView.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    imageView.visibility = View.VISIBLE
                    return false
                }
            })
            .into(imageView)
    }


    class SliderAdapterVH(itemView: View) :
        SliderViewAdapter.ViewHolder(itemView) {
        var imageSlider: ImageView = itemView.findViewById(R.id.imageSlider)

    }
}
