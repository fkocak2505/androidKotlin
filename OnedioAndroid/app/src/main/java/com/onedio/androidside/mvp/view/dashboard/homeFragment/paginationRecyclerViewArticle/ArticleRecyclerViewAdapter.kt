package com.onedio.androidside.mvp.view.dashboard.homeFragment.paginationRecyclerViewArticle

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.article_recycler_view_item_row.view.*
import com.onedio.androidside.R
import com.onedio.androidside.mvp.model.dashboard.homeFragment.dataModel.ArticleItemModel

class ArticleRecyclerViewAdapter(
    private val context: Context,
    private val items: MutableList<ArticleItemModel>,
    private val listener: (Int, String, View, View) -> Unit
) :
    RecyclerView.Adapter<ArticleRecyclerViewAdapter.ViewHoldere>() {

    fun addItems(items: ArrayList<ArticleItemModel>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHoldere {
        return ViewHoldere(
            LayoutInflater.from(parent.context).inflate(
                R.layout.article_recycler_view_item_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHoldere, position: Int) {
        //holder.coverPhoto.setImageResource(items[position].coverPhoto)

        Picasso.get().load(items[position].coverPhoto)
            .into(holder.coverPhoto, object : Callback {
                override fun onSuccess() {
                    holder.coverPhotoProgress.visibility = View.GONE

                }

                override fun onError(e: Exception?) {
                    holder.coverPhotoProgress.visibility = View.GONE
                    /// Log. Errorr..
                }
            })

        /*Picasso.get().load(items[position].articleActionIcon)
            .into(holder.articleAction, object : Callback {
                override fun onSuccess() {
                    holder.articleActionPhotoProgress.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    holder.articleActionPhotoProgress.visibility = View.GONE
                    /// Log. Errorr..
                }
            })*/

        holder.coverPhotoText.text = items[position].coverPhotoText
        holder.coverPhotoActionImage.setImageResource(items[position].coverPhotoActionImage)
        //holder.articleAction.setImageResource(items[position].articleAction)
        holder.feedTitle.text = items[position].feedTitle
        holder.feedDate.text = items[position].feedDate
        holder.emojiAction.text = items[position].emojiAction
        holder.emojiAction2.text = items[position].emojiAction2
        holder.emojiAction3.text  = items[position].emojiAction3
        holder.emojiActionCount.text = items[position].emojiActionCount
        holder.commentCount.text = items[position].commentCount.toString()

        holder.itemView.setOnClickListener{
            listener(position, "General", holder.itemView, holder.itemView)
        }

        holder.addFavoriteText.setOnClickListener{
            listener(position, "Favorite", holder.addFavoriteIcon, holder.addFavoriteText)
        }

        holder.doCommentText.setOnClickListener{
            listener(position, "Comment", holder.doCommentIcon,  holder.doCommentText)
        }

        holder.shareText.setOnClickListener{
            listener(position, "Share", holder.shareIcon, holder.shareText)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHoldere(view: View) : RecyclerView.ViewHolder(view) {
        val coverPhoto: ImageView = view.coverPhoto
        val coverPhotoProgress: ProgressBar = view.coverPhotoProgress
        val coverPhotoText: TextView = view.coverPhotoText
        val coverPhotoActionImage: ImageView = view.coverPhotoActionImage
        val articleAction: ImageView = view.articleAction
        val articleActionPhotoProgress: ProgressBar = view.articleActionPhotoProgress
        val feedTitle: TextView = view.feedTitle
        val feedDate: TextView = view.feedDate
        val emojiAction: TextView = view.emojiAction
        val emojiAction2: TextView = view.emojiAction2
        val emojiAction3: TextView = view.emojiAction3
        val emojiActionCount: TextView = view.emojiActionCount
        val commentCount: TextView = view.commentCount

        val addFavoriteText: TextView = view.addFavoriteText
        val addFavoriteIcon: ImageView = view.addFavoriteIcon

        val doCommentText: TextView = view.doCommentText
        val doCommentIcon: ImageView = view.doCommentIcon

        val shareText: TextView = view.shareText
        val shareIcon: ImageView = view.shareIcon
    }

}