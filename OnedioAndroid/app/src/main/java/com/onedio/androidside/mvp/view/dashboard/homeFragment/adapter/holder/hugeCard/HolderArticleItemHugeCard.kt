package com.onedio.androidside.mvp.view.dashboard.homeFragment.adapter.holder.hugeCard

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.gms.ads.doubleclick.PublisherAdView
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.view.dashboard.DashboardActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.ArticlesFragmentAdapterModel
import com.onedio.androidside.mvp.view.dashboard.homeFragment.widgets.hugeCardRecyclerViewAdapter.model.HugeCardModel
import com.onedio.androidside.mvp.view.loginAndRegister.LoginAndRegisterDashboardActivityViewImpl
import com.onedio.androidside.singleton.OnedioSingletonPattern
import com.sackcentury.shinebuttonlib.ShineButton
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class HolderArticleItemHugeCard(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var coverPhoto: ImageView? =
        itemView.findViewById(R.id.coverPhoto) as ImageView

    private var coverPhotoProgress: ProgressBar? =
        itemView.findViewById(R.id.coverPhotoProgress) as ProgressBar

    private var articleAction: ImageView? =
        itemView.findViewById(R.id.articleAction) as ImageView

    private var articleActionBackground: ImageView? =
        itemView.findViewById(R.id.articleActionBackground) as ImageView

    private var articleActionPhotoProgress: ProgressBar? =
        itemView.findViewById(R.id.articleActionPhotoProgress) as ProgressBar

    private var coverPhotoBadgeImage: ImageView? =
        itemView.findViewById(R.id.coverPhotoBadgeImage) as ImageView

    private var badgeLineCovertPhoto: View? =
        itemView.findViewById(R.id.badgeLineCovertPhoto) as View

    private var feedTitle: TextView? =
        itemView.findViewById(R.id.feedTitle) as TextView

    private var feedDate: TextView? =
        itemView.findViewById(R.id.feedDate) as TextView

    private var flagImage: ShineButton? =
        itemView.findViewById(R.id.flagImage) as ShineButton

    private var emojiAction: ImageView? =
        itemView.findViewById(R.id.emojiAction) as ImageView

    private var emojiAction2: ImageView? =
        itemView.findViewById(R.id.emojiAction2) as ImageView

    private var emojiAction3: ImageView? =
        itemView.findViewById(R.id.emojiAction3) as ImageView

    private var emojiActionCount: TextView? =
        itemView.findViewById(R.id.emojiActionCount) as TextView

    private var doCommentIcon: ImageView? =
        itemView.findViewById(R.id.doCommentIcon) as ImageView

    private var commentConstraintLayout: ConstraintLayout? =
        itemView.findViewById(R.id.commentConstraintLayout) as ConstraintLayout

    private var commentCount: TextView? =
        itemView.findViewById(R.id.commentCount) as TextView

    private var shareIcon: ImageView? =
        itemView.findViewById(R.id.shareIcon) as ImageView

    private var popularCommentUserProfilePhoto: ImageView? =
        itemView.findViewById(R.id.popularCommentUserProfilePhoto) as ImageView

    private var popularCommentConstraintLayout: ConstraintLayout? =
        itemView.findViewById(R.id.popularCommentConstraintLayout) as ConstraintLayout

    private var popularCommentUserName: TextView? =
        itemView.findViewById(R.id.popularCommentUserName) as TextView

    private var popularCommentDotIcon: TextView? =
        itemView.findViewById(R.id.popularCommentDotIcon) as TextView

    private var popularCommentTime: TextView? =
        itemView.findViewById(R.id.popularCommentTime) as TextView

    private var popularCommentText: TextView? =
        itemView.findViewById(R.id.popularCommentText) as TextView

    private var childLikeIcon: ImageView? =
        itemView.findViewById(R.id.childLikeIcon) as ImageView

    private var childLikeDot: TextView? =
        itemView.findViewById(R.id.childLikeDot) as TextView

    private var childLikeCount: TextView? =
        itemView.findViewById(R.id.childLikeCount) as TextView

    private var favoriteIconBackground: ConstraintLayout? =
        itemView.findViewById(R.id.favoriteIconBackground) as ConstraintLayout

    private var commentIconBackground: ConstraintLayout? =
        itemView.findViewById(R.id.commentIconBackground) as ConstraintLayout

    private var shareIconBackground: ConstraintLayout? =
        itemView.findViewById(R.id.shareIconBackground) as ConstraintLayout


    fun bindItems(
        activity: Activity,
        item: ArticlesFragmentAdapterModel,
        listener: (Int, ArticlesFragmentAdapterModel) -> Unit,
        pos: Int
    ) {

        flagImage?.init(activity)

        setArticleItemData(activity.applicationContext, item.articleItem!!)

        //setArticleItemTypeface(activity)

        //setArticleItemOnClick(item.articleItem, listener, pos, activity)

        setArticleItemViewsColor(activity)


    }

    private fun setArticleItemData(context: Context, item: HugeCardModel) {
        loadImage(
            context,
            item.coverPhoto!!,
            coverPhoto!!,
            coverPhotoProgress!!
        )
        if (item.articleAction != " ") {
            articleAction?.visibility = View.VISIBLE
            articleActionBackground?.visibility = View.VISIBLE
            //articleActionPhotoProgress.visibility = View.VISIBLE

            loadImage(
                context,
                item.articleAction!!,
                articleAction!!,
                articleActionPhotoProgress!!
            )
        } else {
            articleAction?.visibility = View.INVISIBLE
            articleActionBackground?.visibility = View.INVISIBLE
            articleActionPhotoProgress?.visibility = View.GONE
        }

        if (item.coverPhotoText != " " && item.coverPhotoBadgeImage != " ") {
            //coverPhotoText.visibility = View.VISIBLE
            coverPhotoBadgeImage?.visibility = View.VISIBLE
            badgeLineCovertPhoto?.visibility = View.VISIBLE

            //coverPhotoText.text = item.coverPhotoText
            loadImageWithoutProgress(item.coverPhotoBadgeImage!!, coverPhotoBadgeImage!!)
        } else {
            //coverPhotoText.visibility = View.GONE
            coverPhotoBadgeImage?.visibility = View.GONE
            badgeLineCovertPhoto?.visibility = View.GONE
        }

        if (item.emojiAction != "") loadImageWithoutProgress(item.emojiAction!!, emojiAction!!)
        if (item.emojiAction2 != "") loadImageWithoutProgress(item.emojiAction2!!, emojiAction2!!)
        if (item.emojiAction3 != "") loadImageWithoutProgress(item.emojiAction3!!, emojiAction3!!)

        feedTitle?.text = item.feedTitle
        feedDate?.text = item.feedDate
        emojiActionCount?.text = item.emojiActionCount
        commentCount?.text = item.commentCount
        flagImage?.isChecked = item.isUserFavorited


        item.popularComment?.user?.let {
            visibilitySettingPopularComment(View.VISIBLE)

            item.popularComment.user?.avatar?.let {
                loadImageWithoutProgress(
                    item.popularComment.user!!.avatar!!,
                    popularCommentUserProfilePhoto!!
                )
            } ?: run {

            }

            popularCommentUserName?.text = item.popularComment.user!!.userName
            popularCommentTime?.text =
                OnedioCommon.convertFeedDate(item.popularComment.createDate!!)
            popularCommentText?.text = item.popularComment.text
            childLikeCount?.text = item.popularComment.likes.toString()
        } ?: run {
            visibilitySettingPopularComment(View.GONE)
        }
    }

    private fun setArticleItemViewsColor(activity: Activity){
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            favoriteIconBackground?.background = ContextCompat.getDrawable(activity.applicationContext, R.drawable.bg_comment_background_dark_mode)
            commentIconBackground?.background = ContextCompat.getDrawable(activity.applicationContext, R.drawable.bg_comment_background_dark_mode)
            shareIconBackground?.background = ContextCompat.getDrawable(activity.applicationContext, R.drawable.bg_comment_background_dark_mode)
            emojiAction?.background = ContextCompat.getDrawable(activity.applicationContext, R.drawable.rounded_corner_dark_mode)
            emojiAction2?.background = ContextCompat.getDrawable(activity.applicationContext, R.drawable.rounded_corner_dark_mode)
            emojiAction3?.background = ContextCompat.getDrawable(activity.applicationContext, R.drawable.rounded_corner_dark_mode)

            doCommentIcon?.setImageResource(R.drawable.ic_comment_dark_mode)
            shareIcon?.setImageResource(R.drawable.ic_share_dark_mode)
            childLikeIcon?.setImageResource(R.drawable.ic_like_dark_mode)

            articleActionBackground?.setImageResource(R.drawable.icon_circle_dark_mode)

            feedTitle?.setTextColor(Color.parseColor("#FFFFFF"))
            feedDate?.setTextColor(Color.parseColor("#FFFFFF"))
            emojiActionCount?.setTextColor(Color.parseColor("#FFFFFF"))
            commentCount?.setTextColor(Color.parseColor("#FFFFFF"))
            popularCommentUserName?.setTextColor(Color.parseColor("#FFFFFF"))
            popularCommentText?.setTextColor(Color.parseColor("#FFFFFF"))
            childLikeCount?.setTextColor(Color.parseColor("#FFFFFF"))
            //lastViewLine4SliderListItem.setBackgroundColor(Color.parseColor("#0e1720"))
        } else {
            favoriteIconBackground?.background = ContextCompat.getDrawable(activity.applicationContext, R.drawable.bg_comment_background)
            commentIconBackground?.background = ContextCompat.getDrawable(activity.applicationContext, R.drawable.bg_comment_background)
            shareIconBackground?.background = ContextCompat.getDrawable(activity.applicationContext, R.drawable.bg_comment_background)
            emojiAction?.background = ContextCompat.getDrawable(activity.applicationContext, R.drawable.rounded_corner)
            emojiAction2?.background = ContextCompat.getDrawable(activity.applicationContext, R.drawable.rounded_corner)
            emojiAction3?.background = ContextCompat.getDrawable(activity.applicationContext, R.drawable.rounded_corner)

            doCommentIcon?.setImageResource(R.drawable.delete_after_comment)
            shareIcon?.setImageResource(R.drawable.ic_share_black)
            childLikeIcon?.setImageResource(R.drawable.ic_like)

            articleActionBackground?.setImageResource(R.drawable.ic_circle)

            feedTitle?.setTextColor(Color.parseColor("#231f20"))
            feedDate?.setTextColor(Color.parseColor("#191919"))
            emojiActionCount?.setTextColor(Color.parseColor("#191919"))
            commentCount?.setTextColor(Color.parseColor("#191919"))
            popularCommentUserName?.setTextColor(Color.parseColor("#191919"))
            popularCommentText?.setTextColor(Color.parseColor("#191919"))
            childLikeCount?.setTextColor(Color.parseColor("#191919"))
            //lastViewLine4SliderListItem.setBackgroundColor(Color.parseColor("#e2e2e2"))
        }
    }




    private fun loadImage(
        context: Context,
        imageUrl: String,
        imageView: ImageView,
        progressBar: ProgressBar
    ) {
        if (imageUrl.contains("gif", true)) {
            if (imageUrl.trim() != "") {
                Glide.with(context)
                    .load(imageUrl)
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            imageView.visibility = View.GONE
                            progressBar.visibility = View.GONE
                            imageView.setImageResource(R.drawable.image_error_dark_mode)
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            imageView.visibility = View.VISIBLE
                            progressBar.visibility = View.GONE
                            return false
                        }
                    })
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(imageView)
            } else {
                imageView.setImageResource(R.drawable.image_error_dark_mode)
            }


        } else {
            if (imageUrl.trim() != "") {
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
            } else {
                imageView.setImageResource(R.drawable.image_error_dark_mode)
            }
        }
    }

    private fun loadImageWithoutProgress(imageUrl: String, imageView: ImageView) {
        if (imageUrl.trim() != "" && imageUrl.startsWith("ht")) {
            Picasso.get().load(imageUrl)
                .into(imageView, object : Callback {
                    override fun onSuccess() {

                    }

                    override fun onError(e: Exception?) {
                        imageView.setImageResource(R.drawable.empty_avatar)
                    }
                })
            //imageView.loadImageWithPicasso(imageUrl, R.drawable.empty_avatar)
        } else
            imageView.setImageResource(R.drawable.empty_avatar)

    }

    private fun visibilitySettingPopularComment(viewStatus: Int) {
        /*infoPopularCommentTitle.visibility = viewStatus*/
        popularCommentUserProfilePhoto?.visibility = viewStatus
        popularCommentConstraintLayout?.visibility = viewStatus
        childLikeIcon?.visibility = viewStatus
        childLikeDot?.visibility = viewStatus
        childLikeCount?.visibility = viewStatus
        /*childAnswerCommentIcon.visibility = viewStatus
        childAnswerCommentText.visibility = viewStatus*/
    }



}