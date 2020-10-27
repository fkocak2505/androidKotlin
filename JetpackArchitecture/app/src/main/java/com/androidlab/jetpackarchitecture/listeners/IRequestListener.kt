package com.androidlab.jetpackarchitecture.listeners

import retrofit2.Response

//==================================================================================================================
/**
 *
 *
 * This interface listening all request service and hadnle response data for arrived presenters..
 *
 *
 */
//==================================================================================================================
interface IRequestListener<T> {

    fun onSuccess(response: Response<T>)
    fun onUnSuccess(response: Response<T>)
    fun onFail(t: Throwable?)

}