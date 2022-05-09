package de.cassisi.tictactoe.game.event

import de.cassisi.tictactoe.common.DomainEvent
import de.cassisi.tictactoe.game.{GameId, SquarePosition}
import de.cassisi.tictactoe.player.PlayerId

import java.util.UUID

class MarkPlacedEvent(val gameId: UUID, val player: UUID, val position: Int) extends DomainEvent("mark-placed-event")
