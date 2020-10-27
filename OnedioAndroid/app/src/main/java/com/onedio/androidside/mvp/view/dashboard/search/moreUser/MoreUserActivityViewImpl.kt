package com.onedio.androidside.mvp.view.dashboard.search.moreUser

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
import kotlinx.android.synthetic.main.activity_more_user.*
import spencerstudios.com.ezdialoglib.EZDialog
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.common.OnedioCustomIntent
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.welcomeDashboard.search.dataModel.SearchListDataModel
import com.onedio.androidside.mvp.model.welcomeDashboard.search.dataModel.users.Response4UserSearch
import com.onedio.androidside.mvp.model.welcomeDashboard.search.dataModel.users.Users
import com.onedio.androidside.mvp.model.welcomeDashboard.search.moreUser.MoreUserActivityModelImpl
import com.onedio.androidside.mvp.presenter.welcomeDashboard.search.moreUser.MoreUserActivityPresenterImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.profile.ProfileActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.search.userSearchAdapter.UserSearchListAdapter
import com.onedio.androidside.mvp.view.dashboard.search.userSearchAdapter.UserSearchRecyclerAdapter
import com.onedio.androidside.singleton.OnedioSingletonPattern
import kotlinx.android.synthetic.main.activity_more_content.*

class MoreUserActivityViewImpl : AppCompatActivity(),
    IMoreUserActivityView {

    private lateinit var toolBar: Toolbar

    private var mUsersResponseList: MutableList<Users> = mutableListOf()
    private var mUsersListOfString: MutableList<SearchListDataModel> = mutableListOf()

    private lateinit var moreUserActivityPresenterImpl: MoreUserActivityPresenterImpl

    private lateinit var searchedWord: String
    private lateinit var searchedWordCount: String

    private lateinit var mAdapter: UserSearchRecyclerAdapter

    private var isFirstLoading: Boolean = false
    private var page: Int = 1

    private var isContinueInfinite = true

    private var theme: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_user)

        val sharedPref =
            getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        theme = sharedPref.getString("mode", "default")!!

        //searchedWord = OnedioSingletonPattern.instance.getSearchedWord()
        searchedWord = intent.getStringExtra("MORE_USER_SEARCH")!!
        searchedWordCount = intent.getStringExtra("MORE_USER_SEARCH_COUNT")!!

        initMoreUserComponent()

        prepareToolbar()

        setSearchedWordAndCount(
            searchedWord,
            searchedWordCount
        )

        changeComponentTypeFace()

        clickSearchArea()


    }

    override fun initMoreUserComponent() {
        supportActionBar?.hide()

        moreUserActivityPresenterImpl =
            MoreUserActivityPresenterImpl(
                MoreUserActivityModelImpl(),
                this
            )

        moreUserActivityPresenterImpl.filterSearchedUserByParams(
            searchedWord,
            page,
            25
        )
    }

    private fun prepareToolbar() {
        toolBar = toolBar4MoreUser as Toolbar

        toolBar.title = "Kullanıcılar"

        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)


        toolBar.setOnClickListener {

        }

        if(theme == "dark"){
            toolBar.setTitleTextColor(Color.parseColor("#FFFFFF"))
            toolBar.setNavigationIcon(R.drawable.ic_back_dark_mode)

            searchArea4User.background =
                ContextCompat.getDrawable(applicationContext, R.drawable.custom_edittext_dark_mode)
            searchArea4User.setCompoundDrawablesRelativeWithIntrinsicBounds(
                R.drawable.ic_search_dark_mode,
                0,
                0,
                0
            )
            searchArea4User.setHintTextColor(Color.parseColor("#FFFFFF"))
            searchArea4User.setTextColor(Color.parseColor("#FFFFFF"))

        }else{
            toolBar.setTitleTextColor(Color.parseColor("#191919"))
            toolBar.setNavigationIcon(R.drawable.ic_back)

            searchArea4User.background =
                ContextCompat.getDrawable(applicationContext, R.drawable.custom_edittext_dark_mode)
            searchArea4User.setCompoundDrawablesRelativeWithIntrinsicBounds(
                R.drawable.ic_search_dark_mode,
                0,
                0,
                0
            )
            searchArea4User.setHintTextColor(Color.parseColor("#FFFFFF"))
            searchArea4User.setTextColor(Color.parseColor("#FFFFFF"))
        }

    }

    private fun clickSearchArea() {
        searchArea4User.setOnEditorActionListener { _, actionId, _ ->
            if (OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility == View.VISIBLE) {
                OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility = View.GONE
                return@setOnEditorActionListener false
            } else {
                var handled = false
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (searchArea4User.text.toString().length >= 3) {

                        if(isContinueInfinite){
                            searchedWord = searchArea4User.text.toString()
                            moreUserActivityPresenterImpl.filterSearchedUserByParams(
                                searchedWord,
                                page,
                                10
                            )
                        }
                    } else if (searchArea4User.text.toString().isEmpty()) {

                    }
                    handled = true
                }
                handled
            }
        }
    }

    override fun fillSearchAllUsersData() {

        mUsersResponseList.forEach { itemOfUserResponseList ->

            var userSearchedId: String? = null
            itemOfUserResponseList.id?.let {
                userSearchedId = it
            }?: run{
                userSearchedId = ""
            }

            var userAvatar : String? = null
            itemOfUserResponseList.avatar?.let {
                it.large?.let {
                    userAvatar = it
                }?: run{
                    userAvatar = ""
                }
            }?: run{
                userAvatar = ""
            }

            var userName: String?= null
            itemOfUserResponseList.username?.let {
                userName = it
            }?: run{
                userName = ""
            }

            mUsersListOfString.add(
                SearchListDataModel(
                    userSearchedId!!,
                    userAvatar!!,
                    userName!!
                )
            )
        }

       /* searchAllUsersRecyclerView.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        searchAllUsersRecyclerView.layoutManager = layoutManager

        val dividerItemDecoration = DividerItemDecoration(
            searchAllUsersRecyclerView.context,
            layoutManager.orientation
        )
        searchAllUsersRecyclerView.addItemDecoration(dividerItemDecoration)

        setUserSearchDataAdapter(mUsersListOfString)

        searchAllUsersRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                if (dy > 0) {
                    val visibleItemCount = layoutManager.childCount
                    val totalItemCount = layoutManager.itemCount
                    val pastVisibleItems = layoutManager.findFirstVisibleItemPosition()

                    if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                        page++

                        moreUserActivityPresenterImpl.filterSearchedUserByParams(
                            OnedioSingletonPattern.instance.getSearchedWord(),
                            page,
                            10
                        )
                    }

                }

                super.onScrolled(recyclerView, dx, dy)
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }
        })*/

        searchAllUsersListView.adapter =
            UserSearchListAdapter(
                applicationContext,
                mUsersListOfString
            ) {

                val intent = Intent(applicationContext, ProfileActivityViewImpl::class.java)
                intent.putExtra("IS_USER_OWN_PROFILE", false)
                intent.putExtra("ANOTHER_USER_PROFILE", it.userId)
                startActivity(intent)

                /*OnedioSingletonPattern.instance.setAnotherUserId(it.userId)
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

    private fun setUserSearchDataAdapter(mUserList: MutableList<SearchListDataModel>) {
        if (!isFirstLoading) {
            mAdapter =
                UserSearchRecyclerAdapter(
                    applicationContext!!,
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
                            applicationContext,
                            ProfileActivityViewImpl::class.java
                        )
                    )*/

                    val intent = Intent(applicationContext, ProfileActivityViewImpl::class.java)
                    intent.putExtra("IS_USER_OWN_PROFILE", false)
                    intent.putExtra("ANOTHER_USER_PROFILE", it.userId)
                    startActivity(intent)


                    /*OnedioSingletonPattern.instance.setArticleItem(itemOfTrendingData)

                    OnedioSingletonPattern.instance.setTabIndex(1)
                    OnedioSingletonPattern.instance.setActivity(DashboardActivityViewImpl::class.java)

                    OnedioCommon.cStartActivity(activity?.applicationContext!!, ArticleDetailActivityViewImpl::class.java )
                    startAnim()*/
                }
            isFirstLoading = true
        } else
            mAdapter.addItems(mUserList)


        //searchAllUsersRecyclerView.adapter = mAdapter

    }

    override fun handleFilteredSearchData(response4UserSearch: Response4UserSearch) {
        setSearchedWordAndCount(searchedWord, response4UserSearch.total.toString())

        mUsersResponseList = mutableListOf()
        mUsersListOfString = mutableListOf()
        mUsersResponseList = response4UserSearch.users!!

        isContinueInfinite = response4UserSearch.users.size != 0

        fillSearchAllUsersData()
    }

    override fun showLoading() {
        avlIndicatorProgress4MoreUserSearchFilter.playAnimation()
        avlIndicatorProgress4MoreUserSearchFilter.visibility = View.VISIBLE
        /*window?.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )*/
    }

    override fun hideLoading() {
        avlIndicatorProgress4MoreUserSearchFilter.cancelAnimation()
        avlIndicatorProgress4MoreUserSearchFilter.visibility = View.GONE
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

    @SuppressLint("SetTextI18n")
    override fun setSearchedWordAndCount(searchedText: String, searchedCount: String) {
        searchedTagWord4User.text =
            "\"$searchedText terimi ile ilgili toplam $searchedCount sonuç bulundu"
        searchArea4User.setText(searchedText)
    }

    override fun changeComponentTypeFace() {
        //applicationToolbarTitle.typeface = OnedioCommon.changeTypeFace4Activity(applicationContext)
        searchArea4User.typeface = OnedioCommon.changeTypeFace4Activity(applicationContext)
        userTitleInfo.typeface = OnedioCommon.changeTypeFace4Activity(applicationContext)
        searchedTagWord4User.typeface = OnedioCommon.changeTypeFace4Activity(applicationContext)
    }

    private fun startAnim() {
        OnedioCustomIntent.startAnim(this, "r2l")
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}