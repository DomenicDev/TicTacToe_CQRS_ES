package de.cassisi.tictactoe.player

import de.cassisi.tictactoe.event.{PlayerCreatedEvent, PlayerNameChangedEvent}

class PlayerState {

  var playerId: PlayerId = _
  var playerName: PlayerName = _
  var version: Long = -1

  def apply(event: PlayerCreatedEvent): Unit = {
    this.playerId = playerId
    this.playerName = playerName
    this.version = 0
  }

  def apply(event: PlayerNameChangedEvent): Unit = {
    this.playerName = event.newName
    this.version += 1
  }

}
