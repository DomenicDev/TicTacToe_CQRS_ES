package de.cassisi.tictactoe.player.event

import de.cassisi.tictactoe.event.DomainEvent
import de.cassisi.tictactoe.player.{PlayerId, PlayerName}

class PlayerCreatedEvent
(
  val playerId: PlayerId,
  val playerName: PlayerName,
)
  extends DomainEvent("player-created-event")
