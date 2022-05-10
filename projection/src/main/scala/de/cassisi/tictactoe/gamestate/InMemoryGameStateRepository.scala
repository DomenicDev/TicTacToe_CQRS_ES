package de.cassisi.tictactoe.gamestate
import de.cassisi.tictactoe.dto.GameStateDTO
import de.cassisi.tictactoe.projection.InMemoryGameStateDatabase

import java.util.UUID

class InMemoryGameStateRepository(private val database: InMemoryGameStateDatabase) extends GameStateRepository {

  override def getGameState(gameId: UUID): GameStateDTO = {
    val gameField = database.getGameField(gameId)
    val completed = database.getGame(gameId).completed
    val winner = database.getGame(gameId).winner
    GameStateDTO(gameField, completed, winner)
  }

}
