package com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.Color
import android.net.http.SslError
import android.os.Bundle
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
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import im.delight.android.webview.AdvancedWebView
import kotlinx.android.synthetic.main.activity_test_article_detail.*
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.common.model.OnedioEzDialogMessageModel4Activity
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleComment.response.Response4LikeAndUnLike
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.ArticleFeedDetailModelImpl
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.newResponse.ArticleFeedDetailsEntryModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.newResponse.ArticleFeedDetailsModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.newResponse.Response4ArticleFeedDetails
import com.onedio.androidside.mvp.model.dashboard.homeFragment.commentModel.CommentModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.addFavorite.Response4AddFavoriteArticle
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel.Response4ArticlesFeed
import com.onedio.androidside.mvp.presenter.dashboard.homeFragment.articleDetail.ArticleDetailActivirtyPresenterImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleComment.ArticleCommentActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.widgets.hugeCardRecyclerViewAdapter.model.HugeCardModel
import com.onedio.androidside.mvp.view.loginAndRegister.LoginAndRegisterDashboardActivityViewImpl
import com.onedio.androidside.prefs.StringSharedPrefs
import com.onedio.androidside.singleton.OnedioSingletonPattern


class TestArticleDetailActivityViewImpl : AppCompatActivity(),
    ITestArticleDetailActivityView,
    AdvancedWebView.Listener,
    IArticleDetailActivityView {

    private lateinit var toolBar: Toolbar

    private lateinit var articleItem: HugeCardModel

    private lateinit var articleDetailActivirtyPresenterImpl: ArticleDetailActivirtyPresenterImpl

    private var isAddedFavorite: Boolean = false

    private var legacyId: Long? = null

    private lateinit var sharedPrefs: SharedPreferences

    private var listOfFavoritesArticleData: MutableList<FavoriteDataInfo> = mutableListOf()

    private var theme: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_article_detail)

        initTestDetailActivityComponent()

    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun initTestDetailActivityComponent() {

        //supportActionBar?.hide()

        //articleItem = OnedioSingletonPattern.instance.getArticleItem()

        intent.getStringExtra("ARTICLE_DETAIL_DATA")?.let {
            articleItem = Gson().fromJson(intent.getStringExtra("ARTICLE_DETAIL_DATA"), HugeCardModel::class.java)

            legacyId= articleItem.legacyId

            OnedioCommon.sendLog2Crashlytics("Article Test - LegacyId -> " + legacyId.toString())

            sharedPrefs =
                getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)

            theme = sharedPrefs.getString("mode", "default")!!

            articleDetailActivirtyPresenterImpl =
                ArticleDetailActivirtyPresenterImpl(
                    ArticleFeedDetailModelImpl(),
                    this
                )


            prepareToolbar()

            isAddedFavorite = articleItem.isUserFavorited

            testWebView.setListener(this, this)
            testWebView.settings.javaScriptEnabled = true
            testWebView.settings.loadWithOverviewMode = true
            testWebView.settings.useWideViewPort = true
            testWebView.settings.domStorageEnabled = true


            testWebView.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                    if (url.contains("showComments")) {
                        OnedioSingletonPattern.instance.setActivity(
                            TestArticleDetailActivityViewImpl::class.java
                        )

                        val intent =
                            Intent(applicationContext, ArticleCommentActivityViewImpl::class.java)
                        intent.putExtra("ARTICLE_COMMENT_DATA", Gson().toJson(articleItem))
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)

                        /*OnedioCommon.cStartActivity(
                            applicationContext!!,
                            ArticleCommentActivityViewImpl::class.java
                        )*/
                    } else {
                        view.loadUrl(url)
                    }
                    return true
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

            testWebView.loadUrl("https://onedio.com/mobile/$legacyId/appview3")
        }?: run{
            onBackPressed()
        }
    }

    override fun onPageFinished(url: String?) {
        //testAvlIndicatorProgress.visibility = View.INVISIBLE
    }

    override fun onPageError(errorCode: Int, description: String?, failingUrl: String?) {

    }

    override fun onDownloadRequested(
        url: String?,
        suggestedFilename: String?,
        mimeType: String?,
        contentLength: Long,
        contentDisposition: String?,
        userAgent: String?
    ) {

    }

    override fun onExternalPageRequest(url: String?) {

    }

    override fun onPageStarted(url: String?, favicon: Bitmap?) {

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
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
                    if (articleItem.isUserFavorited)
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
                OnedioCommon.shareArticle(this@TestArticleDetailActivityViewImpl, legacyId!!)
            }
        }
        return true
    }


    /*private fun prepareToolbar(){
        toolBar = toolBar4CategoryTestArticle as Toolbar

        toolBar.title = "Test"
        toolBar.setTitleTextAppearance(this, R.style.MuliSemiboldTextAppearance)
        toolBar.setTitleTextColor(Color.parseColor("#191919"))
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        toolBar.setNavigationIcon(R.drawable.ic_back)

        toolBar.setOnClickListener{

        }
    }*/

    private fun prepareToolbar() {
        toolBar = toolBarTestArticleDetailActivity as Toolbar

        articleItem.categoryName?.let {
            if(it.trim() != ""){
                toolBar.title = articleItem.categoryName
            }else{
                toolBar.title = "Test"
            }
        }?: run{
            toolBar.title = "Test"
        }

        toolBar.setTitleTextAppearance(this, R.style.MuliSemiboldTextAppearance)
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)


        if (theme == "dark") {
            toolBar.setTitleTextColor(Color.parseColor("#FFFFFF"))
            toolBar.setNavigationIcon(R.drawable.ic_back_dark_mode)
        }else{
            toolBar.setTitleTextColor(Color.parseColor("#191919"))
            toolBar.setNavigationIcon(R.drawable.ic_back)
        }

    }

    override fun handleAddFavoriteDataModel(response4AddFavoriteArticle: Response4AddFavoriteArticle) {
        articleItem.isUserFavorited = true
        isAddedFavorite = true
        //addFavorite.setImageResource(R.drawable.ic_added_favorite)
        invalidateOptionsMenu()

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

    override fun handleDeleteFavoriteDataModel(response4AddFavoriteArticle: Response4AddFavoriteArticle) {
        articleItem.isUserFavorited = false
        isAddedFavorite = false
        invalidateOptionsMenu()

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
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(response4ErrorObj: Response4ErrorObj) {
        showEzDialogMessage(
            "Hata..!",
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

        OnedioCommon.showEzDialog4Activity(ezDialogMessage) {
            onBackPressed()
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    override fun initArticleDetailActivityComponent() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getArticleFeedDetail(legacyId: Long, isTaabola: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun handleArticleFeedDetailDataNew(response4ArticleFeedDetails: Response4ArticleFeedDetails, isTaboola: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getRecommendWidgetData(legaycId: Long, isTaboola: Boolean) {
        TODO("Not yet implemented")
    }

    override fun handleRecommendWidgetData(
        response4ArticlesFeed: Response4ArticlesFeed,
        isTaboola: Boolean,
        legacyId: Long
    ) {
        TODO("Not yet implemented")
    }

    override fun setHeadOfArticleDetail(articleFeedDetailsModel: ArticleFeedDetailsModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadImageWithPicasso(
        imageUrl: String,
        imageView: ImageView,
        progressBar: ProgressBar
    ) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setEntriesData(arrOfEntriesData: MutableList<ArticleFeedDetailsEntryModel>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun handleLikeComment(
        response4LikeAndUnLike: Response4LikeAndUnLike,
        itemOfComment: CommentModel,
        likeCountView: TextView
    ) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun handleUnlikeComment(
        response4LikeAndUnLike: Response4LikeAndUnLike,
        itemOfComment: CommentModel,
        likeCountView: TextView
    ) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun openVisibilityOfLayout() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun handleSendRead(response4ReadState: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFailMethod(response4ReadState: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}