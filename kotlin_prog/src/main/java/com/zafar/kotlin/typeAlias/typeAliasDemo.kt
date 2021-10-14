package com.zafar.kotlin.typeAlias

// https://blog.mindorks.com/type-aliases-in-kotlin

fun main(args: Array<String>) {
    var line :Any = ""
    var name: String? = null!!

    
}

fun fetchUser():Triple<String, String, Int>{
    return Triple("Himanshu","Singh",26)
}
//Here, we will make a typeAlias for the Triple like,
typealias User = Triple<String, String, Int>
fun fetcchUser(): User {
    return Triple("Himanshu", "Singh", 26)
}
