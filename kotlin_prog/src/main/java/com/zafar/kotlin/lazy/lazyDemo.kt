package com.example.kotlin.lazy

fun main() {
    val i by lazy {
        println("inside lazy")
        1
    }

    val j by lazy {
        println("I am too lazy to learn ")

        100
    }

    println("Before lazy call i and J")
    println(i)
    println(j)
    println(j)


//    println(aVariable)
//    println(aVariable)
}

val aVariable by lazy {
    println("Random stmt ")
    "Hello"
}