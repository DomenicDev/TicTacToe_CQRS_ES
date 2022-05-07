package de.cassisi.tictactoe.service

import de.cassisi.tictactoe.game.{GameId, GameSessionAggregate}

trait GameRepository {

  def loadGame(gameId: GameId): GameSessionAggregate

  def storeGame(game: GameSessionAggregate): Unit

}
