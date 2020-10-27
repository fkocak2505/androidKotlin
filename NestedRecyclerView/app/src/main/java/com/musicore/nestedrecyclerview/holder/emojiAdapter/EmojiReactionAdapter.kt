package com.musicore.nestedrecyclerview.holder.emojiAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.musicore.nestedrecyclerview.R
import com.musicore.nestedrecyclerview.model.ReactionsItem
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.article_detail_emoji_reaction_item.view.*

class EmojiReactionAdapter(
    val context: Context,
    val emojiReactions: MutableList<ReactionsItem?>,
    private val listener: (Int, ReactionsItem) -> Unit
):  RecyclerView.Adapter<EmojiReactionAdapter.ModelViewHolder>() {

    class ModelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val emojiItemCount: TextView = view.findViewById(R.id.emojiItemCount)
        private val emojiReactionItem: ImageView = view.findViewById(R.id.emojiReactionItem)
        private val emojiConstraintLayout: ConstraintLayout =
            view.findViewById(R.id.emojiConstraintLayout)
        private val statusLine: ConstraintLayout = view.findViewById(R.id.statusLine)

        private val constraintReaction: ConstraintLayout = view.findViewById(R.id.constraintReaction)

        fun bindItems(
            item: ReactionsItem,
            pos: Int,
            context: Context,
            listener: (Int, ReactionsItem) -> Unit
        ) {

            item.icons?.let {
                it.png?.let {
                    loadImageWithPicasso(
                        it,
                        emojiReactionItem
                    )
                }?: run{

                }
            }?: run{

            }


            emojiItemCount.text = item.count.toString()
            val lp = statusLine.layoutParams as ConstraintLayout.LayoutParams
            val firstHeight = lp.height

            item.count?.let {
                lp.height = firstHeight + (getModEmojiReactionCount(item.count))
                statusLine.layoutParams = lp
            }?: run{
                lp.height = firstHeight + (getModEmojiReactionCount(0))
                statusLine.layoutParams = lp
            }

            statusLine.background = ContextCompat.getDrawable(context, R.drawable.article_detail_emoji_status_line_layout_bg)

            emojiConstraintLayout.setOnClickListener {
                listener(pos, item)
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
        holder.bindItems(emojiReactions[position]!!, position, context,listener)
    }


}