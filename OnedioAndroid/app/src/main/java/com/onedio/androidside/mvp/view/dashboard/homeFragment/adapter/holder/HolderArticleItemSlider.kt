package com.onedio.androidside.mvp.view.dashboard.homeFragment.adapter.holder

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.models.SlideModel
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.doubleclick.PublisherAdRequest
import com.google.android.gms.ads.doubleclick.PublisherAdView
import com.google.gson.Gson
import com.onedio.androidside.R
import com.onedio.androidside.common.customSlider.SliderWithAnimAdapter
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.sliderModel.FeedArticleModel
import com.onedio.androidside.mvp.view.dashboard.homeFragment.ArticlesFragmentAdapterModel
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.optimizedArticleDetail.model.ArticleDetailAdapterModel
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.swipableArticleDetail.SwipableArticleDetailActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.swipableArticleDetail.model.SwipableArticleDetailObj
import com.onedio.androidside.mvp.view.dashboard.homeFragment.widgets.hugeCardRecyclerViewAdapter.model.HugeCardModel
import com.onedio.androidside.singleton.OnedioSingletonPattern
import com.smarteist.autoimageslider.IndicatorAnimations
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import kotlinx.android.synthetic.main.widget_slider.view.*

class HolderArticleItemSlider(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var articleSliderList: SliderView? =
        itemView.findViewById(R.id.articleSliderList) as SliderView

    private var sliderAdapter: SliderWithAnimAdapter? = null
    private var sliderList: MutableList<SlideModel> = mutableListOf()

    fun bindItems(
        context: Context,
        item: ArticlesFragmentAdapterModel,
        listener: (Int, ArticlesFragmentAdapterModel) -> Unit,
        pos: Int,
        allArticleFeedList: MutableList<HugeCardModel>
    ) {

        prepareSliderData(item.data)

        articleSliderList?.setIndicatorAnimation(IndicatorAnimations.THIN_WORM)
        articleSliderList?.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
        articleSliderList?.autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_RIGHT
        articleSliderList?.scrollTimeInSec = 1
        articleSliderList?.indicatorSelectedColor = Color.parseColor("#276cd9")
        articleSliderList?.indicatorUnselectedColor = Color.parseColor("#ffffff")
        articleSliderList?.isAutoCycle = false


        sliderAdapter = SliderWithAnimAdapter(
            context,
            sliderList
        ) { itemOfSlider, index ->
            if (OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility == View.VISIBLE) {
                OnedioSingletonPattern.instance.getProfileSettingPopup()
                    ?.visibility = View.GONE
            } else {
                when {
                    itemOfSlider.redirectUrl != "" -> {
                        val browserIntent =
                            Intent(Intent.ACTION_VIEW, Uri.parse(itemOfSlider.redirectUrl))
                        browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        context.startActivity(browserIntent)
                    }
                    else -> {
                        val sSliderObj = SwipableArticleDetailObj(allArticleFeedList, index)
                        val intent = Intent(
                            context,
                            SwipableArticleDetailActivityViewImpl::class.java
                        )
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        intent.putExtra("SWIPE_ARTICLE_DATA", Gson().toJson(sSliderObj))
                        intent.putExtra("LEGACY_ID", itemOfSlider.sliderItemLegacyId)
                        context.startActivity(intent)
                    }
                }

            }
        }

        articleSliderList?.setSliderAdapter(sliderAdapter!!)


    }

    private fun prepareSliderData(articlesData: MutableList<FeedArticleModel>){
        sliderList = mutableListOf()


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

            sliderList.add(
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