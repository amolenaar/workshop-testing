package nl.javadude.monopoly.fixtures;


public class RollDiceActionTable extends CommonFixtureCode {
	
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

}
