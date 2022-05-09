package de.cassisi.tictactoe

import de.cassisi.tictactoe.eventstore.InMemoryEventStore
import de.cassisi.tictactoe.game.command.{CreateNewGameCommand, PlaceNextMarkCommand}
import de.cassisi.tictactoe.game.{GameEventStoreRepository, GameRepositoryImpl, GameService, SquarePosition}
import de.cassisi.tictactoe.player._
import de.cassisi.tictactoe.player.command.{ChangePlayerNameCommand, CreatePlayerCommand}

import java.util.UUID

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

  val playerOneId = UUID.randomUUID()
  val playerTwoId = UUID.randomUUID()

  // create first player
  playerService.handle(CreatePlayerCommand(playerOneId, "Domenic"))
  playerService.handle(ChangePlayerNameCommand(playerOneId, "Cassisi"))


  val gameId = UUID.randomUUID()

  gameService.createNewGame(CreateNewGameCommand(gameId, playerOneId, playerTwoId))

  // play game session
  gameService.placeMark(PlaceNextMarkCommand(gameId, 1))
  gameService.placeMark(PlaceNextMarkCommand(gameId, 4))
  gameService.placeMark(PlaceNextMarkCommand(gameId, 2))
  gameService.placeMark(PlaceNextMarkCommand(gameId, 5))
  gameService.placeMark(PlaceNextMarkCommand(gameId, 3))



}
