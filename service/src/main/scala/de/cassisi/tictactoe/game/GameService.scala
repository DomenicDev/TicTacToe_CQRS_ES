package de.cassisi.tictactoe.game

import de.cassisi.tictactoe.game.command.{CreateNewGameCommand, PlaceNextMarkCommand}

class GameService(private val repository: GameRepository) {

  def createNewGame(command: CreateNewGameCommand): Unit = {
    val game = GameAggregate.createNewGame(command)
    repository.save(game)
    game.getId
  }

  def placeMark(command: PlaceNextMarkCommand): Unit = {
    val gameId = GameId.of(command.gameId)
    val game = repository.getById(gameId)
    game.execute(command)
    repository.save(game)
  }

}
