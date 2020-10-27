package com.onedio.androidside.mvp.view.dashboard.onedioFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_onedio_feed.view.*
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.common.model.OnedioEzDialogMessageModel4Fragment
import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.onedioFragment.OnedioFragmentActivityModelImpl
import com.onedio.androidside.mvp.model.dashboard.onedioFragment.response.DataOfAllCategory
import com.onedio.androidside.mvp.model.dashboard.onedioFragment.response.Response4AllCategory
import com.onedio.androidside.mvp.presenter.dashboard.onedioFragment.OnedioFragmentActivityPresenterImpl
import com.onedio.androidside.mvp.view.dashboard.onedioFragment.popupMenu.BottomSheetFragment
import com.onedio.androidside.singleton.OnedioSingletonPattern

class OnedioFragmentActivityViewImpl : Fragment(),
    IOnedioFragmentActivityView {

    private lateinit var viewP: View

    private lateinit var onedioFragmentActivityPresenter: OnedioFragmentActivityPresenterImpl


    companion object {
        fun newInstance(): OnedioFragmentActivityViewImpl {
            val fragmentHome =
                OnedioFragmentActivityViewImpl()
            val args = Bundle()
            fragmentHome.arguments = args
            return fragmentHome
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewP = inflater.inflate(R.layout.fragment_onedio_feed, container, false)

        /*viewP.onedioFeed.setOnClickListener{
            val bottomSheetFragment = BottomSheetFragment()
            bottomSheetFragment.show(activity?.supportFragmentManager, bottomSheetFragment.tag)
        }*/

        initOnedioFeedComponent()

        return viewP
    }

    override fun initOnedioFeedComponent() {

        onedioFragmentActivityPresenter =
            OnedioFragmentActivityPresenterImpl(
                OnedioFragmentActivityModelImpl(),
                this
            )

        getAllCategoryAsTree()
    }

    override fun getAllCategoryAsTree() {
        onedioFragmentActivityPresenter.getAllCategoryAsTree()
    }

    override fun handleAllCategoryData(response4AllCategory: Response4AllCategory) {

        var dataOfAllCategory: ArrayList<DataOfAllCategory> =
            response4AllCategory.dataOfAllCategory?.sortedWith(compareBy { it.displayIndex })!!.toCollection(
                ArrayList()
            )

        OnedioSingletonPattern.instance.setAllCategory(dataOfAllCategory)

        if (!isAdded) return
        val bottomSheetFragment =
            BottomSheetFragment()
        bottomSheetFragment.show(childFragmentManager, bottomSheetFragment.tag)
    }

    //==================================================================================================================
    /**
     * Show Loading...
     */
    //==================================================================================================================
    override fun showLoading() {
        viewP.onedioFeedAvlIndicatorProgress.playAnimation()
        viewP.onedioFeedAvlIndicatorProgress.visibility = View.VISIBLE
        /*activity?.window?.setFlags(
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
        viewP.onedioFeedAvlIndicatorProgress.cancelAnimation()
        viewP.onedioFeedAvlIndicatorProgress.visibility = View.GONE
        //activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    //==================================================================================================================
    /**
     * Show Error...
     */
    //==================================================================================================================
    override fun showError(response4ErrorObj: Response4ErrorObj) {
        showEzDialogMessage(
            "Uyarı..!",
            response4ErrorObj.status?.message!!,
            "Tamam",
            ContextCompat.getColor(
                activity?.applicationContext!!,
                R.color.tabIndicatorColor4Proile
            ),
            ContextCompat.getColor(activity?.applicationContext!!, R.color.constWhite),
            ContextCompat.getColor(activity?.applicationContext!!, R.color.constTextColor),
            ContextCompat.getColor(activity?.applicationContext!!, R.color.constTextColor)
        )
        /*EZDialog.Builder(activity)
            .setTitle("Hata.!")
            .setMessage(response4ErrorObj.status?.message)
            .setPositiveBtnText("Tamam")
            .setHeaderColor(resources.getColor(R.color.tabIndicatorColor4Proile))
            .setTitleTextColor(resources.getColor(R.color.constWhite))
            .setMessageTextColor(resources.getColor(R.color.constTextColor))
            .setButtonTextColor(resources.getColor(R.color.constTextColor))
            .setCancelableOnTouchOutside(false)
            .OnPositiveClicked {

            }
            .build()*/
    }

    override fun showEzDialogMessage(
        titleText: String,
        messageText: String,
        buttonText: String,
        headerColor: Int,
        titleTextColor: Int,
        messageTextColor: Int,
        buttonTextColor: Int
    ) {
        val ezDialogMessage =
            OnedioEzDialogMessageModel4Fragment(
                activity!!,
                titleText,
                messageText,
                buttonText,
                headerColor,
                titleTextColor,
                messageTextColor,
                buttonTextColor
            )

        OnedioCommon.showEzDialog4Fragment(ezDialogMessage) {}
    }

}