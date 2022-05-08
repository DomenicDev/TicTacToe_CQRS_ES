package de.cassisi.tictactoe.game

import de.cassisi.tictactoe.game.command.{CreateNewGameCommand, PlaceNextMarkCommand}
import de.cassisi.tictactoe.player.PlayerId

class GameService(private val repository: GameRepository) {

  def createNewGame(playerOne: PlayerId, playerTwo: PlayerId): GameId = {
    val gameId = GameId.nextGameId()
    val command = CreateNewGameCommand(gameId, playerOne, playerTwo)
    val game = GameAggregate.createNewGame(command)
    repository.save(game)
    game.getId
  }

  def placeMark(gameId: GameId, position: GridPosition): Unit = {
    val game = repository.getById(gameId)
    val command = PlaceNextMarkCommand(position)
    game.execute(command)
    repository.save(game)
  }

}
