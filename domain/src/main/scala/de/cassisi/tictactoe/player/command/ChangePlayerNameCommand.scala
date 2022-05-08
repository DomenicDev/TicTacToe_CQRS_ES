package de.cassisi.tictactoe.player.command

import de.cassisi.tictactoe.player.{PlayerId, PlayerName}

case class ChangePlayerNameCommand(player: PlayerId, newName: PlayerName)
