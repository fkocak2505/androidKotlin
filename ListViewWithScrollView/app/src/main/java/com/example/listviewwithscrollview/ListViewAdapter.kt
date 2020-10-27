package com.example.listviewwithscrollview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.listviewwithscrollview.model.EntriesModel
import com.example.listviewwithscrollview.model.HolderEntriesImage
import com.example.listviewwithscrollview.model.HolderEntriesText
import com.example.listviewwithscrollview.model.HolderEntriesVideo

open class ListViewAdapter(
        val context: Context,
        val items: MutableList<EntriesModel>
) : BaseAdapter() {

    private val ENTRIES_TEXT = 0
    private val ENTRIES_IMAGE = 1
    private val ENTRIES_VIDEO = 2


    override fun getItemViewType(position: Int): Int{
        return when (items[position].mode) {
            "text" -> ENTRIES_TEXT
            "image" -> ENTRIES_IMAGE
            "video" -> ENTRIES_VIDEO
            else -> -1
        }
    }

    override fun getViewTypeCount(): Int{
        return ENTRIES_VIDEO + 1
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val type = getItemViewType(position)

        var inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        var vView: View? = null

        when(type){
            ENTRIES_TEXT -> {
                vView =
                        inflater.inflate(R.layout.article_detail_holder_entries_text, parent, false)
                HolderEntriesText(vView).bindItems(context, items[position], position)
            }
            ENTRIES_IMAGE -> {
                vView =
                        inflater.inflate(R.layout.article_detail_holder_entries_image, parent, false)
                HolderEntriesImage(vView).bindItems(context, items[position], position)
            }
            ENTRIES_VIDEO -> {
                vView =
                        inflater.inflate(R.layout.article_detail_holder_entries_video, parent, false)
                HolderEntriesVideo(vView).bindItems(context, items[position], position)
            }
        }

        return vView!!
    }

    override fun getItem(position: Int): Any {
        return ""
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return items.size
    }
}