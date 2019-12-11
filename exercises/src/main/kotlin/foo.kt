fun main() {
    val controller = Controller()
    if(controller.commands.containsKey("add")) {
        println("yes")
    }

}



class Controller() {
    val commands:Map<String, (String)->Unit> = mapOf(
        "add" to ::add,
        "remove" to ::remove,
        "greet" to ::greet
    )
    private fun add(input: String) {
        println("add")
    }
    private fun remove(input: String) {
        println("remove")
    }

    private fun greet(input: String) {

    }
}


