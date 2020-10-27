package com.onedio.androidside.mvp.view.dashboard.search

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.gson.Gson
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.common.OnedioCustomIntent
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel.Response4ArticlesFeed
import com.onedio.androidside.mvp.model.welcomeDashboard.search.SearchActivityModelImpl
import com.onedio.androidside.mvp.model.welcomeDashboard.search.dataModel.FeaturedNewsDataModel
import com.onedio.androidside.mvp.model.welcomeDashboard.search.dataModel.SearchListDataModel
import com.onedio.androidside.mvp.model.welcomeDashboard.search.dataModel.article.Articles
import com.onedio.androidside.mvp.model.welcomeDashboard.search.dataModel.article.Response4Article
import com.onedio.androidside.mvp.model.welcomeDashboard.search.dataModel.tags.Response4Tags
import com.onedio.androidside.mvp.model.welcomeDashboard.search.dataModel.tags.Tags
import com.onedio.androidside.mvp.model.welcomeDashboard.search.dataModel.users.Response4UserSearch
import com.onedio.androidside.mvp.model.welcomeDashboard.search.dataModel.users.Users
import com.onedio.androidside.mvp.presenter.welcomeDashboard.search.SearchActivityViewPresenterImpl
import com.onedio.androidside.mvp.view.dashboard.DashboardActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.ArticleDetailActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.TestArticleDetailActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.profile.ProfileActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.widgets.hugeCardRecyclerViewAdapter.model.HugeCardModel
import com.onedio.androidside.mvp.view.dashboard.search.gridAdapter.FeaturedNewsGridAdapter
import com.onedio.androidside.mvp.view.dashboard.search.moreContent.MoreContentActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.search.moreTag.MoreTagActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.search.moreTag.tagsArticle.TagsArticleActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.search.moreUser.MoreUserActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.search.userSearchAdapter.ContentSearchAdapter
import com.onedio.androidside.mvp.view.dashboard.search.userSearchAdapter.UserSearchListAdapter
import com.onedio.androidside.singleton.OnedioSingletonPattern
import com.yandex.metrica.YandexMetrica
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.activity_search.view.*
import spencerstudios.com.ezdialoglib.EZDialog

class SearchFragmentActivityViewImpl : Fragment(),
    ISearchAcitivityView {

    private var mActivity: Activity? = null

    private lateinit var viewP: View

    private lateinit var searchActivityViewPresenterImpl: SearchActivityViewPresenterImpl

    private var mFeaturedNewsList: MutableList<FeaturedNewsDataModel> = mutableListOf()
    private var mTagsList: MutableList<String> = mutableListOf()
    private var mUserList: MutableList<SearchListDataModel> = mutableListOf()
    private var mArticleList: MutableList<FeaturedNewsDataModel> = mutableListOf()

    private var mTagsResponseList: MutableList<Tags> = mutableListOf()
    private var mUserResponseList: MutableList<Users> = mutableListOf()
    private var mContentResponseList: MutableList<Articles> = mutableListOf()

    private var searchedWord: String = ""

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    //==================================================================================================================
    /**
     * Init New Instance 4 fragment...
     */
    //==================================================================================================================
    companion object {
        fun newInstance(): SearchFragmentActivityViewImpl {
            val fragmentHome =
                SearchFragmentActivityViewImpl()
            val args = Bundle()
            fragmentHome.arguments = args
            return fragmentHome
        }
    }

    //==================================================================================================================
    /**
     * onCreateView Override method 4 Fragment
     */
    //==================================================================================================================
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //viewP = inflater.inflate(R.layout.fragment_profile, container, false)
        viewP = inflater.inflate(R.layout.activity_search, container, false)

        initSearchActivityComponent()

        getMostSharedArticle()

        clickMoreContent()

        clickMoreUser()

        clickMoreTag()

        clickSearchArea()

        changeComponentTypeFace()

        clickEmptyArea()

        sendFirebaseAnalyticsLogEvent()

        changeTheme()

        return viewP

    }

    private fun changeTheme(){
        val sharedPref = mActivity?.applicationContext!!.getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        val theme = sharedPref.getString("mode", "default")

        if(theme == "dark"){
            viewP.searchArea.background = ContextCompat.getDrawable(mActivity?.applicationContext!!, R.drawable.custom_edittext_dark_mode)
            viewP.searchArea.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_search_dark_mode, 0,0,0)
            viewP.searchArea.setHintTextColor(Color.parseColor("#FFFFFF"))
            viewP.searchArea.setTextColor(Color.parseColor("#FFFFFF"))

            viewP.featuredNewsTitleInfo.setTextColor(Color.parseColor("#FFFFFF"))
            viewP.searchContentTitleInfo.setTextColor(Color.parseColor("#FFFFFF"))
            viewP.searchUserTitleInfo.setTextColor(Color.parseColor("#FFFFFF"))
            viewP.searchTagTitleInfo.setTextColor(Color.parseColor("#FFFFFF"))

            viewP.searchTagsStaggeredGrid.setBackgroundDrawable(R.drawable.item_bg_1_dark_mode)
            viewP.searchTagsStaggeredGrid.setItemTextColor(Color.parseColor("#FFFFFF"))
        }else{
            viewP.searchArea.background = ContextCompat.getDrawable(mActivity?.applicationContext!!, R.drawable.custom_edittext)
            viewP.searchArea.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_search, 0,0,0)
            viewP.searchArea.setHintTextColor(Color.parseColor("#191919"))
            viewP.searchArea.setTextColor(Color.parseColor("#191919"))

            viewP.featuredNewsTitleInfo.setTextColor(Color.parseColor("#191919"))
            viewP.searchContentTitleInfo.setTextColor(Color.parseColor("#191919"))
            viewP.searchUserTitleInfo.setTextColor(Color.parseColor("#191919"))
            viewP.searchTagTitleInfo.setTextColor(Color.parseColor("#191919"))

            viewP.searchTagsStaggeredGrid.setBackgroundDrawable(R.drawable.item_bg_1)
            viewP.searchTagsStaggeredGrid.setItemTextColor(Color.parseColor("#191919"))
        }
    }

    override fun initSearchActivityComponent() {
        searchActivityViewPresenterImpl =
            SearchActivityViewPresenterImpl(
                this,
                SearchActivityModelImpl()
            )
    }

    override fun getMostSharedArticle() {
        searchActivityViewPresenterImpl.getMostPopularArticle()
    }

    /*override fun handleMostSharedArticle(response4MostSharedArticle: Response4MostSharedArticle) {

        setMostSharedArticleGridAdapter(fillMostSharedArticleData(response4MostSharedArticle))

    }*/

    override fun handleMostPopularArticle(response4ArticlesFeed: Response4ArticlesFeed) {
        setMostSharedArticleGridAdapter(fillMostSharedArticleData(response4ArticlesFeed))
    }

    override fun setMostSharedArticleGridAdapter(mListOfMostSharedArticle: MutableList<FeaturedNewsDataModel>) {
        viewP.featuredNewsGrid.adapter =
            FeaturedNewsGridAdapter(
                mActivity?.applicationContext!!,
                mListOfMostSharedArticle
            ) {

                if (OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility == View.VISIBLE) {
                    OnedioSingletonPattern.instance.getProfileSettingPopup()
                        ?.visibility = View.GONE
                } else {
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
                            null,
                            null,
                            it.showInWebView,
                            it.redirectUrl
                        )
                    /*OnedioSingletonPattern.instance.setArticleItem(
                        articleItem
                    )*/

                    /*OnedioSingletonPattern.instance.setTabIndex(4)*/
                    OnedioSingletonPattern.instance.setActivity(DashboardActivityViewImpl::class.java)

                    when {
                        it.redirectUrl != "" -> {
                            val browserIntent =
                                Intent(Intent.ACTION_VIEW, Uri.parse(it.redirectUrl))
                            browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(browserIntent)
                        }
                        it.showInWebView -> {
                            val intent =
                                Intent(mActivity, TestArticleDetailActivityViewImpl::class.java)
                            intent.putExtra("ARTICLE_DETAIL_DATA", Gson().toJson(articleItem))
                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                        }
                        else -> {
                            val intent =
                                Intent(mActivity, ArticleDetailActivityViewImpl::class.java)
                            intent.putExtra("ARTICLE_DETAIL_DATA", Gson().toJson(articleItem))
                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                        }
                    }
                        /*OnedioCommon.cStartActivity(
                            activity?.applicationContext!!,
                            TestArticleDetailActivityViewImpl::class.java
                        )
                    else
                        OnedioCommon.cStartActivity(
                            activity?.applicationContext!!,
                            ArticleDetailActivityViewImpl::class.java
                        )*/
                    startAnim()
                }
            }
    }

    override fun fillMostSharedArticleData(response4MostSharedArticle: Response4ArticlesFeed): MutableList<FeaturedNewsDataModel> {
        val mMostSharedArticleList = response4MostSharedArticle.data?.feed

        for (itemOfMostSharedArticle in mMostSharedArticleList!!) {

            var redirectUrl: String? = null
            itemOfMostSharedArticle.redirectUrl?.let {
                redirectUrl = it
            } ?: run {
                redirectUrl = ""
            }

            var id: String? = null
            itemOfMostSharedArticle.id?.let {
                id = it
            }?: run{
                id = ""
            }

            var legacyId: Long? = null
            itemOfMostSharedArticle.legacyId?.let {
                legacyId = it
            }?: run{
                legacyId = 0
            }

            var image: String? = null
            itemOfMostSharedArticle.image?.let {
                it.alternates?.let {
                    it.standardResolution?.let {
                        it.url?.let {
                            image = it
                        }?: run{
                            image = ""
                        }
                    }?: run{
                        image = ""
                    }
                }?: run{
                    image = ""
                }
            }?: run{
                image = ""
            }

            var title: String? = null
            itemOfMostSharedArticle.title?.let {
                title = it
            }?: run{
                title = ""
            }

            var showInWebView: Boolean? = null
            itemOfMostSharedArticle?.showInWebview?.let {
                showInWebView = it
            }?: run{
                showInWebView = true
            }

            mFeaturedNewsList.add(
                FeaturedNewsDataModel(
                    id!!,
                    legacyId!!,
                    image!!,
                    title!!,
                    "",
                    showInWebView!!,
                    redirectUrl!!
                )
            )
        }

        return mFeaturedNewsList

    }

    override fun clickSearchArea() {
        viewP.searchArea.setOnEditorActionListener { _, actionId, _ ->
            if (OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility == View.VISIBLE) {
                OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility = View.GONE
                return@setOnEditorActionListener false
            } else {
                var handled = false
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (searchArea.text.toString().length >= 3) {
                        changeVisibilityNewsFeatured(false)

                        searchedWord = searchArea.text.toString()
                        searchActivityViewPresenterImpl.getSearchData("article", searchedWord, 1, 3)

                    } else if (searchArea.text.toString().isEmpty()) {
                        changeVisibilityNewsFeatured(true)

                        mUserList.clear()

                        setUserSearchedData2Adapter(mUserList,
                            Response4UserSearch()
                        )
                        setTagSearchedData2Adapter(mutableListOf(),
                            Response4Tags()
                        )
                        setArticleSearchData2Adapter(mutableListOf(),
                            Response4Article()
                        )
                    }
                    handled = true
                }
                handled
            }
        }
    }

    override fun changeVisibilityNewsFeatured(isVisible: Boolean) {
        when (isVisible) {
            true -> {
                viewP.viewNews1.visibility = View.VISIBLE
                viewP.viewNews2.visibility = View.VISIBLE
                viewP.featuredNewsTitleInfo.visibility = View.VISIBLE
                viewP.featuredNewsGrid.visibility = View.VISIBLE
            }
            false -> {
                viewP.viewNews1.visibility = View.GONE
                viewP.viewNews2.visibility = View.GONE
                viewP.featuredNewsTitleInfo.visibility = View.GONE
                viewP.featuredNewsGrid.visibility = View.GONE
            }
        }
    }

    override fun handleUserSearchData(response4UserSearch: Response4UserSearch) {
        mUserList = mutableListOf()
        mUserResponseList = mutableListOf()

        mUserResponseList = response4UserSearch.users!!

        val usersCount: Int = if (mUserResponseList.size <= 3)
            mUserResponseList.size
        else
            3

        for (i in 0 until usersCount) {
            var userId = ""
            response4UserSearch.users[i].avatar?.let {
                userId = response4UserSearch.users[i].id!!
            } ?: run {

            }

            var avatar = ""
            response4UserSearch.users[i].avatar?.let {
                avatar = response4UserSearch.users[i].avatar?.large!!
            } ?: run {

            }

            var userName = ""
            response4UserSearch.users[i].username?.let {
                userName = response4UserSearch.users[i].username!!
            } ?: run {

            }

            mUserList.add(
                SearchListDataModel(
                    userId,
                    avatar,
                    userName
                )
            )
        }

        setUserSearchedData2Adapter(mUserList, response4UserSearch)

        searchActivityViewPresenterImpl.getSearchData("tag", searchedWord, 1, 3)
    }

    override fun setUserSearchedData2Adapter(
        mUserList: MutableList<SearchListDataModel>,
        response4UserSearch: Response4UserSearch
    ) {

        if (mUserList.size > 0) {
            viewP.searchUsersListView.visibility = View.VISIBLE
            viewP.searchUsersListView.adapter =
                UserSearchListAdapter(
                    mActivity?.applicationContext!!,
                    mUserList
                ) {
                    /*OnedioSingletonPattern.instance.setAnotherUserId(
                        it.userId
                    )
                    OnedioSingletonPattern.instance.setIsUserOwnProfile(
                        false
                    )
                    startActivity(
                        Intent(
                            activity?.applicationContext,
                            ProfileActivityViewImpl::class.java
                        )
                    )*/
                    val intent = Intent(mActivity, ProfileActivityViewImpl::class.java)
                    intent.putExtra("IS_USER_OWN_PROFILE", false)
                    intent.putExtra("ANOTHER_USER_PROFILE", it.userId)
                    startActivity(intent)
                }


            viewP.imgOfUser.visibility = View.VISIBLE
            viewP.searchUserTitleInfo.visibility = View.VISIBLE
            viewP.moreUsers.visibility = View.VISIBLE
            viewP.countOfUsers.visibility = View.VISIBLE
            viewP.viewUser.visibility = View.VISIBLE

            viewP.countOfUsers.text = response4UserSearch.total.toString()

            viewP.imgOfUser.setImageDrawable(
                ContextCompat.getDrawable(
                    mActivity?.applicationContext!!,
                    R.drawable.ic_users_fill
                )
            )
            viewP.imgOfUser.background = ContextCompat.getDrawable(
                mActivity?.applicationContext!!,
                R.drawable.custom_search_element_fill_user
            )

        } else {
            viewP.searchUsersListView.visibility = View.GONE

            viewP.imgOfUser.visibility = View.GONE
            viewP.searchUserTitleInfo.visibility = View.GONE
            viewP.moreUsers.visibility = View.GONE
            viewP.countOfUsers.visibility = View.GONE
            viewP.viewUser.visibility = View.GONE
        }

    }

    override fun handleTagSearchData(response4Tags: Response4Tags) {
        mTagsList = mutableListOf()
        mTagsResponseList = mutableListOf()

        mTagsResponseList = response4Tags.tags!!

        val tagsCount: Int = if (mTagsResponseList.size <= 3)
            mTagsResponseList.size
        else
            3

        for (i in 0 until tagsCount) {
            mTagsList.add("#" + response4Tags.tags!![i].name)
        }

        setTagSearchedData2Adapter(mTagsList, response4Tags)
    }

    override fun setTagSearchedData2Adapter(
        mTagList: MutableList<String>,
        response4Tags: Response4Tags
    ) {
        if (mTagList.size > 0) {
            viewP.searchTagsStaggeredGrid.visibility = View.VISIBLE
            viewP.searchTagsStaggeredGrid.setData(mTagsList)

            viewP.imgOfTag.visibility = View.VISIBLE
            viewP.searchTagTitleInfo.visibility = View.VISIBLE
            viewP.moreTags.visibility = View.VISIBLE
            viewP.countOfTags.visibility = View.VISIBLE
            viewP.viewTag.visibility = View.VISIBLE


            viewP.countOfTags.text = response4Tags.total.toString()


            viewP.imgOfTag.setImageDrawable(
                ContextCompat.getDrawable(
                    mActivity?.applicationContext!!,
                    R.drawable.ic_tags_fill
                )
            )
            viewP.imgOfTag.background = ContextCompat.getDrawable(
                mActivity?.applicationContext!!,
                R.drawable.custom_search_element_fill_tag
            )


            viewP.searchTagsStaggeredGrid.addOnTagClickListener {

                var item = it as String
                item = item.replace("#", "")

                mTagsResponseList.forEach { itemOfTag ->
                    if (item == itemOfTag.name.toString()) {
                        /*OnedioSingletonPattern.instance.setTagsItem(itemOfTag)
                        OnedioCommon.cStartActivity(
                            activity?.applicationContext!!,
                            TagsArticleActivityViewImpl::class.java
                        )*/
                        val intent =
                            Intent(mActivity, TagsArticleActivityViewImpl::class.java)
                        intent.putExtra("TAGS_ARTICLE", Gson().toJson(itemOfTag))
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                        startAnim()
                    }


                }
            }


        } else {
            viewP.searchTagsStaggeredGrid.visibility = View.GONE

            viewP.imgOfTag.visibility = View.GONE
            viewP.searchTagTitleInfo.visibility = View.GONE
            viewP.moreTags.visibility = View.GONE
            viewP.countOfTags.visibility = View.GONE
            viewP.viewTag.visibility = View.GONE

        }
    }

    override fun handleArticleSearchData(response4Article: Response4Article) {
        mArticleList = mutableListOf()
        mContentResponseList = mutableListOf()

        mContentResponseList = response4Article.articles!!

        val contentsCount: Int = if (mContentResponseList.size <= 3)
            mContentResponseList.size
        else
            3

        for (i in 0 until contentsCount) {

            var id: String? = null
            var legacyId: Long? = null
            var image: String? = null
            var title: String? = null
            var showInWebView: Boolean? = null
            response4Article.articles?.let {
                if(it.size != 0){
                    it[i].id?.let {
                        id = it
                    }?: run{
                        id = ""
                    }

                    it[i].legacyId?.let {
                        legacyId = it
                    }?: run{
                        legacyId = 0
                    }

                    it[i].image?.let {
                        it.x6?.let {
                            image = it
                        }?: run{
                            image = ""
                        }
                    }?: run{
                        image = ""
                    }

                    it[i].title?.let {
                        title = it
                    }?: run{
                        title = ""
                    }

                    it[i].showInWebview?.let {
                        showInWebView = it
                    }?: run{
                        showInWebView = true
                    }
                }else{
                    id = ""
                    legacyId = 0
                    image = ""
                    title = ""
                    showInWebView = true
                }
            }?: run{
                id = ""
                legacyId = 0
                image = ""
                title = ""
                showInWebView = true
            }

            mArticleList.add(
                FeaturedNewsDataModel(
                    id!!,
                    legacyId!!,
                    image!!,
                    title!!,
                    "",
                    showInWebView!!,
                ""
                )
            )
        }

        setArticleSearchData2Adapter(mArticleList, response4Article)

        searchActivityViewPresenterImpl.getSearchData("user", searchedWord, 1, 3)


    }


    override fun setArticleSearchData2Adapter(
        mArticleList: MutableList<FeaturedNewsDataModel>,
        response4Article: Response4Article
    ) {
        if (mArticleList.size > 0) {
            viewP.searchContentsGrid.visibility = View.VISIBLE
            viewP.searchContentsGrid.adapter =
                ContentSearchAdapter(
                    mActivity?.applicationContext!!,
                    mArticleList
                ) {

                    if (OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility == View.VISIBLE) {
                        OnedioSingletonPattern.instance.getProfileSettingPopup()
                            ?.visibility =
                            View.GONE
                    } else {

                        /*var categoryId: String? = null
                        var categoryName: String? = null

                        it.categories?.get(0)?.let {
                            categoryId = arrOfVideosData[0].categories?.get(0)?.id
                            categoryName = arrOfVideosData[0].categories?.get(0)?.name
                        } ?: run {
                            categoryId = ""
                            categoryName = ""
                        }
                        */
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
                                null,
                                null,
                                false,
                                it.redirectUrl
                            )

                        OnedioSingletonPattern.instance.setActivity(
                            DashboardActivityViewImpl::class.java
                        )

                        /*val intent =
                            Intent(mActivity?.applicationContext!!, ArticleDetailActivityViewImpl::class.java)
                        intent.putExtra("ARTICLE_DETAIL_DATA", Gson().toJson(articleItem))
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)*/

                        when {
                            it.redirectUrl != "" -> {
                                val browserIntent =
                                    Intent(Intent.ACTION_VIEW, Uri.parse(it.redirectUrl))
                                browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                startActivity(browserIntent)
                            }
                            it.showInWebView -> {
                                val intent =
                                    Intent(mActivity, TestArticleDetailActivityViewImpl::class.java)
                                intent.putExtra("ARTICLE_DETAIL_DATA", Gson().toJson(articleItem))
                                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                startActivity(intent)
                            }
                            else -> {
                                val intent =
                                    Intent(mActivity, ArticleDetailActivityViewImpl::class.java)
                                intent.putExtra("ARTICLE_DETAIL_DATA", Gson().toJson(articleItem))
                                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                startActivity(intent)
                            }
                        }


                        startAnim()
                    }
                }


            viewP.imgOfContent.visibility = View.VISIBLE
            viewP.searchContentTitleInfo.visibility = View.VISIBLE
            viewP.moreContent.visibility = View.VISIBLE
            viewP.countOfContent.visibility = View.VISIBLE
            viewP.viewContent.visibility = View.VISIBLE


            viewP.countOfContent.text = response4Article.total.toString()

            viewP.imgOfContent.setImageDrawable(
                ContextCompat.getDrawable(
                    mActivity?.applicationContext!!,
                    R.drawable.ic_content_fill
                )
            )
            viewP.imgOfContent.background =
                ContextCompat.getDrawable(
                    mActivity?.applicationContext!!,
                    R.drawable.custom_search_element_fill_content
                )


        } else {
            viewP.searchContentsGrid.visibility = View.GONE

            viewP.imgOfContent.visibility = View.GONE
            viewP.searchContentTitleInfo.visibility = View.GONE
            viewP.moreContent.visibility = View.GONE
            viewP.countOfContent.visibility = View.GONE
            viewP.viewContent.visibility = View.GONE

            /*imgOfContent.setImageDrawable(resources.getDrawable(R.drawable.ic_contents))
            imgOfContent.background =
                resources.getDrawable(R.drawable.custom_search_element_grey)*/
        }
    }

    override fun clickMoreContent() {
        viewP.moreContent.setOnClickListener {

            if (OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility == View.VISIBLE) {
                OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility = View.GONE
            } else {
                //OnedioSingletonPattern.instance.setSearchArticleResponseData(countOfContent.text.toString())
                /*OnedioSingletonPattern.instance.setSearchedWord(searchArea.text.toString())
                OnedioCommon.cStartActivity(
                    activity?.applicationContext!!,
                    MoreContentActivityViewImpl::class.java
                )*/
                val intent =
                    Intent(mActivity, MoreContentActivityViewImpl::class.java)
                intent.putExtra("MORE_CONTENT_SEARCH", searchArea.text.toString())
                intent.putExtra("MORE_CONTENT_SEARCH_COUNT", countOfContent.text.toString())
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                startAnim()
            }
        }
    }

    override fun clickMoreUser() {
        viewP.moreUsers.setOnClickListener {

            if (OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility == View.VISIBLE) {
                OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility = View.GONE
            } else {
                //OnedioSingletonPattern.instance.setSearchUserResponseData(countOfUsers.text.toString())
                /*OnedioSingletonPattern.instance.setSearchedWord(searchArea.text.toString())
                OnedioCommon.cStartActivity(
                    activity?.applicationContext!!,
                    MoreUserActivityViewImpl::class.java
                )*/
                val intent =
                    Intent(mActivity, MoreUserActivityViewImpl::class.java)
                intent.putExtra("MORE_USER_SEARCH", searchArea.text.toString())
                intent.putExtra("MORE_USER_SEARCH_COUNT", countOfUsers.text.toString())
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                startAnim()
            }

        }
    }

    override fun clickMoreTag() {
        viewP.moreTags.setOnClickListener {

            if (OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility == View.VISIBLE) {
                OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility = View.GONE
            } else {
                //OnedioSingletonPattern.instance.setSearchTagResponseData(countOfTags.text.toString())
                /*OnedioSingletonPattern.instance.setSearchedWord(searchArea.text.toString())
                OnedioCommon.cStartActivity(
                    activity?.applicationContext!!,
                    MoreTagActivityViewImpl::class.java
                )*/
                val intent =
                    Intent(mActivity, MoreTagActivityViewImpl::class.java)
                intent.putExtra("MORE_TAG_SEARCH", searchArea.text.toString())
                intent.putExtra("MORE_TAG_SEARCH_COUNT", countOfTags.text.toString())
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                startAnim()
            }

        }
    }

    override fun showLoading() {
        viewP.avlIndicatorProgress.playAnimation()
        viewP.avlIndicatorProgress.visibility = View.VISIBLE
        /*mActivity?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )*/
    }

    override fun hideLoading() {
        viewP.avlIndicatorProgress.cancelAnimation()
        viewP.avlIndicatorProgress.visibility = View.GONE
        //mActivity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    override fun showError(response4ErrorObj: Response4ErrorObj) {
        EZDialog.Builder(mActivity)
            .setTitle("Uyarı.!")
            .setMessage(response4ErrorObj.status?.message)
            .setPositiveBtnText("Tamam")
            .setHeaderColor(
                ContextCompat.getColor(
                    mActivity?.applicationContext!!,
                    R.color.tabIndicatorColor4Proile
                )
            )
            .setTitleTextColor(
                ContextCompat.getColor(
                    mActivity?.applicationContext!!,
                    R.color.constWhite
                )
            )
            .setMessageTextColor(
                ContextCompat.getColor(
                    mActivity?.applicationContext!!,
                    R.color.constTextColor
                )
            )
            .setButtonTextColor(
                ContextCompat.getColor(
                    mActivity?.applicationContext!!,
                    R.color.constTextColor
                )
            )
            .setCancelableOnTouchOutside(false)
            .OnPositiveClicked {

            }
            .build()

    }

    override fun changeComponentTypeFace() {
        //viewP.applicationToolbarTitle.typeface = OnedioCommon.changeTypeFace4Activity(activity?.applicationContext!!)
        viewP.searchArea.typeface =
            OnedioCommon.changeTypeFace4Activity(mActivity?.applicationContext!!)
        viewP.featuredNewsTitleInfo.typeface =
            OnedioCommon.changeTypeFace4Activity(mActivity?.applicationContext!!)
        viewP.searchContentTitleInfo.typeface =
            OnedioCommon.changeTypeFace4Activity(mActivity?.applicationContext!!)
        viewP.moreContent.typeface =
            OnedioCommon.changeTypeFace4Activity(mActivity?.applicationContext!!)
        viewP.countOfContent.typeface =
            OnedioCommon.changeTypeFace4Activity(mActivity?.applicationContext!!)
        viewP.searchUserTitleInfo.typeface =
            OnedioCommon.changeTypeFace4Activity(mActivity?.applicationContext!!)
        viewP.moreUsers.typeface =
            OnedioCommon.changeTypeFace4Activity(mActivity?.applicationContext!!)
        viewP.countOfUsers.typeface =
            OnedioCommon.changeTypeFace4Activity(mActivity?.applicationContext!!)
        viewP.searchTagTitleInfo.typeface =
            OnedioCommon.changeTypeFace4Activity(mActivity?.applicationContext!!)
        viewP.moreTags.typeface =
            OnedioCommon.changeTypeFace4Activity(mActivity?.applicationContext!!)
        viewP.countOfTags.typeface =
            OnedioCommon.changeTypeFace4Activity(mActivity?.applicationContext!!)
        viewP.searchTagsStaggeredGrid.setTypeface(OnedioCommon.changeTypeFace4Activity(mActivity?.applicationContext!!))

    }

    private fun startAnim() {
        OnedioCustomIntent.startAnim(mActivity!!, "l2r")
    }

    //==============================================================================================
    /**
     * Click Empty Area...
     */
    //==============================================================================================
    private fun clickEmptyArea() {
        viewP.scrollableConstraintLayout.setOnClickListener {
            hideSoftKeyboard(scrollableConstraintLayout)
        }
    }

    //==============================================================================================
    /**
     * Hide Soft Keyboard...
     */
    //==============================================================================================
    private fun hideSoftKeyboard(view: View) {
        val inputMethodManager =
            mActivity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is Activity) {
            mActivity = context
        }
    }

    private fun sendFirebaseAnalyticsLogEvent() {
        firebaseAnalytics = FirebaseAnalytics.getInstance(mActivity?.applicationContext!!)

        val params = Bundle()
        params.putString("name", "Onedio - Sosyal İçerik Platformu")
        params.putString("type", "Search")

        firebaseAnalytics.logEvent("view_screen", params)

        val mapOfFeedAppMetrica = HashMap<String, Any>()
        mapOfFeedAppMetrica["name"] = "Onedio - Sosyal İçerik Platformu"
        mapOfFeedAppMetrica["type"] = "Search"

        YandexMetrica.reportEvent("view_screen", mapOfFeedAppMetrica)

    }


}