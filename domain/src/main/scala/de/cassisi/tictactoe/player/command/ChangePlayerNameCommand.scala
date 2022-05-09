package de.cassisi.tictactoe.player.command

import de.cassisi.tictactoe.common.Command

import java.util.UUID

case class ChangePlayerNameCommand(player: UUID, newName: String) extends Command
