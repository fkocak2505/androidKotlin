package com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.optimizedArticleDetail

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.http.SslError
import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.SslErrorHandler
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import cn.jzvd.JzvdStd
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.gson.Gson
import com.onedio.androidside.BuildConfig
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.common.OnedioCustomIntent
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.categoryFragment.categoryModel.CategoryModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleComment.response.Response4LikeAndUnLike
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.ArticleFeedDetailModelImpl
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.newResponse.ArticleFeedDetailsEntryModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.newResponse.ArticleFeedDetailsModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.newResponse.Response4ArticleFeedDetails
import com.onedio.androidside.mvp.model.dashboard.homeFragment.commentModel.CommentModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.addFavorite.Response4AddFavoriteArticle
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel.Response4ArticlesFeed
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.sliderModel.FeedArticleModel
import com.onedio.androidside.mvp.model.welcomeDashboard.search.dataModel.tags.Tags
import com.onedio.androidside.mvp.presenter.dashboard.homeFragment.articleDetail.ArticleDetailActivirtyPresenterImpl
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.CategoryFeedActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.food.FoodActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.video.VideoActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.IArticleDetailActivityView
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.TestArticleDetailActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.optimizedArticleDetail.adapter.ArticleDetailRecyclerViewAdapter
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.optimizedArticleDetail.layutManager.PreCachingLayoutManager
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.optimizedArticleDetail.model.ArticleDetailAdapterModel
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.optimizedArticleDetail.utils.DeviceUtils
import com.onedio.androidside.mvp.view.dashboard.homeFragment.profile.ProfileActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.widgets.hugeCardRecyclerViewAdapter.model.HugeCardModel
import com.onedio.androidside.mvp.view.dashboard.search.moreTag.tagsArticle.TagsArticleActivityViewImpl
import com.onedio.androidside.mvp.view.loginAndRegister.LoginAndRegisterDashboardActivityViewImpl
import com.onedio.androidside.prefs.StringSharedPrefs
import com.onedio.androidside.singleton.OnedioSingletonPattern
import com.yandex.metrica.YandexMetrica
import kotlinx.android.synthetic.main.activity_article_detail_with_recyclerview.*

class ArticleDetailWithRecyclerView : AppCompatActivity(), IArticleDetailActivityView {

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    private lateinit var toolBar: Toolbar

    private var arrOfArticleDetailData: MutableList<ArticleDetailAdapterModel> = mutableListOf()

    private lateinit var articleDetailActivirtyPresenterImpl: ArticleDetailActivirtyPresenterImpl

    private lateinit var sharedPrefs: SharedPreferences

    private var isScreenLoadMultiple: Boolean = false

    private lateinit var articleItem: HugeCardModel

    private var legacyId: Long? = null
    private lateinit var articleId: String

    private var isAddedFavorite: Boolean = false

    private var recommendedArticleFeed: FeedArticleModel? = null

    private var listOfLikedCommentId: MutableList<String> = mutableListOf()

    private var isReqRecommendWidget: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail_with_recyclerview)

        sharedPrefs =
            getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)


        savedInstanceState?.let {
            isScreenLoadMultiple = true
        } ?: run {
            isScreenLoadMultiple = false
        }

        initArticleDetailActivityComponent()

        /*prepareArticleData()

        initRecycler()*/

    }

    override fun initArticleDetailActivityComponent() {
        intent.getStringExtra("ARTICLE_DETAIL_DATA")?.let {

            prepareToolbar()

            articleItem =
                Gson().fromJson(it, HugeCardModel::class.java)

            legacyId = articleItem.legacyId

            if (!::articleDetailActivirtyPresenterImpl.isInitialized)
                articleDetailActivirtyPresenterImpl =
                    ArticleDetailActivirtyPresenterImpl(
                        ArticleFeedDetailModelImpl(),
                        this
                    )

            getRecommendWidgetData(legacyId!!, false)

        } ?: run {
            onBackPressed()
        }
    }

    private fun prepareToolbar() {
        toolBar = toolBar4ArticleDetail as Toolbar
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)


        toolBar.setOnClickListener {
            articleDetailRecyclerView.smoothScrollToPosition(0)
        }
    }

    override fun getRecommendWidgetData(legaycId: Long, isTaboola: Boolean) {
        articleDetailActivirtyPresenterImpl.getRecommendWidgetArticle(
            "50187b5d295c043264000144",
            1,
            10,
            "popular",
            "1day",
            isTaboola,
            legacyId!!
        )
    }

    override fun handleRecommendWidgetData(
        response4ArticlesFeed: Response4ArticlesFeed,
        isTaboola: Boolean,
        legacyId: Long
    ) {

        response4ArticlesFeed.data?.let {
            it.feed?.let { arrOfRecommendArticle ->
                recommendedArticleFeed =
                    arrOfRecommendArticle[(0 until arrOfRecommendArticle.size).random()]
            } ?: run {
                recommendedArticleFeed = null
            }
        } ?: run {
            recommendedArticleFeed = null
        }


        getArticleFeedDetail(legacyId, isTaboola)
    }

    override fun getArticleFeedDetail(legacyId: Long, isTaboola: Boolean) {
        articleDetailActivirtyPresenterImpl.getArticleFeedDetailNew(legacyId, isTaboola)
    }

    override fun handleArticleFeedDetailDataNew(
        response4ArticleFeedDetails: Response4ArticleFeedDetails,
        isTaboola: Boolean
    ) {
        val articleDetailResponse = response4ArticleFeedDetails.data!!

        var showInWebView: Boolean? = null
        articleDetailResponse.showInWebview?.let {
            showInWebView = it
        } ?: run {
            showInWebView = false
        }

        if (isTaboola) {
            if (showInWebView!!) {
                goTestArticleDetailActivity(articleDetailResponse)
            } else {
                var id: String? = null
                articleDetailResponse.id?.let {
                    id = it
                } ?: run {
                    id = ""
                }

                var legacyId: Long? = null
                articleDetailResponse.legacyId?.let {
                    legacyId = it
                } ?: run {
                    legacyId = 0
                }


                var categoryName = ""
                var categoryId = ""

                if (articleDetailResponse.categories?.size != 0) {
                    articleDetailResponse.categories?.let { it1 ->
                        categoryName = it1[0].name!!
                        categoryId = it1[0].id!!
                    } ?: run {
                        categoryName = ""
                        categoryId = ""
                    }
                } else {
                    categoryName = ""
                    categoryId = ""
                }

                goArticleDetailActivity(id!!, legacyId!!, categoryName, categoryId)
            }
        } else {

            articleId = articleDetailResponse.id!!
            val titleOfArticle = articleDetailResponse.title
            isAddedFavorite = articleDetailResponse.isUserFavorite!!

            invalidateOptionsMenu()

            sendFBAnalyticsData(articleDetailResponse)

            OnedioCommon.sendLog2Crashlytics(legacyId.toString() + " --- " + titleOfArticle)

            if (!isScreenLoadMultiple)
                loadGAWebView()

            prepareArticleData(response4ArticleFeedDetails)

            initRecycler()

        }
    }

    private fun sendFBAnalyticsData(articleDetailResponse: ArticleFeedDetailsModel) {

        var categoryName: String? = null
        articleDetailResponse.categories?.let {
            if (it.size != 0) {
                it[0].name?.let {
                    categoryName = it
                } ?: run {
                    categoryName = ""
                }
            } else
                categoryName = ""
        } ?: run {
            categoryName = ""
        }

        var title: String? = null
        articleDetailResponse.title?.let {
            title = it
        } ?: run {
            title = ""
        }

        var legacyId: Long? = null
        articleDetailResponse.legacyId?.let {
            legacyId = it
        } ?: run {
            legacyId = 0
        }

        var contentDate: String? = null
        articleDetailResponse.createDate?.let {
            contentDate = OnedioCommon.convertFeedDate2(it)
        } ?: run {
            contentDate = ""
        }

        var contentEditor: String? = null
        articleDetailResponse.authors?.let {
            if (it.size != 0) {
                it[0].name?.let {
                    contentEditor = it
                } ?: run {
                    contentEditor = ""
                }
            }
        } ?: run {
            contentEditor = ""
        }

        firebaseAnalytics = FirebaseAnalytics.getInstance(this)
        firebaseAnalytics.setCurrentScreen(
            this,
            title,
            "ArticleDetailActivityViewImpl"
        )

        val params = Bundle()
        params.putString("page_type", "Haber Detay")
        params.putString("content_category1", categoryName)
        params.putString("content_name", title)
        params.putString("content_id", legacyId.toString())
        params.putString("content_date", contentDate)
        params.putString("content_editor", contentEditor)
        params.putString("page_name", title)

        firebaseAnalytics.logEvent("view_screen", params)

        val mapOfFeedAppMetrica = HashMap<String, Any>()
        mapOfFeedAppMetrica["page_type"] = "Haber Detay"
        mapOfFeedAppMetrica["content_category1"] = categoryName.toString()
        mapOfFeedAppMetrica["content_name"] = title.toString()
        mapOfFeedAppMetrica["content_id"] = legacyId.toString()
        mapOfFeedAppMetrica["content_date"] = contentDate.toString()
        mapOfFeedAppMetrica["content_editor"] = contentEditor.toString()
        mapOfFeedAppMetrica["page_name"] = title.toString()

        YandexMetrica.reportEvent("view_screen", mapOfFeedAppMetrica)

    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun loadGAWebView() {
        val gaUrl =
            BuildConfig.BASE_URL + "/v3/1/1/1/articles/$legacyId/analytics?platform=android"

        OnedioSingletonPattern.instance.getWebView(applicationContext, gaUrl).webViewClient =
            object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                }

                override fun onReceivedSslError(
                    view: WebView?,
                    handler: SslErrorHandler?,
                    error: SslError?
                ) {
                    handler?.let {
                        handler.cancel()
                    }
                    super.onReceivedSslError(view, handler, error)
                }
            }
    }

    private fun goTestArticleDetailActivity(articleFeedDetailResponse: ArticleFeedDetailsModel) {
        var id: String? = null
        articleFeedDetailResponse.id?.let {
            id = it
        } ?: run {
            id = ""
        }

        var legacyId: Long? = null
        articleFeedDetailResponse.legacyId?.let {
            legacyId = it
        } ?: run {
            legacyId = 0
        }


        var categoryName = ""
        var categoryId = ""

        if (articleFeedDetailResponse.categories?.size != 0) {
            articleFeedDetailResponse.categories?.let { it1 ->
                categoryName = it1[0].name!!
                categoryId = it1[0].id!!
            } ?: run {
                categoryName = ""
                categoryId = ""
            }
        } else {
            categoryName = ""
            categoryId = ""
        }

        var isUserFavorited: Boolean? = null
        articleFeedDetailResponse?.isUserFavorite?.let {
            isUserFavorited = it
        } ?: run {
            isUserFavorited = false
        }

        val articleItem =
            HugeCardModel(
                id!!,
                legacyId!!,
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
                isUserFavorited!!,
                categoryName,
                categoryId,
                false,
                ""
            )

        val intent =
            Intent(
                applicationContext,
                TestArticleDetailActivityViewImpl::class.java
            )
        intent.putExtra(
            "ARTICLE_DETAIL_DATA",
            Gson().toJson(articleItem)
        )
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    private fun goArticleDetailActivity(
        id: String,
        legacyId: Long,
        categoryName: String,
        categoryId: String
    ) {
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
            ArticleDetailWithRecyclerView::class.java
        )

        val intent =
            Intent(applicationContext, ArticleDetailWithRecyclerView::class.java)
        intent.putExtra("ARTICLE_DETAIL_DATA", Gson().toJson(articleItem))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)

        startAnim()
    }

    //==============================================================================================
    /**
     * Init Recycler..
     */
    //==============================================================================================
    private fun initRecycler() {

        val mLayoutManager =
            PreCachingLayoutManager(
                this@ArticleDetailWithRecyclerView,
                RecyclerView.VERTICAL,
                false
            )
        mLayoutManager.setExtraLayoutSpace(DeviceUtils.getScreenHeight(applicationContext))

        articleDetailRecyclerView.apply {
            layoutManager = mLayoutManager
            setItemViewCacheSize(5)
            adapter = ArticleDetailRecyclerViewAdapter(
                this@ArticleDetailWithRecyclerView,
                arrOfArticleDetailData,
                articleDetailActivirtyPresenterImpl
            ) { position, itemOfArticlePart, viewType ->
                handleClickViews(position, itemOfArticlePart, viewType)
            }
        }

        articleDetailRecyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
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
        })
    }

    //==============================================================================================
    /**
     * Handle RecyclerView Item Click..
     */
    //==============================================================================================
    private fun handleClickViews(
        position: Int,
        itemOfArticlePart: ArticleDetailAdapterModel,
        viewType: String
    ) {
        when (viewType) {
            "BADGE" -> {
                clickBadgeView(itemOfArticlePart)
            }
            "CATEGORY" -> {
                clickCategoryView(itemOfArticlePart)
            }
            "PROFILE" -> {
                clickUserProfilePhoto(itemOfArticlePart)
            }
            "RECOMEND_WIDGET" -> {
                clickRecommendWidget(itemOfArticlePart.widgetData!!)
            }
        }
    }

    //==============================================================================================
    /**
     * Click Header Badge..
     */
    //==============================================================================================
    private fun clickBadgeView(itemOfArticlePart: ArticleDetailAdapterModel) {
        val itemOfTag =
            Tags()
        itemOfArticlePart.data.data?.let {
            it.badge?.let {
                if (it.size != 0) {
                    it[0].id?.let {
                        itemOfTag.id = it
                    } ?: run {
                        itemOfTag.id = ""
                    }

                    it[0].name?.let {
                        itemOfTag.name = it
                    } ?: run {
                        itemOfTag.name = ""
                    }
                } else {
                    itemOfTag.id = ""
                    itemOfTag.name = ""
                }
            } ?: run {
                itemOfTag.id = ""
                itemOfTag.name = ""
            }
        } ?: run {
            itemOfTag.id = ""
            itemOfTag.name = ""
        }

        val intent =
            Intent(applicationContext, TagsArticleActivityViewImpl::class.java)
        intent.putExtra("TAGS_ARTICLE", Gson().toJson(itemOfTag))
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        startAnim()
    }

    //==============================================================================================
    /**
     * Click Header Category..
     */
    //==============================================================================================
    private fun clickCategoryView(itemOfArticlePart: ArticleDetailAdapterModel) {
        var categoryId = ""
        var categoryName = ""
        itemOfArticlePart.data.data?.let {
            it.categories?.let {
                if (it.size != 0) {
                    it[0].id?.let {
                        categoryId = it
                    } ?: run {
                        categoryId = ""
                    }

                    it[0].name?.let {
                        categoryName = it
                    } ?: run {
                        categoryName = ""
                    }
                } else {
                    categoryId = ""
                    categoryName = ""
                }
            } ?: run {
                categoryId = ""
                categoryName = ""
            }
        } ?: run {
            categoryId = ""
            categoryName = ""
        }

        val categoryModel =
            CategoryModel(
                categoryId,
                null,
                categoryName
            )

        when (categoryId) {
            OnedioConstant.CATEGORIES_YEMEK_ID -> {
                OnedioSingletonPattern.instance.setActivity(
                    ArticleDetailWithRecyclerView::class.java
                )
                val intent =
                    Intent(
                        applicationContext!!,
                        FoodActivityViewImpl::class.java
                    )
                intent.putExtra(
                    "ARTICLE_FOOD_DATA",
                    Gson().toJson(categoryModel)
                )
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)

                startAnim()
            }
            OnedioConstant.CATEGORIES_VIDEO_ID -> {
                OnedioSingletonPattern.instance.setActivity(
                    ArticleDetailWithRecyclerView::class.java
                )
                val intent =
                    Intent(
                        applicationContext!!,
                        VideoActivityViewImpl::class.java
                    )
                intent.putExtra(
                    "ARTICLE_VIDEO_DATA",
                    Gson().toJson(categoryModel)
                )
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)

                startAnim()
            }
            else -> {
                OnedioSingletonPattern.instance.setActivity(
                    ArticleDetailWithRecyclerView::class.java
                )

                val intent =
                    Intent(
                        applicationContext!!,
                        CategoryFeedActivityViewImpl::class.java
                    )
                intent.putExtra(
                    "ARTICLE_CATEGORY_DATA",
                    Gson().toJson(categoryModel)
                )
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                startAnim()
            }
        }

    }

    //==============================================================================================
    /**
     * Click Editor Profile..
     */
    //==============================================================================================
    private fun clickUserProfilePhoto(itemOfArticlePart: ArticleDetailAdapterModel) {
        itemOfArticlePart.data.data?.let {
            it.authors?.let {
                if (it.size != 0) {
                    val anotherUserId = it[0].id
                    val intent = Intent(applicationContext, ProfileActivityViewImpl::class.java)
                    intent.putExtra("IS_USER_OWN_PROFILE", false)
                    intent.putExtra("ANOTHER_USER_PROFILE", anotherUserId)
                    startActivity(intent)
                    startAnim()
                }
            }
        }
    }

    //==============================================================================================
    /**
     * Click Recommend Widget..
     */
    //==============================================================================================
    private fun clickRecommendWidget(itemOfArticlePart: FeedArticleModel) {
        val params = Bundle()
        firebaseAnalytics.logEvent("in_article_recommendation_click", params)

        val mapOfFeedAppMetrica = HashMap<String, Any>()
        YandexMetrica.reportEvent("in_article_recommendation_click", mapOfFeedAppMetrica)

        var id = ""
        itemOfArticlePart.id?.let {
            id = it
        }


        var legacyId: Long = 0
        itemOfArticlePart.legacyId?.let {
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
            ArticleDetailWithRecyclerView::class.java
        )

        val intent =
            Intent(applicationContext, ArticleDetailWithRecyclerView::class.java)
        intent.putExtra("ARTICLE_DETAIL_DATA", Gson().toJson(articleItem))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)

        startAnim()
    }

    //==============================================================================================
    /**
     * Start Intent Anim..
     */
    //==============================================================================================
    private fun startAnim() {
        OnedioCustomIntent.startAnim(this, "l2r")
    }

    //==============================================================================================
    /**
     * Prepare Article Data Array 4 RecyclerView Adapter..
     */
    //==============================================================================================
    private fun prepareArticleData(response4ArticleFeedDetails: Response4ArticleFeedDetails): MutableList<ArticleDetailAdapterModel> {
        arrOfArticleDetailData.add(
            ArticleDetailAdapterModel(
                "googleAdsTop",
                response4ArticleFeedDetails,
                null,
                false
            )
        )

        arrOfArticleDetailData.add(ArticleDetailAdapterModel("header", response4ArticleFeedDetails))

        if (response4ArticleFeedDetails.data?.entries?.size!! >= 3)
            arrOfArticleDetailData.add(
                ArticleDetailAdapterModel(
                    "entry",
                    response4ArticleFeedDetails,
                    recommendedArticleFeed
                )
            )
        else
            arrOfArticleDetailData.add(
                ArticleDetailAdapterModel(
                    "entry",
                    response4ArticleFeedDetails
                )
            )

        arrOfArticleDetailData.add(
            ArticleDetailAdapterModel(
                "googleAdsBottom",
                response4ArticleFeedDetails,
                null,
                false
            )
        )
        arrOfArticleDetailData.add(
            ArticleDetailAdapterModel(
                "emoji",
                response4ArticleFeedDetails
            )
        )
        arrOfArticleDetailData.add(
            ArticleDetailAdapterModel(
                "comment",
                response4ArticleFeedDetails
            )
        )

        arrOfArticleDetailData.add(
            ArticleDetailAdapterModel(
                "taboola",
                response4ArticleFeedDetails
            )
        )

        if (response4ArticleFeedDetails.data.entries.size < 3) {
            arrOfArticleDetailData.add(
                6,
                ArticleDetailAdapterModel(
                    "recommendWidget",
                    response4ArticleFeedDetails,
                    recommendedArticleFeed
                )
            )
        }

        return arrOfArticleDetailData
    }

    override fun setHeadOfArticleDetail(articleFeedDetailsModel: ArticleFeedDetailsModel) {
        TODO("Not yet implemented")
    }

    override fun loadImageWithPicasso(
        imageUrl: String,
        imageView: ImageView,
        progressBar: ProgressBar
    ) {
        TODO("Not yet implemented")
    }

    override fun setEntriesData(arrOfEntriesData: MutableList<ArticleFeedDetailsEntryModel>) {
        TODO("Not yet implemented")
    }

    override fun handleEmojiUpReactionNew(
        response4LikeAndUnLike: Response4LikeAndUnLike,
        legacyId: Long,
        emojiCode: String,
        type: String,
        view: View,
        firstHeight: Int,
        emojiItemCount: Int,
        tvEmojiItemCount: TextView
    ) {
        val justType = type.split("$")
        val typeString = justType[0]
        val statusSharedPrefString = justType[1].toInt()


        val lp = view.layoutParams as ConstraintLayout.LayoutParams
        when (typeString) {
            OnedioConstant.UP_REACTION -> {
                lp.height = (firstHeight + getModEmojiReactionCount(emojiItemCount)) + 30
                view.layoutParams = lp
                view.background = ContextCompat.getDrawable(
                    applicationContext,
                    R.drawable.article_detail_emoji_status_line_layout_bg_selected
                )

                var newCount = tvEmojiItemCount.text.toString().toInt()
                newCount++
                tvEmojiItemCount.text = newCount.toString()


            }
            OnedioConstant.DOWN_REACTION -> {
                lp.height = firstHeight + (getModEmojiReactionCount(emojiItemCount))
                view.layoutParams = lp
                view.background = ContextCompat.getDrawable(
                    applicationContext,
                    R.drawable.article_detail_emoji_status_line_layout_bg
                )

                var newCount = tvEmojiItemCount.text.toString().toInt()
                newCount--
                tvEmojiItemCount.text = newCount.toString()
            }
        }

        when (statusSharedPrefString) {
            1 -> {
                val sEmojiClickedData = StringSharedPrefs(
                    sharedPrefs,
                    OnedioConstant.EMOJI_REACTION_CLICKED_EMOJI_CODES,
                    ""
                ).getValue<String>()

                val mEmojiClickedData = OnedioCommon.convertString2Map(sEmojiClickedData)
                mEmojiClickedData[legacyId.toString()]?.remove("")
                mEmojiClickedData[legacyId.toString()]?.remove(emojiCode)

                val newSEmojiClickedData = OnedioCommon.convertMap2String(mEmojiClickedData)
                StringSharedPrefs(
                    sharedPrefs,
                    OnedioConstant.EMOJI_REACTION_CLICKED_EMOJI_CODES,
                    ""
                ).setValue(newSEmojiClickedData)

            }
            0 -> {
                val sEmojiClickedData = StringSharedPrefs(
                    sharedPrefs,
                    OnedioConstant.EMOJI_REACTION_CLICKED_EMOJI_CODES,
                    ""
                ).getValue<String>()

                val mEmojiClickedData = OnedioCommon.convertString2Map(sEmojiClickedData)
                mEmojiClickedData[legacyId.toString()]?.remove("")
                mEmojiClickedData[legacyId.toString()]?.add(emojiCode)

                val newSEmojiClickedData = OnedioCommon.convertMap2String(mEmojiClickedData)
                StringSharedPrefs(
                    sharedPrefs,
                    OnedioConstant.EMOJI_REACTION_CLICKED_EMOJI_CODES,
                    ""
                ).setValue(newSEmojiClickedData)

            }
        }
    }

    private fun getModEmojiReactionCount(count: Int): Int {
        return when (count) {
            0 -> 0
            in 0..1000 -> 5
            in 1000..3000 -> 10
            else -> 10 + (count / 1000) * 2
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        JzvdStd.releaseAllVideos()
        //taboolaWidget.reset()
        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        //menuItem = menu.findItem(R.id.actionFavorite)
        if (isAddedFavorite)
            menu.removeItem(R.id.actionAddFavorite)
        else
            menu.removeItem(R.id.actionDeleteFavorite)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        super.onPrepareOptionsMenu(menu)
        //menuItem.setIcon(R.drawable.ic_back)

        if (isAddedFavorite)
            menu.findItem(R.id.actionDeleteFavorite).setIcon(R.drawable.ic_added_favorite)
        else
            menu.findItem(R.id.actionAddFavorite).setIcon(R.drawable.ic_delete_favorited)

        return true
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK) {
            JzvdStd.releaseAllVideos()
            //taboolaWidget.reset()
            onBackPressed()
            false
        } else super.onKeyDown(keyCode, event)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                JzvdStd.releaseAllVideos()
                onBackPressed()
            }
            R.id.actionAddFavorite -> {

                if (OnedioCommon.isUserLogin()) {
                    articleDetailActivirtyPresenterImpl.addFavorite(
                        legacyId!!
                    )
                } else {
                    startActivity(
                        Intent(
                            applicationContext,
                            LoginAndRegisterDashboardActivityViewImpl::class.java
                        )
                    )
                }
            }
            R.id.actionDeleteFavorite -> {
                if (OnedioCommon.isUserLogin()) {
                    //if (articleItem.isUserFavorited)
                    if (isAddedFavorite)
                        articleDetailActivirtyPresenterImpl.deleteFavorite(
                            legacyId!!
                        )
                } else {
                    startActivity(
                        Intent(
                            applicationContext,
                            LoginAndRegisterDashboardActivityViewImpl::class.java
                        )
                    )
                }
            }
            R.id.actionShare -> {
                OnedioCommon.shareArticle(this@ArticleDetailWithRecyclerView, legacyId!!)
            }
        }
        return true
    }

    override fun handleAddFavoriteDataModel(response4AddFavoriteArticle: Response4AddFavoriteArticle) {
        TODO("Not yet implemented")
    }

    override fun handleDeleteFavoriteDataModel(response4AddFavoriteArticle: Response4AddFavoriteArticle) {
        TODO("Not yet implemented")
    }

    override fun handleLikeComment(
        response4LikeAndUnLike: Response4LikeAndUnLike,
        itemOfComment: CommentModel,
        likeCountView: TextView
    ) {
        var cl = likeCountView.text.toString().toInt()
        cl++
        likeCountView.text = cl.toString()
        itemOfComment.commentLikeCount = cl.toString()
        Toast.makeText(applicationContext, "Beğenildi", Toast.LENGTH_SHORT).show()

        listOfLikedCommentId = OnedioCommon.convertString2ListStr(
            StringSharedPrefs(
                sharedPrefs,
                OnedioConstant.SHARED_PREF_LIKE_COMMENT_DATA,
                OnedioCommon.convertList2StringStr(
                    listOfLikedCommentId
                )
            ).getValue()
        )

        listOfLikedCommentId.add(itemOfComment.commentId)

        StringSharedPrefs(
            sharedPrefs,
            OnedioConstant.SHARED_PREF_LIKE_COMMENT_DATA,
            " "
        ).setValue(OnedioCommon.convertList2StringStr(listOfLikedCommentId))

    }

    override fun handleUnlikeComment(
        response4LikeAndUnLike: Response4LikeAndUnLike,
        itemOfComment: CommentModel,
        likeCountView: TextView
    ) {
        var cl = likeCountView.text.toString().toInt()
        cl--
        likeCountView.text = cl.toString()
        itemOfComment.commentLikeCount = cl.toString()
        Toast.makeText(applicationContext, "Beğeni geri alındı", Toast.LENGTH_SHORT).show()

        listOfLikedCommentId = OnedioCommon.convertString2ListStr(
            StringSharedPrefs(
                sharedPrefs,
                OnedioConstant.SHARED_PREF_LIKE_COMMENT_DATA,
                OnedioCommon.convertList2StringStr(
                    listOfLikedCommentId
                )
            ).getValue()
        )

        listOfLikedCommentId.remove(itemOfComment.commentId)

        StringSharedPrefs(
            sharedPrefs,
            OnedioConstant.SHARED_PREF_LIKE_COMMENT_DATA,
            " "
        ).setValue(OnedioCommon.convertList2StringStr(listOfLikedCommentId))

    }

    override fun openVisibilityOfLayout() {
        TODO("Not yet implemented")
    }

    override fun handleSendRead(response4ReadState: String) {
        TODO("Not yet implemented")
    }

    override fun onFailMethod(response4ReadState: String) {
        TODO("Not yet implemented")
    }

    override fun showLoading() {
        newArticleFeedDetailAvlIndicatorProgress.playAnimation()
        newArticleFeedDetailAvlIndicatorProgress.visibility = View.VISIBLE
        /*window?.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )*/
    }

    //==================================================================================================================
    /**
     * Hide Loading
     */
    //==================================================================================================================
    override fun hideLoading() {
        newArticleFeedDetailAvlIndicatorProgress.cancelAnimation()
        newArticleFeedDetailAvlIndicatorProgress.visibility = View.GONE
        /*window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)*/
    }

    override fun showError(response4ErrorObj: Response4ErrorObj) {
        TODO("Not yet implemented")
    }

    override fun showEzDialogMessage(
        titleText: String,
        messageText: String,
        buttonText: String,
        headerColor: Int,
        titleTextColor: Int,
        messageTextColor: Int,
        buttonTextColor: Int
    ) {
        TODO("Not yet implemented")
    }
}