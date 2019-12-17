package greeter

class PersonController(val greeter: Greeter) {

    fun parse(command: String): Boolean {
        if(greeter.commands.any { x -> x.name == command.substringBefore(" ")}) {
            greeter.execute(command.substringBefore(" "), command.substringAfter(" "))
            return true
        }
        println("Befehl $command nicht erkannt!")
        return false
    }
}