package de.cassisi.tictactoe.ranking

import de.cassisi.tictactoe.game.event.{GameCompletedEvent, GameOpenedEvent, MarkPlacedEvent, PlayerSwappedEvent}
import de.cassisi.tictactoe.player.event.{PlayerNameChangedEvent, PlayerRegisteredEvent}
import de.cassisi.tictactoe.projector.EventHandler

class RankingProjector(private val database: InMemoryRankingDatabase) extends EventHandler {

  override def handle(event: GameOpenedEvent): Unit = {}

  override def handle(event: MarkPlacedEvent): Unit = {}

  override def handle(event: PlayerSwappedEvent): Unit = {}

  override def handle(event: GameCompletedEvent): Unit = {
    database.addWinForPlayer(event.winner)
  }

  override def handle(event: PlayerRegisteredEvent): Unit = {
    database.addPlayer(event.playerId)
  }

  override def handle(event: PlayerNameChangedEvent): Unit = {}
}
