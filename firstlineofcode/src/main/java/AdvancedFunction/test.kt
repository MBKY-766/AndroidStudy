package AdvancedFunction

fun main() {

    val list = listOf("Apple", "Banana", "Orange", "Pear", "Grape")
    val result = StringBuilder().build {
        append("Start eating fruits.\n")
        for (fruit in list) {
            append(fruit).append("\n")
        }
        append("Ate all fruits.")
    }
    println(result.toString())
    /*val num1 = 10
    val num2 = 30
    val result = num1AndNum2(num1, num2) { n1, n2 ->
        n1 + n2
    }
    println(result)*/
}

inline fun num1AndNum2(num1: Int, num2: Int, operation: (Int, Int) -> Int): Int {
    val result = operation(num1, num2)
    return result
}

fun StringBuilder.build(block: StringBuilder.() -> Unit): StringBuilder {
    block()
    return this
}