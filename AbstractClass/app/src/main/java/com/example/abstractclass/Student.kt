package com.example.abstractclass

abstract class Student(name: String, age: Int) {

    fun demo(){
        println("Abstract olmayan method çağrıldı..")
    }

    abstract fun absFunc(message: String)

}