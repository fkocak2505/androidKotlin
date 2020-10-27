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

class HolderCommentChild(itemView: View) : RecyclerView.ViewHolder(itemView) {

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

        val sharedPref =
            context.getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        val theme = sharedPref?.getString("mode", "default")!!

        if(theme == "dark"){
            constraintLayout5?.background = ContextCompat.getDrawable(context, R.drawable.bg_comment_parent_dark_mode)
            childCommentProfilePhoto?.foreground = ContextCompat.getDrawable(context, R.drawable.custom_image_view_comment_layout_dark_mode)

            childCommentUserName?.setTextColor(Color.parseColor("#FFFFFF"))
            childCommentText?.setTextColor(Color.parseColor("#FFFFFF"))
            childLikeIcon?.setImageResource(R.drawable.ic_like_dark_mode)
            childLikeText?.setTextColor(Color.parseColor("#FFFFFF"))
            childLikeCount?.setTextColor(Color.parseColor("#FFFFFF"))
            childAnswerCommentIcon?.setImageResource(R.drawable.ic_answer_comment_dark_mode)
            childAnswerCommentText?.setTextColor(Color.parseColor("#FFFFFF"))
        }else{
            constraintLayout5?.background = ContextCompat.getDrawable(context, R.drawable.bg_comment_parent)
            childCommentProfilePhoto?.foreground = ContextCompat.getDrawable(context, R.drawable.custom_image_view_comment_layout)

            childCommentUserName?.setTextColor(Color.parseColor("#191919"))
            childCommentText?.setTextColor(Color.parseColor("#191919"))
            childLikeIcon?.setImageResource(R.drawable.ic_like)
            childLikeText?.setTextColor(Color.parseColor("#191919"))
            childLikeCount?.setTextColor(Color.parseColor("#191919"))
            childAnswerCommentIcon?.setImageResource(R.drawable.ic_answer_comment)
            childAnswerCommentText?.setTextColor(Color.parseColor("#191919"))
        }


        if(item.isCommentLiked){
            childLikeIcon?.setImageResource(R.drawable.ic_like_blue)
        }else{
            if(theme == "dark"){
                childLikeIcon?.setImageResource(R.drawable.ic_like_dark_mode)
            }else{
                childLikeIcon?.setImageResource(R.drawable.ic_like)
            }

        }

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

        childAnswerCommentIcon?.setOnClickListener{
            listener(item, "REPLY_CHILD", childLikeCount!!,childLikeIcon!!)
        }

        childAnswerCommentText?.setOnClickListener{
            listener(item, "REPLY_CHILD",childLikeCount!!,childLikeIcon!!)
        }

        childLikeIcon?.setOnClickListener{
            listener(item, "LIKE_CHILD",childLikeCount!!,childLikeIcon!!)
        }

        childLikeText?.setOnClickListener{
            listener(item, "LIKE_CHILD",childLikeCount!!,childLikeIcon!!)
        }

        childLikeCount?.setOnClickListener{
            listener(item, "LIKE_CHILD",childLikeCount!!,childLikeIcon!!)
        }

        childCommentProfilePhoto?.setOnClickListener{
            listener(item, "PROFIL_CHILD", childLikeCount!!,childLikeIcon!!)
        }

        childCommentUserName?.setOnClickListener{
            listener(item, "PROFIL_CHILD", childLikeCount!!,childLikeIcon!!)
        }


    }

    fun loadURLImage(urlImage: String, imageView: ImageView) {
        if(urlImage != ""){
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
        }else{
            photoProgressCommentChild?.visibility = View.GONE
            imageView.setImageResource(R.drawable.empty_avatar)
        }


    }

}