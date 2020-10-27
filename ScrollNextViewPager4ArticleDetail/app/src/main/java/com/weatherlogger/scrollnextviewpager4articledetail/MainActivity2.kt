package com.weatherlogger.scrollnextviewpager4articledetail

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.weatherlogger.scrollnextviewpager4articledetail.model.ArticleModel
import com.weatherlogger.scrollnextviewpager4articledetail.model.ArticleObj
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val arrOfData = mutableListOf<ArticleModel>()

        arrOfData.add(
            ArticleModel(
                "1",
                100000,
                true
            )
        )

        arrOfData.add(
            ArticleModel(
                "2",
                2000000,
                false
            )
        )

        arrOfData.add(
            ArticleModel(
                "3",
                3000000,
                false
            )
        )

        arrOfData.add(
            ArticleModel(
                "4",
                4000000,
                false
            )
        )

        arrOfData.add(
            ArticleModel(
                "5",
                5000000,
                true
            )
        )

        arrOfData.add(
            ArticleModel(
                "6",
                6000000,
                false
            )
        )

        arrOfData.add(
            ArticleModel(
                "7",
                7000000,
                false
            )
        )

        arrOfData.add(
            ArticleModel(
                "8",
                8000000,
                false
            )
        )

        arrOfData.add(
            ArticleModel(
                "9",
                9000000,
                false
            )
        )

        val index = arrOfData.indexOfFirst { e ->
            e.legacyId.toInt() == 10000000
        }

        //val index = (0 until 9).random()
        val newArr = mutableListOf<ArticleModel>()

        for (i in index until 9) {
            newArr.add(
                ArticleModel(
                    arrOfData[i].id,
                    arrOfData[i].legacyId,
                    arrOfData[i].isTest
                )
            )
        }

        for (j in 0 until index) {
            newArr.add(
                ArticleModel(
                    arrOfData[j].id,
                    arrOfData[j].legacyId,
                    arrOfData[j].isTest
                )
            )
        }

        Log.i("--- INDEX ---", index.toString())

        val articleObj =
            ArticleObj(newArr)

        Log.i("--- DATA ---", Gson().toJson(articleObj))


        goAnotherActivity.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.putExtra("DATA_MODEL", Gson().toJson(articleObj))
            startActivity(intent)
        }
    }
}