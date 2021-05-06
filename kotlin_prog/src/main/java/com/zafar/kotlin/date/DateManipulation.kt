package com.zafar.kotlin.date

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

fun main(arg: Array<String>){
    val currentDateTime = LocalDateTime.now()
    displayDay(currentDateTime)
}

fun displayDay(currentDateTime: LocalDateTime) {
    val fullDate = currentDateTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL))
    val split = fullDate.split(",")
    for (i in 0 until split.size)
        println(split[i])

    println(currentDateTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)))
}
