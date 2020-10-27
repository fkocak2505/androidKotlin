package com.example.listviewwithscrollview.model

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.listviewwithscrollview.R

class HolderEntriesText(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var textEntriesTitle: TextView? =
        itemView.findViewById(R.id.textEntriesTitle) as TextView
    private var textEntriesContent: TextView? =
        itemView.findViewById(R.id.textEntriesContent) as TextView

    fun bindItems(
        context: Context,
        item: EntriesModel,
        pos: Int
    ) {
        ////// Data Set Text
        item.data.title?.let {
            if (item.data.title != "") {
                textEntriesTitle?.visibility = View.VISIBLE
                textEntriesTitle?.text = item.data.title
            }
        } ?: run {
            textEntriesTitle?.visibility = View.GONE
        }

        item.data.content?.let {
            if (item.data.content != "") {
                textEntriesContent?.visibility = View.VISIBLE
                textEntriesContent?.text = item.data.content
            }
        } ?: run {
            textEntriesTitle?.visibility = View.GONE
        }

    }

}