import org.scalatest.Assertions._

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

  def getSquare(value: Int): Int = {
    return value * value;
  }

  val a = getSquare(3);

  println(a);
  assert(a == 9);

  val b = getSquare(6);
  assert(b == 36);

  val c = getSquare(5);
  assert(c == 25);

  def isArg1GreaterThanArg2(val1: Double, val2: Double): Boolean = {
    return val1 > val2
  }

  assert(isArg1GreaterThanArg2(4.15, 4.12))
  val t2 = isArg1GreaterThanArg2(2.1, 1.2)

  assert(t2);


  def ManyTimesString(val1: String, val2: Int): String = {

    var a = 0
    var returnString = ""

    // for loop execution with a range
    for( a <- 1 to val2){
      returnString = returnString + val1
    }

    return returnString

  }

  var m1 = ManyTimesString("abc", 3)
  var m2 = ManyTimesString("123", 2)

  assert("abcabcabc" == m1, "This strings should equal")
  assert("123123" == m2, "This strings should equal")

  val xRange = 1 to 5
  println(xRange)

  val xRangeWithStep = 1 to 5 by 2
  println (xRangeWithStep)

  val s1 : String = "Sally"
  val s2 : String = "Sally"

  // Test if 2 string are the same
  if (s1.equals(s2)) {
    println("s1 and s2 are equal")
  }
  else {
    println("s1 and s2 are NOT equal")
  }

  class Hippo() {
  }
  class Lion() {
  }
  class Tiger() {
  }
  class Monkey() {
  }
  class Giraffe() {
  }

  // Create different classes
  var hippo = new Hippo
  println(hippo)

  var lion = new Lion
  println(lion)

  var tiger = new Tiger
  println(tiger)

  var monkey = new Monkey
  println(monkey)

  var giraffe = new Giraffe
  println(giraffe)

  // Few more classes
  var lion2 = new Lion
  println(lion2)

  // 2 more giraffes
  var giraffe2 = new Giraffe
  println(giraffe2)

  var giraffe3 = new Giraffe
  println(giraffe3)

  class Sailboat() {

    def raise(): String = {
      return "Sails raised"
    }

    def lower(): String = {
      return "Sails lowered"
    }

    // Create Flare object instance and call the light method (returns as String)
    def signal(): String = {
      return new Flare().light()
    }

  }

  var testSail = new Sailboat
  val r1 = testSail.raise()
  val r2 = testSail.lower()

  // If they are not equal excpetion will be thrown showing the message below
  assert(r1 == "Sails raised", "Expected Sails raised, Got " + r1)
  assert(r2 == "Sails lowered", "Expected Sails lowered, Got " + r2)

  class Motorboat() {

    def on(): String = {
      return "Motor on"
    }

    def off(): String = {
      return "Motor off"
    }

    // Create Flare object instance and call the light method (returns as String)
    def signal(): String = {
      return new Flare().light()
    }

  }

  val motorboat = new Motorboat
  val ss1 = motorboat.on()
  val ss2 = motorboat.off()

  assert(ss1 == "Motor on", "Expected Motor on, Got " + ss1)
  assert(ss2 == "Motor off", "Expected Motor off, Got " + ss2)

  class Flare() {
    def light(): String = {
      return "Flare used!"
    }
  }

  val flare = new Flare
  val f1 = flare.light
  assert(f1 == "Flare used!", "Expected Flare used!, Got " + f1)


  // add a method signal that creates a Flare object and calls the light method on the Flare object
  val signal = testSail.signal()
  println(signal)

  assert(signal == "Flare used!", "Expected Flare used! Got " + signal)

  val motorboat2 = new Motorboat
  val flare2 = motorboat2.signal()
  println(flare2)

  assert(flare2 == "Flare used!", "Expected Flare used!, Got " + flare2)


}