package de.cassisi.tictactoe.game

class GameRepositoryImpl(private val repository: GameEventStoreRepository) extends GameRepository {

  override def getById(gameId: GameId): GameAggregate = {
    repository.getById(gameId)
  }

  override def save(game: GameAggregate): Unit = {
    repository.save(game)
  }
}
