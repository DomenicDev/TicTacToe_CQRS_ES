package de.cassisi.tictactoe.player

import java.util.UUID

case class PlayerId(id: UUID)

object PlayerId {

  def next(): PlayerId = {
    PlayerId(UUID.randomUUID())
  }

}
