package nl.javadude.monopoly.domain.squares;

import nl.javadude.monopoly.domain.IAutomatic;
import nl.javadude.monopoly.domain.Player;

/**
 *
 */
@SuppressWarnings("serial")
public class Start extends AbstractSquare implements IAutomatic {

	public Start() {
		super("GO");
	}

	/**
	 * Player receives salary
	 * @param player
	 */
	public void execute(Player player) {
	}

}
