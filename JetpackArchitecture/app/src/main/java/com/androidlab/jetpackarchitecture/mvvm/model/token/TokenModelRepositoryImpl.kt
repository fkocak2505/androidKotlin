package com.androidlab.jetpackarchitecture.mvvm.model.token

import com.androidlab.jetpackarchitecture.listeners.IRequestListener
import com.androidlab.jetpackarchitecture.mvvm.model.ClientCredentials
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import tr.com.androidside.onedio.api.ApiUtils
import tr.com.androidside.onedio.api.interfaced.IApiService

class TokenModelRepositoryImpl : ITokenModelRepository{

    private val iApiService: IApiService = ApiUtils.getAuthService()

    override fun getAppToken(clientCredentials: ClientCredentials, iRequestListener: IRequestListener<TokenModel>) {
        try {
            val request: Single<Response<TokenModel>> = iApiService.getAppToken(clientCredentials)

            request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<Response<TokenModel>> {
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onSuccess(response: Response<TokenModel>) {
                        if(response.isSuccessful) iRequestListener.onSuccess(response) else iRequestListener.onUnSuccess(response)
                    }

                    override fun onError(e: Throwable) {
                        iRequestListener.onFail(e)
                    }
                })
        } catch (e: Exception) {

        }
    }

}