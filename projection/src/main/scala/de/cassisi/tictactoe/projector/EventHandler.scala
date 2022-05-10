package de.cassisi.tictactoe.projector

import de.cassisi.tictactoe.game.event.{GameCompletedEvent, GameCreatedEvent, MarkPlacedEvent, PlayerSwappedEvent}
import de.cassisi.tictactoe.player.event.{PlayerCreatedEvent, PlayerNameChangedEvent}

trait EventHandler {

  // game domain events
  def handle(event: GameCreatedEvent): Unit
  def handle(event: MarkPlacedEvent): Unit
  def handle(event: PlayerSwappedEvent): Unit
  def handle(event: GameCompletedEvent): Unit

  // player domain events
  def handle(event: PlayerCreatedEvent): Unit
  def handle(event: PlayerNameChangedEvent): Unit

}
