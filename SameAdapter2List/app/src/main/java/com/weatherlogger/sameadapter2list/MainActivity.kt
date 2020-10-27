package com.weatherlogger.sameadapter2list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mAdapter: TrendingRecyclerViewAdapter
    private lateinit var mAdapter2: TrendingRecyclerViewAdapter

    val list: MutableList<String> = mutableListOf()
    val list1: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        trendingRecyclerView.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        trendingRecyclerView.layoutManager = layoutManager

        val dividerItemDecoration = DividerItemDecoration(
            trendingRecyclerView.context,
            layoutManager.orientation
        )
        trendingRecyclerView.addItemDecoration(dividerItemDecoration)

        trendingRecyclerView1.setHasFixedSize(true)
        val layoutManager1 = LinearLayoutManager(this)
        trendingRecyclerView1.layoutManager = layoutManager1

        val dividerItemDecoration1 = DividerItemDecoration(
            trendingRecyclerView1.context,
            layoutManager1.orientation
        )
        trendingRecyclerView1.addItemDecoration(dividerItemDecoration1)
        mAdapter = TrendingRecyclerViewAdapter(
            applicationContext!!,
            trendingData()
        )

        trendingRecyclerView.adapter = mAdapter

        mAdapter2 = TrendingRecyclerViewAdapter(
            applicationContext!!,
            trendingData1()
        )

        trendingRecyclerView1.adapter = mAdapter2
    }

    private fun trendingData(): MutableList<String> {
        list.add("Fatih")
        list.add("Fatih")
        list.add("Fatih")
        list.add("Fatih")
        list.add("Fatih")
        list.add("Fatih")
        list.add("Fatih")
        list.add("Fatih")

        return list
    }

    private fun trendingData1(): MutableList<String> {
        list1.add("Koçak")
        list1.add("Koçak")
        list1.add("Koçak")
        list1.add("Koçak")
        list1.add("Koçak")
        list1.add("Koçak")
        list1.add("Koçak")
        list1.add("Koçak")

        return list1
    }
}
