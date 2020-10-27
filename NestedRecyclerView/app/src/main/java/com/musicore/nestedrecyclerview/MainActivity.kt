package com.musicore.nestedrecyclerview

import android.content.res.AssetManager
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.musicore.nestedrecyclerview.model.ArticleDetailAdapterModel
import com.musicore.nestedrecyclerview.model.Response4ArticleDetail
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var arrOfArticleDetailData: MutableList<ArticleDetailAdapterModel> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prepareArticleData()

        initRecycler()

    }

    private fun initRecycler() {

        val mLayoutManager =
            PreCachingLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)


        /*val mLayoutManager: PreCachingLayoutManager = object : PreCachingLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false) {
            override fun smoothScrollToPosition(
                recyclerView: RecyclerView,
                state: RecyclerView.State,
                position: Int
            ) {
                val smoothScroller: LinearSmoothScroller = object : LinearSmoothScroller(applicationContext) {
                    private val SPEED = 300f // Change this value (default=25f)
                    override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {
                        return SPEED / displayMetrics.densityDpi
                    }
                }
                smoothScroller.targetPosition = position
                startSmoothScroll(smoothScroller)
            }
        }*/


        mLayoutManager.setExtraLayoutSpace(DeviceUtils.getScreenHeight(applicationContext))

        rv_parent.apply {
            layoutManager = mLayoutManager
            setItemViewCacheSize(5)
            adapter = ParentAdapter2(applicationContext, arrOfArticleDetailData) { position, itemOfArticlePart, viewType ->
                handleClickViews(position, itemOfArticlePart, viewType)
            }
        }
    }

    private fun handleClickViews(
        position: Int,
        itemOfArticlePart: ArticleDetailAdapterModel,
        viewType: String
    ){
        when(viewType){
            "BADGE" -> {
                Toast.makeText(applicationContext, viewType, Toast.LENGTH_SHORT).show()
            }
            "CATEGORY" -> {
                Toast.makeText(applicationContext, viewType, Toast.LENGTH_SHORT).show()
            }
            "PROFILE" -> {
                Toast.makeText(applicationContext, viewType, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun prepareArticleData(): MutableList<ArticleDetailAdapterModel> {
        val articleDetail = Gson().fromJson(
            this.assets.readAssetsFile("articleData.json"),
            Response4ArticleDetail::class.java
        )

        arrOfArticleDetailData.add(ArticleDetailAdapterModel("googleAdsTop", articleDetail, false))
        arrOfArticleDetailData.add(ArticleDetailAdapterModel("header", articleDetail))
        arrOfArticleDetailData.add(ArticleDetailAdapterModel("entry", articleDetail))
        arrOfArticleDetailData.add(
            ArticleDetailAdapterModel(
                "googleAdsBottom",
                articleDetail,
                false
            )
        )
        arrOfArticleDetailData.add(ArticleDetailAdapterModel("emoji", articleDetail))
        arrOfArticleDetailData.add(ArticleDetailAdapterModel("comment", articleDetail))
        arrOfArticleDetailData.add(ArticleDetailAdapterModel("taboola", articleDetail, false))

        return arrOfArticleDetailData
    }

    private fun AssetManager.readAssetsFile(file: String): String =
        open(file).bufferedReader().use { it.readText() }

}