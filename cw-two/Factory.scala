object Factory {
  def getInstance(c: Class[_], b: Boolean): Game = {
    // Returns the Game object (easy or not easy game)
    return new GamePlay(b)
  }
}