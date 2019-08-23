package com.example.bottomnavmenu

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.view.*

/**
 * Created by ansh on 21/02/18.
 */
class FragmentHome : Fragment() {

    private lateinit var viewP: View

    /**
     * Initialize newInstance for passing parameters
     */
    companion object {
        fun newInstance(): FragmentHome {
            val fragmentHome = FragmentHome()
            val args = Bundle()
            fragmentHome.arguments = args
            return fragmentHome
        }

    }

    @SuppressLint("WrongConstant")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewP = inflater!!.inflate(R.layout.fragment_home, container, false)

        viewP.recyclerView.layoutManager = LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.VERTICAL, false)
        viewP.recyclerView.adapter = RecylerAdapter(getModels()) {
            Toast.makeText(activity?.applicationContext,getModels().get(it).capitalName, Toast.LENGTH_SHORT).show()
        }

        return viewP
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
            CountryModel(R.drawable.ic_launcher_background, "Japonya", "Tokyo"),
            CountryModel(R.drawable.ic_launcher_background, "awfawf", "reheher"),
            CountryModel(R.drawable.ic_launcher_background, "wafawfawfaw", "aqweqqeq"),
            CountryModel(R.drawable.ic_launcher_background, "sreghseg", "hkhukhukh"),
            CountryModel(R.drawable.ic_launcher_background, "aa", "hkhukhukh"),
            CountryModel(R.drawable.ic_launcher_background, "ww", "hkhukhukh"),
            CountryModel(R.drawable.ic_launcher_background, "ggg", "hkhukhukh")
        )
        return models
    }
}