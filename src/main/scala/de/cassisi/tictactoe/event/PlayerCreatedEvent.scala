package de.cassisi.tictactoe.event

import de.cassisi.tictactoe.player.{PlayerId, PlayerName}

class PlayerCreatedEvent
(
  private val playerId: PlayerId,
  val playerName: PlayerName,
)
  extends DomainEvent(playerId.id, 0, "player-created-event")