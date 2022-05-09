package de.cassisi.tictactoe.player.command

import java.util.UUID

case class CreatePlayerCommand(playerId: UUID, name: String) extends Serializable
