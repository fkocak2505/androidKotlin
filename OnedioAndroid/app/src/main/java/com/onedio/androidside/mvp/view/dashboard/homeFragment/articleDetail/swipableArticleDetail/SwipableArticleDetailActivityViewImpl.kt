package com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.swipableArticleDetail

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.gson.Gson
import com.onedio.androidside.R
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.swipableArticleDetail.model.SwipableArticleDetailObj
import com.onedio.androidside.mvp.view.dashboard.homeFragment.widgets.hugeCardRecyclerViewAdapter.model.HugeCardModel
import com.onedio.androidside.prefs.BooleanSharedPrefs
import com.onedio.androidside.prefs.IntSharedPrefs
import com.yandex.metrica.YandexMetrica
import kotlinx.android.synthetic.main.swipable_article_detail.*

class SwipableArticleDetailActivityViewImpl : AppCompatActivity() {

    private var lastPosition = 0

    private lateinit var toolBar: Toolbar

    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    private var theme: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.swipable_article_detail)

        val sharedPref =
            getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        theme = sharedPref?.getString("mode", "default")!!

        prepareToolbar()

        firebaseAnalytics = FirebaseAnalytics.getInstance(applicationContext)

        sharedPreferences =
            getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)

        checkAndCount4ArticleOpen()

        //val strOfDataModel = intent.getStringExtra("DATA_MODEL")

        val strOfSliderObj = intent.extras?.getString("SWIPE_ARTICLE_DATA")
        val strOfSelectedFeedLegacyId = intent.extras?.getLong("LEGACY_ID")
        val sSliderObj = Gson().fromJson<SwipableArticleDetailObj>(
            strOfSliderObj,
            SwipableArticleDetailObj::class.java
        )

        val arrOfData = sSliderObj.swipableArrayList

        val index = arrOfData.indexOfFirst { e ->
            e.legacyId == strOfSelectedFeedLegacyId
        }
        var newArr = mutableListOf<HugeCardModel>()

        if (index == -1) {
            newArr = arrOfData
        } else {
            for (i in index until arrOfData.size) {
                newArr.add(
                    HugeCardModel(
                        arrOfData[i].articleId,
                        arrOfData[i].legacyId,
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
                        arrOfData[i].isUserFavorited,
                        arrOfData[i].categoryName,
                        arrOfData[i].categoryId,
                        arrOfData[i].showInWebView,
                        arrOfData[i].redirectUrl
                    )
                )
            }

            for (j in 0 until index) {
                newArr.add(
                    HugeCardModel(
                        arrOfData[j].articleId,
                        arrOfData[j].legacyId,
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
                        arrOfData[j].isUserFavorited,
                        arrOfData[j].categoryName,
                        arrOfData[j].categoryId,
                        arrOfData[j].showInWebView,
                        arrOfData[j].redirectUrl
                    )
                )
            }
        }


        val articleObj =
            SwipableArticleDetailObj(newArr, 0)

        /*val doppelgangerAdapter = DoppelgangerAdapter(this, 40)
        doppelgangerViewPager.adapter = doppelgangerAdapter*/

        val onBoarding = BooleanSharedPrefs(
            sharedPreferences,
            OnedioConstant.SHARED_PREF_ON_BOARDING,
            false
        ).getValue<Boolean>()

        Handler().postDelayed({
            if (!onBoarding) {
                onBoardingCOnstLayout.visibility = View.VISIBLE
                onBoardingText.visibility = View.VISIBLE
                onBoardingImg.setImageResource(R.drawable.ic_swipe)


                onBoardingCOnstLayout.setOnClickListener {
                    onBoardingCOnstLayout.visibility = View.GONE

                    BooleanSharedPrefs(
                        sharedPreferences,
                        OnedioConstant.SHARED_PREF_ON_BOARDING,
                        false
                    ).setValue(true)
                }

                onBoardingText.setOnClickListener {
                    onBoardingCOnstLayout.visibility = View.GONE

                    BooleanSharedPrefs(
                        sharedPreferences,
                        OnedioConstant.SHARED_PREF_ON_BOARDING,
                        false
                    ).setValue(true)
                }
            } else {
                onBoardingCOnstLayout.visibility = View.GONE
            }
        }, 2500)

        val adapter = ViewPagerAdapterSwipe(
            this,
            supportFragmentManager,
            Gson().toJson(articleObj)!!
        )
        swipableArticleDetailViewPager.adapter = adapter

        // Optional
        //doppelgangerViewPager.setPageTransformer(true, DepthPageTransformer())
        //swipableArticleDetailViewPager.setPageTransformer(true, ZoomOutPageTransformer())
        //doppelgangerViewPager.registerOnPageChangeCallback(doppelgangerPageChangeCallback)

        swipableArticleDetailViewPager.addOnPageChangeListener(object :
            ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                val editor =
                    getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, MODE_PRIVATE).edit()
                if (lastPosition > position) {
                    lastPosition = position
                    editor.putBoolean("IS_RIGHT", false)
                    editor.apply()

                    val params = Bundle()
                    params.putString("swipe_direction", "left")
                    firebaseAnalytics.logEvent("article_swipe", params)

                    val mapOfFeedAppMetrica = HashMap<String, Any>()
                    mapOfFeedAppMetrica["swipe_direction"] = "left"
                    YandexMetrica.reportEvent("article_swipe", mapOfFeedAppMetrica)

                } else {
                    lastPosition = position
                    editor.putBoolean("IS_RIGHT", true)
                    editor.apply()

                    val params = Bundle()
                    params.putString("swipe_direction", "right")
                    firebaseAnalytics.logEvent("article_swipe", params)

                    val mapOfFeedAppMetrica = HashMap<String, Any>()
                    mapOfFeedAppMetrica["swipe_direction"] = "right"
                    YandexMetrica.reportEvent("article_swipe", mapOfFeedAppMetrica)

                    checkAndCount4ArticleOpen()
                }
            }
        })
    }

    private fun prepareToolbar() {
        toolBar = toolBar4ArticleDetail as Toolbar
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)


        invalidateOptionsMenu()

        if (theme == "dark") {
            toolBar.setNavigationIcon(R.drawable.ic_back_dark_mode)
        } else {
            toolBar.setNavigationIcon(R.drawable.ic_back)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.toolbar_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    private fun checkAndCount4ArticleOpen() {
        var count = IntSharedPrefs(
            sharedPreferences,
            OnedioConstant.SHARED_PREF_ARTICLE_OPEN_COUNT,
            0
        ).getValue<Int>()

        if (count != 4) {
            count += 1

            IntSharedPrefs(
                sharedPreferences,
                OnedioConstant.SHARED_PREF_ARTICLE_OPEN_COUNT,
                0
            ).setValue(count)
        }
    }

    /*override fun onBackPressed() {
        if (doppelgangerViewPager.currentItem == 0) {
            super.onBackPressed()
        } else {
            doppelgangerViewPager.currentItem = doppelgangerViewPager.currentItem - 1
        }
    }*/
}