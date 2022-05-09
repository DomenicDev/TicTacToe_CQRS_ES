package de.cassisi.tictactoe.game.command

import java.util.UUID

case class CreateNewGameCommand(gameId: UUID, playerOne: UUID, playerTwo: UUID)
