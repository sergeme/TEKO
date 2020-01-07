package kiosk

fun main(args: Array<String>) {
    val startAmount = 10
    val kioskView = SnackKioskView()
    val kiosk = Kiosk(kioskView, startAmount)
    val kioskController = SnackKioskController(kiosk)
    kioskView.setController(kioskController)
    kioskView.listCommands(kiosk)
    kioskView.showInventory(kioskController.getInventory())
}