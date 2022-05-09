package de.cassisi.tictactoe.player.event

import de.cassisi.tictactoe.common.DomainEvent

import java.util.UUID

class PlayerNameChangedEvent
(
  val playerId: UUID,
  val newName: String
) extends DomainEvent("player-name-changed-event")
