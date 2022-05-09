package de.cassisi.tictactoe.game.event

import de.cassisi.tictactoe.common.DomainEvent
import de.cassisi.tictactoe.game.GameId
import de.cassisi.tictactoe.player.PlayerId

import java.util.UUID

class GameCreatedEvent
(
  val gameId: UUID,
  val playerOne: UUID,
  val playerTwo: UUID
) extends DomainEvent("game-created-event") {

}
