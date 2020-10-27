package com.onedio.androidside.mvp.view.dashboard.homeFragment.articleComment.holder

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.model.dashboard.homeFragment.commentModel.CommentModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.jz_layout_std.view.*

class HolderCommentParent(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var commentProfilePhoto: ImageView? =
        itemView.findViewById(R.id.commentProfilePhoto) as ImageView
    /*private var photoProgressComment: ProgressBar? =
        itemView.findViewById(R.id.photoProgressComment) as ProgressBar*/
    private var commentUserName: TextView? = itemView.findViewById(R.id.commentUserName) as TextView
    private var commentTime: TextView? = itemView.findViewById(R.id.commentTime) as TextView
    private var commentText: TextView? =
        itemView.findViewById(R.id.commentText) as TextView
    private var likeIcon: ImageView? = itemView.findViewById(R.id.likeIcon) as ImageView
    private var likeText: TextView? = itemView.findViewById(R.id.likeText) as TextView
    private var likeCount: TextView? = itemView.findViewById(R.id.likeCount) as TextView
    private var answerCommentIcon: ImageView? =
        itemView.findViewById(R.id.answerCommentIcon) as ImageView
    private var answerCommentText: TextView? =
        itemView.findViewById(R.id.answerCommentText) as TextView

    private var constraintLayout5: ConstraintLayout? =
        itemView.findViewById(R.id.constraintLayout5) as ConstraintLayout



    fun bindItems(
        context: Context,
        item: CommentModel,
        pos: Int,
        listener: (CommentModel, String, TextView, ImageView) -> Unit
    ) {

        ////// Data Set Text
        commentUserName?.text = item.userName
        commentTime?.text = item.time
        commentText?.text = item.text
        likeCount?.text = item.commentLikeCount

        ///// Data Set Image
        loadURLImage(item.profilePhoto, commentProfilePhoto!!)

        //// Text TypeFace
        commentUserName?.typeface = OnedioCommon.changeTypeFace4Activity(context)
        commentTime?.typeface = OnedioCommon.changeTypeFace4Activity(context)
        commentText?.typeface = OnedioCommon.changeTypeFace4Activity(context)
        likeCount?.typeface = OnedioCommon.changeTypeFace4Activity(context)

        /*itemView.setOnClickListener {
            listener(item)
        }*/

        val sharedPref =
            context.getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        val theme = sharedPref?.getString("mode", "default")!!

        if(theme == "dark"){
            constraintLayout5?.background = ContextCompat.getDrawable(context, R.drawable.bg_comment_parent_dark_mode)
            commentProfilePhoto?.foreground = ContextCompat.getDrawable(context, R.drawable.custom_image_view_comment_layout_dark_mode)

            commentUserName?.setTextColor(Color.parseColor("#FFFFFF"))
            commentText?.setTextColor(Color.parseColor("#FFFFFF"))
            likeIcon?.setImageResource(R.drawable.ic_like_dark_mode)
            likeText?.setTextColor(Color.parseColor("#FFFFFF"))
            likeCount?.setTextColor(Color.parseColor("#FFFFFF"))
            answerCommentIcon?.setImageResource(R.drawable.ic_answer_comment_dark_mode)
            answerCommentText?.setTextColor(Color.parseColor("#FFFFFF"))
        }else{
            constraintLayout5?.background = ContextCompat.getDrawable(context, R.drawable.bg_comment_parent)
            commentProfilePhoto?.foreground = ContextCompat.getDrawable(context, R.drawable.custom_image_view_comment_layout)

            commentUserName?.setTextColor(Color.parseColor("#191919"))
            commentText?.setTextColor(Color.parseColor("#191919"))
            likeIcon?.setImageResource(R.drawable.ic_like)
            likeText?.setTextColor(Color.parseColor("#191919"))
            likeCount?.setTextColor(Color.parseColor("#191919"))
            answerCommentIcon?.setImageResource(R.drawable.ic_answer_comment)
            answerCommentText?.setTextColor(Color.parseColor("#191919"))
        }

        if(item.isCommentLiked){
            likeIcon?.setImageResource(R.drawable.ic_like_blue)
        }else{
            if(theme == "dark"){
                likeIcon?.setImageResource(R.drawable.ic_like_dark_mode)
            }else{
                likeIcon?.setImageResource(R.drawable.ic_like)
            }

        }

        answerCommentIcon?.setOnClickListener {
            listener(item, "REPLY", likeCount!!, likeIcon!!)
        }

        answerCommentText?.setOnClickListener {
            listener(item, "REPLY", likeCount!!,likeIcon!!)
        }

        likeIcon?.setOnClickListener {
            listener(item, "LIKE", likeCount!!,likeIcon!!)
        }

        likeText?.setOnClickListener {
            listener(item, "LIKE", likeCount!!,likeIcon!!)
        }

        likeCount?.setOnClickListener {
            listener(item, "LIKE", likeCount!!,likeIcon!!)
        }

        commentProfilePhoto?.setOnClickListener{
            listener(item, "PROFIL", likeCount!!,likeIcon!!)
        }

        commentUserName?.setOnClickListener{
            listener(item, "PROFIL", likeCount!!,likeIcon!!)
        }
    }

    fun loadURLImage(urlImage: String, imageView: ImageView) {
        if (urlImage != "" && urlImage.startsWith("ht")) {
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
        } else{
            imageView.setImageResource(R.drawable.empty_avatar)
            //photoProgressComment?.visibility = View.GONE
        }



    }
}