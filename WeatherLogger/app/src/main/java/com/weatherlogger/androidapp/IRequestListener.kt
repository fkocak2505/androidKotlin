package com.weatherlogger.androidapp

import retrofit2.Response

//==================================================================================================================
/**
 * This interface listening all request service and handle response data for arrived presenters..
 */
//==================================================================================================================
interface IRequestListener<T> {

    fun onSuccess(response: Response<T>)
    fun onUnSuccess(response: Response<T>)
    fun onFail(t: Throwable?)

}