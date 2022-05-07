package de.cassisi.tictactoe.player

case class PlayerName(name: String) {

  if (name.isEmpty || name.isBlank) {
    throw new IllegalArgumentException("Player names must not be blank")
  }

}
