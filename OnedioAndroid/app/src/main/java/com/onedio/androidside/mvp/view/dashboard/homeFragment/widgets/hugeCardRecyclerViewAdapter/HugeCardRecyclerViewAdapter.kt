package com.onedio.androidside.mvp.view.dashboard.homeFragment.widgets.hugeCardRecyclerViewAdapter

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.view.dashboard.DashboardActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.widgets.hugeCardRecyclerViewAdapter.model.HugeCardModel
import com.onedio.androidside.mvp.view.loginAndRegister.LoginAndRegisterDashboardActivityViewImpl
import com.onedio.androidside.singleton.OnedioSingletonPattern
import com.sackcentury.shinebuttonlib.ShineButton
import com.squareup.picasso.Callback
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso


class HugeCardRecyclerViewAdapter(
    private val activity: FragmentActivity,
    private val hugeCardList: MutableList<HugeCardModel>,
    private val listener: (HugeCardModel, Int, String, View, View, Boolean) -> Unit
) : RecyclerView.Adapter<HugeCardRecyclerViewAdapter.HugeCardModelViewHolder>() {

    class HugeCardModelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val coverPhoto: ImageView = view.findViewById(R.id.coverPhoto)
        private val coverPhotoProgress: ProgressBar = view.findViewById(R.id.coverPhotoProgress)
        private val articleAction: ImageView = view.findViewById(R.id.articleAction)
        private val articleActionBackground: ImageView =
            view.findViewById(R.id.articleActionBackground)
        private val articleActionPhotoProgress: ProgressBar =
            view.findViewById(R.id.articleActionPhotoProgress)

        //val coverPhotoText: TextView = view.findViewById(R.id.coverPhotoText)
        private val coverPhotoBadgeImage: ImageView = view.findViewById(R.id.coverPhotoBadgeImage)
        private val badgeLineCovertPhoto: View = view.findViewById(R.id.badgeLineCovertPhoto)

        private val feedTitle: TextView = view.findViewById(R.id.feedTitle)
        private val feedDate: TextView = view.findViewById(R.id.feedDate)


        private val flagImage: ShineButton = view.findViewById(R.id.flagImage)

        //private val addFavoriteText: TextView = view.findViewById(R.id.addFavoriteText)
        //private val view5: View = view.findViewById(R.id.view5)
        private val emojiAction: ImageView = view.findViewById(R.id.emojiAction)
        private val emojiAction2: ImageView = view.findViewById(R.id.emojiAction2)
        private val emojiAction3: ImageView = view.findViewById(R.id.emojiAction3)
        private val emojiActionCount: TextView = view.findViewById(R.id.emojiActionCount)

        private val doCommentIcon: ImageView = view.findViewById(R.id.doCommentIcon)
        //private val doCommentText: TextView = view.findViewById(R.id.doCommentText)
        //private val dotIcon: TextView = view.findViewById(R.id.dotIcon)
        private val commentConstraintLayout: ConstraintLayout =
            view.findViewById(R.id.commentConstraintLayout)
        private val commentCount: TextView = view.findViewById(R.id.commentCount)
        private val shareIcon: ImageView = view.findViewById(R.id.shareIcon)
        //private val shareText: TextView = view.findViewById(R.id.shareText)
        //val widthLineSeparate4Card: View = view.findViewById(R.id.widthLineSeparate4Card)

        /*private val infoPopularCommentTitle: TextView =
            view.findViewById(R.id.infoPopularCommentTitle)*/
        private val popularCommentUserProfilePhoto: ImageView =
            view.findViewById(R.id.popularCommentUserProfilePhoto)
        private val popularCommentConstraintLayout: ConstraintLayout =
            view.findViewById(R.id.popularCommentConstraintLayout)
        private val popularCommentUserName: TextView =
            view.findViewById(R.id.popularCommentUserName)
        private val popularCommentDotIcon: TextView = view.findViewById(R.id.popularCommentDotIcon)
        private val popularCommentTime: TextView = view.findViewById(R.id.popularCommentTime)
        private val popularCommentText: TextView = view.findViewById(R.id.popularCommentText)

        private val childLikeIcon: ImageView = view.findViewById(R.id.childLikeIcon)
        private val childLikeDot: TextView = view.findViewById(R.id.childLikeDot)
        private val childLikeCount: TextView = view.findViewById(R.id.childLikeCount)

        private val favoriteIconBackground: ConstraintLayout = view.findViewById(R.id.favoriteIconBackground)
        private val commentIconBackground: ConstraintLayout = view.findViewById(R.id.commentIconBackground)
        private val shareIconBackground: ConstraintLayout = view.findViewById(R.id.shareIconBackground)
        /*private val childAnswerCommentIcon: ImageView =
            view.findViewById(R.id.childAnswerCommentIcon)
        private val childAnswerCommentText: TextView =
            view.findViewById(R.id.childAnswerCommentText)*/


        fun bindItems(
            activity: FragmentActivity,
            item: HugeCardModel,
            pos: Int,
            listener: (HugeCardModel, Int, String, View, View, Boolean) -> Unit
        ) {

            flagImage.init(activity)

            setArticleItemData(activity, item)

            //setArticleItemTypeface(activity)

            setArticleItemOnClick(item, listener, pos, activity)

            setArticleItemViewsColor(activity)


        }

        private fun setArticleItemData(activity: Activity, item: HugeCardModel) {
            loadImage(
                activity.applicationContext!!,
                item.coverPhoto!!,
                coverPhoto,
                coverPhotoProgress
            )
            if (item.articleAction != " ") {
                articleAction.visibility = View.VISIBLE
                articleActionBackground.visibility = View.VISIBLE
                //articleActionPhotoProgress.visibility = View.VISIBLE

                loadImage(
                    activity.applicationContext,
                    item.articleAction!!,
                    articleAction,
                    articleActionPhotoProgress
                )
            } else {
                articleAction.visibility = View.INVISIBLE
                articleActionBackground.visibility = View.INVISIBLE
                articleActionPhotoProgress.visibility = View.GONE
            }

            if (item.coverPhotoText != " " && item.coverPhotoBadgeImage != " ") {
                //coverPhotoText.visibility = View.VISIBLE
                coverPhotoBadgeImage.visibility = View.VISIBLE
                badgeLineCovertPhoto.visibility = View.VISIBLE

                //coverPhotoText.text = item.coverPhotoText
                loadImageWithoutProgress(item.coverPhotoBadgeImage!!, coverPhotoBadgeImage)
            } else {
                //coverPhotoText.visibility = View.GONE
                coverPhotoBadgeImage.visibility = View.GONE
                badgeLineCovertPhoto.visibility = View.GONE
            }

            if (item.emojiAction != "") loadImageWithoutProgress(item.emojiAction!!, emojiAction)
            if (item.emojiAction2 != "") loadImageWithoutProgress(item.emojiAction2!!, emojiAction2)
            if (item.emojiAction3 != "") loadImageWithoutProgress(item.emojiAction3!!, emojiAction3)

            feedTitle.text = item.feedTitle
            feedDate.text = item.feedDate
            emojiActionCount.text = item.emojiActionCount
            commentCount.text = item.commentCount
            flagImage.isChecked = item.isUserFavorited


            item.popularComment?.user?.let {
                visibilitySettingPopularComment(View.VISIBLE)

                item.popularComment.user?.avatar?.let {
                    loadImageWithoutProgress(
                        item.popularComment.user!!.avatar!!,
                        popularCommentUserProfilePhoto
                    )
                } ?: run {

                }

                popularCommentUserName.text = item.popularComment.user!!.userName
                popularCommentTime.text =
                    OnedioCommon.convertFeedDate(item.popularComment.createDate!!)
                popularCommentText.text = item.popularComment.text
                childLikeCount.text = item.popularComment.likes.toString()
            } ?: run {
                visibilitySettingPopularComment(View.GONE)
            }
        }

        private fun visibilitySettingPopularComment(viewStatus: Int) {
            /*infoPopularCommentTitle.visibility = viewStatus*/
            popularCommentUserProfilePhoto.visibility = viewStatus
            popularCommentConstraintLayout.visibility = viewStatus
            childLikeIcon.visibility = viewStatus
            childLikeDot.visibility = viewStatus
            childLikeCount.visibility = viewStatus
            /*childAnswerCommentIcon.visibility = viewStatus
            childAnswerCommentText.visibility = viewStatus*/
        }

        /*private fun setArticleItemTypeface(activity: Activity) {
            feedTitle.typeface =
                OnedioCommon.changeTypeFace4ActivitySemiBold(activity.applicationContext)
        }*/

        private fun setArticleItemOnClick(
            item: HugeCardModel,
            listener: (HugeCardModel, Int, String, View, View, Boolean) -> Unit,
            pos: Int,
            activity: Activity
        ) {
            itemView.setOnClickListener {
                listener(item, pos, OnedioConstant.GENERAL, itemView, itemView, false)
            }

            flagImage.setOnCheckStateChangeListener { view, checked ->
                if (OnedioCommon.isUserLogin()) {
                    listener(
                        item,
                        pos,
                        OnedioConstant.FAVORITE,
                        flagImage,
                        flagImage,
                        checked
                    )
                } else {
                    OnedioSingletonPattern.instance.setActivity(DashboardActivityViewImpl::class.java)
                    OnedioCommon.cStartActivity(
                        activity.applicationContext!!,
                        LoginAndRegisterDashboardActivityViewImpl::class.java
                    )
                    flagImage.isChecked = !checked
                }
            }

            /*flagImage.setOnCheckStateChangeListener { view, checked ->
                if (OnedioCommon.isUserLogin()) {
                    listener(
                        item,
                        OnedioConstant.FAVORITE,
                        addFavoriteText,
                        addFavoriteText,
                        checked
                    )
                } else {
                    OnedioSingletonPattern.instance.setActivity(DashboardActivityViewImpl::class.java)
                    OnedioCommon.cStartActivity(activity.applicationContext!!, LoginAndRegisterDashboardActivityViewImpl::class.java)
                    flagImage.isChecked = !checked
                }
            }*/

            /*addFavoriteText.setOnClickListener {
                if (OnedioCommon.isUserLogin()) {
                    flagImage.performClick()
                } else {
                    OnedioSingletonPattern.instance.setActivity(DashboardActivityViewImpl::class.java)
                    OnedioCommon.cStartActivity(activity.applicationContext!!, LoginAndRegisterDashboardActivityViewImpl::class.java)
                    *//*Toast.makeText(
                        activity.applicationContext,
                        "Önce giriş yapmalısınız",
                        Toast.LENGTH_LONG
                    ).show()*//*
                }
            }*/

            commentConstraintLayout.setOnClickListener {
                listener(
                    item,
                    pos,
                    OnedioConstant.COMMENTS,
                    flagImage,
                    commentConstraintLayout,
                    false
                )
            }

            /*doCommentText.setOnClickListener {
                listener(item, OnedioConstant.COMMENTS, doCommentIcon, doCommentText, false)
            }

            commentCount.setOnClickListener {
                listener(item, OnedioConstant.COMMENTS, doCommentIcon, doCommentText, false)
            }*/

            /*shareText.setOnClickListener {
                listener(item, OnedioConstant.SHARE, shareIcon, shareText, false)
            }*/

            shareIcon.setOnClickListener {
                listener(item, pos, OnedioConstant.SHARE, shareIcon, shareIcon, false)
            }

            articleAction.setOnClickListener {
                listener(
                    item,
                    pos,
                    OnedioConstant.CATEGORY_ICON,
                    articleAction,
                    articleAction,
                    false
                )
            }

            popularCommentConstraintLayout.setOnClickListener {
                listener(
                    item,
                    pos,
                    OnedioConstant.COMMENTS,
                    flagImage,
                    popularCommentConstraintLayout,
                    false
                )
            }

            badgeLineCovertPhoto.setOnClickListener {
                listener(
                    item,
                    pos,
                    OnedioConstant.BADGE_ICON,
                    flagImage,
                    popularCommentConstraintLayout,
                    false
                )
            }

            coverPhotoBadgeImage.setOnClickListener {
                listener(
                    item,
                    pos,
                    OnedioConstant.BADGE_ICON,
                    flagImage,
                    popularCommentConstraintLayout,
                    false
                )
            }
        }

        private fun setArticleItemViewsColor(activity: FragmentActivity){
            if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
                favoriteIconBackground.background = ContextCompat.getDrawable(activity.applicationContext, R.drawable.bg_comment_background_dark_mode)
                commentIconBackground.background = ContextCompat.getDrawable(activity.applicationContext, R.drawable.bg_comment_background_dark_mode)
                shareIconBackground.background = ContextCompat.getDrawable(activity.applicationContext, R.drawable.bg_comment_background_dark_mode)
                emojiAction.background = ContextCompat.getDrawable(activity.applicationContext, R.drawable.rounded_corner_dark_mode)
                emojiAction2.background = ContextCompat.getDrawable(activity.applicationContext, R.drawable.rounded_corner_dark_mode)
                emojiAction3.background = ContextCompat.getDrawable(activity.applicationContext, R.drawable.rounded_corner_dark_mode)

                doCommentIcon.setImageResource(R.drawable.ic_comment_dark_mode)
                shareIcon.setImageResource(R.drawable.ic_share_dark_mode)
                childLikeIcon.setImageResource(R.drawable.ic_like_dark_mode)

                articleActionBackground.setImageResource(R.drawable.icon_circle_dark_mode)

                feedTitle.setTextColor(Color.parseColor("#FFFFFF"))
                feedDate.setTextColor(Color.parseColor("#FFFFFF"))
                emojiActionCount.setTextColor(Color.parseColor("#FFFFFF"))
                commentCount.setTextColor(Color.parseColor("#FFFFFF"))
                popularCommentUserName.setTextColor(Color.parseColor("#FFFFFF"))
                popularCommentText.setTextColor(Color.parseColor("#FFFFFF"))
                childLikeCount.setTextColor(Color.parseColor("#FFFFFF"))
                //lastViewLine4SliderListItem.setBackgroundColor(Color.parseColor("#0e1720"))
            } else {
                favoriteIconBackground.background = ContextCompat.getDrawable(activity.applicationContext, R.drawable.bg_comment_background)
                commentIconBackground.background = ContextCompat.getDrawable(activity.applicationContext, R.drawable.bg_comment_background)
                shareIconBackground.background = ContextCompat.getDrawable(activity.applicationContext, R.drawable.bg_comment_background)
                emojiAction.background = ContextCompat.getDrawable(activity.applicationContext, R.drawable.rounded_corner)
                emojiAction2.background = ContextCompat.getDrawable(activity.applicationContext, R.drawable.rounded_corner)
                emojiAction3.background = ContextCompat.getDrawable(activity.applicationContext, R.drawable.rounded_corner)

                doCommentIcon.setImageResource(R.drawable.delete_after_comment)
                shareIcon.setImageResource(R.drawable.ic_share_black)
                childLikeIcon.setImageResource(R.drawable.ic_like)

                articleActionBackground.setImageResource(R.drawable.ic_circle)

                feedTitle.setTextColor(Color.parseColor("#231f20"))
                feedDate.setTextColor(Color.parseColor("#191919"))
                emojiActionCount.setTextColor(Color.parseColor("#191919"))
                commentCount.setTextColor(Color.parseColor("#191919"))
                popularCommentUserName.setTextColor(Color.parseColor("#191919"))
                popularCommentText.setTextColor(Color.parseColor("#191919"))
                childLikeCount.setTextColor(Color.parseColor("#191919"))
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
                /*if(imageUrl.trim() != ""){
                    imageView.loadImageWithPicasso(imageUrl, R.drawable.image_error_dark_mode)
                }else{
                    imageView.setImageResource(R.drawable.image_error_dark_mode)
                }*/

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
    }

    fun addItems(items: MutableList<HugeCardModel>) {
        this.hugeCardList.addAll(items)
        notifyDataSetChanged()
    }

    fun getItemsaw(): MutableList<HugeCardModel> {
        return hugeCardList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HugeCardModelViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.widget_huge_card_list_item, parent, false)
        return HugeCardModelViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return hugeCardList.size
    }

    override fun onBindViewHolder(holder: HugeCardModelViewHolder, position: Int) {
        holder.bindItems(activity, hugeCardList[position], position, listener)
    }
}