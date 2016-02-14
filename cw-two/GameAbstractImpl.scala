import scala.collection.mutable.ArrayBuffer

abstract class GameAbstractImpl extends Game {
  private var showCode: Boolean = false

  // Secret code
  private var secretCode: String = ""

  // Guess from the user
  private var guess: String = ""

  private var usedChars:Map[String, String] = Map()

  // Any results stored during the game
  private var results = ArrayBuffer[String]()

  /**
    * Create a Game object.
    *
    * @param easy If easy is true the secret code will be
    *             revealed at all times when playing the game. If easy is
    *             false the secret code is not revealed until correctly guessed
    *             or the player runs out of turns.
    */
  def this(easy: Boolean) {
    this()
    showCode = easy
  }

  /**
    * Returns TRUE if we need to show the secret code when playing the game
    * Otherwise it returns FALSE
    * @return
    */
  def showSecretCode(): Boolean = {
    return this.showCode
  }

  /**
    * Sets and saves the generated secret code for the game as String
    * @param code
    */
  def setSecretCode(code: String): Unit = {
    this.secretCode = code
  }

  /**
    * Returns the secret code
    * @return
    */
  def getSecretCode(): String = {
    return this.secretCode
  }

  /**
    * Sets the guess from the user input
    */
  def setGuess(guess: String): Unit = {
    this.guess = guess
  }

  /**
    * Returns the last guess received from the user
    * @return
    */
  def getGuess(): String = {
    return this.guess
  }

  def setUsedChars(index: String, value: String): Unit = {
    this.usedChars += (index -> value)
  }

  def getUsedChars(): Map[String, String] = {
    return this.usedChars
  }

  def getResultPegs(): String = {
    if (getUsedChars().size > 0) {
      return getUsedChars().view map {
        case (key, value) => value
      } mkString (" ")
    }

    return "No pegs"
  }

  def cleanUsedChars(): Unit = {
    this.usedChars = Map[String, String]()
  }

  def setPartialProgress(progressResult: String): Unit = {
    this.results += progressResult
  }

  def getPartialProgress(): ArrayBuffer[String] = {
    return this.results
  }

  /**
    * Cleanup tasks before starting a new game
    */
  def cleanProgress(): Unit = {
    this.guess = ""
    this.results = ArrayBuffer[String]()
  }
}