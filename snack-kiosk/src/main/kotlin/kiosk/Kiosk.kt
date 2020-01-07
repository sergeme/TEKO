package kiosk

import kotlin.reflect.KFunction1
import kotlin.system.exitProcess

class Kiosk(val kioskView: SnackKioskView, startAmount: Int = 0) {
    private val repository = ChocolateBarRepository()
    private val brands: List<String> = listOf("Snickers", "Mars")

    fun execute(inputCommand: String, inputProperty: String, commands: List<Command>) {
        val command: Command? = commands.find { x -> x.name == inputCommand }
        command?.call?.invoke(inputProperty)
    }

    init {
        for (brand in brands) {
            fillinStock("$startAmount $brand")
        }
    }

    fun saveToJSON() {

    }

    fun loadFromJSON() {

    }

    fun saveToXML() {

    }

    fun loadFromXML() {

    }

    private fun fillinStock(input: String) {
        val amount: Int = input.substringBefore(" ").toInt()
        val chocolateBarName = input.substringAfter(" ")
        for (i in 1..amount) {
            repository.createChocolateBar(chocolateBarName)
        }
    }

    fun refillStock(input: String/*chocolateBarName:String, amount: Int*/) {
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

    fun sell(input: String /*Inventory*/) {
        kioskView.itemSold(repository.removeChocolateBar(input), input, getStock())
    }

    fun getStock(): List<Inventory> {
        return repository.getChocolateBarInventory()
    }

    fun exitApplication(input: String) {
        exitProcess(1)
    }

}