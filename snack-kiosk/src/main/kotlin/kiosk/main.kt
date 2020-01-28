package kiosk

fun main(args: Array<String>) {
    val kioskView = SnackKioskView()
    val kiosk = Kiosk(kioskView)
    val chocolateBarRepository = ChocolateBarJSONRepository()
    kiosk.setRepository(chocolateBarRepository)
    val kioskController = SnackKioskController(kiosk)
    kioskView.setController(kioskController)
    kioskView.listCommands(kiosk)
    kioskView.showInventory(kioskController.getInventory())
}