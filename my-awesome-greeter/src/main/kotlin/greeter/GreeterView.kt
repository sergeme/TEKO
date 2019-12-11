package greeter

import java.util.*
import kotlin.collections.ArrayList

class GreeterView {
    private lateinit var controller: PersonController
    fun setController(controller: PersonController) {
        this.controller = controller
    }
    fun amountOfPersonChanged(persons: List<Person>) {
        println("""
                persons: $persons
                Commands:
                add [name]
                remove [name]
                sort
                clear
                greet
            """.trimIndent())
    }
    fun showInitialMenue() {
        println("""
                Welcome to the best Greeter!
                Commands:
                add [name]
                remove [name]
                sort
                clear
                greet
            """.trimIndent())
        askForCommand()
    }
    fun showGreet(greetings: ArrayList<String>) {
        greetings.forEach { greeting ->
            println(greeting)
        }
    }
    private fun askForCommand() {
        val scanner = Scanner(System.`in`)
        var command:String
        do {
            command = scanner.nextLine()
        } while (controller.parse(command))
    }
}