package strings


fun main(arg: Array<String>){
    //reverseString();
    //removeSpace();
    removeDuplicate()
    findSubString();
}

private fun findSubString() {
    val str1 = "2.5 MB"
    val str2 = "500.65 KB"
    println("Substring of str1 ${str1.substring(0,str1.length-3)}")
    println("Substring of str1 ${str2.substring(0,str2.length-3)}")
}

private fun removeDuplicate() {
    val string: String = "Hello World Hi Hi"
    val lhs = LinkedHashSet<Char>()
    val builder = StringBuilder();
    for (value in string.toCharArray()){
        lhs.add(value)
    }
    for (value in lhs)
        builder.append(value)

    println("After removing duplilcate ${builder.toString()} from $string")
}

private fun removeSpace() {
    val string: String = "Hello Bhai"
    val charArray: CharArray = string.toCharArray()
    val builder = StringBuilder()
    for (char in charArray){
        if (char == ' ')
            continue
        builder.append(char)
    }

    println("After removing space ${builder.toString()} from $string")
}

private fun reverseString() {
    val string: String = "Hello Bhai"
    println("Reverse of $string is ${string.reversed()}")
}
