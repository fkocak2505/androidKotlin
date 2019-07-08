package tr.com.fkocak.kotlinretrofit.mvp.model

import tr.com.fkocak.kotlinretrofit.IRequestResultListener
import tr.com.fkocak.kotlinretrofit.mvp.model.responseModel.Hero

interface IMainActivityModel {

    fun initApiService()

    fun getHeroes(iRequestResultListener: IRequestResultListener<List<Hero>>)

}