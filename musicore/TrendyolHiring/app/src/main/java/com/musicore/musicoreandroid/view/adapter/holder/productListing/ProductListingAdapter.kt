package com.musicore.musicoreandroid.view.adapter.holder.productListing

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
import com.musicore.musicoreandroid.model.ProductsItem
import com.musicore.musicoreandroid.view.MainActivity
import com.musicore.musicoreandroid.view.adapter.holder.productSlider.ProductSliderAdapter

class ProductListingAdapter(
    private val context: Context,
    private val productsItem: MutableList<ProductsItem>,
    private val displayCount: Int,
    private val listener: (View, ProductsItem) -> Unit
) : RecyclerView.Adapter<ProductListingAdapter.ViewHolder>() {

    private var screenWidth = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val displayMetrics = DisplayMetrics()
        (context as MainActivity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        screenWidth = displayMetrics.widthPixels

        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.product_listing_item, parent, false)

        return ViewHolder(cellForRow)

        /*return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout., parent, false)
        )*/
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.bindItems(context, productsItem[position], screenWidth, displayCount, listener)
    }

    override fun getItemCount(): Int {
        return productsItem.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(
            context: Context,
            productsItem: ProductsItem,
            screenWidth: Int,
            displayCount: Int,
            listener: (View, ProductsItem) -> Unit
        ) {
            val productListingImageView =
                itemView.findViewById<ImageView>(R.id.productListingImageView)
            val productListingTextView =
                itemView.findViewById<TextView>(R.id.productListingTextView)
            val productListingConstraint =
                itemView.findViewById<ConstraintLayout>(R.id.productListingConstraint)

            val itemWidth = screenWidth / (displayCount.toDouble() + 0.33)

            val lp = productListingConstraint.layoutParams
            lp.height = lp.height
            lp.width = itemWidth.toInt()
            productListingConstraint.layoutParams = lp

            productListingTextView.text = productsItem.brandName

            loadGifWithGlide(context, productsItem.imageUrl!!, productListingImageView)

            itemView.setOnClickListener {
                listener(it, productsItem)
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