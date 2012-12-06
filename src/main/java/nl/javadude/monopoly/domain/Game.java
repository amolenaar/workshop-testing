package nl.javadude.monopoly.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Plumbing class for the Game of Monopoly.
 */
@SuppressWarnings("serial")
public class Game implements Serializable, IGame
{

	private List<Player> players = new ArrayList<Player>();
	private Player currentPlayer;
	private final Board board = new Board();

	public Board getBoard() {
		return board;
	}

	public void addPlayer(Player player) {
		players.add(player);
		return player;
	}

	/**
	 * Initialize the game, set the first player.
	 */
	public void startPlay() {
		currentPlayer = players.get(0);
		currentPlayer.startTurn();
	}

	public List<Player> getPlayers() {
		return new ArrayList<Player>(players);
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player player) {
		if (player.isFinishedTurn()) player.startTurn();
		currentPlayer = player;
	}

	public Player nextPlayer() {
		int playerPosition = (players.indexOf(currentPlayer) + 1) % players.size();
		currentPlayer.forceTurnFinish();
		currentPlayer = players.get(playerPosition);
		currentPlayer.startTurn();
        return currentPlayer;
	}

	public boolean playerInGame(String name) {
		return (findPlayer(name) != null) ? true : false;
	}

	public Player findPlayer(String name) {
		for (Player p : players) {
			if (p.getName().equals(name)) {
				return p;
			}
		}
		return null;
	}

    public List<ISquare> getSquares()
    {
        return getBoard().getSquares();
    }

    public Player rollDice(int d1, int d2)
    {
        Dice dice = Dice.INSTANCE;
       	dice.setDiceValues(d1, d2);
       	currentPlayer.move(dice);
       	return currentPlayer;
    }

    public boolean currentPlayerBuy()
    {
        return currentPlayer.buy();
    }
}
