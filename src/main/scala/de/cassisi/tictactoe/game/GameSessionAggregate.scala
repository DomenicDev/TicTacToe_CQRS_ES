package de.cassisi.tictactoe.game

import de.cassisi.tictactoe.command.PlaceNextMarkCommand
import de.cassisi.tictactoe.event.GameCompleted
import de.cassisi.tictactoe.game.Mark.Mark
import de.cassisi.tictactoe.player.PlayerId

class GameSessionAggregate(val gameId: GameId, val playerOne: PlayerId, val playerTwo: PlayerId) {

  private var gameGrid: GameGrid = GameGrid.createEmptyGrid()

  private var gameFinished: Boolean = false
  private var currentPlayer: PlayerId = playerOne


  def execute(command: PlaceNextMarkCommand): Unit = {
    if (this.gameFinished) {
      throw new IllegalStateException("Game has already finished!")
    }

    // check if the requested position is not marked yet
    val position = command.position
    val currentMarkAtPosition = gameGrid.getMarkAtPosition(position)
    if (currentMarkAtPosition != Mark.EMPTY) {
      throw new IllegalStateException("A mark has already been placed at the requested position")
    }

    // place mark
    val markToPlace = getMark(currentPlayer)
    gameGrid = gameGrid.placeMark(position, markToPlace)

    if (isWinner(this.currentPlayer)) {
      new GameCompleted(gameId, getCurrentPlayer())
      println("WINNER " + getCurrentPlayer())
      return
    }

    if (isTieGame()) {
      new GameCompleted(gameId, null)
      return
    }

    // the game has not finished yet, so it's the next player's turn
    nextPlayer()
  }


  private def isWinner(player: PlayerId): Boolean = {
    val playerMark = getMark(player)
    this.gameGrid.checkForWin(playerMark)
  }

  private def isTieGame(): Boolean = {
    !(this.gameGrid.emptySquaresRemaining()) &&
      !(isWinner(playerOne)) &&
      !(isWinner(playerTwo))
  }

  private def getCurrentPlayer(): PlayerId = this.currentPlayer

  private def nextPlayer(): Unit = {
    this.currentPlayer =
      if (this.currentPlayer == playerOne) playerTwo
      else playerOne
  }

  private def getMark(player: PlayerId): Mark = {
    if (player == playerOne) return Mark.X
    if (player == playerTwo) return Mark.O
    else throw new IllegalArgumentException("specified player is not a player of this game")
  }

}


object GameSessionAggregate {

  def createNewGame(playerOne: PlayerId, playerTwo: PlayerId): GameSessionAggregate =
    new GameSessionAggregate(GameId.nextGameId(), playerOne, playerTwo)

}