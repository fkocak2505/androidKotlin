package com.example.bottomsheetkotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import java.util.*

class BottomSheetFragment : CustomRoundBottomSheet() {

    private lateinit var viewP: View

    private lateinit var list_parent: MutableList<String>
    private lateinit var expand_adapter: ExpandListViewAdapter
    private lateinit var list_child: HashMap<String, List<String>>
    private lateinit var expandlist_view: BottomSheetExpandableListView
    private lateinit var gs_list: MutableList<String>
    private lateinit var fb_list: MutableList<String>

    companion object {
        fun newInstance(): BottomSheetFragment =
            BottomSheetFragment().apply {
                /*
                arguments = Bundle().apply {
                    putInt(ARG_ITEM_COUNT, itemCount)
                }
                 */
            }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewP = inflater.inflate(R.layout.fragment_bottom_sheet_dialog, container, false)

        expandlist_view = viewP.findViewById(R.id.expand_listview) as BottomSheetExpandableListView
        hazırla()

        expand_adapter =
            ExpandListViewAdapter(activity!!.applicationContext, list_parent, list_child)
        expandlist_view.setAdapter(expand_adapter)  // oluşturduğumuz adapter sınıfını set ediyoruz
        expandlist_view.isClickable = true

        expandlist_view.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
            val child_name = expand_adapter.getChild(groupPosition, childPosition) as String
            Toast.makeText(activity!!.applicationContext, "hey$child_name", Toast.LENGTH_LONG)
                .show()

            false
        }


        return viewP
    }

    fun hazırla() {
        list_parent = ArrayList()  // başlıklarımızı listemelek için oluşturduk
        list_child = HashMap() // başlıklara bağlı elemenları tutmak için oluşturduk

        list_parent.add("GALATASARAY")  // ilk başlığı giriyoruz
        list_parent.add("FENERBAHCE")   // ikinci başlığı giriyoruz

        gs_list = ArrayList()  // ilk başlık için alt elemanları tanımlıyoruz
        gs_list.add("Muslera")
        gs_list.add("Sabri")
        gs_list.add("Chejdou")
        gs_list.add("Semih Kaya")
        gs_list.add("Telles")
        gs_list.add("Selçuk İnan")
        gs_list.add("Felipe Melo")
        gs_list.add("Hamit")
        gs_list.add("Weslej Sneijder")
        gs_list.add("Bruma")
        gs_list.add("Burak Yılmaz")
        gs_list.add("Selçuk İnan")
        gs_list.add("Felipe Melo")
        gs_list.add("Hamit")
        gs_list.add("Weslej Sneijder")
        gs_list.add("Bruma")
        gs_list.add("Burak Yılmaz")

        fb_list = ArrayList() // ikinci başlık için alt elemanları tanımlıyoruz
        fb_list.add("Volkan Demirel")
        fb_list.add("Gökhan Gönül")
        fb_list.add("Bekir")
        fb_list.add("Caner Erkin")
        fb_list.add("Mehmet Topal")
        fb_list.add("Emre")
        fb_list.add("Alper Potuk")
        fb_list.add("Mehmet Topuz")
        fb_list.add("Diego")
        fb_list.add("Sow")
        fb_list.add("Emenike")
        fb_list.add("Alper Potuk")
        fb_list.add("Mehmet Topuz")
        fb_list.add("Diego")
        fb_list.add("Sow")
        fb_list.add("Emenike")

        list_child[list_parent[0]] =
            gs_list // ilk başlığımızı ve onların elemanlarını HashMap sınıfında tutuyoruz
        list_child[list_parent[1]] =
            fb_list // ikinci başlığımızı ve onların elemanlarını HashMap sınıfında tutuyoruz


    }
}