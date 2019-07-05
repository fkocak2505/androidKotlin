package tr.com.fkocak.kotlininitcomponent

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //val meyveler = listOf("Elma", "Armut", "Muz", "Kivi", "Çilek", "Karpuz", "Kavun", "Ananas", "Kiraz", "Dut")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*deneme.setText("Fatih Koçak")
        Toast.makeText(applicationContext,deneme.text,Toast.LENGTH_SHORT).show()

        deneme.setOnClickListener(){
            Toast.makeText(applicationContext,"aaa",Toast.LENGTH_SHORT).show()
        }*/

        /*val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, meyveler)
        listView.adapter = adapter

        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->

            val secilenMeyve = parent.getItemAtPosition(position) as String
            Toast.makeText(applicationContext,secilenMeyve,Toast.LENGTH_LONG).show()

        }*/


        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = RecylerAdapter(getModels()) {
            Toast.makeText(applicationContext,getModels().get(it).capitalName,Toast.LENGTH_SHORT).show()
        }


    }

    fun getModels(): MutableList<CountryModel> {

        val models = mutableListOf(
            CountryModel(R.drawable.ic_launcher_background, "Çin", "Pekin"),
            CountryModel(R.drawable.ic_launcher_background, "Mısır", "Kahire"),
            CountryModel(R.drawable.ic_launcher_background, "Almanya", "Berlin"),
            CountryModel(R.drawable.ic_launcher_background, "Türkiye", "Ankara"),
            CountryModel(R.drawable.ic_launcher_background, "Rusya", "Moskova"),
            CountryModel(R.drawable.ic_launcher_background, "İngiltere", "Londra"),
            CountryModel(R.drawable.ic_launcher_background, "Ukrayna", "Kiev"),
            CountryModel(R.drawable.ic_launcher_background, "Japonya", "Tokyo")
        )
        return models
    }
}
