package com.example.gridviewkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridView

class MainActivity : AppCompatActivity() {

    var list_lang: ScrollableGridView? = null
    var langaugeList: ArrayList<Langauge> = ArrayList<Langauge>()
    var adapter: LangListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list_lang= findViewById(R.id.gv_test)
        addLangData()
        adapter = LangListAdapter(langaugeList, this)
        list_lang?.adapter = adapter
    }

    private fun addLangData() {
        val lang1 = Langauge("Android", R.drawable.ic_launcher_background)
        langaugeList.add(lang1)

        val lang2 = Langauge("Android", R.drawable.ic_launcher_background)
        langaugeList.add(lang2)

        val lang3 = Langauge("Android", R.drawable.ic_launcher_background)
        langaugeList.add(lang3)

        val lang4 = Langauge("Android", R.drawable.ic_launcher_background)
        langaugeList.add(lang4)

        val lang5 = Langauge("Android", R.drawable.ic_launcher_background)
        langaugeList.add(lang5)

        val lang6 = Langauge("Android", R.drawable.ic_launcher_background)
        langaugeList.add(lang6)

        val lang7 = Langauge("Android", R.drawable.ic_launcher_background)
        langaugeList.add(lang7)

        val lang8 = Langauge("Android", R.drawable.ic_launcher_background)
        langaugeList.add(lang8)

        val lang9 = Langauge("Android", R.drawable.ic_launcher_background)
        langaugeList.add(lang9)

        val lang10 = Langauge("Android", R.drawable.ic_launcher_background)
        langaugeList.add(lang10)

        val lang11 = Langauge("Android", R.drawable.ic_launcher_background)
        langaugeList.add(lang11)

        val lang12 = Langauge("Android", R.drawable.ic_launcher_background)
        langaugeList.add(lang12)

        val lang13 = Langauge("Android", R.drawable.ic_launcher_background)
        langaugeList.add(lang13)

        val lang14 = Langauge("Android", R.drawable.ic_launcher_background)
        langaugeList.add(lang14)
    }
}