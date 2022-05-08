package de.cassisi.tictactoe.player

import de.cassisi.tictactoe.event.{PlayerCreatedEvent, PlayerNameChangedEvent}

class PlayerState {

  var playerId: PlayerId = _
  var playerName: PlayerName = _

  def apply(event: PlayerCreatedEvent): Unit = {
    this.playerId = playerId
    this.playerName = playerName
  }

  def apply(event: PlayerNameChangedEvent): Unit = {
    this.playerName = event.newName
  }

}
