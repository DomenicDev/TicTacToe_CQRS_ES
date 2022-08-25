package de.cassisi.tictactoe.player

import com.typesafe.scalalogging.Logger
import de.cassisi.tictactoe.common.{AggregateRoot, DomainEvent}
import de.cassisi.tictactoe.player.command.{ChangePlayerNameCommand, RegisterPlayerCommand}
import de.cassisi.tictactoe.player.event.{PlayerRegisteredEvent, PlayerNameChangedEvent}


class PlayerAggregate(playerId: PlayerId, private val events: List[DomainEvent]) extends AggregateRoot[PlayerId](playerId) {

  private val LOGGER = Logger(getClass)

  private val playerState: PlayerState = new PlayerState()

  loadFromHistory(events)

  def execute(command: ChangePlayerNameCommand): Unit = {
    val newName = command.newName
    PlayerName.checkValidity(newName)
    applyChange(new PlayerNameChangedEvent(getUUID, newName))
  }

  override protected def handle(event: DomainEvent): Unit = {
    event match {
      case event: PlayerRegisteredEvent => playerState.apply(event)
      case event: PlayerNameChangedEvent => playerState.apply(event)
      case other => LOGGER.warn(s"unsupported domain event $other")
    }
  }

}


object PlayerAggregate {

  def createNewPlayer(command: RegisterPlayerCommand): PlayerAggregate = {
    // extract fields
    val id = command.playerId
    val playerName = command.name

    // validate inputs
    val playerId = PlayerId.of(id)
    PlayerName.checkValidity(playerName)

    // create and return aggregate
    val playerAggregate = new PlayerAggregate(playerId, List.empty)
    val playerCreatedEvent = new PlayerRegisteredEvent(playerId.uuid, playerName)
    playerAggregate.applyChange(playerCreatedEvent)
    playerAggregate
  }

}