package com.example.staggeredtextgridview

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast


class WordsAdapter(private val context: Context, private val jArray: List<String>) : BaseAdapter() {

    override fun getCount(): Int {
        return jArray.size
    }

    override fun getItem(position: Int): String {
        return jArray[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(context).inflate(R.layout.row_item_word, null) as TextView

        view.text = jArray[position]

        view.setOnClickListener { v ->
            Toast.makeText(
                context,
                (v as TextView).text.toString(),
                Toast.LENGTH_LONG
            ).show()
        }

        return view
    }
}
