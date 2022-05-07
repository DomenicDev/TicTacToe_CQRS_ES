package de.cassisi.tictactoe.service

import de.cassisi.tictactoe.player.{PlayerAggregate, PlayerName}

class PlayerService(private val repository: PlayerRepository) {

  def createPlayer(name: PlayerName): Unit = {

    val player = PlayerAggregate.createNewPlayer(name)
    repository.save(player)

  }

}
