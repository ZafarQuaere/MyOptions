package com.zafar.kotlin.inheritance

open class Animal {
    open var name: String = "Dog"
    open var speed = "40 km/hr"

}
// derived class
class Tiger: Animal() {
    override var name = "Tiger"
    override var speed = "100 km/hr"
}
fun main(args: Array<String>) {
    val t = Tiger()
    val t1 = Animal()
    println(t.name+" can run at speed "+t.speed)
    println(t1.name+" can run at speed "+t1.speed)
}