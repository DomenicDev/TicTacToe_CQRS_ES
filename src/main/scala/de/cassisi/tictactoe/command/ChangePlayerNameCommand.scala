package de.cassisi.tictactoe.command

import de.cassisi.tictactoe.player.{PlayerId, PlayerName}

case class ChangePlayerNameCommand(player: PlayerId, newName: PlayerName)
