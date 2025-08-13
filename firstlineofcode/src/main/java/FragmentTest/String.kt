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
    val leng = "6666abc...".count()
    println(leng)
}