package de.cassisi.tictactoe.projection
import java.util.UUID

class InMemoryCurrentStateProjection(private val database: InMemoryGameStateDatabase) extends CurrentStateProjection {

  override def onGameCreated(gameId: UUID, playerOne: UUID, playerTwo: UUID): Unit = {
    database.createGame(gameId, playerOne, playerTwo)
  }

  override def onMarkPlaced(gameId: UUID, playerId: UUID, position: Int): Unit = {
    database.placeMark(gameId, position, playerId)
  }

  override def onGameFinished(gameId: UUID, winner: UUID): Unit = {
    database.gameCompleted(gameId, winner)
  }
}
