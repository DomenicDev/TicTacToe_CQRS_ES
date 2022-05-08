package de.cassisi.tictactoe.store

import de.cassisi.tictactoe.event.DomainEvent
import de.cassisi.tictactoe.player.{PlayerAggregate, PlayerId}
import de.cassisi.tictactoe.service.AbstractAggregateRepository

class PlayerAggregateRepository(private val eventStore: EventStore) extends AbstractAggregateRepository[PlayerAggregate, PlayerId](eventStore) {

  override protected def buildAggregate(aggregateId: PlayerId, events: List[DomainEvent]): PlayerAggregate = {
    new PlayerAggregate(aggregateId, events)
  }

}
