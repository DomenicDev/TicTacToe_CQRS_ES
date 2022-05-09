package de.cassisi.tictactoe.game

import de.cassisi.tictactoe.eventstore.EventStore
import de.cassisi.tictactoe.common.{AbstractEventStoreRepository, DomainEvent}

class GameEventStoreRepository(eventStore: EventStore) extends AbstractEventStoreRepository[GameAggregate, GameId](eventStore) {

  override protected def buildAggregate(aggregateId: GameId, events: List[DomainEvent]): GameAggregate = {
    new GameAggregate(aggregateId, events)
  }
}
