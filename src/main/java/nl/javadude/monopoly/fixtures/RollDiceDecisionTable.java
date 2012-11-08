package nl.javadude.monopoly.fixtures;


public class RollDiceDecisionTable extends CommonFixtureCode {

	private String oldPosition;

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
		return oldPosition;
	}

	// Executed after all properties are set.
	public void execute() {
		oldPosition = getPlayer().getCurrentPosition().getName();
		doPlayAction();
	}

	public String position() {
		return super.getNewPosition();
	}

	public String anotherRoll() {
		return (super.allowedAnotherRoll()) ? "yes" : "no";
	}
}
