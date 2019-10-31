package com.example.epochdatedifferent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import java.lang.Long
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var firstEpoch = "1566906653700"
        var secondEpoch = "1567508791080"

        val firstExpiry = Date(Long.parseLong(firstEpoch))
        val secondExpiry = Date(Long.parseLong(secondEpoch))

        val dateFormatGmtFirst = SimpleDateFormat("yyyy-MM-dd / HH:mm:ss")
        dateFormatGmtFirst.timeZone = TimeZone.getTimeZone("GMT")
        var firsDate = dateFormatGmtFirst.format(firstExpiry)

        val dateFormatGmtSecond = SimpleDateFormat("yyyy-MM-dd / HH:mm:ss")
        dateFormatGmtSecond.timeZone = TimeZone.getTimeZone("GMT")
        var secondDate = dateFormatGmtSecond.format(secondExpiry)

        //val sdf = SimpleDateFormat("yyyy-MM-dd / HH:mm:ss", Locale.ENGLISH)
        val firstDatee = dateFormatGmtFirst.parse(firsDate)
        val secondDatee = dateFormatGmtFirst.parse(secondDate)

        val diffInMillies = Math.abs(secondDatee!!.time - firstDatee!!.time)
        val diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS)

        var a = 4


        var time = arrayListOf("10","29","32","33","40")

        for(i in 0 until time.size){
            for(j in i+1 until time.size){
                var n = i
                var f = j

                break
            }
        }


    }
}
