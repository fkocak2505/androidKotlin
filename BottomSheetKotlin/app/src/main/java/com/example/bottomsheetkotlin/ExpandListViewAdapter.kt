package com.example.bottomsheetkotlin

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView

import java.util.HashMap

class ExpandListViewAdapter(
    var context: Context,
    var list_parent: List<String>,
    var list_child: HashMap<String, List<String>>
) : BaseExpandableListAdapter() {
    private lateinit var txt: TextView
    private lateinit var txt_child: TextView
    private lateinit var inflater: LayoutInflater
    override fun getGroupCount(): Int {

        return list_parent.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {

        return list_child[list_parent[groupPosition]]!!.size
    }

    override fun getGroup(groupPosition: Int): Any {

        return list_parent[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {

        return list_child[list_parent[groupPosition]]!![childPosition]

    }

    override fun getGroupId(groupPosition: Int): Long {

        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {

        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {

        return false
    }

    override fun getGroupView(
        groupPosition: Int, isExpanded: Boolean,
        view: View?, parent: ViewGroup
    ): View {
        var view = view
        val title_name = getGroup(groupPosition) as String

        if (view == null) {
            inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.listview_header, null)
        }

        txt = view!!.findViewById(R.id.txt_parent) as TextView
        txt.text = title_name

        return view
    }

    override fun getChildView(
        groupPosition: Int, childPosition: Int,
        isLastChild: Boolean, view: View?, parent: ViewGroup
    ): View {
        var view = view
        // kaçıncı pozisyonda ise başlığımızın elemanı onun ismini alarak string e atıyoruz
        val txt_child_name = getChild(groupPosition, childPosition) as String
        if (view == null) {
            inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.listview_child, null)
            // fonksiyon adından da anlaşılacağı gibi parent a bağlı elemanlarının layoutunu inflate ediyoruz böylece o görüntüye ulaşmış oluyoruz
        }
        if (getGroup(groupPosition).toString() == "GALATASARAY") {
            // Eğer başlığımızın ismi GALATASARAY ise elemanlarının yer aldığı arka plan rengini kırmızı yapıyoruz
            view!!.setBackgroundColor(Color.RED)
        } else if (getGroup(groupPosition).toString() == "FENERBAHCE") {
            // Eğer başlığımızın ismi FENERBAHCE ise elemanlarının yer aldığı arka plan rengini mavi yapıyoruz
            view!!.setBackgroundColor(Color.BLUE)
        }
        // listview_child ulaştığımıza göre içindeki bileşeni de kullanabiliyoruz daha sonradan view olarak return ediyoruz
        txt_child = view!!.findViewById(R.id.txt_items) as TextView
        txt_child.text = txt_child_name
        return view
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {

        return true  // expandablelistview de tıklama yapabilmek için true olmak zorunda
    }


}