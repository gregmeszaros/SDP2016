package poly

sealed trait A {
  def foo: String
}

final case class B() extends A {
  def foo: String = "It's a B"
}

object TestMyPoly extends App {
  val classTest: A = B()

  println(classTest.foo)
}