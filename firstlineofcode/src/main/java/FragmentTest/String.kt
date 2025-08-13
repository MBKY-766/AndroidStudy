package FragmentTest

fun String.count(): Int {
    var count = 0
    for (char in this) {
        if (char.isLetter()) {
            count++
        }
    }
    return count
}
fun main(){
//    val leng = "6666abc...".count()
//    println(leng)
    val str = "abc" * 3
    println(str)
}
operator fun String.times(n: Int): String {
    val builder = StringBuilder()
    repeat(n) {
        builder.append(this)
    }
    return builder.toString()
}