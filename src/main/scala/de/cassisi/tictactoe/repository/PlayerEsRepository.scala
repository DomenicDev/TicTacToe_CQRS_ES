package de.cassisi.tictactoe.repository

import de.cassisi.tictactoe.player.{PlayerAggregate, PlayerId}
import de.cassisi.tictactoe.service.PlayerRepository
import de.cassisi.tictactoe.store.PlayerAggregateRepository

class PlayerEsRepository(private val repository: PlayerAggregateRepository) extends PlayerRepository {

  override def saveNewPlayer(player: PlayerAggregate): Unit = {
    this.repository.insert(player)
  }

  override def updatePlayer(player: PlayerAggregate): Unit = {
    this.repository.update(player)
  }

  override def getPlayer(playerId: PlayerId): PlayerAggregate = {
    this.repository.getById(playerId)
  }
}
