package com.zafar.kotlin.others

//Quest Global
fun main() {
    val mVal = 4115114
    checkIfPalindrome(mVal)
    val name = "Zafar Imam"
    findHighestOccurrence(name)
    val n = 5
    printPattern(n)
}

/**
 *
1
2 2
3 3 3
4 4 4 4
5 5 5 5 5
 */
fun printPattern(n: Int) {
    for (i in 0 .. n){
        for (j in 0 until i){
            print("$i ")
        }
        println()
    }
}


fun findHighestOccurrence(name: String) {
    val nameArr = name.toCharArray()
    val map = HashMap<Char,Int>()
    var count  = 1
    for (i in nameArr.indices) {
        if (map.containsKey(nameArr[i])) {
            count = map.getValue(nameArr[i]) + 1
            map[nameArr[i]] = count
        } else
            map[nameArr[i]] = 1
    }
    var maxCount = 0
    var maxChar = nameArr[0]
    for ((key, value) in map.entries) {
       if (value > maxCount){
           maxCount = value
           maxChar = key
       }
    }
    println("Max Occurrence of Char in $name is $maxCount and Character is $maxChar")
}

fun checkIfPalindrome(mVal: Int) {
    val reverse = reverseNumber(mVal)
    println("Value: $mVal  Reverse: $reverse")
    if (reverse == mVal)
        println("Palindrome")
    else
        println("Not Palindrome")
}

fun reverseNumber(mVal: Int): Int {
    var reverse = 0  //123
    var n = mVal
    while (n != 0){
        reverse *= 10
        reverse += n % 10
        n /= 10
    }

    return reverse
}
