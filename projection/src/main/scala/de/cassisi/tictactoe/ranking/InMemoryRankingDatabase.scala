package de.cassisi.tictactoe.ranking


import java.util.UUID
import scala.collection.mutable

class InMemoryRankingDatabase {

  private val rankings: mutable.Map[UUID, Int] = mutable.Map()

  def addWinForPlayer(player: UUID): Unit = {
    // add player if not present
    if (!rankings.contains(player)) {
      rankings.put(player, 0)
    }

    // add win
    val currentWins = rankings(player)
    val newWins = currentWins + 1
    rankings.put(player, newWins)
  }

  def addPlayer(player: UUID): Unit = {
    rankings.put(player, 0)
  }

  def getRankings: Map[UUID, Int] =  rankings.toMap

}
