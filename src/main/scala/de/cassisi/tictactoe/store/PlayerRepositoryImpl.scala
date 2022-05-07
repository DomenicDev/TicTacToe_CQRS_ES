package de.cassisi.tictactoe.store

import de.cassisi.tictactoe.event.DomainEvent
import de.cassisi.tictactoe.player.{PlayerAggregate, PlayerId}
import de.cassisi.tictactoe.service.PlayerRepository

import java.util.UUID
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

class PlayerRepositoryImpl extends PlayerRepository {

  private def store: mutable.Map[UUID, ListBuffer[DomainEvent]] = mutable.Map()

  override def save(player: PlayerAggregate): Unit = {
    val events = player.getDomainEvents

  }

  override def getById(playerId: PlayerId): PlayerAggregate = {

  }
}
