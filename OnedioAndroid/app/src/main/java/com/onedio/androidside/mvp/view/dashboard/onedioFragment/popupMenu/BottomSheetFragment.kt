package com.onedio.androidside.mvp.view.dashboard.onedioFragment.popupMenu

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.Rect
import android.net.Uri
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.gson.Gson
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.common.OnedioCustomIntent
import com.onedio.androidside.common.OnedioSharedPrefs
import com.onedio.androidside.common.ThemeHelper
import com.onedio.androidside.common.model.OnedioEzDialogMessageModel4Fragment
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.categoryFragment.categoryModel.CategoryModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel.Response4ArticlesFeed
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.sliderModel.FeedArticleModel
import com.onedio.androidside.mvp.model.dashboard.model.ApplicationSettingModel
import com.onedio.androidside.mvp.model.dashboard.onedioFragment.response.DataOfAllCategory
import com.onedio.androidside.mvp.model.welcomeDashboard.search.dataModel.tags.Tags
import com.onedio.androidside.mvp.view.dashboard.DashboardActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.CategoryFeedActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.food.FoodActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.video.VideoActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.swipableArticleDetail.SwipableArticleDetailActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.swipableArticleDetail.model.SwipableArticleDetailObj
import com.onedio.androidside.mvp.view.dashboard.homeFragment.widgets.hugeCardRecyclerViewAdapter.model.HugeCardModel
import com.onedio.androidside.mvp.view.dashboard.onedioFragment.adapter.OnedioButtonRecyclerViewAdapter
import com.onedio.androidside.mvp.view.dashboard.onedioFragment.popupMenu.customPopupBottomSheetFragment.PopupMenuCustomRoundBottomSheet
import com.onedio.androidside.mvp.view.dashboard.profileFragment.generalSetting.GeneralSettingActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.search.moreTag.tagsArticle.TagsArticleActivityViewImpl
import com.onedio.androidside.mvp.view.loginAndRegister.LoginAndRegisterDashboardActivityViewImpl
import com.onedio.androidside.prefs.StringSharedPrefs
import com.onedio.androidside.singleton.OnedioSingletonPattern
import com.yandex.metrica.YandexMetrica
import kotlinx.android.synthetic.main.popup_menu_bottom_sheet_fragment.view.*


class BottomSheetFragment : PopupMenuCustomRoundBottomSheet(),
    IBottomSheetFragment {

    private var mActivity: Activity? = null

    private lateinit var viewP: View

    private lateinit var bottomSheetFragmetnPresenter: BottomSheetFragmentPresenter

    private lateinit var mAdapter: OnedioButtonRecyclerViewAdapter

    private lateinit var dataOfAllCategory: ArrayList<DataOfAllCategory>

    private lateinit var response4ArticlesFeed: Response4ArticlesFeed

    private lateinit var sharedPrefs: SharedPreferences

    var listOfApplicationSetting: MutableList<ApplicationSettingModel> = mutableListOf()

    var isInfinityReq = true

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    private var isContinueInfinite = true
    private var page = 1

    private var onedioButtonDataFeedList: MutableList<HugeCardModel> = mutableListOf()

    companion object {
        fun newInstance(): BottomSheetFragment =
            BottomSheetFragment().apply {
                /*
                arguments = Bundle().apply {
                    putInt(ARG_ITEM_COUNT, itemCount)
                }
                 */
            }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bottomSheetFragmetnPresenter =
            BottomSheetFragmentPresenter(BottomSheetFragmentModel(), this)

        firebaseAnalytics = FirebaseAnalytics.getInstance(mActivity?.applicationContext!!)

        sendFirebaseAnalyticsLogEvent()

        dialog.setOnShowListener { dialog ->
            val d = dialog as BottomSheetDialog
            val bottomSheetInternal =
                d.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheetInternal?.let {
                BottomSheetBehavior.from(bottomSheetInternal)
                    .state = BottomSheetBehavior.STATE_EXPANDED
            }
        }

        viewP = inflater.inflate(R.layout.popup_menu_bottom_sheet_fragment, container, false)

        return viewP
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPrefs =
            mActivity?.getSharedPreferences(
                OnedioConstant.SHARED_PREF_FILE_NAME,
                Context.MODE_PRIVATE
            )!!

        val oButtonData = StringSharedPrefs(
            sharedPrefs,
            OnedioConstant.SHARED_PREF_ONEDIO_BUTTON,
            ""
        ).getValue<String>()

        if (oButtonData != "") {
            response4ArticlesFeed = Gson().fromJson(oButtonData, Response4ArticlesFeed::class.java)

            initBottomSheetFragmentData()

            changeIconIfDarkModeOn()

            checkApplicationSettingListData()

            clickGoToSetting()

            //prepareSectionExpandableListViewData()
            clickVitrinOrYazioItem()

            viewP.dropDownImg.setOnClickListener {
                this.dismiss()
            }

            socialMediaClickListeners()

            val sharedPref = mActivity?.applicationContext!!.getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
            val theme = sharedPref.getString("mode", "default")
            if(theme == "dark"){
                viewP.oynakazan.setImageResource(R.drawable.ic_oyna_kazan_dark_mode)
                viewP.dropDownImg.setImageResource(R.drawable.ic_drop_down_dark_mode)
                viewP.darkModeInfo.setTextColor(Color.parseColor("#FFFFFF"))
                viewP.txtInfoGoToSetting.setTextColor(Color.parseColor("#FFFFFF"))
                viewP.goToSetting.setImageResource(R.drawable.ic_setting_dark_mode)
            } else {
                viewP.oynakazan.setImageResource(R.drawable.ic_oyna_kazan)
                viewP.dropDownImg.setImageResource(R.drawable.ic_drop_down)
                viewP.darkModeInfo.setTextColor(Color.parseColor("#191919"))
                viewP.txtInfoGoToSetting.setTextColor(Color.parseColor("#191919"))
                viewP.goToSetting.setImageResource(R.drawable.ic_setting)
            }


        } else {
            this.dismiss()
        }
    }

    private fun clickVitrinOrYazioItem(){
        viewP.vitrinCardView.setOnClickListener {
            prepareAndGoTagActivity("5ec7b134c5390705202fcd8b", "Vitrin")
        }

        viewP.yazioCardView.setOnClickListener {
            prepareAndGoTagActivity("5f4e15052b1ed4d512f7d81f", "Yazio")
        }
    }

    private fun prepareAndGoTagActivity(id: String, title: String){
        val itemOfTag =
            Tags()
        itemOfTag.id = id
        itemOfTag.name = title

        val intent =
            Intent(mActivity, TagsArticleActivityViewImpl::class.java)
        intent.putExtra("TAGS_ARTICLE", Gson().toJson(itemOfTag))
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        startAnim()
    }

    private fun socialMediaClickListeners() {
        viewP.facebookSocialMedia.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(OnedioCommon.openFacebook(mActivity?.applicationContext!!))
            startActivity(intent)
        }

        viewP.twitterSocialMedia.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(OnedioCommon.openTwitter(mActivity?.applicationContext!!))
            startActivity(intent)
        }

        viewP.youtubeSocialMedia.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(OnedioCommon.openYoutube(mActivity?.applicationContext!!))
            startActivity(intent)
        }

        viewP.instagramSocialMedia.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(OnedioCommon.openInstagram(mActivity?.applicationContext!!))
            startActivity(intent)
        }

        viewP.oynakazan.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(OnedioCommon.openOynakazan(mActivity?.applicationContext!!))
            startActivity(intent)
        }
    }

    override fun initBottomSheetFragmentData() {
        //dataOfAllCategory = OnedioSingletonPattern.instance.getAllCategory()
    }

    override fun changeIconIfDarkModeOn() {
        if (OnedioSharedPrefs(mActivity?.applicationContext!!).isNightModeEnabled()) {
            viewP.dropDownImg.setImageResource(R.drawable.ic_drop_down_dark_mode)
            viewP.goToSetting.setImageResource(R.drawable.ic_setting_dark_mode)

        } else {
            viewP.dropDownImg.setImageResource(R.drawable.ic_drop_down)
            viewP.goToSetting.setImageResource(R.drawable.ic_setting)
        }
    }

    override fun checkApplicationSettingListData() {
        if (OnedioSharedPrefs(mActivity?.applicationContext!!).getApplicationSetting() != null && OnedioSharedPrefs(
                mActivity?.applicationContext!!
            ).getApplicationSetting()!!.size == 0
        ) {
            listOfApplicationSetting.add(
                ApplicationSettingModel(
                    1,
                    "Gece Modu",
                    "",
                    true,
                    "false"
                )
            )
            listOfApplicationSetting.add(
                ApplicationSettingModel(
                    2,
                    "Otomatik video oynatmak",
                    "",
                    true,
                    "false"
                )
            )
            listOfApplicationSetting.add(
                ApplicationSettingModel(
                    3,
                    "İçerik Okuduysam Gizle",
                    "",
                    true,
                    "false"
                )
            )
            listOfApplicationSetting.add(
                ApplicationSettingModel(
                    4,
                    "Veri Kullanımını Kısıtla",
                    "Videoları ve gifleri yüklemez ama imajları yükler.",
                    true,
                    "false"
                )
            )

            listOfApplicationSetting =
                listOfApplicationSetting.sortedWith(compareBy { it.index }).toMutableList()
            OnedioSharedPrefs(mActivity?.applicationContext!!).saveApplicationSetting(
                listOfApplicationSetting
            )

            setDarkModeSwitchVal(false)
        } else
            setDarkModeSwitchVal(OnedioSharedPrefs(activity?.applicationContext!!).getApplicationSetting()!![0].valOfAppSettingItem.toBoolean())

        darkModeSwitchChangeListener()

    }

    private fun setDarkModeSwitchVal(isChecked: Boolean) {
        viewP.darkModeSwitch.isChecked = isChecked
    }

    private fun darkModeSwitchChangeListener() {
        val applicationSetting: MutableList<ApplicationSettingModel> =
            OnedioSharedPrefs(activity?.applicationContext!!).getApplicationSetting()!!

        viewP.darkModeInfo.setOnClickListener{
            Toast.makeText(mActivity?.applicationContext!!, "awfawf", Toast.LENGTH_LONG).show()
        }

        viewP.darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                ThemeHelper.applyTheme(mActivity?.applicationContext!!, "dark")
                applicationSetting[0].valOfAppSettingItem = "true"

            } else {
                ThemeHelper.applyTheme(mActivity?.applicationContext!!, "light")
                applicationSetting[0].valOfAppSettingItem = "false"
            }

            OnedioSharedPrefs(activity?.applicationContext!!).saveApplicationSetting(
                applicationSetting
            )

            val intent = Intent(context, DashboardActivityViewImpl::class.java)
            val options = ActivityOptions.makeCustomAnimation(
                context,
                android.R.anim.fade_in,
                android.R.anim.fade_out
            )
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            activity?.applicationContext!!.startActivity(intent, options.toBundle())
        }
    }

    /*override fun prepareSectionExpandableListViewData() {

        viewP.recycler_view.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(mActivity)
        viewP.recycler_view.layoutManager = layoutManager

        val dividerItemDecoration = DividerItemDecoration(
            viewP.recycler_view.context,
            layoutManager.orientation
        )
        viewP.recycler_view.addItemDecoration(dividerItemDecoration)

        setOnedioButtonDataAdapter(addAllOnedioButtonData(response4ArticlesFeed.data?.feed!!))
    }

    private fun setOnedioButtonDataAdapter(onedioButtonData: MutableList<HugeCardModel>) {
        mAdapter =
            OnedioButtonRecyclerViewAdapter(
                mActivity?.applicationContext!!,
                onedioButtonData
            ) { itemHugeCardModelTrending, index, type ->

                when (type) {
                    "FEED" -> {
                        *//*OnedioSingletonPattern.instance.setArticleItem(
                            itemHugeCardModelTrending
                        )*//*
                        *//*OnedioSingletonPattern.instance.setTabIndex(
                            1
                        )*//*
                        OnedioSingletonPattern.instance.setActivity(
                            DashboardActivityViewImpl::class.java
                        )

                        when {
                            itemHugeCardModelTrending.redirectUrl != "" -> {
                                val browserIntent =
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(itemHugeCardModelTrending.redirectUrl)
                                    )
                                browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                startActivity(browserIntent)
                            }
                            else -> {
                                val sHugeCardObj =
                                    SwipableArticleDetailObj(
                                        onedioButtonDataFeedList,
                                        index
                                    )
                                val intent = Intent(
                                    mActivity,
                                    SwipableArticleDetailActivityViewImpl::class.java
                                )
                                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                intent.putExtra(
                                    "SWIPE_ARTICLE_DATA",
                                    Gson().toJson(sHugeCardObj)
                                )
                                intent.putExtra("LEGACY_ID", itemHugeCardModelTrending.legacyId)
                                startActivity(intent)
                            }
                        }
                        startAnim()
                    }
                    "CATEGORY" -> {
                        val categoryModel =
                            CategoryModel(
                                itemHugeCardModelTrending.categoryId!!,
                                null,
                                itemHugeCardModelTrending.categoryName!!
                            )
                        when (itemHugeCardModelTrending.categoryId) {
                            OnedioConstant.CATEGORIES_YEMEK_ID -> {

                                OnedioSingletonPattern.instance.setActivity(
                                    DashboardActivityViewImpl::class.java
                                )

                                val intent =
                                    Intent(
                                        mActivity?.applicationContext!!,
                                        FoodActivityViewImpl::class.java
                                    )
                                intent.putExtra("ARTICLE_FOOD_DATA", Gson().toJson(categoryModel))
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
                                    Intent(
                                        mActivity?.applicationContext!!,
                                        VideoActivityViewImpl::class.java
                                    )
                                intent.putExtra("ARTICLE_VIDEO_DATA", Gson().toJson(categoryModel))
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
                                    Intent(
                                        mActivity?.applicationContext!!,
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
                }
            }

        viewP.recycler_view.adapter = mAdapter

        viewP.onedioButtonScrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v,
                                                                                                         _,
                                                                                                         _,
                                                                                                         _,
                                                                                                         _ ->

            if (isVisible(viewP.endline)) {
                if (isContinueInfinite && isInfinityReq) {
                    isInfinityReq = false
                    bottomSheetFragmetnPresenter.getOnedioButtonData(
                        "",
                        "5ec7b134c5390705202fcd8b",
                        page,
                        15
                    )
                }
            }
        })
    }*/

    override fun handleOnedioButtonData(
        wName: String,
        response4ArticleFeed: Response4ArticlesFeed
    ) {
        isInfinityReq = true
        isContinueInfinite = response4ArticleFeed.data?.feed?.size != 0

        mAdapter.addItems(addAllOnedioButtonData(response4ArticleFeed.data?.feed!!))
    }

    private fun addAllOnedioButtonData(feedArticleModel: MutableList<FeedArticleModel>): MutableList<HugeCardModel> {
        val arrOfOnedioButtonData: MutableList<HugeCardModel> = mutableListOf()

        feedArticleModel.forEach { itemofOnedioButtonData ->

            var categoryUrl = ""
            var categoryId = ""
            var categoryName = ""

            if (itemofOnedioButtonData.categories?.size != 0) {
                itemofOnedioButtonData.categories?.let {
                    categoryUrl = itemofOnedioButtonData.categories[0].icons?.png!!
                    categoryId = itemofOnedioButtonData.categories[0].id!!
                    categoryName = itemofOnedioButtonData.categories[0].name!!
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

            var isUserFavoriteArticle: Boolean? = null
            itemofOnedioButtonData.isUserFavorite?.let {
                isUserFavoriteArticle = it
            } ?: run {
                isUserFavoriteArticle = false
            }

            var redirectUrl: String? = null
            itemofOnedioButtonData.redirectUrl?.let {
                redirectUrl = it
            } ?: run {
                redirectUrl = ""
            }

            var oArticleId: String? = null
            itemofOnedioButtonData.id?.let {
                oArticleId = it
            } ?: run {
                oArticleId = ""
            }

            var oLegacyId: Long? = null
            itemofOnedioButtonData.legacyId?.let {
                oLegacyId = it
            } ?: run {
                oLegacyId = 0
            }

            var oTitle: String? = null
            itemofOnedioButtonData.title?.let {
                oTitle = it
            } ?: run {
                oTitle = ""
            }

            var oImage: String? = null
            itemofOnedioButtonData.image?.let {
                it.alternates?.let {
                    it.standardResolution?.let {
                        it.url?.let {
                            oImage = it
                        } ?: run {
                            oImage = ""
                        }
                    } ?: run {
                        oImage = ""
                    }
                } ?: run {
                    oImage = ""
                }
            } ?: run {
                oImage = ""
            }

            var oShowInWebView: Boolean? = null
            itemofOnedioButtonData.showInWebview?.let {
                oShowInWebView = it
            } ?: run {
                oShowInWebView = false
            }


            arrOfOnedioButtonData.add(
                HugeCardModel(
                    oArticleId!!,
                    oLegacyId!!,
                    oImage,
                    categoryUrl,
                    null,
                    oTitle!!,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    isUserFavoriteArticle!!,
                    categoryName,
                    categoryId,
                    oShowInWebView!!,
                    redirectUrl!!
                )
            )

            onedioButtonDataFeedList.add(
                HugeCardModel(
                    oArticleId!!,
                    oLegacyId!!,
                    oImage,
                    categoryUrl,
                    null,
                    oTitle!!,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    isUserFavoriteArticle!!,
                    categoryName,
                    categoryId,
                    oShowInWebView!!,
                    redirectUrl!!
                )
            )
        }

        page++

        onedioButtonDataFeedList = onedioButtonDataFeedList.distinct().toMutableList()

        return arrOfOnedioButtonData
    }

    override fun clickGoToSetting() {
        viewP.goToSetting.setOnClickListener {

            /*if (OnedioCommon.isUserLogin()) {
                mActivity?.startActivity(
                    Intent(
                        mActivity?.applicationContext,
                        GeneralSettingActivityViewImpl::class.java
                    )
                )
            } else {
                OnedioSingletonPattern.instance.setActivity(DashboardActivityViewImpl::class.java)
                startActivity(
                    Intent(
                        mActivity?.applicationContext,
                        LoginAndRegisterDashboardActivityViewImpl::class.java
                    )
                )
            }*/

            startActivity(
                Intent(
                    mActivity?.applicationContext,
                    GeneralSettingActivityViewImpl::class.java
                )
            )
        }

        viewP.txtInfoGoToSetting.setOnClickListener {

            startActivity(
                Intent(
                    mActivity?.applicationContext,
                    GeneralSettingActivityViewImpl::class.java
                )
            )

            /*if (OnedioCommon.isUserLogin()) {
                mActivity?.startActivity(
                    Intent(
                        mActivity?.applicationContext,
                        GeneralSettingActivityViewImpl::class.java
                    )
                )
            } else {
                OnedioSingletonPattern.instance.setActivity(DashboardActivityViewImpl::class.java)
                startActivity(
                    Intent(
                        mActivity?.applicationContext,
                        LoginAndRegisterDashboardActivityViewImpl::class.java
                    )
                )
            }*/
        }
    }

    private fun startAnim() {
        OnedioCustomIntent.startAnim(mActivity!!, "l2r")
    }

    //==================================================================================================================
    /**
     * Show Error...
     */
    //==================================================================================================================
    override fun showError(response4ErrorObj: Response4ErrorObj) {
        showEzDialogMessage(
            "Hata..!",
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

        OnedioCommon.showEzDialog4Fragment(ezDialogMessage) { this.dismiss() }
    }

    //==================================================================================================================
    /**
     * Show Loading...
     */
    //==================================================================================================================
    override fun showLoading() {
        viewP.onedioButtonAvlIndicatorProgress.playAnimation()
        viewP.onedioButtonAvlIndicatorProgress.visibility = View.VISIBLE
        /*mActivity?.window?.setFlags(
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
        viewP.onedioButtonAvlIndicatorProgress.cancelAnimation()
        viewP.onedioButtonAvlIndicatorProgress.visibility = View.GONE
        //mActivity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)

    }

    fun isVisible(view: View?): Boolean {
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

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is Activity) {
            mActivity = context
        }
    }

    private fun sendFirebaseAnalyticsLogEvent(){
        val params = Bundle()
        params.putString("name", "Orta Buton")
        params.putString("type", "Orta Buton")

        firebaseAnalytics.logEvent("view_screen", params)

        val mapOfFeedAppMetrica = HashMap<String, Any>()
        mapOfFeedAppMetrica["name"] = "Orta Buton"
        mapOfFeedAppMetrica["type"] = "Orta Buton"

        YandexMetrica.reportEvent("view_screen", mapOfFeedAppMetrica)

    }

}