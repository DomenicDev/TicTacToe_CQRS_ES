package de.cassisi.tictactoe

import de.cassisi.tictactoe.command.PlaceNextMarkCommand
import de.cassisi.tictactoe.game.{GameId, GameSessionAggregate, GridPosition}
import de.cassisi.tictactoe.player.{PlayerId, PlayerName}
import de.cassisi.tictactoe.repository.PlayerEsRepository
import de.cassisi.tictactoe.service.PlayerService
import de.cassisi.tictactoe.store.{InMemoryEventStore, PlayerAggregateRepository}

import java.util.UUID

object TicTacToeApp extends App {

  println("Welcome to TicTacToe")

  val playerOne = new PlayerId(UUID.randomUUID())
  val playerTwo = new PlayerId(UUID.randomUUID())



  val game: GameSessionAggregate = new GameSessionAggregate(GameId.nextGameId(), playerOne, playerTwo)

  game.execute(PlaceNextMarkCommand(GridPosition(1)))
  game.execute(PlaceNextMarkCommand(GridPosition(4)))
  game.execute(PlaceNextMarkCommand(GridPosition(2)))
  game.execute(PlaceNextMarkCommand(GridPosition(5)))
  game.execute(PlaceNextMarkCommand(GridPosition(3)))


  val eventStore = new InMemoryEventStore()
  val playerAggregateRepository = new PlayerAggregateRepository(eventStore)
  val playerRepository = new PlayerEsRepository(playerAggregateRepository)
  val playerService: PlayerService = new PlayerService(playerRepository)

  val playerId = PlayerId.next()
  playerService.createPlayer(playerId, PlayerName("Domenic"))
  playerService.updatePlayerName(playerId, PlayerName("Cassisi"))


}
