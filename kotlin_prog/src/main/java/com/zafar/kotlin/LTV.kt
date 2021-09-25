package com.zafar.kotlin
/*
Given positive integer n implement a function which returns a list numbers from 1 to 100.
However for multiples of three list should contain word Fizz instead of the number and for
the multiples of five list should contain word Buzz. For numbers which are multiples of both three and five
list should contain FizzBuzz word.

Example 1
 kotlin fizzBuzz(5) // [1, 2, "Fizz", 4, "Buzz"]
Example 2
 fizzBuzz(16) // [1, 2, "Fizz", 4, "Buzz", 5, "Fizz", 7, 8, "Fizz", "Buzz", 11, "Fizz", 13, 14, "FizzBuzz"
 */
    fun main(arg: Array<String>){
    val fizzBuzzExample = fizzBuzzExample(21)
    println(fizzBuzzExample)
}

fun fizzBuzzExample(n: Int): MutableList<Any> {
    var list = mutableListOf<Any>()
    for (i in 1..n){
       if (i%3 == 0 && i%5 == 0){
           list.add("FizzBuzz")
       } else if (i%3 == 0) {
           list.add("Fizz")
       } else if(i%5 == 0){
           list.add("Buzz")
       } else {
           list.add(i)
       }
    }
    return list
}
