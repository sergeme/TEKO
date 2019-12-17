package kiosk

class ChocolateBarRepository {
    private val chocolateBarStock = mutableListOf<ChocolateBar>()
    fun createChocolateBar(name: String) {
        val newChocolateBar = ChocolateBar(name.toLowerCase().capitalize())
        chocolateBarStock.add(newChocolateBar)
    }

    fun removeChocolateBar(input: String): Boolean {
        val bar = input.toLowerCase()
        val chocolateBarToRemove = chocolateBarStock.indexOfFirst { it.name.toLowerCase() == bar }
        if (chocolateBarToRemove != -1) {
            val soldChocolateBar: ChocolateBar = chocolateBarStock[chocolateBarToRemove]
            chocolateBarStock.removeAt(chocolateBarToRemove)
            return true
        }
        return false
    }

    fun getChocolateBarInventory(): List<Inventory> {
        val inventory: MutableList<Inventory> = mutableListOf<Inventory>()
        val types = chocolateBarStock.distinct()
        for (type in types) {
            inventory.add(Inventory(chocolateBarStock.count { it.name == type.name }, type.name))
        }
        inventory.sortBy { it.name }

        return inventory
    }
}