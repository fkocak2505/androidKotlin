package com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.optimizedArticleDetail.adapter.holder

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import cn.jzvd.JzvdStd
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.gson.Gson
import com.onedio.androidside.R
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.newResponse.ArticleFeedDetailsEntryModel
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.ArticleDetailActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.optimizedArticleDetail.adapter.holder.entryAdapter.ArticleEntriesAdapter
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.optimizedArticleDetail.layutManager.PreCachingLayoutManager
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.optimizedArticleDetail.model.ArticleDetailAdapterModel
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.optimizedArticleDetail.utils.DeviceUtils
import com.onedio.androidside.mvp.view.dashboard.homeFragment.widgets.hugeCardRecyclerViewAdapter.model.HugeCardModel
import com.onedio.androidside.singleton.OnedioSingletonPattern
import com.yandex.metrica.YandexMetrica

class HolderItemEntry(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val viewPool = RecyclerView.RecycledViewPool()

    private var entryRecyclerView: RecyclerView? =
        itemView.findViewById(R.id.itemOfEntryRecyclerView) as RecyclerView

    private var context: Context? = null

    private var arrOfEntries: MutableList<ArticleFeedDetailsEntryModel> = mutableListOf()

    private var recommendWidgetData4Entry = ArticleFeedDetailsEntryModel()

    private var isReqRecommendWidget: Boolean = false

    fun bindItems(
        context: Context,
        item: ArticleDetailAdapterModel,
        listener: (Int, ArticleDetailAdapterModel, String) -> Unit,
        pos: Int
    ) {

        this.context = context

        val childEntriesLayoutManager =
            PreCachingLayoutManager(entryRecyclerView?.context!!, RecyclerView.VERTICAL, false)
        childEntriesLayoutManager.setExtraLayoutSpace(DeviceUtils.getScreenHeight(entryRecyclerView?.context!!))

        /*val childEntriesLayoutManager =
            PreCachingLayoutManager(entryRecyclerView?.context!!, RecyclerView.VERTICAL, false)
        childEntriesLayoutManager.initialPrefetchItemCount = 5*/

        item.data.data?.let {
            it.entries?.let {
                arrOfEntries = it
            } ?: run {
                arrOfEntries.clear()
            }
        } ?: run {
            arrOfEntries.clear()
        }

        item.widgetData?.let {
            it.id?.let {
                recommendWidgetData4Entry.id = it
            }

            it.legacyId?.let {
                recommendWidgetData4Entry.legacyId = it
            }

            it.title?.let {
                recommendWidgetData4Entry.title = it
            }

            it.image?.let {
                recommendWidgetData4Entry.image = it
            }

            recommendWidgetData4Entry.mode = "recommendWidget"

            arrOfEntries.add(3, recommendWidgetData4Entry)

        }


        entryRecyclerView?.apply {
            layoutManager = childEntriesLayoutManager
            adapter =
                ArticleEntriesAdapter(
                    entryRecyclerView?.context!!,
                    arrOfEntries
                ) { itemPos, itemEntries, viewsType ->

                    handleClickViews(context, itemPos, itemEntries, viewsType)

                }
            setItemViewCacheSize(5)
            setRecycledViewPool(viewPool)
        }

    }

    //==============================================================================================
    /**
     * Handle Click Views..
     */
    //==============================================================================================
    private fun handleClickViews(
        context: Context,
        itemPos: Int,
        itemEntries: ArticleFeedDetailsEntryModel,
        viewsType: String
    ) {
        when (viewsType) {
            "ONEDIO_EMBED" -> {
                clickUserProfilePhoto(itemEntries)
            }
            "RECOMMEND_WIDGET" -> {
                clickRecommendWidget(context, itemEntries)
            }
        }
    }

    private fun clickRecommendWidget(context: Context, itemEntries: ArticleFeedDetailsEntryModel) {
        val firebaseAnalytics = FirebaseAnalytics.getInstance(context)
        val params = Bundle()
        firebaseAnalytics.logEvent("in_article_recommendation_click", params)

        val mapOfFeedAppMetrica = HashMap<String, Any>()
        YandexMetrica.reportEvent("in_article_recommendation_click", mapOfFeedAppMetrica)

        var id = ""
        itemEntries.id?.let {
            id = it
        }

        var legacyId: Long = 0
        itemEntries.legacyId?.let {
            legacyId = it
        }

        val articleItem =
            HugeCardModel(
                id,
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
                "",
                "",
                false,
                ""
            )


        OnedioSingletonPattern.instance.setActivity(
            ArticleDetailActivityViewImpl::class.java
        )

        val intent =
            Intent(context, ArticleDetailActivityViewImpl::class.java)
        intent.putExtra("ARTICLE_DETAIL_DATA", Gson().toJson(articleItem))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)

        //startAnim(context)
    }

    /*private fun startAnim(context: Context) {
        OnedioCustomIntent.startAnim(context, "l2r")
    }*/

    //==============================================================================================
    /**
     * Click User Profile..
     */
    //==============================================================================================
    private fun clickUserProfilePhoto(itemEntries: ArticleFeedDetailsEntryModel) {
        JzvdStd.releaseAllVideos()

        var id: String = ""
        itemEntries.embedArticle?.let {
            it.id?.let {
                id = it
            } ?: run {
                id = ""
            }
        } ?: run {
            id = ""
        }

        var legacyId: Long = 0
        itemEntries.embedArticle?.let {
            it.legacyId?.let {
                legacyId = it
            } ?: run {
                legacyId = 0
            }
        } ?: run {
            legacyId = 0
        }

        var categoryName: String = ""
        var categoryId: String = ""
        itemEntries.embedArticle?.let {
            it.categories?.let {
                if (it.size != 0) {

                    it[0].name?.let {
                        categoryName = it
                    } ?: run {
                        categoryName = ""
                    }

                    it[0].id?.let {
                        categoryId = it
                    } ?: run {
                        categoryId = ""
                    }

                } else {
                    categoryName = ""
                    categoryId = ""
                }
            } ?: run {
                categoryName = ""
                categoryId = ""
            }
        } ?: run {
            categoryName = ""
            categoryId = ""
        }


        val articleItem =
            HugeCardModel(
                id,
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
                categoryName,
                categoryId,
                false,
                ""
            )


        OnedioSingletonPattern.instance.setActivity(
            ArticleDetailActivityViewImpl::class.java
        )

        val intent =
            Intent(context, ArticleDetailActivityViewImpl::class.java)
        intent.putExtra("ARTICLE_DETAIL_DATA", Gson().toJson(articleItem))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context?.startActivity(intent)
    }

}