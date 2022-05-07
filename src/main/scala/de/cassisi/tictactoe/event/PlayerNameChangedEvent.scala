package de.cassisi.tictactoe.event

import de.cassisi.tictactoe.player.{PlayerId, PlayerName}

class PlayerNameChangedEvent
(
  private val playerId: PlayerId,
  private val version: Long,
  val newName: PlayerName
) extends DomainEvent( playerId.id, version, "player-name-changed")
