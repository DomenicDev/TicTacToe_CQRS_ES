package de.cassisi.tictactoe.game.command

import de.cassisi.tictactoe.game.GameId
import de.cassisi.tictactoe.player.PlayerId

case class CreateNewGameCommand(gameId: GameId, playerOne: PlayerId, playerTwo: PlayerId)
