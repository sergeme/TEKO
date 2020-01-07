package kiosk

import kotlin.reflect.KFunction1

open class Command(open val name: String, open val description: String, val call: KFunction1<String, Unit>)

class SnackKioskController(private val kiosk: Kiosk) {

    val commands: List<Command> = listOf(
        Command("buy", "[Brand] -> ie. buy Snickers", kiosk::sell),
        Command("restock", "[Amount Brand] -> ie. restock 10 Mars", kiosk::refillStock),
        Command("exit", "", kiosk::exitApplication)
    )

    fun parse(command: String): Boolean {
        if (commands.any { x -> x.name == command.substringBefore(" ") }) {
            kiosk.execute(command.substringBefore(" "), command.substringAfter(" "), commands)
            return true
        } else {
            println("Invalid command, please try something else!")
            return true
        }
    }

    fun getInventory(): List<Inventory> {
        return kiosk.getStock()
    }
}