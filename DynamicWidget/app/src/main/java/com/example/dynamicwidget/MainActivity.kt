package com.example.dynamicwidget

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dynamicwidget.adapter.RecylerAdapter
import com.example.dynamicwidget.layoutPlace.ComponentMPValue
import com.example.dynamicwidget.layoutPlace.ComponentMarginsAndPaddings
import com.example.dynamicwidget.layoutPlace.ConstLayoutPlace
import com.example.dynamicwidget.layoutPlace.PlaceObj
import com.example.dynamicwidget.model.ConfigModel
import com.example.dynamicwidget.model.listModel.CountryModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), IMainActivity {

    private var xmlConfigMap: HashMap<String, Int> = HashMap<String, Int>()

    private var hugeCardRecyclerViewIds: MutableList<Int> = mutableListOf()

    private lateinit var view: View

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val json = "{\n" +
                "  \"widgets\": [\n" +
                "    {\n" +
                "      \"id\":\"1\",\n" +
                "      \"wName\": \"widget-mobile-1\",\n" +
                "      \"order\": 1,\n" +
                "      \"type\": \"headlines\",\n" +
                "      \"articleCount\": 8\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\":\"2\",\n" +
                "      \"wName\": \"widget-mobile-2\",\n" +
                "      \"order\": 2,\n" +
                "      \"type\": \"feed\",\n" +
                "      \"sort\": \"recent\",\n" +
                "      \"articleCount\": 3\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\":\"3\",\n" +
                "      \"wName\": \"widget-mobile-3\",\n" +
                "      \"order\": 3,\n" +
                "      \"type\": \"category\",\n" +
                "      \"targetId\": \"8197398710318\",\n" +
                "      \"sort\": \"popular\",\n" +
                "      \"articleCount\": 5,\n" +
                "      \"title\": \"Testler\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\":\"4\",\n" +
                "      \"wName\": \"widget-mobile-4\",\n" +
                "      \"order\": 4,\n" +
                "      \"type\": \"tag\",\n" +
                "      \"targetId\": \"8197398710318\",\n" +
                "      \"sort\": \"popular\",\n" +
                "      \"articleCount\": 3,\n" +
                "      \"title\": \"Roket İçerikler\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\":\"5\",\n" +
                "      \"wName\": \"widget-mobile-5\",\n" +
                "      \"order\": 5,\n" +
                "      \"type\": \"tag\",\n" +
                "      \"targetId\": \"8197398710318\",\n" +
                "      \"sort\": \"recent\",\n" +
                "      \"articleCount\": 8,\n" +
                "      \"title\": \"Editörün Seçimi\"\n" +
                "    }\n" +
                "  ]\n" +
                "}"


        //// Map Of Widget Layouts Part..!
        xmlConfigMap["widget-mobile-1$1"] = R.layout.layout
        xmlConfigMap["widget-mobile-2$2"] = R.layout.layout2
        xmlConfigMap["widget-mobile-3$3"] = R.layout.layout3
        xmlConfigMap["widget-mobile-4$4"] = R.layout.layout4
        xmlConfigMap["widget-mobile-5$5"] = R.layout.layout5
        //xmlConfigMap["widget-mobile-2$6"] = R.layout.layout2


        //view = LayoutInflater.from(this).inflate(R.layout.layout2, findViewById(R.id.linearLayout))
        //inflateHugeCardComponent()


        val configModel = Gson().fromJson(json, ConfigModel::class.java)

        includeXMLsByOrder(configModel)

        fillRecyclerViewData(hugeCardRecyclerViewIds[0],1)

    }

    override fun includeXMLsByOrder(jsonObj: ConfigModel) {

        val widgets = jsonObj.widgets
        val sortedWidgetList = widgets?.sortedWith(compareBy { it.order })

        for (itemOfConfigData in sortedWidgetList!!) {
            if (itemOfConfigData.wName == "widget-mobile-2") {
                hugeCardRecyclerViewIds.add((0 until 10).random())
                inflateHugeCardComponent(
                    hugeCardRecyclerViewIds[hugeCardRecyclerViewIds.size - 1],
                    R.id.hugeCardConstraint,
                    R.layout.layout2,
                    (0 until 2).random()
                )
            } else {
                val xmlPart = xmlConfigMap[itemOfConfigData.wName + "$" + itemOfConfigData.id]
                View.inflate(applicationContext!!, xmlPart!!, findViewById(R.id.linearLayout))
            }
        }
    }

    override fun inflateHugeCardComponent(recyclerViewId: Int, constraintId: Int, layout: Int, sizeOfHugeCardListItems: Int) {

        //view = LayoutInflater.from(this).inflate(layout, findViewById(R.id.linearLayout))
        val inflater = this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        view = inflater.inflate(layout, null)

        val constLayout = view.findViewById<ConstraintLayout>(constraintId)

        val recyclerView = RecyclerView(applicationContext)
        val line = View(applicationContext)

        FillHugeCardWidget(applicationContext, constLayout, recyclerView)
            .setComponentProp(
                ComponentMPValue(
                    recyclerViewId,
                    ConstraintLayout.LayoutParams.MATCH_PARENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT,
                    ComponentMarginsAndPaddings(
                        0,
                        10,
                        0,
                        20
                    ),
                    null
                )
            )
            .setComponentMarginValue(
                ConstLayoutPlace(
                    PlaceObj(ConstraintSet.START, constLayout.id, ConstraintSet.START),
                    PlaceObj(ConstraintSet.TOP, constLayout.id, ConstraintSet.TOP),
                    PlaceObj(ConstraintSet.END, constLayout.id, ConstraintSet.END),
                    PlaceObj(ConstraintSet.BOTTOM, constLayout.id, ConstraintSet.BOTTOM)
                )
            )

        //fillRecyclerViewData(recyclerView, sizeOfHugeCardListItems)

        FillHugeCardWidget(applicationContext, constLayout, line)
            .setComponentProp(
                ComponentMPValue(
                    2,
                    ConstraintLayout.LayoutParams.MATCH_PARENT,
                    10,
                    ComponentMarginsAndPaddings(
                        0,
                        10,
                        0,
                        0
                    ),
                    null
                )
            )
            .setViewSpecialProp("#e2e2e2")
            .setComponentMarginValue(
                ConstLayoutPlace(
                    PlaceObj(ConstraintSet.START, recyclerView.id, ConstraintSet.START),
                    PlaceObj(ConstraintSet.TOP, recyclerView.id, ConstraintSet.BOTTOM),
                    PlaceObj(ConstraintSet.END, recyclerView.id, ConstraintSet.END),
                    null
                )
            )

        linearLayout.addView(view)
    }


    fun getModels(): MutableList<CountryModel> {

        val models = mutableListOf(
            CountryModel(R.drawable.ic_launcher_background, "Çin", "Pekin"),
            CountryModel(R.drawable.ic_launcher_background, "Mısır", "Kahire"),
            CountryModel(R.drawable.ic_launcher_background, "Almanya", "Berlin")
        )
        return models
    }

    fun getModels1(): MutableList<CountryModel> {

        val models = mutableListOf(
            CountryModel(R.drawable.ic_launcher_background, "Çin", "Pekin"),
            CountryModel(R.drawable.ic_launcher_background, "Mısır", "Kahire"),
            CountryModel(R.drawable.ic_launcher_background, "Almanya", "Berlin"),
            CountryModel(R.drawable.ic_launcher_background, "Almanya", "Berlin"),
            CountryModel(R.drawable.ic_launcher_background, "Almanya", "Berlin"),
            CountryModel(R.drawable.ic_launcher_background, "Almanya", "Berlin")
        )
        return models
    }



    fun fillRecyclerViewData(recyclerView: Int, sizeOfHugeCardListItems: Int) {
        var recyclerViewww: RecyclerView = findViewById(recyclerView)

        recyclerViewww.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)

        when(sizeOfHugeCardListItems){
            0 -> recyclerViewww.adapter = RecylerAdapter(getModels()) {
                Toast.makeText(applicationContext, getModels()[it].capitalName, Toast.LENGTH_SHORT)
                    .show()
            }
            1 -> recyclerViewww.adapter = RecylerAdapter(getModels1()) {
                Toast.makeText(applicationContext, getModels1()[it].capitalName, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

}
