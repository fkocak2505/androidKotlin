package com.onedio.androidside.mvp.view.dashboard.profileFragment.fragments.userFollowersAndFollowing.following

import retrofit2.Response
import com.onedio.androidside.IRequestListener
import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.profil.Response4UserFollowerAndFollowing
import com.onedio.androidside.mvp.model.dashboard.profileDetail.responseModel.ValOfUpdateUserProfileInfoStatus
import com.onedio.androidside.mvp.view.dashboard.profileFragment.fragments.userFollowersAndFollowing.followers.IFollowerFragmentActivityModel

class FollowingFragmentActivityPresenterImpl(val iFollowerFragmentActivityModel: IFollowerFragmentActivityModel,
                                             val iFollowerFragmentActivityView: IFollowingFragmentActivityView
): IFollowingFragmentActivityPresenter {


    override fun getUserFollowing(userId: String, page: Int) {
        iFollowerFragmentActivityView.showLoading()
        iFollowerFragmentActivityModel.getUserFollowing(userId, page,  object :
            IRequestListener<Response4UserFollowerAndFollowing> {
            override fun onSuccess(response: Response<Response4UserFollowerAndFollowing>) {
                iFollowerFragmentActivityView.hideLoading()
                iFollowerFragmentActivityView.handleUserFollowerData(response.body() as Response4UserFollowerAndFollowing)
            }

            override fun onUnSuccess(response: Response<Response4UserFollowerAndFollowing>) {
                iFollowerFragmentActivityView.hideLoading()
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message = "Şu an teknik bir arıza mevcut. Daha sonra tekrar deneyin.."
                iFollowerFragmentActivityView.showError(errorObj)
            }

            override fun onFail(t: Throwable?) {
                iFollowerFragmentActivityView.hideLoading()
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message = "Şu an teknik bir arıza mevcut. Daha sonra tekrar deneyin.."
                iFollowerFragmentActivityView.showError(errorObj)
            }
        })
    }


}