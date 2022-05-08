package de.cassisi.tictactoe.eventstore

trait EventStoreSubscriber {

  def onEventPublished(event: PersistedEvent): Unit

}
