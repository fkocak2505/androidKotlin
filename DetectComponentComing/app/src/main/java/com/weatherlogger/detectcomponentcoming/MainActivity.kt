package com.weatherlogger.detectcomponentcoming

import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*





class MainActivity : AppCompatActivity() {

    private lateinit var model: MutableList<CountryModel>
    private var isShow = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        /*val itemDecorator = DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
        itemDecorator.setDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.recycler_view_divider)!!)

        recyclerView.addItemDecoration(itemDecorator)*/

        recyclerView.adapter = RecylerAdapter(getModels()) {
            Toast.makeText(applicationContext, getModels()[it].capitalName, Toast.LENGTH_SHORT)
                .show()
        }



        val scrollBound = Rect()
        nestedScrollView.getHitRect(scrollBound)


        if (textView4.getLocalVisibleRect(scrollBound)) {
            if (!textView4.getLocalVisibleRect(scrollBound)
                || scrollBound.height() < textView4.height) {
                Toast.makeText(applicationContext, "BTN APPEAR PARCIALY", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "BTN APPEAR FULLY", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(applicationContext, "No", Toast.LENGTH_SHORT).show()
        }



        nestedScrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (!isShow && recyclerView.getLocalVisibleRect(scrollBound)) {
                isShow = true
                /*if (scrollBound.height() < recyclerView.height) {
                    Log.i("BTN STATUS --> ", "Parçası içerde")
                } else {
                    Log.i("BTN STATUS --> ", "Tamamı içerde")
                }*/
                Log.i("BTN STATUS --> ", "İçerde")
            }

        })

    }

    private fun getModels(): MutableList<CountryModel> {

        model = mutableListOf(
            CountryModel(R.drawable.ic_launcher_background, "Çin", "Pekin"),
            CountryModel(R.drawable.ic_launcher_background, "Mısır", "Kahire"),
            CountryModel(R.drawable.ic_launcher_background, "Almanya", "Berlin"),
            CountryModel(R.drawable.ic_launcher_background, "Mısır", "Kahire"),
            CountryModel(R.drawable.ic_launcher_background, "Mısır", "Kahire"),
            CountryModel(R.drawable.ic_launcher_background, "Mısır", "Kahire"),
            CountryModel(R.drawable.ic_launcher_background, "Mısır", "Kahire"),
            CountryModel(R.drawable.ic_launcher_background, "Mısır", "Kahire"),
            CountryModel(R.drawable.ic_launcher_background, "Mısır", "Kahire")
        )
        return model
    }
}
