package de.cassisi.tictactoe

import de.cassisi.tictactoe.command.PlaceNextMarkCommand
import de.cassisi.tictactoe.game.{GameId, GameSessionAggregate, GridPosition}
import de.cassisi.tictactoe.player.{PlayerAggregate, PlayerId}

import java.util.UUID

object TicTacToeApp extends App {

  println("Welcome to TicTacToe")

  val playerOne = PlayerId(UUID.randomUUID())
  val playerTwo = PlayerId(UUID.randomUUID())

  val game: GameSessionAggregate = new GameSessionAggregate(GameId.nextGameId(), playerOne, playerTwo)

  game.execute(PlaceNextMarkCommand(GridPosition(1)))
  game.execute(PlaceNextMarkCommand(GridPosition(4)))
  game.execute(PlaceNextMarkCommand(GridPosition(2)))
  game.execute(PlaceNextMarkCommand(GridPosition(5)))
  game.execute(PlaceNextMarkCommand(GridPosition(3)))

}
