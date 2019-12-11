package greeter

class PersonController(val greeter: Greeter) {

    fun parse(command: String): Boolean {
        if(greeter.commands.keys.contains(command.substringBefore(" "))) {
            greeter.execute(command)
            return true
        }
        println("Befehl $command nicht erkannt!")
        return false
    }
}