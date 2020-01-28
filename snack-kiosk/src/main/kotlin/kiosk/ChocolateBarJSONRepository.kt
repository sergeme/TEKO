package kiosk

import java.io.File
import kotlinx.serialization.json.*
import kotlinx.serialization.*


class ChocolateBarJSONRepository: ChocolateBarInterface {
    private val chocolateBarStock = mutableListOf<ChocolateBar>()
    private val filePath = "c:/temp/chocolateBar.json"
    val json = Json(JsonConfiguration(prettyPrint = true))
    init {
        if(!File(filePath).exists()) {
            File(filePath).createNewFile()

            val data = json.stringify(ChocolateBar.serializer().list, chocolateBarStock)
            File(filePath).writeText(data)
            println(data)
        }
        else {
            val obj = json.parse(ChocolateBar.serializer().list, File(filePath).readText())
            chocolateBarStock.addAll(obj)
        }

    }
    
    override fun createChocolateBar(name: String) {
        val newChocolateBar = ChocolateBar(name.toLowerCase().capitalize())
        chocolateBarStock.add(newChocolateBar)
        val data = json.stringify(ChocolateBar.serializer().list, chocolateBarStock)
        File(filePath).writeText(data)
    }

    override fun removeChocolateBar(input: String): Boolean {
        val bar = input.toLowerCase()
        val chocolateBarToRemove = chocolateBarStock.indexOfFirst { it.name.toLowerCase() == bar }
        if (chocolateBarToRemove != -1) {
            val soldChocolateBar: ChocolateBar = chocolateBarStock[chocolateBarToRemove]
            chocolateBarStock.removeAt(chocolateBarToRemove)
            val data = json.stringify(ChocolateBar.serializer().list, chocolateBarStock)
            File(filePath).writeText(data)
            return true
        }
        return false
    }

    override fun getChocolateBarInventory(): List<Inventory> {
        val inventory: MutableList<Inventory> = mutableListOf<Inventory>()
        val types = chocolateBarStock.distinct()
        for (type in types) {
            inventory.add(Inventory(chocolateBarStock.count { it.name == type.name }, type.name))
        }
        inventory.sortBy { it.name }

        return inventory
    }

    override fun initialize() {
        return
    }
}