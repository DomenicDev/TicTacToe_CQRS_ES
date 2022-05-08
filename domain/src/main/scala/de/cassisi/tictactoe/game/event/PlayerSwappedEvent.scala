package de.cassisi.tictactoe.game.event

import de.cassisi.tictactoe.event.DomainEvent
import de.cassisi.tictactoe.game.GameId
import de.cassisi.tictactoe.player.PlayerId

class PlayerSwappedEvent(val gameId: GameId, val nextPlayer: PlayerId) extends DomainEvent("player-swapped-event")
