package greeter

fun main(args: Array<String>) {
    val personView = GreeterView()
    val personModel = Greeter(personView)
    val personController = PersonController(personModel)
    personView.setController(personController)
    personView.showInitialMenue()
}