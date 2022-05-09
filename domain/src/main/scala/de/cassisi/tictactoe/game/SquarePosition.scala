package de.cassisi.tictactoe.game

import de.cassisi.tictactoe.game.SquarePosition.checkValidity

case class SquarePosition(position: Int) {
  checkValidity(position)
}

object SquarePosition {

  def checkValidity(position: Int): Unit = {
    if (position < 1 || position > 9) {
      throw new IllegalArgumentException("Grid position must be between 1 and 9")
    }
  }

  def of(position: Int): SquarePosition = SquarePosition(position)

}

