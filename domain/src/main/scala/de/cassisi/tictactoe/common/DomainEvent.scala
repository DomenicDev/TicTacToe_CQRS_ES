package de.cassisi.tictactoe.common

import java.time.Instant
import java.util.UUID

class DomainEvent(val typeOfEvent: String) extends Message {

  val eventId: UUID = UUID.randomUUID()
  val eventTime: Instant = Instant.now()
  var version: Long = -1

}
