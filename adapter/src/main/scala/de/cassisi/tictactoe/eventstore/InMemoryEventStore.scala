package de.cassisi.tictactoe.eventstore

import de.cassisi.tictactoe.event.DomainEvent

import java.util.UUID
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

class InMemoryEventStore extends EventStore {

  private val store: mutable.Map[UUID, ListBuffer[PersistedEvent]] = mutable.Map()
  private val UNSAVED_VERSION = -1
  private val subscribers: ListBuffer[EventStoreSubscriber] = new ListBuffer()

  override def getEvents(id: UUID): List[DomainEvent] = {
    val persistedEvents = store(id).toList
    val eventData = persistedEvents.map(persistedEvent => persistedEvent.payload)
    eventData
  }

  override def storeEvents(id: UUID, events: List[DomainEvent], expectedVersion: Int): Unit = {
    // if not already existing, create a new entry with the specified id
    if (!this.store.contains(id)) {
      store(id) = new ListBuffer[PersistedEvent]()
    }

    // check for concurrency conflicts
    // if the last persisted event does not match the expected version, there were concurrent changes
    if (this.store(id).nonEmpty && this.store(id).last.version != expectedVersion && expectedVersion != UNSAVED_VERSION) {
      throw new ConcurrencyException()
    }

    // process the specified events
    var eventVersion = expectedVersion

    events.foreach(event => {
      // increase version
      eventVersion += 1

      // store and publish event
      val persistedEvent = buildEventToStore(id, eventVersion, event)
      storeEvent(persistedEvent)
      publishEvent(persistedEvent)
    })
  }

  private def buildEventToStore(key: UUID, version: Int, event: DomainEvent): PersistedEvent = {
    PersistedEvent(key, version, event)
  }

  private def storeEvent(event: PersistedEvent): Unit = {
    this.store(event.aggregateId).append(event)
  }

  def publishEvent(persistedEvent: PersistedEvent): Unit = {
    this.subscribers.foreach(subscriber => subscriber.onEventPublished(persistedEvent))
  }

  override def subscribe(subscriber: EventStoreSubscriber): Unit = {
    this.subscribers.addOne(subscriber)
  }
}

private case class PersistedEvent(aggregateId: UUID, version: Int, payload: DomainEvent)
