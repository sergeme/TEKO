fun main(args: Array<String>) {
    val hunde = arrayOf(
        Dog("Fifi", 22, "Terrier"),
        Dog("Bella", 20, "Terrier"),
        Dog("Lenny", 10, "Terrier")
    )
    println("Meine Hunde:")
    for (hund in hunde) {
        hund.whoAmI()
        hund.bark()

    }
}
class Dog(
    private val name:String,
    private var weight:Int,
    private val breed:String) {
    fun whoAmI() {
        println("$name ist $weight kg schwer und ein $breed")
    }
    fun bark() = (if (weight < 20) println("${hund.name} mach Yip!!") else println("${hund.name} mach Woof!!"))
}