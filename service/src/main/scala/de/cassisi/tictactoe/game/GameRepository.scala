package de.cassisi.tictactoe.game

trait GameRepository {

  def save(game: GameAggregate): Unit

  def getById(gameId: GameId): GameAggregate

}
