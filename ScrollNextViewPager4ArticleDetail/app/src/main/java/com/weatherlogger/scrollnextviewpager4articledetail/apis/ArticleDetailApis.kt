package com.weatherlogger.scrollnextviewpager4articledetail.apis

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.weatherlogger.scrollnextviewpager4articledetail.R
import kotlinx.android.synthetic.main.fragment_doppelganger.view.*


class ArticleDetailApis : Fragment() {

    private var mActivity: Activity? = null

    private lateinit var viewP: View

    private var pos = 0

    companion object {
        const val ARG_POSITION = "position"

        fun getInstance(position: Int, legacyId: Long): Fragment {
            val doppelgangerFragment =
                ArticleDetailApis()
            val bundle = Bundle()
            bundle.putInt(ARG_POSITION, position)
            bundle.putLong("ARTICLE_DATA", legacyId)
            doppelgangerFragment.arguments = bundle
            return doppelgangerFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewP = inflater.inflate(R.layout.fragment_doppelganger, container, false)

        return viewP
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        pos = requireArguments().getInt(ARG_POSITION)

        viewP.positionInfo.text = pos.toString()


    }

    @SuppressLint("SetTextI18n")
    private fun getData() {
        Handler().postDelayed({

            /*val sharedPref = mActivity?.getSharedPreferences("PREFS_NAME", Context.MODE_PRIVATE)
            val strOfArticleItem = sharedPref?.getString("BEFORE_AFTER_ARTICLE", "")

            val bundle = this.arguments

            if (strOfArticleItem != ""){
                setData(strOfArticleItem!!)
            }
            else {
                val strOfLegacyId = bundle?.getString("ARTICLE_DATA")
                setData(strOfLegacyId!!)
            }*/

            val bundle = this.arguments
            val legacyId = bundle?.getLong("ARTICLE_DATA")
            setData(legacyId!!)

        }, 1000)
    }

    private fun setData(legacyId: Long) {

        /*val articleItem = JsonParser().parse(strOfArticleItem).asJsonObject
        val legacyId = articleItem.get("legacyId")*/

        //val anotherArticleObj: BeforeAfterArticleInfo

        viewP.articleInfo.text = legacyId.toString()

        /*val beforeAfterObj = BeforeAfterObj(
            BeforeAfterArticleInfo(
                (100000..999999).random().toLong()
            ),
            BeforeAfterArticleInfo(
                (100000..999999).random().toLong()
            )
        )*/

        /*val sharedPref = mActivity?.getSharedPreferences("PREFS_NAME", MODE_PRIVATE)
        val isRight = sharedPref?.getBoolean("IS_RIGHT", true)

        anotherArticleObj = if(isRight!!)
            beforeAfterObj.afterArticle
        else
            beforeAfterObj.beforeArticle

        val editor = mActivity!!.getSharedPreferences("PREFS_NAME", MODE_PRIVATE).edit()
        editor.putString("BEFORE_AFTER_ARTICLE", Gson().toJson(anotherArticleObj))
        editor.apply()*/

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is Activity) {
            mActivity = context
        }

        //Logger.e("ATTACH_FRAGMENT - onAttached successfully")
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            getData()
        }
    }
}
