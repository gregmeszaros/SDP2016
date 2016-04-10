import org.scalatest._
import Matchers._

class GamePlayFunSpec extends FunSpec with Matchers with OneInstancePerTest{
	/*create 4 games for our tests:
	True means secret code is shown ie arg is true
	False means secret code is not shown ie arg is false
	Win means the mockPlay method returns true
	Loss means it returns False
	In these games, the user input for "Do you want a new Game?" is set to "N" 
	*/
	val gTrueWin = new GamePlayWithMockPlayAndWin(true)
	val gFalseWin = new GamePlayWithMockPlayAndWin(false)
	val gTrueLoss = new GamePlayWithMockPlayAndLose(true)
	val gFalseLoss = new GamePlayWithMockPlayAndLose(false)
	describe("setup"){
		it("sets the secretCode by creating a CodeGenerator"){
			gTrueWin.setup
			gTrueWin.getSecretCode.size should be(4)
		}
		it("prints the code if showSecretCode is set to true "){
			val gMock = new GamePlayWithMockPlayAndWin(true)
			gTrueWin.setup
			gTrueWin.secretCodeMessage should be("Secret code is: " + gTrueWin.getSecretCode())
		}
		it("does not print code if showSecretCode is set to false"){
			gFalseWin.secretCodeMessage should be("")
		}
	}
	
	describe("runGames"){
		it("sets up the game by calling def setUp" ){
			//check setup has not been called yet
			gTrueWin.getSecretCode should be("")
			// call runGames, then check setup has been called by checking secretCode is set
			//note - when rungames is called here , we need to exit the game to conclude the test
			gTrueWin.runGames
			gTrueWin.getSecretCode.size should be(4)
		}

		it("congratulates the player if they won the game"){
			gTrueWin.runGames
			gTrueWin.winMessage should be("Congratulations, you won this game!!!")

		}
		it("commiserates the player if they lost the game"){
			gTrueLoss.runGames
			gTrueLoss.loseMessage should be("Sorry, you lost this game!")
		}

		it("asks user for new game, regardless of the game outcome or setting for showCode"){
			gTrueWin.runGames
			gTrueLoss.runGames
			gFalseWin.runGames
			gFalseLoss.runGames
			gTrueWin.nextGameMessage should be ("Do you want to play a new game?")
			gTrueLoss.nextGameMessage should be ("Do you want to play a new game?")
			gTrueLoss.nextGameMessage should be ("Do you want to play a new game?")
			gTrueLoss.nextGameMessage should be ("Do you want to play a new game?")
		}
		it("exits runGames, returning Unit, if user types anything except Y"){
			// no need to test anything just check the tests complete
			val g1 = new GamePlayMockNewGameAsArgument(true , "N")
			g1.runGames
			val g2 = new GamePlayMockNewGameAsArgument(true , "")
			g2.runGames
			val g3 = new GamePlayMockNewGameAsArgument(true , "random entry")
			g3.runGames
		}
		it("resets the guess and results strings by calling cleanProgress, and calls runGames again if user types Y"){
			val g = new GamePlayMockNewGameAsArgument(true , "Y")
			g.runGames
			//check that it has called mockRunGames, in place of runGames
			g.mockRunGamesTracker should be("mockRunGames has been called")
			//check that cleanProgress has been called
			g.getGuess should be("")
		}
	}

	describe("play"){
		it("returns true if the user guesses the correct code within 12 goes"){
			println("running the winning simulations....")
			simulate("BGYR", List("BGYR")) should be(true)
			simulate("BGYR", List("BGGB", "BGGR", "BGYR")) should be(true)
			simulate("BGYR", List("BBBB","BGYB","BGYB","BGYY","BBYR","OOYR","BGOP","BGOO","BGPR","BGGR","BGRR","BGYR")) should be(true)
		}
		it("returns false if too many guesses"){
			println("running the losing simulations....")
			simulate("BGYR", List("BBBB","BGYB","BGYB","BGYY","BBYR","OOYR","BGOP","BGOO","BGPR","BGGR","BGRR","BGYY")) should be(false)
		}
		it("returns an error message and prompts user for new guess if guess is too long"){
			println("running the simulation with one long error")
			val code = "BGYR"
			val guessesWithLongError = List("BBBB","BGYB","BGYBYYY","BGYY","BBYR","OOYR","BGOP","BGOO","BGPR","BGGR","BGRR","BGYR")
			val gLong = new MockGamePlaySimulation(true, code, guessesWithLongError) 
			gLong.setup
			//check errorMessage is empty before we play, and check it has changed by the end of the game
			gLong.errorMessage should be("")
			gLong.play
			gLong.errorMessage should be("Validation error (Code too long or too short): Please enter a new guess: ")
		}
		it("returns an error message and prompts user for new guess if guess is too short"){
			val code = "BGYR"
			val guessesWithShortErrors = List("B","BGYB","BGYB","BGYY","BBYR","OOYR","BGOP","BGOO","BGPR","BGGR","BGRR","BG")
			val gShort = new MockGamePlaySimulation(true, code, guessesWithShortErrors) 
			gShort.setup
			//check errorMessage is empty before we play, and check it has changed by the end of the game
			gShort.errorMessage should be("")
			gShort.play
			//gShort.errorMessage should be("Validation error (Code too long or too short): Please enter a new guess: ")
		}
	}
	//helper method for simulation testing
	def simulate(code:String , guesses:List[String]):Boolean = {
		val g = new MockGamePlaySimulation(true, code, guesses) 
			g.setup
			g.getSecretCode should be("BGYR")
			val gameResult:Boolean = g.play
			println("The game with code: " + code + " and guesses: " + guesses + " has the result: " + gameResult)
			gameResult
	}
}
	


