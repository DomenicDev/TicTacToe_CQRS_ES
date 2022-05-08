package de.cassisi.tictactoe.game

import com.typesafe.scalalogging.Logger
import de.cassisi.tictactoe.repository.AggregateRoot
import de.cassisi.tictactoe.event.DomainEvent
import de.cassisi.tictactoe.game.command.{CreateNewGameCommand, PlaceNextMarkCommand}
import de.cassisi.tictactoe.game.event.{GameCompletedEvent, GameCreatedEvent, MarkPlacedEvent, PlayerSwappedEvent}
import de.cassisi.tictactoe.player.PlayerId

class GameAggregate(gameId: GameId, events: List[DomainEvent]) extends AggregateRoot[GameId](gameId) {

  private val logger = Logger(getClass)

  private val gameState = new GameState()
  loadFromHistory(events)


  def execute(command: PlaceNextMarkCommand): Unit = {
    if (gameState.gameFinished) {
      throw new IllegalStateException("Game has already finished!")
    }

    // check if the requested position is not marked yet
    val position = command.position
    val currentMarkAtPosition = this.gameState.gameGrid.getMarkAtPosition(position)
    if (currentMarkAtPosition != Mark.EMPTY) {
      throw new IllegalStateException("A mark has already been placed at the requested position")
    }

    applyChange(new MarkPlacedEvent(gameId, getCurrentPlayer, position))

    if (gameState.hasCurrentPlayerWon) {
      applyChange(new GameCompletedEvent(gameId, getCurrentPlayer))
      return
    }

    if (gameState.isTieGame) {
      new GameCompletedEvent(gameId, null)
      return
    }

    // the game has not finished yet, so it's the next player's turn
    val nextPlayer = gameState.determineNextPlayer
    applyChange(new PlayerSwappedEvent(gameId, nextPlayer))
  }




  private def getCurrentPlayer: PlayerId = this.gameState.currentPlayer


  override protected def handle(event: DomainEvent): Unit = {
    event match {
      case event: GameCreatedEvent   => gameState(event)
      case event: MarkPlacedEvent    => gameState(event)
      case event: PlayerSwappedEvent => gameState(event)
      case event: GameCompletedEvent => gameState(event)
      case other => logger.warn(s"unsupported domain event $other")
    }
  }
}


object GameAggregate {

  def createNewGame(command: CreateNewGameCommand): GameAggregate = {
    // extract fields from command
    val gameId = command.gameId
    val playerOne = command.playerOne
    val playerTwo = command.playerTwo

    // create and return game aggregate
    val gameAggregate = new GameAggregate(gameId, List.empty)
    val gameCreatedEvent = new GameCreatedEvent(gameId, playerOne, playerTwo)
    gameAggregate.applyChange(gameCreatedEvent)
    gameAggregate
  }

}