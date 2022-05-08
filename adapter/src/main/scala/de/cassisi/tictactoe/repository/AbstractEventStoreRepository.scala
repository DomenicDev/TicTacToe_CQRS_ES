package de.cassisi.tictactoe.repository

import de.cassisi.tictactoe.event.DomainEvent
import de.cassisi.tictactoe.eventstore.EventStore

abstract class AbstractEventStoreRepository[A <: AggregateRoot[ID], ID <: EntityId](private val eventStore: EventStore) extends EventStoreRepository[A, ID] {

  override def save(aggregate: A): Unit = {
    val aggregateId = aggregate.getId.id
    this.eventStore.storeEvents(aggregateId, aggregate.getChanges, aggregate.getVersionCommitted)
  }

  override def getById(aggregateId: ID): A = {
    val uuid = aggregateId.id
    val events: List[DomainEvent] = this.eventStore.getEvents(uuid)
    buildAggregate(aggregateId, events)
  }

  protected def buildAggregate(aggregateId: ID, events: List[DomainEvent]): A
}
