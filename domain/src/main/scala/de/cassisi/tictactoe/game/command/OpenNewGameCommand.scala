package de.cassisi.tictactoe.game.command

import java.util.UUID

case class OpenNewGameCommand(gameId: UUID, playerOne: UUID, playerTwo: UUID)
