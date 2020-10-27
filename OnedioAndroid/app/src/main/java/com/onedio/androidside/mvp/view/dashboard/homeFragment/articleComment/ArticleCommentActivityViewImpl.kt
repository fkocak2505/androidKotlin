package com.onedio.androidside.mvp.view.dashboard.homeFragment.articleComment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.text.method.KeyListener
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.common.OnedioCustomIntent
import com.onedio.androidside.common.OnedioSharedPrefs
import com.onedio.androidside.common.model.OnedioEzDialogMessageModel4Activity
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleComment.ArticleCommentActivityModelImpl
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleComment.response.Response4ArticleComments
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleComment.response.Response4LikeAndUnLike
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.newResponse.ArticleCommentsModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.commentModel.CommentModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.addFavorite.Response4AddFavoriteArticle
import com.onedio.androidside.mvp.model.welcomeDashboard.search.dataModel.tags.Tags
import com.onedio.androidside.mvp.presenter.dashboard.homeFragment.articleComment.ArticleCommentActivityPresenterImpl
import com.onedio.androidside.mvp.view.dashboard.DashboardActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleComment.commentAdapter.CommentRecyclerViewAdapter
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.ArticleDetailActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.FavoriteDataInfo
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.TestArticleDetailActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.messageActivity.conversation.pageTransform.PageTransformer
import com.onedio.androidside.mvp.view.dashboard.homeFragment.profile.ProfileActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.widgets.hugeCardRecyclerViewAdapter.model.HugeCardModel
import com.onedio.androidside.mvp.view.dashboard.search.moreTag.tagsArticle.TagsArticleActivityViewImpl
import com.onedio.androidside.mvp.view.loginAndRegister.LoginAndRegisterDashboardActivityViewImpl
import com.onedio.androidside.prefs.StringSharedPrefs
import com.onedio.androidside.singleton.OnedioSingletonPattern
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.vanniktech.emoji.EmojiManager
import com.vanniktech.emoji.EmojiPopup
import com.vanniktech.emoji.ios.IosEmojiProvider
import kotlinx.android.synthetic.main.activity_article_comment.*


class ArticleCommentActivityViewImpl : AppCompatActivity(),
    IArticleCommentActivityView {

    private lateinit var toolBar: Toolbar

    private lateinit var articleItem: HugeCardModel

    private lateinit var emojiPopup: EmojiPopup

    private lateinit var mAdapter: CommentRecyclerViewAdapter

    private lateinit var articleCommentActivityPreseneterImpl: ArticleCommentActivityPresenterImpl

    private lateinit var keyListener: KeyListener

    private var itemOfCommentData: CommentModel? = null

    private var listOfFavoritesArticleData: MutableList<FavoriteDataInfo> = mutableListOf()
    private var listOfLikedCommentId: MutableList<String> = mutableListOf()

    private lateinit var sharedPrefs: SharedPreferences
    private var commentModel: CommentModel? = null

    private var isParentLike = false
    private var isChildLike = false

    private var isComingSendComment = false

    private var isContinueInfinite = true

    private var isFirstLoad = false

    private var page = 1
    private var legacyId: Long? = null

    private var theme: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setEmojiProvider()

        setContentView(R.layout.activity_article_comment)

        val sharedPref =
            getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        theme = sharedPref?.getString("mode", "default")!!

        initArticleCommentActivityComponent()

    }

    override fun initArticleCommentActivityComponent() {

        intent.getStringExtra("ARTICLE_COMMENT_DATA")?.let {
            articleCommentActivityPreseneterImpl =
                ArticleCommentActivityPresenterImpl(
                    ArticleCommentActivityModelImpl(),
                    this
                )

            sharedPrefs =
                getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)

            articleItem = Gson().fromJson(
                intent.getStringExtra("ARTICLE_COMMENT_DATA"),
                HugeCardModel::class.java
            )
            legacyId = articleItem.legacyId

            OnedioCommon.sendLog2Crashlytics("Article Comment - LegacyId -> " + legacyId.toString())

            prepareToolbar()

            /*setProfilePhoto()

            changeIconIfDarkModeOn()*/

            //swipeRefreshConfig()

            commentRecyclerViewConfig()

            setUpEmojiPopup()

            emojiButtonClickListener()

            getCommentData(legacyId!!, page)

            clickSendComment()

            clickWritingCommentArea()

            clickFavoritedListener()

            prepareToolbar()


            shareText.setOnClickListener {
                shareArticle(legacyId!!)
            }

            shareIcon.setOnClickListener {
                shareArticle(legacyId!!)
            }

            keyListener = writingCommentArea.keyListener
        } ?: run {
            onBackPressed()
        }
    }

    private fun prepareToolbar() {
        toolBar = toolBar4ArticleComment as Toolbar

        toolBar.title = "Yorumlar"
        toolBar.setTitleTextAppearance(this, R.style.MuliSemiboldTextAppearance)

        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)


        toolBar.setOnClickListener {

        }

        if (theme == "dark") {
            toolBar.setTitleTextColor(Color.parseColor("#FFFFFF"))
            toolBar.setNavigationIcon(R.drawable.ic_back_dark_mode)

            articleActionBackground.background = ContextCompat.getDrawable(applicationContext, R.drawable.icon_circle_dark_mode)
            commentEmojiAction.background = ContextCompat.getDrawable(applicationContext, R.drawable.rounded_corner_dark_mode)
            commentEmojiAction2.background = ContextCompat.getDrawable(applicationContext, R.drawable.rounded_corner_dark_mode)
            commentEmojiAction3.background = ContextCompat.getDrawable(applicationContext, R.drawable.rounded_corner_dark_mode)
            doCommentIcon.setImageResource(R.drawable.ic_comment_dark_mode)
            shareIcon.setImageResource(R.drawable.ic_share_dark_mode)
            writingCommentArea.background = ContextCompat.getDrawable(applicationContext, R.drawable.bg_white_rounded_dark_mode)
            emojiButton4Comment.background = ContextCompat.getDrawable(applicationContext, R.drawable.custom_emoji_background_dark_mode)
            emojiButton4Comment.setImageResource(R.drawable.ic_emoji_darkmode)
        } else {
            toolBar.setTitleTextColor(Color.parseColor("#191919"))
            toolBar.setNavigationIcon(R.drawable.ic_back)

            articleActionBackground.background = ContextCompat.getDrawable(applicationContext, R.drawable.ic_circle)
            commentEmojiAction.background = ContextCompat.getDrawable(applicationContext, R.drawable.rounded_corner)
            commentEmojiAction2.background = ContextCompat.getDrawable(applicationContext, R.drawable.rounded_corner)
            commentEmojiAction3.background = ContextCompat.getDrawable(applicationContext, R.drawable.rounded_corner)
            doCommentIcon.setImageResource(R.drawable.delete_after_comment)
            shareIcon.setImageResource(R.drawable.ic_share_black)
            writingCommentArea.background = ContextCompat.getDrawable(applicationContext, R.drawable.bg_white_rounded)
            emojiButton4Comment.background = ContextCompat.getDrawable(applicationContext, R.drawable.custom_emoji_background)
            emojiButton4Comment.setImageResource(R.drawable.ic_emoji)
        }

    }


    /*override fun swipeRefreshConfig() {
        swipeRefresh.setOnRefreshListener(this)
        swipeRefresh.setColorSchemeColors(
            ContextCompat.getColor(applicationContext, R.color.whiteColor),
            ContextCompat.getColor(applicationContext, R.color.userFollowerCountColor)
        )
    }*/

    private fun clickFavoritedListener() {
        addFavoriteArticleItem.setOnCheckStateChangeListener { view, checked ->
            if (OnedioCommon.isUserLogin()) {
                if (addFavoriteArticleItem.isChecked) {
                    addFavoriteArticle(legacyId!!)
                } else {
                    deleteFavoriteArticle(legacyId!!)
                }
            } else {
                OnedioSingletonPattern.instance.setActivity(ArticleCommentActivityViewImpl::class.java)
                val intent =
                    Intent(
                        applicationContext,
                        LoginAndRegisterDashboardActivityViewImpl::class.java
                    )
                intent.putExtra("ARTICLE_COMMENT_DATA", Gson().toJson(articleItem))
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
                startAnim()
                /*OnedioCommon.cStartActivity(
                    applicationContext!!,
                    LoginAndRegisterDashboardActivityViewImpl::class.java
                )*/
                addFavoriteArticleItem.isChecked = !checked
            }
        }

        addFavoriteText.setOnClickListener {
            if (OnedioCommon.isUserLogin()) {
                if (addFavoriteArticleItem.isChecked) {
                    addFavoriteArticleItem.isChecked = false
                    deleteFavoriteArticle(legacyId!!)
                } else {
                    addFavoriteArticleItem.isChecked = true
                    addFavoriteArticle(legacyId!!)
                }
            } else {
                OnedioSingletonPattern.instance.setActivity(ArticleCommentActivityViewImpl::class.java)
                val intent =
                    Intent(
                        applicationContext,
                        LoginAndRegisterDashboardActivityViewImpl::class.java
                    )
                intent.putExtra("ARTICLE_COMMENT_DATA", Gson().toJson(articleItem))
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
                startAnim()

            }
        }
    }

    private fun deleteFavoriteArticle(legacyId: Long) {
        articleCommentActivityPreseneterImpl.deleteFavorite(legacyId)
    }

    override fun handleDeleteFavorite(response4AddFavoriteArticle: Response4AddFavoriteArticle) {
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

    private fun addFavoriteArticle(legacyId: Long) {
        articleCommentActivityPreseneterImpl.addFavorite(legacyId)
    }

    override fun handleAddFavorite(response4AddFavoriteArticle: Response4AddFavoriteArticle) {
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

    private fun getCommentData(legacyId: Long, page: Int) {
        articleCommentActivityPreseneterImpl.getArticleCommentNew(legacyId, page)
    }

    override fun handleArticleCommentNew(response4ArticleFeedDetails: Response4ArticleComments) {

        if (!OnedioSingletonPattern.instance.getDeleteComment()!!)
            OnedioSingletonPattern.instance.getCommentModel()?.let {
                itemOfCommentData = it

                writingCommentArea.keyListener = keyListener
                writingCommentArea.requestFocus()
                val imm =
                    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(writingCommentArea, InputMethodManager.SHOW_IMPLICIT)

                articleCommentNestedScrollView.post {
                    articleCommentNestedScrollView.scrollTo(
                        0,
                        articleCommentNestedScrollView.bottom
                    )
                }

            } ?: run {
                if (OnedioCommon.isUserLogin()) {
                    Handler().postDelayed({
                        writingCommentArea.keyListener = keyListener
                        writingCommentArea.requestFocus()
                        val imm =
                            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.showSoftInput(writingCommentArea, InputMethodManager.SHOW_IMPLICIT)
                    }, 500)

                    articleCommentNestedScrollView.post {
                        articleCommentNestedScrollView.scrollTo(
                            0,
                            articleCommentNestedScrollView.bottom
                        )
                    }

                } else {
                    OnedioSingletonPattern.instance.setActivity(ArticleCommentActivityViewImpl::class.java)
                    val intent =
                        Intent(
                            applicationContext,
                            LoginAndRegisterDashboardActivityViewImpl::class.java
                        )
                    intent.putExtra("ARTICLE_COMMENT_DATA", Gson().toJson(articleItem))
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                }

            }


        val isUserFavorited = response4ArticleFeedDetails.data?.isUserFavorite
        addFavoriteArticleItem.isChecked = isUserFavorited!!

        setCommentDataAdapter(addAllCommentData(response4ArticleFeedDetails.data.comments!!))

        setArticleItemData(response4ArticleFeedDetails)

        if (isComingSendComment)
            articleCommentNestedScrollView.post {
                articleCommentNestedScrollView.scrollTo(0, commentRecyclerView.top)
            }

    }


    override fun commentRecyclerViewConfig() {
        commentRecyclerView.setHasFixedSize(true)
        commentRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    //==================================================================================================================
    /**
     * Setup Emoji Popup...
     */
    //==================================================================================================================
    override fun setUpEmojiPopup() {
        if (OnedioSharedPrefs(applicationContext).isNightModeEnabled()) {
            emojiButton4Comment.setImageDrawable(
                ContextCompat.getDrawable(
                    applicationContext,
                    R.drawable.ic_emoji_darkmode
                )
            )

            emojiPopup = EmojiPopup.Builder.fromRootView(commentRootConstraintLayout)
                .setOnEmojiBackspaceClickListener { }
                .setOnEmojiClickListener { _, _ -> }
                .setOnEmojiPopupShownListener { emojiButton4Comment.setImageResource(R.drawable.ic_keyboard_dark_mode) }
                .setOnSoftKeyboardOpenListener { }
                .setOnEmojiPopupDismissListener { emojiButton4Comment.setImageResource(R.drawable.ic_emoji_darkmode) }
                .setOnSoftKeyboardCloseListener { }
                .setKeyboardAnimationStyle(R.style.emoji_fade_animation_style)
                .setPageTransformer(PageTransformer())
                .build(writingCommentArea)
        } else {
            emojiButton4Comment.setImageDrawable(
                ContextCompat.getDrawable(
                    applicationContext,
                    R.drawable.ic_emoji
                )
            )

            emojiPopup = EmojiPopup.Builder.fromRootView(commentRootConstraintLayout)
                .setOnEmojiBackspaceClickListener { }
                .setOnEmojiClickListener { _, _ -> }
                .setOnEmojiPopupShownListener { emojiButton4Comment.setImageResource(R.drawable.ic_keyboard) }
                .setOnSoftKeyboardOpenListener { }
                .setOnEmojiPopupDismissListener { emojiButton4Comment.setImageResource(R.drawable.ic_emoji) }
                .setOnSoftKeyboardCloseListener { }
                .setKeyboardAnimationStyle(R.style.emoji_fade_animation_style)
                .setPageTransformer(PageTransformer())
                .build(writingCommentArea)
        }
    }

    //==================================================================================================================
    /**
     * Emoj, Button Click Listener...
     */
    //==================================================================================================================
    override fun emojiButtonClickListener() {
        emojiButton4Comment.setOnClickListener { emojiPopup.toggle() }
    }

    private fun setArticleItemData(response4ArticleFeedDetails: Response4ArticleComments) {

        var articleFeedDetail = response4ArticleFeedDetails.data

        var articleId: String? = null
        articleFeedDetail?.id?.let {
            articleId = it
        } ?: run {
            articleId = ""
        }

        var articleLegacyId: Long? = null
        articleFeedDetail?.legacyId?.let {
            articleLegacyId = it
        } ?: run {
            articleLegacyId = 0
        }

        var coverPhoto: String? = null
        articleFeedDetail?.image?.let {
            coverPhoto = it.alternates?.standardResolution?.url
        } ?: run {
            coverPhoto = ""
        }

        var articleCategory: String? = null
        if (articleFeedDetail?.categories?.size != 0) {
            articleFeedDetail?.categories?.let {
                articleCategory = it[0].icons?.png
                /*it[0].badgePNGAsUrl?.let { itemOfCategories ->
                    articleCategory = itemOfCategories.pngFileUrl
                }?: run{
                    articleCategory = ""
                }*/
            } ?: run {
                articleCategory = ""
            }
        } else {
            articleCategory = ""
        }


        var articleTitle: String? = null
        articleFeedDetail?.title?.let {
            articleTitle = it
        } ?: run {
            articleTitle = ""
        }

        var articleDate: String? = null
        articleFeedDetail?.createDate?.let {
            articleDate = OnedioCommon.convertFeedDate(articleFeedDetail.createDate!!)
        } ?: run {
            articleDate = ""
        }

        var emojiAction: String? = null
        articleFeedDetail?.reactions?.let {
            if (it.size != 0) {
                emojiAction = it[0].icons?.png
            } else {
                emojiAction = ""
            }

        } ?: run {
            emojiAction = ""
        }

        var emojiAction2: String? = null
        articleFeedDetail?.reactions?.let {
            if (it.size != 0) {
                emojiAction2 = it[1].icons?.png
            } else {
                emojiAction2 = ""
            }

        } ?: run {
            emojiAction2 = ""
        }

        var emojiAction3: String? = null
        articleFeedDetail?.reactions?.let {
            if (it.size != 0) {
                emojiAction3 = it[2].icons?.png
            } else {
                emojiAction3 = ""
            }
        } ?: run {
            emojiAction3 = ""
        }

        var emojiActionCount = 0
        articleFeedDetail?.stats?.let {
            emojiActionCount = it.reactions!!
        } ?: run {
            emojiActionCount = 0
        }

        var articleCommentCount: Int? = null
        articleFeedDetail?.stats?.let {
            articleCommentCount = it.comments
        } ?: run {
            articleCommentCount = 0
        }

        var badgeId: String? = null
        var badgeName: String? = null
        var badgeIcon: String? = null
        articleFeedDetail?.badge?.let {
            if (it.size != 0) {
                badgeId = it[0].id
                badgeName = it[0].name
                badgeIcon = it[0].icons?.png
            } else {
                badgeId = ""
                badgeName = ""
                badgeIcon = ""
            }
        } ?: run {
            badgeId = ""
            badgeName = ""
            badgeIcon = ""
        }

        if (badgeId != "") {
            badgeLineCovertPhoto.visibility = View.VISIBLE
            coverPhotoBadgeImage.visibility = View.VISIBLE

            loadImageWithoutProgress(badgeIcon!!, coverPhotoBadgeImage)

        } else {
            badgeLineCovertPhoto.visibility = View.INVISIBLE
            coverPhotoBadgeImage.visibility = View.INVISIBLE
        }

        badgeLineCovertPhoto.setOnClickListener {
            val itemOfTag =
                Tags()
            itemOfTag.id = badgeId
            itemOfTag.name = badgeName

            val intent =
                Intent(applicationContext, TagsArticleActivityViewImpl::class.java)
            intent.putExtra("TAGS_ARTICLE", Gson().toJson(itemOfTag))
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            /*OnedioSingletonPattern.instance.setTagsItem(itemOfTag)
            OnedioCommon.cStartActivity(
                applicationContext!!,
                TagsArticleActivityViewImpl::class.java
            )*/
            startAnim()
        }

        coverPhotoBadgeImage.setOnClickListener {
            val itemOfTag =
                Tags()
            itemOfTag.id = badgeId
            itemOfTag.name = badgeName

            val intent =
                Intent(applicationContext, TagsArticleActivityViewImpl::class.java)
            intent.putExtra("TAGS_ARTICLE", Gson().toJson(itemOfTag))
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            /*OnedioSingletonPattern.instance.setTagsItem(itemOfTag)
            OnedioCommon.cStartActivity(
                applicationContext!!,
                TagsArticleActivityViewImpl::class.java
            )*/
            startAnim()
        }



        Picasso.get().load(coverPhoto)
            .into(commentCoverPhoto, object : Callback {
                override fun onSuccess() {
                    commentCoverPhotoProgress.visibility = View.GONE

                }

                override fun onError(e: Exception?) {
                    commentCoverPhotoProgress.visibility = View.GONE
                    /// Log. Errorr..
                }
            })

        commentFeedTitle.text = articleTitle
        commentFeedDate.text = articleDate
        if (emojiAction != "") loadImageWithoutProgress(
            emojiAction!!,
            commentEmojiAction
        )
        if (emojiAction2 != "") loadImageWithoutProgress(
            emojiAction2!!,
            commentEmojiAction2
        )
        if (emojiAction3 != "") loadImageWithoutProgress(
            emojiAction3!!,
            commentEmojiAction3
        )
        commentEmojiActionCount.text = emojiActionCount.toString()
        commentCount.text = articleCommentCount.toString()

        if (articleCategory != "") {
            commentArticleAction.visibility = View.VISIBLE
            articleActionBackground.visibility = View.VISIBLE
            commentArticleActionPhotoProgress.visibility = View.VISIBLE

            loadImage(
                articleCategory!!,
                commentArticleAction,
                commentArticleActionPhotoProgress
            )
        } else {
            commentArticleAction.visibility = View.INVISIBLE
            articleActionBackground.visibility = View.INVISIBLE
            commentArticleActionPhotoProgress.visibility = View.GONE
        }

        var isUserFavorite = false
        articleFeedDetail?.isUserFavorite?.let {
            isUserFavorite = it
        } ?: run {
            isUserFavorite = false
        }

        var redirectUrl: String? = null
        articleFeedDetail?.redirectUrl?.let {
            redirectUrl = it
        } ?: run {
            redirectUrl = ""
        }


        nestedScrollConstraint.visibility = View.VISIBLE

        commentFeedTitle.setOnClickListener {
            val articleItem =
                HugeCardModel(
                    articleId!!,
                    articleLegacyId!!,
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
                    isUserFavorite,
                    null,
                    null,
                    articleFeedDetail?.showInWebview!!,
                    redirectUrl!!
                )
            when {
                redirectUrl != "" -> {
                    val browserIntent =
                        Intent(Intent.ACTION_VIEW, Uri.parse(redirectUrl))
                    browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(browserIntent)
                }
                articleFeedDetail.showInWebview!! -> {
                    //OnedioSingletonPattern.instance.setArticleItem(articleItem)
                    OnedioSingletonPattern.instance.setActivity(DashboardActivityViewImpl::class.java)

                    val intent =
                        Intent(applicationContext, TestArticleDetailActivityViewImpl::class.java)
                    intent.putExtra("ARTICLE_DETAIL_DATA", Gson().toJson(articleItem))
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)

                    /*OnedioCommon.cStartActivity(
                            applicationContext!!,
                            TestArticleDetailActivityViewImpl::class.java
                        )*/
                }
                else -> {
                    //OnedioSingletonPattern.instance.setArticleItem(articleItem)
                    OnedioSingletonPattern.instance.setActivity(DashboardActivityViewImpl::class.java)

                    val intent =
                        Intent(applicationContext, ArticleDetailActivityViewImpl::class.java)
                    intent.putExtra("ARTICLE_DETAIL_DATA", Gson().toJson(articleItem))
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)


                    /*OnedioCommon.cStartActivity(
                            applicationContext!!,
                            ArticleDetailActivityViewImpl::class.java
                        )*/
                }
            }

            startAnim()
        }

        commentCoverPhoto.setOnClickListener {
            val articleItem =
                HugeCardModel(
                    articleId!!,
                    articleLegacyId!!,
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
                    isUserFavorite,
                    null,
                    null,
                    articleFeedDetail?.showInWebview!!,
                    redirectUrl!!
                )
            //OnedioSingletonPattern.instance.setArticleItem(articleItem)

            OnedioSingletonPattern.instance.setActivity(DashboardActivityViewImpl::class.java)
            when {
                redirectUrl != "" -> {
                    val browserIntent =
                        Intent(Intent.ACTION_VIEW, Uri.parse(redirectUrl))
                    browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(browserIntent)
                }
                articleFeedDetail.showInWebview!! -> {
                    val intent =
                        Intent(applicationContext, TestArticleDetailActivityViewImpl::class.java)
                    intent.putExtra("ARTICLE_DETAIL_DATA", Gson().toJson(articleItem))
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }
                else -> {
                    val intent =
                        Intent(applicationContext, ArticleDetailActivityViewImpl::class.java)
                    intent.putExtra("ARTICLE_DETAIL_DATA", Gson().toJson(articleItem))
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }
            }
            /*OnedioCommon.cStartActivity(
                applicationContext!!,
                TestArticleDetailActivityViewImpl::class.java
            ) else
            OnedioCommon.cStartActivity(
                applicationContext!!,
                ArticleDetailActivityViewImpl::class.java
            )*/
            startAnim()
        }


    }

    override fun setCommentDataAdapter(commentData: List<CommentModel>) {

        if (!isFirstLoad) {
            mAdapter =
                CommentRecyclerViewAdapter(
                    applicationContext,
                    commentData.toMutableList()
                ) { itemOfComment, type, likeCountView, likeIconView ->

                    if (profileSettingMenu.visibility == View.VISIBLE) {
                        profileSettingMenu.visibility = View.GONE
                    } else {
                        when (type) {
                            "REPLY" -> {

                                itemOfCommentData = itemOfComment

                                writingCommentArea.keyListener = keyListener
                                writingCommentArea.requestFocus()
                                val imm =
                                    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                                imm.showSoftInput(
                                    writingCommentArea,
                                    InputMethodManager.SHOW_IMPLICIT
                                )

                            }
                            "REPLY_CHILD" -> {
                                itemOfCommentData = itemOfComment

                                writingCommentArea.keyListener = keyListener
                                writingCommentArea.requestFocus()
                                val imm =
                                    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                                imm.showSoftInput(
                                    writingCommentArea,
                                    InputMethodManager.SHOW_IMPLICIT
                                )
                            }
                            "LIKE" -> {
                                likeComment(
                                    itemOfComment,
                                    likeCountView,
                                    likeIconView
                                )
                            }
                            "LIKE_CHILD" -> {
                                likeComment(
                                    itemOfComment,
                                    likeCountView,
                                    likeIconView
                                )
                            }
                            "PROFIL" -> {
                                /*OnedioSingletonPattern.instance.setAnotherUserId(
                                    itemOfComment.userId
                                )
                                OnedioSingletonPattern.instance.setIsUserOwnProfile(
                                    false
                                )*/

                                val intent =
                                    Intent(applicationContext, ProfileActivityViewImpl::class.java)
                                intent.putExtra("IS_USER_OWN_PROFILE", false)
                                intent.putExtra("ANOTHER_USER_PROFILE", itemOfComment.userId)
                                startActivity(intent)
                                /*startActivity(
                                    Intent(
                                        applicationContext,
                                        ProfileActivityViewImpl::class.java
                                    )
                                )*/
                            }

                            "PROFIL_CHILD" -> {
                                val intent =
                                    Intent(applicationContext, ProfileActivityViewImpl::class.java)
                                intent.putExtra("IS_USER_OWN_PROFILE", false)
                                intent.putExtra("ANOTHER_USER_PROFILE", itemOfComment.userId)
                                startActivity(intent)
                                /*OnedioSingletonPattern.instance.setAnotherUserId(
                                    itemOfComment.userId
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
                            }
                        }
                    }
                }

            isFirstLoad = true
            commentRecyclerView.adapter = mAdapter
        } else {
            if (commentData.isNotEmpty())
                mAdapter.addItem(commentData.toMutableList())
        }


        //(commentRecyclerView.layoutManager as LinearLayoutManager).stackFromEnd = true

        articleCommentNestedScrollView.setOnScrollChangeListener(object :
            NestedScrollView.OnScrollChangeListener {
            override fun onScrollChange(
                v: NestedScrollView?,
                scrollX: Int,
                scrollY: Int,
                oldScrollX: Int,
                oldScrollY: Int
            ) {
                var aa = v?.getChildAt(0)?.measuredHeight
                var bbb = v?.measuredHeight

                aa?.let {
                    if (scrollY == (aa - bbb!!)) {
                        if (isContinueInfinite) {
                            page++
                            isComingSendComment = false
                            articleCommentActivityPreseneterImpl.getArticleCommentNew(
                                legacyId!!,
                                page
                            )
                        }
                    }
                } ?: run {

                }
            }
        })
    }

    override fun addAllCommentData(commentData: MutableList<ArticleCommentsModel>): MutableList<CommentModel> {

        val listOfCommentData: MutableList<CommentModel> = mutableListOf()

        isContinueInfinite = commentData.isNotEmpty()

        listOfLikedCommentId = OnedioCommon.convertString2ListStr(
            StringSharedPrefs(
                sharedPrefs,
                OnedioConstant.SHARED_PREF_LIKE_COMMENT_DATA,
                OnedioCommon.convertList2StringStr(
                    listOfLikedCommentId
                )
            ).getValue()
        )

        commentData.forEach { itemOfCommentData ->

            var userImage = ""
            itemOfCommentData.user?.let {
                it.image?.let {
                    it.alternates?.let {
                        it.standardResolution?.let {
                            it.url?.let {
                                userImage = it
                            } ?: run {
                                userImage = ""
                            }
                        } ?: run {
                            userImage = ""
                        }
                    } ?: run {
                        userImage = ""
                    }
                } ?: run {
                    userImage = ""
                }
            } ?: run {
                userImage = ""
            }

            var commentId: String? = null
            itemOfCommentData.id?.let {
                commentId = it
            } ?: run {
                commentId = ""
            }

            var userName: String? = null
            itemOfCommentData.user?.let {
                it.username?.let {
                    userName = it
                } ?: run {
                    userName = ""
                }
            } ?: run {
                userName = ""
            }

            var userId: String? = null
            itemOfCommentData.user?.let {
                it.id?.let {
                    userId = it
                } ?: run {
                    userId = ""
                }
            } ?: run {
                userId = ""
            }

            var itemOfCommentText: String? = null
            itemOfCommentData.text?.let {
                itemOfCommentText = it
            } ?: run {
                itemOfCommentText = ""
            }

            var createDate: String? = null
            itemOfCommentData.createDate?.let {
                createDate = it
            } ?: run {
                createDate = "2020-05-05T18:26:28.514Z"
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

            itemOfCommentData.user?.let {
                listOfCommentData.add(
                    CommentModel(
                        commentId!!,
                        "0",
                        userName!!,
                        userId!!,
                        userImage,
                        itemOfCommentText!!,
                        OnedioCommon.convertFeedDate(createDate!!),
                        likes!!,
                        null,
                        listOfLikedCommentId.contains(commentId!!)
                    )
                )

                itemOfCommentData.replies?.let {
                    if (itemOfCommentData.replies.data?.size != 0) {
                        itemOfCommentData.replies.data?.forEach { itemOfChildrenComment ->

                            var userImageChildren: String? = null
                            itemOfChildrenComment.user?.let {
                                it.image?.let {
                                    it.alternates?.let {
                                        it.standardResolution?.let {
                                            it.url?.let {
                                                userImageChildren = it
                                            } ?: run {
                                                userImageChildren = ""
                                            }
                                        } ?: run {
                                            userImageChildren = ""
                                        }
                                    } ?: run {
                                        userImageChildren = ""
                                    }
                                } ?: run {
                                    userImageChildren = ""
                                }
                            } ?: run {
                                userImageChildren = ""
                            }

                            var userChildrenName: String? = null
                            itemOfChildrenComment.user?.let {
                                it.username?.let {
                                    userChildrenName = it
                                } ?: run {
                                    userChildrenName = ""
                                }
                            } ?: run {
                                userChildrenName = ""
                            }

                            var userChildrenId: String? = null
                            itemOfChildrenComment.user?.let {
                                it.id?.let {
                                    userChildrenId = it
                                } ?: run {
                                    userChildrenId = ""
                                }
                            } ?: run {
                                userChildrenId = ""
                            }

                            var itemOfChildrenCommentText: String? = null
                            itemOfChildrenComment.text?.let {
                                itemOfChildrenCommentText = it
                            } ?: run {
                                itemOfChildrenCommentText = ""
                            }

                            var createDateChildren: String? = null
                            itemOfChildrenComment.createDate?.let {
                                createDateChildren = it
                            } ?: run {
                                createDateChildren = "2020-05-05T18:26:28.514Z"
                            }

                            var childrenCommentLikes: String? = null
                            itemOfChildrenComment.ratings?.let {
                                it.likes?.let {
                                    childrenCommentLikes = it.toString()
                                } ?: run {
                                    childrenCommentLikes = "0"
                                }
                            } ?: run {
                                childrenCommentLikes = "0"
                            }

                            var childrenReplyTo: String? = null
                            itemOfChildrenComment.replyTo?.let {
                                childrenReplyTo = it
                            } ?: run {
                                childrenReplyTo = ""
                            }

                            listOfCommentData.add(
                                CommentModel(
                                    itemOfChildrenComment.id!!,
                                    "1",
                                    userChildrenName!!,
                                    userChildrenId!!,
                                    userImageChildren!!,
                                    itemOfChildrenCommentText!!,
                                    OnedioCommon.convertFeedDate(createDateChildren!!),
                                    childrenCommentLikes!!,
                                    childrenReplyTo!!,
                                    listOfLikedCommentId.contains(itemOfChildrenComment.id)
                                )
                            )


                        }
                    }
                }
            } ?: run {

            }
        }

        return listOfCommentData

    }

    /*override fun onRefresh(direction: SwipyRefreshLayoutDirection?) {

    }*/

    private fun likeComment(
        itemOfComment: CommentModel,
        likeCountView: TextView,
        likeIconView: ImageView
    ) {


        if (itemOfComment.isCommentLiked) {
            likeIconView.setImageResource(R.drawable.ic_like)
            articleCommentActivityPreseneterImpl.unLike(
                itemOfComment,
                likeCountView
            )
        } else {
            likeIconView.setImageResource(R.drawable.ic_like_blue)
            articleCommentActivityPreseneterImpl.likeComment(
                itemOfComment,
                likeCountView
            )
        }

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
        itemOfComment.isCommentLiked = true
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


        isParentLike = true
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
        itemOfComment.isCommentLiked = false
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

        isParentLike = false

    }


    private fun shareArticle(legacyId: Long) {

        if (profileSettingMenu.visibility == View.VISIBLE) {
            profileSettingMenu.visibility = View.GONE
        } else {
            OnedioCommon.shareArticle(this@ArticleCommentActivityViewImpl, legacyId)
        }
    }

    //==================================================================================================================
    /**
     * Set Emoji Provider...
     */
    //==================================================================================================================
    override fun setEmojiProvider() {
        EmojiManager.install(IosEmojiProvider())
    }

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

    private fun clickSendComment() {
        sendComment.setOnClickListener {
            if (profileSettingMenu.visibility == View.VISIBLE) {
                profileSettingMenu.visibility = View.GONE
            } else {
                sendComment()
            }
        }
    }

    private fun clickWritingCommentArea() {
        /*writingCommentArea.setOnClickListener{
            if(!OnedioCommon.isUserLogin()){
                OnedioCommon.cStartActivity(applicationContext, LoginAndRegisterDashboardActivityViewImpl::class.java)
                startAnim()
            }
        }*/

        writingCommentArea.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_UP -> {
                    if (!OnedioCommon.isUserLogin()) {
                        OnedioSingletonPattern.instance.setActivity(
                            ArticleCommentActivityViewImpl::class.java
                        )
                        val intent =
                            Intent(
                                applicationContext,
                                LoginAndRegisterDashboardActivityViewImpl::class.java
                            )
                        intent.putExtra("ARTICLE_COMMENT_DATA", Gson().toJson(articleItem))
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                        finish()
                        /*OnedioCommon.cStartActivity(
                            applicationContext,
                            LoginAndRegisterDashboardActivityViewImpl::class.java
                        )*/
                        startAnim()
                    }
                }
            }

            v?.onTouchEvent(event) ?: true
        }
    }

    private fun sendComment() {
        var commentText = writingCommentArea.text.toString()
        var parentCommentId: String? = null
        var replyTo: String? = null

        itemOfCommentData?.let {
            //commentText = "@${itemOfCommentData!!.userName} " + commentText
            parentCommentId = itemOfCommentData!!.commentId
            replyTo = itemOfCommentData!!.commentId

            itemOfCommentData?.replyToId?.let {
                replyTo = itemOfCommentData!!.commentId
                parentCommentId = itemOfCommentData!!.replyToId
            } ?: run {
                replyTo = itemOfCommentData!!.commentId
            }
        }

        if (OnedioCommon.isUserLogin()) {
            if (commentText != "" && commentText != " " && commentText.length > 5) {
                articleCommentActivityPreseneterImpl.sendCommentNew(
                    legacyId!!,
                    commentText,
                    parentCommentId,
                    replyTo
                )

                itemOfCommentData = null
                OnedioSingletonPattern.instance.setDeleteComment(true)

            } else
                Toast.makeText(
                    applicationContext,
                    "5 karakterden az ve boş yorum gönderemezsiniz..!",
                    Toast.LENGTH_SHORT
                ).show()
        } else {
            /*OnedioCommon.cStartActivity(
                applicationContext,
                LoginAndRegisterDashboardActivityViewImpl::class.java
            )*/
            val intent =
                Intent(applicationContext, LoginAndRegisterDashboardActivityViewImpl::class.java)
            intent.putExtra("ARTICLE_COMMENT_DATA", Gson().toJson(articleItem))
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
            startAnim()
        }

    }

    /*override fun handleSendCommentData(response4SendComment: Response4SendComment) {

        hideSoftKeyboard(nestedScrollConstraint)
        writingCommentArea.clearFocus()
        writingCommentArea.text?.clear()

        getCommentData(articleItem.legacyId)

    }*/

    override fun handleSendCommentDataNew(response4LikeAndUnLike: Response4LikeAndUnLike) {
        hideSoftKeyboard(nestedScrollConstraint)
        writingCommentArea.clearFocus()
        writingCommentArea.text?.clear()

        isComingSendComment = true

        page = 1
        isFirstLoad = false
        mAdapter.removeAllItem()

        getCommentData(legacyId!!, page)
    }

    //==================================================================================================================
    /**
     * Show Loading...
     */
    //==================================================================================================================
    override fun showLoading() {
        articleCommentAvlIndicatorProgress.playAnimation()
        articleCommentAvlIndicatorProgress.visibility = View.VISIBLE
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
        articleCommentAvlIndicatorProgress.cancelAnimation()
        articleCommentAvlIndicatorProgress.visibility = View.GONE
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

    //==============================================================================================
    /**
     * Hide Soft Keyboard...
     */
    //==============================================================================================
    private fun hideSoftKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun startAnim() {
        OnedioCustomIntent.startAnim(this, "l2r")
    }
}