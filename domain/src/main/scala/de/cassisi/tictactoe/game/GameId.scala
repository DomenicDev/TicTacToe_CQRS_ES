package de.cassisi.tictactoe.game

import de.cassisi.tictactoe.common.EntityId

import java.util.UUID

case class GameId(id: UUID) extends EntityId(id)


object GameId {

  def of(id: UUID): GameId = {
    new GameId(id)
  }
}