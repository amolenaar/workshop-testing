package nl.javadude.monopoly.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Plumbing class for the Game of Monopoly.
 */
@SuppressWarnings("serial")
public class Game implements Serializable {

	private Queue<Player> players = new LinkedList<Player>();
	private Player currentPlayer;
	private Board board = new Board();

	public Board getBoard() {
		return board;
	}

	public Player addPlayer(String name) {
		Player player = new Player(board, name);
		players.add(player);
		return player;
	}

	/**
	 * Initialize the game, set the first player.
	 */
	public void startPlay() {
		currentPlayer = players.poll();
        players.add(currentPlayer);
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

	public void nextPlayer() {
        if (currentPlayer.canEndTurn()) {
            currentPlayer.forceTurnFinish();
            currentPlayer = players.poll();
            players.add(currentPlayer);
            currentPlayer.startTurn();
        }
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
}
