package de.cassisi.tictactoe.event

import de.cassisi.tictactoe.player.{PlayerId, PlayerName}

class PlayerNameChanged(playerId: PlayerId, newName: PlayerName) extends DomainEvent("player-name-changed")
