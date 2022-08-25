package de.cassisi.tictactoe.player

import de.cassisi.tictactoe.player.command.{ChangePlayerNameCommand, RegisterPlayerCommand}

// this is a command handler
class PlayerService(private val repository: PlayerRepository) {

  def handle(command: RegisterPlayerCommand): Unit = {
    val player = PlayerAggregate.createNewPlayer(command)
    repository.store(player)
  }

  def handle(command: ChangePlayerNameCommand): Unit = {
    val playerId = PlayerId.of(command.player)
    val player = repository.getPlayer(playerId)
    player.execute(command)
    repository.store(player)
  }

}
