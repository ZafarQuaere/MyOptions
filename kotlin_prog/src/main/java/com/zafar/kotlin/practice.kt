package com.zafar.kotlin

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//fun main() = runBlocking {
//    val task = GlobalScope.launch {
//        delay(1000L)
//        println("there")
//    }
//    println("Hello,")
////    task.join()
//}

class SpecialFunction : () -> Unit {
    override fun invoke() {
        println("Invoked from an instance.")
    }
}
fun main() {

    val x: IntArray = intArrayOf(1, 2, 3)
//    val arrOf: IntArray = arrayOf(2, 3, 5)
    val arrOf1: Array<Int> = arrayOf(2, 3, 5)



    try { SpecialFunction()() }
    catch (ex: Exception) { println("An error occurred") }
}