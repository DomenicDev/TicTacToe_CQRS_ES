package de.cassisi.tictactoe.player

import de.cassisi.tictactoe.player.PlayerName.checkValidity

case class PlayerName(name: String) {
  checkValidity(name)
}

object PlayerName {

  def of(name: String): PlayerName = PlayerName(name)

  def checkValidity(name: String): Unit = {
    if (name.isEmpty || name.isBlank || name.length < 3) {
      throw new IllegalArgumentException("Player names must have at least 3 characters")
    }
  }
}
