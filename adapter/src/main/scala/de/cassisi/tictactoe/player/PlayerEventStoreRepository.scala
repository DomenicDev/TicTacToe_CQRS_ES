package de.cassisi.tictactoe.player

import de.cassisi.tictactoe.common.{AbstractEventStoreRepository, DomainEvent}
import de.cassisi.tictactoe.eventstore.EventStore

class PlayerEventStoreRepository(private val eventStore: EventStore) extends AbstractEventStoreRepository[PlayerAggregate, PlayerId](eventStore) {

  override protected def buildAggregate(aggregateId: PlayerId, events: List[DomainEvent]): PlayerAggregate = {
    new PlayerAggregate(aggregateId, events)
  }

}
