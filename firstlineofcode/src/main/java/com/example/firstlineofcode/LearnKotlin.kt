package com.example.firstlineofcode

fun main() {
    println("Hello Kotlin!")
    //面向对象编程
    //apply函数-无法指定返回值，返回调用者本身
    /*val result = StringBuilder().apply{
        append("start with eating \n")
        append("eat banana\n")
        append("eat apple")
    }
    println(result.toString())*/
    //run函数-和with相似，只不过不能直接调用，要在，某个对象的基础上调用

    /*val result = StringBuilder().run{
        append("start with eating \n")
        append("eat banana\n")
        append("eat apple")
        toString()
    }
    println(result)*/
    //with函数
    /*val result = with(StringBuilder()){
        append("start with eating \n")
        append("eat banana\n")
        append("eat apple")
        toString()
    }
    println(result)*/

    //let
//    val stu = Student("2222", 2, "tom", 14)
//    doStudy(stu)
//    Thread { println("Thread is running.") }.start()

//    val list = mutableListOf("Apple", "Banana", "Orange", "Pear", "Grape")
    //lambda表达式简化
    /*val maxLengthFruit = list.maxBy { it.length }
    println(maxLengthFruit)*/
    /*
    val list = listOf("Apple", "Banana", "Orange", "Pear", "Grape", "Watermelon")
    val lambda = { fruit: String -> fruit.length }
    val maxLengthFruit = list.maxBy(lambda)
    */
    //map集合
    /*val map = mutableMapOf("Apple" to 1, "Banana" to 2, "Orange" to 3)
    for ((fruit, id) in map) {
        println("名称：$fruit, 序号：$id")
    }*/
    //可变集合
    /*val set = mutableSetOf("Apple", "Banana", "Orange", "Pear", "Grape")
    val list = mutableListOf("Apple", "Banana", "Orange", "Pear", "Grape")
    list.add("watermelon")
    for (fruit in list) {
        println(fruit)
    }*/
    //不可变集合
    /*val list = listOf("Apple", "Banana", "Orange", "Pear", "Grape")
    for(i in list){
        println(i)
    }*/
//    Singleton.singletonTest()
//    val cellphone = Cellphone("iPhone", 8999.0)
//    println(cellphone)
//    val p = Person()
//    p.name = "bgb"
//    p.age = 20
//    p.eat()
//    val s = Student("2022",2,"jack",20);
//    println(s.toString())
//    doStudy(s)
//    println(s.toString())
//    cycle()
    //var val
//    println(getScore("tom"))
//   主动声明变量类型： var a: Int = 10
//    a *= 10
//    print(a)
//    val largerNumber = largerNumber(3, 5)
//    println(largerNumber);

}
//利用范性改造build函数 --> 与apply函数功能相同 Unit:无返回值
fun <T> T.build(block: T.() -> Unit): T {
    block()
    return this
}
/*fun StringBuilder.build(block: StringBuilder.() -> Unit): StringBuilder {
    block()
    return this
}*/

//.?:非空检查  类型？:该变量可为空(null) name:String? name可为空值
/*fun doStudy(study: Study?) {
    //let:是个函数，可将原始调用对象作为参数传递到Lambda表达式中
    study?.let {
        it.readBooks()
        it.doHomeWork()
    }
}*/


//循环语句
//for循环 for-in 与python类似
/*fun cycle() {
    //两端闭区间
    for (i in 0..10) {
        print(i)
    }
    println()

    //左闭右开-until
    for (i in 0 until 10) {
        print(i)
    }
    println()
    //step关键字 阶数  step 2 ：每次递增2
    for (i in 0 until 10 step 2) {
        print(i)
    }
    println()

    //downTo关键字：降序
    for (i in 10 downTo 1) {
        print(i)
    }
    println()
}*/
//while跟Java一样
//when
/*fun getScore(name: String): Int = when (name) {
    "lily" -> 90
    "tom" -> 83
    "jack" -> 93
    else -> 0
}*/
//fun 函数名(参数名：参数类型) ： 返回值
/*fun function(parma:String) : String {
    //函数体

    //返回值
    return "String"

}*/
//if可以有返回值-将{}内最后一行代码作为返回值
//fun largerNumber(param1:Int, param2:Int) = if(param1>param2) param1 else param2