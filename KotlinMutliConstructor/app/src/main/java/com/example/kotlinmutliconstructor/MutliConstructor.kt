package com.example.kotlinmutliconstructor

import android.content.Context

class MutliConstructor() {

    private var context : Context? = null
    var age: Int? = null

    constructor(context: Context, age: Int): this(){
        this.context= context
        this.age = age
    }

    constructor(mAge: Int): this(){
        age = mAge
    }

    fun deneme(): Int{
        return age!!
    }
}