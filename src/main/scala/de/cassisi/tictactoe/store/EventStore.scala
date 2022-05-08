package de.cassisi.tictactoe.store

import de.cassisi.tictactoe.event.DomainEvent

import java.util.UUID

trait EventStore {

  def storeEvents(id: UUID, events: List[DomainEvent], expectedVersion: Int): Unit
  def getEvents(id: UUID): List[DomainEvent]

  def subscribe(subscriber: EventStoreSubscriber): Unit

}