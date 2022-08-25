package de.cassisi.tictactoe.projector

import de.cassisi.tictactoe.common.DomainEvent
import de.cassisi.tictactoe.eventstore.EventStoreSubscriber
import de.cassisi.tictactoe.game.event.{GameCompletedEvent, GameOpenedEvent, MarkPlacedEvent, PlayerSwappedEvent}
import de.cassisi.tictactoe.player.event.{PlayerRegisteredEvent, PlayerNameChangedEvent}

import scala.collection.mutable.ListBuffer

class SimpleEventSubscriber extends EventStoreSubscriber {

  private val handlers: ListBuffer[EventHandler] = ListBuffer()

  override def onEventPublished(event: DomainEvent): Unit = {
    event match {
      case event: GameOpenedEvent => handlers.foreach(handler => handler.handle(event))
      case event: MarkPlacedEvent => handlers.foreach(handler => handler.handle(event))
      case event: PlayerSwappedEvent => handlers.foreach(handler => handler.handle(event))
      case event: GameCompletedEvent => handlers.foreach(handler => handler.handle(event))
      case event: PlayerRegisteredEvent => handlers.foreach(handler => handler.handle(event))
      case event: PlayerNameChangedEvent => handlers.foreach(handler => handler.handle(event))
    }
  }

  def addHandler(handler: EventHandler): Unit = {
    this.handlers.append(handler)
  }
}
