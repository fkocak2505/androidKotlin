package com.onedio.androidside.mvp.view.dashboard.homeFragment.widgets.sportsWidgetRecyclerListAdapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.view.dashboard.homeFragment.widgets.testWidgetRecyclerListAdapter.model.TestWidgetModel

class SportsRecyclerListAdapter(
    val context: Context,
    val blogList: MutableList<TestWidgetModel>,
    private val listener: (String, TestWidgetModel) -> Unit
) :
    RecyclerView.Adapter<SportsRecyclerListAdapter.BlogWidgetModelViewHolder>() {

    class BlogWidgetModelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemSportImage: ImageView = view.findViewById(R.id.itemSportImage)
        val itemSportTitle: TextView = view.findViewById(R.id.itemSportTitle)
        val itemSportImageProgress: ProgressBar = view.findViewById(R.id.itemSportImageProgress)

        val sportCoverPhotoBadgeImage: ImageView = view.findViewById(R.id.sportCoverPhotoBadgeImage)
        val sportBadgeLineCovertPhoto: View = view.findViewById(R.id.sportBadgeLineCovertPhoto)

        fun bindItems(
            item: TestWidgetModel,
            pos: Int,
            context: Context,
            listener: (String, TestWidgetModel) -> Unit
        ) {


            itemSportTitle.text = item.testTitle
            loadImage(item.imageURL, itemSportImage, itemSportImageProgress)

            itemSportTitle.typeface = OnedioCommon.changeTypeFace4Activity(context)
            itemSportTitle.setTextColor(Color.parseColor("#191919"))

            if (item.badgeIcon != "") {
                sportCoverPhotoBadgeImage.visibility = View.INVISIBLE
                sportBadgeLineCovertPhoto.visibility = View.INVISIBLE

                loadImage2(item.badgeIcon, sportCoverPhotoBadgeImage)
            } else {
                sportCoverPhotoBadgeImage.visibility = View.INVISIBLE
                sportBadgeLineCovertPhoto.visibility = View.INVISIBLE
            }

            itemView.setOnClickListener {
                listener(OnedioConstant.GENERAL, item)
            }

            sportCoverPhotoBadgeImage.setOnClickListener {
                listener(OnedioConstant.BADGE_ICON, item)
            }

            sportBadgeLineCovertPhoto.setOnClickListener {
                listener(OnedioConstant.BADGE_ICON, item)
            }
        }

        private fun loadImage(imageUrl: String, imageView: ImageView, progressBar: ProgressBar) {
            Picasso.get().load(imageUrl)
                .into(imageView, object : Callback {
                    override fun onSuccess() {
                        progressBar.visibility = View.GONE
                    }

                    override fun onError(e: Exception?) {
                        progressBar.visibility = View.GONE
                        imageView.setImageResource(R.drawable.image_error_dark_mode)
                        /// Log. Errorr..
                    }
                })
        }

        private fun loadImage2(imageUrl: String, imageView: ImageView) {
            Picasso.get().load(imageUrl)
                .into(imageView, object : Callback {
                    override fun onSuccess() {

                    }

                    override fun onError(e: Exception?) {

                    }
                })
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogWidgetModelViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.sports_item_card, parent, false)
        return BlogWidgetModelViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return blogList.size
    }

    override fun onBindViewHolder(holder: BlogWidgetModelViewHolder, position: Int) {
        holder.bindItems(blogList[position], position, context, listener)
    }

}