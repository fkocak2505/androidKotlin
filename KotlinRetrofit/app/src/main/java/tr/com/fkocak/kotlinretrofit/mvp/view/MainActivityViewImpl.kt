package tr.com.fkocak.kotlinretrofit.mvp.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import tr.com.fkocak.kotlinretrofit.R
import tr.com.fkocak.kotlinretrofit.mvp.model.MainActivityModelImpl
import tr.com.fkocak.kotlinretrofit.mvp.presenter.MainActivityPresenterImpl

class MainActivityViewImpl : AppCompatActivity(), IMainActivityView {

    var mainActivityPresenter : MainActivityPresenterImpl? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)

        mainActivityPresenter = MainActivityPresenterImpl(MainActivityModelImpl(), this)

        getHeroes()

        /*RetrofitClient.getClient().create(ApiService::class.java)
            .getHeroes().enqueue(object : retrofit2.Callback<List<Hero>> {
                override fun onFailure(call: Call<List<Hero>>?, t: Throwable?) {
                    Toast.makeText(applicationContext,"Fail",Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<List<Hero>>?, response: Response<List<Hero>>?) {
                    val heroList = ArrayList(response?.body())

                    Toast.makeText(applicationContext,"Successfully",Toast.LENGTH_SHORT).show()
                    recyclerView.adapter = HeroesAdapter(heroList){
                        Toast.makeText(applicationContext,heroList.get(it).name,Toast.LENGTH_SHORT).show()
                    }
                }
            })*/
    }

    override fun getHeroes() {
        mainActivityPresenter?.getHeroes()
    }
}
