package de.cassisi.tictactoe.player

class PlayerRepositoryImpl(private val repository: PlayerEventStoreRepository) extends PlayerRepository {

  override def store(player: PlayerAggregate): Unit = {
    this.repository.save(player)
  }

  override def getPlayer(playerId: PlayerId): PlayerAggregate = {
    this.repository.getById(playerId)
  }
}
