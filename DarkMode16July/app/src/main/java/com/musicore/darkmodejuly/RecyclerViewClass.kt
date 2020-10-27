package com.musicore.darkmodejuly

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main_2.*

class RecyclerViewClass: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_2)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = RecyclerViewAdapter(getTitleList())
    }

    private fun getTitleList(): MutableList<RecyclerViewDataModel>{
        return mutableListOf(
            RecyclerViewDataModel("Fatih"),
            RecyclerViewDataModel("Koçak"),
            RecyclerViewDataModel("aa"),
            RecyclerViewDataModel("ff"),
            RecyclerViewDataModel("ww"),
            RecyclerViewDataModel("gg"),
            RecyclerViewDataModel("ss"),
            RecyclerViewDataModel("hh"),
            RecyclerViewDataModel("jjk"),
            RecyclerViewDataModel("kky"),
            RecyclerViewDataModel("yy"),
            RecyclerViewDataModel("eee"),
            RecyclerViewDataModel("wew"),
            RecyclerViewDataModel("ökök")
        )
    }
}