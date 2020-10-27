package com.example.guavaexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.json.JSONObject
import kotlin.collections.HashMap



class MainActivity : AppCompatActivity() {

    private var map: HashMap<String, MutableList<String>> = HashMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var aa: MutableList<String> = mutableListOf()
        aa.add("1")
        aa.add("2")

        val af = aa.find { it == "2" }
        af?.let {
            var gg = 2
        } ?: run{
            var gga = 2
        }

        var bb: MutableList<String> = mutableListOf()
        bb.add("3")
        bb.add("4")

        map["awf"] = aa
        map["awffff"] = bb

        val mapOfStr = convertMap2String(map)

        val mapp = convertString2Map(mapOfStr)

        var a = 2

    }

    private fun convertMap2String(map: Map<String, MutableList<String>>): String {
        val mapAsString = StringBuilder("")
        for (key in map.keys) {
            val commaSeparator = map[key]?.joinToString("-")
            mapAsString.append("$key=$commaSeparator, ")
        }
        mapAsString.delete(mapAsString.length - 2, mapAsString.length).append("")
        return mapAsString.toString()
    }

    private fun convertString2Map(mapAsString: String): Map<String, MutableList<String>> {
        return mapAsString.split(", ").associate {
            val (left, right) = it.split("=")
            left to right.split("-").toMutableList()
        }
    }


}
