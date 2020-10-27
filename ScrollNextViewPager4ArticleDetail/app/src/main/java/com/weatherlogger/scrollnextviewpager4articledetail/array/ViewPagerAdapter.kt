package com.weatherlogger.scrollnextviewpager4articledetail.array

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.google.gson.Gson
import com.weatherlogger.scrollnextviewpager4articledetail.model.ArticleObj


class ViewPagerAdapter(
    private val mContext: Context, fm: FragmentManager,
    private var strOfArticleItem: String
) :
    FragmentPagerAdapter(fm) {

    private var articleObj: ArticleObj =
        Gson().fromJson(strOfArticleItem, ArticleObj::class.java)

    override fun getItem(position: Int): Fragment {

        /*return if (articleObj.arrOfArticle[position].isTest)
            TestArticleDetail.getInstance(
                position,
                articleObj.arrOfArticle[position].legacyId
            )
        else*/
        return ArticleDetail.getInstance(position, articleObj.arrOfArticle[position].legacyId)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return "aaaa"
    }

    override fun getCount(): Int {
        return articleObj.arrOfArticle.size
    }

    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }
}