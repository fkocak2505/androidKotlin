package tr.com.android.customlistviewwithswitch

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class GeneralSettingListAdapter(val context: Context,val list_of_items: MutableList<String>, val generalSettintList: MutableList<GeneralSettingModel>) :
    BaseAdapter() {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {

        val layouInflator = LayoutInflater.from(context)
        val inflater = layouInflator
        val rowView = inflater.inflate(R.layout.general_setting_list_view_item, null, true)

        val titleText = rowView.findViewById(R.id.generalListTitle) as TextView
        val switchButton = rowView.findViewById(R.id.switchButton) as Switch
        val spinnerAndroid = rowView.findViewById(R.id.spinnerAndroid) as Spinner

        val aa = ArrayAdapter(context, android.R.layout.simple_spinner_item, list_of_items)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerAndroid!!.setAdapter(aa)

        titleText.text = generalSettintList[position].title


        switchButton.setOnCheckedChangeListener { compoundButton, b ->
            if(b)
                Toast.makeText(context, "açık", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(context, "kapalı", Toast.LENGTH_SHORT).show()
        }


        return rowView
    }

    override fun getItem(p0: Int): Any {
        return generalSettintList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return generalSettintList.size
    }

}