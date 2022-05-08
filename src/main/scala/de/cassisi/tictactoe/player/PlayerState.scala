package de.cassisi.tictactoe.player

import de.cassisi.tictactoe.event.{PlayerCreatedEvent, PlayerNameChangedEvent}

class PlayerState {

  var playerId: PlayerId = _
  var playerName: PlayerName = _

  def apply(event: PlayerCreatedEvent): Unit = {
    this.playerId = event.playerId
    this.playerName = event.playerName
  }

  def apply(event: PlayerNameChangedEvent): Unit = {
    this.playerName = event.newName
  }

}
