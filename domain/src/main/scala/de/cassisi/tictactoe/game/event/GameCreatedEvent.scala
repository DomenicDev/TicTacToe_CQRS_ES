package de.cassisi.tictactoe.game.event

import de.cassisi.tictactoe.event.DomainEvent
import de.cassisi.tictactoe.game.GameId
import de.cassisi.tictactoe.player.PlayerId

class GameCreatedEvent
(
  val gameId: GameId,
  val playerOne: PlayerId,
  val playerTwo: PlayerId
) extends DomainEvent("game-created-event") {

}
