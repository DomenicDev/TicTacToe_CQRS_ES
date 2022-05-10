package de.cassisi.tictactoe.gamestate

import de.cassisi.tictactoe.dto.GameStateDTO

import java.util.UUID

trait GameStateRepository {

  def getGameState(gameId: UUID): GameStateDTO

}
