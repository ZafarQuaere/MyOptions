package com.zafar.kotlin.inheritance

open class Canine{
    open fun speak() = "<generic canine noise>"
}

open class Dog : Canine(){
    override fun speak() = "Dog Speak !!"
}
class Cat : Dog(){

    override fun speak() =
        "Cat: Speak()"

}
fun printSpeak(canine: Canine){
    println(canine.speak())
}

fun main(args: Array<String>) {
    printSpeak(Canine())
    printSpeak(Dog())
    printSpeak(Cat())
    println()
    println()
    val canine: Canine = Cat()
    println(canine.speak())
    val dog: Dog = Cat()
    println(dog.speak())
}