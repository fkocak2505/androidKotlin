package com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.optimizedArticleDetail.adapter.holder

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.onedio.androidside.R
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.optimizedArticleDetail.model.ArticleDetailAdapterModel
import io.noties.markwon.Markwon
import kotlinx.android.synthetic.main.activity_article_detail.*
import java.text.SimpleDateFormat

class HolderItemHeader(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var articleDetailCoverPhoto: ImageView? =
        itemView.findViewById(R.id.itemOfArticleDetailCoverPhoto) as ImageView

    private var badgeLineCovertPhoto: View? =
        itemView.findViewById(R.id.itemOfBadgeLineCovertPhoto) as View

    private var coverPhotoBadgeImage: ImageView? =
        itemView.findViewById(R.id.itemOfCoverPhotoBadgeImage) as ImageView

    private var articleActionBackground: ImageView? =
        itemView.findViewById(R.id.itemOfArticleActionBackground) as ImageView

    private var articleDetailAction: ImageView? =
        itemView.findViewById(R.id.itemOfArticleDetailAction) as ImageView

    private var articleDetailTitle: TextView? =
        itemView.findViewById(R.id.itemOfArticleDetailTitle) as TextView

    private var editorUserProfileConstraintLayout: ConstraintLayout? =
        itemView.findViewById(R.id.itemOfEditorUserProfileConstraintLayout) as ConstraintLayout

    private var profilePhoto: ImageView? =
        itemView.findViewById(R.id.itemOfProfilePhoto) as ImageView

    private var editorUserName: TextView? =
        itemView.findViewById(R.id.itemOfEditorUserName) as TextView

    private var editorTitle: TextView? =
        itemView.findViewById(R.id.itemOfEditorTitle) as TextView

    private var readableCountConst: ConstraintLayout? =
        itemView.findViewById(R.id.itemOfReadableCountConst) as ConstraintLayout

    private var articleDetailItemReadCount: TextView? =
        itemView.findViewById(R.id.itemOfArticleDetailItemReadCount) as TextView

    private var infoText: TextView? =
        itemView.findViewById(R.id.itemOfInfoText) as TextView

    private var articleFeedDate: TextView? =
        itemView.findViewById(R.id.itemOfArticleFeedDate) as TextView

    private var widthLine: View? =
        itemView.findViewById(R.id.itemOfWidthLine) as View


    fun bindItems(
        context: Context,
        item: ArticleDetailAdapterModel,
        listener: (Int, ArticleDetailAdapterModel, String) -> Unit,
        pos: Int
    ) {

        widthLine?.visibility = View.VISIBLE
        editorUserProfileConstraintLayout?.visibility = View.VISIBLE

        setTextViewsData(context, item)

        setImageViewsData(context, item)

        setViewsClickListener(pos, item, listener)

        setDarkModeTheme(context)


    }

    private fun setTextViewsData(context: Context, item: ArticleDetailAdapterModel) {
        item.data.data?.let {

            it.title?.let {
                Markwon.create(context)
                    .setMarkdown(articleDetailTitle!!, it)
            } ?: run {
                articleDetailTitle?.text = ""
            }

            it.authors?.let {
                if (it.size != 0) {

                    it[0].username?.let {
                        editorUserName?.text = it
                    } ?: run {
                        editorUserName?.text = ""
                    }

                    it[0].occupation?.let {
                        editorTitle?.text = it
                    } ?: run {
                        editorTitle?.text = "Onedio Üyesi"
                    }
                } else {
                    editorUserName?.text = ""
                    editorTitle?.text = "Onedio Üyesi"
                }
            } ?: run {
                editorUserName?.text = ""
                editorTitle?.text = "Onedio Üyesi"
            }

            it.stats?.let {

                it.viewsTotal?.let {
                    readableCountConst?.visibility = View.VISIBLE
                    articleDetailItemReadCount?.text = calculateViewCount(it.toString())
                } ?: run {
                    readableCountConst?.visibility = View.GONE
                }

            } ?: run {
                readableCountConst?.visibility = View.GONE
            }

            it.createDate?.let {
                articleFeedDate?.text = convertFeedDate(it)
            } ?: run {
                articleFeedDate?.text = ""
            }

        } ?: run {
            articleDetailTitle?.text = ""
            editorUserName?.text = ""
            editorTitle?.text = "Onedio Üyesi"
            readableCountConst?.visibility = View.GONE
            articleFeedDate?.text = ""
        }

    }

    private fun setImageViewsData(context: Context, item: ArticleDetailAdapterModel) {
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
            } ?: run {
                articleDetailCoverPhoto?.visibility = View.VISIBLE
                articleDetailCoverPhoto?.setImageResource(R.drawable.image_error_dark_mode)
            }

            var badgeIcon: String? = null
            var badgeId: String? = null
            var badgeName: String? = null
            it.badge?.let {

                if (it.size != 0) {

                    it[0]?.icons?.let {

                        it.png?.let {
                            badgeIcon = it
                        } ?: run {
                            badgeIcon = " "
                        }

                    } ?: run {
                        badgeIcon = " "
                    }

                    badgeId = it[0]?.id
                    badgeName = it[0]?.name
                } else {
                    badgeIcon = " "
                    badgeId = ""
                    badgeName = ""
                }

            } ?: run {
                badgeIcon = " "
                badgeId = ""
                badgeName = ""
            }

            if (badgeIcon != " ") {
                coverPhotoBadgeImage?.visibility = View.VISIBLE
                badgeLineCovertPhoto?.visibility = View.VISIBLE

                loadImageWithGlide(
                    context,
                    badgeIcon!!,
                    coverPhotoBadgeImage!!,
                    R.drawable.empty_avatar
                )

            } else {
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
            } ?: run {
                categoryIcon = " "
            }

            if (categoryIcon != " ") {
                articleDetailAction?.visibility = View.VISIBLE
                articleActionBackground?.visibility = View.VISIBLE
                loadImageWithGlide(
                    context,
                    categoryIcon!!,
                    articleDetailAction!!,
                    R.drawable.empty_avatar
                )
            } else {
                articleActionBackground?.visibility = View.GONE
            }


            it.authors?.let {
                if (it.size != 0) {
                    it[0]?.image?.let { itemOfImage ->
                        itemOfImage.alternates?.let {
                            it.standardResolution?.let {
                                it.url?.let {
                                    loadImageWithGlide(
                                        context,
                                        it,
                                        profilePhoto!!,
                                        R.drawable.empty_avatar
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
            } ?: run {

            }


        } ?: run {
            articleDetailCoverPhoto?.visibility = View.VISIBLE
            articleDetailCoverPhoto?.setImageResource(R.drawable.image_error_dark_mode)
            coverPhotoBadgeImage?.visibility = View.INVISIBLE
            badgeLineCovertPhoto?.visibility = View.INVISIBLE
            articleActionBackground?.visibility = View.GONE
        }
    }

    private fun setViewsClickListener(
        pos: Int,
        item: ArticleDetailAdapterModel,
        listener: (Int, ArticleDetailAdapterModel, String) -> Unit
    ) {
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

    private fun setDarkModeTheme(context: Context) {
        val sharedPref =
            context.getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        val theme = sharedPref.getString("mode", "default")!!

        if (theme == "dark") {
            readableCountConst?.background = ContextCompat.getDrawable(
                context,
                R.drawable.bg_category_item_read_count_dark_mode
            )
            articleActionBackground?.setImageResource(R.drawable.icon_circle_dark_mode)
            profilePhoto?.foreground = ContextCompat.getDrawable(
                context,
                R.drawable.custom_image_view_white_dark_mode
            )
            widthLine?.setBackgroundColor(Color.parseColor("#0e1720"))
        } else {
            readableCountConst?.background = ContextCompat.getDrawable(
                context,
                R.drawable.bg_category_item_read_count
            )
            articleActionBackground?.setImageResource(R.drawable.ic_circle)
            profilePhoto?.foreground = ContextCompat.getDrawable(
                context,
                R.drawable.empty_avatar
            )
            widthLine?.setBackgroundColor(Color.parseColor("#e2e2e2"))
        }
    }

    private fun loadImage(
        context: Context,
        imageUrl: String,
        imageView: ImageView
    ) {

        loadImageWithGlide(context, imageUrl, imageView, R.drawable.image_error_dark_mode)

    }


    private fun loadImageWithGlide(
        context: Context,
        imageUrl: String,
        imageView: ImageView,
        hintImage: Int
    ) {
        Glide.with(context)
            .load(imageUrl)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    imageView.setImageResource(hintImage)
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