package com.weatherlogger.scrollnextviewpager4articledetail.array

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


class TestArticleDetail : Fragment() {

    private var mActivity: Activity? = null

    private lateinit var viewP: View

    private var pos = 0

    companion object {
        const val ARG_POSITION = "position"

        fun getInstance(position: Int, legacyId: Long): Fragment {
            val doppelgangerFragment =
                TestArticleDetail()
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

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        pos = requireArguments().getInt(ARG_POSITION)

        viewP.positionInfo.text = "T $pos"


    }

    @SuppressLint("SetTextI18n")
    private fun getData() {
        Handler().postDelayed({

            val bundle = this.arguments
            val legacyId = bundle?.getLong("ARTICLE_DATA")
            setData(legacyId!!)

        }, 1000)
    }

    private fun setData(legacyId: Long) {

        viewP.articleInfo.text = legacyId.toString()

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is Activity) {
            mActivity = context
        }

    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            getData()
        }
    }
}
