package com.musicore.recyclerviewinsidescrollview

import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var mAdapter: CategoryFeedRecyclerViewAdapter

    private lateinit var layoutmanager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        denemeRecyclerView.setHasFixedSize(true)
        layoutmanager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        denemeRecyclerView.layoutManager = layoutmanager


        mAdapter =
            CategoryFeedRecyclerViewAdapter(applicationContext, getData()) { item, position ->
                Toast.makeText(applicationContext, "$item $position", Toast.LENGTH_SHORT).show()
            }

        denemeRecyclerView.adapter = mAdapter

        val display = windowManager.defaultDisplay
        val outMetrics = DisplayMetrics()
        display.getMetrics(outMetrics)

        val density = resources.displayMetrics.density
        val dpHeight = outMetrics.heightPixels / density
        val dpWidth = outMetrics.widthPixels / density

        var a = 2

    }

    private fun getData(): MutableList<String> {
        val data = mutableListOf<String>()

        data.add("Fatih")
        data.add("Fatih")
        data.add("Fatih")
        data.add("Fatih")
        data.add("Fatih")
        data.add("Fatih")
        data.add("Fatih")
        data.add("Fatih")
        data.add("Fatih")
        data.add("Fatih")
        data.add("Fatih")
        data.add("Fatih")
        data.add("Fatih")
        data.add("Fatih")
        data.add("Fatih")
        data.add("Fatih")
        data.add("Fatih")
        data.add("Fatih")
        data.add("Fatih")
        data.add("Fatih")

        return data

    }
}