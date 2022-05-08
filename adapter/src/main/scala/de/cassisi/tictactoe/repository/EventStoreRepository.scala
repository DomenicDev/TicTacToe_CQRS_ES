package de.cassisi.tictactoe.repository

trait EventStoreRepository[A <: AggregateRoot[ID], ID <: EntityId] {

  def save(aggregate: A): Unit

  def getById(aggregateId: ID): A


}
