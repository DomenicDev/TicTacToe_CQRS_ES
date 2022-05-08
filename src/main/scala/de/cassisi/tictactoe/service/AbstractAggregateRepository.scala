package de.cassisi.tictactoe.service

import de.cassisi.tictactoe.event.DomainEvent
import de.cassisi.tictactoe.shared.{AggregateRoot, EntityId}
import de.cassisi.tictactoe.store.EventStore

abstract class AbstractAggregateRepository[A <: AggregateRoot[ID], ID <: EntityId](private val eventStore: EventStore) extends AggregateRepository[A, ID] {

  override def insert(aggregate: A): Unit = {
    val aggregateId = aggregate.getId.id
    eventStore.storeEvents(aggregateId, aggregate.getChanges, -1)
  }

  override def update(aggregate: A): Unit = {
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
