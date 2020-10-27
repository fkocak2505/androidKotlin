package com.onedio.androidside.mvp.view.dashboard.homeFragment.sliderListAdapter

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.denzcoskun.imageslider.models.SlideModel
import com.onedio.androidside.R
import com.onedio.androidside.constant.OnedioConstant
import kotlinx.android.synthetic.main.second_slider_recycler_item.view.*

class SecondSliderHugeCard(
    private val context: Context,
    private val secondSliderList: MutableList<SlideModel>,
    private val listener: (SlideModel, Int) -> Unit
) : RecyclerView.Adapter<SecondSliderHugeCard.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.second_slider_recycler_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return secondSliderList.size
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {

        secondSliderList[position].imageUrl?.let {
            if (it.trim().isNotEmpty())
                loadGifWithGlide(
                    context,
                    secondSliderList[position].imageUrl!!,
                    holder.itemOfImageView
                )
            else
                holder.itemOfImageView.setImageResource(R.drawable.image_error_dark_mode)
        } ?: run {
            holder.itemOfImageView.setImageResource(R.drawable.image_error_dark_mode)
        }

        holder.itemOfTextView.text = secondSliderList[position].title

        /*holder.itemOfTextView.setOnClickListener {
            listener(secondSliderList[position], position)
        }

        holder.itemOfImageView.setOnClickListener {
            listener(secondSliderList[position], position)
        }

        holder.itemOfRelativeLayout.setOnClickListener {
            listener(secondSliderList[position], position)
        }*/

        holder.itemView.setOnClickListener {
            listener(secondSliderList[position], position)
        }

        val sharedPref =
            context.getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        val theme = sharedPref?.getString("mode", "default")!!

        if (theme == "dark") {
            holder.itemOfCardView.setCardBackgroundColor(Color.parseColor("#39444e"))
            holder.itemOfTextView.setTextColor(Color.parseColor("#FFFFFF"))
        } else {
            holder.itemOfCardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
            holder.itemOfTextView.setTextColor(Color.parseColor("#191919"))
        }

    }

    private fun loadGifWithGlide(context: Context, gifUrl: String, imageView: ImageView) {
        var requestOption = RequestOptions()
        requestOption = requestOption.transforms(CenterCrop(), RoundedCorners(20))

        Glide.with(context)
            .load(gifUrl)
            .apply(requestOption)
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

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemOfRelativeLayout: RelativeLayout = itemView.itemOfRelativeLayout
        val itemOfTextView: TextView = itemView.itemOfTextView
        val itemOfImageView: ImageView = itemView.itemOfImageView
        val itemOfCardView: CardView = itemView.itemOfCardView
    }


}