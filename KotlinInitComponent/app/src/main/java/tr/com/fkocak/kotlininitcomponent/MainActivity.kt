package tr.com.fkocak.kotlininitcomponent

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {

    private lateinit var model: MutableList<CountryModel>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        /*val itemDecorator = DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
        itemDecorator.setDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.recycler_view_divider)!!)

        recyclerView.addItemDecoration(itemDecorator)*/

        recyclerView.adapter = RecylerAdapter(getModels()) {
            Toast.makeText(applicationContext, getModels()[it].capitalName,Toast.LENGTH_SHORT).show()
        }

        button.setOnClickListener{
            model.add(CountryModel(R.drawable.ic_launcher_background, "Çin", "Pekin"))
            model.add(CountryModel(R.drawable.ic_launcher_background, "Mısır", "Kahire"))
            model.add(CountryModel(R.drawable.ic_launcher_background, "Almanya", "Berlin"))

            recyclerView.adapter = RecylerAdapter(model) {
                Toast.makeText(applicationContext, model[it].capitalName,Toast.LENGTH_SHORT).show()
            }

        }


    }

    fun getModels(): MutableList<CountryModel> {

        model = mutableListOf(
            CountryModel(R.drawable.ic_launcher_background, "Çin", "Pekin"),
            CountryModel(R.drawable.ic_launcher_background, "Mısır", "Kahire"),
            CountryModel(R.drawable.ic_launcher_background, "Almanya", "Berlin")
        )
        return model
    }
}
