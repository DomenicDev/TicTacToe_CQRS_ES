package de.cassisi.tictactoe.service

import de.cassisi.tictactoe.command.ChangePlayerNameCommand
import de.cassisi.tictactoe.player.{PlayerAggregate, PlayerId, PlayerName}

class PlayerService(private val repository: PlayerRepository) {

  def createPlayer(name: PlayerName): Unit = {
    val player = PlayerAggregate.createNewPlayer(name)
    repository.saveNewPlayer(player)
  }

  def updatePlayerName(playerId: PlayerId, newName: PlayerName): Unit = {
    val player = repository.getPlayer(playerId)
    val command = ChangePlayerNameCommand(playerId, newName)
    player.execute(command)
    repository.updatePlayer(player)
  }

}
