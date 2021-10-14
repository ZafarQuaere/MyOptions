package com.zafar.kotlin.scopefun

fun main() {
    val user = User()
    user.displayInfo()
    performLetOperation()
}


private fun performLetOperation() {
    val name = User().name?.let {
        "The name of the Person is: $it"
    }
    print(name)
}



class User {
    var name: String = "Abcd"
    var contactNumber: String = "1234567890"
    var address: String = "xyz"

    fun displayInfo() = print(
        "\n Name: $name\n " +
                "Contact Number: $contactNumber\n " +
                "Address: $address"
    )
}