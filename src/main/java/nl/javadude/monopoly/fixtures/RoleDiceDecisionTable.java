package nl.javadude.monopoly.fixtures;


public class RoleDiceDecisionTable extends CommonFixtureCode {
	
	public void setPlayer(String name) {
		super.setPlayer(name);
	}
	
	public void setFirstDie(int die1) {
		super.setDie1(die1);
	}
	
	public void setSecondDie(int die2) {
		super.setDie2(die2);
	}
	
	public void setLocation(String locationName) {
		super.setLocation(locationName);
	}
	
	public String oldPosition() {
		return super.getOldPosition();
	}
	
	public String position() {
		return super.getNewPosition();
	}
	
	public String anotherTurn() {
		return (super.allowedAnotherTurn()) ? "yes" : "no";
	}
}
