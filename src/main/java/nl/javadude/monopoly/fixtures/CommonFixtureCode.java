package nl.javadude.monopoly.fixtures;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import nl.javadude.monopoly.domain.Dice;
import nl.javadude.monopoly.domain.Game;
import nl.javadude.monopoly.domain.ISquare;
import nl.javadude.monopoly.domain.Player;

import org.apache.commons.lang.SerializationException;

public class CommonFixtureCode {

	private int die1;
	private int die2;

	private Game game = new Game();

	private Player player;

	public void setPlayer(String name) {
		if (game.getCurrentPlayer() == null || !name.equals(game.getCurrentPlayer().getName())) {
			addPlayerIfNotYetInGame(name);
			player = game.findPlayer(name);
			game.setCurrentPlayer(player);
		//	player.startTurn();
		}
	}

	private void addPlayerIfNotYetInGame(String name) {
		if (!game.playerInGame(name)) {
			player = game.addPlayer(name);
		//	player.startTurn();
		}
	}

	public void setDie1(int die1) {
		this.die1 = die1;
	}

	public void setDie2(int die2) {
		this.die2 = die2;
	}

	public void doPlayAction() {
		Dice.getInstance().setDiceValues(die1, die2);
		game.move();
	}

	public String getNewPosition() {
		return player.getCurrentPosition().getName();
	}

	public boolean allowedAnotherTurn() {
	    if(player.isJailed()) return false;
	   // if(player.isFinishedTurn()) return false;
	    if(die1 != die2) return false;

		return true;
	}

	public boolean allowedAnotherRoll() {
		return false;// player.isRollAllowed();
	}

	public boolean isInJail() {
		return player.isJailed();
	}

	public void setLocation(String locationName) {
		ISquare newLocation = game.getBoard().findLocation(locationName);
		player.setCurrentPosition(newLocation);
	}

	public void setCredit(int credit) {
		player.setMoney(credit);
	}

	public boolean canBuy() {
		return player.getCurrentPosition().forSale();
	}


	public void newGame() {
		game = new Game();
	}

	public void saveGame() {
		save(game, "game.data");
	}

	public void getSavedGame() {
		game = (Game) restore("game.data");
		player = game.getCurrentPlayer();
	}

	protected void save(Object obj, String fileName) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			// Write to disk with FileOutputStream
			fos = new FileOutputStream(fileName);

			// Write object with ObjectOutputStream
			oos = new ObjectOutputStream(fos);

			// Write object out to disk
			oos.writeObject(obj);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			throw new RuntimeException("Problem saving object state to "
					+ fileName);
		} finally {
			try {
				if (oos != null) {
					oos.close();
				}
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
				// ignore
			}
		}
	}

	protected Object restore(String fileName) {
		// Read from disk using FileInputStream
		FileInputStream fis = null;
		ObjectInputStream ois = null;

		try {
			fis = new FileInputStream(fileName);

			// Read object using ObjectInputStream
			ois = new ObjectInputStream(fis);

			// Read an object
			return ois.readObject();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			throw new SerializationException("Problem restoring object state from "
					+ fileName);
		} catch (ClassNotFoundException e) {
			throw new SerializationException("Problem restoring object state from "
					+ fileName + "because of ClassNotFoundException");
		} finally {
			try {
				if (ois != null) {
					ois.close();
				}
				if (fis != null) {
					fis.close();
				}
			} catch (IOException ioe) {
				// ignore
			}
		}
	}

	protected Game getGame() {
		return game;
	}

	protected Player getPlayer() {
		return player;
	}
}
