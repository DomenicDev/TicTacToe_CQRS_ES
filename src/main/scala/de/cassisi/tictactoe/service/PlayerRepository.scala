package de.cassisi.tictactoe.service

import de.cassisi.tictactoe.player.{PlayerAggregate, PlayerId}

trait PlayerRepository {

  def save(player: PlayerAggregate): Unit

  def getById(playerId: PlayerId): PlayerAggregate

}
