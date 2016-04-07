object MastermindDriver {
  def main(args: Array[String]) {

    // Run a game where secret code is shown
    var g: Game = Factory.getInstance(classOf[Game], true)
    g.runGames

    // Run a game where the secret code is hidden
    var secretGame: Game = Factory.getInstance(classOf[Game], false)
    secretGame.runGames
  }
}