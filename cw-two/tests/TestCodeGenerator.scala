import org.scalatest._

class CodeGeneratorFunSpec extends FunSpec with Matchers with OneInstancePerTest{
	val cg = new CodeGenerator("BGOPRY")

	describe("randomString"){
		it("generates a random string of length 4 when n is 4, using the letters from the given alphabet string "){
			cg.randomString("BGOPRY")(4).size should be(4)
			//need to test the letters contained are only BGOPRY
		}
	}
	
	describe("randomHexString"){
		it("calls randomString with the availableColours and the given length"){
			cg.randomHexString(4).size should be(4)
			// need to test string contains BGOPRY letters only
		}
	}
	
	describe("generateCode"){
		it("calls randomHexString and passes it the given length"){
			cg.generateCode(4).size should be(4)
		}
	}
}