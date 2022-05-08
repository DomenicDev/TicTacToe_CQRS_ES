package de.cassisi.tictactoe.player

import de.cassisi.tictactoe.player.command.{ChangePlayerNameCommand, CreatePlayerCommand}

class PlayerService(private val repository: PlayerRepository) {

  def createPlayer(playerId: PlayerId, name: PlayerName): Unit = {
    val command = CreatePlayerCommand(playerId, name)
    val player = PlayerAggregate.createNewPlayer(command)
    repository.store(player)
  }

  def updatePlayerName(playerId: PlayerId, newName: PlayerName): Unit = {
    val player = repository.getPlayer(playerId)
    val command = ChangePlayerNameCommand(playerId, newName)
    player.execute(command)
    repository.store(player)
  }

}
