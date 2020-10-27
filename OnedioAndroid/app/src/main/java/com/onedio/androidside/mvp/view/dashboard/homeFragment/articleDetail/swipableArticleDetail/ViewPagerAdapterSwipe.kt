package com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.swipableArticleDetail

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.google.gson.Gson
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.swipableArticleDetail.model.SwipableArticleDetailObj

class ViewPagerAdapterSwipe(
    private val mContext: Context, fm: FragmentManager,
    private var strOfArticleItem: String
) :
    FragmentPagerAdapter(fm) {

    private var articleObj: SwipableArticleDetailObj =
        Gson().fromJson(strOfArticleItem, SwipableArticleDetailObj::class.java)

    override fun getItem(position: Int): Fragment {

        return if (articleObj.swipableArrayList[position].showInWebView)
            TestArticleDetail.getInstance(
                position,
                articleObj.swipableArrayList[position]
            )
        else
            ArticleDetail.getInstance(position, articleObj.swipableArrayList[position])
        /*else
            ArticleDetailRecyclerSwipe.getInstance(position, articleObj.swipableArrayList[position])*/
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return "Onedio"
    }

    override fun getCount(): Int {
        return articleObj.swipableArrayList.size
    }

    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }

}