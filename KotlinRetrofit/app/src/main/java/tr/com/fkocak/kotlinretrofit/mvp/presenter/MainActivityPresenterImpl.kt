package tr.com.fkocak.kotlinretrofit.mvp.presenter

import retrofit2.Response
import tr.com.fkocak.kotlinretrofit.IRequestResultListener
import tr.com.fkocak.kotlinretrofit.mvp.model.IMainActivityModel
import tr.com.fkocak.kotlinretrofit.mvp.model.responseModel.Hero
import tr.com.fkocak.kotlinretrofit.mvp.view.IMainActivityView

class MainActivityPresenterImpl(val iMainAcitivityModel: IMainActivityModel, val iMainActivityView: IMainActivityView) :
    IMainActivityPresenter {


    override fun getHeroes() {
        iMainAcitivityModel.getHeroes(object : IRequestResultListener<List<Hero>>{
            override fun onSuccess(response: Response<List<Hero>>) {
                /// Gelen  Data
                iMainActivityView.handleHeroesData(response.body())
            }

            override fun onFail() {
                /// Hata olma Durumu
            }
        })
    }
}