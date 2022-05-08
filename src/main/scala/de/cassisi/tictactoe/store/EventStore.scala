package de.cassisi.tictactoe.store

import de.cassisi.tictactoe.event.DomainEvent

import java.util.UUID
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

trait EventStore {

  def storeEvents(id: UUID, events: List[DomainEvent], expectedVersion: Int): Unit
  def getEvents(id: UUID): List[DomainEvent]

}

class EventStoreImpl extends EventStore {

  private def store: mutable.Map[UUID, ListBuffer[PersistedEvent]] = mutable.Map()


  override def getEvents(id: UUID): List[DomainEvent] = {
    val persistedEvents = store(id).toList
    val eventData = persistedEvents.map(persistedEvent => persistedEvent.payload)
    return eventData
  }

  override def storeEvents(id: UUID, events: List[DomainEvent], expectedVersion: Int): Unit = ???
}

case class PersistedEvent(aggregateId: UUID, version: Long, payload: DomainEvent)
