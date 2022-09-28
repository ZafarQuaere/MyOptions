package com.zafar.kotlin.enums

enum class Signal { OPEN,CLOSED,SENDING}

fun main(){
    println("Position : ${Signal.CLOSED.ordinal}")
}