package de.cassisi.tictactoe.game

case class GridPosition(position: Int) {

  // validate specified position
  if (position < 1 || position > 9) {
    throw new IllegalArgumentException("Grid position must be between 1 and 9")
  }

}

