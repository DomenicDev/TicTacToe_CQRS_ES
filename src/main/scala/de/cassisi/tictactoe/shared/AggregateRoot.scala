package de.cassisi.tictactoe.shared

import de.cassisi.tictactoe.event.DomainEvent

import scala.collection.mutable.ListBuffer

abstract class AggregateRoot[ID <: EntityId](private val entityId: ID) {

  private val changes: ListBuffer[DomainEvent] = ListBuffer[DomainEvent]()
  private var version: Int = -1

  // the committed version is the version right after rehydration of events (after initialization)
  private var committedVersion: Int = this.version

  def loadFromHistory(history: List[DomainEvent]): Unit = {
    // rehydrate aggregate from specified events
    history.foreach(event => applyChange(event, isNew = false))
    this.committedVersion = version
  }

  def applyChange(event: DomainEvent): Unit = {
    applyChange(event, isNew = true)
  }

  private def applyChange(event: DomainEvent, isNew: Boolean): Unit = {
    if (isNew) changes.append(event)
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

  def clearChanges(): Unit = this.changes.clear()

  def getVersion: Int = this.version

  def getVersionCommitted: Int = this.committedVersion

  def getId: ID = this.entityId

}
