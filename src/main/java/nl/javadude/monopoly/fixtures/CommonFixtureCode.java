package nl.javadude.monopoly.fixtures;

import nl.javadude.monopoly.domain.Board;
import nl.javadude.monopoly.domain.Dice;
import nl.javadude.monopoly.domain.Game;
import nl.javadude.monopoly.domain.ISquare;
import nl.javadude.monopoly.domain.Player;
import fit.Fixture;

public class CommonFixtureCode extends Fixture {
	
	private int die1;
	private int die2;
	
	private Game game = new Game();
	
	private Board board = new Board();
	
	private Player player;
	
	public void setPlayer(String name) {
		addPlayerIfNotYetInGame(name);
		player = game.findPlayer(name);
		game.setCurrentPlayer(player);
	}
	
	private void addPlayerIfNotYetInGame(String name) {
		if (!game.playerInGame(name)) {
			player = new Player(name);
			player.startTurn();
			game.add(player);
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
		doPlayAction();
		return player.getCurrentPosition().getName();
	}
	
	public boolean allowedAnotherTurn() {
		return !(player.finishedTurn() || player.isJailed());
	}

	public boolean isInJail() {
		return player.isJailed();
	}

	public void setLocation(String locationName) {
		ISquare newLocation = board.findLocation(locationName);
		player.setCurrentPosition(newLocation);		
	}
	
	public void setCredit(int credit) {
		player.setMoney(credit);
	}
	
	public boolean canBuy() {
		return player.getCurrentPosition().canBuy();
	}
}
