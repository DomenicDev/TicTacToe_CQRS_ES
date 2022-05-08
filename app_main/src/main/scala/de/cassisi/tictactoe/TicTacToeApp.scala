package de.cassisi.tictactoe

import de.cassisi.tictactoe.eventstore.InMemoryEventStore
import de.cassisi.tictactoe.game.{GameEventStoreRepository, GameRepositoryImpl, GameService, GridPosition}
import de.cassisi.tictactoe.player._

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

  val playerId = PlayerId.next()
  playerService.createPlayer(playerId, PlayerName("Domenic"))
  playerService.updatePlayerName(playerId, PlayerName("Cassisi"))


  val playerOne = PlayerId.next()
  val playerTwo = PlayerId.next()

  val gameId = gameService.createNewGame(playerOne, playerTwo)

  // play game session
  gameService.placeMark(gameId, GridPosition(1))
  gameService.placeMark(gameId, GridPosition(4))
  gameService.placeMark(gameId, GridPosition(2))
  gameService.placeMark(gameId, GridPosition(5))
  gameService.placeMark(gameId, GridPosition(3))



}
