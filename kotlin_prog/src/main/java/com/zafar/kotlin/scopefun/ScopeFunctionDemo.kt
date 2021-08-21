package com.zafar.kotlin.scopefun

class Person{
    var name: String = "Zafar"
    var age:Int = 27
}

fun main(){
    withFunctionDemo() //with() scope fun
    applyFunctionDemo()
    checkAlsoFunction()

}

fun checkAlsoFunction() {
    val numbers : MutableList<Int> = mutableListOf(1,2,3)

    //operation on number list
   val duplicateNumber =  numbers.also {
        println("The elements are $it")
        it.add(4)
        println("The elements after adding an element $it")
        it.remove(2)
        println("The elements after removing an element $it")
    }
    println("original number $numbers")
    println("duplicate number $duplicateNumber")
}

fun applyFunctionDemo() {
    val person= Person().apply{
        name = "Sani"
        age = 25
    }
}

fun withFunctionDemo() {
    val person = Person()
    println(person.name)
    println(person.age)
    val ageAfter5:Int =  with(person){
        name = "Imam"
        age = 26
        println(name)
        println(age)
        //with scope fun return value see below
        age+5
    }
    println("returned value is : $ageAfter5")
}
