package de.cassisi.tictactoe.projector
import de.cassisi.tictactoe.game.event.{GameCompletedEvent, GameOpenedEvent, MarkPlacedEvent, PlayerSwappedEvent}
import de.cassisi.tictactoe.player.event.{PlayerRegisteredEvent, PlayerNameChangedEvent}
import de.cassisi.tictactoe.projection.CurrentStateProjection

class CurrentGameStateProjector(private val projection: CurrentStateProjection) extends EventHandler {


  override def handle(event: GameOpenedEvent): Unit = {
    projection.onGameCreated(event.gameId, event.playerOne, event.playerTwo)
  }

  override def handle(event: MarkPlacedEvent): Unit = {
    projection.onMarkPlaced(event.gameId, event.player, event.position)
  }

  override def handle(event: PlayerSwappedEvent): Unit = {
    // ignore
  }

  override def handle(event: GameCompletedEvent): Unit = {
    projection.onGameFinished(event.gameId, event.winner)
  }

  override def handle(event: PlayerRegisteredEvent): Unit = {
    // ignore
  }

  override def handle(event: PlayerNameChangedEvent): Unit = {
    // ignore
  }
}
