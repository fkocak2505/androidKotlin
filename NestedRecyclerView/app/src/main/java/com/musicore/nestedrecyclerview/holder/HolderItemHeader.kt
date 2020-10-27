package com.musicore.nestedrecyclerview.holder

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.musicore.nestedrecyclerview.R
import com.musicore.nestedrecyclerview.model.ArticleDetailAdapterModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import io.noties.markwon.Markwon
import java.text.SimpleDateFormat

class HolderItemHeader(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var articleDetailCoverPhoto: ImageView? =
        itemView.findViewById(R.id.articleDetailCoverPhoto) as ImageView

    private var badgeLineCovertPhoto: View? =
        itemView.findViewById(R.id.badgeLineCovertPhoto) as View

    private var coverPhotoBadgeImage: ImageView? =
        itemView.findViewById(R.id.coverPhotoBadgeImage) as ImageView

    private var articleActionBackground: ImageView? =
        itemView.findViewById(R.id.articleActionBackground) as ImageView

    private var articleDetailAction: ImageView? =
        itemView.findViewById(R.id.articleDetailAction) as ImageView

    private var articleDetailTitle: TextView? =
        itemView.findViewById(R.id.articleDetailTitle) as TextView

    private var editorUserProfileConstraintLayout: ConstraintLayout? =
        itemView.findViewById(R.id.editorUserProfileConstraintLayout) as ConstraintLayout

    private var profilePhoto: ImageView? =
        itemView.findViewById(R.id.profilePhoto) as ImageView

    private var editorUserName: TextView? =
        itemView.findViewById(R.id.editorUserName) as TextView

    private var editorTitle: TextView? =
        itemView.findViewById(R.id.editorTitle) as TextView

    private var readableCountConst: ConstraintLayout? =
        itemView.findViewById(R.id.readableCountConst) as ConstraintLayout

    private var articleDetailItemReadCount: TextView? =
        itemView.findViewById(R.id.articleDetailItemReadCount) as TextView

    private var infoText: TextView? =
        itemView.findViewById(R.id.infoText) as TextView

    private var articleFeedDate: TextView? =
        itemView.findViewById(R.id.articleFeedDate) as TextView

    private var widthLine: View? =
        itemView.findViewById(R.id.widthLine) as View


    fun bindItems(
        context: Context,
        item: ArticleDetailAdapterModel,
        listener: (Int, ArticleDetailAdapterModel, String) -> Unit,
        pos: Int
    ) {

        setTextViewsData(context, item)

        setImageViewsData(context, item)

        setViewsClickListener(pos, item, listener)


    }

    private fun setTextViewsData(context: Context, item: ArticleDetailAdapterModel){
        item.data.data?.let {

            it.title?.let {
                Markwon.create(context)
                    .setMarkdown(articleDetailTitle!!, it)
            }?: run{
                articleDetailTitle?.text = ""
            }

            it.authors?.let {
                if(it.size != 0){

                    it[0]?.username?.let {
                        editorUserName?.text = it
                    }?:run {
                        editorUserName?.text = ""
                    }

                    it[0]?.occupation?.let {
                        editorTitle?.text = it
                    }?:run{
                        editorTitle?.text = "Onedio Üyesi"
                    }
                }else{
                    editorUserName?.text = ""
                    editorTitle?.text = "Onedio Üyesi"
                }
            }?: run{
                editorUserName?.text = ""
                editorTitle?.text = "Onedio Üyesi"
            }

            it.stats?.let {

                it.views?.let {
                    articleDetailItemReadCount?.text = calculateViewCount(it.toString())
                }?: run{
                    readableCountConst?.visibility = View.GONE
                }

            }?: run{
                readableCountConst?.visibility = View.GONE
            }

            it.publishDate?.let {
                articleFeedDate?.text = convertFeedDate(it)
            }?: run{
                articleFeedDate?.text = ""
            }

        }?: run{
            articleDetailTitle?.text = ""
            editorUserName?.text = ""
            editorTitle?.text = "Onedio Üyesi"
            readableCountConst?.visibility = View.GONE
            articleFeedDate?.text = ""
        }

    }

    private fun setImageViewsData(context: Context, item: ArticleDetailAdapterModel){
        item.data.data?.let {

            it.image?.let {
                it.alternates?.let {
                    it.standardResolution?.let {
                        it.url?.let {
                            loadImage(
                                context,
                                it,
                                articleDetailCoverPhoto!!
                            )
                        } ?: run {
                            articleDetailCoverPhoto?.visibility = View.VISIBLE
                            articleDetailCoverPhoto?.setImageResource(R.drawable.image_error_dark_mode)
                        }
                    } ?: run {
                        articleDetailCoverPhoto?.visibility = View.VISIBLE
                        articleDetailCoverPhoto?.setImageResource(R.drawable.image_error_dark_mode)
                    }
                } ?: run {
                    articleDetailCoverPhoto?.visibility = View.VISIBLE
                    articleDetailCoverPhoto?.setImageResource(R.drawable.image_error_dark_mode)
                }
            }?: run{
                articleDetailCoverPhoto?.visibility = View.VISIBLE
                articleDetailCoverPhoto?.setImageResource(R.drawable.image_error_dark_mode)
            }

            var badgeIcon: String? = null
            var badgeId: String? = null
            var badgeName: String? = null
            it.badge?.let {

                if(it.size != 0){

                    it[0]?.icons?.let {

                        it.png?.let {
                            badgeIcon = it
                        }?: run{
                            badgeIcon = " "
                        }

                    }?: run{
                        badgeIcon = " "
                    }

                    badgeId = it[0]?.id
                    badgeName = it[0]?.name
                }else{
                    badgeIcon = " "
                    badgeId = ""
                    badgeName = ""
                }

            }?: run{
                badgeIcon = " "
                badgeId = ""
                badgeName = ""
            }

            if(badgeIcon != " "){
                coverPhotoBadgeImage?.visibility = View.VISIBLE
                badgeLineCovertPhoto?.visibility = View.VISIBLE

                loadImageWithPicasso(badgeIcon!!, coverPhotoBadgeImage!!)

            }else{
                coverPhotoBadgeImage?.visibility = View.INVISIBLE
                badgeLineCovertPhoto?.visibility = View.INVISIBLE
            }

            var categoryIcon: String? = null
            it.categories?.let {
                if (it.size != 0) {
                    it[0]?.icons?.let {
                        it.png?.let {
                            categoryIcon = it
                        } ?: run {
                            categoryIcon = " "
                        }
                    } ?: run {
                        categoryIcon = " "
                    }
                } else
                    categoryIcon = " "
            }?: run{
                categoryIcon = " "
            }

            if(categoryIcon != " "){
                articleDetailAction?.visibility = View.VISIBLE
                articleActionBackground?.visibility = View.VISIBLE
                loadImageWithPicasso(
                    categoryIcon!!,
                    articleDetailAction!!
                )
            }else{
                articleActionBackground?.visibility = View.GONE
            }


            it.authors?.let {
                if (it.size != 0) {
                    it[0]?.image?.let { itemOfImage ->
                        itemOfImage.alternates?.let {
                            it.standardResolution?.let {
                                it.url?.let {
                                    loadImageWithPicasso(
                                        it,
                                        profilePhoto!!
                                    )
                                } ?: run {

                                }
                            } ?: run {

                            }
                        } ?: run {

                        }
                    } ?: run {

                    }
                }
            }?: run{

            }


        }?: run{
            articleDetailCoverPhoto?.visibility = View.VISIBLE
            articleDetailCoverPhoto?.setImageResource(R.drawable.image_error_dark_mode)
            coverPhotoBadgeImage?.visibility = View.INVISIBLE
            badgeLineCovertPhoto?.visibility = View.INVISIBLE
            articleActionBackground?.visibility = View.GONE
        }
    }

    private fun setViewsClickListener(pos: Int, item: ArticleDetailAdapterModel, listener: (Int, ArticleDetailAdapterModel, String) -> Unit){
        coverPhotoBadgeImage?.setOnClickListener {
            listener(pos, item, "BADGE")
        }

        articleActionBackground?.setOnClickListener {
            listener(pos, item, "CATEGORY")
        }

        articleDetailAction?.setOnClickListener {
            listener(pos, item, "CATEGORY")
        }

        profilePhoto?.setOnClickListener {
            listener(pos, item, "PROFILE")
        }

        editorUserName?.setOnClickListener {
            listener(pos, item, "PROFILE")
        }

        editorTitle?.setOnClickListener {
            listener(pos, item, "PROFILE")
        }
    }

    private fun loadImage(
        context: Context,
        imageUrl: String,
        imageView: ImageView
    ) {

        if (imageUrl.contains("gif", ignoreCase = true))
            loadImageWithGlide(context,imageUrl, imageView)
        else
            loadImageWithPicasso(imageUrl, imageView)
    }

    private fun loadImageWithPicasso(
        imageUrl: String,
        imageView: ImageView
    ) {
        Picasso.get().load(imageUrl)
            .into(imageView, object : Callback {
                override fun onSuccess() {
                    var a = 2
                }

                override fun onError(e: Exception?) {
                    imageView.setImageResource(R.drawable.empty_avatar)
                }
            })
    }


    private fun loadImageWithGlide(
        context: Context,
        imageUrl: String,
        imageView: ImageView
    ) {
        Glide.with(context)
            .load(imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    imageView.visibility = View.GONE
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
                    return false
                }
            })
            .into(imageView)
    }


    private fun calculateViewCount(strOfViewCount: String): String =
        when (strOfViewCount.length) {
            in 4..6 -> strOfViewCount.substring(
                0,
                strOfViewCount.length - 3
            ) + "b"
            in 7..9 -> strOfViewCount.substring(
                0,
                strOfViewCount.length - 6
            ) + "m"
            else -> strOfViewCount
        }

    @SuppressLint("SimpleDateFormat")
    fun convertFeedDate(utcDate: String): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val outputFormat = SimpleDateFormat("dd/MM/yyyy")
        val date = sdf.parse(utcDate)
        return outputFormat.format(date!!)
    }

}