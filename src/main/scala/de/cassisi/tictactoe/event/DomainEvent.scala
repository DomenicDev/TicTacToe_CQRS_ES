package de.cassisi.tictactoe.event

import java.time.Instant
import java.util.UUID

case class DomainEvent(entityId: UUID, version: Long, eventType: String) {

  val eventId: UUID = UUID.randomUUID()
  val eventTime: Instant = Instant.now()

}
