package de.cassisi.tictactoe.projector

import de.cassisi.tictactoe.game.event.{GameCompletedEvent, GameOpenedEvent, MarkPlacedEvent, PlayerSwappedEvent}
import de.cassisi.tictactoe.player.event.{PlayerRegisteredEvent, PlayerNameChangedEvent}

trait EventHandler {

  // game domain events
  def handle(event: GameOpenedEvent): Unit
  def handle(event: MarkPlacedEvent): Unit
  def handle(event: PlayerSwappedEvent): Unit
  def handle(event: GameCompletedEvent): Unit

  // player domain events
  def handle(event: PlayerRegisteredEvent): Unit
  def handle(event: PlayerNameChangedEvent): Unit

}
