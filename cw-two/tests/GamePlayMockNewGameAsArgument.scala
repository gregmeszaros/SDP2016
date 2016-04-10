

case class GamePlayMockNewGameAsArgument(b: Boolean, newGameChoice: String) extends GameAbstractImpl(b: Boolean) {
  println("This is the test game with argument for new Game input")


  val BLACK_VAL = "BLACK"
  val WHITE_VAL = "WHITE"
  val SECRET_LENGTH = 4 // Secret code length + determines how many different colours the secret code can maximally have
  val AVAILABLE_COLOURS = "BGOPRY" // Available colours when generating the secret code
  val MAX_ALLOWED_GUESSES = 12 // Max Number of guesses to allow before loosing the game

  def apply() = {

    println("Welcome to Mastermind.")

  }
  // some vars for storing the print messages to test
  var secretCodeMessage = ""
  var winMessage = ""
  var loseMessage = ""
  var nextGameMessage = ""

  def setup() = {

    println("Generating secret code ....")

    // Setup secret code (with the defined length)
    // Pass the available CHARS which will be used to generate the secret code
    this.setSecretCode(new CodeGenerator(AVAILABLE_COLOURS).generateCode(SECRET_LENGTH))

    // If we need to show the secret code show the secret code
    if (this.showSecretCode() == true) {
      secretCodeMessage += "Secret code is: " + this.getSecretCode()
      println(secretCodeMessage)
    }

  }

  // A mock play method to assist in testing def runGames when play() ==true ie won the game

  def play(): Boolean = true

  override def runGames(): Unit = {

    // Setup game
    this.setup()

    // Play the game
    if (this.play() != false) {
      // a val to store and test the winning message
      winMessage += "Congratulations, you won this game!!!"
      println(winMessage)
    }
    else {
      loseMessage += "Sorry, you lost this game!"
      println(loseMessage)
    }

    // New game???
    nextGameMessage += "Do you want to play a new game?"
    println(nextGameMessage)

    // Prompt the user to select new game or quit
    // newGame is set to Y  for testing
    var newGame = newGameChoice
    println("newGame is: " + newGame)

    if (newGame == "Y") {

      // Cleanup tasks
      this.cleanProgress()

      // Run new game
      this.mockRunGames()
    }
  }
  var mockRunGamesTracker = ""
  def mockRunGames() = {
    println("running mockrunGames")
    mockRunGamesTracker += "mockRunGames has been called"
  }

  apply()
}