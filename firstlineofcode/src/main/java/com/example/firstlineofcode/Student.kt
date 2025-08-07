package com.example.firstlineofcode

//extends->:
//主构造函数紧跟函数名，无函数体，可以无参数
//继承的类后面的括号()：调用父类的某个构造函数
class Student(val sno: String, val grade: Int, name: String, age: Int) : Person(name, age) ,Study {

    //主构造函数的逻辑可以写在init结构体中
//    init {
//        println("sno is $sno")
//        println("grade is $grade")
//    }

    override fun toString(): String {
        return "Student(sno='$sno', grade=$grade, name=$name, age='$age')"
    }

    override fun readBooks() {
        println("$name is reading books")
    }

    override fun doHomeWork() {
        println("$name is doing homeWork")
    }

}