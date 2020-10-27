package com.example.passingclassasparameter

import android.app.Activity

class CSingleton {

    companion object{
        val instance: CSingleton by lazy { CSingleton() }
    }

    private lateinit var className: Class<out Activity>
    fun setActivityName(className: Class<out Activity>){
        this.className = className
    }

    fun getActivityname(): Class<out Activity>{
        return className
    }

}