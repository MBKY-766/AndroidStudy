package com.example.firstlineofcode

fun main() {

//    val student = Student("Tom", 19)
//    val data = SimpleData<Student>(student)
//    handleMyData(data)
//    val studentData = data.get()
}
//范型逆变-MyClass<Person>是MyClass<Student>的子类 in：只能出现在参数位置
//interface Transformer<in T> {
//    fun transform(t: T): String
//}
//范型协变——MyClass<Student>是MyClass<Person>的子类 out：只能出现在返回值位置
//class SimpleData<out T>(val data: T?) {
//    fun get(): T? {
//        return data }
//}
//fun handleMyData(data: SimpleData<Person>) {
//    val personData = data.get()
//}
//利用范性实化简化Intent启动
//inline fun <reified T> startService(context: Context){
//    val intent = Intent(context,T::class.java)
//    context.startService(intent)
//}

////范型实化
//inline fun <reified T> getGenericType() = T::class.java
////infix函数
//infix fun String.beginsWith(prefix: String) = startsWith(prefix)
//利用范性改造build函数 --> 与apply函数功能相同 Unit:无返回值
//fun <T> T.build(block: T.() -> Unit): T {
//    block()
//    return this
//}
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