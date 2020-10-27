package com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.optimizedArticleDetail.adapter.holder

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.model.dashboard.homeFragment.commentModel.CommentModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel.ArticleTopCommentModel
import com.onedio.androidside.mvp.presenter.dashboard.homeFragment.articleDetail.ArticleDetailActivirtyPresenterImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleComment.ArticleCommentActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.ArticleDetailActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.optimizedArticleDetail.adapter.holder.commentAdapter.CommentAdapter
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.optimizedArticleDetail.model.ArticleDetailAdapterModel
import com.onedio.androidside.mvp.view.dashboard.homeFragment.profile.ProfileActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.widgets.hugeCardRecyclerViewAdapter.model.HugeCardModel
import com.onedio.androidside.prefs.StringSharedPrefs
import com.onedio.androidside.singleton.OnedioSingletonPattern

class HolderItemComment(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val viewPool = RecyclerView.RecycledViewPool()

    private var articleDetailCommentsRecyclerView: RecyclerView? =
        itemView.findViewById(R.id.itemOfArticleDetailCommentsRecyclerView) as RecyclerView

    private var seeAllComments: Button? =
        itemView.findViewById(R.id.seeAllComments) as Button

    private var infoFirstComment: TextView? =
        itemView.findViewById(R.id.itemOfInfoFirstComment) as TextView

    private var writeCommentArea: EditText? =
        itemView.findViewById(R.id.itemOfWriteCommentArea) as EditText

    private var itemOfViewLineComment: View? =
        itemView.findViewById(R.id.itemOfViewLineComment) as View



    private var listOfLikedCommentId: MutableList<String> = mutableListOf()

    private var articleId: String = ""
    private var legacyId: Long = 0
    private var isUserFavorite: Boolean = false

    @SuppressLint("SetTextI18n")
    fun bindItems(
        context: Context,
        item: ArticleDetailAdapterModel,
        listener: (Int, ArticleDetailAdapterModel, String) -> Unit,
        pos: Int,
        articleDetailActivirtyPresenterImpl: ArticleDetailActivirtyPresenterImpl
    ) {

        setDarkModeTheme(context)


        item.data.data?.let {
            it.id?.let {
                articleId = it
            }

            it.legacyId?.let {
                legacyId = it
            }

            it.isUserFavorite?.let {
                isUserFavorite = it
            }

            it.comments?.let {
                if (it.size != 0) {
                    seeAllComments?.visibility = View.VISIBLE
                    articleDetailCommentsRecyclerView?.visibility = View.VISIBLE
                    infoFirstComment?.visibility = View.INVISIBLE
                    writeCommentArea?.visibility = View.INVISIBLE

                    item.data.data?.let {
                        it.stats?.let {
                            it.comments?.let {
                                seeAllComments?.text = "Tüm Yorumları Gör (${it})"
                            } ?: run {
                                seeAllComments?.text = "0"
                            }
                        } ?: run {
                            seeAllComments?.text = "0"
                        }
                    } ?: run {
                        seeAllComments?.text = "0"
                    }

                    val childLayoutManager =
                        LinearLayoutManager(
                            articleDetailCommentsRecyclerView?.context,
                            RecyclerView.VERTICAL,
                            false
                        )
                    childLayoutManager.initialPrefetchItemCount = 10
                    articleDetailCommentsRecyclerView?.apply {
                        layoutManager = childLayoutManager
                        adapter = CommentAdapter(
                            articleDetailCommentsRecyclerView?.context!!,
                            addAllCommentsData(item.data.data?.comments!!)
                        ) { pos, itemOfComments, viewType, likeCountView, likeIconView ->
                            handleClickViewsComment(
                                context,
                                pos,
                                itemOfComments,
                                viewType,
                                articleDetailActivirtyPresenterImpl,
                                likeCountView,
                                likeIconView
                            )
                        }
                        setRecycledViewPool(viewPool)
                    }
                } else {
                    seeAllComments?.visibility = View.INVISIBLE
                    articleDetailCommentsRecyclerView?.visibility = View.INVISIBLE
                    infoFirstComment?.visibility = View.VISIBLE
                    writeCommentArea?.visibility = View.VISIBLE
                }
            } ?: run {
                seeAllComments?.visibility = View.INVISIBLE
                articleDetailCommentsRecyclerView?.visibility = View.INVISIBLE
                infoFirstComment?.visibility = View.VISIBLE
                writeCommentArea?.visibility = View.VISIBLE
            }
        } ?: run {
            seeAllComments?.visibility = View.INVISIBLE
            articleDetailCommentsRecyclerView?.visibility = View.INVISIBLE
            infoFirstComment?.visibility = View.VISIBLE
            writeCommentArea?.visibility = View.VISIBLE
        }

        seeAllComments?.setOnClickListener {
            val intent =
                Intent(
                    context,
                    ArticleCommentActivityViewImpl::class.java
                )
            intent.putExtra(
                "ARTICLE_COMMENT_DATA", Gson().toJson(
                    HugeCardModel(
                        "",
                        legacyId,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        isUserFavorite,
                        null,
                        null,
                        false,
                        ""

                    )
                )
            )
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }

        writeCommentArea?.setOnClickListener {

            OnedioSingletonPattern.instance.setDeleteComment(false)
            OnedioSingletonPattern.instance.setActivity(ArticleDetailActivityViewImpl::class.java)

            val intent =
                Intent(
                    context,
                    ArticleCommentActivityViewImpl::class.java
                )
            intent.putExtra(
                "ARTICLE_COMMENT_DATA", Gson().toJson(
                    HugeCardModel(
                        "",
                        legacyId,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        isUserFavorite,
                        null,
                        null,
                        false,
                        ""

                    )
                )
            )
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
            //startAnim(context)
        }
    }

    private fun handleClickViewsComment(
        context: Context,
        pos: Int,
        itemOfComment: CommentModel,
        viewType: String,
        articleDetailActivirtyPresenterImpl: ArticleDetailActivirtyPresenterImpl,
        likeCountView: TextView,
        likeIconView: ImageView
    ) {
        when (viewType) {
            "PROFILE" -> {
                goProfileActivity(context, itemOfComment)
            }
            "LIKE" -> {
                likeComment(
                    itemOfComment,
                    likeCountView,
                    likeIconView,
                    articleDetailActivirtyPresenterImpl
                )
            }
            "REPLY" -> {
                goCommentActivity(context, itemOfComment)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun addAllCommentsData(arrOfArticleDetailComment: MutableList<ArticleTopCommentModel>): MutableList<CommentModel> {

        val commentArr: MutableList<CommentModel> = mutableListOf()

        listOfLikedCommentId = OnedioCommon.convertString2ListStr(
            StringSharedPrefs(
                OnedioCommon.getSharedPref()!!,
                OnedioConstant.SHARED_PREF_LIKE_COMMENT_DATA,
                OnedioCommon.convertList2StringStr(
                    listOfLikedCommentId
                )
            ).getValue()
        )

        arrOfArticleDetailComment.forEach { itemOfCommentData ->

            var id: String? = null
            itemOfCommentData.id?.let {
                id = it
            } ?: run {
                id = ""
            }

            var userName: String? = null
            var userId: String? = null
            itemOfCommentData.user?.let {
                it.username?.let {
                    userName = it
                } ?: run {
                    userName = ""
                }

                it.id?.let {
                    userId = it
                } ?: run {
                    userId = ""
                }

            } ?: run {
                userName = ""
                userId = ""
            }

            var text: String? = null
            itemOfCommentData.text?.let {
                text = it
            } ?: run {
                text = ""
            }

            var createDate: String? = null
            itemOfCommentData.createDate?.let {
                createDate = OnedioCommon.convertFeedDate(it)
            } ?: run {
                createDate = ""
            }

            var likes: String? = null
            itemOfCommentData.ratings?.let {
                it.likes?.let {
                    likes = it.toString()
                } ?: run {
                    likes = "0"
                }
            } ?: run {
                likes = "0"
            }


            var image: String? = null
            itemOfCommentData.user?.let {
                it.image?.let {
                    it.alternates?.let {
                        it.standardResolution?.let {
                            it.url?.let {
                                image = it
                            } ?: run {
                                image = ""
                            }
                        } ?: run {
                            image = ""
                        }
                    } ?: run {
                        image = ""
                    }
                } ?: run {
                    image = ""
                }
            } ?: run {
                image = ""
            }

            commentArr.add(
                CommentModel(
                    id!!,
                    "0",
                    userName!!,
                    userId!!,
                    image!!,
                    text!!,
                    createDate!!,
                    likes!!,
                    null,
                    listOfLikedCommentId.contains(itemOfCommentData.id)
                )
            )
        }

        return commentArr

    }

    private fun setDarkModeTheme(context: Context){
        val sharedPref =
            context.getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        val theme = sharedPref.getString("mode", "default")!!

        if (theme == "dark") {
            itemOfViewLineComment?.setBackgroundColor(Color.parseColor("#0e1720"))

            writeCommentArea?.background =
                ContextCompat.getDrawable(context, R.drawable.custom_edittext_dark_mode)
            writeCommentArea?.setHintTextColor(Color.parseColor("#FFFFFF"))
            writeCommentArea?.setTextColor(Color.parseColor("#FFFFFF"))
            infoFirstComment?.setTextColor(Color.parseColor("#FFFFFF"))
        }else{
            itemOfViewLineComment?.setBackgroundColor(Color.parseColor("#e2e2e2"))

            writeCommentArea?.background =
                ContextCompat.getDrawable(context, R.drawable.custom_edittext)
            writeCommentArea?.setHintTextColor(Color.parseColor("#191919"))
            writeCommentArea?.setTextColor(Color.parseColor("#191919"))
            infoFirstComment?.setTextColor(Color.parseColor("#191919"))
        }
    }

    private fun likeComment(
        itemOfComment: CommentModel,
        likeCountView: TextView,
        likeIconView: ImageView,
        articleDetailActivirtyPresenterImpl: ArticleDetailActivirtyPresenterImpl
    ) {

        if (itemOfComment.isCommentLiked) {
            itemOfComment.isCommentLiked = false
            likeIconView.setImageResource(R.drawable.ic_like)
            articleDetailActivirtyPresenterImpl.unLike(
                itemOfComment,
                likeCountView
            )
        } else {
            itemOfComment.isCommentLiked = true
            likeIconView.setImageResource(R.drawable.ic_like_blue)
            articleDetailActivirtyPresenterImpl.likeComment(
                itemOfComment,
                likeCountView
            )
        }
    }

    private fun goCommentActivity(context: Context, itemOfComment: CommentModel) {
        val articleItem =
            HugeCardModel(
                articleId,
                legacyId,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                false,
                null,
                null,
                false,
                ""
            )

        OnedioSingletonPattern.instance.setDeleteComment(
            false
        )
        OnedioSingletonPattern.instance.setCommentModel(
            itemOfComment
        )
        OnedioSingletonPattern.instance.setActivity(
            ArticleDetailActivityViewImpl::class.java
        )

        val intent =
            Intent(context, ArticleCommentActivityViewImpl::class.java)
        intent.putExtra("ARTICLE_COMMENT_DATA", Gson().toJson(articleItem))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)

        //startAnim(context)
    }

    private fun goProfileActivity(context: Context, itemOfComment: CommentModel) {
        val intent = Intent(context, ProfileActivityViewImpl::class.java)
        intent.putExtra("IS_USER_OWN_PROFILE", false)
        intent.putExtra("ANOTHER_USER_PROFILE", itemOfComment.userId)
        context.startActivity(intent)
    }

    /*private fun startAnim(context: Context) {
        OnedioCustomIntent.startAnim(context, "l2r")
    }*/


}