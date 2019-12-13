package greeter

import kotlin.reflect.KFunction1
import kotlin.system.exitProcess

class Command(name: String, description: String, call: KFunction1<@ParameterName(name = "input") String, Unit>) {

}


class Greeter(private val greeterView: GreeterView) {
    private val personRepository = PersonInMemoryRepository()

    val commands:List<Command> = listOf(
        Command("add", "[name]", ::addPerson),
        Command("remove", "[name]", ::removePerson),
        Command("greet", "", ::greet),
        Command("sortPersons", "", ::sortPersons),
        Command("clear", "", ::clearPersons),
        Command("exit", "", ::exitApplication)
            )


    fun execute(input: String) {
        val command:Command? = commands.find { it.equals(input.substringBefore(" ")) }
        println(command)
        //commands[commands.find { it.equals(input.substringBefore(" ")) }]?.invoke(input.substringAfter(" "))
    }

    private fun greet(input: String) {
        val greetings = arrayListOf<String>()
        val persons = personRepository.getAllPersons()
        persons.forEach { person ->
            greetings.add(person.greet())
        }
        greeterView.showGreet(greetings)
    }
    private fun addPerson(personName: String) {
        personRepository.createPerson(personName)
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