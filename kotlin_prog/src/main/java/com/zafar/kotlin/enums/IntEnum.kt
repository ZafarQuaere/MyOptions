package com.zafar.kotlin.enums

enum class PlaybackQualityUIOptions(val intValue: Int) {
    LW(1),
    LP(2),
    SDHD(3)
}

fun main() {
    println(1 == PlaybackQualityUIOptions.LW.intValue)
}