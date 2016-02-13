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

  def apply() = {

    println("Welcome to Mastermind.")

  }

  def setup() = {

    println("Generating secret code ....")

    // Setup secret code (with the defined length)
    this.setSecretCode(new CodeGenerator().generateCode(4))

    // If we need to show the secret code show the secret code
    if (this.showSecretCode() == true) {
      println("Secret code is: " + this.getSecretCode())
    }

  }

  /**
    * Check if the guessed Character matches our secret word characters
    * @param guessedChar
    */
  def checkGuessedChar(guessedChar: Char, guessedCharIndex: Int): Unit = {
    //println(guessedChar + " " + index)
    this.getSecretCode().zipWithIndex.map { case (char, index) => this.checkSecretCodeChar(char, index, guessedChar, guessedCharIndex) }
  }

  def checkSecretCodeChar(secretChar: Char, secretCharIndex: Int, guessedChar: Char, guessedCharIndex: Int): Unit = {

    println (secretCharIndex + " " + secretChar + " " + guessedCharIndex + " " + guessedChar)

    // If we have a match (check if the match is exact (black peg) or not exact (white peg)
    if (secretChar == guessedChar) {

      // Value doesn't exist create
      if (!this.getUsedChars().contains(secretCharIndex + "-" + guessedChar)) {
        // Value doesn't exist create
        if (secretCharIndex != guessedCharIndex) {
          this.setResultPegs(WHITE_VAL, secretCharIndex, guessedChar)
          if (this.getUsedChars().contains(secretCharIndex + "-" + guessedChar) && this.getUsedChars()(secretCharIndex + "-" + guessedChar) != BLACK_VAL) {

          }
        }
      }

      if (secretCharIndex == guessedCharIndex) {
        this.setResultPegs(BLACK_VAL, secretCharIndex, guessedChar)
      }
      else {
        if (this.getUsedChars().contains(secretCharIndex + "-" + guessedChar) && this.getUsedChars()(secretCharIndex + "-" + guessedChar) != BLACK_VAL) {
          this.setResultPegs(WHITE_VAL, secretCharIndex, guessedChar)
        }
      }
    }
  }

  def play(): Boolean = {

    var counter = 0

    // Until the guess is not correct and we didn't exceeded or guess count
    while (this.getGuess() != this.getSecretCode()) {

      // If we exceeded the allowed guesses limit -> end the game
      if (counter < this.getAllowedGuesses()) {
        println("What is your next guess?")

        // Prompt the user to guess a value
        this.setGuess(readLine("Enter guess: "))

        // Check each character against the characters in secret code
        this.getGuess().zipWithIndex.map { case (char, index) => this.checkGuessedChar(char, index) }


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