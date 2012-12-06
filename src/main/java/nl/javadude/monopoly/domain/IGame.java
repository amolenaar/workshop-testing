package nl.javadude.monopoly.domain;

import java.util.*;

public interface IGame
{
    Board getBoard();

    List<ISquare> getSquares();

    Player addPlayer(Player player);

    void startPlay();

    List<Player> getPlayers();

    Player getCurrentPlayer();

    Player rollDice(final int d1, final int d2);

    boolean currentPlayerBuy();

    Player nextPlayer();
}
