package tr.com.android.customlistviewwithswitch

import android.os.Bundle
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.general_setting_list_view_item.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var list_of_items = arrayOf("Item 1", "Item 2", "Item 3").toMutableList()

        var listOfGeneralSetting: MutableList<GeneralSettingModel> = mutableListOf()
        listOfGeneralSetting.add(GeneralSettingModel("Profil Ayarları"))

        listOfGeneralSetting.add(GeneralSettingModel("Çıkış Yap"))

        generalSettingListView.adapter = GeneralSettingListAdapter(
            applicationContext,
            list_of_items,
            listOfGeneralSetting

        )

        generalSettingListView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->

            var aa = parent.getItemAtPosition(position) as GeneralSettingModel

            Toast.makeText(
                applicationContext,
                list_of_items[view.spinnerAndroid.selectedItemPosition],
                Toast.LENGTH_SHORT
            ).show()

            //Toast.makeText(applicationContext, aa.title, Toast.LENGTH_SHORT).show()
        }

    }

    fun a() {
        Toast.makeText(applicationContext, "awfawf", Toast.LENGTH_SHORT).show()
    }
}
