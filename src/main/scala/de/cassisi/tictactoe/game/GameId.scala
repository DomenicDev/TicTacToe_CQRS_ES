package de.cassisi.tictactoe.game

import de.cassisi.tictactoe.shared.EntityId

import java.util.UUID

class GameId(id: UUID) extends EntityId(id)


object GameId {

  def nextGameId(): GameId = {
    new GameId(UUID.randomUUID())
  }
}