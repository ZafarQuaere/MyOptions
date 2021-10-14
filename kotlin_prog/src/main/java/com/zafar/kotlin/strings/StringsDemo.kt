package strings

fun main(args: Array<String>) {

    var str : String = " Hey! Lets Start Kotlin. "
    var str1 : String = " It is the best Language for mobilE app development "

//    var stringManipulation : String = str + str1
//    println(stringManipulation)
//
//    var phoneNO: String = "(+91)7834908329"
//    println("Before : $phoneNO   After ${formatPhone(phoneNO)}")

    val newStr = removeOccurence_e(str1,'e')
    val newStr1 = removeOccurenceByReplace(str1,'e')
    println("Old String: $str1 \nNew String: $newStr \nby replace: $newStr1")
 /*   val token : List<String> = str.split(" ")
    for (string in token){
        println(string)
    }*/

}

fun removeOccurenceByReplace(str1: String, c: Char): String {
//    return str1.replace("e","")
    return str1.filterNot { it == "e"[0] || it == "E"[0] }
}

fun removeOccurence_e(str: String, c: Char) :String{
    val charArray = str.toCharArray()
    val sb = StringBuilder()
    for (char in charArray) {
        if (char == c || char == 'E')
            continue
        sb.append(char)
    }
    return sb.toString()
}

fun formatPhone(phoneNO: String): String {
    var formatted = phoneNO.replace("[^0-9]".toRegex(),"")
    println(formatted)
    return formatted
}
