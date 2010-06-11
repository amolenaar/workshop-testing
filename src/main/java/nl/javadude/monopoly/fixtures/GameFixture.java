package nl.javadude.monopoly.fixtures;

import nl.javadude.monopoly.domain.Game;
import nl.javadude.monopoly.domain.Player;

import org.apache.commons.lang.SerializationException;

public class GameFixture extends CommonDoFixtureCode {

	public void startGameWithPlayers(String[] playerNames) {
	  game = new Game();
	  for (String name : playerNames) {
		  Player player;
		  try {
		   player = (Player) restore(name+".data");
		  } catch (SerializationException ex) {
			  player = new Player(name);
		  }
		  game.add(player);
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
		return player.owns(name);
	}
	
	public boolean buy() {
		return player.buy();	
	}
	
	public long budget() {
		return player.getMoney();
	}


}
