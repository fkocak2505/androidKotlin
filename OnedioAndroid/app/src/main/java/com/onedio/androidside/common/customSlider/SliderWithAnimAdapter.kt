package com.onedio.androidside.common.customSlider

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.denzcoskun.imageslider.models.SlideModel
import com.onedio.androidside.R
import com.smarteist.autoimageslider.SliderViewAdapter


class SliderWithAnimAdapter(
    private val context: Context,
    private var mSliderItems: MutableList<SlideModel>?,
    private val listener: (SlideModel, Int) -> Unit
) : SliderViewAdapter<SliderWithAnimAdapter.SliderAdapterVH>() {


    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterVH {
        val inflate = LayoutInflater.from(parent.context)
            .inflate(R.layout.second_slider_image_layout_item, null)
        return SliderAdapterVH(inflate)
    }

    override fun onBindViewHolder(viewHolder: SliderAdapterVH, position: Int) {

        viewHolder.textViewDescription.text = mSliderItems!![position].title

        if(mSliderItems!![position].imageUrl != ""){
            //viewHolder.imageViewBackground.loadImage(context, mSliderItems!![position].imageUrl, R.drawable.image_error_dark_mode)
            Glide.with(viewHolder.itemView)
                .load(mSliderItems!![position].imageUrl)
                .into(viewHolder.imageViewBackground)
        }else{
            viewHolder.imageViewBackground.setImageResource(R.drawable.image_error_dark_mode)
        }



        viewHolder.sliderItem.setOnClickListener {
            listener(mSliderItems!![position], position)
        }
    }

    override fun getCount(): Int {
        //slider view count could be dynamic size
        return mSliderItems!!.size
    }


    class SliderAdapterVH(itemView: View) :
        SliderViewAdapter.ViewHolder(itemView) {
        var imageViewBackground: ImageView = itemView.findViewById(R.id.iv_auto_image_slider)
        var imageGifContainer: ImageView = itemView.findViewById(R.id.iv_gif_container)
        var textViewDescription: TextView = itemView.findViewById(R.id.tv_auto_image_slider)
        var sliderItem: FrameLayout = itemView.findViewById(R.id.sliderItem)

    }
}
