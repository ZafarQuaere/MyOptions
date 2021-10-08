package com.zafar.kotlin.coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/*fun main(){
    GlobalScope.launch { // launch a new coroutine in background and continue
        delay(1000) // non-blocking delay for 1 second
        println("World")
    }
    println("Hello")// main thread continues while coroutine is delayed
    Thread.sleep(2000)// block main thread for 2 seconds to keep JVM alive

}*/

fun main() {
    GlobalScope.launch { // launch a new coroutine in background and continue
        delay(1000) // non blocking delay for 1 sec
        println("World")

    }
    println("Hello") // main thread continue here immediately
    runBlocking {// but this expression blocks the main thread
        delay(2000) // delay for 2 seconds to keep jvm alive

    }
//    mySuspendFun(2000)
}

suspend fun mySuspendFun(time: Long) {
    delay(time)
}