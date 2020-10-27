package com.onedio.androidside.mvp.view.dashboard.profileFragment.fragments.userFollowersAndFollowing.followers

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
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_follower.view.*
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.common.model.OnedioEzDialogMessageModel4Fragment
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.profil.Response4UserFollowerAndFollowing
import com.onedio.androidside.mvp.model.dashboard.profil.UsersProfilData
import com.onedio.androidside.mvp.view.dashboard.homeFragment.profile.ProfileActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.profileFragment.fragments.userFollowersAndFollowing.followers.followersRecylerAdapter.ProfileUserFollowerRecylerAdapter2
import com.onedio.androidside.singleton.OnedioSingletonPattern

class FollowerFragmentActivityViewImpl : Fragment(),
    IFollowerFragmentActivityView {


    private lateinit var viewP: View

    private lateinit var listOfFollowers: MutableList<UsersProfilData>

    private lateinit var followerFragmentActivityPresenter: IFollowerFragmentActivityPresenter

    private lateinit var mUserFollowerAdapter: ProfileUserFollowerRecylerAdapter2

    private var page = 2

    private var isContinueInfinite = true

    private var isOwnUserProfile: Boolean = false
    private lateinit var anotheruserId: String

    private var theme: String = ""

    //==================================================================================================================
    /**
     * onCreateView Override Method
     */
    //==================================================================================================================
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewP = inflater.inflate(R.layout.fragment_follower, container, false)

        val sharedPref = activity?.applicationContext?.getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        theme = sharedPref?.getString("mode", "default")!!

        isOwnUserProfile = activity?.intent?.getBooleanExtra("IS_USER_OWN_PROFILE", false)!!
        anotheruserId = activity?.intent?.getStringExtra("ANOTHER_USER_PROFILE")!!

        initFollowerComponent()

        changeComponentTypeFace()

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
     * Init Follower Component...
     */
    //==================================================================================================================
    override fun initFollowerComponent() {

        /*listOfFollowers =
            Gson().fromJson(OnedioSingletonPattern.instance.getUserProfileInfoData(), Response4UserProfileInfo::class.java).valOfUserProfileInfoData?.followers!!*/

        followerFragmentActivityPresenter =
            FollowerFragmentActivityPresenterImpl(
                FollowerFragmentActivityModelImpl(),
                this
            )

        listOfFollowers = OnedioSingletonPattern.instance.getUserFollowersData()?.data!!

        if (listOfFollowers.isNotEmpty()) {
            viewP.recylerUserFollower.visibility = View.VISIBLE
            //viewP.searchAreaFollower.visibility = View.VISIBLE
            viewP.noFollowerConstraintLayout.visibility = View.INVISIBLE

            propRecylerViewComponent()
        } else {
            viewP.recylerUserFollower.visibility = View.INVISIBLE
            //viewP.searchAreaFollower.visibility = View.INVISIBLE
            viewP.noFollowerConstraintLayout.visibility = View.VISIBLE

            viewP.txtOfNoData.text = "Kullanıcıya ait takipçi \nbulunmamaktadır"
        }

    }

    //==================================================================================================================
    /**
     * Change Component Typeface...
     */
    //==================================================================================================================
    override fun changeComponentTypeFace() {
        //viewP.searchAreaFollower.typeface = OnedioCommon.changeTypeFace(activity!!)
        viewP.txtOfNoData.typeface = OnedioCommon.changeTypeFace(activity!!)
    }

    //==================================================================================================================
    /**
     * Prop RecylerView Component...
     */
    //==================================================================================================================
    @SuppressLint("WrongConstant")
    override fun propRecylerViewComponent() {
        viewP.recylerUserFollower.layoutManager =
            LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.VERTICAL, false)

        mUserFollowerAdapter =
            ProfileUserFollowerRecylerAdapter2(
                activity?.applicationContext!!,
                listOfFollowers
            ) { itemOfUserProfile, _ ->

                val intent = Intent(activity, ProfileActivityViewImpl::class.java)
                intent.putExtra("IS_USER_OWN_PROFILE", false)
                intent.putExtra("ANOTHER_USER_PROFILE", itemOfUserProfile.id)
                startActivity(intent)

                /*OnedioSingletonPattern.instance.setAnotherUserId(
                    itemOfUserProfile.id!!
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

        viewP.recylerUserFollower.adapter = mUserFollowerAdapter

        viewP.recylerUserFollower.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if(isContinueInfinite){
                        val userId = if (isOwnUserProfile)
                            "me"
                        else
                            anotheruserId

                        followerFragmentActivityPresenter.getUserFollowers(userId, page)
                    }
                }
            }
        })


    }

    override fun handleUserFollowerData(response4UserFollowerAndFollowing: Response4UserFollowerAndFollowing) {

        isContinueInfinite = response4UserFollowerAndFollowing.data?.size != 0

        page++
        setAdapter4Follower(response4UserFollowerAndFollowing.data!!)
    }

    private fun setAdapter4Follower(arrOfFollowersData: MutableList<UsersProfilData>) {
        mUserFollowerAdapter.addItems(arrOfFollowersData)
    }


    override fun showLoading() {
        viewP.avlIndicatorProgressFollower.playAnimation()
        viewP.avlIndicatorProgressFollower.visibility = View.GONE
        /*activity?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )*/
    }

    override fun hideLoading() {
        viewP.avlIndicatorProgressFollower.cancelAnimation()
        viewP.avlIndicatorProgressFollower.visibility = View.GONE
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
        viewP.followerConstraint.setOnClickListener() {
            hideSoftKeyboard(viewP.followerConstraint)
        }

    }

    //==================================================================================================================
    /**
     * Hide Soft Keyboard...
     */
    //==================================================================================================================
    fun hideSoftKeyboard(view: View) {
        val inputMethodManager =
            activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }


}