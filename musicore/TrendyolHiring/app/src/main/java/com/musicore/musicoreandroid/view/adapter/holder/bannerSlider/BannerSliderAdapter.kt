package com.musicore.musicoreandroid.view.adapter.holder.bannerSlider

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.musicore.musicoreandroid.R
import com.musicore.musicoreandroid.model.BannerContentsItem
import com.musicore.musicoreandroid.view.MainActivity

class BannerSliderAdapter(
    private val context: Context,
    private val contentsItem: MutableList<BannerContentsItem>,
    private val displayCount: Int,
    private val listener: (View, BannerContentsItem) -> Unit
) : RecyclerView.Adapter<BannerSliderAdapter.ViewHolder>() {

    private var screenWidth = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val displayMetrics = DisplayMetrics()
        (context as MainActivity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        screenWidth = displayMetrics.widthPixels

        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.banner_slider_list_item, parent, false)

        return ViewHolder(cellForRow)

        //return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.bindItems(context, contentsItem[position], listener, screenWidth, displayCount, position)
    }

    override fun getItemCount(): Int {
        return contentsItem.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(
            context: Context,
            contentsItem: BannerContentsItem,
            listener: (View, BannerContentsItem) -> Unit,
            screenWidth: Int,
            displayCount: Int,
            position: Int
        ) {
            val bannerSliderImageView = itemView.findViewById<ImageView>(R.id.bannerSliderImageView)
            val bannerSliderTextView = itemView.findViewById<TextView>(R.id.bannerSliderTextView)
            val bannerSliderConstraint =
                itemView.findViewById<ConstraintLayout>(R.id.bannerSliderConstraint)



            bannerSliderTextView.text = contentsItem.title

            val itemWidth = screenWidth / (displayCount.toDouble() + 0.33)

            val lp = bannerSliderConstraint.layoutParams
            lp.height = lp.height
            lp.width = itemWidth.toInt()
            bannerSliderConstraint.layoutParams = lp

            loadGifWithGlide(context, contentsItem.imageUrl!!, bannerSliderImageView)

            bannerSliderImageView.setOnClickListener {
                listener(it, contentsItem)
            }

            bannerSliderTextView.setOnClickListener {
                listener(it, contentsItem)
            }
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
    }


}