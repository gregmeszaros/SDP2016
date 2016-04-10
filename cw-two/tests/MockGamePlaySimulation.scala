import scala.util.control.Breaks._

case class MockGamePlaySimulation(b: Boolean, code:String , guesses:List[String]) extends GameAbstractImpl(b: Boolean) {

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
    //a code is passed in as argument for testing purposes
    this.setSecretCode(code)

    // If we need to show the secret code show the secret code
    if (this.showSecretCode() == true) {
      println("Secret code is: " + this.getSecretCode())
    }

  }

  // create a var errorMessage for testing when the guess is too long or short during the play method
  var errorMessage = "" 

  def play(): Boolean = {

    var counter = 0

    // Until the guess is not correct and we didn't exceeded or guess count
    while (this.getGuess() != this.getSecretCode()) {

      // If we exceeded the allowed guesses limit -> end the game
      if (counter < MAX_ALLOWED_GUESSES) {
        println("What is your next guess?")

        // Prompt the user to guess a value
        // Replaced user input with our simulated input from the guesses List, using counter to iterate through the List
        this.setGuess(guesses(counter))
        println("guess has been set to " + getGuess)

        var pMatch = 0
        var cMatch = 0

        /**
          * Validate if the guess is long/short
          */
        while (this.getGuess().length != this.getSecretCode().length) {
          // Prompt the user to guess a value
          //For simulation, check the errorMessage code will work
          errorMessage += "Validation error (Code too long or too short): Please enter a new guess: "
          //For simulation test, set the new guess to a default guess
          println("this guess is now being set to BBBB due to error")
          this.setGuess("BBBB")
        }

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

