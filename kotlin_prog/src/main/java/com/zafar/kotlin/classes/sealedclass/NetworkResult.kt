package com.zafar.kotlin.classes.sealedclass

/*
Sealed Classes(https://medium.com/codex/kotlin-sealed-classes-for-better-handling-of-api-response-6aa1fbd23c76)
The concept of Sealed classes is pretty similar to that of enums, sealed classes represent restricted class hierarchies,
i.e, our class can have a specific number of subclasses, that provide more control over the inheritance.
All subclasses of a sealed class are known at compile time. Sealed classes ensure type safety by restricting the types to
be matched at compile-time rather than at runtime.

Unlike the Enum class, the sealed class can have states, as we can have several objects of the same class.
 */
sealed class NetworkResult<T>(
    val data: T? = null,
    val message: String? = null) {

    class Success<T>(data: T) : NetworkResult<T>(data)
    class Error<T>(message: String, data: T? = null) : NetworkResult<T>(data, message)
    class Loading<T> : NetworkResult<T>()
}

fun main() {
    val status = NetworkResult.Success<A>(A("empty"))
}

class A(val name: String) {
    val status: String = "Success";
}