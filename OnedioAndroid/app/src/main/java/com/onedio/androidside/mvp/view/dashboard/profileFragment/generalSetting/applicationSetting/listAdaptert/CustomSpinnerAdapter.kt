package com.onedio.androidside.mvp.view.dashboard.profileFragment.generalSetting.applicationSetting.listAdaptert

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import com.onedio.androidside.R

class CustomSpinnerAdapter(val context: Context, var listItemsTxt: MutableList<String>) : BaseAdapter() {


    val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val vh: ItemRowHolder
        if (convertView == null) {
            view = mInflater.inflate(R.layout.spinner_item, parent, false)
            vh =
                ItemRowHolder(
                    view
                )
            view?.tag = vh
        } else {
            view = convertView
            vh = view.tag as ItemRowHolder
        }


        vh.label.text = listItemsTxt.get(position)
        return view
    }

    override fun getItem(position: Int): Any? {

        return null

    }

    override fun getItemId(position: Int): Long {

        return 0

    }

    override fun getCount(): Int {
        return listItemsTxt.size
    }

    private class ItemRowHolder(row: View?) {

        val label: TextView

        init {
            this.label = row?.findViewById(R.id.spinnerItem) as TextView
            if (AppCompatDelegate.getDefaultNightMode() === AppCompatDelegate.MODE_NIGHT_YES) {
                this.label.setTextColor(Color.parseColor("#e6e6e6"))
            } else {
                this.label.setTextColor(Color.parseColor("#191919"))
            }
        }
    }
}