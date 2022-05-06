package de.cassisi.tictactoe.player

import de.cassisi.tictactoe.command.ChangePlayerNameCommand

class PlayerAggregate(val playerId: PlayerId, var playerName: PlayerName ) {


  def execute(command: ChangePlayerNameCommand): Unit = {
    if (!(this.playerId.equals(command.player))) {
      throw new IllegalArgumentException("trying to update the wrong identity")
    }

    this.playerName = command.newName
  }


}
