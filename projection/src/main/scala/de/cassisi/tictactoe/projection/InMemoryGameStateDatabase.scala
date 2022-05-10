package de.cassisi.tictactoe.projection

import java.util.UUID
import java.util.stream.IntStream
import scala.collection.mutable

class InMemoryGameStateDatabase {

  private val games: mutable.Map[UUID, Game] = mutable.Map()

  def createGame(id: UUID, playerOne: UUID, playerTwo: UUID): Unit = {
    val game = Game()
    game.playerOne = playerOne
    game.playerTwo = playerTwo
    games.put(id, game)
  }

  def placeMark(gameId: UUID, position: Int, playerId: UUID): Unit = {
    games(gameId).squares.update(position-1, games(gameId).getValue(playerId))
  }

  def gameCompleted(gameId: UUID, playerId: UUID): Unit = {
    games(gameId).winner = playerId
    games(gameId).completed = true
  }

  def getGameField(gameId: UUID): Array[Int] = {
    games(gameId).squares.clone()
  }

  def getGame(gameId: UUID): Game = {
    games(gameId)
  }

}

case class Game() {

  var playerOne: UUID = _
  var playerTwo: UUID = _

  var completed: Boolean = false
  var winner: UUID = _

  var squares: Array[Int] = IntStream.range(1, 10).map(_ => 0).toArray

  def getValue(playerId: UUID): Int = {
    if (playerId == playerOne) 1
    else 2
  }

}
