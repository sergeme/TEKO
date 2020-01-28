package kiosk

interface ChocolateBarInterface {
    fun createChocolateBar(name: String)
    fun removeChocolateBar(input: String): Boolean
    fun getChocolateBarInventory(): List<Inventory>
    fun initialize()
}
