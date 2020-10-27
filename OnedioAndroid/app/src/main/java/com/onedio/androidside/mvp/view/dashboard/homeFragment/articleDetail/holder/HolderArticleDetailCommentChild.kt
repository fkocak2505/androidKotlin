package com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.holder

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.model.dashboard.homeFragment.commentModel.CommentModel

class HolderArticleDetailCommentChild(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var childCommentProfilePhoto: ImageView? =
        itemView.findViewById(R.id.childCommentProfilePhoto) as ImageView
    private var photoProgressCommentChild: ProgressBar? =
        itemView.findViewById(R.id.photoProgressCommentChild) as ProgressBar
    private var childCommentUserName: TextView? =
        itemView.findViewById(R.id.childCommentUserName) as TextView
    private var childCommentTime: TextView? =
        itemView.findViewById(R.id.childCommentTime) as TextView
    private var childCommentText: TextView? =
        itemView.findViewById(R.id.childCommentText) as TextView
    private var childLikeIcon: ImageView? = itemView.findViewById(R.id.childLikeIcon) as ImageView
    private var childLikeText: TextView? = itemView.findViewById(R.id.childLikeText) as TextView
    private var childLikeCount: TextView? = itemView.findViewById(R.id.childLikeCount) as TextView
    private var childAnswerCommentIcon: ImageView? =
        itemView.findViewById(R.id.childAnswerCommentIcon) as ImageView
    private var childAnswerCommentText: TextView? =
        itemView.findViewById(R.id.childAnswerCommentText) as TextView

    private var constraintLayout5: ConstraintLayout? =
        itemView.findViewById(R.id.constraintLayout5) as ConstraintLayout

    fun bindItems(
        context: Context,
        item: CommentModel,
        pos: Int,
        listener: (CommentModel, String, TextView, ImageView) -> Unit
    ) {

        ////// Data Set Text
        childCommentUserName?.text = item.userName
        childCommentTime?.text = item.time
        childCommentText?.text = item.text
        childLikeCount?.text = item.commentLikeCount

        ///// Data Set Image
        loadURLImage(item.profilePhoto, childCommentProfilePhoto!!)

        //// Text TypeFace
        childCommentUserName?.typeface = OnedioCommon.changeTypeFace4Activity(context)
        childCommentTime?.typeface = OnedioCommon.changeTypeFace4Activity(context)
        childCommentText?.typeface = OnedioCommon.changeTypeFace4Activity(context)
        childLikeCount?.typeface = OnedioCommon.changeTypeFace4Activity(context)

        /*itemView.setOnClickListener {
            listener(item, "REPLY")
        }*/

        val sharedPref =
            context.getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        val theme = sharedPref.getString("mode", "default")!!

        if(theme == "dark"){
            childCommentUserName?.setTextColor(Color.parseColor("#FFFFFF"))
            childCommentTime?.setTextColor(Color.parseColor("#FFFFFF"))
            childCommentText?.setTextColor(Color.parseColor("#FFFFFF"))
            childLikeText?.setTextColor(Color.parseColor("#FFFFFF"))
            childLikeCount?.setTextColor(Color.parseColor("#FFFFFF"))
            childAnswerCommentText?.setTextColor(Color.parseColor("#FFFFFF"))

            constraintLayout5?.background = ContextCompat.getDrawable(context, R.drawable.bg_comment_parent_dark_mode)
            childCommentProfilePhoto?.foreground = ContextCompat.getDrawable(context, R.drawable.custom_image_view_comment_layout_dark_mode)

            childAnswerCommentIcon?.setImageResource(R.drawable.ic_answer_comment_dark_mode)
            childLikeIcon?.setImageResource(R.drawable.ic_like_dark_mode)

        }else{
            childCommentUserName?.setTextColor(Color.parseColor("#191919"))
            childCommentTime?.setTextColor(Color.parseColor("#191919"))
            childCommentText?.setTextColor(Color.parseColor("#191919"))
            childLikeText?.setTextColor(Color.parseColor("#191919"))
            childLikeCount?.setTextColor(Color.parseColor("#191919"))
            childAnswerCommentText?.setTextColor(Color.parseColor("#191919"))

            constraintLayout5?.background = ContextCompat.getDrawable(context, R.drawable.bg_comment_parent)
            childCommentProfilePhoto?.foreground = ContextCompat.getDrawable(context, R.drawable.custom_image_view_comment_layout)

            childAnswerCommentIcon?.setImageResource(R.drawable.ic_answer_comment_dark_mode)
            childLikeIcon?.setImageResource(R.drawable.ic_like_dark_mode)
        }



    }

    fun loadURLImage(urlImage: String, imageView: ImageView) {
        if (urlImage != "") {
            Picasso.get().load(urlImage)
                .into(imageView, object : Callback {
                    override fun onSuccess() {
                        photoProgressCommentChild?.visibility = View.GONE
                    }

                    override fun onError(e: Exception?) {
                        photoProgressCommentChild?.visibility = View.GONE
                        imageView.setImageResource(R.drawable.empty_avatar)
                    }
                })
        } else
            photoProgressCommentChild?.visibility = View.GONE
    }

}