package com.example.firstlineofcode

//open:可继承
open class Person(val name:String,val age:Int) {

    fun eat(){
        println("$name is eating. She is $age years old.")
    }
}