package greeter

class Person(val name:String) {
    fun greet() :String {
        return "Hello, $name"
    }
    override fun toString(): String {
        return name
    }
}