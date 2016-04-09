

object Week5 extends App {

  println("week 5")

  // This is scala strategy pattern using more functional style because we can pass algorithms around just like we pass objects around
  def CapTextFormatter(inputString: String) : String = {
    inputString.toUpperCase()
  }

  def LowerTextFormatter(inputString: String) : String = {
    inputString.toLowerCase()
  }

  // We can use whatever function we want and they kind of behaving like objects
  // This totally reduces the boilerplate code Strategy pattern usually produces so it makes the implementation much cleaner
  def execute(f:(String) => String, inputString: String) = f(inputString)

  var customString = "Testing text in caps formatter"
  println("CapTextFormatter: " + execute(CapTextFormatter, customString))

  var customstring = "testing text in lower formatter"
  println("LowerTextFormatter: " + execute(LowerTextFormatter, customString))

}