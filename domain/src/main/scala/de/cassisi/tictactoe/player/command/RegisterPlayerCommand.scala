package de.cassisi.tictactoe.player.command

import java.util.UUID

case class RegisterPlayerCommand(playerId: UUID, name: String) extends Serializable
