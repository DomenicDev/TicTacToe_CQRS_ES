package de.cassisi.tictactoe.eventstore

import de.cassisi.tictactoe.common.DomainEvent

trait EventStoreSubscriber {

  def onEventPublished(event: DomainEvent): Unit

}
