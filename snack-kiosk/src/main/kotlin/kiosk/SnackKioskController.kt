package kiosk

class SnackKioskController(private val kiosk: Kiosk) {
    fun parse(command: String): Boolean {
        if (kiosk.commands.any { x -> x.name == command.substringBefore(" ") }) {
            kiosk.execute(command.substringBefore(" "), command.substringAfter(" "))
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