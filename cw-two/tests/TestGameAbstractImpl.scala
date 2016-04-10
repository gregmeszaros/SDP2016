import org.scalatest._

class GameAbstractImplFunSpec extends FunSpec with Matchers with OneInstancePerTest{
	val gameAbs = new MockGameAbsImpl
	describe("secretCode"){
		it("is the empty string if not set"){
			gameAbs.getSecretCode should equal ("")
		}
		it("can be set and retrieved with setSecretCode and getSecretCode"){
			gameAbs.setSecretCode("a code")
			gameAbs.getSecretCode should equal ("a code")
		}
	}
	describe("guess"){
		it("is the empty string if not set"){
			gameAbs.getSecretCode should equal ("")
		}
		it("can be set and retrieved with setGuess and getGuess"){
			gameAbs.setGuess("a guess")
			gameAbs.getGuess should equal ("a guess")
		}
	}
	// not clear when to use: should be/should equal/isAnInstanceOf
	describe("usedChars"){
		//Do I need to check the empty map is still Map[String,String]?
		it("is the empty Map() if not set"){
			gameAbs.getUsedChars should be(Map())
		}
		it("can be set and retrieved with setUsedChars and getUsedChars"){
			gameAbs.setUsedChars("first key" , "first value")
			gameAbs.setUsedChars("second key" , "second value")
			gameAbs.setUsedChars("third key" , "third value")

			gameAbs.getUsedChars.size should be(3)
		}
	}
	describe("setPartialProgress and getPartialProgress"){
		it("getPartialProgress returns an empty ArrayBuffer[String] if not set"){
			gameAbs.getPartialProgress.mkString should be("")
		}
		it("sets and retrieves results with setPartialProgress and getPartialProgress"){
			gameAbs.setPartialProgress("abc")
			gameAbs.setPartialProgress("def")
			gameAbs.getPartialProgress.mkString("") should be("abcdef")
		}
	}
	describe("getResultsPegs"){
		it("returns 'No pegs' if usedChars is empty"){
			gameAbs.getResultPegs should be ("No pegs")
		}
		it("returns a string of the result pegs for the guess"){
			gameAbs.setUsedChars("B1" , "BLACK")
			gameAbs.setUsedChars("B2" , "BLACK")
			gameAbs.setUsedChars("W1" , "WHITE")
			gameAbs.getResultPegs() should be("BLACK BLACK WHITE")
		}
	}
	

}
