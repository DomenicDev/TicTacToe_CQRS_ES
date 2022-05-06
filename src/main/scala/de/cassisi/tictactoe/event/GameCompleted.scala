package de.cassisi.tictactoe.event

import de.cassisi.tictactoe.game.GameId
import de.cassisi.tictactoe.player.PlayerId

/**
 *
 * @param gameId the game id
 * @param winner the player id of the winner, null if there is no winner
 */
case class GameCompleted(gameId: GameId, winner: PlayerId)
