package de.cassisi.tictactoe.event

import de.cassisi.tictactoe.game.GridPosition
import de.cassisi.tictactoe.player.PlayerId

case class MarkPlaced(player: PlayerId, position: GridPosition, mark: MarkPlaced)
