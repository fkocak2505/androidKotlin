package com.gencdil.audiorecord.common

import com.gencdil.audiorecord.adapter.RecordModel
import com.google.gson.Gson

class RecordCommon {

    companion object{
        //==================================================================================================================
        /**
         * Convert Map To String..
         */
        //==================================================================================================================
        fun convertMap2String(map: Map<String, RecordModel>): String {
            val mapAsString = StringBuilder("")
            for (key in map.keys) {
                val commaSeparator = Gson().toJson(map[key])
                mapAsString.append("$key$$$commaSeparator,$")
            }
            mapAsString.delete(mapAsString.length - 2, mapAsString.length).append("")
            return mapAsString.toString()
        }

        //==================================================================================================================
        /**
         * Convert String To Map..
         */
        //==================================================================================================================
        fun convertString2Map(mapAsString: String): Map<String, RecordModel> {
            return mapAsString.split(",$").associate {
                val (left, right) = it.split("$$")
                left to Gson().fromJson(right, RecordModel::class.java)
            }
        }
    }
}