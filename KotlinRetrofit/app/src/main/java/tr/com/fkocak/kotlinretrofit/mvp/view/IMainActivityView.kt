package tr.com.fkocak.kotlinretrofit.mvp.view

import tr.com.fkocak.kotlinretrofit.mvp.model.responseModel.Hero

interface IMainActivityView {

    fun getHeroes()

    fun handleHeroesData(hero: List<Hero>)

}