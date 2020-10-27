package com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.entriesAdapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.recommended.RecommendModel

class ArticleDetailRecommendedRecyclerViewAdapter(
    val context: Context,
    val recommendList: MutableList<RecommendModel>,
    private val listener: (RecommendModel) -> Unit
) : RecyclerView.Adapter<ArticleDetailRecommendedRecyclerViewAdapter.RecommendModelViewHolder>() {

    class RecommendModelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val itemRecommendDataImage: ImageView = view.findViewById(R.id.itemRecommendDataImage)
        private val itemRecommendTitle: TextView = view.findViewById(R.id.itemRecommendTitle)
        private val itemRecommendDataImageProgress: ProgressBar = view.findViewById(R.id.itemRecommendDataImageProgress)
        private val itemOfRecommentConstraint: ConstraintLayout = view.findViewById(R.id.recommentConstraint)

        fun bindItems(item: RecommendModel, pos: Int, context: Context, listener: (RecommendModel) -> Unit) {

            itemRecommendTitle.text = item.title
            loadImage(item.image, itemRecommendDataImage, itemRecommendDataImageProgress)

            itemRecommendTitle.typeface = OnedioCommon.changeTypeFace4Activity(context)
            itemRecommendTitle.setTextColor(Color.parseColor("#191919"))


            itemOfRecommentConstraint.setOnClickListener {
                listener(item)
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
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendModelViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.article_detail_recommend_item_card, parent, false)
        return RecommendModelViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return recommendList.size
    }

    override fun onBindViewHolder(holder: RecommendModelViewHolder, position: Int) {
        holder.bindItems(recommendList[position], position, context, listener)
    }

}