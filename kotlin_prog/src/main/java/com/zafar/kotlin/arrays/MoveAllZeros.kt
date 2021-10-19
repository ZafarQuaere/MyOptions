package com.zafar.kotlin.arrays

fun main(arg: Array<String>) {
    val intArray = intArrayOf(0,1,0,3,12,0) // input
    // output should be {1,3,12,0,0,0}
    moveAllZeros(intArray)
//    moveAllZeros1(intArray)
}

fun moveAllZeros1(arr: IntArray) {
    println("Before moving Array: ${arr.contentToString()}")
    val newArr = IntArray(arr.size)
    var count = 0
    for (i in arr.indices) {
        if (arr[i] != 0) {
            newArr[count] = arr[i]
            count++
        }
    }
    for (i in count downTo 1) {
        newArr[count] = 0
    }
    println("After moving Array: ${newArr.contentToString()}")
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
