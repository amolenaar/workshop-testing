package nl.javadude.monopoly.domain.squares;

import nl.javadude.monopoly.domain.IAutomatic;
import nl.javadude.monopoly.domain.Player;

/**
 *
 */
@SuppressWarnings("serial")
public class PoliceAgent extends AbstractSquare implements IAutomatic {

	public PoliceAgent(Jail jail) {
		super("Police Agent / Go To Jail");
	}

	public void execute(Player player) {
		//player.gotSentToJail();
	}
}
