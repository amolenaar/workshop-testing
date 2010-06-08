package nl.javadude.monopoly.domain.squares;

import nl.javadude.monopoly.domain.*;
import nl.javadude.monopoly.domain.squares.Jail;

/**
 *
 */
public class PoliceAgent extends AbstractSquare implements IAutomatic {
	
	public PoliceAgent(Jail jail) {
		super("Police Agent / Go To Jail");
	}

	public void execute(Player player) {
		//player.gotSentToJail();
	}
}
