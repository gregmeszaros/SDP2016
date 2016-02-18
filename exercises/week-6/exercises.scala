import atomicscala.AtomicTest._

object Week6 extends App {

  println("week 6")

  def forecast(temp: Int): String = {
    temp match {
      case 100 => "Sunny"
      case 80 => "Mostly Sunny"
      case 50 => "Partly Sunny"
      case 20 => "Mostly Sunny"
      case 0 => "Cloudy"
      case _ => "Unknown"
    }
  }

  forecast(100) is "Sunny"
  forecast(80) is "Mostly Sunny"
  forecast(50) is "Partly Sunny"
  forecast(20) is "Mostly Sunny"
  forecast(0) is "Cloudy"
  forecast(15) is "Unknown"

  val forecastVector = Vector(100, 80, 50, 20, 0, 15)

  println(forecastVector)

  for (dataVal <- forecastVector) {
    // Use a for loop to call forecast with the contents of sunnyData.
    println(forecast(dataVal))
  }

}

object TestArgs extends App {

  class Family(incomingMembers: String*) {

    // Save the length size coming from constructor
    private val paramMem = incomingMembers.length

    def familySize(): Int = {
      return this.paramMem
    }
  }

  val family1 = new Family("Mum", "Dad", "Sally", "Dick")
  family1.familySize() is 4
  val family2 = new Family("Dad", "Mom", "Harry")
  family2.familySize() is 3

  class FlexibleFamily(mum: String, dad: String, incomingMembers: String*) {

    // Save the length size coming from constructor
    private val paramMem = incomingMembers.length

    def familySize(): Int = {
      // Hardcode 2 for mum and dad (plus the array length)
      return 2 + this.paramMem
    }
  }

  // Flexible family
  val family3 = new FlexibleFamily("Mum", "Dad", "Sally", "Dick")
  family3.familySize() is 4
  val family4 = new FlexibleFamily("Dad", "Mom", "Harry")
  family4.familySize() is 3

  // Write a method that squares a variable argument list of numbers and returns thesum. Satisfy the following tests
  def squareThem(numbersToSquare: Int*): Int = {

    var returnSquaredSum = 0

    for (data <- numbersToSquare) {
      returnSquaredSum += data * data
    }

    return returnSquaredSum

  }
  squareThem(2) is 4
  squareThem(2, 4) is 20
  squareThem(1, 2, 4) is 21

  // Simple time class
  class SimpleTime(h: Int = 0, m: Int = 0) {
    val hours = h
    val minutes = m
  }

  val tTime = new SimpleTime(5, 30)
  tTime.hours is 5
  tTime.minutes is 30

  val t2 = new SimpleTime(10)
  t2.hours is 10
  t2.minutes is 0

  class Planet(name: String = "", description: String = "", moon: Int = 1) {

    def name(): String = {
      return name
    }

    def description(): String = {
      return description
    }

    def moon(): Int = {
      return moon
    }

    def hasMoon(): Boolean = {

      if (moon() > 0) {
        return true
      }
      return false
    }

  }

  val tMoon = new Planet(moon = 0, name = "Mercury", description = "small and hot planet")
  println(tMoon.hasMoon())

  // We don't need to change the code
  val earth = new Planet(moon = 1, name = "Earth", description = "a hospitable planet")
  earth.hasMoon is true

}