package com.zafar.kotlin.arrays

import java.util.*


fun main(arg: Array<String>){
    val arr = intArrayOf(2, 3, 3, 5, 2, 6, 7)
    //remove 2 from array
    removeElement(arr,0)
}

private fun removeElement(arr: IntArray,index: Int) {
    println("Before removing first 2 ${arr.contentToString()}")
        if (index < 0 || index >= arr.size)
            return

    val removedArray = arr.indices.filter { i: Int -> i != index }.map { i: Int -> arr[i] }.toIntArray()

    // another way to remove element is to convert it into mutable list then remove at index
    val list = arr.toMutableList();
    list.removeAt(index)
    val newArray = list.toIntArray();
    println("After removing first 2 ${removedArray.contentToString()}")
    println("After removing first 2 ${newArray.contentToString()}")

}
