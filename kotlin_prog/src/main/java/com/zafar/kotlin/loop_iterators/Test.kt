package com.zafar.kotlin.loop_iterators

fun main(){
    val names = listOf("Zafar", "Imam", "Sani")
    testBreak(names)
}

fun testBreak(names: List<String>) {
    val numbers = listOf(1,2,3,4,5)
    for (name in names) {
        if (name == "Zafar") {
            for (number in numbers) {
                println("Inner Number: $number")
            }
        }
        println("Outer for loop: $name")
        break
    }
}
