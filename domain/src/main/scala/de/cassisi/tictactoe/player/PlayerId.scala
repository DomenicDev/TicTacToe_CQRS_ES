package de.cassisi.tictactoe.player

import de.cassisi.tictactoe.repository.EntityId

import java.util.UUID

class PlayerId(id: UUID) extends EntityId(id)

object PlayerId {

  def next(): PlayerId = {
    new PlayerId(UUID.randomUUID())
  }

}
