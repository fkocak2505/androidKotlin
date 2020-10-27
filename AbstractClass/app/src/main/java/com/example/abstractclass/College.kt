package com.example.abstractclass

class College(name: String, age: Int) : Student(name, age) {

    override fun absFunc(message: String) {
        println(message)
    }

}