package com.zafar.kotlin.coroutines

import kotlinx.coroutines.*
/*
When we need to collect the result of a coroutine, then we use withContext  or async
withContext has the same functionality as async followed by await but with less overhead
When we need parallel code execution, then we put them in several async blocks and finally await for all of them
When we need sequential code execution, then we put them in several withContext
With async, we have to catch the exceptions of the code block inside the async body. Otherwise, it can terminate the parent scope
 */
fun main() {
    runBlocking {
        testWithContext()
        /*
        Output of the above will be
        withContext: Before
        withContext: function1
        withContext: function2
        withContext: After
        withContext: function1function2
         */

        testLaunch()
    }

}

suspend fun testLaunch() {
    var resultOne = "Hardstyle"
    var resultTwo = "Minions"
    println("launch Before")
    runBlocking {
        launch(Dispatchers.IO) { resultOne = function3() }
        launch(Dispatchers.IO) { resultTwo = function4() }
    }
    println("launch After")
    val resultText = resultOne + resultTwo
    println("launch $resultText")
}

suspend fun testWithContext() {
    var resultOne = "Hardstyle"
    var resultTwo = "Minions"
    println("withContext Before")
    resultOne = withContext(Dispatchers.IO) { function1() }
    resultTwo = withContext(Dispatchers.IO) { function2() }
    println("withContext After")
    val resultText = resultOne + resultTwo
    println("withContext $resultText")
}

suspend fun function1(): String {
    delay(1000L)
    val message = "function1"
    println("withContext $message")
    return message
}

suspend fun function2(): String {
    delay(100L)
    val message = "function2"
    println("withContext $message")
    return message
}

suspend fun function3(): String {
    delay(1000L)
    val message = "function1"
    println("launch $message")
    return message
}
suspend fun function4(): String {
    delay(100L)
    val message = "function2"
    println("launch $message")
    return message
}
