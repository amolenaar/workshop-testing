package nl.javadude.monopoly.domain.squares;

import nl.javadude.monopoly.domain.Dice;
import nl.javadude.monopoly.domain.IOwnable;

/**
 *
 */
public class Utility extends AbstractOwnableSquare {

	public Utility(String name) {
		super(name, 150);
	}

	public long getRent() {
		int utilities = 0;
		for (IOwnable ownable : owner.getPossessions()) {
			if (ownable instanceof Utility) {
				utilities++;
			}
		}
		return utilities > 1 ? Dice.getInstance().view() * 2 : Dice.getInstance().view() * 6;
	}


}
