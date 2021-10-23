package com.zafar.kotlin.arrays

fun main(arg: Array<String>) {
    val intArray = intArrayOf(0,1,0,3,12,0) // input
    // output should be {1,3,12,0,0,0}
    moveAllZeros(intArray)
    println()
    moveAllZeros1(intArray)
}

fun moveAllZeros1(arr: IntArray) {
    println("While Before moving Array: ${arr.contentToString()}")
    var reader = 0
    var writer = 0
    while (reader < arr.size) {
        if (arr[reader] != 0){
            arr[writer] = arr[reader]
            writer++
        }
        reader++
    }

    while (writer < arr.size) {
        arr[writer] = 0
        writer++
    }
    println("While After moving Array: ${arr.contentToString()}")
}

fun moveAllZeros(intArray: IntArray) {
    println("Before moving Array: ${intArray.contentToString()}")
    var count = 0
    val newArray = IntArray(intArray.size)
    for (i in intArray.indices) {
        if (intArray[i] != 0){
            newArray[count] = intArray[i]
            count++
        }
    }

    for (i in count downTo 1){
        newArray[count] = 0
    }
    println("After moving Array: ${newArray.contentToString()}")
}
