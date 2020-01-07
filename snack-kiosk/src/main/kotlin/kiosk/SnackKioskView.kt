package kiosk

import java.util.*

class SnackKioskView {
    private lateinit var controller: SnackKioskController
    fun setController(controller: SnackKioskController) {
        this.controller = controller
    }

    fun listCommands(kiosk: Kiosk) {
        println("\nAvailable Commands:")
        for (command in controller.commands) {
            print(command.name)
            if (command.description != "") {
                print(" - ${command.description}")
            }
            print("\n")
        }
    }

    fun showInventory(stock: List<Inventory>) {
        println("\nMake your choice! Available Chocolatebars:")
        for (item in stock) {
            println("Amount of ${item.name} - ${item.amount}")
        }
        askForCommand()
    }

    private fun askForCommand() {
        val scanner = Scanner(System.`in`)
        var command: String
        do {
            command = scanner.nextLine()
        } while (controller.parse(command))
    }

    fun inventoryChanged(stock: List<Inventory>) {
        for (item in stock) {
            println("Amount of ${item.name} - ${item.amount}")
        }
        askForCommand()
    }

    fun itemSold(removeChocolateBar: Boolean, input: String, stock: List<Inventory>) {
        if (removeChocolateBar) {
            println("$input sold!")
        } else {
            println("$input sold out, make another choice!")
        }
        inventoryChanged(stock)
    }
}