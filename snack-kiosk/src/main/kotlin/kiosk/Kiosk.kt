package kiosk

import kotlin.reflect.KFunction1
import kotlin.system.exitProcess

class Kiosk(val kioskView: SnackKioskView, startAmount: Int = 0) {
    private val repository = ChocolateBarRepository()
    private val brands: List<String> = listOf("Snickers", "Mars")
    val commands: List<Command> = listOf(
        Command("buy", "[Brand] -> ie. buy Snickers", this::sell),
        Command("restock", "[Amount Brand] -> ie. restock 10 Mars", this::refillStock),
        Command("exit", "", this::exitApplication)
    )

    fun execute(inputCommand: String, inputProperty: String) {
        val command: Command? = commands.find { x -> x.name == inputCommand }
        command?.call?.invoke(inputProperty)
    }

    init {
        for (brand in brands) {
            fillinStock("$startAmount $brand")
        }
    }

    private fun fillinStock(input: String) {
        val amount: Int = input.substringBefore(" ").toInt()
        val chocolateBarName = input.substringAfter(" ")
        for (i in 1..amount) {
            repository.createChocolateBar(chocolateBarName)
        }
    }

    private fun refillStock(input: String/*chocolateBarName:String, amount: Int*/) {
        if (input.contains(" ")) {
            val amount: Int = input.substringBefore(" ").toInt()
            println(amount)
            val chocolateBarName = input.substringAfter(" ")
            for (i in 1..amount) {
                repository.createChocolateBar(chocolateBarName)
            }
        } else {
            println("Invalid input")
        }
        kioskView.inventoryChanged(getStock())
    }

    private fun sell(input: String /*Inventory*/) {
        kioskView.itemSold(repository.removeChocolateBar(input), input, getStock())
    }

    fun getStock(): List<Inventory> {
        return repository.getChocolateBarInventory()
    }

    private fun exitApplication(input: String) {
        exitProcess(1)
    }

}

open class Command(open val name: String, open val description: String, val call: KFunction1<String, Unit>)