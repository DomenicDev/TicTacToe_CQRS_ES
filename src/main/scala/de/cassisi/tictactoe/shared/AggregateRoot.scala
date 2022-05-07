package de.cassisi.tictactoe.shared

import de.cassisi.tictactoe.event.DomainEvent

import scala.collection.mutable.ListBuffer

abstract class AggregateRoot(private val events: List[DomainEvent]) {

  private val changes: ListBuffer[DomainEvent] = ListBuffer[DomainEvent]()
  private var version: Long = -1

  // rehydrate aggregate from specified events
  events.foreach(event => applyChange(event, isNew = false))

  def applyChange(event: DomainEvent): Unit = {
    applyChange(event, isNew = true)
  }

  private def applyChange(event: DomainEvent, isNew: Boolean): Unit = {
    if (!isNew) changes.append(event)
    handle(event)
    increaseVersion()
  }

  def increaseVersion(): Unit = version += 1

  /**
   * Abstract domain event handle method to be implemented
   * in concrete aggregates.
   * @param event the event to be handled
   */
  protected def handle(event: DomainEvent): Unit


  def getChanges: List[DomainEvent] = changes.toList

  def getVersion: Long = this.version

}
