package com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.swipableArticleDetail


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.Color
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
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.common.model.OnedioEzDialogMessageModel4Fragment
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
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.FavoriteDataInfo
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.IArticleDetailActivityView
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.ITestArticleDetailActivityView
import com.onedio.androidside.mvp.view.dashboard.homeFragment.widgets.hugeCardRecyclerViewAdapter.model.HugeCardModel
import com.onedio.androidside.mvp.view.loginAndRegister.LoginAndRegisterDashboardActivityViewImpl
import com.onedio.androidside.prefs.StringSharedPrefs
import im.delight.android.webview.AdvancedWebView
import kotlinx.android.synthetic.main.activity_test_article_detail.view.*


class TestArticleDetail : Fragment(),
    ITestArticleDetailActivityView,
    AdvancedWebView.Listener,
    IArticleDetailActivityView {

    private lateinit var toolBar: Toolbar

    private var mActivity: Activity? = null

    private lateinit var viewP: View

    private var pos = 0

    private lateinit var articleItemData: HugeCardModel

    private var legacyId: Long? = null

    private lateinit var articleDetailActivirtyPresenterImpl: ArticleDetailActivirtyPresenterImpl

    private var isAddedFavorite: Boolean = false

    private lateinit var sharedPrefs: SharedPreferences

    private var listOfFavoritesArticleData: MutableList<FavoriteDataInfo> = mutableListOf()

    companion object {
        const val ARG_POSITION = "position"

        fun getInstance(position: Int, articleItem: HugeCardModel): Fragment {
            val doppelgangerFragment =
                TestArticleDetail()
            val bundle = Bundle()
            bundle.putInt(ARG_POSITION, position)
            //bundle.putLong("ARTICLE_DATA", legacyId)
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

        viewP = inflater.inflate(R.layout.activity_test_article_detail_2, container, false)

        initTestDetailActivityComponent()

        return viewP
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val bundle = this.arguments
        pos = bundle?.getInt(ARG_POSITION)!!

    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun initTestDetailActivityComponent() {

        Handler().postDelayed({

            val bundle = this.arguments
            articleItemData = bundle?.getParcelable("ARTICLE_DATA")!!

            legacyId = articleItemData.legacyId

            OnedioCommon.sendLog2Crashlytics("Article Test - LegacyId -> $legacyId")

            sharedPrefs =
                mActivity?.getSharedPreferences(
                    OnedioConstant.SHARED_PREF_FILE_NAME,
                    Context.MODE_PRIVATE
                )!!

            articleDetailActivirtyPresenterImpl =
                ArticleDetailActivirtyPresenterImpl(
                    ArticleFeedDetailModelImpl(),
                    this
                )

            //prepareToolbar()


            isAddedFavorite = articleItemData.isUserFavorited

            viewP.testWebView.setListener(mActivity, this)
            viewP.testWebView.settings.javaScriptEnabled = true
            viewP.testWebView.settings.loadWithOverviewMode = true
            viewP.testWebView.settings.useWideViewPort = true
            viewP.testWebView.settings.domStorageEnabled = true


            viewP.testWebView.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                    if (url.contains("showComments")) {

                        val intent =
                            Intent(mActivity, ArticleCommentActivityViewImpl::class.java)
                        intent.putExtra("ARTICLE_COMMENT_DATA", Gson().toJson(articleItemData))
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)

                        OnedioCommon.cStartActivity(
                            mActivity!!,
                            ArticleCommentActivityViewImpl::class.java
                        )
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

            viewP.testWebView.loadUrl("https://onedio.com/mobile/$legacyId/appview3")

            mActivity?.invalidateOptionsMenu()


        }, 500)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        if(isAddedFavorite)
            menu.removeItem(R.id.actionAddFavorite)
        else
            menu.removeItem(R.id.actionDeleteFavorite)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)

        if(isAddedFavorite)
            menu.findItem(R.id.actionDeleteFavorite).setIcon(R.drawable.ic_added_favorite)
        else
            menu.findItem(R.id.actionAddFavorite).setIcon(R.drawable.ic_delete_favorited)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
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
                mActivity?.let {
                    OnedioCommon.shareArticle(it, legacyId!!)
                }?: run{

                }
            }
        }
        return true
    }

    override fun onPageFinished(url: String?) {
        //viewP.testAvlIndicatorProgress.visibility = View.INVISIBLE
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

    override fun handleAddFavoriteDataModel(response4AddFavoriteArticle: Response4AddFavoriteArticle) {
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

    override fun handleDeleteFavoriteDataModel(response4AddFavoriteArticle: Response4AddFavoriteArticle) {
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
    }

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


    /*@SuppressLint("SetTextI18n")
    private fun getData() {
        Handler().postDelayed({

            val bundle = this.arguments
            //val legacyId = bundle?.getLong("ARTICLE_DATA")
            articleItemData = bundle?.getParcelable("ARTICLE_DATA")!!
            val legacyId = articleItemData.legacyId
            setData(legacyId)

        }, 1000)
    }

    private fun setData(legacyId: Long) {

        viewP.articleInfo.text = legacyId.toString()

    }*/

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is Activity) {
            mActivity = context
        }

    }

    /*override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            initTestDetailActivityComponent()
        }
    }*/


    override fun initArticleDetailActivityComponent() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getArticleFeedDetail(legacyId: Long, isTaboola: Boolean) {
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

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
