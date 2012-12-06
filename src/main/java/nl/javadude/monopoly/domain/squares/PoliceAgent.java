package nl.javadude.monopoly.domain.squares;

import nl.javadude.monopoly.domain.IAutomatic;
import nl.javadude.monopoly.domain.Player;

/**
 *
 */
@SuppressWarnings("serial")
public class PoliceAgent extends AbstractSquare implements IAutomatic {

    private final Jail jail;

    public PoliceAgent(Jail jail) {
		super("Police Agent / Go To Jail");
        this.jail = jail;
    }

	public void execute(Player player) {
		player.setCurrentPosition(jail);
	}
}
