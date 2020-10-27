package com.onedio.androidside.mvp.presenter.dashboard

import com.onedio.androidside.IRequestListener
import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.IUserProfileActivityModel
import com.onedio.androidside.mvp.model.dashboard.profil.Response4UserFavoriteArticle
import com.onedio.androidside.mvp.model.dashboard.profil.Response4UserFollowerAndFollowing
import com.onedio.androidside.mvp.model.dashboard.profil.Response4UsersProfile
import com.onedio.androidside.mvp.model.dashboard.profileDetail.responseModel.ValOfUpdateUserProfileInfoStatus
import com.onedio.androidside.mvp.model.dashboard.responseModel.Response4UserProfileInfo
import com.onedio.androidside.mvp.model.noDesigned.blockedUser.responseModel.Response4UserBlockedList
import com.onedio.androidside.mvp.model.noDesigned.doBlockUser.responseModel.Response4DoBlockedUser
import com.onedio.androidside.mvp.view.dashboard.homeFragment.profile.Response4UserArticle
import com.onedio.androidside.mvp.view.dashboard.profileFragment.IProfileFragmentActivityView
import com.onedio.androidside.mvp.view.loginAndRegister.login.ILoginFragmentActivityView
import retrofit2.Response

class UserProfileInfoActivityPresenterImpl() :
    IUserProfileInfoActivityPresenter {

    //// Models
    private var iUserProfileActivityModel: IUserProfileActivityModel? = null

    //// Activity Views
    private var iUserProfileActivityView: IProfileFragmentActivityView? = null
    private var iLoginFragmentActivityView: ILoginFragmentActivityView? = null

    //// Constructor 4 User Profile
    constructor(
        iUserProfileActivityModel: IUserProfileActivityModel,
        iUserProfileActivityView: IProfileFragmentActivityView
    ) : this() {
        this.iUserProfileActivityModel = iUserProfileActivityModel
        this.iUserProfileActivityView = iUserProfileActivityView
    }

    //// Constructor 4 Gettin Data from Login
    constructor(
        iUserProfileActivityModel: IUserProfileActivityModel,
        iLoginFragmentActivityView: ILoginFragmentActivityView
    ) : this() {
        this.iUserProfileActivityModel = iUserProfileActivityModel
        this.iLoginFragmentActivityView = iLoginFragmentActivityView
    }


    override fun getUserProfileData(userId: String) {
        iUserProfileActivityView?.let {
            iUserProfileActivityView!!.showLoading()
        } ?: run {
            iLoginFragmentActivityView!!.showLoading()
        }

        iUserProfileActivityModel?.getUserProfileData(userId, object :
            IRequestListener<Response4UsersProfile> {
            override fun onSuccess(response: Response<Response4UsersProfile>) {
                iUserProfileActivityView?.let {
                    iUserProfileActivityView!!.hideLoading()
                    iUserProfileActivityView?.handleUserProfileInfoData(
                        response.body() as Response4UsersProfile,
                        userId
                    )
                } ?: run {
                    iLoginFragmentActivityView!!.hideLoading()
                    iLoginFragmentActivityView?.handleUsersProfileData(response.body() as Response4UsersProfile)
                }
            }

            override fun onUnSuccess(response: Response<Response4UsersProfile>) {
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                iUserProfileActivityView?.let {
                    iUserProfileActivityView!!.hideLoading()
                    iUserProfileActivityView!!.showError(errorObj)
                } ?: run {
                    iLoginFragmentActivityView!!.hideLoading()
                    iLoginFragmentActivityView!!.showError(errorObj)
                }
            }

            override fun onFail(t: Throwable?) {
                iUserProfileActivityView?.let {
                    iUserProfileActivityView!!.hideLoading()
                } ?: run {
                    iLoginFragmentActivityView!!.hideLoading()
                }
            }
        })

    }


    override fun getUserFavoriteArticles(userId: String) {
        iUserProfileActivityView?.showLoading()
        iUserProfileActivityModel?.getUserFavoriteArticles(userId, object :
            IRequestListener<Response4UserFavoriteArticle> {
            override fun onSuccess(response: Response<Response4UserFavoriteArticle>) {
                iUserProfileActivityView?.hideLoading()
                iUserProfileActivityView?.handleUserFavoriteArticle(
                    response.body() as Response4UserFavoriteArticle,
                    userId
                )
            }

            override fun onUnSuccess(response: Response<Response4UserFavoriteArticle>) {
                iUserProfileActivityView?.hideLoading()
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                iUserProfileActivityView!!.showError(errorObj)
            }

            override fun onFail(t: Throwable?) {
                iUserProfileActivityView?.hideLoading()
            }
        })
    }

    override fun getUserFavoriteArticlesInfinite(userId: String, page: Int) {
        iUserProfileActivityView?.showLoading()
        iUserProfileActivityModel?.getUserFavoriteArticlesInfinite(userId, page, object :
            IRequestListener<Response4UserFavoriteArticle> {
            override fun onSuccess(response: Response<Response4UserFavoriteArticle>) {
                iUserProfileActivityView?.hideLoading()
                iUserProfileActivityView?.handleUserFavoriteArticleInfinite(
                    response.body() as Response4UserFavoriteArticle,
                    userId
                )
            }

            override fun onUnSuccess(response: Response<Response4UserFavoriteArticle>) {
                iUserProfileActivityView?.hideLoading()
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                iUserProfileActivityView!!.showError(errorObj)
            }

            override fun onFail(t: Throwable?) {
                iUserProfileActivityView?.hideLoading()
            }
        })
    }

    override fun getUserArticleData(userId: String) {
        iUserProfileActivityView?.showLoading()
        iUserProfileActivityModel?.getUserArticleData(userId, object :
            IRequestListener<Response4UserArticle> {
            override fun onSuccess(response: Response<Response4UserArticle>) {
                iUserProfileActivityView?.hideLoading()
                iUserProfileActivityView?.handleUserArticlesData(
                    response.body() as Response4UserArticle,
                    userId
                )
            }

            override fun onUnSuccess(response: Response<Response4UserArticle>) {
                iUserProfileActivityView?.hideLoading()
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                iUserProfileActivityView!!.showError(errorObj)
            }

            override fun onFail(t: Throwable?) {
                iUserProfileActivityView?.hideLoading()
                iUserProfileActivityView?.forNow()
            }
        })

    }

    override fun getUserArticlesInfinite(userId: String, page: Int) {
        iUserProfileActivityView?.showLoading()
        iUserProfileActivityModel?.getUserArticlesInfinite(userId, page, object :
            IRequestListener<Response4UserArticle> {
            override fun onSuccess(response: Response<Response4UserArticle>) {
                iUserProfileActivityView?.hideLoading()
                iUserProfileActivityView?.handleUserArticlesDataInfinite(response.body() as Response4UserArticle)
            }

            override fun onUnSuccess(response: Response<Response4UserArticle>) {
                iUserProfileActivityView?.hideLoading()
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                iUserProfileActivityView!!.showError(errorObj)
            }

            override fun onFail(t: Throwable?) {
                iUserProfileActivityView?.hideLoading()
                iUserProfileActivityView?.forNow()
            }
        })
    }

    override fun getUserFollowings(userId: String, page: Int) {
        iUserProfileActivityView?.showLoading()
        iUserProfileActivityModel?.getUserFollowings(userId, page, object :
            IRequestListener<Response4UserFollowerAndFollowing> {
            override fun onSuccess(response: Response<Response4UserFollowerAndFollowing>) {
                iUserProfileActivityView?.hideLoading()
                iUserProfileActivityView?.handleUserFollowingsData(response.body() as Response4UserFollowerAndFollowing)
            }

            override fun onUnSuccess(response: Response<Response4UserFollowerAndFollowing>) {
                iUserProfileActivityView?.hideLoading()
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                iUserProfileActivityView!!.showError(errorObj)
            }

            override fun onFail(t: Throwable?) {

            }
        })
    }

    override fun getUserFollowers(userId: String, page: Int) {
        iUserProfileActivityView?.showLoading()
        iUserProfileActivityModel?.getUserFollowers(userId, page, object :
            IRequestListener<Response4UserFollowerAndFollowing> {
            override fun onSuccess(response: Response<Response4UserFollowerAndFollowing>) {
                iUserProfileActivityView?.hideLoading()
                iUserProfileActivityView?.handleUserFollowersData(response.body() as Response4UserFollowerAndFollowing)
            }

            override fun onUnSuccess(response: Response<Response4UserFollowerAndFollowing>) {
                iUserProfileActivityView?.hideLoading()
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                iUserProfileActivityView!!.showError(errorObj)
            }

            override fun onFail(t: Throwable?) {
                iUserProfileActivityView?.hideLoading()
            }
        })
    }


    //==================================================================================================================
    /**
     * Presenter of UserProfileInfo Data..
     */
    //==================================================================================================================
    override fun getUserProfileInfo() {
        iUserProfileActivityView?.let {
            iUserProfileActivityView!!.showLoading()
        } ?: run {
            iLoginFragmentActivityView!!.showLoading()
        }

        iUserProfileActivityModel?.getUserProfileInfo(object :
            IRequestListener<Response4UserProfileInfo> {
            override fun onSuccess(response: Response<Response4UserProfileInfo>) {
                iUserProfileActivityView?.let {
                    iUserProfileActivityView!!.hideLoading()
                    //iUserProfileActivityView?.handleUserProfileInfoData(response.body() as Response4UserProfileInfo)
                } ?: run {
                    iLoginFragmentActivityView!!.hideLoading()
                    iLoginFragmentActivityView?.handleUserProfileInfoData(response.body() as Response4UserProfileInfo)
                }
            }

            override fun onUnSuccess(response: Response<Response4UserProfileInfo>) {

            }

            override fun onFail(t: Throwable?) {

            }
        })
    }

    override fun getAnotherUserProfile() {
        iUserProfileActivityView?.showLoading()
        iUserProfileActivityModel?.getAnotherUserProfile(object :
            IRequestListener<Response4UserProfileInfo> {
            override fun onSuccess(response: Response<Response4UserProfileInfo>) {
                iUserProfileActivityView?.hideLoading()
                iUserProfileActivityView?.handleAnotherUserProfileInfoData(response.body() as Response4UserProfileInfo)
            }

            override fun onUnSuccess(response: Response<Response4UserProfileInfo>) {

            }

            override fun onFail(t: Throwable?) {

            }
        })
    }

    override fun doBlockUser() {
        iUserProfileActivityView?.showLoading()
        iUserProfileActivityModel?.doBlockUser(object :
            IRequestListener<Response4DoBlockedUser> {
            override fun onSuccess(response: Response<Response4DoBlockedUser>) {
                iUserProfileActivityView?.hideLoading()
                iUserProfileActivityView?.handleAnotherUserBlockInfoData(response.body() as Response4DoBlockedUser)
            }

            override fun onUnSuccess(response: Response<Response4DoBlockedUser>) {

            }

            override fun onFail(t: Throwable?) {

            }
        })
    }

    override fun getUserBlockedList() {
        iUserProfileActivityView?.showLoading()
        iUserProfileActivityModel?.getUserBlockedList(object :
            IRequestListener<Response4UserBlockedList> {
            override fun onSuccess(response: Response<Response4UserBlockedList>) {
                iUserProfileActivityView?.hideLoading()
                iUserProfileActivityView?.handleUserBlockedList(response.body() as Response4UserBlockedList)
            }

            override fun onUnSuccess(response: Response<Response4UserBlockedList>) {

            }

            override fun onFail(t: Throwable?) {

            }
        })
    }

    /*override fun getUserArticleData(userId: String) {
        iUserProfileActivityView.showLoading()
        iUserProfileActivityModel.getUserArticleData(userId, object : IRequestListener<Response4UserContentData> {
            override fun onSuccess(response: Response<Response4UserContentData>) {
                iUserProfileActivityView.hideLoading()
                iUserProfileActivityView.handleUserContentArticleData(response.body() as Response4UserContentData)
            }

            override fun onUnSuccess(response: Response<Response4UserContentData>) {
                iUserProfileActivityView.hideLoading()
            }

            override fun onFail(t: Throwable?) {
                iUserProfileActivityView.hideLoading()
            }
        })
    }*/

    override fun getUserFavoriteData(userId: String) {
        /*iUserProfileActivityView.showLoading()
        iUserProfileActivityModel.getUserFavoriteData(userId, object : IRequestListener<Response4UserContentData>{
            override fun onSuccess(response: Response<Response4UserContentData>) {
                iUserProfileActivityView.hideLoading()
                iUserProfileActivityView.handleUserContentFavoriteData(response.body() as Response4UserContentData)
            }

            override fun onUnSuccess(response: Response<Response4UserContentData>) {
                iUserProfileActivityView.hideLoading()
            }

            override fun onFail(t: Throwable?) {
                iUserProfileActivityView.hideLoading()
            }
        })*/
    }

}