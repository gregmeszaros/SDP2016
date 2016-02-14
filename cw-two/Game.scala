import scala.util.control.Breaks._

trait Game {
  /**
    * Run a one or more game sof mastermind, until the player
    * quits.
    */
  def runGames
}

case class GamePlay(b: Boolean) extends GameAbstractImpl(b: Boolean) {

  val BLACK_VAL = "BLACK"
  val WHITE_VAL = "WHITE"
  val SECRET_LENGTH = 4 // Secret code length + determines how many different colours the secret code can maximally have
  val AVAILABLE_COLOURS = "BGOPRY" // Available colours when generating the secret code
  val MAX_ALLOWED_GUESSES = 12 // Max Number of guesses to allow before loosing the game

  def apply() = {

    println("Welcome to Mastermind.")

  }

  def setup() = {

    println("Generating secret code ....")

    // Setup secret code (with the defined length)
    // Pass the available CHARS which will be used to generate the secret code
    this.setSecretCode(new CodeGenerator(AVAILABLE_COLOURS).generateCode(SECRET_LENGTH))

    // If we need to show the secret code show the secret code
    if (this.showSecretCode() == true) {
      println("Secret code is: " + this.getSecretCode())
    }

  }

  def play(): Boolean = {

    var counter = 0

    // Until the guess is not correct and we didn't exceeded or guess count
    while (this.getGuess() != this.getSecretCode()) {

      // If we exceeded the allowed guesses limit -> end the game
      if (counter < MAX_ALLOWED_GUESSES) {
        println("What is your next guess?")

        // Prompt the user to guess a value
        this.setGuess(readLine("Enter guess: "))

        var pMatch = 0
        var cMatch = 0

        for ((guessChar, k) <- this.getGuess().view.zipWithIndex) {
          if (guessChar == this.getSecretCode().charAt(k)) {
            pMatch += 1
          }
        }

        var tempSecretCode = this.getSecretCode()

        for ((guessChar, k) <- this.getGuess().view.zipWithIndex) {
          breakable {
            for ((originalChar, originalK) <- tempSecretCode.view.zipWithIndex) {
              if (guessChar ==  originalChar) {
                cMatch += 1
                tempSecretCode = tempSecretCode.substring(0, originalK) + tempSecretCode.substring(originalK + 1, tempSecretCode.length)
                break
              }
            }
          }
        }

        // Sets results
        for(a <- 1 to pMatch) {
          this.setUsedChars("B" + a.toString, BLACK_VAL)
        }

        for(a <- 1 to cMatch-pMatch) {
          this.setUsedChars("W" + a.toString, WHITE_VAL)
        }

        // Check what we guessed and save to the ArrayBuffer
        this.setPartialProgress(this.getGuess() + " Result: " + this.getResultPegs())

        println(this.getUsedChars())
        println(this.getPartialProgress().mkString("\n"))

        // Clean the result pegs
        this.cleanUsedChars()

        // Increase the guess counter
        counter += 1

      }
      else {
        // Set variable so we know we lost the game
        return false
      }

    }

    // Won the game
    return true

  }

  override def runGames(): Unit = {

    // Setup game
    this.setup()

    // Play the game
    if (this.play() != false) {
      println("Congratulations, you won this game!!!")
    }
    else {
      println("Sorry, you lost this game!")
    }

    // New game???
    println("Do you want to play a new game?")

    // Prompt the user to select new game or quit
    var newGame = readLine("Press 'Y' if yes or anything else to quit: ")

    if (newGame == "Y") {

      // Cleanup tasks
      this.cleanProgress()

      // Run new game
      this.runGames()
    }

  }

  apply()
}