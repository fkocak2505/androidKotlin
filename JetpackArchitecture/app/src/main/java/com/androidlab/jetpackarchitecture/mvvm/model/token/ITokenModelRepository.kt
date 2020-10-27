package com.androidlab.jetpackarchitecture.mvvm.model.token

import com.androidlab.jetpackarchitecture.listeners.IRequestListener
import com.androidlab.jetpackarchitecture.mvvm.model.ClientCredentials

interface ITokenModelRepository {

    fun getAppToken(clientCredentials: ClientCredentials, RequestListener: IRequestListener<TokenModel>)
}