package de.cassisi.tictactoe.game

import de.cassisi.tictactoe.game.Mark.Mark
import de.cassisi.tictactoe.game.event.{GameCompletedEvent, GameCreatedEvent, MarkPlacedEvent, PlayerSwappedEvent}
import de.cassisi.tictactoe.player.PlayerId

class GameState {

  var gameId: GameId = _
  var playerOne: PlayerId = _
  var playerTwo: PlayerId = _

  var currentPlayer: PlayerId = _
  var gameFinished: Boolean = false

  var gameGrid: GameGrid = _

  def apply(event: GameCreatedEvent): Unit = {
    this.gameId = event.gameId
    this.playerOne = event.playerOne
    this.playerTwo = event.playerTwo
    this.gameFinished = false
    this.currentPlayer = playerOne
    this.gameGrid = GameGrid.createEmptyGrid()
  }

  def apply(event: MarkPlacedEvent): Unit = {
    val position = event.position
    val markToPlace = getMark(currentPlayer)
    this.gameGrid = gameGrid.placeMark(position, markToPlace)
  }

  def apply(event: PlayerSwappedEvent): Unit = {
    this.currentPlayer = event.nextPlayer
  }

  def apply(event: GameCompletedEvent): Unit = {
    this.gameFinished = true
  }


  /********* HELPER METHODS *********/

  def hasCurrentPlayerWon: Boolean = {
    isWinner(this.currentPlayer)
  }

  private def getMark(player: PlayerId): Mark = {
    if (player == playerOne) return Mark.X
    if (player == playerTwo) return Mark.O
    else throw new IllegalArgumentException("specified player is not a player of this game")
  }

  def isWinner(player: PlayerId): Boolean = {
    val playerMark = getMark(player)
    this.gameGrid.checkForWin(playerMark)
  }

  def isTieGame: Boolean = {
    !this.gameGrid.emptySquaresRemaining() &&
      !isWinner(playerOne) &&
      !isWinner(playerTwo)
  }

  def determineNextPlayer: PlayerId = {
    if (this.currentPlayer == playerOne) playerTwo
    else playerOne
  }

}
