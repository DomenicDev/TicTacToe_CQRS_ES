package de.cassisi.tictactoe.player

import de.cassisi.tictactoe.player.event.{PlayerRegisteredEvent, PlayerNameChangedEvent}

class PlayerState {

  var playerId: PlayerId = _
  var playerName: PlayerName = _

  def apply(event: PlayerRegisteredEvent): Unit = {
    this.playerId = PlayerId.of(event.playerId)
    this.playerName = PlayerName.of(event.playerName)
  }

  def apply(event: PlayerNameChangedEvent): Unit = {
    this.playerName = PlayerName.of(event.newName)
  }

}
