package com.zafar.kotlin.coroutines

import kotlinx.coroutines.*

/*
To cancel a coroutine it should be cooperative
1) Periodically invokes the suspending fncn, that checks for cancellation.
2) Only coroutines suspending fncn will make coroutine cooperative.
such as delay(), yield() or isActive flag etc

To Handle Cancellation exception we have  withContext(), withTimeout() function
 */
fun main(arg: Array<String>){

    println("Main program starts ${Thread.currentThread().name}")

    runBlocking {
        val job: Job = launch {
            for (i in 1..500) {
                print("$i .")
//                Thread.sleep(50) // it will work but will not make this coroutine cooperative
//                delay(10) // it is cancellable method, so make this coroutine cooperative.
                yield()

                // we can use isActive flag also to check the coroutine is active or not and then we can cancel this
//                if (!isActive){
//                    break
//                }
            }
        }
        delay(50)
        job.cancelAndJoin()
        println("Main program ends ${Thread.currentThread().name}")
    }

}

