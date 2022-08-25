package de.cassisi.tictactoe.game

import com.typesafe.scalalogging.Logger
import de.cassisi.tictactoe.common.{AggregateRoot, DomainEvent}
import de.cassisi.tictactoe.game.command.{OpenNewGameCommand, PlaceNextMarkCommand}
import de.cassisi.tictactoe.game.event.{GameCompletedEvent, GameOpenedEvent, MarkPlacedEvent, PlayerSwappedEvent}
import de.cassisi.tictactoe.player.PlayerId

class GameAggregate(gameId: GameId, events: List[DomainEvent]) extends AggregateRoot[GameId](gameId) {

  private val logger = Logger(getClass)

  private val gameState = new GameState()
  loadFromHistory(events)


  def execute(command: PlaceNextMarkCommand): Unit = {
    throwIfGameIsAlreadyOver()

    // validate input
    SquarePosition.checkValidity(command.position)

    // convert to domain object
    val position = SquarePosition.of(command.position)

    // check if the requested position is not marked yet
    throwIfAlreadyMarked(position)
    applyChange(new MarkPlacedEvent(gameId.uuid, getCurrentPlayer.uuid, position.position))

    // check if the current player has won
    if (gameState.hasCurrentPlayerWon) {
      applyChange(new GameCompletedEvent(gameId.uuid, getCurrentPlayer.uuid))
      return
    }

    // check if the game is tied
    if (gameState.isTieGame) {
      applyChange(new GameCompletedEvent(gameId.uuid, null))
      return
    }

    // the game has not finished yet, so it's the next player's turn
    val nextPlayer = gameState.determineNextPlayer
    applyChange(new PlayerSwappedEvent(gameId.uuid, nextPlayer.uuid))
  }

  private def throwIfAlreadyMarked(position: SquarePosition): Unit = {
    val currentMarkAtPosition = this.gameState.gameGrid.getMarkAtPosition(position)
    if (currentMarkAtPosition != Mark.EMPTY) {
      throw new IllegalStateException("A mark has already been placed at the requested position")
    }
  }

  private def throwIfGameIsAlreadyOver(): Unit = {
    if (gameState.gameFinished) {
      throw new IllegalStateException("Game has already finished!")
    }
  }

  private def getCurrentPlayer: PlayerId = this.gameState.currentPlayer


  override protected def handle(event: DomainEvent): Unit = {
    event match {
      case event: GameOpenedEvent   => gameState(event)
      case event: MarkPlacedEvent    => gameState(event)
      case event: PlayerSwappedEvent => gameState(event)
      case event: GameCompletedEvent => gameState(event)
      case other => logger.warn(s"unsupported domain event $other")
    }
  }
}


object GameAggregate {

  def createNewGame(command: OpenNewGameCommand): GameAggregate = {
    // extract fields from command
    val gameUuid = command.gameId
    val playerOneUuid = command.playerOne
    val playerTwoUuid = command.playerTwo

    // create domain objects
    val gameId = GameId.of(gameUuid)
    val playerOne = PlayerId.of(playerOneUuid)
    val playerTwo = PlayerId.of(playerTwoUuid)

    // create and return game aggregate
    val gameAggregate = new GameAggregate(gameId, List.empty)
    val gameCreatedEvent = new GameOpenedEvent(gameId.uuid, playerOne.uuid, playerTwo.uuid)
    gameAggregate.applyChange(gameCreatedEvent)
    gameAggregate
  }

}