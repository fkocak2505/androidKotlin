package com.musicore.nestedrecyclerview.holder.commentAdapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.musicore.nestedrecyclerview.R
import com.musicore.nestedrecyclerview.model.CommentsItem
import com.musicore.nestedrecyclerview.model.ReactionsItem
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat

class CommentAdapter(
    val context: Context,
    val emojiReactions: MutableList<CommentsItem?>,
    private val listener: (Int, CommentsItem, String) -> Unit
) : RecyclerView.Adapter<CommentAdapter.ModelViewHolder>() {

    class ModelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val commentProfilePhoto: ImageView = view.findViewById(R.id.commentProfilePhoto)
        private val commentUserName: TextView = view.findViewById(R.id.commentUserName)
        private val commentTime: TextView = view.findViewById(R.id.commentTime)
        private val commentText: TextView = view.findViewById(R.id.commentText)
        private val likeCount: TextView = view.findViewById(R.id.likeCount)

        private val likeIcon: ImageView = view.findViewById(R.id.likeIcon)
        private val likeText: TextView = view.findViewById(R.id.likeText)
        private val answerCommentIcon: ImageView = view.findViewById(R.id.answerCommentIcon)
        private val answerCommentText: TextView = view.findViewById(R.id.answerCommentText)


        fun bindItems(
            item: CommentsItem,
            pos: Int,
            context: Context,
            listener: (Int, CommentsItem, String) -> Unit
        ) {

            item.user?.let {
                it.username?.let {
                    commentUserName.text = it
                } ?: run {
                    commentUserName.text = ""
                }
            } ?: run {
                commentUserName.text = ""
            }

            item.createDate?.let {
                commentTime.text = convertFeedDate(it)
            } ?: run {
                commentTime.text = ""
            }

            item.text?.let {
                commentText.text = it
            } ?: run {
                commentText.text = ""
            }

            item.ratings?.let {
                it.likes?.let {
                    likeCount.text = it.toString()
                } ?: run {
                    likeCount.text = "0"
                }
            } ?: run {
                likeCount.text = "0"
            }

            item.user?.let {
                it.image?.let {
                    it.alternates?.let {
                        it.standardResolution?.let {
                            it.url?.let {
                                loadURLImage(it, commentProfilePhoto)
                            } ?: run {
                                commentProfilePhoto.setImageResource(R.drawable.empty_avatar)
                            }
                        } ?: run {
                            commentProfilePhoto.setImageResource(R.drawable.empty_avatar)
                        }
                    } ?: run {
                        commentProfilePhoto.setImageResource(R.drawable.empty_avatar)
                    }
                } ?: run {
                    commentProfilePhoto.setImageResource(R.drawable.empty_avatar)
                }
            } ?: run {
                commentProfilePhoto.setImageResource(R.drawable.empty_avatar)
            }


            commentProfilePhoto.setOnClickListener {
                listener(pos, item, "PROFILE")
            }

            commentUserName.setOnClickListener {
                listener(pos, item, "PROFILE")
            }

            likeIcon.setOnClickListener {
                listener(pos, item, "LIKE")
            }

            likeText.setOnClickListener {
                listener(pos, item, "LIKE")
            }

            likeCount.setOnClickListener {
                listener(pos, item, "LIKE")
            }

            answerCommentIcon.setOnClickListener {
                listener(pos, item, "COMMENT")
            }

            answerCommentText.setOnClickListener {
                listener(pos, item, "COMMENT")
            }
        }

        fun loadURLImage(urlImage: String, imageView: ImageView) {
            if (urlImage != "") {
                Picasso.get().load(urlImage)
                    .into(imageView, object : Callback {
                        override fun onSuccess() {
                            //photoProgressComment?.visibility = View.GONE
                        }

                        override fun onError(e: Exception?) {
                            //photoProgressComment?.visibility = View.GONE
                            imageView.setImageResource(R.drawable.empty_avatar)
                        }
                    })
            }/* else
            photoProgressComment?.visibility = View.GONE*/

        }

        @SuppressLint("SimpleDateFormat")
        fun convertFeedDate(utcDate: String): String {
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            val outputFormat = SimpleDateFormat("dd/MM/yyyy")
            val date = sdf.parse(utcDate)
            return outputFormat.format(date!!)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.comment_holder_comment_parent, parent, false)
        return ModelViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return emojiReactions.size
    }

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        holder.bindItems(emojiReactions[position]!!, position, context, listener)
    }


}