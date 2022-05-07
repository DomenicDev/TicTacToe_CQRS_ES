package de.cassisi.tictactoe.service

import de.cassisi.tictactoe.command.PlaceNextMarkCommand
import de.cassisi.tictactoe.game.{GameId, GameSessionAggregate, GridPosition}
import de.cassisi.tictactoe.player.PlayerId

class GameService(private val repository: GameRepository) {

  def createNewGame(playerOne: PlayerId, playerTwo: PlayerId): GameId = {
    val game = GameSessionAggregate.createNewGame(playerOne, playerTwo)
    repository.storeGame(game)
    return game.gameId
  }

  def placeMark(gameId: GameId, position: GridPosition): Unit = {
    val game = repository.loadGame(gameId)
    val command = PlaceNextMarkCommand(position)
    game.execute(command)
    repository.storeGame(game)
  }




}
