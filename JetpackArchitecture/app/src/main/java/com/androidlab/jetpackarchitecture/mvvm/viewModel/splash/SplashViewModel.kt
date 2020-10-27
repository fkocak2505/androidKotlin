package com.androidlab.jetpackarchitecture.mvvm.viewModel.splash

import androidx.lifecycle.MutableLiveData
import com.androidlab.jetpackarchitecture.listeners.IRequestListener
import com.androidlab.jetpackarchitecture.mvvm.model.ClientCredentials
import com.androidlab.jetpackarchitecture.mvvm.model.token.ITokenModelRepository
import com.androidlab.jetpackarchitecture.mvvm.model.token.TokenModel
import com.androidlab.jetpackarchitecture.mvvm.model.token.TokenModelRepositoryImpl
import com.androidlab.jetpackarchitecture.mvvm.view.splash.ISplashActivity
import com.androidlab.jetpackarchitecture.mvvm.viewModel.BaseViewModel
import retrofit2.Response

class SplashViewModel : BaseViewModel<ISplashActivity>() {

    private var tokenModel: MutableLiveData<TokenModel> = MutableLiveData()
    private var repositoryImpl: ITokenModelRepository = TokenModelRepositoryImpl()

    fun getTokenModel(): MutableLiveData<TokenModel> {
        return tokenModel
    }

    fun fetchAppToken() {
        repositoryImpl.getAppToken(
            ClientCredentials(
                "45",
                "45",
                "client_credentials"
            ), object : IRequestListener<TokenModel> {
            override fun onSuccess(response: Response<TokenModel>) {
                tokenModel.value = response.body()
            }

            override fun onUnSuccess(response: Response<TokenModel>) {
            }

            override fun onFail(t: Throwable?) {
            }
        })
    }
}