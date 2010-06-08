package nl.javadude.monopoly.domain.squares;

import nl.javadude.monopoly.domain.*;
import nl.javadude.monopoly.domain.squares.AbstractSquare;

/**
 *
 */
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
