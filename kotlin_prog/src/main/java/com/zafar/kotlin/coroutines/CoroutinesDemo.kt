package com.example.kotlin.coroutines

import kotlinx.coroutines.*

fun main(args: Array<String>) = runBlocking {

    val myVal: Int by lazy { 10 }

    GlobalScope.launch {

        println("Thread name : ${Thread.currentThread().name}")

        val job: Job = launch(Dispatchers.Unconfined) {
            delay(1000)
            println("Kotlin !")
            println("Thread name : ${Thread.currentThread().name}")
        }
        print("Hello ")
        job.join()
        println("Job object finish")

        val deferred: Deferred<String> = async (Dispatchers.Default){
            delay(1000)
            "Zafar"
//       5
        }

        val str = deferred.await()
        println("Return of async $str")
    }
    checkAsync()
}

suspend fun checkAsync() {
    val defObj: Deferred<String> = GlobalScope.async {
        print("GlobalScope.async")
        delay(1000)
        "Zafar"
    }
}


/*
runBlocking: This function creates a coroutine and blocks the current Thread until the coroutine
finishes, returning its result value (Unit in this case).

launch: This function creates a new coroutine without blocking the current Thread and
returns Job (ignored here).

delay: This function is a suspending (more on this later) function that delays the current
coroutine without blocking the current thread.

suspend: A suspending function is a function that may suspend the execution of a coroutine,
without blocking the current Thread; therefore a suspending function must be called inside a

coroutine—it can't be invoked from normal code. The function must be marked with the
suspend modifier. So, delay can be invoked inside runBlocking and launch, both functions
(among others) take a suspending lambda as the last parameter—a suspending lambda is a
lambda marked with the suspend modifier.*/
