package com.onedio.androidside.mvp.view.dashboard.homeFragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.Rect
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.models.SlideModel
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.gson.Gson
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.common.OnedioCustomIntent
import com.onedio.androidside.common.ScrollableGridView
import com.onedio.androidside.common.customSlider.SliderWithAnimAdapter
import com.onedio.androidside.common.model.OnedioEzDialogMessageModel4Fragment
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.categoryFragment.categoryModel.CategoryModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.HomeFeedFragmentActivityModelImpl
import com.onedio.androidside.mvp.model.dashboard.homeFragment.requestModel.WidgetReqModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.addFavorite.Response4AddFavoriteArticle
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.feedModel.Response4FeedModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.feedModel.TopComments
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.feedModel.UserComment
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel.Response4ArticlesFeed
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.sliderModel.FeedArticleModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.sliderModel.Response4ArticleSlider
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.widgetConfig.Response4WidgetConfig
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.widgetConfig.WidgetsData
import com.onedio.androidside.mvp.model.welcomeDashboard.search.dataModel.tags.Tags
import com.onedio.androidside.mvp.presenter.dashboard.homeFragment.HomeFeedFragmentActivityViewPresenterImpl
import com.onedio.androidside.mvp.view.dashboard.DashboardActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.CategoryFeedActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.food.FoodActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.video.VideoActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleComment.ArticleCommentActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.ArticleDetailActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.FavoriteDataInfo
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.TestArticleDetailActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.swipableArticleDetail.SwipableArticleDetailActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.swipableArticleDetail.model.SwipableArticleDetailObj
import com.onedio.androidside.mvp.view.dashboard.homeFragment.sliderListAdapter.HugeCardRecyclerViewAdapter4Slider
import com.onedio.androidside.mvp.view.dashboard.homeFragment.sliderListAdapter.SecondSliderHugeCard
import com.onedio.androidside.mvp.view.dashboard.homeFragment.widgets.editorChoosingWidgetGirdAdapter.EditorChoosingWidgetGridViewAdapter
import com.onedio.androidside.mvp.view.dashboard.homeFragment.widgets.editorChoosingWidgetGirdAdapter.model.EditorChoosingWidgetGridModel
import com.onedio.androidside.mvp.view.dashboard.homeFragment.widgets.fillRootXMLs.FillRootXMLs
import com.onedio.androidside.mvp.view.dashboard.homeFragment.widgets.fillRootXMLs.model.WidgetLayoutModel
import com.onedio.androidside.mvp.view.dashboard.homeFragment.widgets.hugeCardRecyclerViewAdapter.HugeCardRecyclerViewAdapter
import com.onedio.androidside.mvp.view.dashboard.homeFragment.widgets.hugeCardRecyclerViewAdapter.model.HugeCardModel
import com.onedio.androidside.mvp.view.dashboard.homeFragment.widgets.sportsWidgetRecyclerListAdapter.SportsRecyclerListAdapter
import com.onedio.androidside.mvp.view.dashboard.homeFragment.widgets.testWidgetRecyclerListAdapter.TestRecyclerListAdapter
import com.onedio.androidside.mvp.view.dashboard.homeFragment.widgets.testWidgetRecyclerListAdapter.model.TestWidgetModel
import com.onedio.androidside.mvp.view.dashboard.search.moreTag.tagsArticle.TagsArticleActivityViewImpl
import com.onedio.androidside.prefs.StringSharedPrefs
import com.onedio.androidside.singleton.OnedioSingletonPattern
import com.smarteist.autoimageslider.IndicatorAnimations
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.yandex.metrica.YandexMetrica
import kotlinx.android.synthetic.main.fragment_feed_home.view.*
import kotlinx.android.synthetic.main.widget_editor_choosing.view.*
import kotlinx.android.synthetic.main.widget_huge_card_with_popular_comment.view.*
import kotlinx.android.synthetic.main.widget_infinite.view.*
import kotlinx.android.synthetic.main.widget_second_slider.view.*
import kotlinx.android.synthetic.main.widget_slider.view.*
import kotlinx.android.synthetic.main.widget_sports.view.*
import kotlinx.android.synthetic.main.widget_tests.view.*
import kotlinx.android.synthetic.main.widget_videos.view.*
import kotlin.collections.component1
import kotlin.collections.component2
import kotlin.collections.set
import kotlin.system.exitProcess


class HomeFeedFragmentActivityViewImpl : Fragment(),
    IHomeFeedFragmentActivityView {

    private var sliderAdapter: SliderWithAnimAdapter? = null
    private var secondSliderAdapter: SliderWithAnimAdapter? = null

    private lateinit var viewP: View

    private var sliderList: MutableList<SlideModel> = mutableListOf()
    private var sSliderList: MutableList<HugeCardModel> = mutableListOf()

    private lateinit var secondSliderList: MutableList<SlideModel>
    private var secondSSliderList: MutableList<HugeCardModel> = mutableListOf()
    /*private var gridData4EditorChoosing: MutableList<EditorChoosingWidgetGridModel> =
        mutableListOf()*/

    private var gridData4EditorChoosing: HashMap<String, MutableList<EditorChoosingWidgetGridModel>> =
        HashMap()

    private var sportWidgetData: MutableList<TestWidgetModel> = mutableListOf()

    //private var testWidgetData: MutableList<TestWidgetModel> = mutableListOf()
    private var testWidgetData: HashMap<String, MutableList<TestWidgetModel>> = HashMap()

    private lateinit var homeFeedFragmentActivityViewPresenter: HomeFeedFragmentActivityViewPresenterImpl

    private var arrOfWidgetReqModel: MutableList<WidgetReqModel> = mutableListOf()

    private var page = 1
    private var isFirst4TestWidget = true
    private var isFirstLoad = false

    private lateinit var infiniteRecyclerViewAdapter: HugeCardRecyclerViewAdapter
    private lateinit var arrayList: MutableList<HugeCardModel>

    private var activityP: FragmentActivity? = null

    private lateinit var widgetResponse: Response4WidgetConfig

    private var isMoreContentEditorChoosing: Boolean = false
    private var isMoreContentTest: Boolean = false
    private var isMoreContentSports: Boolean = false

    private var isSkeletonRunning = false

    private var bbbb = 0

    private var isMoreTagData: Boolean = false
    private var isMoreCategoryData: Boolean = false

    private lateinit var sharedPrefs: SharedPreferences
    private var listOfRecyclerView: MutableList<RecyclerView> = mutableListOf()
    private var listOfFavoritesArticleData: MutableList<FavoriteDataInfo> = mutableListOf()

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    private var xmlViewsMap: HashMap<String, WidgetLayoutModel> = HashMap()

    private var isScreenLoadMultiple: Boolean = false

    private var allArticleFeedList: MutableList<HugeCardModel> = mutableListOf()

    private var currWidgetShowCount = 0

    private var allArticleFeedCount = 0
    private var articleCount4Infinite = 0

    private var isNotif: Boolean = false

    private var isShowSecondSlider = false


    //var allSliderData: MutableList<HugeCardModel> = mutableListOf()

    companion object {
        fun newInstance(isNotif: Boolean): HomeFeedFragmentActivityViewImpl {
            val fragmentFeedHome =
                HomeFeedFragmentActivityViewImpl()
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

        activityP = activity

        setEmojiProvider()


        viewP = inflater.inflate(R.layout.fragment_feed_home, null)

        swiperRefresh()

        OnedioSingletonPattern.instance.setNestedScrollView(viewP.nestedScrollViewHomeFeed)

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
            activityP?.getSharedPreferences(
                OnedioConstant.SHARED_PREF_FILE_NAME,
                Context.MODE_PRIVATE
            )!!

        savedInstanceState?.let {
            isScreenLoadMultiple = true
        } ?: run {
            isScreenLoadMultiple = false
        }

        if (savedInstanceState == null) {
            setWidgetPlace()
        }
    }

    private fun swiperRefresh() {
        viewP.swiperefreshHomeFeed.setOnRefreshListener {

            allArticleFeedList = mutableListOf()
            allArticleFeedCount = 0
            articleCount4Infinite = 0

            isMoreContentEditorChoosing = true
            isMoreContentSports = true
            isMoreContentTest = true

            isMoreTagData = false

            isSkeletonRunning = false

            isFirstLoad = false

            page = 1

            currWidgetShowCount = 0

            gridData4EditorChoosing = HashMap()

            arrOfWidgetReqModel.find { it.pageCount != 1 }?.pageCount = 1

            //val xmlViewsType = OnedioSingletonPattern.instance.getXmlViews()!!
            val xmlViewsType = xmlViewsMap
            xmlViewsType.forEach { (key, value) ->
                val xmlLayout = value.xmlLayout

                if (key.contains(OnedioConstant.WIDGET_MOBILE_EDITOR_CHOOSING)) {

                    val moreGridData4EditorChoosing: TextView =
                        xmlLayout.findViewById(R.id.moreGridData4EditorChoosing)

                    moreGridData4EditorChoosing.visibility = View.INVISIBLE
                }

                if (key.contains(OnedioConstant.WIDGET_MOBILE_TEST)) {
                    val moreListData4Test: TextView =
                        xmlLayout.findViewById(R.id.moreListData4Test)

                    moreListData4Test.visibility = View.INVISIBLE
                }

            }

            /*viewP.moreGridData4EditorChoosing.visibility = View.VISIBLE
            viewP.moreListData4Test.visibility = View.VISIBLE*/

            setWidgetPlace()

            loadMastheadAd()
        }
    }

    private fun getOnedioButtonData() {
        homeFeedFragmentActivityViewPresenter.getArticleFeedCategoryWidgetDataWidthTag4OnedioButton(
            "",
            "5ec7b134c5390705202fcd8b",
            1,
            15
        )
    }

    override fun handleOnedioButtonData(
        wName: String,
        response4ArticlesFeed: Response4ArticlesFeed
    ) {

        StringSharedPrefs(
            sharedPrefs,
            OnedioConstant.SHARED_PREF_ONEDIO_BUTTON,
            ""
        ).setValue(Gson().toJson(response4ArticlesFeed))

        getArticleSliderData()
    }

    //==================================================================================================================
    /**
     * Emoji Provider..
     */
    //==================================================================================================================
    override fun setEmojiProvider() {
        //EmojiManager.install(IosEmojiProvider())
    }

    //==================================================================================================================
    /**
     * Request for Widget Config File..
     */
    //==================================================================================================================
    override fun setWidgetPlace() {
        homeFeedFragmentActivityViewPresenter =
            HomeFeedFragmentActivityViewPresenterImpl(
                HomeFeedFragmentActivityModelImpl(),
                this
            )

        getWidgetConfig()
    }

    //==================================================================================================================
    /**
     * Request for Widget Config File..
     */
    //==================================================================================================================
    override fun getWidgetConfig() {
        homeFeedFragmentActivityViewPresenter.getWidgetConfig()
    }

    override fun handleWidgetConfigData(response4WidgetConfig: Response4WidgetConfig) {
        widgetResponse = response4WidgetConfig

        FillRootXMLs(
            activityP!!,
            viewP,
            widgetResponse,
            viewP.feedLinearLayout,
            xmlViewsMap
        )
            .mapOfWidgetLayoutAttr()
            .fillWidgetXMLs()

        initHomeFeedFragmentComponent()

        //val xmlViewsType = OnedioSingletonPattern.instance.getXmlViews()!!
        val xmlViewsType = xmlViewsMap
        xmlViewsType.forEach { (key, value) ->

            val xmlLayout = value.xmlLayout

            if (key.contains(OnedioConstant.WIDGET_MOBILE_EDITOR_CHOOSING)) {

                val moreGridData4EditorChoosing: TextView =
                    xmlLayout.findViewById(R.id.moreGridData4EditorChoosing)

                val widgetName = OnedioConstant.WIDGET_MOBILE_EDITOR_CHOOSING
                val widgetID = key.split("$")[1]

                clickMoreDataView(
                    moreGridData4EditorChoosing,
                    widgetName,
                    widgetID,
                    "getWidgetDataWithTargetId"
                )
            }

            if (key.contains(OnedioConstant.WIDGET_MOBILE_TEST)) {
                val moreListData4Test: TextView =
                    xmlLayout.findViewById(R.id.moreListData4Test)

                val widgetName = OnedioConstant.WIDGET_MOBILE_TEST
                val widgetID = key.split("$")[1]

                clickMoreDataView(
                    moreListData4Test,
                    widgetName,
                    widgetID,
                    "getCategoryWidgetDataByName"
                )
            }
        }

        getOnedioButtonData()
    }


    override fun getArticleSliderData() {
        homeFeedFragmentActivityViewPresenter.getArticleSliderData()

        if (!isSkeletonRunning) {
            viewP.shimmerViewContainer.visibility = View.VISIBLE
            viewP.shimmerViewContainer.startShimmer()

            viewP.feedLinearLayout.visibility = View.GONE
        }

    }

    override fun handleArticleSliderData(response4ArticleSlider: Response4ArticleSlider) {

        response4ArticleSlider.data?.let {
            it.feed?.let { itemOfSLiderData ->
                if (itemOfSLiderData.size != 0) {
                    allArticleFeedCount += itemOfSLiderData.size
                    prepareSliderListData(
                        0,
                        itemOfSLiderData.size,
                        response4ArticleSlider
                    )
                } else
                    viewP.constraintSlider.visibility = View.GONE

            } ?: run {
                viewP.constraintSlider.visibility = View.GONE
            }
        } ?: run {
            viewP.constraintSlider.visibility = View.GONE
        }


        getArticlesFeed(
            widgetResponse,
            page,
            sumOfWidgetCount(widgetResponse.data?.widgets!!)
        )
    }

    override fun getArticlesFeed(
        response4WidgetConfig: Response4WidgetConfig,
        page: Int,
        perPage: Int
    ) {
        homeFeedFragmentActivityViewPresenter.getArticlesFeed(response4WidgetConfig, page, perPage)
    }

    override fun handleArticlesFeedData(
        response4WidgetConfig: Response4WidgetConfig,
        response4ArticlesFeed: Response4ArticlesFeed
    ) {

        if (response4ArticlesFeed.data?.feed?.size != 0) {
            var beforeWidgetShowCount: Int
            var hugeCardRecyclerViewIdCount = 0

            for (itemOfWidgetConfig in response4WidgetConfig.data?.widgets!!) {
                when (itemOfWidgetConfig.name) {
                    OnedioConstant.WIDGET_MOBILE_BIG_CARD -> {
                        beforeWidgetShowCount = currWidgetShowCount
                        currWidgetShowCount += itemOfWidgetConfig.count!!
                        fillRecyclerViewData(
                            OnedioSingletonPattern.instance.getHugeCardRecyclerViewIds()[hugeCardRecyclerViewIdCount],
                            beforeWidgetShowCount,
                            currWidgetShowCount,
                            response4ArticlesFeed
                        )
                        hugeCardRecyclerViewIdCount++
                    }
                }
            }

            //viewP.sliderView.visibility = View.VISIBLE
            prepareWidgetDataWithTargetId(response4WidgetConfig)
        } else
            showEzDialogMessage(
                "Hata..!",
                "Şu an teknik bir arıza mevcut. Daha sonra tekrar deneyin..",
                "Tamam",
                ContextCompat.getColor(
                    activityP?.applicationContext!!,
                    R.color.tabIndicatorColor4Proile
                ),
                ContextCompat.getColor(activityP?.applicationContext!!, R.color.constWhite),
                ContextCompat.getColor(activityP?.applicationContext!!, R.color.constTextColor),
                ContextCompat.getColor(activityP?.applicationContext!!, R.color.constTextColor)
            )

        /*if (!isSkeletonRunning) {
            viewP.shimmerViewContainer.stopShimmer()
            viewP.shimmerViewContainer.visibility = View.GONE
            isSkeletonRunning = true

            viewP.feedLinearLayout.visibility = View.VISIBLE

        }*/

    }

    //==================================================================================================================
    /**
     * Click More Data View..
     */
    //==================================================================================================================
    override fun clickMoreDataView(
        view: View,
        widgetName: String,
        widgetId: String,
        functionName: String
    ) {
        view.setOnClickListener {

            isMoreContentTest = false
            isMoreContentEditorChoosing = false
            isMoreContentSports = false

            val itemOfConfigData = arrOfWidgetReqModel.filter {
                //widgetType.contains(it.uniqueKey)
                it.uniqueKey.contains(widgetName)
            }

            itemOfConfigData.forEach { childrenOfConfigData ->
                if (childrenOfConfigData.uniqueKey.split("$")[1] == widgetId) {
                    childrenOfConfigData.pageCount += 1

                    when (functionName) {

                        "getWidgetDataWithTargetId" -> {
                            isMoreTagData = true

                            getWidgetDataWithTargetId(
                                childrenOfConfigData.uniqueKey,
                                childrenOfConfigData.targetId,
                                childrenOfConfigData.pageCount,
                                childrenOfConfigData.perPageCount
                            )
                            //viewP.moreGridData4EditorChoosing.visibility = View.INVISIBLE
                            view.visibility = View.INVISIBLE
                        }

                        "getCategoryWidgetDataByName" -> {
                            bbbb = 0

                            isMoreCategoryData = true

                            getCategoryWidgetDataWithTargetId(
                                childrenOfConfigData.uniqueKey,
                                childrenOfConfigData.targetId,
                                childrenOfConfigData.pageCount,
                                childrenOfConfigData.perPageCount
                            )

                            view.visibility = View.INVISIBLE
                        }
                    }

                }
            }
        }
    }

    //==================================================================================================================
    /**
     * Init Component Typeface and Profil Photo..
     */
    //==================================================================================================================
    override fun initHomeFeedFragmentComponent() {
        setComponentTypeFace()
    }

    //==================================================================================================================
    /**
     * Sum Widget Items Count Data..
     */
    //==================================================================================================================
    override fun sumOfWidgetCount(widgets: MutableList<WidgetsData>): Int {
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
     * Prepare WidgetDataWith TargetId..
     */
    //==================================================================================================================
    override fun prepareWidgetDataWithTargetId(response4WidgetConfig: Response4WidgetConfig) {

        val widgets = response4WidgetConfig.data?.widgets
        var wNameInfinite = ""

        widgets!!.forEach { itemOfWidgets ->
            val targetId = itemOfWidgets.targetId
            val count = itemOfWidgets.count


            targetId?.let {
                arrOfWidgetReqModel.add(
                    WidgetReqModel(
                        itemOfWidgets.name!! + "$" + itemOfWidgets.id,
                        targetId,
                        1,
                        itemOfWidgets.count!!
                    )
                )
            }

            count?.let {

            } ?: run {
                wNameInfinite = itemOfWidgets.name + "$" + itemOfWidgets.id
            }

            /*if (wNameInfinite != "")
                getInfiniteRecyclerViewData(
                    wNameInfinite
                )*/
        }

        var index = 0
        widgets.forEach { itemOfWidgets ->
            val type = itemOfWidgets.type

            type?.let {
                when (type) {
                    "tag" -> {
                        getWidgetDataWithTargetId(
                            arrOfWidgetReqModel[index].uniqueKey,
                            arrOfWidgetReqModel[index].targetId,
                            arrOfWidgetReqModel[index].pageCount,
                            arrOfWidgetReqModel[index].perPageCount
                        )
                        index++
                    }
                    "category" -> {
                        getCategoryWidgetDataWithTargetId(
                            arrOfWidgetReqModel[index].uniqueKey,
                            arrOfWidgetReqModel[index].targetId,
                            arrOfWidgetReqModel[index].pageCount,
                            arrOfWidgetReqModel[index].perPageCount
                        )
                        index++
                    }
                    else -> {

                    }
                }
            }
        }

        if (wNameInfinite != "") {
            Handler().postDelayed({
                getInfiniteRecyclerViewData(
                    wNameInfinite
                )
            }, 500)
        }

    }

    //==================================================================================================================
    /**
     * Get Widget Data With TargetId..
     */
    //==================================================================================================================
    override fun getWidgetDataWithTargetId(
        wName: String,
        targetId: String,
        page: Int,
        perPage: Int
    ) {
        //homeFeedFragmentActivityViewPresenter.getWidgetDataWithTagId(wName, targetId, page, perPage)
        homeFeedFragmentActivityViewPresenter.getArticleFeedCategoryWidgetDataWidthTagId(
            wName,
            targetId,
            page,
            perPage
        )
    }

    //==================================================================================================================
    /**
     * Handle Widget Data With TargetId..
     */
    //==================================================================================================================
    override fun handleArticleFeedCategoryWidgetDataWithTagId(
        wName: String,
        response4ArticlesFeed: Response4ArticlesFeed
    ) {
        when (wName.split("$")[0]) {
            OnedioConstant.WIDGET_MOBILE_EDITOR_CHOOSING -> {
                prepareEditorChoosing4GridWidgetData(wName, response4ArticlesFeed)
            }
            OnedioConstant.WIDGET_MOBILE_SLIDER_2 -> {
                allArticleFeedCount += response4ArticlesFeed.data?.feed?.size!!
                prepareSecondSliderData(response4ArticlesFeed)
            }
        }
    }

    private fun prepareSecondSliderData(response4ArticlesFeed: Response4ArticlesFeed) {

        secondSliderList = mutableListOf()
        secondSSliderList = mutableListOf()

        val articlesData = response4ArticlesFeed.data?.feed

        var categoryId: String? = null
        var categoryName: String? = null

        for (i in 0 until articlesData?.size!!) {

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

            secondSliderList.add(
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

            secondSSliderList.add(
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

        val childLayoutManager =
            LinearLayoutManager(activityP?.applicationContext, RecyclerView.HORIZONTAL, false)
        viewP.secondSliderHugeCard.apply {
            layoutManager = childLayoutManager
            adapter = SecondSliderHugeCard(
                activityP?.applicationContext!!,
                secondSliderList
            ) { itemOfSlideModel, index ->
                if (OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility == View.VISIBLE) {
                    OnedioSingletonPattern.instance.getProfileSettingPopup()
                        ?.visibility = View.GONE
                } else {

                    val params = Bundle()
                    firebaseAnalytics.logEvent("slider_2_slide_click", params)

                    val mapOfFeedAppMetrica = HashMap<String, Any>()
                    YandexMetrica.reportEvent("slider_2_slide_click", mapOfFeedAppMetrica)

                    when {
                        itemOfSlideModel.redirectUrl != "" -> {
                            val browserIntent =
                                Intent(Intent.ACTION_VIEW, Uri.parse(itemOfSlideModel.redirectUrl))
                            browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(browserIntent)
                        }
                        else -> {
                            val sSliderObj =
                                SwipableArticleDetailObj(allArticleFeedList, (index + 11))
                            val intent = Intent(
                                activityP?.applicationContext,
                                SwipableArticleDetailActivityViewImpl::class.java
                            )
                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            intent.putExtra("SWIPE_ARTICLE_DATA", Gson().toJson(sSliderObj))
                            intent.putExtra("LEGACY_ID", itemOfSlideModel.sliderItemLegacyId)
                            startActivity(intent)
                        }
                    }

                    startAnim()
                }
            }
        }

        /*viewP.secondSliderListView.setIndicatorAnimation(IndicatorAnimations.THIN_WORM)
        viewP.secondSliderListView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
        viewP.secondSliderListView.autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_RIGHT
        viewP.secondSliderListView.scrollTimeInSec = 1
        viewP.secondSliderListView.indicatorSelectedColor = Color.parseColor("#276cd9")
        viewP.secondSliderListView.indicatorUnselectedColor = Color.parseColor("#ffffff")
        viewP.secondSliderListView.isAutoCycle = false

        secondSliderAdapter = SliderWithAnimAdapter(
            activityP?.applicationContext!!,
            secondSliderList
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
                        startActivity(browserIntent)
                    }
                    else -> {
                        val sSliderObj = SwipableArticleDetailObj(allArticleFeedList, (index + 11))
                        val intent = Intent(
                            activityP?.applicationContext,
                            SwipableArticleDetailActivityViewImpl::class.java
                        )
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        intent.putExtra("SWIPE_ARTICLE_DATA", Gson().toJson(sSliderObj))
                        intent.putExtra("LEGACY_ID", itemOfSlider.sliderItemLegacyId)
                        startActivity(intent)
                    }
                }

                startAnim()
            }
        }

        viewP.secondSliderListView.setSliderAdapter(secondSliderAdapter!!)*/
    }

    //==================================================================================================================
    /**
     * Get Widget Data With CategoryId..
     */
    //==================================================================================================================
    override fun getCategoryWidgetDataWithTargetId(
        wName: String,
        categoryId: String,
        page: Int,
        perPage: Int
    ) {

        homeFeedFragmentActivityViewPresenter.getArticleFeedCategoryWidgetDataWithCategoryId(
            wName,
            categoryId,
            page,
            perPage
        )
    }

    override fun handleArticleFeedCategoryWidgetDataWithCategoryId(
        wName: String,
        response4ArticlesFeed: Response4ArticlesFeed
    ) {

        when (wName.split("$")[0]) {
            OnedioConstant.WIDGET_MOBILE_VIDEOS -> {
                prepareVideo2GridWidgetData(response4ArticlesFeed)
            }
            OnedioConstant.WIDGET_MOBILE_SPORTS -> {
                prepare4SportsWidgetData(response4ArticlesFeed)
            }
            OnedioConstant.WIDGET_MOBILE_TEST -> {
                bbbb++
                prepare4TestWidgetData(wName, response4ArticlesFeed)
            }
        }
    }

    //==================================================================================================================
    /**
     * Prepare Video Widget Data..
     */
    //==================================================================================================================
    @SuppressLint("DefaultLocale", "SetTextI18n")
    override fun prepareVideo2GridWidgetData(response4ArticlesFeed: Response4ArticlesFeed) {

        //viewP.constraintVideo.visibility = View.VISIBLE

        response4ArticlesFeed.data?.let {
            it.feed?.let {
                viewP.constraintVideo.visibility = View.VISIBLE

                val arrOfVideosData = response4ArticlesFeed.data.feed

                var badgeIdHuge: String? = null
                var badgeIconHuge: String? = null
                var badgeNameHuge: String? = null

                arrOfVideosData!![0].badge?.let {
                    if (it.size != 0) {
                        badgeIdHuge = it[0].id
                        badgeIconHuge = it[0].icons?.png
                        badgeNameHuge = it[0].name
                    } else {
                        badgeIdHuge = ""
                        badgeIconHuge = ""
                        badgeNameHuge = ""
                    }
                } ?: run {
                    badgeIdHuge = ""
                    badgeIconHuge = ""
                    badgeNameHuge = ""
                }

                if (badgeIconHuge != "") {
                    viewP.videoHugeCoverPhotoBadgeImage.visibility = View.VISIBLE
                    viewP.videoHugeBadgeLineCovertPhoto.visibility = View.VISIBLE

                    loadImageWithoutProgress(badgeIconHuge!!, viewP.videoHugeCoverPhotoBadgeImage)
                } else {
                    viewP.videoHugeCoverPhotoBadgeImage.visibility = View.INVISIBLE
                    viewP.videoHugeBadgeLineCovertPhoto.visibility = View.INVISIBLE
                }

                var badgeIdChild1: String? = null
                var badgeIconChild1: String? = null
                var badgeNameChild1: String? = null

                arrOfVideosData[1].badge?.let {
                    if (it.size != 0) {
                        badgeIdChild1 = it[0].id
                        badgeIconChild1 = it[0].icons?.png
                        badgeNameChild1 = it[0].name
                    } else {
                        badgeIdChild1 = ""
                        badgeIconChild1 = ""
                        badgeNameChild1 = ""
                    }
                } ?: run {
                    badgeIdChild1 = ""
                    badgeIconChild1 = ""
                    badgeNameChild1 = ""
                }

                if (badgeIconChild1 != "") {
                    viewP.videoChild1BadgeLineCovertPhoto.visibility = View.VISIBLE
                    viewP.videoChild1CoverPhotoBadgeImage.visibility = View.VISIBLE

                    loadImageWithoutProgress(
                        badgeIconChild1!!,
                        viewP.videoChild1CoverPhotoBadgeImage
                    )
                } else {
                    viewP.videoChild1CoverPhotoBadgeImage.visibility = View.INVISIBLE
                    viewP.videoChild1BadgeLineCovertPhoto.visibility = View.INVISIBLE
                }


                var badgeIdChild2: String? = null
                var badgeIconChild2: String? = null
                var badgeNameChild2: String? = null

                arrOfVideosData[2].badge?.let {
                    if (it.size != 0) {
                        badgeIdChild2 = it[0].id
                        badgeIconChild2 = it[0].icons?.png
                        badgeNameChild2 = it[0].name
                    } else {
                        badgeIdChild2 = ""
                        badgeIconChild2 = ""
                        badgeNameChild2 = ""
                    }
                } ?: run {
                    badgeIdChild2 = ""
                    badgeIconChild2 = ""
                    badgeNameChild2 = ""
                }

                if (badgeIconChild2 != "") {
                    viewP.videoChild2BadgeLineCovertPhoto.visibility = View.VISIBLE
                    viewP.videoChild2CoverPhotoBadgeImage.visibility = View.VISIBLE

                    loadImageWithoutProgress(
                        badgeIconChild2!!,
                        viewP.videoChild2CoverPhotoBadgeImage
                    )
                } else {
                    viewP.videoChild2CoverPhotoBadgeImage.visibility = View.INVISIBLE
                    viewP.videoChild2BadgeLineCovertPhoto.visibility = View.INVISIBLE
                }


                loadImage(
                    arrOfVideosData[0].image?.alternates?.standardResolution?.url!!,
                    viewP.videoHugeCoverPhoto,
                    viewP.videoHugeCoverPhotoProgress
                )

                viewP.videoCoverTitle.text = arrOfVideosData[0].title

                loadImage(
                    arrOfVideosData[1].image?.alternates?.standardResolution?.url!!,
                    viewP.videoChild1Image,
                    viewP.videoChild1Progress
                )

                viewP.videoChild1Title.text = arrOfVideosData[1].title


                loadImage(
                    arrOfVideosData[2].image?.alternates?.standardResolution?.url!!,
                    viewP.videoChild2Image,
                    viewP.videoChild2Progress
                )

                viewP.videoChild2Title.text = arrOfVideosData[2].title

                viewP.videoHugeCoverPhotoBadgeImage.setOnClickListener {
                    val itemOfTag =
                        Tags()
                    itemOfTag.id = badgeIdHuge
                    itemOfTag.name = badgeNameHuge

                    val intent =
                        Intent(activityP, TagsArticleActivityViewImpl::class.java)
                    intent.putExtra("TAGS_ARTICLE", Gson().toJson(itemOfTag))
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    startAnim()
                }

                viewP.videoChild1CoverPhotoBadgeImage.setOnClickListener {
                    val itemOfTag =
                        Tags()
                    itemOfTag.id = badgeIdChild1
                    itemOfTag.name = badgeNameChild1

                    val intent =
                        Intent(activityP, TagsArticleActivityViewImpl::class.java)
                    intent.putExtra("TAGS_ARTICLE", Gson().toJson(itemOfTag))
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    startAnim()
                }

                viewP.videoChild2CoverPhotoBadgeImage.setOnClickListener {
                    val itemOfTag =
                        Tags()
                    itemOfTag.id = badgeIdChild2
                    itemOfTag.name = badgeNameChild2

                    val intent =
                        Intent(activityP, TagsArticleActivityViewImpl::class.java)
                    intent.putExtra("TAGS_ARTICLE", Gson().toJson(itemOfTag))
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    startAnim()
                }


                viewP.videoHugeCoverPhoto.setOnClickListener {

                    if (OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility == View.VISIBLE) {
                        OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility =
                            View.GONE
                    } else {

                        var categoryId: String? = null
                        var categoryName: String? = null

                        arrOfVideosData[0].categories?.get(0)?.let {
                            categoryId = arrOfVideosData[0].categories?.get(0)?.id
                            categoryName = arrOfVideosData[0].categories?.get(0)?.name
                        } ?: run {
                            categoryId = ""
                            categoryName = ""
                        }

                        var badgeId: String? = null
                        arrOfVideosData[0].badge?.let {
                            if (it.size != 0) {
                                badgeId = it[0].id!!
                            } else {
                                badgeId = ""
                            }
                        } ?: run {
                            badgeId = ""
                        }

                        var redirectUrl: String? = null
                        arrOfVideosData[0].redirectUrl?.let {
                            redirectUrl = it
                        } ?: run {
                            redirectUrl = ""
                        }

                        val articleItem =
                            HugeCardModel(
                                arrOfVideosData[0].id!!,
                                arrOfVideosData[0].legacyId!!,
                                null,
                                null,
                                badgeId,
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
                                categoryName!!,
                                categoryId!!,
                                arrOfVideosData[0].showInWebview!!,
                                redirectUrl!!
                            )

                        OnedioSingletonPattern.instance.setActivity(
                            DashboardActivityViewImpl::class.java
                        )

                        if (articleItem.redirectUrl != "") {
                            val browserIntent =
                                Intent(Intent.ACTION_VIEW, Uri.parse(articleItem.redirectUrl))
                            browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(browserIntent)
                        } else {
                            val intent =
                                Intent(
                                    activityP!!,
                                    ArticleDetailActivityViewImpl::class.java
                                )
                            intent.putExtra("ARTICLE_DETAIL_DATA", Gson().toJson(articleItem))
                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                        }

                        startAnim()
                    }

                }

                viewP.videoCoverTitle.setOnClickListener {

                    if (OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility == View.VISIBLE) {
                        OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility =
                            View.GONE
                    } else {

                        var categoryId: String? = null
                        var categoryName: String? = null

                        arrOfVideosData[0].categories?.get(0)?.let {
                            categoryId = arrOfVideosData[0].categories?.get(0)?.id
                            categoryName = arrOfVideosData[0].categories?.get(0)?.name
                        } ?: run {
                            categoryId = ""
                            categoryName = ""
                        }

                        var badgeId: String? = null
                        arrOfVideosData[0].badge?.let {
                            if (it.size != 0) {
                                badgeId = it[0].id!!
                            } else {
                                badgeId = ""
                            }
                        } ?: run {
                            badgeId = ""
                        }

                        var redirectUrl: String? = null
                        arrOfVideosData[0].redirectUrl?.let {
                            redirectUrl = it
                        } ?: run {
                            redirectUrl = ""
                        }

                        val articleItem =
                            HugeCardModel(
                                arrOfVideosData[0].id!!,
                                arrOfVideosData[0].legacyId!!,
                                null,
                                null,
                                badgeId,
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
                                categoryName!!,
                                categoryId!!,
                                arrOfVideosData[0].showInWebview!!,
                                redirectUrl!!
                            )
                        //OnedioSingletonPattern.instance.setArticleItem(articleItem)

                        OnedioSingletonPattern.instance.setActivity(
                            DashboardActivityViewImpl::class.java
                        )

                        if (articleItem.redirectUrl != "") {
                            val browserIntent =
                                Intent(Intent.ACTION_VIEW, Uri.parse(articleItem.redirectUrl))
                            browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(browserIntent)
                        } else {
                            val intent =
                                Intent(
                                    activityP,
                                    ArticleDetailActivityViewImpl::class.java
                                )
                            intent.putExtra("ARTICLE_DETAIL_DATA", Gson().toJson(articleItem))
                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                        }

                        /*OnedioCommon.cStartActivity(
                            activityP?.applicationContext!!,
                            ArticleDetailActivityViewImpl::class.java
                        )*/

                        startAnim()
                    }

                }

                viewP.videoChild1Image.setOnClickListener {

                    if (OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility == View.VISIBLE) {
                        OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility =
                            View.GONE
                    } else {

                        var categoryId: String? = null
                        var categoryName: String? = null

                        arrOfVideosData[1].categories?.get(0)?.let {
                            categoryId = arrOfVideosData[1].categories?.get(0)?.id
                            categoryName = arrOfVideosData[1].categories?.get(0)?.name
                        } ?: run {
                            categoryId = ""
                            categoryName = ""
                        }

                        var badgeId: String? = null
                        arrOfVideosData[1].badge?.let {
                            if (it.size != 0) {
                                badgeId = it[0].id!!
                            } else {
                                badgeId = ""
                            }
                        } ?: run {
                            badgeId = ""
                        }

                        var redirectUrl: String? = null
                        arrOfVideosData[1].redirectUrl?.let {
                            redirectUrl = it
                        } ?: run {
                            redirectUrl = ""
                        }

                        val articleItem =
                            HugeCardModel(
                                arrOfVideosData[1].id!!,
                                arrOfVideosData[1].legacyId!!,
                                null,
                                null,
                                badgeId,
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
                                categoryName!!,
                                categoryId!!,
                                arrOfVideosData[1].showInWebview!!,
                                redirectUrl!!
                            )
                        //OnedioSingletonPattern.instance.setArticleItem(articleItem)

                        OnedioSingletonPattern.instance.setActivity(
                            DashboardActivityViewImpl::class.java
                        )

                        /*OnedioCommon.cStartActivity(
                            activityP?.applicationContext!!,
                            ArticleDetailActivityViewImpl::class.java
                        )*/
                        if (articleItem.redirectUrl != "") {
                            val browserIntent =
                                Intent(Intent.ACTION_VIEW, Uri.parse(articleItem.redirectUrl))
                            browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(browserIntent)
                        } else {
                            val intent =
                                Intent(
                                    activityP,
                                    ArticleDetailActivityViewImpl::class.java
                                )
                            intent.putExtra("ARTICLE_DETAIL_DATA", Gson().toJson(articleItem))
                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                        }

                        startAnim()
                    }

                }

                viewP.videoChild1Title.setOnClickListener {

                    if (OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility == View.VISIBLE) {
                        OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility =
                            View.GONE
                    } else {

                        var categoryId: String? = null
                        var categoryName: String? = null

                        arrOfVideosData[1].categories?.get(0)?.let {
                            categoryId = arrOfVideosData[1].categories?.get(0)?.id
                            categoryName = arrOfVideosData[1].categories?.get(0)?.name
                        } ?: run {
                            categoryId = ""
                            categoryName = ""
                        }

                        var badgeId: String? = null
                        arrOfVideosData[1].badge?.let {
                            if (it.size != 0) {
                                badgeId = it[0].id!!
                            } else {
                                badgeId = ""
                            }
                        } ?: run {
                            badgeId = ""
                        }

                        var redirectUrl: String? = null
                        arrOfVideosData[1].redirectUrl?.let {
                            redirectUrl = it
                        } ?: run {
                            redirectUrl = ""
                        }

                        val articleItem =
                            HugeCardModel(
                                arrOfVideosData[1].id!!,
                                arrOfVideosData[1].legacyId!!,
                                null,
                                null,
                                badgeId,
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
                                categoryName!!,
                                categoryId!!,
                                arrOfVideosData[1].showInWebview!!,
                                redirectUrl!!
                            )
                        //OnedioSingletonPattern.instance.setArticleItem(articleItem)

                        OnedioSingletonPattern.instance.setActivity(
                            DashboardActivityViewImpl::class.java
                        )

                        if (articleItem.redirectUrl != "") {
                            val browserIntent =
                                Intent(Intent.ACTION_VIEW, Uri.parse(articleItem.redirectUrl))
                            browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(browserIntent)
                        } else {
                            val intent =
                                Intent(
                                    activityP,
                                    ArticleDetailActivityViewImpl::class.java
                                )
                            intent.putExtra("ARTICLE_DETAIL_DATA", Gson().toJson(articleItem))
                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                        }


                        /*OnedioCommon.cStartActivity(
                            activityP?.applicationContext!!,
                            ArticleDetailActivityViewImpl::class.java
                        )*/

                        startAnim()
                    }

                }

                viewP.videoChild2Image.setOnClickListener {

                    if (OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility == View.VISIBLE) {
                        OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility =
                            View.GONE
                    } else {

                        var categoryId: String? = null
                        var categoryName: String? = null

                        arrOfVideosData[2].categories?.get(0)?.let {
                            categoryId = arrOfVideosData[2].categories?.get(0)?.id
                            categoryName = arrOfVideosData[2].categories?.get(0)?.name
                        } ?: run {
                            categoryId = ""
                            categoryName = ""
                        }

                        var badgeId: String? = null
                        arrOfVideosData[2].badge?.let {
                            if (it.size != 0) {
                                badgeId = it[0].id!!
                            } else {
                                badgeId = ""
                            }
                        } ?: run {
                            badgeId = ""
                        }

                        var redirectUrl: String? = null
                        arrOfVideosData[2].redirectUrl?.let {
                            redirectUrl = it
                        } ?: run {
                            redirectUrl = ""
                        }

                        val articleItem =
                            HugeCardModel(
                                arrOfVideosData[2].id!!,
                                arrOfVideosData[2].legacyId!!,
                                null,
                                null,
                                badgeId,
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
                                categoryName!!,
                                categoryId!!,
                                arrOfVideosData[2].showInWebview!!,
                                redirectUrl!!
                            )
                        //OnedioSingletonPattern.instance.setArticleItem(articleItem)

                        OnedioSingletonPattern.instance.setActivity(
                            DashboardActivityViewImpl::class.java
                        )

                        if (articleItem.redirectUrl != "") {
                            val browserIntent =
                                Intent(Intent.ACTION_VIEW, Uri.parse(articleItem.redirectUrl))
                            browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(browserIntent)
                        } else {
                            val intent =
                                Intent(
                                    activityP,
                                    ArticleDetailActivityViewImpl::class.java
                                )
                            intent.putExtra("ARTICLE_DETAIL_DATA", Gson().toJson(articleItem))
                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                        }

                        /*OnedioCommon.cStartActivity(
                            activityP?.applicationContext!!,
                            ArticleDetailActivityViewImpl::class.java
                        )*/

                        startAnim()
                    }

                }

                viewP.videoChild2Title.setOnClickListener {

                    if (OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility == View.VISIBLE) {
                        OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility =
                            View.GONE
                    } else {

                        var categoryId: String? = null
                        var categoryName: String? = null

                        arrOfVideosData[2].categories?.get(0)?.let {
                            categoryId = arrOfVideosData[2].categories?.get(0)?.id
                            categoryName = arrOfVideosData[2].categories?.get(0)?.name
                        } ?: run {
                            categoryId = ""
                            categoryName = ""
                        }

                        var badgeId: String? = null
                        arrOfVideosData[2].badge?.let {
                            if (it.size != 0) {
                                badgeId = it[0].id!!
                            } else {
                                badgeId = ""
                            }
                        } ?: run {
                            badgeId = ""
                        }

                        var redirectUrl: String? = null
                        arrOfVideosData[2].redirectUrl?.let {
                            redirectUrl = it
                        } ?: run {
                            redirectUrl = ""
                        }

                        val articleItem =
                            HugeCardModel(
                                arrOfVideosData[2].id!!,
                                arrOfVideosData[2].legacyId!!,
                                null,
                                null,
                                badgeId,
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
                                categoryName!!,
                                categoryId!!,
                                arrOfVideosData[2].showInWebview!!,
                                redirectUrl!!
                            )
                        //OnedioSingletonPattern.instance.setArticleItem(articleItem)

                        OnedioSingletonPattern.instance.setActivity(
                            DashboardActivityViewImpl::class.java
                        )

                        if (articleItem.redirectUrl != "") {
                            val browserIntent =
                                Intent(Intent.ACTION_VIEW, Uri.parse(articleItem.redirectUrl))
                            browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(browserIntent)
                        } else {
                            val intent =
                                Intent(
                                    activityP,
                                    ArticleDetailActivityViewImpl::class.java
                                )
                            intent.putExtra("ARTICLE_DETAIL_DATA", Gson().toJson(articleItem))
                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                        }


                        /*OnedioCommon.cStartActivity(
                            activityP?.applicationContext!!,
                            ArticleDetailActivityViewImpl::class.java
                        )*/

                        startAnim()
                    }

                }

                viewP.showAllVideos.setOnClickListener {
                    if (OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility == View.VISIBLE) {
                        OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility =
                            View.GONE
                    } else {
                        /*OnedioSingletonPattern.instance.setCategoryId(
                            CategoryModel(
                                OnedioConstant.CATEGORIES_VIDEO_ID,
                                "",
                                ""
                            )
                        )*/
                        /*OnedioSingletonPattern.instance.setTabIndex(0)*/
                        OnedioSingletonPattern.instance.setActivity(
                            DashboardActivityViewImpl::class.java
                        )
                        //startActivity(Intent(activityP?.applicationContext, VideoActivityViewImpl::class.java))
                        /*OnedioCommon.cStartActivity(
                            activityP?.applicationContext!!,
                            VideoActivityViewImpl::class.java
                        )*/

                        val intent =
                            Intent(
                                activityP,
                                VideoActivityViewImpl::class.java
                            )
                        intent.putExtra(
                            "ARTICLE_VIDEO_DATA", Gson().toJson(
                                CategoryModel(
                                    OnedioConstant.CATEGORIES_VIDEO_ID,
                                    "",
                                    ""
                                )
                            )
                        )
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)

                        startAnim()
                    }

                }
            } ?: run {
                viewP.constraintVideo.visibility = View.GONE
            }
        } ?: run {
            viewP.constraintVideo.visibility = View.GONE
        }

    }

    //==================================================================================================================
    /**
     * Prepare Sports Widget Data..
     */
    //==================================================================================================================
    override fun prepare4SportsWidgetData(response4ArticlesFeed: Response4ArticlesFeed) {

        /*viewP.constraintSports.visibility = View.VISIBLE*/

        response4ArticlesFeed.data?.let {
            it.feed?.let {
                viewP.constraintSports.visibility = View.VISIBLE

                if (isMoreContentSports)
                    sportWidgetData = mutableListOf()

                //val arrOfSportsWidgetData = response4FeedModel.dataOfFeedModel?.articles
                val arrOfSportsWidgetData = response4ArticlesFeed.data?.feed

                arrOfSportsWidgetData?.forEach { itemOfSportsData ->

                    var categoryId: String? = null
                    var categoryName: String? = null

                    itemOfSportsData.categories?.get(0)?.let {
                        categoryId = itemOfSportsData.categories[0].id
                        categoryName = itemOfSportsData.categories[0].name
                    } ?: run {
                        categoryId = ""
                        categoryName = ""
                    }

                    var badgeImage: String? = null
                    var badgeId: String? = null
                    var badgeName: String? = null
                    itemOfSportsData.badge?.let {
                        if (itemOfSportsData.badge?.size != 0) {
                            badgeImage = itemOfSportsData.badge[0].icons?.png
                            badgeId = itemOfSportsData.badge[0].id
                            badgeName = itemOfSportsData.badge[0].name
                        } else {
                            badgeImage = ""
                            badgeId = ""
                            badgeName = ""
                        }

                    } ?: run {
                        badgeImage = ""
                        badgeId = ""
                        badgeName = ""
                    }

                    var isFavorite: Boolean? = null
                    itemOfSportsData.isUserFavorite?.let {
                        isFavorite = itemOfSportsData.isUserFavorite
                    } ?: run {
                        isFavorite = false
                    }

                    var redirectUrl: String? = null
                    itemOfSportsData.redirectUrl?.let {
                        redirectUrl = it
                    } ?: run {
                        redirectUrl = ""
                    }

                    var sArticleId: String? = null
                    itemOfSportsData.id?.let {
                        sArticleId = it
                    } ?: run {
                        sArticleId = ""
                    }

                    var sLegacyId: Long? = null
                    itemOfSportsData.legacyId?.let {
                        sLegacyId = it
                    } ?: run {
                        sLegacyId = 0
                    }

                    var sTitle: String? = null
                    itemOfSportsData.title?.let {
                        sTitle = it
                    } ?: run {
                        sTitle = ""
                    }

                    var sImage: String? = null
                    itemOfSportsData.image?.let {
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

                    var sShowInWebView: Boolean? = null
                    itemOfSportsData.showInWebview?.let {
                        sShowInWebView = it
                    } ?: run {
                        sShowInWebView = false
                    }



                    sportWidgetData.add(
                        TestWidgetModel(
                            sArticleId!!,
                            sLegacyId!!,
                            sImage!!,
                            sTitle!!,
                            categoryId!!,
                            categoryName!!,
                            badgeImage!!,
                            badgeId!!,
                            badgeName!!,
                            sShowInWebView!!,
                            isFavorite!!,
                            redirectUrl!!
                        )
                    )
                }

                viewP.sportsRecyclerListView.layoutManager =
                    LinearLayoutManager(
                        activityP?.applicationContext,
                        LinearLayoutManager.HORIZONTAL,
                        false
                    )
                viewP.sportsRecyclerListView.adapter =
                    SportsRecyclerListAdapter(
                        activityP?.applicationContext!!,
                        sportWidgetData
                    ) { type, itemOfSportsData ->

                        when (type) {
                            OnedioConstant.GENERAL -> {
                                if (OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility == View.VISIBLE) {
                                    OnedioSingletonPattern.instance.getProfileSettingPopup()
                                        ?.visibility =
                                        View.GONE
                                } else {
                                    val articleItem =
                                        HugeCardModel(
                                            itemOfSportsData.id,
                                            itemOfSportsData.legacyId,
                                            null,
                                            null,
                                            itemOfSportsData.badgeId,
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
                                            itemOfSportsData.isFavorited,
                                            itemOfSportsData.categoryName,
                                            itemOfSportsData.categoryId,
                                            itemOfSportsData.showInWebView,
                                            itemOfSportsData.redirectUrl
                                        )
                                    /*OnedioSingletonPattern.instance.setArticleItem(
                                        articleItem
                                    )*/

                                    OnedioSingletonPattern.instance.setActivity(
                                        DashboardActivityViewImpl::class.java
                                    )
                                    when {
                                        itemOfSportsData.redirectUrl != "" -> {
                                            val browserIntent =
                                                Intent(
                                                    Intent.ACTION_VIEW,
                                                    Uri.parse(itemOfSportsData.redirectUrl)
                                                )
                                            browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                            startActivity(browserIntent)
                                        }
                                        itemOfSportsData.showInWebView -> {
                                            val intent =
                                                Intent(
                                                    activityP,
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
                                        else -> {
                                            val intent =
                                                Intent(
                                                    activityP,
                                                    ArticleDetailActivityViewImpl::class.java
                                                )
                                            intent.putExtra(
                                                "ARTICLE_DETAIL_DATA",
                                                Gson().toJson(articleItem)
                                            )
                                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                            startActivity(intent)
                                        }
                                    }
                                    /*OnedioCommon.cStartActivity(
                                        activityP?.applicationContext!!,
                                        TestArticleDetailActivityViewImpl::class.java
                                    ) else
                                    OnedioCommon.cStartActivity(
                                        activityP?.applicationContext!!,
                                        ArticleDetailActivityViewImpl::class.java
                                    )*/

                                    startAnim()
                                }
                            }
                            OnedioConstant.BADGE_ICON -> {
                                val itemOfTag =
                                    Tags()
                                itemOfTag.id = itemOfSportsData.badgeId
                                itemOfTag.name = itemOfSportsData.badgeName

                                /*OnedioSingletonPattern.instance.setTagsItem(
                                    itemOfTag
                                )
                                OnedioCommon.cStartActivity(
                                    activityP?.applicationContext!!,
                                    TagsArticleActivityViewImpl::class.java
                                )*/
                                val intent =
                                    Intent(activityP, TagsArticleActivityViewImpl::class.java)
                                intent.putExtra("TAGS_ARTICLE", Gson().toJson(itemOfTag))
                                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                startActivity(intent)
                                startAnim()
                            }
                        }
                    }

            } ?: run {
                viewP.constraintSports.visibility = View.GONE
            }
        } ?: run {
            viewP.constraintSports.visibility = View.GONE
        }


    }


    private fun getInfiniteRecyclerViewData(wNameInfinite: String) {

        when (wNameInfinite) {
            OnedioConstant.WIDGET_MOBILE_INFINITE_WITH_ID -> {
                page += 1

                articleCount4Infinite += (allArticleFeedCount + 20)

                homeFeedFragmentActivityViewPresenter.getArticleData4Infinite(
                    page,
                    sumOfWidgetCount(widgetResponse.data?.widgets!!),
                    false
                )
            }
        }
    }

    //==================================================================================================================
    /**
     * Get Widget Category Data With Name..
     */
    //==================================================================================================================
    override fun getCategoryWidgetDataByName(
        wName: String,
        categoryId: String,
        page: Int,
        perPage: Int
    ) {
        homeFeedFragmentActivityViewPresenter.getCategoryWidgetDataByName(
            isFirst4TestWidget,
            wName,
            categoryId,
            page,
            perPage
        )
    }

    //==================================================================================================================
    /**
     * Handle Widget Category Data With Name..
     */
    //==================================================================================================================
    override fun handleCategoryDataByName(
        isFirst: Boolean,
        response4FeedModel: Response4FeedModel
    ) {

        //prepare4TestWidgetData(response4FeedModel)
    }

    //==================================================================================================================
    /**
     * Preapre Editor Choosing Data...
     */
    //==================================================================================================================
    override fun prepareEditorChoosing4GridWidgetData(
        wName: String,
        response4ArticlesFeed: Response4ArticlesFeed
    ) {

        //val xmlViewsType = OnedioSingletonPattern.instance.getXmlViews()
        val xmlViewsType = xmlViewsMap
        val widgetLayoutModel = xmlViewsType[wName]!!
        val widgetTitle = widgetLayoutModel.widgetTitle
        val xmlLayout = widgetLayoutModel.xmlLayout

        val constraintEditorChoosing: ConstraintLayout =
            xmlLayout.findViewById(R.id.constraintEditorChoosing)
        val editorChoosingWidgetGridView: ScrollableGridView =
            xmlLayout.findViewById(R.id.editorChoosingWidgetGridView)
        val editorChoosingTextInfo: TextView = xmlLayout.findViewById(R.id.editorChoosingTextInfo)
        var mAdapter: EditorChoosingWidgetGridViewAdapter

        /*constraintEditorChoosing.visibility = View.VISIBLE*/

        response4ArticlesFeed.data?.let {
            it.feed?.let {
                constraintEditorChoosing.visibility = View.VISIBLE

                editorChoosingTextInfo.text = widgetTitle.split("§")[0]

                var listOfTagData: MutableList<EditorChoosingWidgetGridModel> = mutableListOf()

                if (isMoreTagData)
                    listOfTagData = gridData4EditorChoosing[wName]!!


                /*if (isMoreContentEditorChoosing)
                    gridData4EditorChoosing = HashMap()*/

                //gridData4EditorChoosing = mutableListOf()


                val arrOfEditorWidgetDatas = response4ArticlesFeed.data?.feed

                arrOfEditorWidgetDatas?.forEach { itemOfEditorWidgetData ->

                    var categoryId: String? = null
                    var categoryName: String? = null

                    itemOfEditorWidgetData.categories?.let {
                        categoryId = itemOfEditorWidgetData.categories[0].id!!
                        categoryName = itemOfEditorWidgetData.categories[0].name!!
                    } ?: run {

                    }

                    var badgeImage: String? = null
                    var badgeId: String? = null
                    var badName: String? = null
                    itemOfEditorWidgetData.badge?.let {
                        if (itemOfEditorWidgetData.badge.size != 0) {
                            badgeImage = itemOfEditorWidgetData.badge[0].icons?.png
                            badgeId = itemOfEditorWidgetData.badge[0].id
                            badName = itemOfEditorWidgetData.badge[0].name
                        } else {
                            badgeImage = ""
                            badgeId = ""
                            badName = ""
                        }

                    } ?: run {
                        badgeImage = ""
                        badgeId = ""
                        badName = ""
                    }

                    var redirectUrl: String? = null
                    itemOfEditorWidgetData.redirectUrl?.let {
                        redirectUrl = it
                    } ?: run {
                        redirectUrl = ""
                    }

                    var eArticleId: String? = null
                    itemOfEditorWidgetData.id?.let {
                        eArticleId = it
                    } ?: run {
                        eArticleId = ""
                    }

                    var eLegacyId: Long? = null
                    itemOfEditorWidgetData.legacyId?.let {
                        eLegacyId = it
                    } ?: run {
                        eLegacyId = 0
                    }

                    var eTitle: String? = null
                    itemOfEditorWidgetData.title?.let {
                        eTitle = it
                    } ?: run {
                        eTitle = ""
                    }

                    var eImage: String? = null
                    itemOfEditorWidgetData.image?.let {
                        it.alternates?.let {
                            it.standardResolution?.let {
                                it.url?.let {
                                    eImage = it
                                } ?: run {
                                    eImage = ""
                                }
                            } ?: run {
                                eImage = ""
                            }
                        } ?: run {
                            eImage = ""
                        }
                    } ?: run {
                        eImage = ""
                    }

                    var eShowInWebView: Boolean? = null
                    itemOfEditorWidgetData.showInWebview?.let {
                        eShowInWebView = it
                    } ?: run {
                        eShowInWebView = false
                    }

                    listOfTagData.add(
                        EditorChoosingWidgetGridModel(
                            eArticleId!!,
                            eLegacyId!!,
                            eImage!!,
                            eTitle!!,
                            categoryId!!,
                            categoryName!!,
                            badgeImage,
                            badgeId,
                            badName,
                            eShowInWebView!!,
                            redirectUrl!!
                        )
                    )
                }

                gridData4EditorChoosing[wName] = listOfTagData

                mAdapter =
                    EditorChoosingWidgetGridViewAdapter(
                        gridData4EditorChoosing[wName]!!,
                        activityP!!
                    ) { type, itemOfEditorChoosingArticle ->

                        when (type) {
                            OnedioConstant.GENERAL -> {
                                if (OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility == View.VISIBLE) {
                                    OnedioSingletonPattern.instance.getProfileSettingPopup()
                                        ?.visibility =
                                        View.GONE
                                } else {
                                    val articleItem =
                                        HugeCardModel(
                                            itemOfEditorChoosingArticle.id,
                                            itemOfEditorChoosingArticle.legacyId,
                                            null,
                                            null,
                                            itemOfEditorChoosingArticle.badgeId,
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
                                            itemOfEditorChoosingArticle.categoryName,
                                            itemOfEditorChoosingArticle.categoryId,
                                            itemOfEditorChoosingArticle.showInWebView,
                                            itemOfEditorChoosingArticle.redirectUrl
                                        )
                                    /*OnedioSingletonPattern.instance.setArticleItem(
                                        articleItem
                                    )*/

                                    OnedioSingletonPattern.instance.setActivity(
                                        DashboardActivityViewImpl::class.java
                                    )

                                    when {
                                        itemOfEditorChoosingArticle.redirectUrl != "" -> {
                                            val browserIntent =
                                                Intent(
                                                    Intent.ACTION_VIEW,
                                                    Uri.parse(itemOfEditorChoosingArticle.redirectUrl)
                                                )
                                            browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                            startActivity(browserIntent)
                                        }
                                        itemOfEditorChoosingArticle.showInWebView -> {
                                            val intent =
                                                Intent(
                                                    activityP,
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
                                        else -> {
                                            val intent =
                                                Intent(
                                                    activityP,
                                                    ArticleDetailActivityViewImpl::class.java
                                                )
                                            intent.putExtra(
                                                "ARTICLE_DETAIL_DATA",
                                                Gson().toJson(articleItem)
                                            )
                                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                            startActivity(intent)
                                        }
                                    }
                                    /*OnedioCommon.cStartActivity(
                                        activityP?.applicationContext!!,
                                        TestArticleDetailActivityViewImpl::class.java
                                    ) else
                                    OnedioCommon.cStartActivity(
                                        activityP?.applicationContext!!,
                                        ArticleDetailActivityViewImpl::class.java
                                    )*/

                                    startAnim()
                                }
                            }
                            OnedioConstant.BADGE_ICON -> {
                                val itemOfTag =
                                    Tags()
                                itemOfTag.id = itemOfEditorChoosingArticle.badgeId
                                itemOfTag.name = itemOfEditorChoosingArticle.badgeName

                                /*OnedioSingletonPattern.instance.setTagsItem(
                                    itemOfTag
                                )
                                OnedioCommon.cStartActivity(
                                    activityP?.applicationContext!!,
                                    TagsArticleActivityViewImpl::class.java
                                )*/
                                val intent =
                                    Intent(activityP, TagsArticleActivityViewImpl::class.java)
                                intent.putExtra("TAGS_ARTICLE", Gson().toJson(itemOfTag))
                                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                startActivity(intent)
                                startAnim()
                            }
                        }


                    }

                editorChoosingWidgetGridView.adapter = mAdapter

                isMoreTagData = false

            } ?: run {
                constraintEditorChoosing.visibility = View.GONE
            }
        } ?: run {
            constraintEditorChoosing.visibility = View.GONE
        }


    }

    //==================================================================================================================
    /**
     * Test Widget Data Preparing..
     */
    //==================================================================================================================
    override fun prepare4TestWidgetData(
        wName: String,
        response4ArticlesFeed: Response4ArticlesFeed
    ) {

        Log.i("WNAME----", wName)

        //val xmlViewsType = OnedioSingletonPattern.instance.getXmlViews()
        val xmlViewsType = xmlViewsMap
        val widgetLayoutModel = xmlViewsType[wName]!!
        val widgetTitle = widgetLayoutModel.widgetTitle
        val xmlLayout = widgetLayoutModel.xmlLayout


        val constraintTest: ConstraintLayout = xmlLayout.findViewById(R.id.constraintTest)
        val testTextInfo: TextView = xmlLayout.findViewById(R.id.testTextInfo)
        val testCoverPhoto: ImageView = xmlLayout.findViewById(R.id.testCoverPhoto)
        val testCoverPhotoProgress: ProgressBar =
            xmlLayout.findViewById(R.id.testCoverPhotoProgress)
        val testCoverTitle: TextView = xmlLayout.findViewById(R.id.testCoverTitle)
        val testsRecyclerListView: RecyclerView = xmlLayout.findViewById(R.id.testsRecyclerListView)
        val showAllTests: TextView = xmlLayout.findViewById(R.id.showAllTests)
        val testBadgeLineCovertPhoto: View = xmlLayout.findViewById(R.id.testBadgeLineCovertPhoto)
        val testCoverPhotoBadgeImage: ImageView =
            xmlLayout.findViewById(R.id.testCoverPhotoBadgeImage)

        /*constraintTest.visibility = View.VISIBLE*/

        response4ArticlesFeed.data?.let {
            it.feed?.let {
                constraintTest.visibility = View.VISIBLE

                testTextInfo.text = widgetTitle.split("§")[0]


                var listOfCategoryData: MutableList<TestWidgetModel> = mutableListOf()
                if (isMoreCategoryData)
                    listOfCategoryData = testWidgetData[wName]!!

                if (isFirst4TestWidget || bbbb >= 2) {

                    var badgeId: String? = null
                    var badgeIcon: String? = null
                    var badgeName: String? = null
                    response4ArticlesFeed.data!!.feed!![0].badge?.let {
                        if (it.size != 0) {
                            badgeId = it[0].id
                            badgeIcon = it[0].icons?.png
                            badgeName = it[0].name
                        } else {
                            badgeId = ""
                            badgeIcon = ""
                            badgeName = ""
                        }
                    } ?: run {
                        badgeId = ""
                        badgeIcon = ""
                        badgeName = ""
                    }

                    if (badgeId != "" && badgeIcon != "") {
                        testBadgeLineCovertPhoto.visibility = View.VISIBLE
                        testCoverPhotoBadgeImage.visibility = View.VISIBLE

                        loadImageWithoutProgress(
                            badgeIcon!!,
                            testCoverPhotoBadgeImage
                        )

                        testBadgeLineCovertPhoto.setOnClickListener {
                            val itemOfTag =
                                Tags()
                            itemOfTag.id = badgeId
                            itemOfTag.name = badgeName

                            /*OnedioSingletonPattern.instance.setTagsItem(itemOfTag)
                            OnedioCommon.cStartActivity(
                                activityP?.applicationContext!!,
                                TagsArticleActivityViewImpl::class.java
                            )*/
                            val intent =
                                Intent(activityP, TagsArticleActivityViewImpl::class.java)
                            intent.putExtra("TAGS_ARTICLE", Gson().toJson(itemOfTag))
                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                            startAnim()
                        }

                        testCoverPhotoBadgeImage.setOnClickListener {
                            val itemOfTag =
                                Tags()
                            itemOfTag.id = badgeId
                            itemOfTag.name = badgeName

                            /*OnedioSingletonPattern.instance.setTagsItem(itemOfTag)
                            OnedioCommon.cStartActivity(
                                activityP?.applicationContext!!,
                                TagsArticleActivityViewImpl::class.java
                            )*/
                            val intent =
                                Intent(activityP, TagsArticleActivityViewImpl::class.java)
                            intent.putExtra("TAGS_ARTICLE", Gson().toJson(itemOfTag))
                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                            startAnim()
                        }

                    } else {
                        testBadgeLineCovertPhoto.visibility = View.INVISIBLE
                        testCoverPhotoBadgeImage.visibility = View.INVISIBLE
                    }



                    loadImage(
                        response4ArticlesFeed.data!!.feed!![0].image?.alternates?.standardResolution?.url!!,
                        testCoverPhoto,
                        testCoverPhotoProgress
                    )
                    testCoverTitle.text =
                        response4ArticlesFeed.data.feed!![0].title
                    isFirst4TestWidget = false
                }

                if (isMoreContentTest)
                    testWidgetData = HashMap()

                //testWidgetData = mutableListOf()

                var badgeImage: String? = null
                var badgeId: String? = null
                var badgeName: String? = null

                val arrOfTestWidgetData = response4ArticlesFeed.data?.feed
                for (i in 1 until arrOfTestWidgetData?.size!!) {

                    var categoryId: String? = null
                    var categoryName: String? = null

                    arrOfTestWidgetData[i].categories?.let {
                        categoryId = arrOfTestWidgetData[i].categories?.get(0)?.id!!
                        categoryName = arrOfTestWidgetData[i].categories?.get(0)?.name!!
                    } ?: run {

                    }

                    arrOfTestWidgetData[i].badge?.let {
                        if (arrOfTestWidgetData[i].badge?.size != 0) {
                            badgeImage = arrOfTestWidgetData[i].badge?.get(0)?.icons?.png
                            badgeId = arrOfTestWidgetData[i].badge?.get(0)?.id
                            badgeName = arrOfTestWidgetData[i].badge?.get(0)?.name
                        } else {
                            badgeImage = ""
                            badgeId = ""
                            badgeName = ""
                        }

                    } ?: run {
                        badgeImage = ""
                        badgeId = ""
                        badgeName = ""
                    }

                    var isFavorited: Boolean? = null
                    arrOfTestWidgetData[i].isUserFavorite?.let {
                        isFavorited = it
                    } ?: run {
                        isFavorited = false
                    }

                    var redirectUrl: String? = null
                    arrOfTestWidgetData[i].redirectUrl?.let {
                        redirectUrl = it
                    } ?: run {
                        redirectUrl = ""
                    }

                    var tArticleId: String? = null
                    arrOfTestWidgetData[i].id?.let {
                        tArticleId = it
                    } ?: run {
                        tArticleId = ""
                    }

                    var tLegacyId: Long? = null
                    arrOfTestWidgetData[i].legacyId?.let {
                        tLegacyId = it
                    } ?: run {
                        tLegacyId = 0
                    }

                    var tTitle: String? = null
                    arrOfTestWidgetData[i].title?.let {
                        tTitle = it
                    } ?: run {
                        tTitle = ""
                    }

                    var tImage: String? = null
                    arrOfTestWidgetData[i].image?.let {
                        it.alternates?.let {
                            it.standardResolution?.let {
                                it.url?.let {
                                    tImage = it
                                } ?: run {
                                    tImage = ""
                                }
                            } ?: run {
                                tImage = ""
                            }
                        } ?: run {
                            tImage = ""
                        }
                    } ?: run {
                        tImage = ""
                    }

                    var tShowInWebView: Boolean? = null
                    arrOfTestWidgetData[i].showInWebview?.let {
                        tShowInWebView = it
                    } ?: run {
                        tShowInWebView = false
                    }


                    listOfCategoryData.add(
                        TestWidgetModel(
                            tArticleId!!,
                            tLegacyId!!,
                            tImage!!,
                            tTitle!!,
                            categoryId,
                            categoryName,
                            badgeImage!!,
                            badgeId!!,
                            badgeName!!,
                            tShowInWebView!!,
                            isFavorited!!,
                            redirectUrl!!
                        )
                    )
                }

                testWidgetData[wName] = listOfCategoryData

                testsRecyclerListView.layoutManager =
                    LinearLayoutManager(
                        activityP?.applicationContext,
                        LinearLayoutManager.VERTICAL,
                        false
                    )

                val itemDecorator =
                    DividerItemDecoration(
                        activityP?.applicationContext,
                        DividerItemDecoration.VERTICAL
                    )
                itemDecorator.setDrawable(
                    ContextCompat.getDrawable(
                        activityP?.applicationContext!!,
                        R.drawable.test_recycler_view_divider
                    )!!
                )

                testsRecyclerListView.addItemDecoration(itemDecorator)

                testsRecyclerListView.adapter =
                    TestRecyclerListAdapter(
                        activityP?.applicationContext!!,
                        testWidgetData[wName]!!
                    ) { type, itemOfTestWidgetData ->

                        when (type) {
                            OnedioConstant.GENERAL -> {
                                if (OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility == View.VISIBLE) {
                                    OnedioSingletonPattern.instance.getProfileSettingPopup()
                                        ?.visibility =
                                        View.GONE
                                } else {
                                    /*OnedioSingletonPattern.instance.setArticleItem(
                                        HugeCardModel(
                                            itemOfTestWidgetData.id,
                                            itemOfTestWidgetData.legacyId,
                                            null,
                                            null,
                                            itemOfTestWidgetData.badgeId,
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
                                            itemOfTestWidgetData.isFavorited,
                                            itemOfTestWidgetData.categoryName,
                                            itemOfTestWidgetData.categoryId,
                                            itemOfTestWidgetData.showInWebView
                                        )
                                    )*/

                                    /*OnedioSingletonPattern.instance.setTabIndex(
                                        0
                                    )*/
                                    OnedioSingletonPattern.instance.setActivity(
                                        DashboardActivityViewImpl::class.java
                                    )

                                    when {
                                        itemOfTestWidgetData.redirectUrl != "" -> {
                                            val browserIntent =
                                                Intent(
                                                    Intent.ACTION_VIEW,
                                                    Uri.parse(itemOfTestWidgetData.redirectUrl)
                                                )
                                            browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                            startActivity(browserIntent)
                                        }
                                        itemOfTestWidgetData.showInWebView -> {
                                            val intent =
                                                Intent(
                                                    activityP,
                                                    TestArticleDetailActivityViewImpl::class.java
                                                )
                                            intent.putExtra(
                                                "ARTICLE_DETAIL_DATA", Gson().toJson(
                                                    HugeCardModel(
                                                        itemOfTestWidgetData.id,
                                                        itemOfTestWidgetData.legacyId,
                                                        null,
                                                        null,
                                                        itemOfTestWidgetData.badgeId,
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
                                                        itemOfTestWidgetData.isFavorited,
                                                        itemOfTestWidgetData.categoryName,
                                                        itemOfTestWidgetData.categoryId,
                                                        itemOfTestWidgetData.showInWebView,
                                                        itemOfTestWidgetData.redirectUrl
                                                    )
                                                )
                                            )
                                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                            startActivity(intent)

                                            /*OnedioCommon.cStartActivity(
                                                                            activityP?.applicationContext!!,
                                                                            TestArticleDetailActivityViewImpl::class.java
                                                                        )*/
                                        }
                                        else -> {
                                            val intent =
                                                Intent(
                                                    activityP,
                                                    ArticleDetailActivityViewImpl::class.java
                                                )
                                            intent.putExtra(
                                                "ARTICLE_DETAIL_DATA", Gson().toJson(
                                                    HugeCardModel(
                                                        itemOfTestWidgetData.id,
                                                        itemOfTestWidgetData.legacyId,
                                                        null,
                                                        null,
                                                        itemOfTestWidgetData.badgeId,
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
                                                        itemOfTestWidgetData.isFavorited,
                                                        itemOfTestWidgetData.categoryName,
                                                        itemOfTestWidgetData.categoryId,
                                                        itemOfTestWidgetData.showInWebView,
                                                        itemOfTestWidgetData.redirectUrl
                                                    )
                                                )
                                            )
                                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                            startActivity(intent)
                                        }
                                    }

                                    /*else
                                        OnedioCommon.cStartActivity(
                                            activityP?.applicationContext!!,
                                            ArticleDetailActivityViewImpl::class.java
                                        )*/

                                    startAnim()
                                }
                            }
                            OnedioConstant.BADGE_ICON -> {
                                val itemOfTag =
                                    Tags()
                                itemOfTag.id = itemOfTestWidgetData.badgeId
                                itemOfTag.name = itemOfTestWidgetData.badgeName

                                /*OnedioSingletonPattern.instance.setTagsItem(
                                    itemOfTag
                                )
                                OnedioCommon.cStartActivity(
                                    activityP?.applicationContext!!,
                                    TagsArticleActivityViewImpl::class.java
                                )*/
                                val intent =
                                    Intent(activityP, TagsArticleActivityViewImpl::class.java)
                                intent.putExtra("TAGS_ARTICLE", Gson().toJson(itemOfTag))
                                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                startActivity(intent)
                                startAnim()
                            }
                        }


                    }


                testCoverPhoto.setOnClickListener {


                    if (OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility == View.VISIBLE) {
                        OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility =
                            View.GONE
                    } else {

                        var categoryId: String? = null
                        var categoryName: String? = null

                        response4ArticlesFeed.data.feed[0].categories?.let {
                            categoryId = response4ArticlesFeed.data.feed[0].categories?.get(0)?.id!!
                            categoryName =
                                response4ArticlesFeed.data.feed[0].categories?.get(0)?.name!!
                        } ?: run {

                        }

                        var badgeId: String? = null
                        response4ArticlesFeed.data.feed[0].badge?.let {
                            if (it.size != 0) {
                                badgeId = it[0].id!!
                            } else {
                                badgeId = ""
                            }
                        } ?: run {
                            badgeId = ""
                        }

                        var isFavorited: Boolean? = null
                        response4ArticlesFeed.data.feed[0].isUserFavorite?.let {
                            isFavorited = response4ArticlesFeed.data.feed[0].isUserFavorite
                        } ?: run {
                            isFavorited = false
                        }

                        var redirectUrl: String? = null
                        response4ArticlesFeed.data.feed[0].redirectUrl?.let {
                            redirectUrl = it
                        } ?: run {
                            redirectUrl = ""
                        }


                        /*OnedioSingletonPattern.instance.setArticleItem(
                            HugeCardModel(
                                response4ArticlesFeed.data.feed[0].id!!,
                                response4ArticlesFeed.data.feed[0].legacyId!!,
                                null,
                                null,
                                badgeId,
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
                                isFavorited!!,
                                categoryName,
                                categoryId,
                                response4ArticlesFeed.data.feed[0].showInWebview!!
                            )
                        )*/

                        /*OnedioSingletonPattern.instance.setTabIndex(0)*/
                        OnedioSingletonPattern.instance.setActivity(
                            DashboardActivityViewImpl::class.java
                        )

                        when {
                            redirectUrl != "" -> {
                                val browserIntent =
                                    Intent(Intent.ACTION_VIEW, Uri.parse(redirectUrl))
                                browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                startActivity(browserIntent)
                            }
                            response4ArticlesFeed.data.feed[0].showInWebview!! -> {
                                val intent =
                                    Intent(
                                        activityP,
                                        TestArticleDetailActivityViewImpl::class.java
                                    )
                                intent.putExtra(
                                    "ARTICLE_DETAIL_DATA", Gson().toJson(
                                        HugeCardModel(
                                            response4ArticlesFeed.data.feed[0].id!!,
                                            response4ArticlesFeed.data.feed[0].legacyId!!,
                                            null,
                                            null,
                                            badgeId,
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
                                            isFavorited!!,
                                            categoryName,
                                            categoryId,
                                            response4ArticlesFeed.data.feed[0].showInWebview!!,
                                            redirectUrl!!
                                        )
                                    )
                                )
                                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                startActivity(intent)
                            }
                            /*OnedioCommon.cStartActivity(
                                activityP?.applicationContext!!,
                                TestArticleDetailActivityViewImpl::class.java
                            )*/
                            else -> {
                                val intent =
                                    Intent(
                                        activityP,
                                        ArticleDetailActivityViewImpl::class.java
                                    )
                                intent.putExtra(
                                    "ARTICLE_DETAIL_DATA", Gson().toJson(
                                        HugeCardModel(
                                            response4ArticlesFeed.data.feed[0].id!!,
                                            response4ArticlesFeed.data.feed[0].legacyId!!,
                                            null,
                                            null,
                                            badgeId,
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
                                            isFavorited!!,
                                            categoryName,
                                            categoryId,
                                            response4ArticlesFeed.data.feed[0].showInWebview!!,
                                            redirectUrl!!
                                        )
                                    )
                                )
                                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                startActivity(intent)
                            }
                        }
                        /*OnedioCommon.cStartActivity(
                            activityP?.applicationContext!!,
                            ArticleDetailActivityViewImpl::class.java
                        )*/

                        startAnim()
                    }
                }

                testCoverTitle.setOnClickListener {

                    if (OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility == View.VISIBLE) {
                        OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility =
                            View.GONE
                    } else {

                        var categoryId: String? = null
                        var categoryName: String? = null

                        response4ArticlesFeed.data.feed[0].categories?.let {
                            categoryId = response4ArticlesFeed.data.feed[0].categories?.get(0)?.id!!
                            categoryName =
                                response4ArticlesFeed.data.feed[0].categories?.get(0)?.name!!
                        } ?: run {

                        }

                        var badgeId: String? = null
                        response4ArticlesFeed.data.feed[0].badge?.let {
                            if (it.size != 0) {
                                badgeId = it[0].id!!
                            } else {
                                badgeId = ""
                            }
                        } ?: run {
                            badgeId = ""
                        }

                        var isFavorited: Boolean? = null
                        response4ArticlesFeed.data.feed[0].isUserFavorite?.let {
                            isFavorited = response4ArticlesFeed.data.feed[0].isUserFavorite
                        } ?: run {
                            isFavorited = false
                        }

                        var redirectUrl: String? = null
                        response4ArticlesFeed.data.feed[0].redirectUrl?.let {
                            redirectUrl = it
                        } ?: run {
                            redirectUrl = ""
                        }


                        /*OnedioSingletonPattern.instance.setArticleItem(
                            HugeCardModel(
                                response4ArticlesFeed.data.feed[0].id!!,
                                response4ArticlesFeed.data.feed[0].legacyId!!,
                                null,
                                null,
                                badgeId,
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
                                isFavorited!!,
                                categoryName,
                                categoryId,
                                response4ArticlesFeed.data.feed[0].showInWebview!!
                            )
                        )*/

                        /*OnedioSingletonPattern.instance.setTabIndex(0)*/
                        OnedioSingletonPattern.instance.setActivity(
                            DashboardActivityViewImpl::class.java
                        )

                        when {
                            redirectUrl != "" -> {
                                val browserIntent =
                                    Intent(Intent.ACTION_VIEW, Uri.parse(redirectUrl))
                                browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                startActivity(browserIntent)
                            }
                            response4ArticlesFeed.data.feed[0].showInWebview!! -> {
                                val intent =
                                    Intent(
                                        activityP,
                                        TestArticleDetailActivityViewImpl::class.java
                                    )
                                intent.putExtra(
                                    "ARTICLE_DETAIL_DATA", Gson().toJson(
                                        HugeCardModel(
                                            response4ArticlesFeed.data.feed[0].id!!,
                                            response4ArticlesFeed.data.feed[0].legacyId!!,
                                            null,
                                            null,
                                            badgeId,
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
                                            isFavorited!!,
                                            categoryName,
                                            categoryId,
                                            response4ArticlesFeed.data.feed[0].showInWebview!!,
                                            redirectUrl!!
                                        )
                                    )
                                )
                                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                startActivity(intent)
                            }
                            else -> {
                                val intent =
                                    Intent(
                                        activityP,
                                        ArticleDetailActivityViewImpl::class.java
                                    )
                                intent.putExtra(
                                    "ARTICLE_DETAIL_DATA", Gson().toJson(
                                        HugeCardModel(
                                            response4ArticlesFeed.data.feed[0].id!!,
                                            response4ArticlesFeed.data.feed[0].legacyId!!,
                                            null,
                                            null,
                                            badgeId,
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
                                            isFavorited!!,
                                            categoryName,
                                            categoryId,
                                            response4ArticlesFeed.data.feed[0].showInWebview!!,
                                            redirectUrl!!
                                        )
                                    )
                                )
                                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                startActivity(intent)
                            }
                        }
                        /*OnedioCommon.cStartActivity(
                            activityP?.applicationContext!!,
                            TestArticleDetailActivityViewImpl::class.java
                        )
                    else
                        OnedioCommon.cStartActivity(
                            activityP?.applicationContext!!,
                            ArticleDetailActivityViewImpl::class.java
                        )*/

                        startAnim()
                    }
                }

                showAllTests.setOnClickListener {

                    if (OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility == View.VISIBLE) {
                        OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility =
                            View.GONE
                    } else {

                        var widgetName = testTextInfo.text.toString()
                        //var xmlViews = OnedioSingletonPattern.instance.getXmlViews()
                        var xmlViews = xmlViewsMap
                        var categoryModel: CategoryModel? = null
                        xmlViews.forEach { (key, value) ->
                            var wName = value.widgetTitle.split("§")[0]
                            var wTargetId = value.widgetTitle.split("§")[1]

                            if (wName == widgetName) {
                                categoryModel = CategoryModel(
                                    wTargetId,
                                    "",
                                    wName
                                )
                                /*OnedioSingletonPattern.instance.setCategoryId(
                                    CategoryModel(
                                        wTargetId,
                                        "",
                                        wName
                                    )
                                )*/
                            }
                        }

                        /*OnedioSingletonPattern.instance.setTabIndex(0)*/
                        OnedioSingletonPattern.instance.setActivity(
                            DashboardActivityViewImpl::class.java
                        )

                        val intent =
                            Intent(activityP, CategoryFeedActivityViewImpl::class.java)
                        intent.putExtra("ARTICLE_CATEGORY_DATA", Gson().toJson(categoryModel))
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                        /*OnedioCommon.cStartActivity(
                            activityP?.applicationContext!!,
                            CategoryFeedActivityViewImpl::class.java
                        )*/

                        startAnim()
                    }
                }

                isMoreCategoryData = false


            } ?: run {
                constraintTest.visibility = View.GONE
            }
        } ?: run {
            constraintTest.visibility = View.GONE
        }


    }

    //==================================================================================================================
    /**
     * Set Component TypeFace Method..
     */
    //==================================================================================================================
    override fun setComponentTypeFace() {
        viewP.coverPhotoText?.let {

        }?.run {
            //// Huge Card With Comment..
            viewP.coverPhotoText.typeface = OnedioCommon.changeTypeFace(activityP!!)
            viewP.feedTitle.typeface = OnedioCommon.changeTypeFaceSemiBold(activityP!!)
            viewP.feedDate.typeface = OnedioCommon.changeTypeFace(activityP!!)
            viewP.addFavoriteText.typeface = OnedioCommon.changeTypeFace(activityP!!)
            viewP.emojiActionCount.typeface = OnedioCommon.changeTypeFace(activityP!!)
            viewP.doCommentText.typeface = OnedioCommon.changeTypeFace(activityP!!)
            viewP.dotIcon.typeface = OnedioCommon.changeTypeFace(activityP!!)
            viewP.commentCount.typeface = OnedioCommon.changeTypeFace(activityP!!)
            viewP.shareText.typeface = OnedioCommon.changeTypeFace(activityP!!)
            viewP.textView4.typeface = OnedioCommon.changeTypeFace(activityP!!)
            viewP.childCommentUserName.typeface = OnedioCommon.changeTypeFace(activityP!!)
            viewP.textView2.typeface = OnedioCommon.changeTypeFace(activityP!!)
            viewP.childCommentTime.typeface = OnedioCommon.changeTypeFace(activityP!!)
            viewP.childCommentText.typeface = OnedioCommon.changeTypeFace(activityP!!)
            viewP.childLikeText.typeface = OnedioCommon.changeTypeFace(activityP!!)
            viewP.childLikeDot.typeface = OnedioCommon.changeTypeFace(activityP!!)
            viewP.childLikeCount.typeface = OnedioCommon.changeTypeFace(activityP!!)
            //viewP.childAnswerCommentText.typeface = OnedioCommon.changeTypeFace(activityP!!)
        }

        viewP.editorChoosingTextInfo?.let {

        }?.run {
            //// Editor Choosing Widget
            viewP.editorChoosingTextInfo.typeface = OnedioCommon.changeTypeFaceSemiBold(activityP!!)
            viewP.moreGridData4EditorChoosing.typeface = OnedioCommon.changeTypeFace(activityP!!)
        }

        viewP.testTextInfo?.let {

        }?.run {
            //// Test Widget
            viewP.testTextInfo.typeface = OnedioCommon.changeTypeFaceSemiBold(activityP!!)
            viewP.showAllTests.typeface = OnedioCommon.changeTypeFace(activityP!!)
            viewP.testCoverTitle.typeface = OnedioCommon.changeTypeFaceSemiBold(activityP!!)
            viewP.moreListData4Test.typeface = OnedioCommon.changeTypeFace(activityP!!)
        }

        viewP.videoTextInfo?.let {

        }?.run {
            //// Video Widget
            viewP.videoTextInfo.typeface = OnedioCommon.changeTypeFaceSemiBold(activityP!!)
            viewP.showAllVideos.typeface = OnedioCommon.changeTypeFace(activityP!!)
            viewP.videoCoverTitle.typeface = OnedioCommon.changeTypeFaceSemiBold(activityP!!)
            viewP.videoChild1Title.typeface = OnedioCommon.changeTypeFace(activityP!!)
            viewP.videoChild2Title.typeface = OnedioCommon.changeTypeFace(activityP!!)
            //viewP.moreVideoGridData.typeface = OnedioCommon.changeTypeFace(activity!!)
        }

        viewP.sportTextInfo?.let {

        }?.run {
            //// Sport Widget
            viewP.sportTextInfo.typeface = OnedioCommon.changeTypeFaceSemiBold(activityP!!)
        }

    }

    //==================================================================================================================
    /**
     * Prepare Slider Data...
     */
    //==================================================================================================================
    override fun prepareSliderListData(
        beforeWidgetShowCount: Int,
        currWidgetShowCount: Int,
        response4ArticleSlider: Response4ArticleSlider
    ) {

        sliderList = mutableListOf()
        sSliderList = mutableListOf()

        //val articlesData = response4FeedModel.dataOfFeedModel?.articles
        val articlesData = response4ArticleSlider.data?.feed

        var categoryId: String? = null
        var categoryName: String? = null

        val endSize = if (currWidgetShowCount < 8)
            articlesData?.size
        else
            8

        for (i in beforeWidgetShowCount until endSize!!) {

            articlesData!![i].categories?.let {
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

            sSliderList.add(
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

        viewP.sliderList.setIndicatorAnimation(IndicatorAnimations.THIN_WORM)
        viewP.sliderList.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
        viewP.sliderList.autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_RIGHT
        viewP.sliderList.scrollTimeInSec = 1
        viewP.sliderList.indicatorSelectedColor = Color.parseColor("#276cd9")
        viewP.sliderList.indicatorUnselectedColor = Color.parseColor("#ffffff")
        viewP.sliderList.isAutoCycle = false

        /*viewP.secondSliderList.setIndicatorAnimation(IndicatorAnimations.THIN_WORM)
        viewP.secondSliderList.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
        viewP.secondSliderList.autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_RIGHT
        viewP.secondSliderList.scrollTimeInSec = 1
        viewP.secondSliderList.indicatorSelectedColor = Color.parseColor("#276cd9")
        viewP.secondSliderList.indicatorUnselectedColor = Color.parseColor("#ffffff")
        viewP.secondSliderList.isAutoCycle = false*/

        sliderAdapter = SliderWithAnimAdapter(
            activityP?.applicationContext!!,
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
                        startActivity(browserIntent)
                    }
                    else -> {
                        val sSliderObj = SwipableArticleDetailObj(allArticleFeedList, index)
                        val intent = Intent(
                            activityP?.applicationContext,
                            SwipableArticleDetailActivityViewImpl::class.java
                        )
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        intent.putExtra("SWIPE_ARTICLE_DATA", Gson().toJson(sSliderObj))
                        intent.putExtra("LEGACY_ID", itemOfSlider.sliderItemLegacyId)
                        startActivity(intent)
                    }
                }

                startAnim()
            }
        }

        viewP.sliderList.setSliderAdapter(sliderAdapter!!)
        //viewP.secondSliderList.setSliderAdapter(sliderAdapter!!)

        fillSliderRecyclerViewData(response4ArticleSlider)

    }

    private fun fillSliderRecyclerViewData(response4ArticleSlider: Response4ArticleSlider) {

        val sliderListData = prepareHugeCardArticleData(
            8,
            response4ArticleSlider.data?.feed?.size!!,
            false,
            response4ArticleSlider.data.feed,
            true
        )

        sSliderList.let { listOfSlider ->
            sliderListData.let(listOfSlider::addAll)
        }

        viewP.sliderRecyclerView.layoutManager =
            LinearLayoutManager(
                activityP?.applicationContext!!,
                LinearLayoutManager.VERTICAL,
                false
            )

        //allArticleFeedList = (allArticleFeedList + sliderListData).toMutableList()

        viewP.sliderRecyclerView.adapter =
            HugeCardRecyclerViewAdapter4Slider(
                activityP!!,
                sliderListData
            ) { item, index, type, viewIcon, viewText, checked ->
                when (type) {
                    OnedioConstant.GENERAL -> {

                        if (OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility == View.VISIBLE) {
                            OnedioSingletonPattern.instance.getProfileSettingPopup()
                                ?.visibility =
                                View.GONE
                        } else {
                            OnedioSingletonPattern.instance.setActivity(
                                DashboardActivityViewImpl::class.java
                            )

                            when {
                                item.redirectUrl != "" -> {
                                    val browserIntent =
                                        Intent(Intent.ACTION_VIEW, Uri.parse(item.redirectUrl))
                                    startActivity(browserIntent)
                                }
                                else -> {
                                    /*val sSliderObj =
                                        SwipableArticleDetailObj(allSliderData, (index + 8))*/
                                    val sSliderObj =
                                        SwipableArticleDetailObj(allArticleFeedList, (index + 8))
                                    val intent = Intent(
                                        activityP?.applicationContext,
                                        SwipableArticleDetailActivityViewImpl::class.java
                                    )
                                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                    intent.putExtra("SWIPE_ARTICLE_DATA", Gson().toJson(sSliderObj))
                                    intent.putExtra("LEGACY_ID", item.legacyId)
                                    startActivity(intent)
                                }
                            }
                        }


                    }
                    OnedioConstant.FAVORITE -> {
                        if (OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility == View.VISIBLE) {
                            OnedioSingletonPattern.instance.getProfileSettingPopup()
                                ?.visibility =
                                View.GONE
                        } else {
                            if (checked) {
                                item.isUserFavorited = true
                                addFavoriteArticle(
                                    item.legacyId, viewText, viewIcon
                                )
                            } else {
                                item.isUserFavorited = false
                                deleteFavoriteArticle(
                                    item.legacyId, viewText, viewIcon
                                )
                            }
                        }
                    }
                    OnedioConstant.COMMENTS -> {
                        if (OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility == View.VISIBLE) {
                            OnedioSingletonPattern.instance.getProfileSettingPopup()
                                ?.visibility =
                                View.GONE
                        } else {
                            OnedioSingletonPattern.instance.setDeleteComment(
                                true
                            )
                            OnedioSingletonPattern.instance.setActivity(
                                DashboardActivityViewImpl::class.java
                            )

                            val intent =
                                Intent(
                                    activityP,
                                    ArticleCommentActivityViewImpl::class.java
                                )
                            intent.putExtra("ARTICLE_COMMENT_DATA", Gson().toJson(item))
                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                            startAnim()
                        }
                    }
                    OnedioConstant.SHARE -> {
                        if (OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility == View.VISIBLE) {
                            OnedioSingletonPattern.instance.getProfileSettingPopup()
                                ?.visibility =
                                View.GONE
                        } else {
                            OnedioCommon.shareArticle(
                                activityP!!,
                                item.legacyId
                            )
                        }
                    }
                    OnedioConstant.CATEGORY_ICON -> {
                        if (OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility == View.VISIBLE) {
                            OnedioSingletonPattern.instance.getProfileSettingPopup()
                                ?.visibility =
                                View.GONE
                        } else {
                            val categoryModel =
                                CategoryModel(
                                    item.categoryId!!,
                                    null,
                                    item.categoryName!!
                                )
                            when (item.categoryId) {
                                OnedioConstant.CATEGORIES_YEMEK_ID -> {
                                    OnedioSingletonPattern.instance.setActivity(
                                        DashboardActivityViewImpl::class.java
                                    )
                                    val intent =
                                        Intent(activityP, FoodActivityViewImpl::class.java)
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
                                        DashboardActivityViewImpl::class.java
                                    )

                                    val intent =
                                        Intent(activityP, VideoActivityViewImpl::class.java)
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
                                        DashboardActivityViewImpl::class.java
                                    )

                                    val intent =
                                        Intent(activityP, CategoryFeedActivityViewImpl::class.java)
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
                    }
                    OnedioConstant.BADGE_ICON -> {
                        val itemOfTag =
                            Tags()
                        itemOfTag.id = item.badgeId
                        itemOfTag.name = item.coverPhotoText

                        val intent =
                            Intent(activityP, TagsArticleActivityViewImpl::class.java)
                        intent.putExtra("TAGS_ARTICLE", Gson().toJson(itemOfTag))
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                        startAnim()
                    }
                }
            }

    }

    //==================================================================================================================
    /**
     * Fill RecyclerView Data...
     */
    //==================================================================================================================
    private fun fillRecyclerViewData(
        recyclerViewId: Int,
        beforeWidgetShowCount: Int,
        currWidgetShowCount: Int,
        response4ArticlesFeed: Response4ArticlesFeed
        //response4FeedModel: Response4FeedModel
    ) {
        val recyclerView = viewP.findViewById(recyclerViewId) as RecyclerView
        listOfRecyclerView.add(recyclerView)

        var index = currWidgetShowCount

        val articleFeedData4Widget = response4ArticlesFeed.data?.feed

        recyclerView.layoutManager =
            LinearLayoutManager(
                activityP?.applicationContext!!,
                LinearLayoutManager.VERTICAL,
                false
            )

        if (articleFeedData4Widget?.size!! != currWidgetShowCount) {
            index = articleFeedData4Widget.size
        }

        val articleFeedList = prepareHugeCardArticleData(
            beforeWidgetShowCount,
            index,
            false,
            articleFeedData4Widget,
            false
        )


        recyclerView.adapter =
            HugeCardRecyclerViewAdapter(
                activityP!!,
                articleFeedList
            ) { item, index, type, viewIcon, viewText, checked ->
                when (type) {
                    OnedioConstant.GENERAL -> {

                        if (OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility == View.VISIBLE) {
                            OnedioSingletonPattern.instance.getProfileSettingPopup()
                                ?.visibility =
                                View.GONE
                        } else {
                            /*OnedioSingletonPattern.instance.setArticleItem(
                                item
                            )*/

                            /*OnedioSingletonPattern.instance.setTabIndex(
                                0
                            )

                            OnedioSingletonPattern.instance.setActivity(
                                DashboardActivityViewImpl::class.java
                            )*/

                            when {
                                item.redirectUrl != "" -> {
                                    val browserIntent =
                                        Intent(Intent.ACTION_VIEW, Uri.parse(item.redirectUrl))
                                    startActivity(browserIntent)
                                }

                                else -> {
                                    val sHugeCardObj =
                                        SwipableArticleDetailObj(
                                            allArticleFeedList,
                                            (index + allArticleFeedCount)
                                        )
                                    val intent = Intent(
                                        activityP?.applicationContext,
                                        SwipableArticleDetailActivityViewImpl::class.java
                                    )
                                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                    intent.putExtra(
                                        "SWIPE_ARTICLE_DATA",
                                        Gson().toJson(sHugeCardObj)
                                    )
                                    intent.putExtra("LEGACY_ID", item.legacyId)
                                    startActivity(intent)
                                }
                                /*item.showInWebView -> {
                                    *//*OnedioCommon.cStartActivity(
                                                        )*//*
                                }*/
                            }
                        }


                    }
                    OnedioConstant.FAVORITE -> {
                        if (OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility == View.VISIBLE) {
                            OnedioSingletonPattern.instance.getProfileSettingPopup()
                                ?.visibility =
                                View.GONE
                        } else {
                            if (checked) {
                                item.isUserFavorited = true
                                addFavoriteArticle(
                                    item.legacyId, viewText, viewIcon
                                )
                            } else {
                                item.isUserFavorited = false
                                deleteFavoriteArticle(
                                    item.legacyId, viewText, viewIcon
                                )
                            }
                        }
                    }
                    OnedioConstant.COMMENTS -> {
                        if (OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility == View.VISIBLE) {
                            OnedioSingletonPattern.instance.getProfileSettingPopup()
                                ?.visibility =
                                View.GONE
                        } else {
                            /*OnedioSingletonPattern.instance.setArticleItem(
                                item
                            )*/
                            OnedioSingletonPattern.instance.setDeleteComment(
                                true
                            )
                            OnedioSingletonPattern.instance.setActivity(
                                DashboardActivityViewImpl::class.java
                            )

                            val intent =
                                Intent(
                                    activityP,
                                    ArticleCommentActivityViewImpl::class.java
                                )
                            intent.putExtra("ARTICLE_COMMENT_DATA", Gson().toJson(item))
                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)

                            /*OnedioCommon.cStartActivity(
                                activityP!!.applicationContext!!,
                                ArticleCommentActivityViewImpl::class.java
                            )*/

                            startAnim()
                        }
                    }
                    OnedioConstant.SHARE -> {
                        if (OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility == View.VISIBLE) {
                            OnedioSingletonPattern.instance.getProfileSettingPopup()
                                ?.visibility =
                                View.GONE
                        } else {
                            OnedioCommon.shareArticle(
                                activityP!!,
                                item.legacyId
                            )
                        }
                    }
                    OnedioConstant.CATEGORY_ICON -> {
                        if (OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility == View.VISIBLE) {
                            OnedioSingletonPattern.instance.getProfileSettingPopup()
                                ?.visibility =
                                View.GONE
                        } else {
                            val categoryModel =
                                CategoryModel(
                                    item.categoryId!!,
                                    null,
                                    item.categoryName!!
                                )
                            when (item.categoryId) {
                                OnedioConstant.CATEGORIES_YEMEK_ID -> {
                                    /*OnedioSingletonPattern.instance.setCategoryId(
                                        categoryModel
                                    )*/
                                    OnedioSingletonPattern.instance.setActivity(
                                        DashboardActivityViewImpl::class.java
                                    )
                                    /*OnedioCommon.cStartActivity(
                                        activityP?.applicationContext!!,
                                        FoodActivityViewImpl::class.java
                                    )*/

                                    val intent =
                                        Intent(activityP, FoodActivityViewImpl::class.java)
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
                                    /*OnedioSingletonPattern.instance.setCategoryId(
                                        categoryModel
                                    )*/
                                    /*OnedioSingletonPattern.instance.setTabIndex(
                                        3
                                    )*/
                                    OnedioSingletonPattern.instance.setActivity(
                                        DashboardActivityViewImpl::class.java
                                    )

                                    val intent =
                                        Intent(activityP, VideoActivityViewImpl::class.java)
                                    intent.putExtra(
                                        "ARTICLE_VIDEO_DATA",
                                        Gson().toJson(categoryModel)
                                    )
                                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                    startActivity(intent)

                                    /*OnedioCommon.cStartActivity(
                                        activityP?.applicationContext!!,
                                        VideoActivityViewImpl::class.java
                                    )*/
                                    startAnim()
                                }
                                else -> {
                                    /*OnedioSingletonPattern.instance.setCategoryId(
                                        categoryModel
                                    )*/
                                    OnedioSingletonPattern.instance.setActivity(
                                        DashboardActivityViewImpl::class.java
                                    )

                                    val intent =
                                        Intent(activityP, CategoryFeedActivityViewImpl::class.java)
                                    intent.putExtra(
                                        "ARTICLE_CATEGORY_DATA",
                                        Gson().toJson(categoryModel)
                                    )
                                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                    startActivity(intent)

                                    /*OnedioCommon.cStartActivity(
                                        activityP?.applicationContext!!,
                                        CategoryFeedActivityViewImpl::class.java
                                    )*/
                                    startAnim()
                                }
                            }
                        }
                    }
                    OnedioConstant.BADGE_ICON -> {
                        val itemOfTag =
                            Tags()
                        itemOfTag.id = item.badgeId
                        itemOfTag.name = item.coverPhotoText

                        /*OnedioSingletonPattern.instance.setTagsItem(
                            itemOfTag
                        )
                        OnedioCommon.cStartActivity(
                            activityP?.applicationContext!!,
                            TagsArticleActivityViewImpl::class.java
                        )*/
                        val intent =
                            Intent(activityP, TagsArticleActivityViewImpl::class.java)
                        intent.putExtra("TAGS_ARTICLE", Gson().toJson(itemOfTag))
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                        startAnim()
                    }
                }
            }
    }


    //==================================================================================================================
    /**
     * Prepare Huge Card Article Data..
     */
    //==================================================================================================================
    override fun prepareHugeCardArticleData(
        beforeWidgetShowCount: Int,
        currWidgetShowCount: Int,
        isInfinite: Boolean,
        articleFeed: MutableList<FeedArticleModel>,
        isSlider: Boolean
    ): MutableList<HugeCardModel> {

        var hugeCardArticleDataArr: MutableList<HugeCardModel> = mutableListOf()

        //val articlesData = response4FeedModel.dataOfFeedModel?.articles
        var startIndex = beforeWidgetShowCount
        var endIndex = currWidgetShowCount

        if (isInfinite) {
            startIndex = 0
            //endIndex = response4FeedModel.dataOfFeedModel?.articles?.size!!
            endIndex = articleFeed.size
        }


        for (i in startIndex until endIndex) {
            val itemOfArticlesHugeCardData = articleFeed[i]

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


            hugeCardArticleDataArr.add(
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

            if (!isSlider)
                allArticleFeedList.add(
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

        return hugeCardArticleDataArr
    }

    //==================================================================================================================
    /**
     * Show Loading..
     */
    //==================================================================================================================
    override fun showLoading() {
        viewP.feedAvlIndicatorProgress.playAnimation()
        viewP.feedAvlIndicatorProgress.visibility = View.VISIBLE
        /*activityP?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )*/
    }

    //==================================================================================================================
    /**
     * Hide Loading..
     */
    //==================================================================================================================
    override fun hideLoading() {
        viewP.feedAvlIndicatorProgress.cancelAnimation()
        viewP.feedAvlIndicatorProgress.visibility = View.GONE
        //activityP?.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    //==================================================================================================================
    /**
     * Show Error..
     */
    //==================================================================================================================
    override fun showError(response4ErrorObj: Response4ErrorObj, isContinue: Boolean) {
        if (!isContinue) {
            showEzDialogMessage(
                "Uyarı..!",
                response4ErrorObj.status?.message!!,
                "Tamam",
                ContextCompat.getColor(
                    activityP?.applicationContext!!,
                    R.color.tabIndicatorColor4Proile
                ),
                ContextCompat.getColor(activityP?.applicationContext!!, R.color.constWhite),
                ContextCompat.getColor(activityP?.applicationContext!!, R.color.constTextColor),
                ContextCompat.getColor(activityP?.applicationContext!!, R.color.constTextColor)
            )
        } else {
            viewP.constraintSlider.visibility = View.GONE

            getArticlesFeed(
                widgetResponse,
                page,
                sumOfWidgetCount(widgetResponse.data?.widgets!!)
            )
        }

    }

    //==================================================================================================================
    /**
     * Show EzDialog Message..
     */
    //==================================================================================================================
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
                activityP!!,
                titleText,
                messageText,
                buttonText,
                headerColor,
                titleTextColor,
                messageTextColor,
                buttonTextColor
            )

        OnedioCommon.showEzDialog4Fragment(ezDialogMessage) {
            activityP?.finishAffinity()
            exitProcess(0)
        }
    }

    //==================================================================================================================
    /**
     * Prepare Huge Card With Popular Comment...
     */
    //==================================================================================================================
    override fun prepareHugeCardWithPopularComment() {
        viewP.coverPhotoProgress.visibility = View.GONE
        viewP.articleActionPhotoProgress.visibility = View.GONE
        viewP.emojiAction.text = String(Character.toChars(0x1F60A))
        viewP.emojiAction2.text = String(Character.toChars(0x1F60A))
        viewP.emojiAction3.text = String(Character.toChars(0x1F60A))
    }

    override fun addFavoriteArticle(legacyId: Long, viewText: View, viewIcon: View) {
        homeFeedFragmentActivityViewPresenter.addFavorite(legacyId, viewText, viewIcon)
    }

    override fun handleAddFavoriteDataModel(
        response4AddFavoriteArticle: Response4AddFavoriteArticle,
        viewText: View,
        viewIcon: View,
        legacyId: Long
    ) {
        Toast.makeText(activityP?.applicationContext, "Eklendi", Toast.LENGTH_SHORT).show()

        allArticleFeedList.forEach { itemOfAllArticleList ->
            if (itemOfAllArticleList.legacyId == legacyId) {
                itemOfAllArticleList.isUserFavorited = true
            }
        }
    }

    override fun deleteFavoriteArticle(legacyId: Long, viewText: View, viewIcon: View) {
        homeFeedFragmentActivityViewPresenter.deleteFavorite(legacyId, viewText, viewIcon)
    }

    override fun handleDeleteFavoriteDataModel(
        response4AddFavoriteArticle: Response4AddFavoriteArticle,
        viewText: View,
        viewIcon: View,
        legacyId: Long
    ) {
        Toast.makeText(activityP?.applicationContext, "Silindi", Toast.LENGTH_SHORT).show()
        allArticleFeedList.forEach { itemOfAllArticleList ->
            if (itemOfAllArticleList.legacyId == legacyId) {
                itemOfAllArticleList.isUserFavorited = false
            }
        }
    }

    override fun handleArticleFeedData4Infinite(response4ArticlesFeed: Response4ArticlesFeed) {

        viewP.swiperefreshHomeFeed.isRefreshing = false
        //val articleFeedList = response4FeedModel.dataOfFeedModel?.articles
        val articleFeedList = response4ArticlesFeed.data?.feed

        if (!isFirstLoad) {
            infiniteRecyclerViewAdapter =
                HugeCardRecyclerViewAdapter(
                    activityP!!,
                    generateList(articleFeedList!!)
                ) { item, index, type, viewIcon, viewText, checked ->
                    when (type) {
                        OnedioConstant.GENERAL -> {

                            if (OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility == View.VISIBLE) {
                                OnedioSingletonPattern.instance.getProfileSettingPopup()
                                    ?.visibility = View.GONE
                            } else {
                                /*OnedioSingletonPattern.instance.setArticleItem(
                                    item
                                )*/
                                /*OnedioSingletonPattern.instance.setTabIndex(
                                    0
                                )*/
                                OnedioSingletonPattern.instance.setActivity(
                                    DashboardActivityViewImpl::class.java
                                )

                                when {
                                    item.redirectUrl != "" -> {
                                        val browserIntent =
                                            Intent(Intent.ACTION_VIEW, Uri.parse(item.redirectUrl))
                                        browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                        startActivity(browserIntent)
                                    }

                                    else -> {
                                        /*val sHugeCardObj =
                                            SwipableArticleDetailObj(
                                                allArticleFeedList,
                                                (index + currWidgetShowCount)
                                            )*/
                                        val sHugeCardObj =
                                            SwipableArticleDetailObj(
                                                allArticleFeedList,
                                                (index + articleCount4Infinite)
                                            )
                                        val intent = Intent(
                                            activityP?.applicationContext,
                                            SwipableArticleDetailActivityViewImpl::class.java
                                        )
                                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                        intent.putExtra(
                                            "SWIPE_ARTICLE_DATA",
                                            Gson().toJson(sHugeCardObj)
                                        )
                                        intent.putExtra("LEGACY_ID", item.legacyId)
                                        startActivity(intent)
                                    }
                                    /*item.categoryName != "Test" -> {
                                    }*/
                                }
                            }

                        }
                        OnedioConstant.FAVORITE -> {
                            if (OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility == View.VISIBLE) {
                                OnedioSingletonPattern.instance.getProfileSettingPopup()
                                    ?.visibility = View.GONE
                            } else {
                                if (checked)
                                    addFavoriteArticle(
                                        item.legacyId, viewText, viewIcon
                                    )
                                else
                                    deleteFavoriteArticle(
                                        item.legacyId, viewText, viewIcon
                                    )
                            }

                        }
                        OnedioConstant.COMMENTS -> {
                            if (OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility == View.VISIBLE) {
                                OnedioSingletonPattern.instance.getProfileSettingPopup()
                                    ?.visibility = View.GONE
                            } else {
                                /*OnedioSingletonPattern.instance.setArticleItem(
                                    item
                                )*/
                                OnedioSingletonPattern.instance.setDeleteComment(
                                    true
                                )
                                OnedioSingletonPattern.instance.setActivity(
                                    DashboardActivityViewImpl::class.java
                                )

                                val intent =
                                    Intent(
                                        activityP,
                                        ArticleCommentActivityViewImpl::class.java
                                    )
                                intent.putExtra("ARTICLE_COMMENT_DATA", Gson().toJson(item))
                                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                startActivity(intent)

                                /*OnedioCommon.cStartActivity(
                                    activityP?.applicationContext!!,
                                    ArticleCommentActivityViewImpl::class.java
                                )*/

                                startAnim()
                            }

                        }
                        OnedioConstant.SHARE -> {
                            if (OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility == View.VISIBLE) {
                                OnedioSingletonPattern.instance.getProfileSettingPopup()
                                    ?.visibility = View.GONE
                            } else {
                                OnedioCommon.shareArticle(
                                    activityP!!,
                                    item.legacyId
                                )
                            }

                        }
                        OnedioConstant.CATEGORY_ICON -> {
                            if (OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility == View.VISIBLE) {
                                OnedioSingletonPattern.instance.getProfileSettingPopup()
                                    ?.visibility = View.GONE
                            } else {
                                val categoryModel =
                                    CategoryModel(
                                        item.categoryId!!,
                                        null,
                                        item.categoryName!!
                                    )
                                when (item.categoryId) {
                                    OnedioConstant.CATEGORIES_YEMEK_ID -> {
                                        /*OnedioSingletonPattern.instance.setCategoryId(
                                            categoryModel
                                        )*/
                                        OnedioSingletonPattern.instance.setActivity(
                                            DashboardActivityViewImpl::class.java
                                        )

                                        val intent =
                                            Intent(activityP, FoodActivityViewImpl::class.java)
                                        intent.putExtra(
                                            "ARTICLE_FOOD_DATA",
                                            Gson().toJson(categoryModel)
                                        )
                                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                        startActivity(intent)

                                        /*OnedioCommon.cStartActivity(
                                            activityP?.applicationContext!!,
                                            FoodActivityViewImpl::class.java
                                        )*/
                                        startAnim()
                                    }
                                    OnedioConstant.CATEGORIES_VIDEO_ID -> {
                                        /*OnedioSingletonPattern.instance.setCategoryId(
                                            categoryModel
                                        )*/
                                        /*OnedioSingletonPattern.instance.setTabIndex(
                                            3
                                        )*/
                                        OnedioSingletonPattern.instance.setActivity(
                                            DashboardActivityViewImpl::class.java
                                        )

                                        val intent =
                                            Intent(activityP, VideoActivityViewImpl::class.java)
                                        intent.putExtra(
                                            "ARTICLE_VIDEO_DATA",
                                            Gson().toJson(categoryModel)
                                        )
                                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                        startActivity(intent)

                                        /*OnedioCommon.cStartActivity(
                                            activityP?.applicationContext!!,
                                            VideoActivityViewImpl::class.java
                                        )*/
                                        startAnim()
                                    }
                                    else -> {
                                        /*OnedioSingletonPattern.instance.setCategoryId(
                                            categoryModel
                                        )*/
                                        OnedioSingletonPattern.instance.setActivity(
                                            DashboardActivityViewImpl::class.java
                                        )

                                        val intent =
                                            Intent(
                                                activityP,
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
                                            activityP?.applicationContext!!,
                                            CategoryFeedActivityViewImpl::class.java
                                        )*/
                                        startAnim()
                                    }
                                }
                            }
                        }
                        OnedioConstant.BADGE_ICON -> {
                            val itemOfTag =
                                Tags()
                            itemOfTag.id = item.badgeId
                            itemOfTag.name = item.coverPhotoText

                            /*OnedioSingletonPattern.instance.setTagsItem(
                                itemOfTag
                            )
                            OnedioCommon.cStartActivity(
                                activityP?.applicationContext!!,
                                TagsArticleActivityViewImpl::class.java
                            )*/
                            val intent =
                                Intent(activityP, TagsArticleActivityViewImpl::class.java)
                            intent.putExtra("TAGS_ARTICLE", Gson().toJson(itemOfTag))
                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                            startAnim()
                        }
                    }
                }


            viewP.infiniteScrollFeedRecyclerView.adapter = infiniteRecyclerViewAdapter
            viewP.infiniteScrollFeedRecyclerView?.layoutManager =
                LinearLayoutManager(activityP)

            val itemDecorator = DividerItemDecoration(
                activityP?.applicationContext!!,
                DividerItemDecoration.VERTICAL
            )
            itemDecorator.setDrawable(
                ContextCompat.getDrawable(
                    activityP?.applicationContext!!,
                    R.drawable.recycler_view_divider
                )!!
            )
            viewP.infiniteScrollFeedRecyclerView.addItemDecoration(itemDecorator)

            isFirstLoad = true
        } else
            infiniteRecyclerViewAdapter.addItems(generateList(articleFeedList!!))

        viewP.nestedScrollViewHomeFeed.setOnScrollChangeListener(object :
            NestedScrollView.OnScrollChangeListener {
            override fun onScrollChange(
                v: NestedScrollView?,
                scrollX: Int,
                scrollY: Int,
                oldScrollX: Int,
                oldScrollY: Int
            ) {
                val aa = v?.getChildAt(0)?.measuredHeight
                val bbb = v?.measuredHeight

                if (isVisible(viewP.secondSliderHugeCard)) {
                    if (!isShowSecondSlider) {
                        val params = Bundle()
                        firebaseAnalytics.logEvent("slider_2_slide_view", params)

                        val mapOfFeedAppMetrica = HashMap<String, Any>()
                        YandexMetrica.reportEvent("slider_2_slide_view", mapOfFeedAppMetrica)
                        isShowSecondSlider = true
                    }
                }

                aa?.let {
                    if (scrollY == (aa - bbb!!)) {
                        homeFeedFragmentActivityViewPresenter.getArticleData4Infinite(
                            page,
                            sumOfWidgetCount(widgetResponse.data?.widgets!!),
                            true
                        )
                    }
                } ?: run {

                }
            }
        })

        if (!isSkeletonRunning) {
            viewP.shimmerViewContainer.stopShimmer()
            viewP.shimmerViewContainer.visibility = View.GONE
            isSkeletonRunning = true

            viewP.feedLinearLayout.visibility = View.VISIBLE


            Handler().postDelayed({
                sSliderList.let { listOfArticle ->
                    secondSSliderList.let(listOfArticle::addAll)
                }

                sSliderList.let { listOfArticle ->
                    allArticleFeedList.let(listOfArticle::addAll)
                }

                allArticleFeedList = mutableListOf()
                allArticleFeedList = sSliderList
            }, 1000)
        }
    }

    private fun generateList(articleFeedList: MutableList<FeedArticleModel>): MutableList<HugeCardModel> {
        arrayList = mutableListOf()

        arrayList = prepareHugeCardArticleData(-1, -1, true, articleFeedList, false)

        page++

        return arrayList
    }

    //==================================================================================================================
    /**
     * Load Picasso Image...
     */
    //==================================================================================================================
    private fun loadImage(imageUrl: String, imageView: ImageView, progressBar: ProgressBar) {
        Picasso.get().load(imageUrl)
            .into(imageView, object : Callback {
                override fun onSuccess() {
                    progressBar.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    progressBar.visibility = View.GONE
                    imageView.setImageResource(R.drawable.image_error_dark_mode)
                    /// Log. Errorr..
                }
            })
    }

    private fun loadImageWithoutProgress(imageUrl: String, imageView: ImageView) {
        Picasso.get().load(imageUrl)
            .into(imageView, object : Callback {
                override fun onSuccess() {
                    var a = 2
                }

                override fun onError(e: Exception?) {
                    var a = 2
                }
            })
    }

    private fun startAnim() {
        OnedioCustomIntent.startAnim(activityP!!, "l2r")
    }

    override fun onResume() {

        firebaseAnalytics = FirebaseAnalytics.getInstance(activity?.applicationContext!!)
        firebaseAnalytics.setCurrentScreen(
            activity!!,
            "Ana Sayfa",
            "HomeFeedFragmentActivityViewImpl"
        )

        listOfFavoritesArticleData = OnedioCommon.convertString2List(
            StringSharedPrefs(
                sharedPrefs,
                OnedioConstant.SHARED_PREF_FAVORITE_DATA,
                OnedioCommon.convertList2String(
                    listOfFavoritesArticleData
                )
            ).getValue()
        )

        listOfRecyclerView.forEach { itemOfRecyclerView ->
            var recyclerData =
                (itemOfRecyclerView.adapter as HugeCardRecyclerViewAdapter).getItemsaw()

            for (i in 0 until recyclerData.size) {
                listOfFavoritesArticleData.forEach { itemOfFavoriteArticleData ->
                    if (recyclerData[i].legacyId == itemOfFavoriteArticleData.legacyId) {
                        recyclerData[i].isUserFavorited = itemOfFavoriteArticleData.isFavorited
                        itemOfRecyclerView.adapter!!.notifyItemChanged(i)
                    }
                }
            }
        }

        if (!isScreenLoadMultiple)
            loadMastheadAd()

        super.onResume()
    }

    private fun isVisible(view: View?): Boolean {
        if (view == null) {
            return false
        }
        if (!view.isShown) {
            return false
        }

        val displaymetrics = DisplayMetrics()
        activityP?.windowManager?.defaultDisplay?.getMetrics(displaymetrics)
        val height = displaymetrics.heightPixels
        val width = displaymetrics.widthPixels

        val actualPosition = Rect()
        view.getGlobalVisibleRect(actualPosition)
        val screen = Rect(0, 0, width, height)
        return actualPosition.intersect(screen)
    }

    private fun loadMastheadAd() {

        if (!isNotif)
            sendFirebaseAnalyticsLogEvent()

        OnedioCommon.loadMastheadAd(viewP.publisherAdViewFeedTop, "FEED_TOP")
    }

    private fun sendFirebaseAnalyticsLogEvent() {
        val params = Bundle()
        params.putString("name", "Onedio - Sosyal İçerik Platformu")
        params.putString("type", "Ana Sayfa")

        firebaseAnalytics.logEvent("view_screen", params)

        val mapOfFeedAppMetrica = HashMap<String, Any>()
        mapOfFeedAppMetrica["name"] = "Onedio - Sosyal İçerik Platformu"
        mapOfFeedAppMetrica["type"] = "Ana Sayfa"

        YandexMetrica.reportEvent("view_screen", mapOfFeedAppMetrica)

    }

}