package com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.optimizedArticleDetail

import android.annotation.SuppressLint
import android.app.Activity
import android.content.*
import android.net.Uri
import android.net.http.SslError
import android.os.Bundle
import android.os.Handler
import android.view.*
import android.webkit.SslErrorHandler
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import cn.jzvd.JzvdStd
import com.bumptech.glide.Glide
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.gson.Gson
import com.onedio.androidside.BuildConfig
import com.onedio.androidside.R
import com.onedio.androidside.common.CustomElegantActionListeners
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.common.OnedioCustomIntent
import com.onedio.androidside.common.PopupClass
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
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.ArticleDetailActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.FavoriteDataInfo
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
import com.onedio.androidside.prefs.BooleanSharedPrefs
import com.onedio.androidside.prefs.IntSharedPrefs
import com.onedio.androidside.prefs.StringSharedPrefs
import com.onedio.androidside.singleton.OnedioSingletonPattern
import com.yandex.metrica.YandexMetrica
import kotlinx.android.synthetic.main.activity_article_detail_with_recyclerview.view.*

class ArticleDetailRecyclerSwipe : Fragment(), IArticleDetailActivityView {

    private lateinit var articleDetailActivirtyPresenterImpl: ArticleDetailActivirtyPresenterImpl

    private var mActivity: Activity? = null

    private lateinit var viewP: View

    private lateinit var sharedPref: SharedPreferences

    private var pos = 0

    private var isRight: Boolean = true
    private var isScreenLoadMultiple: Boolean = false

    private var theme: String = ""

    private lateinit var articleItemData: HugeCardModel
    private var legacyId: Long? = null
    private lateinit var articleId: String
    private var isAddedFavorite: Boolean = false

    private lateinit var newMapOfEmojiReactionSharedPrefs: HashMap<String, MutableList<String>>

    private var recommendedArticleFeed: FeedArticleModel? = null

    private var arrOfArticleDetailData: MutableList<ArticleDetailAdapterModel> = mutableListOf()

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    private var isReqRecommendWidget: Boolean = false

    private var listOfLikedCommentId: MutableList<String> = mutableListOf()

    private var listOfFavoritesArticleData: MutableList<FavoriteDataInfo> = mutableListOf()


    companion object {
        const val ARG_POSITION = "position"

        fun getInstance(position: Int, articleItem: HugeCardModel): Fragment {
            val doppelgangerFragment =
                ArticleDetailRecyclerSwipe()
            val bundle = Bundle()
            bundle.putInt(ARG_POSITION, position)
            bundle.putParcelable("ARTICLE_DATA", articleItem)
            doppelgangerFragment.arguments = bundle
            return doppelgangerFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewP =
            inflater.inflate(R.layout.activity_article_detail_with_recyclerview_2, container, false)

        sharedPref =
            mActivity?.applicationContext?.getSharedPreferences(
                OnedioConstant.SHARED_PREF_FILE_NAME,
                Context.MODE_PRIVATE
            )!!

        theme = sharedPref.getString("mode", "default")!!

        val bundle = this.arguments
        articleItemData = bundle?.getParcelable("ARTICLE_DATA")!!

        legacyId = articleItemData.legacyId

        initArticleDetailActivityComponent()

        return viewP
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val bundle = this.arguments
        pos = bundle?.getInt(ArticleDetailRecyclerSwipe.ARG_POSITION)!!


        isRight = sharedPref.getBoolean("IS_RIGHT", true)

        showInAppReview()

        savedInstanceState?.let {
            isScreenLoadMultiple = true
        } ?: run {
            isScreenLoadMultiple = false
        }
    }

    override fun initArticleDetailActivityComponent() {
        Handler().postDelayed({

            if (!::articleDetailActivirtyPresenterImpl.isInitialized)
                articleDetailActivirtyPresenterImpl =
                    ArticleDetailActivirtyPresenterImpl(
                        ArticleFeedDetailModelImpl(),
                        this
                    )

            checkArticleIdIsExist(legacyId.toString())

            if (pos == 0 && isRight)
                sendReadState(legacyId!!)

            getRecommendWidgetData(legacyId!!, false)
        }, 500)
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

            mActivity?.invalidateOptionsMenu()

            sendFBAnalyticsData(articleDetailResponse)

            OnedioCommon.sendLog2Crashlytics(legacyId.toString() + " --- " + titleOfArticle)

            if (!isScreenLoadMultiple && pos == 0 && isRight)
                loadGAWebView()

            prepareArticleData(response4ArticleFeedDetails)

            initRecycler()

        }
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

    private fun initRecycler() {

        val mLayoutManager =
            PreCachingLayoutManager(
                mActivity?.applicationContext!!,
                RecyclerView.VERTICAL,
                false
            )
        mLayoutManager.setExtraLayoutSpace(DeviceUtils.getScreenHeight(mActivity?.applicationContext!!))

        viewP.articleDetailRecyclerView.apply {
            layoutManager = mLayoutManager
            setItemViewCacheSize(5)
            adapter = ArticleDetailRecyclerViewAdapter(
                mActivity?.applicationContext!!,
                arrOfArticleDetailData,
                articleDetailActivirtyPresenterImpl
            ) { position, itemOfArticlePart, viewType ->
                handleClickViews(position, itemOfArticlePart, viewType)
            }
        }

        viewP.articleDetailRecyclerView?.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val firstVisibleItemPos = mLayoutManager.findFirstCompletelyVisibleItemPosition()
                if (firstVisibleItemPos == 6 && !isReqRecommendWidget) {
                    val params = Bundle()
                    firebaseAnalytics =
                        FirebaseAnalytics.getInstance(mActivity?.applicationContext!!)
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
            Intent(mActivity, TagsArticleActivityViewImpl::class.java)
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
                    ArticleDetailActivityViewImpl::class.java
                )
                val intent =
                    Intent(
                        mActivity!!,
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
                    ArticleDetailActivityViewImpl::class.java
                )
                val intent =
                    Intent(
                        mActivity!!,
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
                    ArticleDetailActivityViewImpl::class.java
                )

                val intent =
                    Intent(
                        mActivity!!,
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
                    val intent = Intent(mActivity, ProfileActivityViewImpl::class.java)
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
            ArticleDetailActivityViewImpl::class.java
        )

        val intent =
            Intent(mActivity, ArticleDetailActivityViewImpl::class.java)
        intent.putExtra("ARTICLE_DETAIL_DATA", Gson().toJson(articleItem))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)

        startAnim()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun loadGAWebView() {
        val gaUrl =
            BuildConfig.BASE_URL + "/v3/1/1/1/articles/$legacyId/analytics?platform=android"

        OnedioSingletonPattern.instance.getWebView(mActivity?.applicationContext!!, gaUrl)
            .webViewClient =
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

        firebaseAnalytics = FirebaseAnalytics.getInstance(mActivity?.applicationContext!!)
        firebaseAnalytics.setCurrentScreen(
            mActivity!!,
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
            ArticleDetailActivityViewImpl::class.java
        )

        val intent =
            Intent(mActivity, ArticleDetailActivityViewImpl::class.java)
        intent.putExtra("ARTICLE_DETAIL_DATA", Gson().toJson(articleItem))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)

        startAnim()
    }

    private fun startAnim() {
        OnedioCustomIntent.startAnim(mActivity!!, "l2r")
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
                mActivity,
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

    private fun sendReadState(legacyId: Long) {
        if (!::articleDetailActivirtyPresenterImpl.isInitialized)
            articleDetailActivirtyPresenterImpl =
                ArticleDetailActivirtyPresenterImpl(
                    ArticleFeedDetailModelImpl(),
                    this
                )
        articleDetailActivirtyPresenterImpl.sendReadState(legacyId)
    }

    private fun checkArticleIdIsExist(legacyId: String) {
        val sEmojiClickedData = StringSharedPrefs(
            sharedPref,
            OnedioConstant.EMOJI_REACTION_CLICKED_EMOJI_CODES,
            ""
        ).getValue<String>()


        if (sEmojiClickedData == "") {
            newMapOfEmojiReactionSharedPrefs = HashMap()
            newMapOfEmojiReactionSharedPrefs[legacyId] = mutableListOf()
        } else
            newMapOfEmojiReactionSharedPrefs =
                OnedioCommon.convertString2Map(sEmojiClickedData) as HashMap<String, MutableList<String>>


        newMapOfEmojiReactionSharedPrefs[legacyId]?.let {

        } ?: run {
            newMapOfEmojiReactionSharedPrefs[legacyId] = mutableListOf()
        }

        StringSharedPrefs(
            sharedPref,
            OnedioConstant.EMOJI_REACTION_CLICKED_EMOJI_CODES,
            ""
        ).setValue(OnedioCommon.convertMap2String(newMapOfEmojiReactionSharedPrefs))
    }

    private fun showInAppReview() {
        val articleOpenCount = IntSharedPrefs(
            sharedPref,
            OnedioConstant.SHARED_PREF_ARTICLE_OPEN_COUNT,
            0
        ).getValue<Int>()

        val inAppReviewPopupShow = BooleanSharedPrefs(
            sharedPref,
            OnedioConstant.SHARED_PREF_IN_APP_REVIEW_POPUP_SHOW,
            true
        ).getValue<Boolean>()

        if (articleOpenCount == 4 && inAppReviewPopupShow) {
            showUpdateDialogIfNeeded("Uygulamamızı beğendiysen vereceğin 5 yıldıza talibiz", false)

            BooleanSharedPrefs(
                sharedPref,
                OnedioConstant.SHARED_PREF_IN_APP_REVIEW_POPUP_SHOW,
                false
            ).setValue(false)

        }
    }

    private fun showUpdateDialogIfNeeded(message: String, isForce: Boolean) {
        val dialog = PopupClass(mActivity, isForce)
            .setCanceledOnTouchOutside(false)
            .setTitleHidden(false)
            .setElegantActionClickListener(object :
                CustomElegantActionListeners {
                override fun onPositiveListener(dialog: PopupClass) {
                    goPlayStore()
                    dialog.dismiss()
                }

                override fun onNegativeListener(dialog: PopupClass) {
                    dialog.dismiss()
                }

                override fun onGotItListener(dialog: PopupClass) {

                    dialog.dismiss()
                }

                override fun onCloseClicked(dialog: PopupClass) {
                    dialog.dismiss()
                }

                override fun onCancelListener(dialog: DialogInterface) {
                    dialog.dismiss()
                }
            })
            .show()

        if (dialog.getTitleIconView() != null) {
            val imageUrl = if (theme == "dark") {
                R.drawable.ic_onedio_logo_popup_dark
            } else {
                R.drawable.ic_onedio_logo_popup
            }


            Glide.with(this).load(imageUrl)
                .into(dialog.getTitleIconView()!!)
            //dialog.getTitleTextView()!!.visibility = View.GONE
            dialog.getContentTextView()!!.text =
                message
            dialog.getPositiveButtonTextView()!!.text = "Puan Ver"
            //dialog.getContentTextView()!!.setTextColor(contentTextColor)
        }
    }

    private fun goPlayStore() {
        val uri: Uri =
            Uri.parse("market://details?id=${mActivity?.applicationContext?.packageName}")
        val goToMarket = Intent(Intent.ACTION_VIEW, uri)
        goToMarket.addFlags(
            Intent.FLAG_ACTIVITY_NO_HISTORY or
                    Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK
        )
        try {
            startActivity(goToMarket)
        } catch (e: ActivityNotFoundException) {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=${mActivity?.applicationContext?.packageName}")
                )
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        if (isAddedFavorite)
            menu.removeItem(R.id.actionAddFavorite)
        else
            menu.removeItem(R.id.actionDeleteFavorite)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)

        if (isAddedFavorite)
            menu.findItem(R.id.actionDeleteFavorite).setIcon(R.drawable.ic_added_favorite)
        else
            menu.findItem(R.id.actionAddFavorite).setIcon(R.drawable.ic_delete_favorited)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                JzvdStd.releaseAllVideos()
                //viewP.taboolaWidget.reset()
                mActivity?.onBackPressed()
            }
            R.id.actionAddFavorite -> {

                if (OnedioCommon.isUserLogin()) {
                    articleDetailActivirtyPresenterImpl.addFavorite(
                        legacyId!!
                    )
                } else {
                    startActivity(
                        Intent(
                            mActivity,
                            LoginAndRegisterDashboardActivityViewImpl::class.java
                        )
                    )
                }
            }
            R.id.actionDeleteFavorite -> {
                if (OnedioCommon.isUserLogin()) {
                    if (isAddedFavorite)
                        articleDetailActivirtyPresenterImpl.deleteFavorite(
                            legacyId!!
                        )
                } else {
                    startActivity(
                        Intent(
                            mActivity,
                            LoginAndRegisterDashboardActivityViewImpl::class.java
                        )
                    )
                }
            }
            R.id.actionShare -> {
                OnedioCommon.shareArticle(mActivity!!, legacyId!!)
            }
        }
        return true
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is Activity) {
            mActivity = context
        }

    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            legacyId?.let {
                sendReadState(legacyId!!)
                loadGAWebView()
            } ?: run {
                //initArticleDetailActivityComponent()
            }
        }
    }


    override fun setHeadOfArticleDetail(articleFeedDetailsModel: ArticleFeedDetailsModel) {

    }

    override fun loadImageWithPicasso(
        imageUrl: String,
        imageView: ImageView,
        progressBar: ProgressBar
    ) {

    }

    override fun setEntriesData(arrOfEntriesData: MutableList<ArticleFeedDetailsEntryModel>) {

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
                    mActivity?.applicationContext!!,
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
                    mActivity?.applicationContext!!,
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
                    sharedPref,
                    OnedioConstant.EMOJI_REACTION_CLICKED_EMOJI_CODES,
                    ""
                ).getValue<String>()

                val mEmojiClickedData = OnedioCommon.convertString2Map(sEmojiClickedData)
                mEmojiClickedData[legacyId.toString()]?.remove("")
                mEmojiClickedData[legacyId.toString()]?.remove(emojiCode)

                val newSEmojiClickedData = OnedioCommon.convertMap2String(mEmojiClickedData)
                StringSharedPrefs(
                    sharedPref,
                    OnedioConstant.EMOJI_REACTION_CLICKED_EMOJI_CODES,
                    ""
                ).setValue(newSEmojiClickedData)

            }
            0 -> {
                val sEmojiClickedData = StringSharedPrefs(
                    sharedPref,
                    OnedioConstant.EMOJI_REACTION_CLICKED_EMOJI_CODES,
                    ""
                ).getValue<String>()

                val mEmojiClickedData = OnedioCommon.convertString2Map(sEmojiClickedData)
                mEmojiClickedData[legacyId.toString()]?.remove("")
                mEmojiClickedData[legacyId.toString()]?.add(emojiCode)

                val newSEmojiClickedData = OnedioCommon.convertMap2String(mEmojiClickedData)
                StringSharedPrefs(
                    sharedPref,
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

    override fun handleAddFavoriteDataModel(response4AddFavoriteArticle: Response4AddFavoriteArticle) {
        articleItemData.isUserFavorited = true
        isAddedFavorite = true
        //addFavorite.setImageResource(R.drawable.ic_added_favorite)
        mActivity?.invalidateOptionsMenu()

        listOfFavoritesArticleData = OnedioCommon.convertString2List(
            StringSharedPrefs(
                sharedPref,
                OnedioConstant.SHARED_PREF_FAVORITE_DATA,
                OnedioCommon.convertList2String(
                    listOfFavoritesArticleData
                )
            ).getValue()
        )

        listOfFavoritesArticleData.add(
            FavoriteDataInfo(
                legacyId!!,
                true
            )
        )

        StringSharedPrefs(
            sharedPref,
            OnedioConstant.SHARED_PREF_FAVORITE_DATA,
            " "
        ).setValue(OnedioCommon.convertList2String(listOfFavoritesArticleData))
    }

    override fun handleDeleteFavoriteDataModel(response4AddFavoriteArticle: Response4AddFavoriteArticle) {
        articleItemData.isUserFavorited = false
        isAddedFavorite = false
        mActivity?.invalidateOptionsMenu()

        listOfFavoritesArticleData = OnedioCommon.convertString2List(
            StringSharedPrefs(
                sharedPref,
                OnedioConstant.SHARED_PREF_FAVORITE_DATA,
                OnedioCommon.convertList2String(
                    listOfFavoritesArticleData
                )
            ).getValue()
        )

        listOfFavoritesArticleData.add(
            FavoriteDataInfo(
                legacyId!!,
                false
            )
        )

        StringSharedPrefs(
            sharedPref,
            OnedioConstant.SHARED_PREF_FAVORITE_DATA,
            " "
        ).setValue(OnedioCommon.convertList2String(listOfFavoritesArticleData))
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
        Toast.makeText(mActivity?.applicationContext, "Beğenildi", Toast.LENGTH_SHORT).show()

        listOfLikedCommentId = OnedioCommon.convertString2ListStr(
            StringSharedPrefs(
                sharedPref,
                OnedioConstant.SHARED_PREF_LIKE_COMMENT_DATA,
                OnedioCommon.convertList2StringStr(
                    listOfLikedCommentId
                )
            ).getValue()
        )

        listOfLikedCommentId.add(itemOfComment.commentId)

        StringSharedPrefs(
            sharedPref,
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
        Toast.makeText(mActivity?.applicationContext, "Beğeni geri alındı", Toast.LENGTH_SHORT)
            .show()

        listOfLikedCommentId = OnedioCommon.convertString2ListStr(
            StringSharedPrefs(
                sharedPref,
                OnedioConstant.SHARED_PREF_LIKE_COMMENT_DATA,
                OnedioCommon.convertList2StringStr(
                    listOfLikedCommentId
                )
            ).getValue()
        )

        listOfLikedCommentId.remove(itemOfComment.commentId)

        StringSharedPrefs(
            sharedPref,
            OnedioConstant.SHARED_PREF_LIKE_COMMENT_DATA,
            " "
        ).setValue(OnedioCommon.convertList2StringStr(listOfLikedCommentId))
    }

    override fun openVisibilityOfLayout() {

    }

    override fun handleSendRead(response4ReadState: String) {

    }

    override fun onFailMethod(response4ReadState: String) {

    }

    override fun showLoading() {
        viewP.newArticleFeedDetailAvlIndicatorProgress.playAnimation()
        viewP.newArticleFeedDetailAvlIndicatorProgress.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        viewP.newArticleFeedDetailAvlIndicatorProgress.cancelAnimation()
        viewP.newArticleFeedDetailAvlIndicatorProgress.visibility = View.GONE
    }

    override fun showError(response4ErrorObj: Response4ErrorObj) {

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

    }

    override fun onDestroy() {
        super.onDestroy()
        JzvdStd.releaseAllVideos()
    }

}