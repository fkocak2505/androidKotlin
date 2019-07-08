package tr.com.fkocak.kotlinretrofit

import retrofit2.Response

interface IRequestResultListener<T> {

    fun onSuccess(response: Response<T>)
    fun onFail()

}