

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

  val newYorkFactory = ParserFactory("NYCFactory")
  val newYorkmsg = newYorkFactory.getParserInstance("NYCORDER")
  newYorkmsg.parse

  val LondonFactory = ParserFactory("LondonFactory")
  val londonmsg = LondonFactory.getParserInstance("LondonFEEDBACK")
  londonmsg.parse
  

}

trait ParserFactory {
  def getParserInstance(instance: String) : ParserInstance
}

trait ParserInstance {
  def parse
}

object ParserFactory {

  private class NYCFactory extends ParserFactory {
    override def getParserInstance(instance: String) : ParserInstance = {
      instance match {
        case "NYCORDER" => new NYCORDER
        // case "NYFEEDBACK" => new NYFEEDBACK
        case _ => new DefaultInstance
      }
    }
  }

  private class LondonFactory extends ParserFactory {
    override def getParserInstance(instance: String) : ParserInstance = {
      instance match {
        case "LondonFEEDBACK" => new LondonFEEDBACK
        // case "NYFEEDBACK" => new NYFEEDBACK
        case _ => new DefaultInstance
      }
    }
  }

  private class DefaultFactory extends ParserFactory {
    override def getParserInstance(instance: String) : ParserInstance = {
      instance match {
        case _ => new DefaultInstance
      }
    }
  }

  private class NYCORDER extends ParserInstance {

    println("NYC Parsing order XML...")

    override def parse { println("NYC Order XML Message") }
  }

  private class LondonFEEDBACK extends ParserInstance {

    println("LondonFEEDBACK Parsing feedback XML...")

    override def parse { println("London feedback XML Message") }
  }

  private class DefaultInstance extends ParserInstance {

    println("Default instance")

    override def parse { println("Default message") }
  }

  // Our 'Factory' method
  def apply(s: String): ParserFactory = {
    s match {
      case "NYCFactory" => new NYCFactory
      case "LondonFactory" => new LondonFactory
      case _ => new DefaultFactory
    }
  }

}