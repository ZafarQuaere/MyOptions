package com.zafar.kotlin.gfg

fun main(){
    val array = intArrayOf(1,2,3,4,5,6)
    printAlternate(array)
}

fun printAlternate(array: IntArray) {
    var i = 1;
    while (i < array.size){
        println(array[i-1])
        i++
    }
}
