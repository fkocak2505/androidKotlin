package com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.food

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.gson.Gson
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.common.OnedioCustomIntent
import com.onedio.androidside.common.model.OnedioEzDialogMessageModel4Activity
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.categoryFragment.categoryFeed.food.CategoryFeedFoodActivityModelImpl
import com.onedio.androidside.mvp.model.dashboard.categoryFragment.categoryModel.CategoryModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel.Response4ArticlesFeed
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.sliderModel.FeedArticleModel
import com.onedio.androidside.mvp.presenter.dashboard.categotyFragment.categoryFeed.food.CategoryFeedFoodActivityPresenterImpl
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.food.adapter.CategoryFeedFoodRecyclerViewAdapter
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.video.adapter.model.VideoAdapterModel
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.optimizedArticleDetail.layutManager.PreCachingLayoutManager
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.optimizedArticleDetail.utils.DeviceUtils
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.swipableArticleDetail.SwipableArticleDetailActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.swipableArticleDetail.model.SwipableArticleDetailObj
import com.onedio.androidside.mvp.view.dashboard.homeFragment.widgets.hugeCardRecyclerViewAdapter.model.HugeCardModel
import com.yandex.metrica.YandexMetrica
import kotlinx.android.synthetic.main.activity_category_feed_food.*


class FoodActivityViewImpl : AppCompatActivity(),
    IFoodActivityViewImpl {

    private lateinit var toolBar: Toolbar

    private lateinit var categoryIdWithName: CategoryModel

    private lateinit var categoryFeedFoodActivityPresenterImpl: CategoryFeedFoodActivityPresenterImpl


    private var foodCategoryFeedList: MutableList<HugeCardModel> = mutableListOf()
    private var arrOfFoodData: MutableList<VideoAdapterModel> = mutableListOf()

    private lateinit var filterParamDate: String
    private lateinit var filterParamSort: String
    var mainPage = 1

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    private var isScreenLoadMultiple: Boolean = false

    private var theme: String = ""

    private lateinit var mLayoutManager: PreCachingLayoutManager
    private var isLoading: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_feed_food)

        val sharedPref =
            getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        theme = sharedPref.getString("mode", "default")!!

        savedInstanceState?.let {
            isScreenLoadMultiple = true
        } ?: run {
            isScreenLoadMultiple = false
        }

        initCategoryFeedFoodComponent()

    }

    override fun initCategoryFeedFoodComponent() {
        //supportActionBar?.hide()

        //categoryIdWithName = OnedioSingletonPattern.instance.getCategoryId()
        intent.getStringExtra("ARTICLE_FOOD_DATA")?.let {
            categoryIdWithName = Gson().fromJson(
                intent.getStringExtra("ARTICLE_FOOD_DATA"),
                CategoryModel::class.java
            )

            categoryFeedFoodActivityPresenterImpl =
                CategoryFeedFoodActivityPresenterImpl(
                    CategoryFeedFoodActivityModelImpl(),
                    this
                )

            prepareToolbar()

            filterParamDate = ""
            filterParamSort = ""

            getFoodArticleData(
                categoryIdWithName.id,
                mainPage,
                15,
                filterParamDate,
                filterParamSort
            )

            clickDateSort()

            clickPopularitySort()

            showPopularitySortMenu()

            setRecyclerViewConfig()

        } ?: run {
            onBackPressed()
        }

    }

    private fun prepareToolbar() {
        toolBar = toolBar4CategoryFood as Toolbar

        toolBar.title = "Yemek"
        toolBar.setTitleTextAppearance(this, R.style.MuliSemiboldTextAppearance)
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)


        toolBar.setOnClickListener {

        }

        if (theme == "dark") {
            toolBar.setTitleTextColor(Color.parseColor("#FFFFFF"))
            toolBar.setNavigationIcon(R.drawable.ic_back_dark_mode)

            dateSort.background = ContextCompat.getDrawable(
                applicationContext,
                R.drawable.custom_button_sort_selected_dark_mode
            )
            popularitySort.background = ContextCompat.getDrawable(
                applicationContext,
                R.drawable.custom_button_sort_unselected_dark_mode
            )
            popularMenu.setImageResource(R.drawable.ic_popular_menu_2)

        } else {
            toolBar.setTitleTextColor(Color.parseColor("#191919"))
            toolBar.setNavigationIcon(R.drawable.ic_back)

            dateSort.background = ContextCompat.getDrawable(
                applicationContext,
                R.drawable.custom_button_sort_selected
            )
            popularitySort.background = ContextCompat.getDrawable(
                applicationContext,
                R.drawable.custom_button_sort_unselected
            )
            popularMenu.setImageResource(R.drawable.ic_popular_menu)
        }

    }

    /*private fun openVisibility4Toolbar() {
        constraintLayout.visibility = View.VISIBLE
    }
*/
    override fun getFoodArticleData(
        categoryId: String,
        page: Int,
        perPage: Int,
        dateFilterType: String,
        sortType: String
    ) {
        categoryFeedFoodActivityPresenterImpl.getCategoryFeedFoodDataNew(
            false,
            categoryId,
            page,
            perPage,
            dateFilterType,
            sortType,
            true
        )
    }

    override fun handleArticleCategoryFoodData(response4ArticlesFeed: Response4ArticlesFeed) {
        paginationRecyclerView4CategoryFeedFood.visibility = View.VISIBLE

        val foodData = response4ArticlesFeed.data?.feed!!

        if (arrOfFoodData.size == 0) {
            prepareFoodData(foodData)

            paginationRecyclerView4CategoryFeedFood.adapter = CategoryFeedFoodRecyclerViewAdapter(
                applicationContext,
                arrOfFoodData
            ) { itemOfVideoData, type ->
                handleClickViews(itemOfVideoData, type)
            }
        } else {
            arrOfFoodData.removeAt(arrOfFoodData.size - 1)
            paginationRecyclerView4CategoryFeedFood.adapter?.notifyItemRemoved(arrOfFoodData.size)
            isLoading = false

            prepareFoodData(foodData)

            paginationRecyclerView4CategoryFeedFood.adapter?.notifyDataSetChanged()
        }

        paginationRecyclerView4CategoryFeedFood.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastPosition = mLayoutManager.findLastVisibleItemPosition()
                val listSize = arrOfFoodData.size

                if (!isLoading && listSize == (lastPosition + 1)) {
                    mainPage++
                    infiniteScroll4FoodData(mLayoutManager)
                    isLoading = true
                }
            }
        })
    }

    private fun handleClickViews(itemOfVideoData: VideoAdapterModel, type: String) {
        when {
            itemOfVideoData.redirectUrl != "" -> {
                val browserIntent =
                    Intent(Intent.ACTION_VIEW, Uri.parse(itemOfVideoData.redirectUrl))
                browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(browserIntent)
            }
            else -> {
                val sHugeCardObj =
                    SwipableArticleDetailObj(
                        foodCategoryFeedList,
                        0
                    )
                val intent = Intent(
                    applicationContext,
                    SwipableArticleDetailActivityViewImpl::class.java
                )
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.putExtra(
                    "SWIPE_ARTICLE_DATA",
                    Gson().toJson(sHugeCardObj)
                )
                intent.putExtra("LEGACY_ID", itemOfVideoData.legacyId)
                startActivity(intent)
            }
        }
        startAnim()
    }

    private fun prepareFoodData(foodData: MutableList<FeedArticleModel>): MutableList<VideoAdapterModel> {

        sortInfo4Comments.visibility = View.VISIBLE
        dateSort.visibility = View.VISIBLE
        popularitySort.visibility = View.VISIBLE

        if (arrOfFoodData.size == 0)
            arrOfFoodData.add(
                VideoAdapterModel(
                    "googleAdsTop",
                    "",
                    0,
                    "",
                    "",
                    "",
                    "",
                    "",
                    " ",
                    "",
                    "",
                    false,
                    "",
                    false,
                    false
                )
            )

        for (i in 0 until foodData.size) {
            var articleId: String? = null
            foodData[i].id?.let {
                articleId = it
            } ?: run {
                articleId = ""
            }

            var legacyId: Long? = null
            foodData[i].legacyId?.let {
                legacyId = it
            } ?: run {
                legacyId = 0
            }

            var image: String? = null
            foodData[i].image?.let {
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

            var videoPath: String? = null
            foodData[i].videoData?.let {
                it.video?.let {
                    videoPath = it
                } ?: run {
                    videoPath = ""
                }
            } ?: run {
                videoPath = ""
            }

            var title: String? = null
            foodData[i].title?.let {
                title = it
            } ?: run {
                title = ""
            }

            var createDate: String? = null
            foodData[i].createDate?.let {
                createDate = it
            } ?: run {
                createDate = "2020-07-02T06:01:12.691Z"
            }

            var showInWebview: Boolean? = null
            foodData[i].showInWebview?.let {
                showInWebview = it
            } ?: run {
                showInWebview = false
            }

            var categoryId: String? = null
            var categoryName: String? = null

            foodData[i].categories?.let {
                if (foodData[i].categories?.size != 0) {
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

            var description: String? = null
            foodData[i].description?.let {
                description = it
            } ?: run {
                description = ""
            }

            var redirectUrl: String? = null
            foodData[i].redirectUrl?.let {
                redirectUrl = it
            } ?: run {
                redirectUrl = ""
            }

            var viewTotal = ""
            foodData[i].stats?.let {
                it.viewsTotal?.let {
                    viewTotal = it.toString()
                } ?: run {
                    viewTotal = "0"
                }
            } ?: run {
                viewTotal = "0"
            }

            var isHideCountLayout: Boolean = false
            foodData[i].tags?.let {
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

            val type = if (typeOfFoodCardIsVideo(foodData[i])) {
                "video"
            } else {
                "text"
            }


            arrOfFoodData.add(
                VideoAdapterModel(
                    type,
                    articleId!!,
                    legacyId!!,
                    image!!,
                    videoPath!!,
                    title!!,
                    description!!,
                    viewTotal,
                    OnedioCommon.convertFeedDate(createDate!!),
                    categoryId!!,
                    categoryName!!,
                    showInWebview!!,
                    redirectUrl!!,
                    isHideCountLayout
                )
            )

            foodCategoryFeedList.add(
                HugeCardModel(
                    articleId!!,
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
                    categoryName!!,
                    categoryId!!,
                    showInWebview!!,
                    redirectUrl!!
                )
            )

        }

        return arrOfFoodData

    }

    private fun infiniteScroll4FoodData(layoutManager: PreCachingLayoutManager) {
        arrOfFoodData.add(
            VideoAdapterModel(
                "loading",
                "",
                0,
                "",
                "",
                "",
                "",
                "",
                " ",
                "",
                "",
                false,
                "",
                false,
                false
            )
        )

        paginationRecyclerView4CategoryFeedFood.adapter?.notifyItemInserted(arrOfFoodData.size - 1)
        layoutManager.scrollToPosition(arrOfFoodData.size)
        categoryFeedFoodActivityPresenterImpl.getCategoryFeedFoodDataNew(
            false,
            categoryIdWithName.id,
            mainPage,
            15,
            filterParamDate,
            filterParamSort,
            false
        )
    }

    private fun clickDateSort() {
        dateSort.setOnClickListener {
            changeSortingButton2Date()

            foodCategoryFeedList = mutableListOf()

            filterParamDate = ""
            filterParamSort = ""
            mainPage = 1

            paginationRecyclerView4CategoryFeedFood.visibility = View.INVISIBLE

            arrOfFoodData = mutableListOf()

            categoryFeedFoodActivityPresenterImpl.getCategoryFeedFoodDataNew(
                true,
                categoryIdWithName.id,
                mainPage,
                15,
                filterParamDate,
                filterParamSort,
                true
            )

        }
    }

    private fun clickPopularitySort() {
        popularitySort.setOnClickListener {

            foodCategoryFeedList = mutableListOf()
            arrOfFoodData = mutableListOf()

            if (dotLast24Hour.visibility == View.VISIBLE) {
                dotLast24Hour.visibility = View.VISIBLE
                dotLastWeek.visibility = View.INVISIBLE
                dotLastMonth.visibility = View.INVISIBLE
                dotAllTime.visibility = View.INVISIBLE

                popularitySortMenu.visibility = View.INVISIBLE

                paginationRecyclerView4CategoryFeedFood.visibility = View.INVISIBLE

                filterParamDate = "popular"
                filterParamSort = "1day"
                mainPage = 1

                categoryFeedFoodActivityPresenterImpl.getCategoryFeedFoodDataNew(
                    true,
                    categoryIdWithName.id,
                    mainPage,
                    15,
                    filterParamDate,
                    filterParamSort,
                    true
                )
            }

            changeSortingButton2Popularity()

        }
    }

    private fun changeSortingButton2Date() {
        infoPopularite.setTextColor(Color.parseColor("#777777"))

        if (theme == "dark") {
            popularMenu.setImageResource(R.drawable.ic_popular_menu_2)
            popularitySort.background = ContextCompat.getDrawable(
                applicationContext,
                R.drawable.custom_button_sort_unselected_dark_mode
            )

            dateSort.background = ContextCompat.getDrawable(
                applicationContext,
                R.drawable.custom_button_sort_selected_dark_mode
            )

        } else {
            popularMenu.setImageResource(R.drawable.ic_popular_menu)
            popularitySort.background = ContextCompat.getDrawable(
                applicationContext,
                R.drawable.custom_button_sort_unselected
            )

            dateSort.background = ContextCompat.getDrawable(
                applicationContext,
                R.drawable.custom_button_sort_selected
            )
        }

        infoDate.setTextColor(Color.parseColor("#FFFFFF"))
        popularitySortMenu.visibility = View.INVISIBLE

    }

    private fun changeSortingButton2Popularity() {
        infoPopularite.setTextColor(Color.parseColor("#FFFFFF"))

        if (theme == "dark") {
            popularMenu.setImageResource(R.drawable.ic_popular_menu_2)
            popularitySort.background = ContextCompat.getDrawable(
                applicationContext,
                R.drawable.custom_button_sort_selected_dark_mode
            )

            dateSort.background = ContextCompat.getDrawable(
                applicationContext,
                R.drawable.custom_button_sort_unselected_dark_mode
            )

            popularitySortMenu.background =
                ContextCompat.getDrawable(applicationContext, R.drawable.layout_border_dark_mode)
        } else {
            popularMenu.setImageResource(R.drawable.ic_popular_menu)
            popularitySort.background = ContextCompat.getDrawable(
                applicationContext,
                R.drawable.custom_button_sort_selected
            )

            dateSort.background = ContextCompat.getDrawable(
                applicationContext,
                R.drawable.custom_button_sort_unselected
            )

            popularitySortMenu.background =
                ContextCompat.getDrawable(applicationContext, R.drawable.layout_border)
        }

        infoDate.setTextColor(Color.parseColor("#777777"))
        popularitySortMenu.visibility = View.VISIBLE
    }

    private fun showPopularitySortMenu() {
        last24Hour.setOnClickListener {
            dotLast24Hour.visibility = View.VISIBLE
            dotLastWeek.visibility = View.INVISIBLE
            dotLastMonth.visibility = View.INVISIBLE
            dotAllTime.visibility = View.INVISIBLE

            popularitySortMenu.visibility = View.INVISIBLE

            foodCategoryFeedList = mutableListOf()
            arrOfFoodData = mutableListOf()

            paginationRecyclerView4CategoryFeedFood.visibility = View.INVISIBLE

            filterParamDate = "popular"
            filterParamSort = "1day"
            mainPage = 1

            categoryFeedFoodActivityPresenterImpl.getCategoryFeedFoodDataNew(
                true,
                categoryIdWithName.id,
                mainPage,
                15,
                filterParamDate,
                filterParamSort,
                true
            )

        }

        lastWeek.setOnClickListener {
            dotLast24Hour.visibility = View.INVISIBLE
            dotLastWeek.visibility = View.VISIBLE
            dotLastMonth.visibility = View.INVISIBLE
            dotAllTime.visibility = View.INVISIBLE

            popularitySortMenu.visibility = View.INVISIBLE

            foodCategoryFeedList = mutableListOf()
            arrOfFoodData = mutableListOf()

            paginationRecyclerView4CategoryFeedFood.visibility = View.INVISIBLE

            filterParamDate = "popular"
            filterParamSort = "1week"
            mainPage = 1

            categoryFeedFoodActivityPresenterImpl.getCategoryFeedFoodDataNew(
                true,
                categoryIdWithName.id,
                mainPage,
                15,
                filterParamDate,
                filterParamSort,
                true
            )
        }

        lastMonth.setOnClickListener {
            dotLast24Hour.visibility = View.INVISIBLE
            dotLastWeek.visibility = View.INVISIBLE
            dotLastMonth.visibility = View.VISIBLE
            dotAllTime.visibility = View.INVISIBLE

            popularitySortMenu.visibility = View.INVISIBLE

            foodCategoryFeedList = mutableListOf()
            arrOfFoodData = mutableListOf()

            paginationRecyclerView4CategoryFeedFood.visibility = View.INVISIBLE

            filterParamDate = "popular"
            filterParamSort = "1month"
            mainPage = 1

            categoryFeedFoodActivityPresenterImpl.getCategoryFeedFoodDataNew(
                true,
                categoryIdWithName.id,
                mainPage,
                15,
                filterParamDate,
                filterParamSort,
                true
            )
        }

        allTime.setOnClickListener {
            dotLast24Hour.visibility = View.INVISIBLE
            dotLastWeek.visibility = View.INVISIBLE
            dotLastMonth.visibility = View.INVISIBLE
            dotAllTime.visibility = View.VISIBLE

            popularitySortMenu.visibility = View.INVISIBLE

            foodCategoryFeedList = mutableListOf()
            arrOfFoodData = mutableListOf()

            paginationRecyclerView4CategoryFeedFood.visibility = View.INVISIBLE

            filterParamDate = "popular"
            filterParamSort = ""
            mainPage = 1

            categoryFeedFoodActivityPresenterImpl.getCategoryFeedFoodDataNew(
                true,
                categoryIdWithName.id,
                mainPage,
                15,
                filterParamDate,
                filterParamSort,
                true
            )
        }
    }

    private fun setRecyclerViewConfig() {
        mLayoutManager =
            PreCachingLayoutManager(
                applicationContext,
                RecyclerView.VERTICAL,
                false
            )

        mLayoutManager.setExtraLayoutSpace(DeviceUtils.getScreenHeight(applicationContext))
        paginationRecyclerView4CategoryFeedFood.layoutManager = mLayoutManager
        paginationRecyclerView4CategoryFeedFood.setItemViewCacheSize(5)
    }

    /*private fun prepareArticleData(
        isFirstLoad: Boolean,
        articles: MutableList<FeedArticleModel>
    ): MutableList<CategoryItem4Food> {
        val arrOfCategoryItem: MutableList<CategoryItem4Food> = mutableListOf()

        if (!isFirstLoad) {
            for (i in 0 until articles.size) {

                var articleId: String? = null
                articles[i].id?.let {
                    articleId = it
                } ?: run {
                    articleId = ""
                }

                var legacyId: Long? = null
                articles[i].legacyId?.let {
                    legacyId = it
                } ?: run {
                    legacyId = 0
                }

                var image: String? = null
                articles[i].image?.let {
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

                var videoPath: String? = null
                articles[i].videoData?.let {
                    it.video?.let {
                        videoPath = it
                    } ?: run {
                        videoPath = ""
                    }
                } ?: run {

                }

                var title: String? = null
                articles[i].title?.let {
                    title = it
                } ?: run {
                    title = ""
                }

                var createDate: String? = null
                articles[i].createDate?.let {
                    createDate = it
                } ?: run {
                    createDate = "2020-07-02T06:01:12.691Z"
                }

                var showInWebview: Boolean? = null
                articles[i].showInWebview?.let {
                    showInWebview = it
                } ?: run {
                    showInWebview = false
                }

                var categoryId: String? = null
                var categoryName: String? = null

                articles[i].categories?.let {
                    if (articles[i].categories?.size != 0) {
                        categoryId = articles[i].categories?.get(0)?.id
                        categoryName = articles[i].categories?.get(0)?.name
                    } else {
                        categoryId = ""
                        categoryName = ""
                    }
                } ?: run {
                    categoryId = ""
                    categoryName = ""
                }

                var description: String? = null
                articles[i].description?.let {
                    description = it
                } ?: run {
                    description = ""
                }

                var redirectUrl: String? = null
                articles[i].redirectUrl?.let {
                    redirectUrl = it
                } ?: run {
                    redirectUrl = ""
                }

                var viewTotal = ""
                articles[i].stats?.let {
                    it.viewsTotal?.let {
                        viewTotal = it.toString()
                    } ?: run {
                        viewTotal = "0"
                    }
                } ?: run {
                    viewTotal = "0"
                }

                var isHideCountLayout: Boolean = false
                articles[i].tags?.let {
                    if(it.size != 0){
                        it.find { it == "~no-visitcount"}?.let {
                            isHideCountLayout = true
                        }?: run{
                            isHideCountLayout = false
                        }
                    }else{
                        isHideCountLayout = false
                    }
                }?: run{
                    isHideCountLayout = false
                }

                if (typeOfFoodCardIsVideo(articles[i])) {
                    arrOfCategoryItem.add(
                        CategoryItem4Food(
                            "video",
                            articleId!!,
                            legacyId!!,
                            image!!,
                            videoPath!!,
                            title!!,
                            description!!,
                            viewTotal,
                            OnedioCommon.convertFeedDate(createDate!!),
                            categoryId!!,
                            categoryName!!,
                            showInWebview!!,
                            redirectUrl!!,
                            isHideCountLayout
                        )
                    )

                    foodCategoryFeedList.add(
                        HugeCardModel(
                            articleId!!,
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
                            categoryName!!,
                            categoryId!!,
                            showInWebview!!,
                            redirectUrl!!
                        )
                    )

                } else {
                    arrOfCategoryItem.add(
                        CategoryItem4Food(
                            "text",
                            articleId!!,
                            legacyId!!,
                            image!!,
                            description!!,
                            title!!,
                            description!!,
                            viewTotal,
                            OnedioCommon.convertFeedDate(createDate!!),
                            categoryId!!,
                            categoryName!!,
                            showInWebview!!,
                            redirectUrl!!,
                            isHideCountLayout
                        )
                    )

                    foodCategoryFeedList.add(
                        HugeCardModel(
                            articleId!!,
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
                            categoryName!!,
                            categoryId!!,
                            showInWebview!!,
                            redirectUrl!!
                        )
                    )
                }
            }
        } else {
            articles.forEach { itemOfArticleFeed ->

                var categoryId: String? = null
                var categoryName: String? = null

                itemOfArticleFeed.categories?.let {
                    categoryId = itemOfArticleFeed.categories[0].id
                    categoryName = itemOfArticleFeed.categories[0].name
                } ?: run {
                    categoryId = ""
                    categoryName = ""
                }

                var description: String? = null
                itemOfArticleFeed.description?.let {
                    description = it
                } ?: run {
                    description = ""
                }

                var redirectUrl: String? = null
                itemOfArticleFeed.redirectUrl?.let {
                    redirectUrl = it
                } ?: run {
                    redirectUrl = ""
                }

                var viewTotal = ""
                itemOfArticleFeed.stats?.let {
                    it.viewsTotal?.let {
                        viewTotal = it.toString()
                    } ?: run {
                        viewTotal = "0"
                    }
                } ?: run {
                    viewTotal = "0"
                }

                var articleId: String? = null
                itemOfArticleFeed.id?.let {
                    articleId = it
                } ?: run {
                    articleId = ""
                }

                var legacyId: Long? = null
                itemOfArticleFeed.legacyId?.let {
                    legacyId = it
                } ?: run {
                    legacyId = 0
                }

                var image: String? = null
                itemOfArticleFeed.image?.let {
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

                var videoPath: String? = null
                itemOfArticleFeed.videoData?.let {
                    it.video?.let {
                        videoPath = it
                    } ?: run {
                        videoPath = ""
                    }
                } ?: run {
                    videoPath = ""
                }

                var title: String? = null
                itemOfArticleFeed.title?.let {
                    title = it
                } ?: run {
                    title = ""
                }

                var createDate: String? = null
                itemOfArticleFeed.createDate?.let {
                    createDate = it
                } ?: run {
                    createDate = ""
                }

                var showInWebview: Boolean? = null
                itemOfArticleFeed.showInWebview?.let {
                    showInWebview = it
                } ?: run {
                    showInWebview = false
                }

                var isHideCountLayout: Boolean = false
                itemOfArticleFeed.tags?.let {
                    if(it.size != 0){
                        it.find { it == "~no-visitcount"}?.let {
                            isHideCountLayout = true
                        }?: run{
                            isHideCountLayout = false
                        }
                    }else{
                        isHideCountLayout = false
                    }

                }?: run{
                    isHideCountLayout = false
                }

                if (typeOfFoodCardIsVideo(itemOfArticleFeed)) {
                    arrOfCategoryItem.add(
                        CategoryItem4Food(
                            "video",
                            articleId!!,
                            legacyId!!,
                            image!!,
                            videoPath!!,
                            title!!,
                            description!!,
                            viewTotal,
                            OnedioCommon.convertFeedDate(
                                createDate!!
                            ),
                            categoryId!!,
                            categoryName!!,
                            showInWebview!!,
                            redirectUrl!!,
                            isHideCountLayout
                        )
                    )

                    foodCategoryFeedList.add(
                        HugeCardModel(
                            articleId!!,
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
                            categoryName!!,
                            categoryId!!,
                            showInWebview!!,
                            redirectUrl!!
                        )
                    )

                } else {
                    arrOfCategoryItem.add(
                        CategoryItem4Food(
                            "text",
                            articleId!!,
                            legacyId!!,
                            image!!,
                            description!!,
                            title!!,
                            description!!,
                            viewTotal,
                            OnedioCommon.convertFeedDate(
                                createDate!!
                            ),
                            categoryId!!,
                            categoryName!!,
                            showInWebview!!,
                            redirectUrl!!,
                            isHideCountLayout
                        )
                    )

                    foodCategoryFeedList.add(
                        HugeCardModel(
                            articleId!!,
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
                            categoryName!!,
                            categoryId!!,
                            showInWebview!!,
                            redirectUrl!!
                        )
                    )

                }
            }
        }

        mainPage++

        foodCategoryFeedList = foodCategoryFeedList.distinct().toMutableList()

        return arrOfCategoryItem

    }*/

    private fun typeOfFoodCardIsVideo(itemOfArticleFeed: FeedArticleModel): Boolean {
        val videoLink = itemOfArticleFeed.videoData
        videoLink?.let {
            return true
        } ?: run {
            return false
        }
    }

    //==================================================================================================================
    /**
     * Show Loading...
     */
    //==================================================================================================================
    override fun showLoading() {
        categoryFeedFoodAvlIndicatorProgress.playAnimation()
        categoryFeedFoodAvlIndicatorProgress.visibility = View.VISIBLE
        /*window?.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )*/
    }

    //==================================================================================================================
    /**
     * Show Loading...
     */
    //==================================================================================================================
    override fun showLoading4List() {
        categoryFeedVideoAvlIndicatorProgress4List.playAnimation()
        categoryFeedVideoAvlIndicatorProgress4List.visibility = View.VISIBLE
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
        categoryFeedFoodAvlIndicatorProgress.cancelAnimation()
        categoryFeedFoodAvlIndicatorProgress.visibility = View.GONE
        //window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)

    }

    //==================================================================================================================
    /**
     * Hide Loading
     */
    //==================================================================================================================
    override fun hideLoading4List() {
        categoryFeedVideoAvlIndicatorProgress4List.cancelAnimation()
        categoryFeedVideoAvlIndicatorProgress4List.visibility = View.GONE
        //window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)

    }

    //==================================================================================================================
    /**
     * Show Error...
     */
    //==================================================================================================================
    override fun showError(response4ErrorObj: Response4ErrorObj) {
        showEzDialogMessage(
            "Uyarı..!",
            response4ErrorObj.status?.message!!,
            "Tamam",
            ContextCompat.getColor(
                applicationContext!!,
                R.color.tabIndicatorColor4Proile
            ),
            ContextCompat.getColor(applicationContext!!, R.color.constWhite),
            ContextCompat.getColor(applicationContext!!, R.color.constTextColor),
            ContextCompat.getColor(applicationContext!!, R.color.constTextColor)
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
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
            OnedioEzDialogMessageModel4Activity(
                this,
                titleText,
                messageText,
                buttonText,
                headerColor,
                titleTextColor,
                messageTextColor,
                buttonTextColor
            )

        OnedioCommon.showEzDialog4Activity(ezDialogMessage) { onBackPressed() }

    }

    private fun startAnim() {
        OnedioCustomIntent.startAnim(this, "l2r")
    }

    override fun onResume() {

        firebaseAnalytics = FirebaseAnalytics.getInstance(this)
        firebaseAnalytics.setCurrentScreen(this, "Yemek", "FoodActivityViewImpl")

        if (!isScreenLoadMultiple)
            sendFirebaseAnalyticsLogEvent()

        super.onResume()

    }

    private fun sendFirebaseAnalyticsLogEvent() {
        val params = Bundle()
        params.putString("name", "Yemek")
        params.putString("type", "Kategori Liste")
        params.putString("category1", "Yemek")

        firebaseAnalytics.logEvent("view_screen", params)

        val mapOfFeedAppMetrica = HashMap<String, Any>()
        mapOfFeedAppMetrica["name"] = "Yemek"
        mapOfFeedAppMetrica["type"] = "Kategori Liste"
        mapOfFeedAppMetrica["category1"] = "Yemek"

        YandexMetrica.reportEvent("view_screen", mapOfFeedAppMetrica)

    }

}