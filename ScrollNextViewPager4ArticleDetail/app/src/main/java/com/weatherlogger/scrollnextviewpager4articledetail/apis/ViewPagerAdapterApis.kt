package com.weatherlogger.scrollnextviewpager4articledetail.apis

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.google.gson.Gson
import com.weatherlogger.scrollnextviewpager4articledetail.array.ArticleDetail
import com.weatherlogger.scrollnextviewpager4articledetail.model.ArticleObj


class ViewPagerAdapterApis(
    private val mContext: Context, fm: FragmentManager,
    private var strOfArticleItem: String
) :
    FragmentPagerAdapter(fm) {

    private var articleObj: ArticleObj =
        Gson().fromJson(strOfArticleItem, ArticleObj::class.java)
    private var lastPosition = 0

    override fun getItem(position: Int): Fragment {
        /*return if (lastPosition > position) {
            lastPosition = position
            ArticleDetail.getInstance(position, strOfArticleItem, false)
        } else {
            lastPosition = position
            ArticleDetail.getInstance(position, strOfArticleItem, true)
        }*/

        //return ArticleDetail.getInstance(position, strOfArticleItem)



        return ArticleDetail.getInstance(
            position,
            articleObj.arrOfArticle[position].legacyId
        )
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