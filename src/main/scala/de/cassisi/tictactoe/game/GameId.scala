package de.cassisi.tictactoe.game

import java.util.UUID

case class GameId(gameId: UUID)


object GameId {

  def nextGameId(): GameId = {
    GameId(UUID.randomUUID())
  }
}