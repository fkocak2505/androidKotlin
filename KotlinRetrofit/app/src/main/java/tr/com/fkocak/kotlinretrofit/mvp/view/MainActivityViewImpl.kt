package tr.com.fkocak.kotlinretrofit.mvp.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import tr.com.fkocak.kotlinretrofit.R
import tr.com.fkocak.kotlinretrofit.mvp.model.MainActivityModelImpl
import tr.com.fkocak.kotlinretrofit.mvp.model.responseModel.Hero
import tr.com.fkocak.kotlinretrofit.mvp.presenter.MainActivityPresenterImpl
import tr.com.fkocak.kotlinretrofit.mvp.view.adapter.HeroesAdapter

class MainActivityViewImpl : AppCompatActivity(), IMainActivityView {

    var mainActivityPresenter : MainActivityPresenterImpl? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)

        mainActivityPresenter = MainActivityPresenterImpl(MainActivityModelImpl(), this)

        getHeroes()


    }

    override fun getHeroes() {
        mainActivityPresenter?.getHeroes()
    }

    override fun handleHeroesData(hero: List<Hero>) {
        recyclerView.adapter = HeroesAdapter(hero.toMutableList()){
            Toast.makeText(applicationContext,hero.get(it).name,Toast.LENGTH_SHORT).show()
        }
    }
}
