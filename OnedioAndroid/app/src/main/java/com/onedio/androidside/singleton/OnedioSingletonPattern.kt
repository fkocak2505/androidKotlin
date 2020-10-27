package com.onedio.androidside.singleton

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.View
import android.webkit.CookieManager
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.NestedScrollView
import com.onedio.androidside.mvp.model.dashboard.categoryFragment.categoryModel.CategoryModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.commentModel.CommentModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.chatModel.Chat
import com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.chatModel.UserSearchChat
import com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.conversation.DeletedConversationDataModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel.Response4ArticlesFeed
import com.onedio.androidside.mvp.model.dashboard.onedioFragment.response.DataOfAllCategory
import com.onedio.androidside.mvp.model.dashboard.profil.Response4UserFollowerAndFollowing
import com.onedio.androidside.mvp.model.welcomeDashboard.search.dataModel.tags.Tags
import com.onedio.androidside.mvp.view.dashboard.DashboardActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.widgets.fillRootXMLs.model.WidgetLayoutModel
import com.onedio.androidside.mvp.view.dashboard.homeFragment.widgets.hugeCardRecyclerViewAdapter.model.HugeCardModel

//==================================================================================================================
/**
 *
 *
 * This Singleton Pattern Using All Data..
 * Warning..! : If app is kill then all data is gone..
 *
 *
 */
//==================================================================================================================

class OnedioSingletonPattern {

    companion object {
        val instance: OnedioSingletonPattern by lazy { OnedioSingletonPattern() }
    }

    //==============================================================================================
    /**
     * Token
     */
    //==============================================================================================
    private lateinit var token: String
    fun getToken(): String {
        return token
    }

    fun setToken(valOfToken: String) {
        token = valOfToken
    }


    //==============================================================================================
    /**
     * Access Token
     */
    //==============================================================================================
    private var accessToken: String? = " "
    fun getAccessToken(): String {
        return accessToken!!
    }

    fun setAccessToken(valOfAccessToken: String) {
        accessToken = valOfAccessToken
    }

    //==============================================================================================
    /**
     * Show/Hide Navigation Menu
     */
    //==============================================================================================
    private lateinit var navigation: View

    fun getNav(): View {
        return navigation
    }

    fun setNav(valOfNav: View) {
        navigation = valOfNav
    }

    //==============================================================================================
    /**
     * Using All Nav Menu that's why need to FrameLayout Content ID
     */
    //==============================================================================================
    private var contentId: Int = 0

    fun getContentId(): Int {
        return contentId
    }

    fun setContentId(valOfContentId: Int) {
        contentId = valOfContentId
    }

    //==============================================================================================
    /**
     * Decision Tab Index
     */
    //==============================================================================================
    private var tabIndex: Int = -1

    fun getTabIndex(): Int {
        return tabIndex
    }

    fun setTabIndex(valOfTabIndex: Int) {
        tabIndex = valOfTabIndex
    }

    //==============================================================================================
    /**
     * User Profile ID..
     */
    //==============================================================================================
    private lateinit var userId: String

    fun getUserId(): String {
        return userId
    }

    fun setUserId(valOfUserId: String) {
        userId = valOfUserId
    }

    //==============================================================================================
    /**
     * User Profile Info Data..
     */
    //==============================================================================================
    private lateinit var response4UserProfileInfo: String

    fun getUserProfileInfoData(): String {
        return response4UserProfileInfo
    }

    fun setUserProfileInfoData(valOfUserProfileInfoData: String) {
        response4UserProfileInfo = valOfUserProfileInfoData
    }

    //==============================================================================================
    /**
     * Searched UserId..
     */
    //==============================================================================================
    var isUserOwnProfile: Boolean? = null

    fun getIsUserOwnProfile(): Boolean {
        return isUserOwnProfile!!
    }

    fun setIsUserOwnProfile(valOfIsUserOwnProfile: Boolean) {
        isUserOwnProfile = valOfIsUserOwnProfile
    }

    //==============================================================================================
    /**
     * Searched UserId..
     */
    //==============================================================================================
    private lateinit var anotherUserId: String

    fun getAnotherUserId(): String {
        return anotherUserId
    }

    fun setAnotherUserId(valOfAnotherUserId: String) {
        anotherUserId = valOfAnotherUserId
    }

    //==============================================================================================
    /**
     * Searched UserName..
     */
    //==============================================================================================
    private lateinit var anotherUserName: String

    fun getAnotherUserName(): String {
        return anotherUserName
    }

    fun setAnotherUserName(valOfAnotherUserName: String) {
        anotherUserName = valOfAnotherUserName
    }

    //==============================================================================================
    /**
     * Is Facebook Login
     */
    //==============================================================================================
    var isFBLogin: Boolean? = false

    fun isFBLogin(): Boolean {
        return isFBLogin!!
    }

    fun setFBLogin(valOfIsFBLogin: Boolean) {
        isFBLogin = valOfIsFBLogin
    }

    //==============================================================================================
    /**
     * MessageActivityConversationViewImpl ID..
     */
    //==============================================================================================
    private lateinit var conversationChatData: Chat

    fun getConversationChatData(): Chat {
        return conversationChatData
    }

    fun setConversationChatData(conversationChatData: Chat) {
        this.conversationChatData = conversationChatData
    }

    //==============================================================================================
    /**
     * MessageActivityConversationViewImpl ID..
     */
    //==============================================================================================
    private lateinit var userSearchChatData: UserSearchChat

    fun getUserSearchChatData(): UserSearchChat {
        return userSearchChatData
    }

    fun setUserSearchChatData(userSearchChatData: UserSearchChat) {
        this.userSearchChatData = userSearchChatData
    }

    //==============================================================================================
    /**
     * Is Conversation Start
     */
    //==============================================================================================
    var isConversationStart: Boolean? = null

    fun isConversationStart(): Boolean {
        return isConversationStart!!
    }

    fun setConversationStart(isConversationStart: Boolean) {
        this.isConversationStart = isConversationStart
    }

    //==============================================================================================
    /**
     * Username and Surname for start conversation from profile..
     */
    //==============================================================================================
    private lateinit var userNameAndSurname4StartConversationFromProfile: String

    fun getUserNameAndSurname(): String {
        return userNameAndSurname4StartConversationFromProfile
    }

    fun setUserNameAndSurname(userNameAndSurname4StartConversationFromProfile: String) {
        this.userNameAndSurname4StartConversationFromProfile =
            userNameAndSurname4StartConversationFromProfile
    }

    //==============================================================================================
    /**
     * ConversationID from Profile
     */
    //==============================================================================================
    private lateinit var conversationID: String

    fun getConversationID(): String {
        return conversationID
    }

    fun setConversationID(conversationID: String) {
        this.conversationID = conversationID
    }

    //==============================================================================================
    /**
     * Deleted Conversation Date..
     */
    //==============================================================================================
    private var conversationDeletedDate: MutableList<DeletedConversationDataModel> = mutableListOf()

    fun getConversationDeletedDate(): MutableList<DeletedConversationDataModel> {
        return conversationDeletedDate
    }

    fun setConversationDeletedDate(conversationDeletedDate: MutableList<DeletedConversationDataModel>) {
        this.conversationDeletedDate = conversationDeletedDate
    }

    //==============================================================================================
    /**
     * User Conversation Image Url...
     */
    //==============================================================================================
    private lateinit var imageUrl: String

    fun getConversationUserImageURL(): String {
        return imageUrl
    }

    fun setConversationUserImageURL(imageUrl: String) {
        this.imageUrl = imageUrl
    }

    //==============================================================================================
    /**
     * Search Word
     */
    //==============================================================================================
    private lateinit var searchedWord: String

    fun getSearchedWord(): String {
        return searchedWord
    }

    fun setSearchedWord(searchedWord: String) {
        this.searchedWord = searchedWord
    }

    //==============================================================================================
    /**
     * Search User Data...
     */
    //==============================================================================================
    private var mArticleResponseCount: String? = ""

    fun getSearchArticleResponseData(): String? {
        return mArticleResponseCount
    }

    fun setSearchArticleResponseData(mArticleResponseCount: String) {
        this.mArticleResponseCount = mArticleResponseCount
    }

    //==============================================================================================
    /**
     * Search User Data...
     */
    //==============================================================================================
    private var mUserResponseCount: String? = ""

    fun getSearchUserResponseData(): String? {
        return mUserResponseCount
    }

    fun setSearchUserResponseData(mUserResponseCount: String) {
        this.mUserResponseCount = mUserResponseCount
    }


    //==============================================================================================
    /**
     * Search Tag Data
     */
    //==============================================================================================
    private var mTagResponseCount: String? = ""

    fun getSearchTagResponseData(): String? {
        return mTagResponseCount
    }

    fun setSearchTagResponseData(mTagResponseCount: String) {
        this.mTagResponseCount = mTagResponseCount
    }

    //==============================================================================================
    /**
     * Get All Categories as a tree Data...
     */
    //==============================================================================================
    private lateinit var dataOfAllCategory: ArrayList<DataOfAllCategory>

    fun getAllCategory(): ArrayList<DataOfAllCategory> {
        return dataOfAllCategory
    }

    fun setAllCategory(dataOfAllCategory: ArrayList<DataOfAllCategory>) {
        this.dataOfAllCategory = dataOfAllCategory
    }

    //==============================================================================================
    /**
     * Click Article Item for Comment
     */
    //==============================================================================================
    private lateinit var articleItem: HugeCardModel

    fun getArticleItem(): HugeCardModel {
        return articleItem
    }

    fun setArticleItem(articleItem: HugeCardModel) {
        this.articleItem = articleItem
    }

    //==============================================================================================
    /**
     * Huge Card RecyclerView List Ids..
     */
    //==============================================================================================
    private var hugeCardRecyclerViewIds: MutableList<Int> = mutableListOf()

    fun getHugeCardRecyclerViewIds(): MutableList<Int> {
        return hugeCardRecyclerViewIds
    }

    fun setHugeCardRecyclerViewIds(hugeCardRecyclerViewIds: MutableList<Int>) {
        this.hugeCardRecyclerViewIds = hugeCardRecyclerViewIds
    }

    //==============================================================================================
    /**
     * Category Id..
     */
    //==============================================================================================
    private lateinit var categoryIdWithName: CategoryModel

    fun getCategoryId(): CategoryModel {
        return categoryIdWithName
    }

    fun setCategoryId(categoryId: CategoryModel) {
        this.categoryIdWithName = categoryId
    }

    //==============================================================================================
    /**
     * Handle Before Activity
     */
    //==============================================================================================
    private lateinit var className: Class<out Activity>

    fun setActivity(className: Class<out Activity>) {
        this.className = className
    }

    fun getActivity(): Class<out Activity> {
        return if (::className.isInitialized)
            className
        else
            DashboardActivityViewImpl::class.java
    }

    //==============================================================================================
    /**
     * Handle Inflated
     */
    //==============================================================================================
    private lateinit var inflated: View

    fun setInflated(inflated: View) {
        this.inflated = inflated
    }

    fun getInflated(): View {
        return inflated
    }

    //==============================================================================================
    /**
     * Response 4 Widget..
     */
    //==============================================================================================
    /*private var response4WidgetConfig: Response4WidgetConfig =
        Response4WidgetConfig()

    fun setWidgetConfigResponse(response4WidgetConfig: Response4WidgetConfig) {
        this.response4WidgetConfig = response4WidgetConfig
    }

    fun getWidgetConfigResponse(): Response4WidgetConfig {
        return response4WidgetConfig
    }*/

    //==============================================================================================
    /**
     * Nested ScrollView
     */
    //==============================================================================================
    private lateinit var nestedScrollView: NestedScrollView

    fun getNestedScrollView(): NestedScrollView {
        return nestedScrollView
    }

    fun setNestedScrollView(nestedScrollView: NestedScrollView) {
        this.nestedScrollView = nestedScrollView
    }

    //==============================================================================================
    /**
     * Get Tag Article
     */
    //==============================================================================================
    private var tagsItem: Tags? = null

    fun getTagsItem(): Tags? {
        return tagsItem
    }

    fun setTagsItem(tagsItem: Tags?) {
        this.tagsItem = tagsItem
    }

    //==============================================================================================
    /**
     * ProfileSettingPopup Layout..
     */
    //==============================================================================================
    private var profileSettingPopup: ConstraintLayout? = null

    fun getProfileSettingPopup(): ConstraintLayout? {
        return profileSettingPopup
    }

    fun setProfileSettingPopup(profileSettingPopup: ConstraintLayout?) {
        this.profileSettingPopup = profileSettingPopup
    }

    //==============================================================================================
    /**
     * ProfileSettingPopup Layout..
     */
    //==============================================================================================
    private var xmlViews: HashMap<String, WidgetLayoutModel>? = null

    fun getXmlViews(): HashMap<String, WidgetLayoutModel>? {
        return xmlViews
    }

    fun setXmlViews(xmlViews: HashMap<String, WidgetLayoutModel>?) {
        this.xmlViews = xmlViews
    }

    //==============================================================================================
    /**
     * Onedio Button Data
     */
    //==============================================================================================
    private var userFollowersData: Response4UserFollowerAndFollowing? = null

    fun getUserFollowersData(): Response4UserFollowerAndFollowing? {
        return userFollowersData
    }

    fun setUserFollowersData(userFollowersData: Response4UserFollowerAndFollowing?) {
        this.userFollowersData = userFollowersData
    }


    //==============================================================================================
    /**
     * Onedio Button Data
     */
    //==============================================================================================
    private var userFollowingsData: Response4UserFollowerAndFollowing? = null

    fun getUserFollowingsData(): Response4UserFollowerAndFollowing? {
        return userFollowingsData
    }

    fun setUserFollowingsData(userFollowingsData: Response4UserFollowerAndFollowing?) {
        this.userFollowingsData = userFollowingsData
    }

    //==============================================================================================
    /**
     * Set Follower or Following Index
     */
    //==============================================================================================
    private var index: Int = 0

    fun getIndex(): Int {
        return index
    }

    fun setIndex(index: Int) {
        this.index = index
    }

    //==============================================================================================
    /**
     * Comment Model for Article Detail Comment
     */
    //==============================================================================================
    private var commentModel: CommentModel? = null

    fun getCommentModel(): CommentModel? {
        return commentModel
    }

    fun setCommentModel(commentModel: CommentModel) {
        this.commentModel = commentModel
    }

    //==============================================================================================
    /**
     * Delete Comment Model
     */
    //==============================================================================================
    private var deleteCommentModel = true

    fun getDeleteComment(): Boolean? {
        return deleteCommentModel
    }

    fun setDeleteComment(deleteCommentModel: Boolean) {
        this.deleteCommentModel = deleteCommentModel
    }

    //==============================================================================================
    /**
     * GA WebView
     */
    //==============================================================================================
    private lateinit var webView: WebView
    @SuppressLint("SetJavaScriptEnabled")
    fun getWebView(context: Context, url: String): WebView{

        if(!::webView.isInitialized)
            webView = WebView(context)

        webView.webChromeClient = WebChromeClient()
        webView.settings.javaScriptEnabled = true
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true
        webView.settings.domStorageEnabled = true
        CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true)

        webView.loadUrl(url)

        return webView
    }

}