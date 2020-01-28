package kiosk

import kotlin.reflect.KFunction1
import kotlin.system.exitProcess

class Kiosk(val kioskView: SnackKioskView) {
    private lateinit var repository: ChocolateBarInterface
    fun setRepository(repository: ChocolateBarInterface) {
        this.repository = repository
        this.repository.initialize()
    }

    fun execute(inputCommand: String, inputProperty: String, commands: List<Command>) {
        val command: Command? = commands.find { x -> x.name == inputCommand }
        command?.call?.invoke(inputProperty)
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