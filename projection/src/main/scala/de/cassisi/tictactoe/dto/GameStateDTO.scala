package de.cassisi.tictactoe.dto

import java.util.UUID

case class GameStateDTO(gameGrid: Array[Int], completed: Boolean, winner: UUID)
