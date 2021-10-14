package com.example.kotlin.collections

/* Difference b/w fold and reduce.
fold takes an initial value, and the first invocation of the lambda you pass to it will receive that initial value and the
first element of the collection as parameters.

listOf(1, 2, 3).fold(0) { sum, element -> sum + element }
The first call to the lambda will be with parameters 0 and 1.

Having the ability to pass in an initial value is useful if you have to provide some sort of default value or parameter for
your operation.

reduce doesn't take an initial value, but instead starts with the first element of the collection as the accumulator (called sum in the
following example)

listOf(1, 2, 3).reduce { sum, element -> sum + element }
The first call to the lambda here will be with parameters 1 and 2.
 */
fun main() {
    val numList = 1..5;
    val sumList = numList.reduce{x,y -> x+y} // reduce does not take initial value, first call of lambda will be 1 and 2
    println("SumList Reduce : $sumList")

    val sumList2 = numList.fold(5){x,y -> x+y} // fold take initial value, first call of lambda will be 5 and 1,
    println("SumList Folde : $sumList2")

    val testString: String by lazy { "Zafar" }

    val test: String by lazy { "some value" }
    val isBool : Boolean by lazy { false }
    val pp: String? = null
    // elvis operator
    val x = pp?.length?:0
}