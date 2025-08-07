package com.example.firstlineofcode

interface Study {
    fun readBooks()

    //接口的默认实现
    fun doHomeWork(){
        println("doHomeWork default implementation.")
    }
}