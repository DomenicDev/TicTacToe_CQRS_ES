package de.cassisi.tictactoe.handler

import de.cassisi.tictactoe.dto.GameStateDTO
import de.cassisi.tictactoe.gamestate.GameStateRepository

import java.util.UUID

class QueryHandler(private val gameStateRepository: GameStateRepository) {

  def getCurrentGameState(gameId: UUID): GameStateDTO = {
    gameStateRepository.getGameState(gameId)
  }

}
