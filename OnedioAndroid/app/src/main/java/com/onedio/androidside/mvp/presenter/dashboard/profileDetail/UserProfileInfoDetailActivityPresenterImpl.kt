package com.onedio.androidside.mvp.presenter.dashboard.profileDetail

import com.onedio.androidside.IRequestListener
import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.profil.Response4UsersProfile
import com.onedio.androidside.mvp.model.dashboard.profileDetail.IUserProfileInfoDetailActivityModel
import com.onedio.androidside.mvp.model.dashboard.profileDetail.requestModel.UpdateUserProfileInfoParams
import com.onedio.androidside.mvp.model.dashboard.profileDetail.responseModel.Response4UpdateUserProfileInfo
import com.onedio.androidside.mvp.model.dashboard.profileDetail.responseModel.Response4UpdateUserProfilePhoto
import com.onedio.androidside.mvp.model.dashboard.profileDetail.responseModel.ValOfUpdateUserProfileInfoStatus
import com.onedio.androidside.mvp.model.dashboard.profileDetail.responseModel.countryList.Response4CountryList
import com.onedio.androidside.mvp.view.dashboard.profileFragment.generalSetting.profileSetting.IUserProfileInfoDetailActivityView
import retrofit2.Response

class UserProfileInfoDetailActivityPresenterImpl(
    val iUserProfileInfoDetailActivityModel: IUserProfileInfoDetailActivityModel,
    val iUserProfileInfoDetailActivityView: IUserProfileInfoDetailActivityView
) : IUserProfileInfoDetailActivityPresenter {

    override fun updateUserProfileInfoData(updateUserProfileInfoParams: UpdateUserProfileInfoParams) {
        iUserProfileInfoDetailActivityView.showLoading()
        iUserProfileInfoDetailActivityModel.updateUserProfileInfoData(
            updateUserProfileInfoParams,
            object :
                IRequestListener<Response4UpdateUserProfileInfo> {
                override fun onSuccess(response: Response<Response4UpdateUserProfileInfo>) {
                    iUserProfileInfoDetailActivityView.hideLoading()
                    iUserProfileInfoDetailActivityView.handleUpdateUserProfileInfo(response.body() as Response4UpdateUserProfileInfo)
                }

                override fun onUnSuccess(response: Response<Response4UpdateUserProfileInfo>) {
                    iUserProfileInfoDetailActivityView.hideLoading()
                    val errorObj = Response4ErrorObj()
                    errorObj.status =
                        ValOfUpdateUserProfileInfoStatus()
                    errorObj.status?.message =
                        "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                    iUserProfileInfoDetailActivityView.showError(errorObj)
                }

                override fun onFail(t: Throwable?) {
                    iUserProfileInfoDetailActivityView.hideLoading()
                }
            })
    }

    override fun updateUserProfileInfoDataNew(updateUserProfileInfoParams: UpdateUserProfileInfoParams) {
        iUserProfileInfoDetailActivityView.showLoading()
        iUserProfileInfoDetailActivityModel.updateUserProfileInfoDataNew(
            updateUserProfileInfoParams,
            object :
                IRequestListener<Response4UpdateUserProfileInfo> {
                override fun onSuccess(response: Response<Response4UpdateUserProfileInfo>) {
                    iUserProfileInfoDetailActivityView.hideLoading()
                    iUserProfileInfoDetailActivityView.handleUpdateUserProfileInfo(response.body() as Response4UpdateUserProfileInfo)
                }

                override fun onUnSuccess(response: Response<Response4UpdateUserProfileInfo>) {
                    iUserProfileInfoDetailActivityView.hideLoading()
                    val errorObj = Response4ErrorObj()
                    errorObj.status =
                        ValOfUpdateUserProfileInfoStatus()
                    errorObj.status?.message =
                        "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                    iUserProfileInfoDetailActivityView.showError(errorObj)
                }

                override fun onFail(t: Throwable?) {
                    iUserProfileInfoDetailActivityView.hideLoading()
                }
            })
    }

    override fun updateUserProfilePhoto(image: String) {
        iUserProfileInfoDetailActivityView.showLoading()
        iUserProfileInfoDetailActivityModel.updateUserProfilePhoto(image, object :
            IRequestListener<Response4UpdateUserProfilePhoto> {
            override fun onSuccess(response: Response<Response4UpdateUserProfilePhoto>) {
                iUserProfileInfoDetailActivityView.hideLoading()
                iUserProfileInfoDetailActivityView.handleUpdateUserProfilePhotoInfo(response.body() as Response4UpdateUserProfilePhoto)
            }

            override fun onUnSuccess(response: Response<Response4UpdateUserProfilePhoto>) {
                iUserProfileInfoDetailActivityView.hideLoading()
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                iUserProfileInfoDetailActivityView.showError(errorObj)
            }

            override fun onFail(t: Throwable?) {

            }
        })
    }

    override fun getUserProfileData(userId: String) {
        iUserProfileInfoDetailActivityView.showLoading()
        iUserProfileInfoDetailActivityModel.getUserProfileData(userId, object :
            IRequestListener<Response4UsersProfile> {
            override fun onSuccess(response: Response<Response4UsersProfile>) {
                iUserProfileInfoDetailActivityView.hideLoading()
                iUserProfileInfoDetailActivityView.handleUserProfileData(response.body() as Response4UsersProfile)
            }

            override fun onUnSuccess(response: Response<Response4UsersProfile>) {
                iUserProfileInfoDetailActivityView.hideLoading()
            }

            override fun onFail(t: Throwable?) {
                iUserProfileInfoDetailActivityView.hideLoading()
            }
        })
    }

    override fun getCountries() {
        iUserProfileInfoDetailActivityView.showLoading()
        iUserProfileInfoDetailActivityModel.getCountries(object :
            IRequestListener<Response4CountryList> {
            override fun onSuccess(response: Response<Response4CountryList>) {
                iUserProfileInfoDetailActivityView.hideLoading()
                iUserProfileInfoDetailActivityView.handleCountryListData(response.body() as Response4CountryList)
            }

            override fun onUnSuccess(response: Response<Response4CountryList>) {
                iUserProfileInfoDetailActivityView.hideLoading()
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                iUserProfileInfoDetailActivityView.showError(errorObj)
            }

            override fun onFail(t: Throwable?) {
                iUserProfileInfoDetailActivityView.hideLoading()
            }
        })
    }
}