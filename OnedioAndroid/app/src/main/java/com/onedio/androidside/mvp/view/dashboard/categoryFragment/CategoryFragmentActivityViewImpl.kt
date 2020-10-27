package com.onedio.androidside.mvp.view.dashboard.categoryFragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.common.OnedioCustomIntent
import com.onedio.androidside.common.model.OnedioEzDialogMessageModel4Fragment
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.categoryFragment.CategoryFragmentActivityModelImpl
import com.onedio.androidside.mvp.model.dashboard.categoryFragment.categoryModel.CategoryModel
import com.onedio.androidside.mvp.model.dashboard.onedioFragment.response.Response4AllCategory
import com.onedio.androidside.mvp.model.welcomeDashboard.search.dataModel.tags.Tags
import com.onedio.androidside.mvp.presenter.dashboard.categotyFragment.CategoryFragmentActivityPresenterImpl
import com.onedio.androidside.mvp.view.dashboard.DashboardActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.adapter.BadgeCategoryGridViewAdapter
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.adapter.CategoryGridViewAdapter
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.CategoryFeedActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.food.FoodActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.video.VideoActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.search.moreTag.tagsArticle.TagsArticleActivityViewImpl
import com.onedio.androidside.singleton.OnedioSingletonPattern
import kotlinx.android.synthetic.main.fragment_categories.view.*


class CategoryFragmentActivityViewImpl : Fragment(),
    ICategoryFragmentActivityView {

    private var mActivity: Activity? = null

    private lateinit var viewP: View

    private lateinit var categoryFragmentActivityPresenter: CategoryFragmentActivityPresenterImpl

    //==================================================================================================================
    /**
     * Init Message Fragment...
     */
    //==================================================================================================================
    companion object {
        fun newInstance(): CategoryFragmentActivityViewImpl {
            val fragmentHome =
                CategoryFragmentActivityViewImpl()
            val args = Bundle()
            fragmentHome.arguments = args
            return fragmentHome
        }
    }

    //==================================================================================================================
    /**
     * Fragment onCreateView Override Method...
     */
    //==================================================================================================================
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewP = inflater.inflate(R.layout.fragment_categories, container, false)

        initCategoryComponent()

        return viewP
    }

    override fun initCategoryComponent() {
        categoryFragmentActivityPresenter =
            CategoryFragmentActivityPresenterImpl(
                CategoryFragmentActivityModelImpl(),
                this
            )

        getBadgeCategory()
    }

    override fun getAllCategoryAsTree() {
        categoryFragmentActivityPresenter.getAllCategory()
    }

    override fun handleCategoryData(response4AllCategory: Response4AllCategory) {
        val arrOfCategoryData = response4AllCategory.dataOfAllCategory

        val categoryGridData: MutableList<CategoryModel> = mutableListOf()

        arrOfCategoryData?.forEach { itemOfCategoryData ->

            var categoryImage: String? = null
            itemOfCategoryData.icons?.let {
                categoryImage = it.png
            } ?: run{
                categoryImage = ""
            }

            categoryGridData.add(
                CategoryModel(
                    itemOfCategoryData.id!!,
                    categoryImage!!,
                    itemOfCategoryData.name!!
                )
            )
        }

        viewP.categoryGridView.adapter =
            CategoryGridViewAdapter(
                categoryGridData,
                mActivity?.applicationContext!!
            ) { position, categoryModelItem ->

                if (OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility == View.VISIBLE) {
                    OnedioSingletonPattern.instance.getProfileSettingPopup()
                        ?.visibility = View.GONE
                } else {
                    when (categoryModelItem.id) {
                        OnedioConstant.CATEGORIES_YEMEK_ID -> {
                            /*OnedioSingletonPattern.instance.setCategoryId(
                                categoryGridData[position]
                            )*/
                            OnedioSingletonPattern.instance.setActivity(
                                DashboardActivityViewImpl::class.java
                            )
                            /*OnedioCommon.cStartActivity(
                                activity?.applicationContext!!,
                                FoodActivityViewImpl::class.java
                            )*/

                            val intent =
                                Intent(mActivity?.applicationContext!!, FoodActivityViewImpl::class.java)
                            intent.putExtra("ARTICLE_FOOD_DATA", Gson().toJson(categoryGridData[position]))
                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)

                            startAnim()
                        }
                        OnedioConstant.CATEGORIES_VIDEO_ID -> {
                            /*OnedioSingletonPattern.instance.setCategoryId(
                                categoryGridData[position]
                            )*/
                            /*OnedioSingletonPattern.instance.setTabIndex(
                                3
                            )*/
                            OnedioSingletonPattern.instance.setActivity(
                                DashboardActivityViewImpl::class.java
                            )

                            val intent =
                                Intent(mActivity?.applicationContext!!, VideoActivityViewImpl::class.java)
                            intent.putExtra("ARTICLE_VIDEO_DATA", Gson().toJson(categoryGridData[position]))
                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)

                            /*OnedioCommon.cStartActivity(
                                activity?.applicationContext!!,
                                VideoActivityViewImpl::class.java
                            )*/
                            startAnim()
                        }
                        else -> {
                            /*OnedioSingletonPattern.instance.setCategoryId(
                                categoryGridData[position]
                            )*/
                            OnedioSingletonPattern.instance.setActivity(
                                DashboardActivityViewImpl::class.java
                            )

                            val intent =
                                Intent(mActivity?.applicationContext!!, CategoryFeedActivityViewImpl::class.java)
                            intent.putExtra("ARTICLE_CATEGORY_DATA", Gson().toJson(categoryGridData[position]))
                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)

                            /*OnedioCommon.cStartActivity(
                                activity?.applicationContext!!,
                                CategoryFeedActivityViewImpl::class.java
                            )*/
                            startAnim()
                        }
                    }
                }
            }

    }

    private fun getBadgeCategory(){
        categoryFragmentActivityPresenter.getBadgeCategory()
    }

    override fun handleBadgeCategoryData(response4AllCategory: Response4AllCategory) {

        val arrOfBadgeCategoryData = response4AllCategory.dataOfAllCategory

        val badgeCategoryGridData: MutableList<CategoryModel> = mutableListOf()

        arrOfBadgeCategoryData?.forEach {itemOfBadgeCategory ->
            badgeCategoryGridData.add(
                CategoryModel(
                    itemOfBadgeCategory.id!!,
                    itemOfBadgeCategory.icons?.png!!,
                    itemOfBadgeCategory.name!!
                )
            )
        }


        viewP.badgeCategoryGridView.adapter =
            BadgeCategoryGridViewAdapter(
                badgeCategoryGridData,
                mActivity?.applicationContext!!
            ) { _, itemOfBadgeCategory ->

                val itemOfTag =
                    Tags()
                itemOfTag.id = itemOfBadgeCategory.id
                itemOfTag.name = itemOfBadgeCategory.title

                val intent =
                    Intent(mActivity, TagsArticleActivityViewImpl::class.java)
                intent.putExtra("TAGS_ARTICLE", Gson().toJson(itemOfTag))
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                startAnim()

            }

        getAllCategoryAsTree()

    }

    //==================================================================================================================
    /**
     * Show Loading...
     */
    //==================================================================================================================
    override fun showLoading() {
        viewP.categoryAvlIndicatorProgress.playAnimation()
        viewP.categoryAvlIndicatorProgress.visibility = View.VISIBLE
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
        viewP.categoryAvlIndicatorProgress.cancelAnimation()
        viewP.categoryAvlIndicatorProgress.visibility = View.GONE
        //mActivity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)

    }

    //==================================================================================================================
    /**
     * Show Error...
     */
    //==================================================================================================================
    override fun showError(methodName: String, response4ErrorObj: Response4ErrorObj) {
        when(methodName){
            "badge" -> {
                Handler().postDelayed({
                    getBadgeCategory()
                }, 1000)
            }
            "category" -> {
                Handler().postDelayed({
                    getAllCategoryAsTree()
                }, 1000)
            }
        }
    }

    private fun startAnim() {
        OnedioCustomIntent.startAnim(mActivity!!, "l2r")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is Activity) {
            mActivity = context
        }
    }

}