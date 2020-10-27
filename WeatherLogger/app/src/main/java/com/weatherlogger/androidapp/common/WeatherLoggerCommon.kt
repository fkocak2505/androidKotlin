package com.weatherlogger.androidapp.common

import android.annotation.SuppressLint
import android.content.SharedPreferences
import com.weatherlogger.androidapp.prefs.StringSharedPrefs
import java.text.SimpleDateFormat
import java.util.*

class WeatherLoggerCommon {


    companion object {

        //==============================================================================================
        /**
         * Check if user saved weather Local Storage..
         */
        //==============================================================================================
        fun isSavedLocal(sharedPreferences: SharedPreferences, key: String): Boolean{
            return StringSharedPrefs(
                sharedPreferences,
                key,
                ""
            ).getValue<String>() != ""
        }

        //==============================================================================================
        /**
         * Convert EpochDate to CalendarDate..
         */
        //==============================================================================================
        @SuppressLint("SimpleDateFormat")
        fun getUTCDate2Date(utcDate: String): String {
            val date = utcDate.substring(0, 10)
            val sdf = SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH)
            val d = sdf.parse(date)
            sdf.applyPattern("dd/MM/yyyy")
            return sdf.format(d!!)
        }
    }

}