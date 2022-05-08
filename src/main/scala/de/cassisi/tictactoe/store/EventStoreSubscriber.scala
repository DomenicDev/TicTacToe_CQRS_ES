package de.cassisi.tictactoe.store

trait EventStoreSubscriber {

  def onEventPublished(event: PersistedEvent): Unit

}
