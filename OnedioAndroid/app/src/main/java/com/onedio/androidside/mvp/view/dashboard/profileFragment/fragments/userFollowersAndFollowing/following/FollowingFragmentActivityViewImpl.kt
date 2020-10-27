package com.onedio.androidside.mvp.view.dashboard.profileFragment.fragments.userFollowersAndFollowing.following

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_following.view.*
import kotlinx.android.synthetic.main.fragment_following.view.txtOfNoData
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.common.model.OnedioEzDialogMessageModel4Fragment
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.profil.Response4UserFollowerAndFollowing
import com.onedio.androidside.mvp.model.dashboard.profil.UsersProfilData
import com.onedio.androidside.mvp.view.dashboard.homeFragment.profile.ProfileActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.profileFragment.fragments.userFollowersAndFollowing.followers.FollowerFragmentActivityModelImpl
import com.onedio.androidside.mvp.view.dashboard.profileFragment.fragments.userFollowersAndFollowing.following.userFollowingRecylerAdapter.ProfileUserFollowingRecylerAdapter2
import com.onedio.androidside.singleton.OnedioSingletonPattern

class FollowingFragmentActivityViewImpl: Fragment(),
    IFollowingFragmentActivityView {

    private lateinit var viewP: View

    private lateinit var listOfFollowings: MutableList<UsersProfilData>

    private lateinit var followingFragmentActivityPresenter: FollowingFragmentActivityPresenterImpl

    private lateinit var mUserFollowingAdapter: ProfileUserFollowingRecylerAdapter2

    private var page = 2

    private var isContinueInfinite = true

    private var isOwnUserProfile: Boolean = false
    private lateinit var anotheruserId: String

    private var theme: String = ""

    //==================================================================================================================
    /**
     * onCreateView Override Method...
     */
    //==================================================================================================================
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewP = inflater.inflate(R.layout.fragment_following, container, false)

        isOwnUserProfile = activity?.intent?.getBooleanExtra("IS_USER_OWN_PROFILE", false)!!
        anotheruserId = activity?.intent?.getStringExtra("ANOTHER_USER_PROFILE")!!

        val sharedPref = activity?.application?.getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        theme = sharedPref?.getString("mode", "default")!!

        initUserFollowingActivityComponents()

        changeTypeFace()

        clickEmptyArea()

        if(theme == "dark"){
            viewP.imageView.setImageResource(R.drawable.ic_onedio_fligram_dark)
            viewP.txtOfNoData.setTextColor(Color.parseColor("#FFFFFF"))
        }else{
            viewP.imageView.setImageResource(R.drawable.ic_logo_filigran)
            viewP.txtOfNoData.setTextColor(Color.parseColor("#191919"))
        }

        return viewP
    }

    //==================================================================================================================
    /**
     * Init User Following Activity Component...
     */
    //==================================================================================================================
    override fun initUserFollowingActivityComponents() {
        /*listOfFollowings =
            Gson().fromJson(OnedioSingletonPattern.instance.getUserProfileInfoData(), Response4UserProfileInfo::class.java).valOfUserProfileInfoData?.followings!!*/

        followingFragmentActivityPresenter =
            FollowingFragmentActivityPresenterImpl(
                FollowerFragmentActivityModelImpl(),
                this
            )

        listOfFollowings = OnedioSingletonPattern.instance.getUserFollowingsData()?.data!!

        if (listOfFollowings.isNotEmpty()){
            viewP.recylerUserFollowing.visibility = View.VISIBLE
            //viewP.searchAreaFollowing.visibility = View.VISIBLE
            viewP.noFollowingConstraintLayout.visibility = View.INVISIBLE

            propRecylerViewComponent()

        } else{
            viewP.recylerUserFollowing.visibility = View.INVISIBLE
            //viewP.searchAreaFollowing.visibility = View.INVISIBLE
            viewP.noFollowingConstraintLayout.visibility = View.VISIBLE

            viewP.txtOfNoData.text = "Kullanıcının takip ettiği \nkimse bulunmamaktadır"
        }

    }

    //==================================================================================================================
    /**
     * Change TypeFace...
     */
    //==================================================================================================================
    override fun changeTypeFace() {
        //viewP.searchAreaFollowing.typeface = OnedioCommon.changeTypeFace(activity!!)
        viewP.txtOfNoData.typeface = OnedioCommon.changeTypeFace(activity!!)
    }


    //==================================================================================================================
    /**
     * Prop RecylerView Component...
     */
    //==================================================================================================================
    @SuppressLint("WrongConstant")
    override fun propRecylerViewComponent() {
        viewP.recylerUserFollowing.layoutManager = LinearLayoutManager(activity?.applicationContext, LinearLayout.VERTICAL, false)

        mUserFollowingAdapter =
            ProfileUserFollowingRecylerAdapter2(
                activity?.applicationContext!!,
                listOfFollowings
            ) { itemOfUserProfilData, _ ->

                val intent = Intent(activity, ProfileActivityViewImpl::class.java)
                intent.putExtra("IS_USER_OWN_PROFILE", false)
                intent.putExtra("ANOTHER_USER_PROFILE", itemOfUserProfilData.id)
                startActivity(intent)

                /*OnedioSingletonPattern.instance.setAnotherUserId(
                    itemOfUserProfilData.id!!
                )
                OnedioSingletonPattern.instance.setIsUserOwnProfile(
                    false
                )
                startActivity(
                    Intent(
                        activity?.applicationContext,
                        ProfileActivityViewImpl::class.java
                    )
                )*/

            }

        viewP.recylerUserFollowing.adapter = mUserFollowingAdapter

        viewP.recylerUserFollowing.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1)) {

                    if(isContinueInfinite){
                        val userId = if (isOwnUserProfile)
                            "me"
                        else
                            anotheruserId

                        followingFragmentActivityPresenter.getUserFollowing(userId, page)
                    }
                }
            }
        })
    }

    override fun handleUserFollowerData(response4UserFollowerAndFollowing: Response4UserFollowerAndFollowing) {
        isContinueInfinite = response4UserFollowerAndFollowing.data?.size != 0

        page++
        setAdapter4Following(response4UserFollowerAndFollowing.data!!)
    }

    private fun setAdapter4Following(arrOfFollowingData: MutableList<UsersProfilData>) {
        mUserFollowingAdapter.addItems(arrOfFollowingData)
    }

    override fun showLoading() {
        viewP.avlIndicatorProgressFollowing.playAnimation()
        viewP.avlIndicatorProgressFollowing.visibility = View.VISIBLE
        /*activity?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )*/
    }

    override fun hideLoading() {
        viewP.avlIndicatorProgressFollowing.cancelAnimation()
        viewP.avlIndicatorProgressFollowing.visibility = View.GONE
        //activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    override fun showError(response4ErrorObj: Response4ErrorObj) {
        showEzDialogMessage(
            "Hata..!",
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
    }

    private fun showEzDialogMessage(
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

    //==================================================================================================================
    /**
     * Click Empty Area...
     */
    //==================================================================================================================
    fun clickEmptyArea() {
        viewP.followingConstraint.setOnClickListener() {
            hideSoftKeyboard(viewP.followingConstraint)
        }

    }

    //==================================================================================================================
    /**
     * Hide Soft Keyboard...
     */
    //==================================================================================================================
    fun hideSoftKeyboard(view: View) {
        val inputMethodManager = activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }


}