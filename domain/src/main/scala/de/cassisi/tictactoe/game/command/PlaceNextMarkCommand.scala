package de.cassisi.tictactoe.game.command

import java.util.UUID

case class PlaceNextMarkCommand(gameId: UUID, position: Int)
