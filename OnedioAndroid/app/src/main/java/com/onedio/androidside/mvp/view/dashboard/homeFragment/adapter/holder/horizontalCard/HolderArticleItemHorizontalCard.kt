package com.onedio.androidside.mvp.view.dashboard.homeFragment.adapter.holder.horizontalCard

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.models.SlideModel
import com.onedio.androidside.R
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.sliderModel.FeedArticleModel
import com.onedio.androidside.mvp.view.dashboard.homeFragment.ArticlesFragmentAdapterModel
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.optimizedArticleDetail.layutManager.PreCachingLayoutManager
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.optimizedArticleDetail.utils.DeviceUtils
import com.onedio.androidside.mvp.view.dashboard.homeFragment.sliderListAdapter.SecondSliderHugeCard

class HolderArticleItemHorizontalCard(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val viewPool = RecyclerView.RecycledViewPool()

    private var secondSliderHugeCard: RecyclerView? =
        itemView.findViewById(R.id.secondSliderHugeCard) as RecyclerView

    private var horizontalCardList: MutableList<SlideModel> = mutableListOf()

    fun bindItems(
        context: Context,
        item: ArticlesFragmentAdapterModel,
        listener: (Int, ArticlesFragmentAdapterModel) -> Unit,
        pos: Int
    ) {

        prepareHorizontalCardData(item.data)

        val childEntriesLayoutManager =
            PreCachingLayoutManager(secondSliderHugeCard?.context!!, RecyclerView.HORIZONTAL, false)
        childEntriesLayoutManager.setExtraLayoutSpace(DeviceUtils.getScreenHeight(secondSliderHugeCard?.context!!))

        secondSliderHugeCard?.apply {
            layoutManager = childEntriesLayoutManager
            adapter =
                SecondSliderHugeCard(
                    secondSliderHugeCard?.context!!,
                    horizontalCardList
                ) { itemOfSlideModel, index ->

                    //handleClickViews(context, itemPos, itemEntries, viewsType)

                }
            setRecycledViewPool(viewPool)
        }

    }

    private fun prepareHorizontalCardData(articlesData: MutableList<FeedArticleModel>) {

        for (i in 0 until articlesData.size) {

            var categoryId: String? = null
            var categoryName: String? = null

            articlesData[i].categories?.let {
                if (articlesData[i].categories?.size != 0) {
                    categoryId = articlesData[i].categories?.get(0)?.id!!
                    categoryName = articlesData[i].categories?.get(0)?.name!!
                } else {
                    categoryId = ""
                    categoryName = ""
                }
            } ?: run {
                categoryId = ""
                categoryName = ""
            }

            var redirectUrl: String? = null
            articlesData[i].redirectUrl?.let {
                redirectUrl = it
            } ?: run {
                redirectUrl = ""
            }

            var sLegacyId: Long? = null
            articlesData[i].legacyId?.let {
                sLegacyId = it
            } ?: run {
                sLegacyId = 0
            }

            var sArticleId: String? = null
            articlesData[i].id?.let {
                sArticleId = it
            } ?: run {
                sArticleId = ""
            }

            var sImage: String? = null
            articlesData[i].image?.let {
                it.alternates?.let {
                    it.standardResolution?.let {
                        it.url?.let {
                            sImage = it
                        } ?: run {
                            sImage = ""
                        }
                    } ?: run {
                        sImage = ""
                    }
                } ?: run {
                    sImage = ""
                }
            } ?: run {
                sImage = ""
            }

            var sTitle: String? = null
            articlesData[i].title?.let {
                sTitle = it
            } ?: run {
                sTitle = ""
            }

            var sShowInWebView: Boolean? = null
            articlesData[i].showInWebview?.let {
                sShowInWebView = it
            } ?: run {
                sShowInWebView = false
            }

            var isUserFavorite: Boolean? = null
            articlesData[i].isUserFavorite?.let {
                isUserFavorite = it
            } ?: run {
                isUserFavorite = false
            }

            horizontalCardList.add(
                SlideModel(
                    sLegacyId!!,
                    sArticleId!!,
                    sImage!!,
                    sTitle!!,
                    true,
                    categoryId!!,
                    categoryName!!,
                    sShowInWebView!!,
                    redirectUrl!!
                )
            )
        }
    }

}