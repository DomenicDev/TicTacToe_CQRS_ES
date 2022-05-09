package de.cassisi.tictactoe.player

import de.cassisi.tictactoe.common.EntityId

import java.util.UUID

case class PlayerId(id: UUID) extends EntityId(id)

object PlayerId {

  def of(id: UUID): PlayerId = {
    new PlayerId(id)
  }

}
