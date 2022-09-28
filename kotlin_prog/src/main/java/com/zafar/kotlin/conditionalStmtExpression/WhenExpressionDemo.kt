package com.zafar.kotlin.conditionalStmtExpression
/**
In when statements, the else branch is mandatory in the following conditions:
1) when has a subject of an Boolean, enum, or sealed type, or their nullable counterparts.
2) branches of when don't cover all possible cases for this subject.
3) else is mandatory until the compiler proves that all the possible cases are covered with conditions.
 */
fun main(args: Array<String>) {

    val x = 10 
    val y = 10
//    when1(x)
    when2(x) // here else is mandatory
    when3(x,y)
    whenForEnum()
}

fun when3(x: Int, y: Int) {
    when {
        x > y -> println("x is greater")
        y > x -> println("y is greater")
        else -> println("both are equals")// else is not mandatory
    }
}

fun when2(x: Int) {
   // here else is mandatory, when as an expression needs else block
    val str :String = when(x){
        0,1 -> "The value of x is 0 or 1"
        2 -> "The value of x is 2"
        3,4,5,6,7,8,9,10 -> "The value of x is 3, 4,5,6,7,8,9"
        in 10..15 -> "The value of x is between 10 to 15"
        else ->
            "value is something else"

    }
    println("The string value is $str")
}

fun when1(x: Int) {
    when (x){
        0,1 -> println("The value of x is 0 or 1")
        2 -> println("The value of x is 2")
        3,4,5,6,7,8,9,10 -> println("The value of x is 3, 4,5,6,7,8,9")
        in 10..15 -> println("The value of x is between 10 to 15")
//        else -> {
//            println("value is something else")
//            println("value is something else")
//            println("value is something else")
//        }
    }

}
fun whenForEnum() {
 val numericVal = when(Bit.ZERO){
     Bit.ONE -> ""
     Bit.ZERO -> ""
     //here else is not required as all cases is covered.
 }
}

enum class Bit {
    ZERO, ONE
}