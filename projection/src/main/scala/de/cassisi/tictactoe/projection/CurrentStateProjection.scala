package de.cassisi.tictactoe.projection


import java.util.UUID

trait CurrentStateProjection {

  def onGameCreated(gameId: UUID, playerOne: UUID, playerTwo: UUID): Unit

  def onMarkPlaced(gameId: UUID, playerId: UUID, position: Int): Unit

  def onGameFinished(gameId: UUID, winner: UUID): Unit

}
