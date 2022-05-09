package de.cassisi.tictactoe.game.event

import de.cassisi.tictactoe.common.DomainEvent
import de.cassisi.tictactoe.game.GameId
import de.cassisi.tictactoe.player.PlayerId

import java.util.UUID

/**
 *
 * @param gameId the game id
 * @param winner the player id of the winner, null if there is no winner
 */
class GameCompletedEvent(val gameId: UUID, val winner: UUID) extends DomainEvent("game-completed")
