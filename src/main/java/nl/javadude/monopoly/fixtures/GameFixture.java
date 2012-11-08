package nl.javadude.monopoly.fixtures;

import nl.javadude.monopoly.domain.Game;

import org.apache.commons.lang.SerializationException;

public class GameFixture extends CommonFixtureCode {

	public void startGameWithPlayers(String[] playerNames) {
	  newGame();
	  Game game = getGame();
	  for (String name : playerNames) {
		  try {
			  // TODO: use in memory pool.
			  PlayerPool.newPlayer(name, game);

		  } catch (SerializationException ex) {
			  game.addPlayer(name);
		  }
	  }
	}

	public void player(String name) {
		super.setPlayer(name);
	}

	public void firstDie(int die1) {
		super.setDie1(die1);
	}

	public void secondDie(int die2) {
		super.setDie2(die2);
	}

	public void play() {
		super.doPlayAction();
	}

	public String position() {
		return super.getNewPosition();
	}

	public boolean repeat() {
		return super.allowedAnotherTurn();
	}

	public boolean jail() {
		return super.isInJail();
	}

	public boolean ownsStreet(String name) {
		return getPlayer().owns(name);
	}

	public boolean buy() {
		return getPlayer().buy();
	}

	public long budget() {
		return getPlayer().getMoney();
	}


}
