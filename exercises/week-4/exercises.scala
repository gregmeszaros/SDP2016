object Main extends App {

  // Store 17
  val t = 17
  // t = 12; val identifier cannot be changed
  println(t)

  // Store ABC1234
  val tt = "ABC1234"
  // tt = "DBC1234" can't reassign val
  println(tt)

  val ttt = 15.56
  println(ttt)

  val sky = "sunny";
  val temperature = 81

  // Write an expression that evaluates to true
  // if the sky is sunny and temperature > 80
  if (sky == "sunny" && temperature > 80) {
    println("This expression evaluates true")
  }

  // sky is sunny or partly cloudy and temperature > 80
  if ((sky == "sunny" || sky == "partly cloudy") && temperature > 80) {
    println("This expression evaluates true")
  }

  // sky is sunny or partly cloudy
  // and temperature > 80 or < 20
  if ((sky == "sunny" || sky == "partly cloudy") && (temperature > 80 || temperature < 20)) {
    println("This expression evaluates true")
  }

  // Fahrenheit
  var F: Double = 56.00

  // Convert Fahrenheit to celsius
  var C: Double = (F - 32) * (5f/9)
  println("Converted to Celsius: " + C)

  // Convert Celsius to Fahrenheit
  F = (C * (9f/5)) + 32

  println("Converted to Fahrenheit: " + F)


}
