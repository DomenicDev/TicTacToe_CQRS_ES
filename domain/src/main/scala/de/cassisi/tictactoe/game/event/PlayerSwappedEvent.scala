package de.cassisi.tictactoe.game.event

import de.cassisi.tictactoe.common.DomainEvent
import de.cassisi.tictactoe.game.GameId
import de.cassisi.tictactoe.player.PlayerId

import java.util.UUID

class PlayerSwappedEvent(val gameId: UUID, val nextPlayer: UUID) extends DomainEvent("player-swapped-event")
