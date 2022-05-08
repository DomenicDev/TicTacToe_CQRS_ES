package de.cassisi.tictactoe.game.event

import de.cassisi.tictactoe.event.DomainEvent
import de.cassisi.tictactoe.game.GameId
import de.cassisi.tictactoe.player.PlayerId

/**
 *
 * @param gameId the game id
 * @param winner the player id of the winner, null if there is no winner
 */
class GameCompletedEvent(val gameId: GameId, val winner: PlayerId) extends DomainEvent("game-completed")
