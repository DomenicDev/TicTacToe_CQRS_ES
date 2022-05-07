package de.cassisi.tictactoe.player

import de.cassisi.tictactoe.command.ChangePlayerNameCommand
import de.cassisi.tictactoe.event.{DomainEvent, PlayerCreatedEvent, PlayerNameChangedEvent}
import de.cassisi.tictactoe.shared.AggregateRoot


class PlayerAggregate(private val events: List[DomainEvent]) extends AggregateRoot(events) {

  private val playerState: PlayerState = new PlayerState()

  def execute(command: ChangePlayerNameCommand): Unit = {
    if (!(getPlayerId.equals(command.player))) {
      throw new IllegalArgumentException("trying to update the wrong identity")
    }

    applyChange(new PlayerNameChangedEvent(getPlayerId, playerState.version+1, command.newName))
  }

  override protected def handle(event: DomainEvent): Unit = {
    event match {
      case event: PlayerCreatedEvent => playerState(event)
      case event: PlayerNameChangedEvent => playerState(event)
    }
  }

  private def getPlayerId: PlayerId = this.playerState.playerId

}


object PlayerAggregate {

  def createNewPlayer(playerName: PlayerName): PlayerAggregate = {
    val playerAggregate = new PlayerAggregate(List.empty)
    val playerId = PlayerId.next()
    val playerCreatedEvent = new PlayerCreatedEvent(playerId, playerName)
    playerAggregate.applyChange(playerCreatedEvent)
    return playerAggregate
  }

}