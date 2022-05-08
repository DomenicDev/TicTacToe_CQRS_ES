package de.cassisi.tictactoe.player.event

import de.cassisi.tictactoe.event.DomainEvent
import de.cassisi.tictactoe.player.{PlayerId, PlayerName}

class PlayerNameChangedEvent
(
  val playerId: PlayerId,
  val newName: PlayerName
) extends DomainEvent("player-name-changed-event")
