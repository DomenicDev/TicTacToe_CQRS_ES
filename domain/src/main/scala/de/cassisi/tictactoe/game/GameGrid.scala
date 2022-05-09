package de.cassisi.tictactoe.game

import de.cassisi.tictactoe.game.Mark.{EMPTY, Mark}


case class GameGrid(private val squares: List[Square]) {

  def placeMark(position: SquarePosition, markToPlace: Mark): GameGrid = {
    val positionToUpdate = getIndexForPosition(position)
    val updatedGrid: List[Square] = getUpdatedSquares(positionToUpdate, markToPlace)
    GameGrid(updatedGrid)
  }

  def getMarkAtPosition(pos: SquarePosition): Mark = {
    val position = getIndexForPosition(pos)
    squares(position).mark
  }

  def emptySquaresRemaining(): Boolean = {
    this.squares.count(square => square.mark == EMPTY) > 0
  }

  private def getUpdatedSquares(positionToUpdate: Int, markToPlace: Mark): List[Square] = {
    squares.updated(positionToUpdate, Square(markToPlace))
  }

  private def getIndexForPosition(gridPosition: SquarePosition): Int = {
    gridPosition.position - 1
  }

  def checkForWin(player: Mark): Boolean = {
    // check horizontal
    if ((squares.head.mark == player && squares(1).mark == player && squares(2).mark == player) ||
      (squares(3).mark == player && squares(4).mark == player && squares(5).mark == player) ||
      (squares(6).mark == player && squares(7).mark == player && squares(8).mark == player)) {
      return true
    }
    // check vertical
    if ((squares.head.mark == player && squares(3).mark == player && squares(6).mark == player) ||
      (squares(1).mark == player && squares(4).mark == player && squares(7).mark == player) ||
      (squares(2).mark == player && squares(5).mark == player && squares(8).mark == player)) {
      return true
    }
    // check diagonal
    if ((squares.head.mark == player && squares(4).mark == player && squares(8).mark == player) ||
      (squares(6).mark == player && squares(4).mark == player && squares(2).mark == player)) {
      return true
    }
    return false
  }

}

object GameGrid {

  def createEmptyGrid(): GameGrid = {
    GameGrid(List[Square](
      Square(Mark.EMPTY), Square(Mark.EMPTY), Square(Mark.EMPTY),
      Square(Mark.EMPTY), Square(Mark.EMPTY), Square(Mark.EMPTY),
      Square(Mark.EMPTY), Square(Mark.EMPTY), Square(Mark.EMPTY)
    ))
  }

}
