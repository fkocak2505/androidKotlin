package com.onedio.androidside.mvp.view.dashboard.search.moreTag

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_more_tag.*
import spencerstudios.com.ezdialoglib.EZDialog
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.common.OnedioCustomIntent
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.welcomeDashboard.search.dataModel.tags.Response4Tags
import com.onedio.androidside.mvp.model.welcomeDashboard.search.dataModel.tags.Tags
import com.onedio.androidside.mvp.model.welcomeDashboard.search.moreTag.MoreTagActivityModelImpl
import com.onedio.androidside.mvp.presenter.welcomeDashboard.search.moreTag.MoreTagActivityPresenterImpl
import com.onedio.androidside.mvp.view.dashboard.search.moreTag.tagsArticle.TagsArticleActivityViewImpl
import com.onedio.androidside.singleton.OnedioSingletonPattern
import kotlinx.android.synthetic.main.activity_more_user.*
import kotlinx.android.synthetic.main.activity_search.view.*

class MoreTagActivityViewImpl : AppCompatActivity(),
    IMoreTagActivityView {

    private lateinit var toolBar: Toolbar

    var mTagsResponseList: MutableList<Tags> = mutableListOf()
    var mTagsListOfString: MutableList<String> = mutableListOf()

    private lateinit var moreTagActivityPresenterImpl: MoreTagActivityPresenterImpl

    private lateinit var searchedWord: String
    private lateinit var searchedWordCount: String

    private var isFirstLoading: Boolean = false
    private var page: Int = 1

    private var isContinueInfinite = true

    private var theme: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_tag)

        val sharedPref =
            getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        theme = sharedPref.getString("mode", "default")!!

        //searchedWord = OnedioSingletonPattern.instance.getSearchedWord()
        searchedWord = intent.getStringExtra("MORE_TAG_SEARCH")!!
        searchedWordCount = intent.getStringExtra("MORE_TAG_SEARCH_COUNT")!!

        initMoreTagComponent()

        prepareToolbar()

        setSearchedWordAndCount(
            searchedWord,
            searchedWordCount
        )

        changeComponentTypeFace()

        clickSelectedTag()

        clickSearchArea()

    }

    override fun initMoreTagComponent() {
        supportActionBar?.hide()

        moreTagActivityPresenterImpl =
            MoreTagActivityPresenterImpl(
                MoreTagActivityModelImpl(),
                this
            )

        moreTagActivityPresenterImpl.filterSearchedTagByParams(
            searchedWord,
            page,
            25
        )

    }

    private fun prepareToolbar() {
        toolBar = toolBar4MoreTag as Toolbar

        toolBar.title = "Etiket"
        toolBar.setTitleTextAppearance(this, R.style.MuliSemiboldTextAppearance)

        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)


        toolBar.setOnClickListener {

        }

        if(theme == "dark"){
            toolBar.setTitleTextColor(Color.parseColor("#FFFFFF"))
            toolBar.setNavigationIcon(R.drawable.ic_back_dark_mode)

            searchArea4Tags.background =
                ContextCompat.getDrawable(applicationContext, R.drawable.custom_edittext_dark_mode)
            searchArea4Tags.setCompoundDrawablesRelativeWithIntrinsicBounds(
                R.drawable.ic_search_dark_mode,
                0,
                0,
                0
            )
            searchArea4Tags.setHintTextColor(Color.parseColor("#FFFFFF"))
            searchArea4Tags.setTextColor(Color.parseColor("#FFFFFF"))

            searchTagsStaggeredGridDetail.setBackgroundDrawable(R.drawable.item_bg_1_dark_mode)
            searchTagsStaggeredGridDetail.setItemTextColor(Color.parseColor("#FFFFFF"))

        }else{
            toolBar.setTitleTextColor(Color.parseColor("#191919"))
            toolBar.setNavigationIcon(R.drawable.ic_back)

            searchArea4Tags.background =
                ContextCompat.getDrawable(applicationContext, R.drawable.custom_edittext_dark_mode)
            searchArea4Tags.setCompoundDrawablesRelativeWithIntrinsicBounds(
                R.drawable.ic_search_dark_mode,
                0,
                0,
                0
            )
            searchArea4Tags.setHintTextColor(Color.parseColor("#FFFFFF"))
            searchArea4Tags.setTextColor(Color.parseColor("#FFFFFF"))

            searchTagsStaggeredGridDetail.setBackgroundDrawable(R.drawable.item_bg_1)
            searchTagsStaggeredGridDetail.setItemTextColor(Color.parseColor("#191919"))
        }

    }

    private fun clickSearchArea() {
        searchArea4Tags.setOnEditorActionListener { _, actionId, _ ->
            if (OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility == View.VISIBLE) {
                OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility = View.GONE
                return@setOnEditorActionListener false
            } else {
                var handled = false
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (searchArea4Tags.text.toString().length >= 3) {

                        if(isContinueInfinite){
                            searchedWord = searchArea4Tags.text.toString()
                            moreTagActivityPresenterImpl.filterSearchedTagByParams(
                                searchedWord,
                                page,
                                25
                            )
                        }

                    } else if (searchArea4Tags.text.toString().isEmpty()) {

                    }
                    handled = true
                }
                handled
            }
        }
    }


    override fun fillSearchAllTagsData() {

        for (itemOfTagsResponseList in mTagsResponseList) {
            mTagsListOfString.add("#" + itemOfTagsResponseList.name!!)
        }

        searchTagsStaggeredGridDetail.setData(mTagsListOfString)
    }

    @SuppressLint("SetTextI18n")
    override fun setSearchedWordAndCount(searchedText: String, searchedCount: String) {
        searchedTagWord.text =
            "\"$searchedText terimi ile ilgili toplam $searchedCount sonuç bulundu"
        searchArea4Tags.setText(searchedText)

    }

    override fun changeComponentTypeFace() {
        //applicationToolbarTitle.typeface = OnedioCommon.changeTypeFace4Activity(applicationContext)
        searchArea4Tags.typeface = OnedioCommon.changeTypeFace4Activity(applicationContext)
        tagTitleInfo.typeface = OnedioCommon.changeTypeFace4Activity(applicationContext)
        searchedTagWord.typeface = OnedioCommon.changeTypeFace4Activity(applicationContext)
        searchTagsStaggeredGridDetail.setTypeface(
            OnedioCommon.changeTypeFace4Activity(
                applicationContext
            )
        )
    }

    override fun handleFilteredSearchData(response4Tags: Response4Tags) {
        setSearchedWordAndCount(searchedWord, response4Tags.total.toString())

        mTagsResponseList = mutableListOf()
        mTagsListOfString = mutableListOf()
        mTagsResponseList = response4Tags.tags!!

        isContinueInfinite = response4Tags.tags?.size != 0

        fillSearchAllTagsData()
    }

    private fun clickSelectedTag(){


        searchTagsStaggeredGridDetail.addOnTagClickListener {

            var item = it as String
            item = item.replace("#","")

            mTagsResponseList.forEach {itemOfTag ->
                if(item == itemOfTag.name.toString()){
                    /*OnedioSingletonPattern.instance.setTagsItem(itemOfTag)
                    OnedioCommon.cStartActivity(applicationContext, TagsArticleActivityViewImpl::class.java)*/
                    val intent =
                        Intent(applicationContext, TagsArticleActivityViewImpl::class.java)
                    intent.putExtra("TAGS_ARTICLE", Gson().toJson(itemOfTag))
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    startAnim()
                }


            }
        }
    }

    override fun showLoading() {
        avlIndicatorProgress4MoreTagSearchFilter.playAnimation()
        avlIndicatorProgress4MoreTagSearchFilter.visibility = View.VISIBLE
        /*window?.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )*/
    }

    override fun hideLoading() {
        avlIndicatorProgress4MoreTagSearchFilter.cancelAnimation()
        avlIndicatorProgress4MoreTagSearchFilter.visibility = View.GONE
        //window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    override fun showError(response4ErrorObj: Response4ErrorObj) {
        EZDialog.Builder(this)
            .setTitle("Uyarı.!")
            .setMessage(response4ErrorObj.status?.message)
            .setPositiveBtnText("Tamam")
            .setHeaderColor(resources.getColor(R.color.tabIndicatorColor4Proile))
            .setTitleTextColor(resources.getColor(R.color.constWhite))
            .setMessageTextColor(resources.getColor(R.color.constTextColor))
            .setButtonTextColor(resources.getColor(R.color.constTextColor))
            .setCancelableOnTouchOutside(false)
            .OnPositiveClicked {

            }
            .build()
    }

    private fun startAnim() {
        OnedioCustomIntent.startAnim(this, "l2r")
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
