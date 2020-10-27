package com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.optimizedArticleDetail.adapter.holder.commentAdapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.onedio.androidside.R
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.model.dashboard.homeFragment.commentModel.CommentModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat

class CommentAdapter(
    val context: Context,
    val emojiReactions: MutableList<CommentModel>,
    private val listener: (Int, CommentModel, String, TextView, ImageView) -> Unit
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
        private val constraintLayout5: ConstraintLayout = view.findViewById(R.id.constraintLayout5)


        fun bindItems(
            item: CommentModel,
            pos: Int,
            context: Context,
            listener: (Int, CommentModel, String, TextView, ImageView) -> Unit
        ) {

            commentUserName.text = item.userName
            commentTime.text = item.time
            commentText.text = item.text
            likeCount.text = item.commentLikeCount

            loadURLImage(item.profilePhoto, commentProfilePhoto)

            if (item.isCommentLiked)
                likeIcon.setImageResource(R.drawable.ic_like_blue)
            else
                likeIcon.setImageResource(R.drawable.ic_like)


            commentProfilePhoto.setOnClickListener {
                listener(pos, item, "PROFILE", likeCount, likeIcon)
            }

            commentUserName.setOnClickListener {
                listener(pos, item, "PROFILE", likeCount, likeIcon)
            }

            likeIcon.setOnClickListener {
                listener(pos, item, "LIKE", likeCount, likeIcon)
            }

            likeText.setOnClickListener {
                listener(pos, item, "LIKE", likeCount, likeIcon)
            }

            likeCount.setOnClickListener {
                listener(pos, item, "LIKE", likeCount, likeIcon)
            }

            answerCommentIcon.setOnClickListener {
                listener(pos, item, "REPLY", likeCount, likeIcon)
            }

            answerCommentText.setOnClickListener {
                listener(pos, item, "REPLY", likeCount, likeIcon)
            }

            val sharedPref =
                context.getSharedPreferences(
                    OnedioConstant.SHARED_PREF_FILE_NAME,
                    Context.MODE_PRIVATE
                )
            val theme = sharedPref.getString("mode", "default")!!

            if (theme == "dark") {
                commentUserName.setTextColor(Color.parseColor("#FFFFFF"))
                commentTime.setTextColor(Color.parseColor("#FFFFFF"))
                commentText.setTextColor(Color.parseColor("#FFFFFF"))
                likeText.setTextColor(Color.parseColor("#FFFFFF"))
                likeCount.setTextColor(Color.parseColor("#FFFFFF"))
                answerCommentText.setTextColor(Color.parseColor("#FFFFFF"))

                constraintLayout5.background =
                    ContextCompat.getDrawable(context, R.drawable.bg_comment_parent_dark_mode)
                commentProfilePhoto.foreground = ContextCompat.getDrawable(
                    context,
                    R.drawable.custom_image_view_comment_layout_dark_mode
                )

                answerCommentIcon.setImageResource(R.drawable.ic_answer_comment_dark_mode)
                likeIcon.setImageResource(R.drawable.ic_like_dark_mode)
            } else {
                commentUserName.setTextColor(Color.parseColor("#191919"))
                commentTime.setTextColor(Color.parseColor("#191919"))
                commentText.setTextColor(Color.parseColor("#191919"))
                likeText.setTextColor(Color.parseColor("#191919"))
                likeCount.setTextColor(Color.parseColor("#191919"))
                answerCommentText.setTextColor(Color.parseColor("#191919"))

                constraintLayout5.background =
                    ContextCompat.getDrawable(context, R.drawable.bg_comment_parent)
                commentProfilePhoto.foreground =
                    ContextCompat.getDrawable(context, R.drawable.custom_image_view_comment_layout)

                answerCommentIcon.setImageResource(R.drawable.ic_answer_comment)
                likeIcon.setImageResource(R.drawable.ic_like)
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