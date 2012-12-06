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
    private TurnState turnState = TurnState.END_TURN;

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
        currentPlayer.activate();
		turnState = turnState.transition(currentPlayer);
	}

	public List<Player> getPlayers() {
		return new ArrayList<Player>(players);
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player player) {
		currentPlayer = player;
	}

    public void nextPlayer() {
        if (turnState == TurnState.TURN_ACTION ||
                turnState == TurnState.ROLLED_SAME_ONCE ||
                turnState == TurnState.ROLLED_SAME_TWICE) {
            currentPlayer.deactivate();
            currentPlayer = players.poll();
            players.add(currentPlayer);
            currentPlayer.activate();
            turnState.transition(currentPlayer);
        }
    }

	public boolean playerInGame(String name) {
		return (findPlayer(name) != null);
	}

	public Player findPlayer(String name) {
		for (Player p : players) {
			if (p.getName().equals(name)) {
				return p;
			}
		}
		return null;
	}

    public void move() {
        if (turnState == TurnState.START_TURN ||
                turnState == TurnState.ROLLED_SAME_ONCE ||
                turnState ==TurnState.ROLLED_SAME_TWICE) {
            board.move(getCurrentPlayer(), Dice.INSTANCE.view());
            turnState = turnState.transition(getCurrentPlayer());
        }
    }
}
