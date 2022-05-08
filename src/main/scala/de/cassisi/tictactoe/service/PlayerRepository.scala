package de.cassisi.tictactoe.service

import de.cassisi.tictactoe.player.{PlayerAggregate, PlayerId}

trait PlayerRepository {

  def saveNewPlayer(player: PlayerAggregate): Unit

  def updatePlayer(player: PlayerAggregate): Unit

  def getPlayer(playerId: PlayerId): PlayerAggregate

}
