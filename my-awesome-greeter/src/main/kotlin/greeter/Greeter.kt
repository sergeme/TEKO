package greeter

import kotlin.reflect.KFunction1
import kotlin.system.exitProcess

class Command(val name: String, val description: String, val call: KFunction1<String, Unit>)

class Greeter(private val greeterView: GreeterView) {
    private val personRepository = PersonInMemoryRepository()

    val commands:List<Command> = listOf(
        Command("add", "[name]", this::addPerson),
        Command("remove", "[name]", this::removePerson),
        Command("sort", "", this::sortPersons),
        Command("clear", "", this::clearPersons),
        Command("exit", "", this::exitApplication),
        Command("greet", "", this::greet)
    )
    var functions = listOf<(uuid: String) -> Unit?>(
        this::addPerson,
        this::removePerson)

    fun execute(inputCommand: String, inputProperty: String) {
        val command:Command? = commands.find { x -> x.name == inputCommand }
        command?.call?.invoke(inputProperty)
    }

    private fun greet(input: String) {
        val greetings = arrayListOf<String>()
        val persons = personRepository.getAllPersons()
        persons.forEach { person ->
            greetings.add(person.greet())
        }
        greeterView.showGreet(greetings)
    }
    private fun addPerson(input: String) {
        personRepository.createPerson(input)
        greeterView.amountOfPersonChanged(personRepository.getAllPersons())
    }

    private fun removePerson(personName: String) {
        personRepository.removePerson(personName)
        greeterView.amountOfPersonChanged(personRepository.getAllPersons())
    }

    private fun sortPersons(input: String) {
        personRepository.sortRepository()
        greeterView.amountOfPersonChanged(personRepository.getAllPersons())
    }

    private fun clearPersons(input: String) {
        personRepository.clearRepository()
        greeterView.amountOfPersonChanged(personRepository.getAllPersons())
    }

    private fun exitApplication(input: String) {
        exitProcess(1)
    }
}