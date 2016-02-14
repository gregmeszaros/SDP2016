/**
  * Created by gergelymeszaros on 13/02/2016.
  */
class CodeGenerator(Colours: String) {

  // Coming from constructor
  private var availableColours: String = Colours

  // Random generator
  val random = new scala.util.Random

  // Generate a random string of length n from the given alphabet
  def randomString(alphabet: String)(n: Int): String = {
    Stream.continually(random.nextInt(alphabet.size)).map(alphabet).take(n).mkString
  }

  // Length of the random string to generate
  def randomHexString(length: Int): String = {
    // Blue, Green, Orange, Purple, Red, Yellow
    // Valid colours for the pegs which will be used to generate the secret code
    randomString(this.availableColours)(length)
  }

  def generateCode(length: Int): String = {

    // return "PRGP"
    return randomHexString(length)
  }

}
