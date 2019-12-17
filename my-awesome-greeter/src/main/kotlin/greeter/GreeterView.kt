package greeter

import java.util.*
import kotlin.collections.ArrayList

class GreeterView {
    private lateinit var controller: PersonController
    private lateinit var greeter: Greeter
    fun setController(controller: PersonController) {
        this.controller = controller
    }

    fun setGreeter(greeter: Greeter) {
        this.greeter = greeter
    }
    fun amountOfPersonChanged(persons: List<Person>) {
        println("""
                
                persons: $persons
                Commands:
            """.trimIndent())
        listCommands(greeter)
    }
    fun showInitialMenue() {
        println("""
                Welcome to the best Greeter!
                Commands:
            """.trimIndent())
        listCommands(greeter)
        askForCommand()
    }

    fun listCommands(greeter: Greeter) {
        for(command in greeter.commands) {
            print(command.name)
            if(command.description != "") {
                print(" - ${command.description}")
            }
            print("\n")
        }
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