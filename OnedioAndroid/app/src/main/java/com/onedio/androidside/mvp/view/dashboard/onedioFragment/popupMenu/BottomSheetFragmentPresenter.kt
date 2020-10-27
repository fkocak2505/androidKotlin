package com.onedio.androidside.mvp.view.dashboard.onedioFragment.popupMenu

import com.onedio.androidside.IRequestListener
import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel.Response4ArticlesFeed
import com.onedio.androidside.mvp.model.dashboard.profileDetail.responseModel.ValOfUpdateUserProfileInfoStatus
import retrofit2.Response

class BottomSheetFragmentPresenter(
    private val iBottomSheetFragmentModel: IBottomSheetFragmentModel,
    val iBottomSheetFragment: IBottomSheetFragment
): IBottomSheetFragmetnPresenter {
    override fun getOnedioButtonData(
        wName: String,
        targetId: String,
        page: Int,
        perPage: Int
    ) {
        iBottomSheetFragment.showLoading()
        iBottomSheetFragmentModel.getArticleFeedCategoryWidgetDataWidthTagId(targetId, page, perPage, object :
            IRequestListener<Response4ArticlesFeed> {
            override fun onSuccess(response: Response<Response4ArticlesFeed>) {
                iBottomSheetFragment.hideLoading()
                iBottomSheetFragment.handleOnedioButtonData(wName, response.body() as Response4ArticlesFeed)
            }

            override fun onUnSuccess(response: Response<Response4ArticlesFeed>) {
                iBottomSheetFragment.hideLoading()
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message = "Şu an teknik bir arıza mevcut. Daha sonra tekrar deneyin.."
                iBottomSheetFragment.showError(errorObj)
            }

            override fun onFail(t: Throwable?) {
                iBottomSheetFragment.hideLoading()
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message = "Lütfen internet bağlantınızı kontrol edip tekrar deneyiniz."
                iBottomSheetFragment.showError(errorObj)
            }
        })
    }
}