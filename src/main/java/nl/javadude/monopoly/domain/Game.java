package nl.javadude.monopoly.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Plumbing class for the Game of Monopoly.
 */
public class Game {

	private Board board;
	private List<Player> players = new ArrayList<Player>();
	protected Player currentPlayer;
	private int playerPosition;

	public Game() {
		board = new Board();
	}

	public void add(Player player) {
		players.add(player);
		// Add the base money to the player.
		Bank.BANK.pay(1500, player);
		player.setCurrentPosition(Board.START);
		player.setBoard(board);
	}
	
	/**
	 * Initialize the game, set the first player.
	 */
	public void startPlay() {
		playerPosition = new Random().nextInt(players.size());
		currentPlayer = players.get(playerPosition);
		currentPlayer.startTurn();
	}

	public Player getCurrentPlayer() {
		if (currentPlayer.finishedTurn()) {
			nextPlayer();
		}
		return currentPlayer;
	}

	public void setCurrentPlayer(Player player) {
		if (player.finishedTurn()) player.startTurn();
		currentPlayer = player;
	}

	void nextPlayer() {
		playerPosition = ++playerPosition % players.size();
		currentPlayer = players.get(playerPosition);
		currentPlayer.startTurn();
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
