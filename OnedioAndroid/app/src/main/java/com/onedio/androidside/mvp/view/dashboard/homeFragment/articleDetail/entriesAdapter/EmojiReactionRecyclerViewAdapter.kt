package com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.entriesAdapter

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.emojiReaction.EmojiReactionModel

class EmojiReactionRecyclerViewAdapter(
    val context: Context,
    val emojiCodeArr: MutableList<String>,
    val emojiReactions: MutableList<EmojiReactionModel>,
    private val listener: (Int, View, Int, Int, TextView) -> Unit
) :
    RecyclerView.Adapter<EmojiReactionRecyclerViewAdapter.ModelViewHolder>() {

    class ModelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val emojiItemCount: TextView = view.findViewById(R.id.emojiItemCount)
        private val emojiReactionItem: ImageView = view.findViewById(R.id.emojiReactionItem)
        private val emojiConstraintLayout: ConstraintLayout =
            view.findViewById(R.id.emojiConstraintLayout)
        private val statusLine: ConstraintLayout = view.findViewById(R.id.statusLine)

        private val constraintReaction: ConstraintLayout = view.findViewById(R.id.constraintReaction)



        fun bindItems(
            item: EmojiReactionModel,
            emojiCodeArr: MutableList<String>,
            pos: Int,
            context: Context,
            listener: (Int, View, Int, Int, TextView) -> Unit
        ) {

            loadImageWithPicasso(
                item.emojiReactionUrl,
                emojiReactionItem
            )

            emojiItemCount.typeface = OnedioCommon.changeTypeFace4ActivitySemiBold(context)

            emojiItemCount.text = item.count.toString()
            val lp = statusLine.layoutParams as ConstraintLayout.LayoutParams
            val firstHeight = lp.height

            lp.height = firstHeight + (getModEmojiReactionCount(item.count))
            statusLine.layoutParams = lp


            emojiCodeArr.find { it == item.code } ?. let {
                statusLine.background = ContextCompat.getDrawable(context, R.drawable.article_detail_emoji_status_line_layout_bg_selected)
            } ?: run{
                statusLine.background = ContextCompat.getDrawable(context, R.drawable.article_detail_emoji_status_line_layout_bg)
            }

            val sharedPref =
                context.getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
            val theme = sharedPref.getString("mode", "default")!!

            if(theme == "dark"){
                emojiConstraintLayout.background = ContextCompat.getDrawable(context, R.drawable.article_detail_emoji_layout_bg_dark_mode)
                emojiItemCount.setTextColor(Color.parseColor("#FFFFFF"))
            }else{
                emojiConstraintLayout.background = ContextCompat.getDrawable(context, R.drawable.article_detail_emoji_layout_bg)
                emojiItemCount.setTextColor(Color.parseColor("#191919"))
            }

            itemView.setOnClickListener{
                listener(pos, statusLine, firstHeight, item.count, emojiItemCount)
            }

        }

        private fun loadImageWithPicasso(
            imageUrl: String,
            imageView: ImageView
        ) {
            Picasso.get().load(imageUrl)
                .into(imageView, object : Callback {
                    override fun onSuccess() {

                    }

                    override fun onError(e: Exception?) {

                    }
                })
        }

        private fun getModEmojiReactionCount(count: Int): Int {
            return when (count) {
                0 -> 0
                in 0..1000 -> 5
                in 1000..3000 -> 10
                else -> 10 + (count / 1000) * 2
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.article_detail_emoji_reaction_item, parent, false)
        return ModelViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return emojiReactions.size
    }

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        holder.bindItems(emojiReactions[position], emojiCodeArr, position, context,listener)
    }


}