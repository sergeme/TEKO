import java.util.Scanner

fun main(args: Array<String>) {
    val options:Array<String> = arrayOf("Rock", "Paper", "Scissors")
    val gameChoice:String = getGameChoice(options)
    val userChoice:Int = getUserChoice(options, "Make your choice:")
}

fun getGameChoice(options: Array<String>): String = options[(Math.random()*options.size).toInt()]

fun getUserChoice(options: Array<String>, message: String):Int {
    println(message)
    println("(1) %s (2) %s (3) %s".format(options[0], options[1], options[2]))
    val reader = Scanner(System.`in`)
    val choice:Int = reader.nextInt()
    val userInputValid:Boolean = evalUserChoice(choice)
    return if(userInputValid) {
        choice
    }
    else getUserChoice(options,"Invalid Input, make your choice:")
}

fun evalUserChoice(choice: Int): Boolean = choice in 1..3
