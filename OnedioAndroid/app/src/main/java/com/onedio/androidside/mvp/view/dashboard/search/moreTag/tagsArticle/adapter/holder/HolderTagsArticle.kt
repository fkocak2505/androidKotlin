package com.onedio.androidside.mvp.view.dashboard.search.moreTag.tagsArticle.adapter.holder

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.view.dashboard.search.moreTag.tagsArticle.adapter.model.TagsAdapterModel

class HolderTagsArticle(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var itemOfTagsArticleCoverPhoto: ImageView? =
        itemView.findViewById(R.id.itemOfTagsArticleCoverPhoto) as ImageView

    private var itemOfTagsCoverPhotoProgress: ProgressBar? =
        itemView.findViewById(R.id.itemOfTagsCoverPhotoProgress) as ProgressBar

    private var itemOfTagsCoverPhotoText: TextView? =
        itemView.findViewById(R.id.itemOfTagsCoverPhotoText) as TextView

    private var tagsArticleConstraint: ConstraintLayout? =
        itemView.findViewById(R.id.tagsArticleConstraint) as ConstraintLayout


    fun bindItems(
        context: Context,
        item: TagsAdapterModel,
        listener: (TagsAdapterModel, String) -> Unit,
        pos: Int
    ) {

        setImageViewsData(context, item)

        setTextViewsData(context, item)

        setViewsClickLsitener(item, listener)

        setDarkModeTheme(context)


    }

    private fun setImageViewsData(context: Context, item: TagsAdapterModel) {
        loadGifWithGlide(
            context,
            item.coverPhoto!!,
            itemOfTagsArticleCoverPhoto!!
        )
    }

    private fun setTextViewsData(context: Context, item: TagsAdapterModel) {
        itemOfTagsCoverPhotoText?.text = item.coverPhotoText
        itemOfTagsCoverPhotoText?.typeface = OnedioCommon.changeTypeFace4Activity(context)
    }

    private fun setViewsClickLsitener(
        item: TagsAdapterModel,
        listener: (TagsAdapterModel, String) -> Unit
    ) {
        tagsArticleConstraint?.setOnClickListener {
            listener(item, "ARTICLE")
        }
    }

    private fun setDarkModeTheme(context: Context) {
        val sharedPref =
            context.getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        val theme = sharedPref.getString("mode", "default")!!

        if (theme == "dark") {
            itemOfTagsCoverPhotoText?.setTextColor(Color.parseColor("#FFFFFF"))
        } else {
            itemOfTagsCoverPhotoText?.setTextColor(Color.parseColor("#231f20"))
        }
    }

    private fun loadGifWithGlide(context: Context, gifUrl: String, imageView: ImageView) {
        Glide.with(context)
            .load(gifUrl)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    imageView.visibility = View.GONE
                    itemOfTagsCoverPhotoProgress?.visibility = View.GONE
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
                    itemOfTagsCoverPhotoProgress?.visibility = View.GONE
                    return false
                }
            })
            .into(imageView)
    }

}