package com.onedio.androidside.mvp.view.dashboard.homeFragment

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.common.model.OnedioEzDialogMessageModel4Fragment
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.homeFragment.HomeFeedFragmentActivityModelImpl
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.feedModel.TopComments
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.feedModel.UserComment
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel.Response4ArticlesFeed
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.sliderModel.FeedArticleModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.sliderModel.Response4ArticleSlider
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.widgetConfig.Response4WidgetConfig
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.widgetConfig.WidgetsData
import com.onedio.androidside.mvp.presenter.dashboard.homeFragment.ArticlesFragmentViewPresenterImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.adapter.ArticlesRecyclerViewAdapter
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.optimizedArticleDetail.layutManager.PreCachingLayoutManager
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.optimizedArticleDetail.utils.DeviceUtils
import com.onedio.androidside.mvp.view.dashboard.homeFragment.widgets.hugeCardRecyclerViewAdapter.model.HugeCardModel
import com.onedio.androidside.prefs.StringSharedPrefs
import kotlinx.android.synthetic.main.fragment_articles.view.*
import kotlin.system.exitProcess

class ArticlesFragmentViewImpl : Fragment(), IArticlesFragmentView {

    private var mActivity: Activity? = null
    private lateinit var viewP: View

    private lateinit var articlesFragmentViewPresenterImpl: ArticlesFragmentViewPresenterImpl

    private var isNotif: Boolean = false
    private var isScreenLoadMultiple: Boolean = false

    private lateinit var sharedPrefs: SharedPreferences

    private var allArticlesList: MutableList<HugeCardModel> = mutableListOf()
    private var feedList4Adapter: MutableList<ArticlesFragmentAdapterModel> = mutableListOf()
    private var widgetsData: MutableList<WidgetsData> = mutableListOf()

    private var page = 0


    companion object {
        fun newInstance(isNotif: Boolean): ArticlesFragmentViewImpl {
            val fragmentFeedHome =
                ArticlesFragmentViewImpl()
            val args = Bundle()
            args.putBoolean("IS_NOTIF", isNotif)
            fragmentFeedHome.arguments = args

            return fragmentFeedHome
        }
    }

    //==================================================================================================================
    /**
     * OnCreate View method..
     */
    //==================================================================================================================
    @SuppressLint("InflateParams")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val bundle = this.arguments
        bundle?.let {
            isNotif = it.getBoolean("IS_NOTIF")
        } ?: run {
            isNotif = false
        }

        viewP = inflater.inflate(R.layout.fragment_articles, null)

        return viewP
    }

    //==================================================================================================================
    /**
     * OnCreatedView Method. Running after onCreateView
     */
    //==================================================================================================================
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPrefs =
            mActivity?.getSharedPreferences(
                OnedioConstant.SHARED_PREF_FILE_NAME,
                Context.MODE_PRIVATE
            )!!

        savedInstanceState?.let {
            isScreenLoadMultiple = true
        } ?: run {
            isScreenLoadMultiple = false
        }

        if (savedInstanceState == null) {
            getConfig()
        }

    }

    //==================================================================================================================
    /**
     * Get Config Data..
     */
    //==================================================================================================================
    private fun getConfig() {
        articlesFragmentViewPresenterImpl =
            ArticlesFragmentViewPresenterImpl(
                HomeFeedFragmentActivityModelImpl(),
                this
            )

        articlesFragmentViewPresenterImpl.getWidgetConfig()
    }

    //==================================================================================================================
    /**
     * Handle Config Data
     */
    //==================================================================================================================
    override fun handleWidgetConfigData(response4WidgetConfig: Response4WidgetConfig) {
        getOnedioButtonData()

        response4WidgetConfig.data?.let {
            it.widgets?.let {
                if (it.size != 0) {
                    widgetsData = it
                    getSliderData()
                }
            }
        }
    }

    //==============================================================================================
    /**
     * Init Recycler..
     */
    //==============================================================================================
    private fun initRecycler() {

        val mLayoutManager =
            PreCachingLayoutManager(
                mActivity?.applicationContext!!,
                RecyclerView.VERTICAL,
                false
            )
        mLayoutManager.setExtraLayoutSpace(DeviceUtils.getScreenHeight(mActivity?.applicationContext!!))

        viewP.articlesRecyclerView.apply {
            layoutManager = mLayoutManager
            setItemViewCacheSize(5)
            adapter = ArticlesRecyclerViewAdapter(
                mActivity!!,
                mActivity?.applicationContext!!,
                feedList4Adapter,
                articlesFragmentViewPresenterImpl,
                allArticlesList
            ) { position, itemOfArticlesModel->
                //handleClickViews(position, itemOfArticlePart, viewType)
            }
        }

        /*articleDetailRecyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val firstVisibleItemPos = mLayoutManager.findFirstCompletelyVisibleItemPosition()
                if (firstVisibleItemPos == 6 && !isReqRecommendWidget) {
                    val params = Bundle()
                    firebaseAnalytics =
                        FirebaseAnalytics.getInstance(this@ArticleDetailWithRecyclerView)
                    firebaseAnalytics.logEvent("in_article_recommendation_view", params)

                    val mapOfFeedAppMetrica = HashMap<String, Any>()
                    YandexMetrica.reportEvent("in_article_recommendation_view", mapOfFeedAppMetrica)
                    isReqRecommendWidget = true
                }


            }
        })*/
    }

    //==================================================================================================================
    /**
     * Slider - 1 Button Data
     */
    //==================================================================================================================
    private fun getSliderData() {
        articlesFragmentViewPresenterImpl.getArticleSliderData()
    }

    //==================================================================================================================
    /**
     * Handle Slider - 1 Data
     */
    //==================================================================================================================
    override fun handleArticleSliderData(response4ArticleSlider: Response4ArticleSlider) {
        feedList4Adapter.add(
            ArticlesFragmentAdapterModel(
                "googleAdsTop",
                mutableListOf(),
                null,
                false
            )
        )


        response4ArticleSlider.data?.let {
            it.feed?.let {
                if (it.size != 0) {
                    fillSliderData2AllArticleList(0, it.size, it)

                    val eightSliderData = it.subList(0, 8)
                    val lastThreeSliderData = it.subList(8, it.size)

                    feedList4Adapter.add(
                        ArticlesFragmentAdapterModel(
                            "slider",
                            eightSliderData,
                            null
                        )
                    )

                    /*feedList4Adapter.add(
                        ArticlesFragmentAdapterModel(
                            "hugecardSlider",
                            lastThreeSliderData
                        )
                    )*/

                    prepareArticlesData(lastThreeSliderData)



                }
            }
        }

        widgetsData.forEach {
            if (it.name == "widget:mobile:slider-2")
                getSecondSliderData(it.name, it.targetId!!, it.count!!)
        }

    }

    //==================================================================================================================
    /**
     * Slider - 2  Data
     */
    //==================================================================================================================
    private fun getSecondSliderData(wName: String, targetId: String, count: Int) {
        articlesFragmentViewPresenterImpl.getArticlesByTagId(wName, targetId, 1, count)
    }

    //==================================================================================================================
    /**
     * Handle Slider - 2 Data
     */
    //==================================================================================================================
    override fun handleArticlesByTagId(
        wName: String,
        response4ArticlesFeed: Response4ArticlesFeed
    ) {
        when (wName.split("$")[0]) {
            OnedioConstant.WIDGET_MOBILE_SLIDER_2 -> {
                prepareSecondSliderData(response4ArticlesFeed)


                response4ArticlesFeed.data?.let {
                    it.feed?.let {
                        feedList4Adapter.add(
                            ArticlesFragmentAdapterModel(
                                "horizontalSlideCard",
                                it,
                                null
                            )
                        )
                    }
                }

                getArticlesFeed(
                    page,
                    sumOfWidgetCount(widgetsData)
                )
            }
        }
    }

    private fun getArticlesFeed(page: Int, perPage: Int) {
        articlesFragmentViewPresenterImpl.getArticlesFeed(page, perPage)
    }

    override fun handleArticlesFeedData(response4ArticlesFeed: Response4ArticlesFeed) {

        response4ArticlesFeed.data?.let {
            it.feed?.let {
                prepareArticlesHugeCardData(it)

                /*feedList4Adapter.add(
                    ArticlesFragmentAdapterModel(
                        "hugeCard",
                        it
                    )
                )*/

                prepareArticlesData(it)

                initRecycler()

            }
        }

    }

    private fun prepareArticlesData(articlesData: MutableList<FeedArticleModel>){
        for (i in 0 until articlesData.size) {
            val itemOfArticlesHugeCardData = articlesData[i]

            var articleId: String? = null
            itemOfArticlesHugeCardData.id?.let {
                articleId = itemOfArticlesHugeCardData.id
            } ?: run {
                articleId = ""
            }

            var legacyId: Long? = null
            itemOfArticlesHugeCardData.legacyId?.let {
                legacyId = itemOfArticlesHugeCardData.legacyId
            } ?: run {
                legacyId = 0
            }

            var articleUrl: String? = null
            itemOfArticlesHugeCardData.image?.let {
                it.alternates?.let {
                    it.standardResolution?.let {
                        articleUrl = it.url
                    } ?: run {
                        articleUrl = " "
                    }
                } ?: run {
                    articleUrl = " "
                }
            } ?: run {
                articleUrl = " "
            }


            var categoryUrl: String? = null
            itemOfArticlesHugeCardData.categories?.let {
                if (it.size != 0) {
                    it[0].icons?.let {
                        categoryUrl = it.png
                    } ?: run {
                        categoryUrl = " "
                    }
                } else {
                    categoryUrl = " "
                }
            } ?: run {
                categoryUrl = " "
            }

            var coverPhotoText: String? = null
            itemOfArticlesHugeCardData.badge?.let {
                if (itemOfArticlesHugeCardData.badge.size != 0) {
                    itemOfArticlesHugeCardData.badge[0].name?.let {
                        coverPhotoText = it
                    } ?: run {
                        coverPhotoText = " "
                    }
                } else {
                    coverPhotoText = " "
                }
            } ?: run {
                coverPhotoText = " "
            }


            var coverPhotoBadgeImage: String? = null
            itemOfArticlesHugeCardData.badge?.let {
                if (itemOfArticlesHugeCardData.badge.size != 0) {
                    itemOfArticlesHugeCardData.badge[0].icons?.let {
                        coverPhotoBadgeImage = it.png
                    } ?: run {
                        coverPhotoBadgeImage = " "
                    }
                } else
                    coverPhotoBadgeImage = " "
            } ?: run {
                coverPhotoBadgeImage = " "
            }

            var badgeId: String? = null
            itemOfArticlesHugeCardData.badge?.let {
                if (it.size != 0) {
                    it[0].id?.let {
                        badgeId = it
                    } ?: run {
                        badgeId = ""
                    }
                } else {
                    badgeId = ""
                }
            } ?: run {
                badgeId = ""
            }


            var articleTitle: String? = null
            itemOfArticlesHugeCardData.title?.let {
                articleTitle = it
            } ?: run {
                articleTitle = " "
            }


            var articleDate: String? = null
            itemOfArticlesHugeCardData.createDate?.let {
                articleDate = OnedioCommon.convertFeedDate(it)
            } ?: run {
                articleDate = " "
            }

            var emojiAction1: String? = null
            itemOfArticlesHugeCardData.reactions?.let {
                if (it.size != 0) {
                    it[0].icons?.let {
                        emojiAction1 = it.png
                    } ?: run {
                        emojiAction1 = " "
                    }
                } else {
                    emojiAction1 = " "
                }
            } ?: run {
                emojiAction1 = " "
            }

            var emojiAction2: String? = null
            itemOfArticlesHugeCardData.reactions?.let {
                if (it.size != 0) {
                    it[1].icons?.let {
                        emojiAction2 = it.png
                    } ?: run {
                        emojiAction2 = " "
                    }
                } else {
                    emojiAction2 = " "
                }
            } ?: run {
                emojiAction2 = " "
            }

            var emojiAction3: String? = null
            itemOfArticlesHugeCardData.reactions?.let {
                if (it.size != 0) {
                    it[2].icons?.let {
                        emojiAction3 = it.png
                    } ?: run {
                        emojiAction3 = " "
                    }
                } else {
                    emojiAction3 = " "
                }
            } ?: run {
                emojiAction3 = " "
            }

            var emojiReactionCount: Int? = null
            itemOfArticlesHugeCardData.stats?.let {
                it.reactions?.let {
                    emojiReactionCount = it
                } ?: run {
                    emojiReactionCount = 0
                }
            } ?: run {
                emojiReactionCount = 0
            }


            var commentCount: Int? = null
            itemOfArticlesHugeCardData.stats?.let {
                it.comments?.let {
                    commentCount = it
                } ?: run {

                }
            } ?: run {
                commentCount = 0
            }


            var topComment: TopComments? = null
            if (itemOfArticlesHugeCardData.topComments?.size != 0) {
                itemOfArticlesHugeCardData.topComments?.get(0)?.let {
                    if (itemOfArticlesHugeCardData.topComments.size != 0) {
                        topComment =
                            TopComments()
                        topComment!!.user =
                            UserComment()
                        topComment!!.id = itemOfArticlesHugeCardData.topComments[0].id
                        topComment!!.user?.id = itemOfArticlesHugeCardData.topComments[0].user?.id
                        topComment!!.user?.userName =
                            itemOfArticlesHugeCardData.topComments[0].user?.username
                        topComment!!.user?.avatar =
                            itemOfArticlesHugeCardData.topComments[0].user?.image?.alternates?.standardResolution?.url
                        topComment!!.text = itemOfArticlesHugeCardData.topComments[0].text
                        topComment!!.likes =
                            itemOfArticlesHugeCardData.topComments[0].ratings?.likes
                        topComment!!.createDate =
                            itemOfArticlesHugeCardData.topComments[0].createDate
                    } else
                        TopComments()
                } ?: run {
                    topComment =
                        TopComments()
                }
            } else
                topComment =
                    TopComments()


            var isUserFavorited = false
            itemOfArticlesHugeCardData.isUserFavorite?.let {
                isUserFavorited = itemOfArticlesHugeCardData.isUserFavorite
            } ?: run {
                isUserFavorited = false
            }

            var showInWebView = false
            itemOfArticlesHugeCardData.showInWebview?.let {
                showInWebView = itemOfArticlesHugeCardData.showInWebview
            } ?: run {
                showInWebView = false
            }

            var categoryName: String? = null
            var categoryId: String? = null
            if (itemOfArticlesHugeCardData.categories?.size != 0) {
                itemOfArticlesHugeCardData.categories?.get(0)?.let {
                    categoryName = itemOfArticlesHugeCardData.categories[0].name
                    categoryId = itemOfArticlesHugeCardData.categories[0].id
                } ?: run {
                    categoryName = ""
                    categoryId = ""
                }
            } else {
                categoryName = ""
                categoryId = ""
            }

            var redirectUrl: String? = null
            itemOfArticlesHugeCardData.redirectUrl?.let {
                redirectUrl = it
            } ?: run {
                redirectUrl = ""
            }


            feedList4Adapter.add(
                ArticlesFragmentAdapterModel(
                    "hugeCard",
                    mutableListOf(),
                    HugeCardModel(
                        articleId!!,
                        legacyId!!,
                        articleUrl!!,
                        categoryUrl!!,
                        badgeId,
                        coverPhotoText!!,
                        coverPhotoBadgeImage!!,
                        articleTitle!!,
                        articleDate!!,
                        emojiAction1!!,
                        emojiAction2!!,
                        emojiAction3!!,
                        emojiReactionCount!!.toString(),
                        commentCount!!.toString(),
                        topComment,
                        isUserFavorited,
                        categoryName!!,
                        categoryId!!,
                        showInWebView,
                        redirectUrl!!
                    )
                )
            )
        }
    }

    private fun prepareArticlesHugeCardData(articlesData: MutableList<FeedArticleModel>) {
        for (i in 0 until articlesData.size) {
            val itemOfArticlesHugeCardData = articlesData[i]

            var articleId: String? = null
            itemOfArticlesHugeCardData.id?.let {
                articleId = itemOfArticlesHugeCardData.id
            } ?: run {
                articleId = ""
            }

            var legacyId: Long? = null
            itemOfArticlesHugeCardData.legacyId?.let {
                legacyId = itemOfArticlesHugeCardData.legacyId
            } ?: run {
                legacyId = 0
            }

            var articleUrl: String? = null
            itemOfArticlesHugeCardData.image?.let {
                it.alternates?.let {
                    it.standardResolution?.let {
                        articleUrl = it.url
                    } ?: run {
                        articleUrl = " "
                    }
                } ?: run {
                    articleUrl = " "
                }
            } ?: run {
                articleUrl = " "
            }


            var categoryUrl: String? = null
            itemOfArticlesHugeCardData.categories?.let {
                if (it.size != 0) {
                    it[0].icons?.let {
                        categoryUrl = it.png
                    } ?: run {
                        categoryUrl = " "
                    }
                } else {
                    categoryUrl = " "
                }
            } ?: run {
                categoryUrl = " "
            }

            var coverPhotoText: String? = null
            itemOfArticlesHugeCardData.badge?.let {
                if (itemOfArticlesHugeCardData.badge.size != 0) {
                    itemOfArticlesHugeCardData.badge[0].name?.let {
                        coverPhotoText = it
                    } ?: run {
                        coverPhotoText = " "
                    }
                } else {
                    coverPhotoText = " "
                }
            } ?: run {
                coverPhotoText = " "
            }


            var coverPhotoBadgeImage: String? = null
            itemOfArticlesHugeCardData.badge?.let {
                if (itemOfArticlesHugeCardData.badge.size != 0) {
                    itemOfArticlesHugeCardData.badge[0].icons?.let {
                        coverPhotoBadgeImage = it.png
                    } ?: run {
                        coverPhotoBadgeImage = " "
                    }
                } else
                    coverPhotoBadgeImage = " "
            } ?: run {
                coverPhotoBadgeImage = " "
            }

            var badgeId: String? = null
            itemOfArticlesHugeCardData.badge?.let {
                if (it.size != 0) {
                    it[0].id?.let {
                        badgeId = it
                    } ?: run {
                        badgeId = ""
                    }
                } else {
                    badgeId = ""
                }
            } ?: run {
                badgeId = ""
            }


            var articleTitle: String? = null
            itemOfArticlesHugeCardData.title?.let {
                articleTitle = it
            } ?: run {
                articleTitle = " "
            }


            var articleDate: String? = null
            itemOfArticlesHugeCardData.createDate?.let {
                articleDate = OnedioCommon.convertFeedDate(it)
            } ?: run {
                articleDate = " "
            }

            var emojiAction1: String? = null
            itemOfArticlesHugeCardData.reactions?.let {
                if (it.size != 0) {
                    it[0].icons?.let {
                        emojiAction1 = it.png
                    } ?: run {
                        emojiAction1 = " "
                    }
                } else {
                    emojiAction1 = " "
                }
            } ?: run {
                emojiAction1 = " "
            }

            var emojiAction2: String? = null
            itemOfArticlesHugeCardData.reactions?.let {
                if (it.size != 0) {
                    it[1].icons?.let {
                        emojiAction2 = it.png
                    } ?: run {
                        emojiAction2 = " "
                    }
                } else {
                    emojiAction2 = " "
                }
            } ?: run {
                emojiAction2 = " "
            }

            var emojiAction3: String? = null
            itemOfArticlesHugeCardData.reactions?.let {
                if (it.size != 0) {
                    it[2].icons?.let {
                        emojiAction3 = it.png
                    } ?: run {
                        emojiAction3 = " "
                    }
                } else {
                    emojiAction3 = " "
                }
            } ?: run {
                emojiAction3 = " "
            }

            var emojiReactionCount: Int? = null
            itemOfArticlesHugeCardData.stats?.let {
                it.reactions?.let {
                    emojiReactionCount = it
                } ?: run {
                    emojiReactionCount = 0
                }
            } ?: run {
                emojiReactionCount = 0
            }


            var commentCount: Int? = null
            itemOfArticlesHugeCardData.stats?.let {
                it.comments?.let {
                    commentCount = it
                } ?: run {

                }
            } ?: run {
                commentCount = 0
            }


            var topComment: TopComments? = null
            if (itemOfArticlesHugeCardData.topComments?.size != 0) {
                itemOfArticlesHugeCardData.topComments?.get(0)?.let {
                    if (itemOfArticlesHugeCardData.topComments.size != 0) {
                        topComment =
                            TopComments()
                        topComment!!.user =
                            UserComment()
                        topComment!!.id = itemOfArticlesHugeCardData.topComments[0].id
                        topComment!!.user?.id = itemOfArticlesHugeCardData.topComments[0].user?.id
                        topComment!!.user?.userName =
                            itemOfArticlesHugeCardData.topComments[0].user?.username
                        topComment!!.user?.avatar =
                            itemOfArticlesHugeCardData.topComments[0].user?.image?.alternates?.standardResolution?.url
                        topComment!!.text = itemOfArticlesHugeCardData.topComments[0].text
                        topComment!!.likes =
                            itemOfArticlesHugeCardData.topComments[0].ratings?.likes
                        topComment!!.createDate =
                            itemOfArticlesHugeCardData.topComments[0].createDate
                    } else
                        TopComments()
                } ?: run {
                    topComment =
                        TopComments()
                }
            } else
                topComment =
                    TopComments()


            var isUserFavorited = false
            itemOfArticlesHugeCardData.isUserFavorite?.let {
                isUserFavorited = itemOfArticlesHugeCardData.isUserFavorite
            } ?: run {
                isUserFavorited = false
            }

            var showInWebView = false
            itemOfArticlesHugeCardData.showInWebview?.let {
                showInWebView = itemOfArticlesHugeCardData.showInWebview
            } ?: run {
                showInWebView = false
            }

            var categoryName: String? = null
            var categoryId: String? = null
            if (itemOfArticlesHugeCardData.categories?.size != 0) {
                itemOfArticlesHugeCardData.categories?.get(0)?.let {
                    categoryName = itemOfArticlesHugeCardData.categories[0].name
                    categoryId = itemOfArticlesHugeCardData.categories[0].id
                } ?: run {
                    categoryName = ""
                    categoryId = ""
                }
            } else {
                categoryName = ""
                categoryId = ""
            }

            var redirectUrl: String? = null
            itemOfArticlesHugeCardData.redirectUrl?.let {
                redirectUrl = it
            } ?: run {
                redirectUrl = ""
            }


            allArticlesList.add(
                HugeCardModel(
                    articleId!!,
                    legacyId!!,
                    articleUrl!!,
                    categoryUrl!!,
                    badgeId,
                    coverPhotoText!!,
                    coverPhotoBadgeImage!!,
                    articleTitle!!,
                    articleDate!!,
                    emojiAction1!!,
                    emojiAction2!!,
                    emojiAction3!!,
                    emojiReactionCount!!.toString(),
                    commentCount!!.toString(),
                    topComment,
                    isUserFavorited,
                    categoryName!!,
                    categoryId!!,
                    showInWebView,
                    redirectUrl!!
                )
            )
        }
    }

    private fun prepareSecondSliderData(response4ArticlesFeed: Response4ArticlesFeed) {
        var articlesData = response4ArticlesFeed.data?.feed

        for (i in 0 until articlesData?.size!!) {

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

            allArticlesList.add(
                HugeCardModel(
                    sArticleId!!,
                    sLegacyId!!,
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
                    isUserFavorite!!,
                    categoryName,
                    categoryId,
                    sShowInWebView!!,
                    redirectUrl!!
                )
            )
        }
    }

    //==================================================================================================================
    /**
     * Fill Slider - 1 Data 4 All List
     */
    //==================================================================================================================
    private fun fillSliderData2AllArticleList(
        sIndex: Int,
        eIndex: Int,
        arrOfSliderData: MutableList<FeedArticleModel>
    ) {

        val sizeOfSliderData = calcSliderArrSize(eIndex)

        for (i in sIndex until sizeOfSliderData) {
            var sCategoryId: String? = null
            var sCategoryName: String? = null
            arrOfSliderData[i].categories?.let {
                if (arrOfSliderData[i].categories?.size != 0) {
                    sCategoryId = it[0].id
                    sCategoryName = it[0].name
                } else {
                    sCategoryId = ""
                    sCategoryName = ""
                }
            } ?: run {
                sCategoryId = ""
                sCategoryName = ""
            }

            var redirectUrl: String? = null
            arrOfSliderData[i].redirectUrl?.let {
                redirectUrl = it
            } ?: run {
                redirectUrl = ""
            }

            var sLegacyId: Long? = null
            arrOfSliderData[i].legacyId?.let {
                sLegacyId = it
            } ?: run {
                sLegacyId = 0
            }

            var sArticleId: String? = null
            arrOfSliderData[i].id?.let {
                sArticleId = it
            } ?: run {
                sArticleId = ""
            }

            var sImage: String? = null
            arrOfSliderData[i].image?.let {
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
            arrOfSliderData[i].title?.let {
                sTitle = it
            } ?: run {
                sTitle = ""
            }

            var sShowInWebView: Boolean? = null
            arrOfSliderData[i].showInWebview?.let {
                sShowInWebView = it
            } ?: run {
                sShowInWebView = false
            }

            var isUserFavorite: Boolean? = null
            arrOfSliderData[i].isUserFavorite?.let {
                isUserFavorite = it
            } ?: run {
                isUserFavorite = false
            }

            allArticlesList.add(
                HugeCardModel(
                    sArticleId!!,
                    sLegacyId!!,
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
                    isUserFavorite!!,
                    sCategoryName,
                    sCategoryId,
                    sShowInWebView!!,
                    redirectUrl!!
                )
            )

        }
    }

    //==================================================================================================================
    /**
     * Calc Slider List Size..
     */
    //==================================================================================================================
    private fun calcSliderArrSize(sizeOfSliderData: Int): Int = if (sizeOfSliderData < 8) {
        8
    } else
        sizeOfSliderData

    //==================================================================================================================
    /**
     * OnedioButtonData
     */
    //==================================================================================================================
    private fun getOnedioButtonData() {
        articlesFragmentViewPresenterImpl.getArticleFeedCategoryWidgetDataWidthTag4OnedioButton(
            "",
            "5ec7b134c5390705202fcd8b",
            1,
            15
        )
    }

    //==================================================================================================================
    /**
     * Handle Onedio Button Data..
     */
    //==================================================================================================================
    override fun handleOnedioButtonData(
        wName: String,
        response4ArticlesFeed: Response4ArticlesFeed
    ) {
        StringSharedPrefs(
            sharedPrefs,
            OnedioConstant.SHARED_PREF_ONEDIO_BUTTON,
            ""
        ).setValue(Gson().toJson(response4ArticlesFeed))
    }

    //==================================================================================================================
    /**
     * Sum Widget Items Count Data..
     */
    //==================================================================================================================
    private fun sumOfWidgetCount(widgets: MutableList<WidgetsData>): Int {
        return widgets.sumBy {
            if (it.type == "feed") {
                it.count?.let { count ->
                    count
                } ?: run {
                    0
                }
            } else
                0
        }
    }

    //==================================================================================================================
    /**
     * Show Error
     */
    //==================================================================================================================
    override fun showError(response4ErrorObj: Response4ErrorObj, isContinue: Boolean) {
        if (!isContinue) {
            showEzDialogMessage(
                "Uyarı..!",
                response4ErrorObj.status?.message!!,
                "Tamam",
                ContextCompat.getColor(
                    mActivity?.applicationContext!!,
                    R.color.tabIndicatorColor4Proile
                ),
                ContextCompat.getColor(mActivity?.applicationContext!!, R.color.constWhite),
                ContextCompat.getColor(mActivity?.applicationContext!!, R.color.constTextColor),
                ContextCompat.getColor(mActivity?.applicationContext!!, R.color.constTextColor)
            )
        } else {
            getArticlesFeed(
                page,
                sumOfWidgetCount(widgetsData)
            )
        }
    }

    private fun showEzDialogMessage(
        titleText: String,
        messageText: String,
        buttonText: String,
        headerColor: Int,
        titleTextColor: Int,
        messageTextColor: Int,
        buttonTextColor: Int
    ) {
        val ezDialogMessage =
            OnedioEzDialogMessageModel4Fragment(
                mActivity!!,
                titleText,
                messageText,
                buttonText,
                headerColor,
                titleTextColor,
                messageTextColor,
                buttonTextColor
            )

        OnedioCommon.showEzDialog4Fragment(ezDialogMessage) {
            mActivity?.finishAffinity()
            exitProcess(0)
        }
    }

    //==================================================================================================================
    /**
     * OnCreatedView Method. Running after onCreateView
     */
    //==================================================================================================================
    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is Activity) {
            mActivity = context
        }
    }

}