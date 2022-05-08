package de.cassisi.tictactoe.player

trait PlayerRepository {

  def store(player: PlayerAggregate): Unit

  def getPlayer(playerId: PlayerId): PlayerAggregate

}
