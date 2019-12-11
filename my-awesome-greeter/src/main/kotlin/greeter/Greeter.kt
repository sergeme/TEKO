package greeter

class Greeter(private val greeterView: GreeterView) {
    private val personRepository = PersonInMemoryRepository()

    val commands:Map<String, (String)->Unit> = mapOf(
        "add" to ::addPerson,
        "remove" to ::removePerson,
        "greet" to ::greet
    )

    fun execute(input: String) {
        commands[input.substringBefore(" ")]?.invoke(input.substringAfter(" "))
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
}