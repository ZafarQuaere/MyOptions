package com.zafar.kotlin.leetcode


fun main(args: Array<String>) {
    val nums = intArrayOf(2, 7, 11, 15)
    val target = 9
    val output = twoSum(nums, target)
    println("output is ${listOf(output).toString()}")
}

private fun twoSum(nums: IntArray, target: Int): IntArray {
    val output = intArrayOf()
    val first = nums[0]
    if (nums.size > 1){
        for (i in 1..nums.size){
            if (first+nums[i] == target) {
                
            }
        }
    }
    return output
}