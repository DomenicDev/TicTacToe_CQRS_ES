package de.cassisi.tictactoe.player

import com.typesafe.scalalogging.Logger
import de.cassisi.tictactoe.event.DomainEvent
import de.cassisi.tictactoe.repository.AggregateRoot
import de.cassisi.tictactoe.player.command.{ChangePlayerNameCommand, CreatePlayerCommand}
import de.cassisi.tictactoe.player.event.{PlayerCreatedEvent, PlayerNameChangedEvent}


class PlayerAggregate(private val playerId: PlayerId, private val events: List[DomainEvent]) extends AggregateRoot[PlayerId](playerId) {

  private val LOGGER = Logger(getClass)

  private val playerState: PlayerState = new PlayerState()

  loadFromHistory(events)

  def execute(command: ChangePlayerNameCommand): Unit = {
    if (!(getId.equals(command.player))) {
      throw new IllegalArgumentException("trying to update the wrong identity")
    }

    applyChange(new PlayerNameChangedEvent(getId, command.newName))
  }

  override protected def handle(event: DomainEvent): Unit = {
    event match {
      case event: PlayerCreatedEvent => playerState.apply(event)
      case event: PlayerNameChangedEvent => playerState.apply(event)
      case other => LOGGER.warn(s"unsupported domain event $other")
    }
  }

}


object PlayerAggregate {

  def createNewPlayer(command: CreatePlayerCommand): PlayerAggregate = {
    val playerId = command.playerId
    val playerName = command.name

    val playerAggregate = new PlayerAggregate(playerId, List.empty)
    val playerCreatedEvent = new PlayerCreatedEvent(playerId, playerName)
    playerAggregate.applyChange(playerCreatedEvent)
    return playerAggregate
  }

}