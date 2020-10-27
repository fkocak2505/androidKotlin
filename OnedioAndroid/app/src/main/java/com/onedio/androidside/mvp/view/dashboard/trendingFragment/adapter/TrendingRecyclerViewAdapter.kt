package com.onedio.androidside.mvp.view.dashboard.trendingFragment.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.trending_recycler_view_item_row.view.*
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.view.dashboard.homeFragment.widgets.hugeCardRecyclerViewAdapter.model.HugeCardModel

class TrendingRecyclerViewAdapter(
    private val context: Context,
    private var items: MutableList<HugeCardModel>,
    private val listener: (HugeCardModel, Int, String) -> Unit
) :
    RecyclerView.Adapter<TrendingRecyclerViewAdapter.ViewHoldere>() {

    fun addItems(items: MutableList<HugeCardModel>) {
        this.items.addAll(items)
        this.items = this.items.distinct().toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        p1: Int
    ): ViewHoldere {
        return ViewHoldere(
            LayoutInflater.from(parent.context).inflate(
                R.layout.trending_recycler_view_item_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHoldere, position: Int) {

        loadMostTrendingCoverPhoto(
            items[position].coverPhoto!!,
            holder.itemOfTrendCoverPhoto,
            holder.itemOfTrendCoverPhotoProgress
        )

        holder.itemOfTrendCoverPhotoText.text = items[position].coverPhotoText

        holder.itemOfTrendCoverPhotoText.typeface = OnedioCommon.changeTypeFace4Activity(context)

        holder.trendingConstraint.setOnClickListener{
            listener(items[position], position, "FEED")
        }

        holder.trendingCategory.setOnClickListener{
            listener(items[position], position, "CATEGORY")
        }

        if(items[position].articleAction != ""){
            holder.trendingCategoryBackground.visibility = View.VISIBLE
            holder.trendingCategory.visibility = View.VISIBLE

            loadImageWithoutProgress(items[position].articleAction!!, holder.trendingCategory)

        }else {
            holder.trendingCategoryBackground.visibility = View.GONE
            holder.trendingCategory.visibility = View.GONE
        }

        val sharedPref = context.getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        val theme = sharedPref.getString("mode", "default")

        if (theme == "dark") {
            holder.itemOfTrendCoverPhotoText.setTextColor(Color.parseColor("#FFFFFF"))
            holder.trendingCategoryBackground.setImageResource(R.drawable.bg_image_view_background_dark_mode)
        }else{
            holder.itemOfTrendCoverPhotoText.setTextColor(Color.parseColor("#191919"))
            holder.trendingCategoryBackground.setImageResource(R.drawable.bg_image_view_background)
        }

    }

    private fun loadMostTrendingCoverPhoto(
        coverPhoto: String,
        imageView: ImageView,
        progressBar: ProgressBar
    ) {
        if (coverPhoto.contains("gif", true))
            loadGifWithGlide(
                context,
                coverPhoto,
                imageView,
                progressBar
            )
        else
            loadImageWithProgress(coverPhoto, imageView, progressBar)
    }

    private fun loadImageWithProgress(
        imageUrl: String,
        imageView: ImageView,
        progressBar: ProgressBar
    ) {
        Picasso.get().load(imageUrl)
            .into(imageView, object : Callback {
                override fun onSuccess() {
                    imageView.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    imageView.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                    imageView.setImageResource(R.drawable.image_error_dark_mode)
                }
            })
    }

    private fun loadImageWithoutProgress(
        imageUrl: String,
        imageView: ImageView
    ) {
        Picasso.get().load(imageUrl)
            .into(imageView, object : Callback {
                override fun onSuccess() {
                    imageView.visibility = View.VISIBLE
                }

                override fun onError(e: Exception?) {
                    imageView.visibility = View.VISIBLE
                    imageView.setImageResource(R.drawable.image_error_dark_mode)
                }
            })
    }

    private fun loadGifWithGlide(
        context: Context,
        gifUrl: String,
        imageView: ImageView,
        progressBar: ProgressBar
    ) {
        Glide.with(context)
            .load(gifUrl)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    imageView.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                    imageView.setImageResource(R.drawable.image_error_dark_mode)
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
                    progressBar.visibility = View.GONE
                    return false
                }
            })
            .into(imageView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHoldere(view: View) : RecyclerView.ViewHolder(view) {
        val itemOfTrendCoverPhoto: ImageView = view.itemOfTrendCoverPhoto
        val itemOfTrendCoverPhotoProgress: ProgressBar = view.itemOfTrendCoverPhotoProgress
        val itemOfTrendCoverPhotoText: TextView = view.itemOfTrendCoverPhotoText
        val trendingConstraint: ConstraintLayout = view.trendingConstraint
        val trendingCategoryBackground: ImageView = view.trendingCategoryBackground
        val trendingCategory: ImageView = view.trendingCategory
    }


}