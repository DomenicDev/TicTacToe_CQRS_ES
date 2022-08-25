package de.cassisi.tictactoe.player.event

import de.cassisi.tictactoe.common.DomainEvent
import de.cassisi.tictactoe.player.{PlayerId, PlayerName}

import java.util.UUID

class PlayerRegisteredEvent
(
  val playerId: UUID,
  val playerName: String,
)
  extends DomainEvent("player-created-event")
