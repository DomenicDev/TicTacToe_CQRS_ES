package de.cassisi.tictactoe.exception

import de.cassisi.tictactoe.event.DomainEvent

class UnsupportedDomainEventException(event: DomainEvent) extends scala.RuntimeException(s"Unsupported Domain Event $event") {

}
