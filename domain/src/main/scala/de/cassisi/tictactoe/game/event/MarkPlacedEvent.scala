package de.cassisi.tictactoe.game.event

import de.cassisi.tictactoe.event.DomainEvent
import de.cassisi.tictactoe.game.{GameId, GridPosition}
import de.cassisi.tictactoe.player.PlayerId

class MarkPlacedEvent(val gameId: GameId, val player: PlayerId, val position: GridPosition) extends DomainEvent("mark-placed-event")
