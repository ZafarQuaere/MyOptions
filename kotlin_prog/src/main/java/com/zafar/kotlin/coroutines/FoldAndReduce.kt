package com.zafar.kotlin.coroutines

fun main(arg: Array<String>) {
    val fold = listOf(1, 2, 3).fold(0) { sum, element -> sum + element }
    println("Fold: $fold")
    println()
    val reduce = listOf(1, 2, 3).reduce { sum, element -> sum + element }
    println("reduce: $reduce")
//fold
    //Accumulates value starting with [initial] value and applying [operation] from left to right to current accumulator value and each element.
//reduce
    //Accumulates value starting with the first element and applying [operation] from left to right to current accumulator value and each element.

}