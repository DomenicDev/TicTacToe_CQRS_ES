package de.cassisi.tictactoe.game

import de.cassisi.tictactoe.event.DomainEvent
import de.cassisi.tictactoe.eventstore.EventStore
import de.cassisi.tictactoe.repository.AbstractEventStoreRepository

class GameEventStoreRepository(eventStore: EventStore) extends AbstractEventStoreRepository[GameAggregate, GameId](eventStore) {

  override protected def buildAggregate(aggregateId: GameId, events: List[DomainEvent]): GameAggregate = {
    new GameAggregate(aggregateId, events)
  }
}
