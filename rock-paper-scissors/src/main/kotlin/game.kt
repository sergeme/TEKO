import java.util.Scanner

class GameOption(inputName: String) {
  val name = inputName
  lateinit var canBeat: GameOption

  fun canBeat(gameOption: GameOption) {
    this.canBeat = gameOption
  }
}

class Player(inputName: String) {
  val name = inputName
  var score = 0
}

fun main(args: Array<String>) {
  val player = Player("Player")
  val computer = Player("Computer")
  val players: Array<Player> = arrayOf(player, computer)
  val rock = GameOption("Rock")
  val paper = GameOption("Paper")
  val scissors = GameOption("Scissors")
  rock.canBeat(scissors)
  paper.canBeat(rock)
  scissors.canBeat(paper)
  val options: Array<GameOption> = arrayOf(rock, paper, scissors)
  println("How many rounds would you like to play?")
  val reader = Scanner(System.`in`)
  val rounds: Int = reader.nextInt()
  game(options, players, rounds)
}

fun getGameChoice(options: Array<GameOption>): GameOption = options[(Math.random() * options.size).toInt()]

fun getUserChoice(options: Array<GameOption>, message: String): GameOption {
  println(message)
  println("(1) %s (2) %s (3) %s".format(options[0].name, options[1].name, options[2].name))
  val reader = Scanner(System.`in`)
  val choice: Int = reader.nextInt()
  val userInputValid: Boolean = validateUserChoice(choice)
  return if (userInputValid) {
    options[choice-1]
  } else getUserChoice(options, "Invalid Input, make your choice:")
}

fun game(options: Array<GameOption>, players: Array<Player>,rounds: Int) {
  for (round in 1..rounds) {
    println("Round $round of $rounds")
    gameRound(options, players)
  }
  end(players)
}

fun gameRound(options: Array<GameOption>, players: Array<Player>) {
  val gameChoice: GameOption = getGameChoice(options)
  val userChoice: GameOption = getUserChoice(options, "Make your choice:")
  val winner = evalRound(gameChoice, userChoice)
  println("${players[0].name}: ${userChoice.name} - ${players[1].name}: ${gameChoice.name}")
  if (winner == 1 || winner == 0) {
    players[winner].score++
    println("${players[winner].name} wins this round!")
    println("${players[0].name}: ${players[0].score} - ${players[1].name}: ${players[1].score}")
  } else {
    println("Draw! One more round!")
    gameRound(options, players)
  }
}

fun end(players: Array<Player>) {
  var sortedPlayers = players.sortedWith(compareByDescending{it.score})
  println("${sortedPlayers[0].name} wins the game!")
}

fun evalRound(gameChoice: GameOption, userChoice: GameOption): Int {
  var returnInt = 2
  if (userChoice.canBeat == gameChoice) {
    returnInt = 0
  }
  if (gameChoice.canBeat == userChoice) {
    returnInt = 1
  }
  return returnInt
}

fun validateUserChoice(choice: Int): Boolean = choice in 1..3
