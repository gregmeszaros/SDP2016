import atomicscala.AtomicTest._

object Week7 extends App {

  println("week 7")

  val v = Vector(1, 2, 3, 4)
  v.map(n => n + 1) is Vector(2, 3, 4, 5)

  val vNew = Vector(1, 2, 3, 4)
  vNew.map(n => (n * 11) + 10) is Vector(21, 32, 43, 54)

  // Replace to foreach fails
  vNew.foreach(n => (n * 11) + 10) is Vector(21, 32, 43, 54)

  // Same thing with little bit refactored foreach
  vNew.foreach((n: Int) => println((n * 11) + 10))

  // Rewrite to for loop (more complicated than simple map function)
  for (vectorVal <- vNew) {
    println((vectorVal * 11) + 10)
  }


}