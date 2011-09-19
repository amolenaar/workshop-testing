package nl.javadude.monopoly.fixtures;

import nl.javadude.monopoly.domain.Dice;
import nl.javadude.monopoly.domain.Game;
import nl.javadude.monopoly.domain.ISquare;
import nl.javadude.monopoly.domain.Player;
import fitlibrary.DoFixture;

public class CommonDoFixtureCode extends DoFixture {

	private int die1;
	private int die2;

	protected Game game;
	protected Player player;

	public CommonDoFixtureCode() {
		newGame();
	}
	
	public void newGame() {
		game = new Game();
	}
	
	public void setPlayer(String name) {
		addPlayerIfNotYetInGame(name);
		player = game.findPlayer(name);
		game.setCurrentPlayer(player);
	}

	private void addPlayerIfNotYetInGame(String name) {
		if (!game.playerInGame(name)) {
			player = game.addPlayer(name);
			player.startTurn();
		}
	}

	public void setDie1(int die1) {
		this.die1 = die1;
	}

	public void setDie2(int die2) {
		this.die2 = die2;
	}

	public String getOldPosition() {
		return player.getCurrentPosition().getName();
	}

	public void doPlayAction() {
		Dice.getInstance().setDiceValues(die1, die2);
		player.move(Dice.getInstance());
	}

	public String getNewPosition() {
		return player.getCurrentPosition().getName();
	}

	public boolean allowedAnotherTurn() {
		return !(player.isFinishedTurn() || player.isJailed());
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
		return player.getCurrentPosition().canBuy();
	}
	
}
