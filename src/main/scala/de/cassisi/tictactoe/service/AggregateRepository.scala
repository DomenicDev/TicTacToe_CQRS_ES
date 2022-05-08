package de.cassisi.tictactoe.service

import de.cassisi.tictactoe.shared.{AggregateRoot, EntityId}

trait AggregateRepository[A <: AggregateRoot[ID], ID <: EntityId] {

  def insert(aggregate: A): Unit

  def update(aggregate: A): Unit

  def getById(aggregateId: ID): A


}
