package greeter

class PersonInMemoryRepository {
    private val persons = arrayListOf<Person>()
    fun createPerson(personName: String): Person {
        val newPerson = Person(personName)
        persons.add(newPerson)
        return newPerson
    }
    fun removePerson(personName: String) = persons.removeAt(persons.indexOfFirst { it.name == personName })

    fun sortRepository() = persons.sortBy { it.name }

    fun clearRepository() = persons.clear()

    fun getAllPersons(): List<Person> = persons
}