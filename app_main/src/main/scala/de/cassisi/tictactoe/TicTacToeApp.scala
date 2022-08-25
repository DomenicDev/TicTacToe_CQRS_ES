package de.cassisi.tictactoe

import de.cassisi.tictactoe.dto.GameStateDTO
import de.cassisi.tictactoe.eventstore.InMemoryEventStore
import de.cassisi.tictactoe.game.command.{OpenNewGameCommand, PlaceNextMarkCommand}
import de.cassisi.tictactoe.game.{GameEventStoreRepository, GameRepositoryImpl, GameService}
import de.cassisi.tictactoe.gamestate.InMemoryGameStateRepository
import de.cassisi.tictactoe.handler.QueryHandler
import de.cassisi.tictactoe.player._
import de.cassisi.tictactoe.player.command.{ChangePlayerNameCommand, RegisterPlayerCommand}
import de.cassisi.tictactoe.projection.{InMemoryCurrentStateProjection, InMemoryGameStateDatabase}
import de.cassisi.tictactoe.projector.{CurrentGameStateProjector, SimpleEventSubscriber}
import de.cassisi.tictactoe.ranking.{InMemoryRankingDatabase, RankingProjector}

import java.lang.Exception
import java.util.UUID
import scala.io.StdIn.readLine
import scala.util.control.Breaks.break

object TicTacToeApp extends App {

  println("Welcome to TicTacToe")


  val eventStore = new InMemoryEventStore()

  eventStore.subscribe((event) => println(event))

  val playerEventRepository = new PlayerEventStoreRepository(eventStore)
  val playerRepository = new PlayerRepositoryImpl(playerEventRepository)
  val playerService: PlayerService = new PlayerService(playerRepository)

  val gameEventStoreRepository = new GameEventStoreRepository(eventStore)
  val gameRepository = new GameRepositoryImpl(gameEventStoreRepository)
  val gameService = new GameService(gameRepository)

  val subscriber = new SimpleEventSubscriber()
  val database = new InMemoryGameStateDatabase()
  val projection = new InMemoryCurrentStateProjection(database)
  val projector = new CurrentGameStateProjector(projection)
  val rankingDatabase = new InMemoryRankingDatabase()
  val rankingProjector = new RankingProjector(rankingDatabase)

  subscriber.addHandler(projector)
  subscriber.addHandler(rankingProjector)
  eventStore.subscribe(subscriber)

  val gameStateRepository = new InMemoryGameStateRepository(database)
  val queryHandler = new QueryHandler(gameStateRepository)

  // PLAY A GAME....

  val playerOneId = UUID.randomUUID()
  val playerTwoId = UUID.randomUUID()

  // create first player
  playerService.handle(RegisterPlayerCommand(playerOneId, "Domenic"))
  playerService.handle(ChangePlayerNameCommand(playerOneId, "Cassisi"))

  playerService.handle(RegisterPlayerCommand(playerTwoId, "Alfred"))

  val gameId = UUID.randomUUID()

  gameService.createNewGame(OpenNewGameCommand(gameId, playerOneId, playerTwoId))

  // play game session
  gameService.placeMark(PlaceNextMarkCommand(gameId, 1))
  gameService.placeMark(PlaceNextMarkCommand(gameId, 4))
  gameService.placeMark(PlaceNextMarkCommand(gameId, 2))
  gameService.placeMark(PlaceNextMarkCommand(gameId, 5))
  gameService.placeMark(PlaceNextMarkCommand(gameId, 3))

  println(queryHandler.getCurrentGameState(gameId).gameGrid.mkString("Array(", ", ", ")"))


  def visualizeGame(gameState: GameStateDTO): Unit = {
    val squares = gameState.gameGrid
    println("-------------")
    println(s"| ${getCharacter(squares(0))} | ${getCharacter(squares(1))} | ${getCharacter(squares(2))} |")
    println("-------------")
    println(s"| ${getCharacter(squares(3))} | ${getCharacter(squares(4))} | ${getCharacter(squares(5))} |")
    println("-------------")
    println(s"| ${getCharacter(squares(6))} | ${getCharacter(squares(7))} | ${getCharacter(squares(8))} |")
    println("-------------")
  }

  def getCharacter(value: Int): Character = {
    value match {
      case 0 => '_'
      case 1 => 'X'
      case 2 => 'O'
      case _ => ' '
    }
  }

  var exit: Boolean = false
  do {

    println("'1' => Start a new game")
    println("'2' => Show Stats")
    println("'3' => Exit Game")
    val input = readLine()
    if ("1".equals(input)) {
      val gameId = UUID.randomUUID()
      gameService.createNewGame(OpenNewGameCommand(gameId, playerOneId, playerTwoId))

      var gameState = queryHandler.getCurrentGameState(gameId)
      visualizeGame(gameState)
      while (!gameState.completed) {
        println("Enter the position of the next mark to place:")

        var valid: Boolean = false
        while (!valid) {
          try {
            valid = true

            val pos = Integer.parseInt(readLine())
            gameService.placeMark(PlaceNextMarkCommand(gameId, pos))

            gameState = queryHandler.getCurrentGameState(gameId)
            visualizeGame(gameState)

          } catch {
            case e: Exception => {
              println(e)
              valid = false
            }
          }
        }
      }

      if (gameState.winner != null) {
        println("Winner is: " + gameState.winner)
      }

    } else if ("2".equals(input)) {
      rankingDatabase.getRankings.foreach(f => println(f._1 + ": " + f._2))
    } else if ("3".equals(input)) {
      exit = true
    }

  } while (!exit)


}
