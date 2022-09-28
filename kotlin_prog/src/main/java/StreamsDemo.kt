


fun printStudents(vararg students: String) {
    for(student in students) println(student)
}

fun main(){
    val students/*: Array<String>*/ = arrayOf("abc","def","ghi","jkl","mno")
    printStudents(*students)
    val y = arrayOf(10, 20, 30, 40)
    val z = listOf(10, 20, 30, 40)

    y[3] = 60

    println(y.toString())
    println(z.toString())
//    for(z in 1..7) println("$z ")
//    for(z in 1..6) print("$z ")
//    println()
//    for(z in 1 until 6) print("$z ")


}