package com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.swipableArticleDetail

import android.annotation.SuppressLint
import android.app.Activity
import android.content.*
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.net.Uri
import android.net.http.SslError
import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import android.util.Log
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
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import cn.jzvd.JzvdStd
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.doubleclick.PublisherAdRequest
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.gson.Gson
import com.onedio.androidside.BuildConfig
import com.onedio.androidside.R
import com.onedio.androidside.common.CustomElegantActionListeners
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.common.OnedioCustomIntent
import com.onedio.androidside.common.PopupClass
import com.onedio.androidside.common.model.OnedioEzDialogMessageModel4Fragment
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.categoryFragment.categoryModel.CategoryModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleComment.response.Response4LikeAndUnLike
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.ArticleFeedDetailModelImpl
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.emojiReaction.EmojiReactionModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.entries.EntriesModelData
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.newResponse.ArticleFeedDetailsEntryModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.newResponse.ArticleFeedDetailsModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.newResponse.Response4ArticleFeedDetails
import com.onedio.androidside.mvp.model.dashboard.homeFragment.commentModel.CommentModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.addFavorite.Response4AddFavoriteArticle
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel.ArticleReactionsModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel.ArticleTopCommentModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel.Response4ArticlesFeed
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.sliderModel.FeedArticleModel
import com.onedio.androidside.mvp.model.welcomeDashboard.search.dataModel.tags.Tags
import com.onedio.androidside.mvp.presenter.dashboard.homeFragment.articleDetail.ArticleDetailActivirtyPresenterImpl
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.CategoryFeedActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.food.FoodActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.video.VideoActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleComment.ArticleCommentActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.ArticleDetailActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.FavoriteDataInfo
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.IArticleDetailActivityView
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.TestArticleDetailActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.entriesAdapter.ArticleDetailCommentRecyclerViewAdapter
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.entriesAdapter.ArticleDetailRecommendedRecyclerViewAdapter
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.entriesAdapter.EmojiReactionRecyclerViewAdapter
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.entriesAdapter.EntriesRecyclerViewAdapter
import com.onedio.androidside.mvp.view.dashboard.homeFragment.profile.ProfileActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.widgets.hugeCardRecyclerViewAdapter.model.HugeCardModel
import com.onedio.androidside.mvp.view.dashboard.search.moreTag.tagsArticle.TagsArticleActivityViewImpl
import com.onedio.androidside.mvp.view.loginAndRegister.LoginAndRegisterDashboardActivityViewImpl
import com.onedio.androidside.prefs.BooleanSharedPrefs
import com.onedio.androidside.prefs.IntSharedPrefs
import com.onedio.androidside.prefs.StringSharedPrefs
import com.onedio.androidside.singleton.OnedioSingletonPattern
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.taboola.android.PublisherInfo
import com.taboola.android.Taboola
import com.taboola.android.TaboolaWidget
import com.taboola.android.listeners.TaboolaEventListener
import com.taboola.android.utils.SdkDetailsHelper
import com.yandex.metrica.YandexMetrica
import kotlinx.android.synthetic.main.activity_article_detail.view.articleActionBackground
import kotlinx.android.synthetic.main.activity_article_detail.view.articleDetailAction
import kotlinx.android.synthetic.main.activity_article_detail.view.articleDetailActionPhotoProgress
import kotlinx.android.synthetic.main.activity_article_detail.view.articleDetailCommentsRecyclerView
import kotlinx.android.synthetic.main.activity_article_detail.view.articleDetailCoverPhoto
import kotlinx.android.synthetic.main.activity_article_detail.view.articleDetailCoverPhotoProgress
import kotlinx.android.synthetic.main.activity_article_detail.view.articleDetailItemReadCount
import kotlinx.android.synthetic.main.activity_article_detail.view.articleDetailTitle
import kotlinx.android.synthetic.main.activity_article_detail.view.articleFeedDate
import kotlinx.android.synthetic.main.activity_article_detail.view.badgeLineCovertPhoto
import kotlinx.android.synthetic.main.activity_article_detail.view.coverPhotoBadgeImage
import kotlinx.android.synthetic.main.activity_article_detail.view.editorTitle
import kotlinx.android.synthetic.main.activity_article_detail.view.editorUserName
import kotlinx.android.synthetic.main.activity_article_detail.view.editorUserProfileConstraintLayout
import kotlinx.android.synthetic.main.activity_article_detail.view.emojiReactionsRecyclerView
import kotlinx.android.synthetic.main.activity_article_detail.view.emojiViewLine
import kotlinx.android.synthetic.main.activity_article_detail.view.entriesRecyclerView
import kotlinx.android.synthetic.main.activity_article_detail.view.infoEmojiReaction
import kotlinx.android.synthetic.main.activity_article_detail.view.infoFirstComment
import kotlinx.android.synthetic.main.activity_article_detail.view.infoOnedioUyeleri
import kotlinx.android.synthetic.main.activity_article_detail.view.nestedScrollView
import kotlinx.android.synthetic.main.activity_article_detail.view.profilePhoto
import kotlinx.android.synthetic.main.activity_article_detail.view.profilePhotoProgress
import kotlinx.android.synthetic.main.activity_article_detail.view.publisherAdViewBottom
import kotlinx.android.synthetic.main.activity_article_detail.view.publisherAdViewTop
import kotlinx.android.synthetic.main.activity_article_detail.view.readableCountConst
import kotlinx.android.synthetic.main.activity_article_detail.view.recommendWidgetViewLine
import kotlinx.android.synthetic.main.activity_article_detail.view.seeAllComments
import kotlinx.android.synthetic.main.activity_article_detail.view.taboolaWidget
import kotlinx.android.synthetic.main.activity_article_detail.view.viewLineBenzerIcerikler
import kotlinx.android.synthetic.main.activity_article_detail.view.viewLineComment
import kotlinx.android.synthetic.main.activity_article_detail.view.widthLine
import kotlinx.android.synthetic.main.activity_article_detail.view.writeCommentArea
import kotlinx.android.synthetic.main.activity_article_detail_2.view.*

class ArticleDetail : Fragment(),
    IArticleDetailActivityView, TaboolaEventListener {

    private var mActivity: Activity? = null

    private lateinit var viewP: View

    private var pos = 0

    private lateinit var articleItemData: HugeCardModel

    /*private lateinit var toolBar: Toolbar*/
    private var isAddedFavorite: Boolean = false

    private lateinit var articleDetailActivirtyPresenterImpl: ArticleDetailActivirtyPresenterImpl

    private lateinit var mAdapter: EntriesRecyclerViewAdapter
    private var mAdapter2Comment: ArticleDetailCommentRecyclerViewAdapter? = null
    private lateinit var mAdapter2RecommendedArticles: ArticleDetailRecommendedRecyclerViewAdapter

    private lateinit var sharedPrefs: SharedPreferences

    private lateinit var newMapOfEmojiReactionSharedPrefs: HashMap<String, MutableList<String>>
    private var listOfLikedCommentId: MutableList<String> = mutableListOf()

    private var legacyId: Long? = null
    private lateinit var articleId: String
    private lateinit var categoryId: String
    private lateinit var categoryName: String

    private var count = 0

    private var listOfFavoritesArticleData: MutableList<FavoriteDataInfo> = mutableListOf()

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    private var isScreenLoadMultiple: Boolean = false
    private var isBottomAdRequest: Boolean = false
    private var isRecommendWidgetReq: Boolean = false

    private var isShowAds: Boolean = false

    private var isRight: Boolean = true

    private var theme: String = ""

    private var recommendedArticleFeed: MutableList<FeedArticleModel> = mutableListOf()

    companion object {
        const val ARG_POSITION = "position"

        fun getInstance(position: Int, articleItem: HugeCardModel): Fragment {
            val doppelgangerFragment =
                ArticleDetail()
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

        viewP = inflater.inflate(R.layout.activity_article_detail_2, container, false)

        val sharedPref =
            mActivity?.applicationContext?.getSharedPreferences(
                OnedioConstant.SHARED_PREF_FILE_NAME,
                Context.MODE_PRIVATE
            )
        theme = sharedPref?.getString("mode", "default")!!

        val bundle = this.arguments
        articleItemData = bundle?.getParcelable("ARTICLE_DATA")!!

        legacyId = articleItemData.legacyId

        initArticleDetailActivityComponent()

        if (theme == "dark") {
            viewP.readableCountConst.background = ContextCompat.getDrawable(
                mActivity?.applicationContext!!,
                R.drawable.bg_category_item_read_count_dark_mode
            )
            viewP.articleActionBackground.setImageResource(R.drawable.icon_circle_dark_mode)
            viewP.profilePhoto.foreground = ContextCompat.getDrawable(
                mActivity?.applicationContext!!,
                R.drawable.custom_image_view_white_dark_mode
            )
            viewP.writeCommentArea.background = ContextCompat.getDrawable(
                mActivity?.applicationContext!!,
                R.drawable.custom_edittext_dark_mode
            )
            viewP.writeCommentArea.setHintTextColor(Color.parseColor("#FFFFFF"))
            viewP.writeCommentArea.setTextColor(Color.parseColor("#FFFFFF"))
        } else {
            viewP.readableCountConst.background = ContextCompat.getDrawable(
                mActivity?.applicationContext!!,
                R.drawable.bg_category_item_read_count
            )
            viewP.articleActionBackground.setImageResource(R.drawable.ic_circle)
            viewP.profilePhoto.foreground = ContextCompat.getDrawable(
                mActivity?.applicationContext!!,
                R.drawable.custom_image_view_white
            )
            viewP.writeCommentArea.background = ContextCompat.getDrawable(
                mActivity?.applicationContext!!,
                R.drawable.custom_edittext
            )
            viewP.writeCommentArea.setHintTextColor(Color.parseColor("#191919"))
            viewP.writeCommentArea.setTextColor(Color.parseColor("#191919"))
        }

        return viewP
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val bundle = this.arguments
        pos = bundle?.getInt(ARG_POSITION)!!


        sharedPrefs =
            mActivity?.getSharedPreferences(
                OnedioConstant.SHARED_PREF_FILE_NAME,
                Context.MODE_PRIVATE
            )!!

        isRight = sharedPrefs.getBoolean("IS_RIGHT", true)

        showInAppReview()

        savedInstanceState?.let {
            isScreenLoadMultiple = true
        } ?: run {
            isScreenLoadMultiple = false
        }


    }

    override fun initArticleDetailActivityComponent() {
        Handler().postDelayed({
            prepareToolbar()

            if (!::articleDetailActivirtyPresenterImpl.isInitialized)
                articleDetailActivirtyPresenterImpl =
                    ArticleDetailActivirtyPresenterImpl(
                        ArticleFeedDetailModelImpl(),
                        this
                    )

            checkArticleIdIsExist(legacyId.toString())

            if (pos == 0 && isRight)
                sendReadState(legacyId!!)

            clickSeeAllComment()

            getRecommendWidgetData(legacyId!!, false)
            //getArticleFeedDetail(legacyId!!, false)
        }, 500)
    }

    private fun prepareToolbar() {
        /*toolBar = viewP.toolBar4ArticleDetail as Toolbar
        (mActivity as AppCompatActivity).setSupportActionBar(toolBar)
        (mActivity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (mActivity as AppCompatActivity).supportActionBar!!.setDisplayShowHomeEnabled(true)
        (mActivity as AppCompatActivity).supportActionBar!!.setDisplayShowTitleEnabled(false)
        toolBar.setNavigationIcon(R.drawable.ic_back)

        toolBar.setOnClickListener {
            viewP.nestedScrollView.smoothScrollTo(0, 0)
        }*/

        viewP.nestedScrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v,
                                                                                                   _,
                                                                                                   _,
                                                                                                   _,
                                                                                                   _ ->

            if (isVisible(viewP.infoEmojiReaction)) {
                if (isShowAds) {
                    if (!isBottomAdRequest) {
                        val adRequestBottom = PublisherAdRequest.Builder().build()
                        viewP.publisherAdViewBottom.loadAd(adRequestBottom)
                        isBottomAdRequest = true
                        viewP.publisherAdViewBottom.adListener = object : AdListener() {
                            override fun onAdLoaded() {
                                Log.i("BOTTOM_AD_LOADED --- ", "onAdLoaded")
                            }

                            override fun onAdFailedToLoad(errorCode: Int) {
                                Log.i("BOTTOM_AD_FAILED --- ", "onAdFailedToLoad")
                                viewP.publisherAdViewBottom.visibility = View.GONE
                            }

                            override fun onAdOpened() {
                                Log.i("BOTTOM_AD_OPENED --- ", "onAdOpened")
                            }

                            override fun onAdClicked() {
                                Log.i("BOTTOM_AD_CLICKED --- ", "onAdClicked")
                            }

                            override fun onAdLeftApplication() {
                                Log.i("BOTTOM_AD_LEFTAPP --- ", "onAdLeftApplication")
                            }

                            override fun onAdClosed() {
                                Log.i("BOTTOM_AD_CLOSED --- ", "onAdClosed")
                            }
                        }
                    }
                } else
                    viewP.publisherAdViewBottom.visibility = View.GONE
            }

            if (isVisible(viewP.recommendWidgetViewLine)) {
                if (!isRecommendWidgetReq) {
                    val params = Bundle()

                    if (!::firebaseAnalytics.isInitialized)
                        firebaseAnalytics =
                            FirebaseAnalytics.getInstance(mActivity?.applicationContext!!)

                    firebaseAnalytics.logEvent("in_article_recommendation_view", params)

                    val mapOfFeedAppMetrica = HashMap<String, Any>()
                    YandexMetrica.reportEvent("in_article_recommendation_view", mapOfFeedAppMetrica)
                    isRecommendWidgetReq = true
                }

            }

        })

    }

    override fun getArticleFeedDetail(legacyId: Long, isTaboola: Boolean) {
        articleDetailActivirtyPresenterImpl.getArticleFeedDetailNew(legacyId, isTaboola)
    }

    override fun handleArticleFeedDetailDataNew(
        response4ArticleFeedDetails: Response4ArticleFeedDetails,
        isTaboola: Boolean
    ) {

        val articleDetailResponse = response4ArticleFeedDetails.data


        var showInWebView: Boolean? = null
        articleDetailResponse?.showInWebview?.let {
            showInWebView = it
        } ?: run {
            showInWebView = false
        }

        if (isTaboola) {
            if (showInWebView!!) {
                goTestArticleDetailActivity(articleDetailResponse!!)
            } else {
                var id: String? = null
                articleDetailResponse?.id?.let {
                    id = it
                } ?: run {
                    id = ""
                }

                var legacyId: Long? = null
                articleDetailResponse?.legacyId?.let {
                    legacyId = it
                } ?: run {
                    legacyId = 0
                }


                var categoryName = ""
                var categoryId = ""

                if (articleDetailResponse?.categories?.size != 0) {
                    articleDetailResponse?.categories?.let { it1 ->
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
            articleId = articleDetailResponse?.id!!
            val titleOfArticle = articleDetailResponse.title
            isAddedFavorite = articleDetailResponse.isUserFavorite!!

            mActivity?.invalidateOptionsMenu()

            setTaboolaWidget(titleOfArticle!!, legacyId.toString())

            firebaseAnalytics = FirebaseAnalytics.getInstance(mActivity?.applicationContext!!)
            firebaseAnalytics.setCurrentScreen(
                mActivity!!,
                titleOfArticle,
                "ArticleDetailActivityViewImpl"
            )

            sendFBAnalyticsData(articleDetailResponse)

            OnedioCommon.sendLog2Crashlytics(legacyId.toString() + " --- " + titleOfArticle)

            response4ArticleFeedDetails.data.flags?.let {
                it.showAds?.let {
                    isShowAds = it
                } ?: run {
                    isShowAds = false
                }
            } ?: run {
                isShowAds = false
            }

            //isShowAds = response4ArticleFeedDetails.data.flags?.showAds!!
            if (isShowAds) {
                if (!isScreenLoadMultiple) {
                    Log.i("isShownAds --- ", "true")
                    val adRequest = PublisherAdRequest.Builder().build()
                    viewP.publisherAdViewTop.loadAd(adRequest)

                    viewP.publisherAdViewTop.adListener = object : AdListener() {
                        override fun onAdLoaded() {
                            Log.i("TOP_AD_LOADED --- ", "onAdLoaded")
                        }

                        override fun onAdFailedToLoad(errorCode: Int) {
                            Log.i("TOP_AD_FAILED --- ", "onAdFailedToLoad + $errorCode")
                            viewP.publisherAdViewTop.visibility = View.GONE
                        }

                        override fun onAdOpened() {
                            Log.i("TOP_AD_OPENED --- ", "onAdOpened")
                        }

                        override fun onAdClicked() {
                            Log.i("TOP_AD_CLICKED --- ", "onAdClicked")
                        }

                        override fun onAdLeftApplication() {
                            Log.i("TOP_AD_LEFT_APP --- ", "onAdLeftApplication")
                        }

                        override fun onAdClosed() {
                            Log.i("TOP_AD_CLOSED --- ", "onAdClosed")
                        }
                    }
                }
            } else {
                viewP.publisherAdViewTop.visibility = View.GONE
            }

            if (!isScreenLoadMultiple && pos == 0 && isRight)
                loadGAWebView()

            setHeadOfArticleDetail(response4ArticleFeedDetails.data)

            entriesRecyclerViewConfig()

            setEntriesData(response4ArticleFeedDetails.data.entries!!)

            setEmojiReactionsData2Adapter(response4ArticleFeedDetails.data.reactions!!)

            clickCategoryIcon(articleDetailResponse)

            ///// Article Detail Comment
            if (response4ArticleFeedDetails.data.stats?.comments!! > 0) {
                //viewLineYorumlar.visibility = View.VISIBLE
                /*sortInfo4Comments.visibility = View.VISIBLE
                dateSort.visibility = View.VISIBLE
                popularitySort.visibility = View.VISIBLE*/
                viewP.articleDetailCommentsRecyclerView.visibility = View.VISIBLE
                viewP.seeAllComments.visibility = View.VISIBLE

                articleDetailCommentsRecyclerViewConfig()

                count = response4ArticleFeedDetails.data.stats.comments
                setArticleDetailCommentData(response4ArticleFeedDetails.data.comments!!)
            } else {
                viewP.infoFirstComment.visibility = View.VISIBLE
                viewP.writeCommentArea.visibility = View.VISIBLE
            }
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
                recommendedArticleFeed = arrOfRecommendArticle
            } ?: run {
                recommendedArticleFeed.clear()
            }
        } ?: run {
            recommendedArticleFeed.clear()
        }


        getArticleFeedDetail(legacyId, isTaboola)
    }

    override fun setHeadOfArticleDetail(articleFeedDetailsModel: ArticleFeedDetailsModel) {

        viewP.profilePhoto.setOnClickListener {

            articleFeedDetailsModel.authors?.let {
                if (it.size != 0) {
                    val anotherUserId = it[0].id
                    val intent = Intent(mActivity, ProfileActivityViewImpl::class.java)
                    intent.putExtra("IS_USER_OWN_PROFILE", false)
                    intent.putExtra("ANOTHER_USER_PROFILE", anotherUserId)
                    startActivity(intent)
                }
            } ?: run {

            }
        }

        viewP.editorUserName.setOnClickListener {
            articleFeedDetailsModel.authors?.let {
                if (it.size != 0) {
                    val anotherUserId = it[0].id
                    val intent = Intent(mActivity, ProfileActivityViewImpl::class.java)
                    intent.putExtra("IS_USER_OWN_PROFILE", false)
                    intent.putExtra("ANOTHER_USER_PROFILE", anotherUserId)
                    startActivity(intent)
                }
            } ?: run {

            }
        }

        viewP.editorTitle.setOnClickListener {
            articleFeedDetailsModel.authors?.let {
                if (it.size != 0) {
                    val anotherUserId = it[0].id
                    val intent = Intent(mActivity, ProfileActivityViewImpl::class.java)
                    intent.putExtra("IS_USER_OWN_PROFILE", false)
                    intent.putExtra("ANOTHER_USER_PROFILE", anotherUserId)
                    startActivity(intent)
                }
            } ?: run {

            }
        }


        articleFeedDetailsModel.image?.let {
            it.alternates?.let {
                it.standardResolution?.let {
                    it.url?.let {
                        loadImage(
                            it,
                            viewP.articleDetailCoverPhoto,
                            viewP.articleDetailCoverPhotoProgress
                        )
                    } ?: run {
                        viewP.articleDetailCoverPhoto.visibility = View.VISIBLE
                        viewP.articleDetailCoverPhoto.setImageResource(R.drawable.image_error_dark_mode)
                        viewP.articleDetailCoverPhotoProgress.visibility = View.GONE
                    }
                } ?: run {
                    viewP.articleDetailCoverPhoto.visibility = View.VISIBLE
                    viewP.articleDetailCoverPhoto.setImageResource(R.drawable.image_error_dark_mode)
                    viewP.articleDetailCoverPhotoProgress.visibility = View.GONE
                }
            } ?: run {
                viewP.articleDetailCoverPhoto.visibility = View.VISIBLE
                viewP.articleDetailCoverPhoto.setImageResource(R.drawable.image_error_dark_mode)
                viewP.articleDetailCoverPhotoProgress.visibility = View.GONE
            }
        } ?: run {
            viewP.articleDetailCoverPhoto.visibility = View.VISIBLE
            viewP.articleDetailCoverPhoto.setImageResource(R.drawable.image_error_dark_mode)
            viewP.articleDetailCoverPhotoProgress.visibility = View.GONE
        }

        var strCoverPhotoBadgeImage: String? = null
        var badgeId: String? = null
        var badgeName: String? = null
        articleFeedDetailsModel.badge?.let {
            if (it.size != 0) {
                it[0].icons?.let {
                    it.png?.let {
                        strCoverPhotoBadgeImage = it
                    } ?: run {
                        strCoverPhotoBadgeImage = " "
                    }
                } ?: run {
                    strCoverPhotoBadgeImage = " "
                }
            } else {
                strCoverPhotoBadgeImage = " "
            }
        } ?: run {
            strCoverPhotoBadgeImage = " "
        }

        articleFeedDetailsModel.badge?.let {
            if (articleFeedDetailsModel.badge.size != 0) {
                badgeId = articleFeedDetailsModel.badge[0].id
                badgeName = articleFeedDetailsModel.badge[0].name
            } else {
                badgeId = ""
                badgeName = ""
            }
        } ?: run {
            badgeId = ""
            badgeName = ""
        }

        if (strCoverPhotoBadgeImage != " ") {
            viewP.coverPhotoBadgeImage.visibility = View.VISIBLE
            viewP.badgeLineCovertPhoto.visibility = View.VISIBLE
            loadImageWithoutPicasso(strCoverPhotoBadgeImage!!, viewP.coverPhotoBadgeImage!!)

            viewP.badgeLineCovertPhoto.setOnClickListener {
                val itemOfTag =
                    Tags()
                itemOfTag.id = badgeId
                itemOfTag.name = badgeName

                val intent =
                    Intent(mActivity, TagsArticleActivityViewImpl::class.java)
                intent.putExtra("TAGS_ARTICLE", Gson().toJson(itemOfTag))
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                startAnim()
            }

            viewP.coverPhotoBadgeImage.setOnClickListener {
                val itemOfTag =
                    Tags()
                itemOfTag.id = badgeId
                itemOfTag.name = badgeName

                val intent =
                    Intent(mActivity, TagsArticleActivityViewImpl::class.java)
                intent.putExtra("TAGS_ARTICLE", Gson().toJson(itemOfTag))
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                startAnim()
            }
        } else {
            viewP.coverPhotoBadgeImage.visibility = View.INVISIBLE
            viewP.badgeLineCovertPhoto.visibility = View.INVISIBLE
        }

        var pngFile = " "
        articleFeedDetailsModel.categories?.let {
            if (it.size != 0) {
                it[0].icons?.let {
                    it.png?.let {
                        pngFile = it
                    } ?: run {
                        pngFile = " "
                    }
                } ?: run {
                    pngFile = " "
                }
            } else
                pngFile = " "
        } ?: run {
            pngFile = " "
        }

        if (pngFile != " ") {
            viewP.articleDetailAction.visibility = View.VISIBLE
            viewP.articleActionBackground.visibility = View.VISIBLE
            loadImageWithPicasso(
                pngFile,
                viewP.articleDetailAction,
                viewP.articleDetailActionPhotoProgress
            )
        } else {
            viewP.articleActionBackground.visibility = View.GONE
        }

        articleFeedDetailsModel.authors?.let {
            if (it.size != 0) {
                it[0].image?.let { itemOfImage ->
                    itemOfImage.alternates?.let {
                        it.standardResolution?.let {
                            it.url?.let {
                                loadImageWithPicasso(
                                    it,
                                    viewP.profilePhoto,
                                    viewP.profilePhotoProgress
                                )
                            } ?: run {
                                viewP.profilePhotoProgress.visibility = View.GONE
                            }
                        } ?: run {
                            viewP.profilePhotoProgress.visibility = View.GONE
                        }
                    } ?: run {
                        viewP.profilePhotoProgress.visibility = View.GONE
                    }
                } ?: run {
                    viewP.profilePhotoProgress.visibility = View.GONE
                }

                viewP.editorUserName.text = it[0].username
                it[0].occupation?.let { itemOfUserTitle ->
                    viewP.editorTitle.text = itemOfUserTitle
                } ?: run {
                    viewP.editorTitle.text = "Onedio Üyesi"
                }
            } else
                viewP.profilePhotoProgress.visibility = View.INVISIBLE
        } ?: run {
            viewP.profilePhotoProgress.visibility = View.INVISIBLE
        }


        articleFeedDetailsModel.title?.let {
            viewP.articleDetailTitle.text = articleFeedDetailsModel.title
        } ?: run {
            viewP.articleFeedDate.text = ""
        }

        var isHideCountLayout: Boolean = false
        articleFeedDetailsModel.tags?.let {
            if (it.size != 0) {
                it.find { it == "~no-visitcount" }?.let {
                    isHideCountLayout = true
                } ?: run {
                    isHideCountLayout = false
                }
            } else {
                isHideCountLayout = false
            }
        } ?: run {
            isHideCountLayout = false
        }

        if (!isHideCountLayout) {
            articleFeedDetailsModel.stats?.let {
                it.viewsTotal?.let {
                    viewP.readableCountConst.visibility = View.VISIBLE
                    viewP.articleDetailItemReadCount.text = calculateViewCount(it.toString())
                } ?: run {
                    viewP.readableCountConst.visibility = View.INVISIBLE
                }
            } ?: run {
                viewP.readableCountConst.visibility = View.INVISIBLE
            }
        } else {
            viewP.readableCountConst.visibility = View.INVISIBLE
        }



        articleFeedDetailsModel.createDate?.let {
            viewP.articleFeedDate.text = OnedioCommon.convertFeedDate(it)
        } ?: run {
            viewP.articleFeedDate.text = ""
        }

        openVisibilityOfLayout()
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

    private fun entriesRecyclerViewConfig() {
        viewP.entriesRecyclerView.setHasFixedSize(true)
        viewP.entriesRecyclerView.layoutManager = LinearLayoutManager(mActivity?.applicationContext)
    }

    override fun setEntriesData(arrOfEntriesData: MutableList<ArticleFeedDetailsEntryModel>) {
        setEntriesDataAdapter(addAllEntriesData(arrOfEntriesData))
    }

    private fun setEntriesDataAdapter(entriesData: MutableList<EntriesModelData>) {
        mAdapter =
            EntriesRecyclerViewAdapter(
                mActivity?.applicationContext!!,
                entriesData
            ) {

                when (it.mode) {
                    "oio-embed" -> {

                        JzvdStd.releaseAllVideos()

                        val articleItem =
                            HugeCardModel(
                                it.id,
                                it.legacyId,
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
                                it.categoryName,
                                it.cateegoryId,
                                false,
                                ""
                            )
                        /*OnedioSingletonPattern.instance.setArticleItem(
                            articleItem
                        )*/

                        OnedioSingletonPattern.instance.setActivity(
                            ArticleDetailActivityViewImpl::class.java
                        )
                        //startActivity(Intent(applicationContext, ArticleDetailActivityViewImpl::class.java))
                        /*OnedioCommon.nStartActivity(
                            applicationContext,
                            ArticleDetailActivityViewImpl::class.java
                        )*/

                        val intent =
                            Intent(mActivity, ArticleDetailActivityViewImpl::class.java)
                        intent.putExtra("ARTICLE_DETAIL_DATA", Gson().toJson(articleItem))
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)

                        startAnim()
                    }
                    "recommendWidget" -> {

                        if (!::firebaseAnalytics.isInitialized)
                            firebaseAnalytics =
                                FirebaseAnalytics.getInstance(mActivity?.applicationContext!!)

                        val params = Bundle()
                        firebaseAnalytics.logEvent("in_article_recommendation_click", params)

                        val mapOfFeedAppMetrica = HashMap<String, Any>()
                        YandexMetrica.reportEvent(
                            "in_article_recommendation_click",
                            mapOfFeedAppMetrica
                        )

                        JzvdStd.releaseAllVideos()

                        val articleItem =
                            HugeCardModel(
                                it.id,
                                it.legacyId,
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
                                it.categoryName,
                                it.cateegoryId,
                                false,
                                ""
                            )
                        /*OnedioSingletonPattern.instance.setArticleItem(
                            articleItem
                        )*/

                        OnedioSingletonPattern.instance.setActivity(
                            ArticleDetailActivityViewImpl::class.java
                        )
                        //startActivity(Intent(applicationContext, ArticleDetailActivityViewImpl::class.java))
                        /*OnedioCommon.nStartActivity(
                            applicationContext,
                            ArticleDetailActivityViewImpl::class.java
                        )*/

                        val intent =
                            Intent(mActivity, ArticleDetailActivityViewImpl::class.java)
                        intent.putExtra("ARTICLE_DETAIL_DATA", Gson().toJson(articleItem))
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)

                        startAnim()
                    }
                }

            }

        viewP.entriesRecyclerView.adapter = mAdapter

    }

    private fun addAllEntriesData(arrOfEntriesData: MutableList<ArticleFeedDetailsEntryModel>): MutableList<EntriesModelData> {
        //val listOfEntriesData: MutableList<EntriesModel> = mutableListOf()
        val listOfEntriesData: MutableList<EntriesModelData> = mutableListOf()


        arrOfEntriesData.forEach { itemOfEntryData ->

            var categoryUrl = ""
            var categoryId = ""
            var categoryName = ""
            var emptyLegacyId: Long = 0

            if (itemOfEntryData.embedArticle?.categories?.size != 0) {
                itemOfEntryData.embedArticle?.categories?.let { it1 ->
                    it1[0].icons?.let {
                        it.png?.let {
                            categoryUrl = it
                        } ?: run {
                            categoryUrl = ""
                        }
                    } ?: run {
                        categoryUrl = ""
                    }
                    categoryId = it1[0].id!!
                    categoryName = it1[0].name!!
                } ?: run {
                    categoryUrl = ""
                    categoryId = ""
                    categoryName = ""
                }
            } else {
                categoryUrl = ""
                categoryId = ""
                categoryName = ""
            }

            var source: String? = null
            itemOfEntryData.urls?.let {
                it.source?.let {
                    source = ""
                } ?: run {
                    source = ""
                }
            } ?: run {
                source = ""
            }

            var postDelayed: Boolean? = null
            itemOfEntryData.internaldata?.let {
                it.postDeleted?.let {
                    postDelayed = it
                } ?: run {
                    postDelayed = false
                }
            } ?: run {
                postDelayed = false
            }

            var image: String? = null
            itemOfEntryData.image?.let {
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

            var internalDataFile: String? = null
            itemOfEntryData.internaldata?.let {
                it.file?.let {
                    internalDataFile = it
                } ?: run {
                    internalDataFile = ""
                }
            } ?: run {
                internalDataFile = ""
            }


            when (itemOfEntryData.mode) {
                "text" -> {
                    listOfEntriesData.add(
                        EntriesModelData(
                            "",
                            emptyLegacyId,
                            "",
                            "",
                            itemOfEntryData.mode!!,
                            itemOfEntryData.title,
                            itemOfEntryData.content,
                            image,
                            itemOfEntryData.html,
                            itemOfEntryData.embedSource,
                            internalDataFile,
                            itemOfEntryData.embedArticle,
                            source!!,
                            postDelayed!!
                        )
                    )
                }
                "image" -> {
                    listOfEntriesData.add(
                        EntriesModelData(
                            "",
                            emptyLegacyId,
                            "",
                            "",
                            itemOfEntryData.mode!!,
                            itemOfEntryData.title,
                            itemOfEntryData.content,
                            image,
                            itemOfEntryData.html,
                            itemOfEntryData.embedSource,
                            internalDataFile,
                            itemOfEntryData.embedArticle,
                            source!!,
                            postDelayed!!
                        )
                    )
                }
                "video" -> {
                    listOfEntriesData.add(
                        EntriesModelData(
                            "",
                            emptyLegacyId,
                            "",
                            "",
                            itemOfEntryData.mode!!,
                            itemOfEntryData.title,
                            itemOfEntryData.content,
                            image,
                            itemOfEntryData.html,
                            itemOfEntryData.embedSource,
                            internalDataFile,
                            itemOfEntryData.embedArticle,
                            source!!,
                            postDelayed!!
                        )
                    )
                }
                /*"html" -> {
                    itemOfEntryData.html?.let {
                        if(itemOfEntryData.html?.contains("UA-26809107-1")){
                            listOfEntriesData.add(
                                EntriesModelData(
                                    "",
                                    emptyLegacyId,
                                    "",
                                    "",
                                    itemOfEntryData.mode,
                                    itemOfEntryData.title,
                                    itemOfEntryData.content,
                                    itemOfEntryData.image?.url,
                                    itemOfEntryData.html,
                                    itemOfEntryData.embedSource,
                                    itemOfEntryData.internaldata?.file,
                                    itemOfEntryData.embedArticle,
                                    source!!,
                                    postDelayed!!
                                )
                            )
                        }
                    }?: run{

                    }

                }*/
                "embed" -> {
                    listOfEntriesData.add(
                        EntriesModelData(
                            "",
                            emptyLegacyId,
                            "",
                            "",
                            itemOfEntryData.mode!!,
                            itemOfEntryData.title,
                            itemOfEntryData.content,
                            image,
                            itemOfEntryData.html,
                            itemOfEntryData.embedSource,
                            internalDataFile,
                            itemOfEntryData.embedArticle,
                            source!!,
                            postDelayed!!
                        )
                    )
                }
                "oio-embed" -> {
                    listOfEntriesData.add(
                        EntriesModelData(
                            itemOfEntryData.embedArticle?.id!!,
                            itemOfEntryData.embedArticle?.legacyId!!,
                            categoryName,
                            categoryId,
                            itemOfEntryData.mode!!,
                            itemOfEntryData.title,
                            itemOfEntryData.content,
                            image,
                            itemOfEntryData.html,
                            itemOfEntryData.embedSource,
                            internalDataFile,
                            itemOfEntryData.embedArticle,
                            source!!,
                            postDelayed!!
                        )
                    )
                }
            }
        }

        if (recommendedArticleFeed.size != 0) {
            var random = (0 until recommendedArticleFeed.size).random()
            var itemOfRecommendArticle: FeedArticleModel = recommendedArticleFeed[random]

            if (recommendedArticleFeed[random].legacyId == legacyId) {
                if (random == recommendedArticleFeed.size - 1)
                    random = recommendedArticleFeed.size - 2
                else if (random == 0)
                    random = 1
                itemOfRecommendArticle = recommendedArticleFeed[random]
            }

            val selectedRecommendArticle = getRecommendEntryData(itemOfRecommendArticle)

            if (listOfEntriesData.size > 3)
                listOfEntriesData.add(3, selectedRecommendArticle)
            else
                listOfEntriesData.add(selectedRecommendArticle)
        }

        return listOfEntriesData
    }

    private fun getRecommendEntryData(itemOfRecommendArticle: FeedArticleModel): EntriesModelData {
        var id: String? = ""
        itemOfRecommendArticle.id?.let {
            id = it
        } ?: run {
            id = ""
        }

        var legacyId: Long? = 0
        itemOfRecommendArticle.legacyId?.let {
            legacyId = it
        } ?: run {
            legacyId = 0
        }

        var categoryUrl: String? = ""
        var categoryId: String? = ""
        var categoryName: String? = ""

        if (itemOfRecommendArticle.categories?.size != 0) {
            itemOfRecommendArticle.categories?.let { it1 ->
                categoryUrl = it1[0].icons?.png!!
                categoryId = it1[0].id!!
                categoryName = it1[0].name!!
            } ?: run {
                categoryUrl = ""
                categoryId = ""
                categoryName = ""
            }
        } else {
            categoryUrl = ""
            categoryId = ""
            categoryName = ""
        }

        var title: String? = ""
        itemOfRecommendArticle.title?.let {
            title = it
        } ?: run {
            title = ""
        }

        var image: String? = ""
        itemOfRecommendArticle.image?.let {
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

        return EntriesModelData(
            id!!,
            legacyId!!,
            categoryName!!,
            categoryId!!,
            "recommendWidget",
            title!!,
            "",
            image,
            "",
            "",
            "",
            null,
            "",
            false
        )
    }

    private fun setEmojiReactionsData2Adapter(emojiReactions: MutableList<ArticleReactionsModel>) {
        val emojiReactionArr = prepareEmojiReactionArr(emojiReactions)

        val sEmojiClickedData = StringSharedPrefs(
            sharedPrefs,
            OnedioConstant.EMOJI_REACTION_CLICKED_EMOJI_CODES,
            ""
        ).getValue<String>()
        val mEmojiClickedData = OnedioCommon.convertString2Map(sEmojiClickedData)

        val mArrEmojiClickedDataItem: MutableList<String>? =
            mEmojiClickedData[legacyId.toString()]


        viewP.emojiReactionsRecyclerView.layoutManager =
            LinearLayoutManager(
                mActivity?.applicationContext,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        viewP.emojiReactionsRecyclerView.adapter =
            EmojiReactionRecyclerViewAdapter(
                mActivity?.applicationContext!!,
                mArrEmojiClickedDataItem!!,
                emojiReactionArr
            ) { position, view, firstHeight, emojiItemCount, tvEmojiItemCount ->

                val emojiCode = emojiReactionArr[position].code

                if (!checkMaxEmojiReactionByUser(legacyId.toString()) || findEmojiCode(
                        legacyId.toString(),
                        emojiCode
                    ) == 1
                ) {

                    when (checkedEmojiIsClicked(legacyId.toString(), emojiCode)) {
                        1 -> {

                            articleDetailActivirtyPresenterImpl.downReactionNew(
                                legacyId!!,
                                emojiCode,
                                OnedioConstant.DOWN_REACTION + "$1",
                                view,
                                firstHeight,
                                emojiItemCount,
                                tvEmojiItemCount
                            )
                        }
                        0 -> {

                            articleDetailActivirtyPresenterImpl.upReactionNew(
                                legacyId!!,
                                emojiCode,
                                OnedioConstant.UP_REACTION + "$0",
                                view,
                                firstHeight,
                                emojiItemCount,
                                tvEmojiItemCount
                            )
                        }
                    }
                } else
                    Toast.makeText(
                        mActivity?.applicationContext,
                        "En fazla 3 emoji ile tepki verebilirsiniz..!",
                        Toast.LENGTH_LONG
                    ).show()

            }
    }

    private fun clickCategoryIcon(articleFeedDetailsModel: ArticleFeedDetailsModel) {

        articleFeedDetailsModel.categories?.let { itemOfCategories ->
            if (itemOfCategories.size != 0) {
                itemOfCategories[0].id?.let {
                    categoryId = itemOfCategories[0].id!!
                    categoryName = itemOfCategories[0].name!!

                    if (viewP.articleDetailAction.visibility == View.VISIBLE) {
                        viewP.articleDetailAction.setOnClickListener {

                            val categoryModel =
                                CategoryModel(
                                    categoryId,
                                    null,
                                    categoryName
                                )

                            when (categoryId) {
                                OnedioConstant.CATEGORIES_YEMEK_ID -> {
                                    //OnedioSingletonPattern.instance.setCategoryId(categoryModel)
                                    OnedioSingletonPattern.instance.setActivity(
                                        ArticleDetailActivityViewImpl::class.java
                                    )
                                    /*OnedioCommon.cStartActivity(
                                        applicationContext!!,
                                        FoodActivityViewImpl::class.java
                                    )*/

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
                                    //OnedioSingletonPattern.instance.setCategoryId(categoryModel)
                                    OnedioSingletonPattern.instance.setTabIndex(3)
                                    OnedioSingletonPattern.instance.setActivity(
                                        ArticleDetailActivityViewImpl::class.java
                                    )
                                    /*OnedioCommon.cStartActivity(
                                        applicationContext!!,
                                        VideoActivityViewImpl::class.java
                                    )*/

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
                                    //OnedioSingletonPattern.instance.setCategoryId(categoryModel)
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

                                    /*OnedioCommon.cStartActivity(
                                        applicationContext!!,
                                        CategoryFeedActivityViewImpl::class.java
                                    )*/
                                    startAnim()
                                }
                            }
                        }
                    }
                } ?: run {

                }
            } else {

            }
        } ?: run {

        }
    }

    private fun articleDetailCommentsRecyclerViewConfig() {
        viewP.articleDetailCommentsRecyclerView.setHasFixedSize(true)
        viewP.articleDetailCommentsRecyclerView.layoutManager =
            LinearLayoutManager(mActivity?.applicationContext)
    }

    private fun setArticleDetailCommentData(arrArticleOfCommentsData: MutableList<ArticleTopCommentModel>) {
        setArticleDetailCommentDataAdapter(addAllCommentsData(arrArticleOfCommentsData))
    }

    private fun setArticleDetailCommentDataAdapter(commentData: MutableList<CommentModel>) {
        mAdapter2Comment =
            ArticleDetailCommentRecyclerViewAdapter(
                mActivity?.applicationContext!!,
                commentData
            ) { itemOfCommentModel, type, likeCount, likeIcon ->
                when (type) {
                    "LIKE" -> {
                        likeComment(
                            itemOfCommentModel,
                            likeCount,
                            likeIcon
                        )
                    }
                    "REPLY" -> {
                        val articleItem =
                            HugeCardModel(
                                articleId,
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
                                false,
                                null,
                                null,
                                false,
                                ""
                            )
                        /*OnedioSingletonPattern.instance.setArticleItem(
                            articleItem
                        )*/
                        OnedioSingletonPattern.instance.setDeleteComment(
                            false
                        )
                        OnedioSingletonPattern.instance.setCommentModel(
                            itemOfCommentModel
                        )
                        OnedioSingletonPattern.instance.setActivity(
                            ArticleDetailActivityViewImpl::class.java
                        )

                        val intent =
                            Intent(mActivity, ArticleCommentActivityViewImpl::class.java)
                        intent.putExtra("ARTICLE_COMMENT_DATA", Gson().toJson(articleItem))
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)

                        /*OnedioCommon.cStartActivity(
                            applicationContext!!,
                            ArticleCommentActivityViewImpl::class.java
                        )*/
                        startAnim()

                    }
                    "PROFILE" -> {
                        /*OnedioSingletonPattern.instance.setAnotherUserId(
                            itemOfCommentModel.userId
                        )
                        OnedioSingletonPattern.instance.setIsUserOwnProfile(
                            false
                        )
                        startActivity(
                            Intent(
                                applicationContext,
                                ProfileActivityViewImpl::class.java
                            )
                        )*/
                        val intent = Intent(mActivity, ProfileActivityViewImpl::class.java)
                        intent.putExtra("IS_USER_OWN_PROFILE", false)
                        intent.putExtra("ANOTHER_USER_PROFILE", itemOfCommentModel.userId)
                        startActivity(intent)
                    }
                }
            }

        viewP.articleDetailCommentsRecyclerView.adapter = mAdapter2Comment
    }

    @SuppressLint("SetTextI18n")
    private fun addAllCommentsData(arrOfArticleDetailComment: MutableList<ArticleTopCommentModel>): MutableList<CommentModel> {

        val commentArr: MutableList<CommentModel> = mutableListOf()

        viewP.seeAllComments.text = "Tüm Yorumları Gör ($count)"


        listOfLikedCommentId = OnedioCommon.convertString2ListStr(
            StringSharedPrefs(
                sharedPrefs,
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

    override fun handleAddFavoriteDataModel(
        response4AddFavoriteArticle: Response4AddFavoriteArticle
    ) {
        articleItemData.isUserFavorited = true
        isAddedFavorite = true
        //addFavorite.setImageResource(R.drawable.ic_added_favorite)
        mActivity?.invalidateOptionsMenu()

        listOfFavoritesArticleData = OnedioCommon.convertString2List(
            StringSharedPrefs(
                sharedPrefs,
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
            sharedPrefs,
            OnedioConstant.SHARED_PREF_FAVORITE_DATA,
            " "
        ).setValue(OnedioCommon.convertList2String(listOfFavoritesArticleData))

    }

    override fun handleDeleteFavoriteDataModel(
        response4AddFavoriteArticle: Response4AddFavoriteArticle
    ) {
        articleItemData.isUserFavorited = false
        isAddedFavorite = false
        mActivity?.invalidateOptionsMenu()

        listOfFavoritesArticleData = OnedioCommon.convertString2List(
            StringSharedPrefs(
                sharedPrefs,
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
            sharedPrefs,
            OnedioConstant.SHARED_PREF_FAVORITE_DATA,
            " "
        ).setValue(OnedioCommon.convertList2String(listOfFavoritesArticleData))

        //addFavorite.setImageResource(R.drawable.ic_delete_favorited)
    }

    override fun handleLikeComment(
        response4LikeAndUnLike: Response4LikeAndUnLike,
        itemOfComment: CommentModel,
        likeCountView: TextView
    ) {
        //var cl = itemOfComment.commentLikeCount.toInt()
        var cl = likeCountView.text.toString().toInt()
        cl++
        likeCountView.text = cl.toString()
        itemOfComment.commentLikeCount = cl.toString()
        Toast.makeText(mActivity?.applicationContext, "Beğenildi", Toast.LENGTH_SHORT).show()

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
        Toast.makeText(mActivity?.applicationContext, "Beğeni geri alındı", Toast.LENGTH_SHORT)
            .show()

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

    override fun showLoading() {
        //viewP.articleFeedDetailAvlIndicatorProgress2.smoothToShow()
        viewP.lottieProgress.playAnimation()
        viewP.lottieProgress.visibility = View.VISIBLE
    }

    private fun getModEmojiReactionCount(count: Int): Int {
        return when (count) {
            0 -> 0
            in 0..100 -> 5
            else -> 5 + (count / 100) * 2
        }
    }

    private fun likeComment(
        itemOfComment: CommentModel,
        likeCountView: TextView,
        likeIconView: ImageView
    ) {

        if (itemOfComment.isCommentLiked) {
            likeIconView.setImageResource(R.drawable.ic_like)
            articleDetailActivirtyPresenterImpl.unLike(
                itemOfComment,
                likeCountView
            )
        } else {
            likeIconView.setImageResource(R.drawable.ic_like_blue)
            articleDetailActivirtyPresenterImpl.likeComment(
                itemOfComment,
                likeCountView
            )
        }
    }

    private fun prepareEmojiReactionArr(emojiReactions: MutableList<ArticleReactionsModel>): MutableList<EmojiReactionModel> {
        val emojiReactionArr: MutableList<EmojiReactionModel> = mutableListOf()

        val sortedEmojiReactionArr =
            emojiReactions.sortedWith(compareBy { it.count }).reversed()

        sortedEmojiReactionArr.forEach { itemOfEmojiReaction ->
            emojiReactionArr.add(
                EmojiReactionModel(
                    itemOfEmojiReaction.count!!,
                    itemOfEmojiReaction.icons?.png!!,
                    itemOfEmojiReaction.id!!
                )
            )
        }

        return emojiReactionArr
    }

    private fun checkedEmojiIsClicked(legacyId: String, emojiCode: String): Int {
        val sEmojiClickedData = StringSharedPrefs(
            sharedPrefs,
            OnedioConstant.EMOJI_REACTION_CLICKED_EMOJI_CODES,
            ""
        ).getValue<String>()

        return findEmojiCode(legacyId, emojiCode)

    }

    private fun checkMaxEmojiReactionByUser(legacyId: String): Boolean {
        val sEmojiClickedData = StringSharedPrefs(
            sharedPrefs,
            OnedioConstant.EMOJI_REACTION_CLICKED_EMOJI_CODES,
            ""
        ).getValue<String>()

        val mEmojiClickedData = OnedioCommon.convertString2Map(sEmojiClickedData)
        mEmojiClickedData[legacyId]?.remove("")
        if (mEmojiClickedData[legacyId]?.size!! < 3)
            return false
        return true

    }

    private fun findEmojiCode(legacyId: String, emojiCode: String): Int {
        val sEmojiClickedData = StringSharedPrefs(
            sharedPrefs,
            OnedioConstant.EMOJI_REACTION_CLICKED_EMOJI_CODES,
            ""
        ).getValue<String>()

        val mEmojiClicked = OnedioCommon.convertString2Map(sEmojiClickedData).toMutableMap()
        mEmojiClicked[legacyId]?.remove("")
        mEmojiClicked.forEach { (key, value) ->
            if (key == legacyId) {
                value.find { it == emojiCode }?.let {
                    return 1
                } ?: run {
                    return 0
                }
            }
        }

        return 0
    }

    private fun checkArticleIdIsExist(legacyId: String) {
        val sEmojiClickedData = StringSharedPrefs(
            sharedPrefs,
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
            sharedPrefs,
            OnedioConstant.EMOJI_REACTION_CLICKED_EMOJI_CODES,
            ""
        ).setValue(OnedioCommon.convertMap2String(newMapOfEmojiReactionSharedPrefs))
    }

    private fun clickSeeAllComment() {
        viewP.seeAllComments.setOnClickListener {
            OnedioSingletonPattern.instance.setActivity(ArticleDetailActivityViewImpl::class.java)
            val intent =
                Intent(
                    mActivity,
                    ArticleCommentActivityViewImpl::class.java
                )
            intent.putExtra(
                "ARTICLE_COMMENT_DATA", Gson().toJson(
                    HugeCardModel(
                        "",
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
                        isAddedFavorite,
                        null,
                        null,
                        false,
                        ""

                    )
                )
            )
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)

            //startActivity(Intent(applicationContext, ArticleCommentActivityViewImpl::class.java))
        }

        viewP.writeCommentArea.setOnClickListener {

            OnedioSingletonPattern.instance.setDeleteComment(false)
            OnedioSingletonPattern.instance.setActivity(ArticleDetailActivityViewImpl::class.java)

            val intent =
                Intent(
                    mActivity,
                    ArticleCommentActivityViewImpl::class.java
                )
            intent.putExtra(
                "ARTICLE_COMMENT_DATA", Gson().toJson(
                    HugeCardModel(
                        "",
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
                        isAddedFavorite,
                        null,
                        null,
                        false,
                        ""

                    )
                )
            )
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            startAnim()

            //startActivity(Intent(applicationContext, ArticleCommentActivityViewImpl::class.java))
            //startAnim()


            /*OnedioSingletonPattern.instance.setActivity(ArticleDetailActivityViewImpl::class.java)
            startActivity(Intent(applicationContext, ArticleCommentActivityViewImpl::class.java))*/
        }
    }

    private fun isVisible(view: View?): Boolean {
        if (view == null) {
            return false
        }
        if (!view.isShown) {
            return false
        }

        val displaymetrics = DisplayMetrics()
        mActivity?.windowManager?.defaultDisplay?.getMetrics(displaymetrics)
        val height = displaymetrics.heightPixels
        val width = displaymetrics.widthPixels

        val actualPosition = Rect()
        view.getGlobalVisibleRect(actualPosition)
        val screen = Rect(0, 0, width, height)
        return actualPosition.intersect(screen)
    }

    private fun setTaboolaWidget(articleTitle: String, legacyId: String) {
        val publisherInfo = PublisherInfo("onedio-app-android")
        Taboola.init(publisherInfo)

        val pageUrl = convertPageUrl(articleTitle, legacyId)

        viewP.taboolaWidget.pageUrl = pageUrl
        viewP.taboolaWidget.layoutParams.height =
            SdkDetailsHelper.getDisplayHeight(mActivity?.applicationContext) * 2
        viewP.taboolaWidget.setInterceptScroll(true)
        viewP.taboolaWidget.setProgressBarEnabled(true)
        viewP.taboolaWidget.setProgressBarDuration(1f)
        viewP.taboolaWidget.setProgressBarColor(Color.MAGENTA)
        viewP.taboolaWidget.fetchContent()

        viewP.taboolaWidget.taboolaEventListener = this

    }

    private fun convertPageUrl(articleTitle: String, legacyId: String): String {
        var newUrl = articleTitle

        newUrl = OnedioCommon.convertString4TaboolaUrl(newUrl)
        return "https://onedio.com/$newUrl-$legacyId"

    }

    private fun calculateViewCount(strOfViewCount: String): String =
        when {
            strOfViewCount.length in 4..6 -> strOfViewCount.substring(
                0,
                strOfViewCount.length - 3
            ) + "b"
            strOfViewCount.length in 7..9 -> strOfViewCount.substring(
                0,
                strOfViewCount.length - 6
            ) + "m"
            else -> strOfViewCount
        }

    private fun loadImage(
        imageUrl: String,
        imageView: ImageView,
        progressBar: ProgressBar
    ) {
        if (imageUrl.contains("gif", ignoreCase = true))
            loadImageWithGlide(imageUrl, imageView, progressBar)
        else
            loadImageWithPicasso(imageUrl, imageView, progressBar)
    }

    private fun loadImageWithGlide(
        imageUrl: String,
        imageView: ImageView,
        progressBar: ProgressBar
    ) {
        Glide.with(mActivity!!)
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
                    progressBar.visibility = View.GONE
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
                    progressBar.visibility = View.GONE
                    return false
                }
            })
            .into(imageView)
    }

    override fun loadImageWithPicasso(
        imageUrl: String,
        imageView: ImageView,
        progressBar: ProgressBar
    ) {
        Picasso.get().load(imageUrl)
            .into(imageView, object : Callback {
                override fun onSuccess() {
                    progressBar.visibility = View.INVISIBLE
                }

                override fun onError(e: Exception?) {
                    progressBar.visibility = View.INVISIBLE
                    imageView.setImageResource(R.drawable.empty_avatar)
                }
            })
    }

    private fun loadImageWithoutPicasso(
        imageUrl: String,
        imageView: ImageView
    ) {
        Picasso.get().load(imageUrl)
            .into(imageView, object : Callback {
                override fun onSuccess() {

                }

                override fun onError(e: Exception?) {
                    imageView.setImageResource(R.drawable.image_error_dark_mode)
                }
            })
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

    override fun onFailMethod(response4ReadState: String) {

    }

    override fun handleSendRead(response4ReadState: String) {

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

    /*override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater){
        inflater.inflate(R.menu.toolbar_menu, menu)

        if (isAddedFavorite)
            menu.removeItem(R.id.actionAddFavorite)
        else
            menu.removeItem(R.id.actionDeleteFavorite)

        super.onCreateOptionsMenu(menu, inflater)
    }*/

    /*override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        //menuItem.setIcon(R.drawable.ic_back)

        //menu.findItem(R.id.actionDeleteFavorite).setIcon(R.drawable.ic_added_favorite)

        if (isAddedFavorite)
            menu.findItem(R.id.actionDeleteFavorite).setIcon(R.drawable.ic_added_favorite)
        else
            menu.findItem(R.id.actionAddFavorite).setIcon(R.drawable.ic_delete_favorited)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                JzvdStd.releaseAllVideos()
                viewP.taboolaWidget.reset()
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
    }*/

    override fun openVisibilityOfLayout() {
        viewP.editorUserProfileConstraintLayout.visibility = View.VISIBLE
        //viewP.readableCountConst.visibility = View.VISIBLE
        viewP.widthLine.visibility = View.VISIBLE
        viewP.infoOnedioUyeleri.visibility = View.VISIBLE
        viewP.viewLineComment.visibility = View.VISIBLE
        viewP.infoEmojiReaction.visibility = View.VISIBLE
        viewP.emojiViewLine.visibility = View.VISIBLE
        viewP.viewLineBenzerIcerikler.visibility = View.VISIBLE
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

    override fun taboolaViewItemClickHandler(p0: String?, p1: Boolean): Boolean {

        if (p1) {
            p0?.let {
                val url = it.split("-")
                val lastItem = url[url.size - 1]
                val legacyId = lastItem.split("?")[0]

                getRecommendWidgetData(legacyId.toLong(), true)
                //getArticleFeedDetail(legacyId.toLong(), true)

                return false

            } ?: run {
                return true
            }
        }

        return true
    }

    override fun taboolaViewResizeHandler(p0: TaboolaWidget?, p1: Int) {

    }

    private fun startAnim() {
        OnedioCustomIntent.startAnim(mActivity!!, "l2r")
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

    override fun hideLoading() {
        //viewP.articleFeedDetailAvlIndicatorProgress2.smoothToHide()
        viewP.lottieProgress.cancelAnimation()
        viewP.lottieProgress.visibility = View.GONE
    }


    override fun showError(response4ErrorObj: Response4ErrorObj) {
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

    private fun showInAppReview() {
        val articleOpenCount = IntSharedPrefs(
            sharedPrefs,
            OnedioConstant.SHARED_PREF_ARTICLE_OPEN_COUNT,
            0
        ).getValue<Int>()

        val inAppReviewPopupShow = BooleanSharedPrefs(
            sharedPrefs,
            OnedioConstant.SHARED_PREF_IN_APP_REVIEW_POPUP_SHOW,
            true
        ).getValue<Boolean>()

        if (articleOpenCount == 4 && inAppReviewPopupShow) {
            showUpdateDialogIfNeeded("Uygulamamızı beğendiysen vereceğin 5 yıldıza talibiz", false)

            BooleanSharedPrefs(
                sharedPrefs,
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

    override fun showEzDialogMessage(
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
            mActivity?.onBackPressed()
        }
    }


    override fun onResume() {
        super.onResume()
        viewP.publisherAdViewTop.resume()
        viewP.publisherAdViewBottom.resume()
        listOfLikedCommentId = OnedioCommon.convertString2ListStr(
            StringSharedPrefs(
                sharedPrefs,
                OnedioConstant.SHARED_PREF_LIKE_COMMENT_DATA,
                OnedioCommon.convertList2StringStr(
                    listOfLikedCommentId
                )
            ).getValue()
        )

        mAdapter2Comment?.let {
            val recyclerData = it.getAllData()

            for (i in 0 until recyclerData.size) {
                listOfLikedCommentId.forEach { itemOfLikedCommentData ->
                    if (recyclerData[i].commentId == itemOfLikedCommentData) {
                        recyclerData[i].isCommentLiked = true
                        viewP.articleDetailCommentsRecyclerView.adapter!!.notifyItemChanged(i)
                    }
                }
            }
        } ?: run {

        }
    }

    override fun onPause() {
        super.onPause()
        JzvdStd.releaseAllVideos()

        if (::viewP.isInitialized) {
            //viewP.taboolaWidget.reset()
            viewP.publisherAdViewTop.pause()
            viewP.publisherAdViewBottom.pause()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        JzvdStd.releaseAllVideos()

        if (::viewP.isInitialized) {
            //viewP.taboolaWidget.reset()
            viewP.publisherAdViewTop.destroy()
            viewP.publisherAdViewBottom.destroy()
        }
    }
}