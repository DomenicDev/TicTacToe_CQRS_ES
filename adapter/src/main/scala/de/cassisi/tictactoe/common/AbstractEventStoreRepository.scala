package de.cassisi.tictactoe.common

import de.cassisi.tictactoe.eventstore.EventStore

abstract class AbstractEventStoreRepository[A <: AggregateRoot[ID], ID <: EntityId](private val eventStore: EventStore) extends EventStoreRepository[A, ID] {

  override def save(aggregate: A): Unit = {
    val aggregateId = aggregate.getId.uuid
    this.eventStore.storeEvents(aggregateId, aggregate.getChanges, aggregate.getVersionCommitted)
  }

  override def getById(aggregateId: ID): A = {
    val uuid = aggregateId.uuid
    val events: List[DomainEvent] = this.eventStore.getEvents(uuid)
    buildAggregate(aggregateId, events)
  }

  protected def buildAggregate(aggregateId: ID, events: List[DomainEvent]): A
}
